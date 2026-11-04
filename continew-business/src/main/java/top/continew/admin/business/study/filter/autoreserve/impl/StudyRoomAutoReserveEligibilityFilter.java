package top.continew.admin.business.study.filter.autoreserve.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import top.continew.admin.business.study.client.http.StudyRoomReserveV2Client;
import top.continew.admin.business.study.enums.StudyRoomAutoReserveFilterOrderEnum;
import top.continew.admin.business.study.enums.StudyRoomReserveStatusEnum;
import top.continew.admin.business.study.filter.FilterResultDTO;
import top.continew.admin.business.study.filter.autoreserve.StudyRoomAutoReserveFilter;
import top.continew.admin.business.study.model.context.StudyRoomAutoReserveContext;
import top.continew.admin.business.study.model.resp.StudyRoomAutoReserveTaskInfoResp;
import top.continew.admin.business.study.model.resp.StudyRoomClientResult;
import top.continew.admin.business.study.model.resp.StudyRoomReserveCheckClientResp;

/**
 * 预约资格校验过滤器
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Component
@RequiredArgsConstructor
public class StudyRoomAutoReserveEligibilityFilter implements StudyRoomAutoReserveFilter {

    private final StudyRoomReserveV2Client reserveV2Client;

    @Override
    public FilterResultDTO<?> doFilter(StudyRoomAutoReserveContext context) {
        StudyRoomAutoReserveTaskInfoResp taskRespDTO = context.getTaskInfoResp();
        StudyRoomClientResult<StudyRoomReserveCheckClientResp> resultDTO =
            reserveV2Client.reserveCheck(taskRespDTO.getUserId());
        StudyRoomReserveCheckClientResp reserveCheckRespDTO = resultDTO.getData();

        if (!StudyRoomReserveStatusEnum.isCanReserve(reserveCheckRespDTO.getReserveState())) {
            return FilterResultDTO.fail("无预约资格");
        }
        return FilterResultDTO.success(reserveCheckRespDTO);
    }

    @Override
    public int getOrder() {
        return StudyRoomAutoReserveFilterOrderEnum.ELIGIBILITY.getValue();
    }
}
