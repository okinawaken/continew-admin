package top.continew.admin.business.study.client.http.model.req;

import lombok.Data;

/**
 * 签退可选结算套餐（/mp/study/settlePackage/select）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomSettlePackageReq {

    /**
     * 门店ID（必填）
     */
    private Integer venueId;

    /**
     * 当前签到ID
     */
    private Long userSignId;
}
