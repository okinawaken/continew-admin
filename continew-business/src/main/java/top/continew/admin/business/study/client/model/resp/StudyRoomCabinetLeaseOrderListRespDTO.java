package top.continew.admin.business.study.client.model.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 储物柜租赁订单列表（/mp/cabinetGrip/v2/lease/order/list）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomCabinetLeaseOrderListRespDTO {

    /**
     * 订单列表
     */
    private List<StudyRoomCabinetLeaseOrderRespDTO> list;
}
