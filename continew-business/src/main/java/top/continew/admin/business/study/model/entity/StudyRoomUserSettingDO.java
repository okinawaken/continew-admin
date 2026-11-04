package top.continew.admin.business.study.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.continew.admin.business.study.model.bo.StudyRoomUserConfigBO;
import top.continew.admin.common.base.model.entity.TenantBaseDO;

import java.io.Serial;

/**
 * 自习室用户设置 study_room_user_setting
 * <p>
 * 一个系统用户一份配置，由唯一索引 {@code uk_user_setting_tenant_user_id} 保证。
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "study_room_user_setting")
public class StudyRoomUserSettingDO extends TenantBaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 系统用户 ID
     */
    private Long userId;

    /**
     * 配置 JSON，结构见 {@link StudyRoomUserConfigBO}
     */
    private String configJson;
}
