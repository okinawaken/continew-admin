package top.continew.admin.business.study.client.http.model.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 储物柜逾期支付（/mp/cabinetGrip/v2/lease/overdue/pay）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomCabinetLeaseOverduePayClientResp {

    /**
     * 支付单ID
     */
    private Long payId;

    /**
     * 微信支付参数
     */
    private StudyRoomWxPayParamClientResp payParam;

}
