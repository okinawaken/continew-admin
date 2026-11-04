package top.continew.admin.business.study.model.resp;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * StudyRoomUserLeaveEnterRespDTO 视图对象
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomUserLeaveEnterVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户套餐ID
     */
    private Long userPackageId;

    /**
     * 是否可请假：0 不可
     */
    private Integer canLeaveState;

    /**
     * 不可请假原因
     */
    private String canNotLeaveMsg;

    /**
     * 剩余可请假天数
     */
    private Integer leftLeaveDays;

    /**
     * 今日使用状态
     */
    private Integer todayStudyState;

    /**
     * 请假记录
     */
    private List<StudyRoomUserLeaveVO> leaveList;
}
