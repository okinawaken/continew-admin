package top.continew.admin.business.study.client.http.model.req;

import lombok.Data;

/**
 * 签到选座座位图（/mp/study/seat/list）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomSignSeatQueryClientReq {

    /**
     * 门店前台二维码内容（必填）
     */
    private String qrCode;

    /**
     * 门店ID
     */
    private Integer venueId;

    /**
     * 签到扣费套餐ID（用户套餐ID）
     */
    private Long signInUserPackageId;

    /**
     * 是否加签：1 是 0 否
     */
    private Integer signAnotherFlag;
}
