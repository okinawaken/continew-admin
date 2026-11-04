package top.continew.admin.business.study.model.resp;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * StudyRoomCabinetLeaseOverduePayRespDTO 视图对象
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomCabinetLeaseOverduePayVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 支付单ID
     */
    private Long payId;

    /**
     * 微信支付参数
     */
    private StudyRoomWxPayParamVO payParam;
}
