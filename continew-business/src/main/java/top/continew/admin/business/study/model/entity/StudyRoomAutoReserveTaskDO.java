package top.continew.admin.business.study.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.continew.admin.business.study.enums.StudyRoomAutoReserveTaskEnableStatusEnum;
import top.continew.admin.business.study.model.bo.StudyRoomAutoReserveTaskConfigBO;
import top.continew.admin.common.base.model.entity.TenantBaseDO;

import java.io.Serial;
import java.time.LocalDate;

/**
 * 自动预约任务 study_room_auto_reserve_task
 * <p>
 * 规则详情（座位、区域、时段、星期）存 {@code task_json}；查询与索引相关字段独立成列。
 *
 * @author bixiu
 * @since 4.2.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "study_room_auto_reserve_task")
public class StudyRoomAutoReserveTaskDO extends TenantBaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 系统用户 ID
     */
    private Long userId;

    /**
     * 门店 ID
     */
    private Integer venueId;

    /**
     * 门店名称（冗余，避免列表页反复调上游）
     */
    private String venueName;

    /**
     * 任务详情 JSON，结构见 {@link StudyRoomAutoReserveTaskConfigBO}
     */
    private String taskJson;

    /**
     * 启用状态，见 {@link StudyRoomAutoReserveTaskEnableStatusEnum}
     */
    private Integer enableStatus;

    /**
     * 暂停原因
     */
    private String pauseReason;

    /**
     * 放号基线日期
     */
    private LocalDate lastMaxReserveDate;
}
