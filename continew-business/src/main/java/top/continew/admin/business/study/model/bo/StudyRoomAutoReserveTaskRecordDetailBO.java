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
     * 座位ID（成功时为实际预约座位，跳过时可能只是选中的座位）
     */
    private Long seatId;

    /**
     * 座位编号（成功时为实际预约座位，跳过时可能只是选中的座位）
     */
    private String seatNumber;

    /**
     * 任务期望预约时段 {@code HH:mm-HH:mm}
     */
    private String timeRange;

    /**
     * 上游预约单ID，取消预约时使用
     */
    private Long userReserveId;

    /**
     * 跳过或失败原因描述
     */
    private String failMsg;
}
