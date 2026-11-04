package top.continew.admin.business.study.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.continew.admin.business.study.model.bo.StudyRoomAutoReserveTaskRecordDetailBO;
import top.continew.admin.common.base.model.entity.TenantBaseDO;

import java.io.Serial;
import java.time.LocalDate;

/**
 * 自动预约执行记录 study_room_auto_reserve_task_record
 * <p>
 * 每轮抢座一条：仅在终态落库，跳过 / 成功 / 失败三种状态。
 *
 * @author bixiu
 * @since 4.2.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "study_room_auto_reserve_task_record")
public class StudyRoomAutoReserveTaskRecordDO extends TenantBaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 任务 ID
     */
    private Long taskId;

    /**
     * 系统用户 ID
     */
    private Long userId;

    /**
     * 触发方式：0 定时，1 手动
     */
    private Integer triggerType;

    /**
     * 目标预约日期
     */
    private LocalDate targetDate;

    /**
     * 执行状态：0 跳过，1 成功，2 失败
     */
    private Integer status;

    /**
     * 尝试明细 JSON，结构见 {@link StudyRoomAutoReserveTaskRecordDetailBO}
     */
    private String detailJson;
}
