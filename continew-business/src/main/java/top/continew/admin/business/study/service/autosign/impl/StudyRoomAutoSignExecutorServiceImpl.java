package top.continew.admin.business.study.service.autosign.impl;

import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.stereotype.Service;
import top.continew.admin.business.study.client.http.StudyRoomSignV2Client;
import top.continew.admin.business.study.constant.StudyRoomCacheConstants;
import top.continew.admin.business.study.filter.FilterResultDTO;
import top.continew.admin.business.study.filter.autosign.StudyRoomAutoSignFilterChain;
import top.continew.admin.business.study.model.context.StudyRoomAutoSignContext;
import top.continew.admin.business.study.model.req.StudyRoomSignSeatQueryClientReq;
import top.continew.admin.business.study.model.resp.StudyRoomClientResult;
import top.continew.admin.business.study.model.resp.StudyRoomSignSeatListClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomUserPackageClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomUserReserveClientResp;
import top.continew.admin.business.study.service.autosign.StudyRoomAutoSignExecutorService;
import top.continew.starter.cache.redisson.util.RedisUtils;
import top.continew.starter.core.exception.BusinessException;
import top.continew.starter.json.jackson.util.JsonUtils;

import java.util.Objects;

/**
 * 自动签到执行器实现
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class StudyRoomAutoSignExecutorServiceImpl implements StudyRoomAutoSignExecutorService {

    private final StudyRoomSignV2Client signV2Client;
    private final StudyRoomAutoSignFilterChain filterChain;

    @Override
    public void execute(Long userId) {
        String lockKey = StudyRoomCacheConstants.AUTO_SIGN_EXECUTE_LOCK + userId;
        RLock lock = RedisUtils.getClient().getLock(lockKey);
        if (!lock.tryLock()) {
            log.error("自动签到正在执行中，加锁失败，忽略，userId={}", userId);
            return;
        }

        StudyRoomAutoSignContext context = new StudyRoomAutoSignContext(userId);

        try {
            FilterResultDTO<?> filterResultDTO = filterChain.doFilter(context);
            if (!filterResultDTO.isSuccess()) {
                return;
            }

            StudyRoomUserReserveClientResp reserveRespDTO = context.getReserveRespDTO();
            StudyRoomUserPackageClientResp userPackageRespDTO = context.getUserPackageRespDTO();

            StudyRoomSignSeatQueryClientReq reqDTO = new StudyRoomSignSeatQueryClientReq();
            reqDTO.setQrCode(StrUtil.EMPTY);
            reqDTO.setVenueId(reserveRespDTO.getVenueId());
            reqDTO.setSignInUserPackageId(userPackageRespDTO.getSignInUserPackageId());
            reqDTO.setSignAnotherFlag(0);
            StudyRoomClientResult<StudyRoomSignSeatListClientResp> resultDTO =
                signV2Client.studySeatList(reqDTO, userId);
            if (Objects.isNull(resultDTO) || Objects.isNull(resultDTO.getData())
                || !resultDTO.getData().isSignInSuccess()) {
                throw new BusinessException("签到失败");
            }
        } catch (Exception e) {
            log.error("自动签到执行失败，userId={}，context={}", userId, JsonUtils.toJsonStr(context), e);
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
}
