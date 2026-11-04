package top.continew.admin.business.study.model.bo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 单轮执行明细，对应 {@code study_room_auto_reserve_task_record.detail_json}
 *
 * @author bixiu
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomAutoReserveTaskRecordDetailBO {

    /**
     * 实际预约座位ID
     */
    private Long seatId;

    /**
     * 实际预约座位编号
     */
    private String seatNumber;

    /**
     * 实际预约时段 {@code HH:mm-HH:mm}
     */
    private String timeRange;

    /**
     * 上游预约单ID，取消预约时使用
     */
    private Long userReserveId;

    /**
     * 失败原因描述
     */
    private String failMsg;
}
