/*
 * Copyright (c) 2022-present Charles7c Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package top.continew.admin.business.study.service.autosign.impl;

import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.stereotype.Service;
import top.continew.admin.business.study.client.http.StudyRoomSignV2Client;
import top.continew.admin.business.study.client.model.StudyRoomApiResultDTO;
import top.continew.admin.business.study.client.model.StudyRoomSignSeatListRespDTO;
import top.continew.admin.business.study.client.model.StudyRoomUserPackageRespDTO;
import top.continew.admin.business.study.client.model.StudyRoomUserReserveRespDTO;
import top.continew.admin.business.study.constant.StudyRoomCacheConstants;
import top.continew.admin.business.study.filter.FilterResultDTO;
import top.continew.admin.business.study.filter.autosign.StudyRoomAutoSignFilterChain;
import top.continew.admin.business.study.model.context.StudyRoomAutoSignContext;
import top.continew.admin.business.study.model.req.StudyRoomSignSeatQueryReqDTO;
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

            StudyRoomUserReserveRespDTO reserveRespDTO = context.getReserveRespDTO();
            StudyRoomUserPackageRespDTO userPackageRespDTO = context.getUserPackageRespDTO();

            StudyRoomSignSeatQueryReqDTO reqDTO = new StudyRoomSignSeatQueryReqDTO();
            reqDTO.setQrCode(StrUtil.EMPTY);
            reqDTO.setVenueId(reserveRespDTO.getVenueId());
            reqDTO.setSignInUserPackageId(userPackageRespDTO.getSignInUserPackageId());
            reqDTO.setSignAnotherFlag(0);
            StudyRoomApiResultDTO<StudyRoomSignSeatListRespDTO> resultDTO =
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
