package top.continew.admin.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;
import top.continew.admin.common.base.model.entity.BaseDO;
import top.continew.admin.common.enums.DisEnableStatusEnum;
import top.continew.admin.system.enums.LogoutModeEnum;
import top.continew.admin.system.enums.ReplacedRangeEnum;
import top.continew.starter.auth.refresh.token.enums.RefreshTokenModeEnum;

import java.io.Serial;
import java.util.List;

/**
 * 客户端实体
 *
 * @author bixiu
 * @author bixiu
 * @since 3.5.0
 */
@Data
@TableName(value = "sys_client", autoResultMap = true)
public class ClientDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 客户端 ID
     */
    private String clientId;

    /**
     * 客户端类型
     */
    private String clientType;

    /**
     * 登录类型
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> authType;

    /**
     * Token 最低活跃频率（单位：秒，-1：不限制，永不冻结）
     */
    private Long activeTimeout;

    /**
     * Token 有效期（单位：秒，-1：永不过期）
     */
    private Long timeout;

    /**
     * Refresh Token 绝对有效期（单位：秒），轮换不会延长该期限
     */
    private Long refreshTokenTimeout;

    /**
     * Refresh Token 传输模式：浏览器使用 COOKIE，App / 小程序使用 BODY
     */
    private RefreshTokenModeEnum refreshTokenMode;

    /**
     * 是否允许同一账号多地同时登录（true：允许；false：新登录挤掉旧登录）
     */
    private Boolean isConcurrent;

    /**
     * 顶人下线的范围
     */
    private ReplacedRangeEnum replacedRange;

    /**
     * 同一账号最大登录数量（-1：不限制，只有在 isConcurrent=true，isShare=false 时才有效）
     */
    private Integer maxLoginCount;

    /**
     * 溢出人数的下线方式
     */
    private LogoutModeEnum overflowLogoutMode;

    /**
     * 状态
     */
    private DisEnableStatusEnum status;
}
