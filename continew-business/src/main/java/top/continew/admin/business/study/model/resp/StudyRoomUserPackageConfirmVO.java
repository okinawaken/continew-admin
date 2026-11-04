package top.continew.admin.business.study.model.resp;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * StudyRoomUserPackageConfirmResp 视图对象
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomUserPackageConfirmVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 欠费分钟数
     */
    private Integer unpaidMinute;

    /**
     * 结算使用的用户套餐ID
     */
    private Long userPackageId;

    /**
     * 签退类型
     */
    private Integer signOutType;

    /**
     * 提示文案
     */
    private String studyTipText;

    /**
     * 结算明细
     */
    private StudyRoomSettleDataVO settleData;
}
