package top.continew.admin.business.study.filter.autoreserve.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import top.continew.admin.business.study.client.http.StudyRoomVenueV2Client;
import top.continew.admin.business.study.enums.StudyRoomAutoReserveFilterOrderEnum;
import top.continew.admin.business.study.enums.StudyRoomReserveStatusEnum;
import top.continew.admin.business.study.filter.FilterResultDTO;
import top.continew.admin.business.study.filter.autoreserve.StudyRoomAutoReserveFilter;
import top.continew.admin.business.study.model.context.StudyRoomAutoReserveContext;
import top.continew.admin.business.study.model.resp.StudyRoomAutoReserveTaskInfoResp;
import top.continew.admin.business.study.model.resp.StudyRoomClientResult;
import top.continew.admin.business.study.model.resp.StudyRoomVenueClientResp;
import top.continew.starter.core.exception.BusinessException;

import java.util.Objects;

/**
 * 预约门店校验过滤器
 * <p>
 * 查询门店详情并校验门店是否可预约，结果缓存到执行上下文。
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Component
@RequiredArgsConstructor
public class StudyRoomAutoReserveVenueFilter implements StudyRoomAutoReserveFilter {

    private final StudyRoomVenueV2Client studyRoomVenueV2Client;

    @Override
    public int getOrder() {
        return StudyRoomAutoReserveFilterOrderEnum.VENUE.getValue();
    }

    @Override
    public FilterResultDTO<?> doFilter(StudyRoomAutoReserveContext context) {
        StudyRoomAutoReserveTaskInfoResp taskInfoResp = context.getTaskInfoResp();
        StudyRoomClientResult<StudyRoomVenueClientResp> resultDTO = studyRoomVenueV2Client
            .reserveVenueDetail(taskInfoResp.getVenueId(), taskInfoResp.getUserId());
        if (Objects.isNull(resultDTO.getData())) {
            throw new BusinessException("门店数据返回为空");
        }
        StudyRoomVenueClientResp venueClientResp = resultDTO.getData();
        context.setVenueClientResp(venueClientResp);
        if (!StudyRoomReserveStatusEnum.isCanReserve(venueClientResp.getVenueReserveState())) {
            return FilterResultDTO.fail("门店当前不可预约");
        }
        return FilterResultDTO.success(venueClientResp);
    }
}
