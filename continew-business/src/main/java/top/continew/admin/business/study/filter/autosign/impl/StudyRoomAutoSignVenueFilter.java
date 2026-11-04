package top.continew.admin.business.study.filter.autosign.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import top.continew.admin.business.study.client.http.StudyRoomVenueV2Client;
import top.continew.admin.business.study.enums.StudyRoomAutoSignFilterOrderEnum;
import top.continew.admin.business.study.filter.FilterResultDTO;
import top.continew.admin.business.study.filter.autosign.StudyRoomAutoSignFilter;
import top.continew.admin.business.study.model.context.StudyRoomAutoSignContext;
import top.continew.admin.business.study.model.resp.StudyRoomClientResult;
import top.continew.admin.business.study.model.resp.StudyRoomUserReserveClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomVenueClientResp;
import top.continew.starter.core.exception.BusinessException;

import java.util.Objects;

/**
 * 签到门店校验过滤器
 * <p>
 * 按待签到预约的门店 ID 查询门店详情，校验门店存在并写入执行上下文，
 * 后续签到首页使用该门店的经纬度作为当前定位。
 *
 * @author bixiu
 * @since 4.2.0
 */
@Component
@RequiredArgsConstructor
public class StudyRoomAutoSignVenueFilter implements StudyRoomAutoSignFilter {

    private final StudyRoomVenueV2Client venueV2Client;

    @Override
    public int getOrder() {
        return StudyRoomAutoSignFilterOrderEnum.VENUE.getValue();
    }

    @Override
    public FilterResultDTO<?> doFilter(StudyRoomAutoSignContext context) {
        StudyRoomUserReserveClientResp userReserveClientResp = context.getReserveClientResp();
        StudyRoomClientResult<StudyRoomVenueClientResp> resultDTO =
            venueV2Client.reserveVenueDetail(userReserveClientResp.getVenueId(),
                context.getUserId());
        if (Objects.isNull(resultDTO.getData())) {
            throw new BusinessException("门店信息返回为空");
        }

        StudyRoomVenueClientResp venueClientResp = resultDTO.getData();
        context.setVenueClientResp(venueClientResp);
        return FilterResultDTO.success(venueClientResp);
    }
}
