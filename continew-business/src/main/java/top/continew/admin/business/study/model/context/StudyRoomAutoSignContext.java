package top.continew.admin.business.study.model.context;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomUserPackageResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomUserReserveResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomVenueResp;

/**
 * 自动签到执行上下文
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@RequiredArgsConstructor
public class StudyRoomAutoSignContext {

    /**
     * 系统用户 ID
     */
    private final Long userId;

    /**
     * 待签到预约
     */
    private StudyRoomUserReserveResp reserveRespDTO;

    /**
     * 签到门店
     */
    private StudyRoomVenueResp venueRespDTO;

    /**
     * 签到扣费套餐
     */
    private StudyRoomUserPackageResp userPackageRespDTO;
}
