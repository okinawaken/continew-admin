package top.continew.admin.business.study.model.req;

import lombok.Data;

/**
 * 签到选座确认（/mp/study/seat/confirm）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomSeatConfirmClientReq {

    /**
     * 座位ID（签到座位图中的 seat.id）（必填）
     */
    private Long seatId;

    /**
     * 当前经度（可传门店经度）
     */
    private Double currentLng;

    /**
     * 当前纬度（可传门店纬度）
     */
    private Double currentLat;

    /**
     * 扣费套餐ID（签到首页 signInUserPackage.id）（必填）
     */
    private Long signInUserPackageId;

    /**
     * 是否再签一个座位：1 是 0 否
     */
    private Integer signAnotherFlag;

}
