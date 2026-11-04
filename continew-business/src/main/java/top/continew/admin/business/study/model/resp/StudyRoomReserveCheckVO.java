package top.continew.admin.business.study.model.resp;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * StudyRoomReserveCheckRespDTO 视图对象
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomReserveCheckVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 当前用户可用于预约的套餐列表
     */
    private List<StudyRoomUserPackageVO> canReservePackageList;

    /**
     * 预约状态：0 可预约 1 不可预约
     */
    private Integer reserveState;

    /**
     * 预约状态说明
     */
    private String reserveStateMsg;
}
