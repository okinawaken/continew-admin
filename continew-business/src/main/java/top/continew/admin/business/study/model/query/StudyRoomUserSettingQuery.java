package top.continew.admin.business.study.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 自习室用户设置查询条件
 * <p>
 * 用户设置按用户 ID 直接读取，暂无过滤条件，保留类型以满足 CRUD 通用接口的查询参数约束。
 *
 * @author bixiu
 * @since 4.2.0
 */
@Data
@Schema(description = "自习室用户设置查询条件")
public class StudyRoomUserSettingQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
}
