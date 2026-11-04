package top.continew.admin.business.study.client.http.model.req;

import lombok.Data;

/**
 * 确认签退结算套餐（/mp/study/userPackage/confirm）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomUserPackageConfirmReq {

    /**
     * 当前签到ID（必填）
     */
    private Long userSignId;

    /**
     * 选中的结算套餐ID（settlement 返回的 userPackageList[].id）（必填）
     */
    private Long userPackageId;

}
