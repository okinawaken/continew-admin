package top.continew.admin.business.study.model.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 进入请假页（/mp/user/userLeave/enterLeave）
 *
 * @author bixiu
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomUserLeaveEnterClientResp {

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
    private List<StudyRoomUserLeaveClientResp> leaveList;
}
