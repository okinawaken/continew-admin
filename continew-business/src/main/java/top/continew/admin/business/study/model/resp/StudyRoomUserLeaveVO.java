package top.continew.admin.business.study.model.resp;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * StudyRoomUserLeaveRespDTO 视图对象
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomUserLeaveVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 请假记录ID
     */
    private Long id;

    /**
     * 开始日期
     */
    private String startDate;

    /**
     * 结束日期
     */
    private String endDate;

    /**
     * 请假状态
     */
    private Integer leaveState;

    /**
     * 请假状态说明
     */
    private String leaveStateDesc;

    /**
     * 请假类型
     */
    private Integer leaveType;
}
