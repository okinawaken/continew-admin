package top.continew.admin.business.study.model.context;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomUserPackageClientResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomUserReserveClientResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomVenueClientResp;

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
    private StudyRoomUserReserveClientResp reserveRespDTO;

    /**
     * 签到门店
     */
    private StudyRoomVenueClientResp venueRespDTO;

    /**
     * 签到扣费套餐
     */
    private StudyRoomUserPackageClientResp userPackageRespDTO;
}
