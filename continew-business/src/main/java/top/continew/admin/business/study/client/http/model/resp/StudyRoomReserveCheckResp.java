package top.continew.admin.business.study.client.http.model.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 可预约校验结果（/mp/reserve/check）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomReserveCheckResp {

    /**
     * 当前用户可用于预约的套餐列表
     */
    private List<StudyRoomUserPackageResp> canReservePackageList;

    /**
     * 预约状态：0 可预约 1 不可预约
     */
    private Integer reserveState;

    /**
     * 预约状态说明
     */
    private String reserveStateMsg;
}
