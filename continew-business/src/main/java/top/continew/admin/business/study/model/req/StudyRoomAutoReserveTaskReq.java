package top.continew.admin.business.study.model.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.List;

/**
 * 自动预约任务创建 / 修改请求
 *
 * @author bixiu
 * @since 4.2.0
 */
@Data
@Schema(description = "自动预约任务创建或修改请求参数")
public class StudyRoomAutoReserveTaskReq {

    /**
     * 门店 ID
     */
    @Schema(description = "门店 ID", example = "1")
    @NotNull(message = "门店不能为空")
    private Integer venueId;

    /**
     * 指定座位ID，不指定传 0 或不传
     */
    @Schema(description = "指定座位 ID，不指定传 0 或不传", example = "1")
    private Long seatId;

    /**
     * 指定座位编号（展示用）
     */
    @Schema(description = "指定座位编号", example = "A01")
    private String seatNumber;

    /**
     * 偏好区域ID，不指定传 0 或不传
     */
    @Schema(description = "偏好区域 ID，不指定传 0 或不传", example = "1")
    private Long areaId;

    /**
     * 偏好区域名称（展示用）
     */
    @Schema(description = "偏好区域名称", example = "安静区")
    private String areaName;

    /**
     * 期望开始时间 {@code HH:mm}
     */
    @Schema(description = "期望开始时间", example = "08:00", type = "string")
    @NotNull(message = "开始时间不能为空")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime reserveStartTime;

    /**
     * 期望结束时间 {@code HH:mm}
     */
    @Schema(description = "期望结束时间", example = "22:00", type = "string")
    @NotNull(message = "结束时间不能为空")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime reserveEndTime;

    /**
     * 执行星期，1 周一 … 7 周日
     */
    @Schema(description = "执行星期，1 周一 … 7 周日", example = "[1,2,3,4,5]")
    @NotEmpty(message = "执行星期不能为空")
    private List<Integer> weekDays;
}
