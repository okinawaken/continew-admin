package top.continew.admin.business.study.model.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 请假记录
 *
 * @author bixiu
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomUserLeaveClientResp {

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
