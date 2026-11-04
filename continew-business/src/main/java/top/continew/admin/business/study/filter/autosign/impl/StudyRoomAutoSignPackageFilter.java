package top.continew.admin.business.study.filter.autosign.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import top.continew.admin.business.study.client.http.StudyRoomSignV2Client;
import top.continew.admin.business.study.enums.StudyRoomAutoSignFilterOrderEnum;
import top.continew.admin.business.study.filter.FilterResultDTO;
import top.continew.admin.business.study.filter.autosign.StudyRoomAutoSignFilter;
import top.continew.admin.business.study.model.context.StudyRoomAutoSignContext;
import top.continew.admin.business.study.model.resp.StudyRoomClientResult;
import top.continew.admin.business.study.model.resp.StudyRoomIndexClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomUserPackageClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomUserReserveClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomVenueClientResp;
import top.continew.starter.core.exception.BusinessException;

import java.util.Objects;

/**
 * 签到扣费套餐查询过滤器
 * <p>
 * 按待签到预约的门店查询签到首页，取 {@code signInUserPackage.signInUserPackageId} 作为签到扣费套餐。
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Component
@RequiredArgsConstructor
public class StudyRoomAutoSignPackageFilter implements StudyRoomAutoSignFilter {

    private final StudyRoomSignV2Client signV2Client;

    @Override
    public int getOrder() {
        return StudyRoomAutoSignFilterOrderEnum.SIGN_IN_PACKAGE.getValue();
    }

    @Override
    public FilterResultDTO<?> doFilter(StudyRoomAutoSignContext context) {
        StudyRoomUserReserveClientResp reserveClientResp = context.getReserveClientResp();
        StudyRoomVenueClientResp venueClientResp = context.getVenueClientResp();

        StudyRoomClientResult<StudyRoomIndexClientResp> resultDTO =
            signV2Client.studyIndex(venueClientResp.getVenueLng(), venueClientResp.getVenueLat(),
                reserveClientResp.getVenueId(), null, null, context.getUserId());
        if (Objects.isNull(resultDTO.getData())) {
            throw new BusinessException("签到首页返回为空");
        }

        StudyRoomUserPackageClientResp userPackageClientResp =
            resultDTO.getData().getSignInUserPackage();
        context.setUserPackageClientResp(userPackageClientResp);
        return FilterResultDTO.success(userPackageClientResp);
    }
}
