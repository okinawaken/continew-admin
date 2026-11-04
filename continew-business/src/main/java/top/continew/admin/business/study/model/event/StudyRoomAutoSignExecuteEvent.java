package top.continew.admin.business.study.model.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 自动签到执行事件
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudyRoomAutoSignExecuteEvent implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 系统用户 ID
     */
    private Long userId;

    /**
     * 租户 ID（异步监听线程需要显式恢复租户上下文）
     */
    private Long tenantId;
}
