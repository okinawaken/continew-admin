package top.continew.admin.business.study.model.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

/**
 * 自动预约任务规则详情，对应 {@code study_room_auto_reserve_task.task_json}
 * <p>
 * 座位与区域至少配置一个：{@code seatId = 0} 表示未指定座位，{@code areaId = 0} 表示未指定区域。
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomAutoReserveTaskConfigBO {

    /**
     * 指定座位ID
     */
    private Long seatId;

    /**
     * 指定座位编号（展示用）
     */
    private String seatNumber;

    /**
     * 偏好区域ID
     */
    private Long areaId;

    /**
     * 偏好区域名称（展示用）
     */
    private String areaName;

    /**
     * 期望开始时间 {@code HH:mm}
     */
    @JsonFormat(pattern = "HH:mm")
    private LocalTime reserveStartTime;

    /**
     * 期望结束时间 {@code HH:mm}
     */
    @JsonFormat(pattern = "HH:mm")
    private LocalTime reserveEndTime;

    /**
     * 执行星期，1 周一 … 7 周日
     */
    private List<Integer> weekDays;
}
