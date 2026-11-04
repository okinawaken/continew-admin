package top.continew.admin.business.study.client.model.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 我的订单（/mp/user/userOrder/list）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomUserOrderListRespDTO {

    /**
     * 订单列表
     */
    private List<StudyRoomUserOrderRespDTO> list;
}
