package top.continew.admin.business.study.client.http.model.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 储物柜下单结果（/mp/cabinetGrip/v2/lease/commit）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomCabinetLeaseCommitClientResp {

    /**
     * 租约ID（随后调 lease/pay 时作为 leaseId）
     */
    private Long id;

    /**
     * 支付单ID
     */
    private Long payId;

}
