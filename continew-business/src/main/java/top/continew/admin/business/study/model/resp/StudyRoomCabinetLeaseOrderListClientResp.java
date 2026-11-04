package top.continew.admin.business.study.model.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 储物柜租赁订单列表（/mp/cabinetGrip/v2/lease/order/list）
 *
 * @author bixiu
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomCabinetLeaseOrderListClientResp {

    /**
     * 订单列表
     */
    private List<StudyRoomCabinetLeaseOrderClientResp> list;
}
