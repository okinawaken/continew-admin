package top.continew.admin.business.study.filter.autoreserve.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import top.continew.admin.business.study.client.http.v2.StudyRoomReserveV2Client;
import top.continew.admin.business.study.enums.StudyRoomAutoReserveFilterOrderEnum;
import top.continew.admin.business.study.enums.StudyRoomReserveStatusEnum;
import top.continew.admin.business.study.filter.FilterResultDTO;
import top.continew.admin.business.study.filter.autoreserve.StudyRoomAutoReserveFilter;
import top.continew.admin.business.study.model.context.StudyRoomAutoReserveContext;
import top.continew.admin.business.study.model.resp.StudyRoomAutoReserveTaskInfoResp;
import top.continew.admin.business.study.model.resp.StudyRoomClientResult;
import top.continew.admin.business.study.model.resp.StudyRoomReserveCheckClientResp;
import top.continew.starter.core.exception.BusinessException;

import java.util.Objects;

/**
 * 预约资格校验过滤器
 *
 * @author bixiu
 * @since 4.2.0
 */
@Component
@RequiredArgsConstructor
public class StudyRoomAutoReserveEligibilityFilter implements StudyRoomAutoReserveFilter {

    private final StudyRoomReserveV2Client reserveV2Client;

    @Override
    public FilterResultDTO<?> doFilter(StudyRoomAutoReserveContext context) {
        StudyRoomAutoReserveTaskInfoResp taskInfoResp = context.getTaskInfoResp();
        StudyRoomClientResult<StudyRoomReserveCheckClientResp> resultDTO =
            reserveV2Client.reserveCheck(taskInfoResp.getUserId());
        if (Objects.isNull(resultDTO.getData())) {
            throw new BusinessException("预约资格返回为空");
        }

        StudyRoomReserveCheckClientResp reserveCheckClientResp = resultDTO.getData();
        if (!StudyRoomReserveStatusEnum.isCanReserve(reserveCheckClientResp.getReserveState())) {
            return FilterResultDTO.fail("无预约资格");
        }
        return FilterResultDTO.success(reserveCheckClientResp);
    }

    @Override
    public int getOrder() {
        return StudyRoomAutoReserveFilterOrderEnum.ELIGIBILITY.getValue();
    }
}
