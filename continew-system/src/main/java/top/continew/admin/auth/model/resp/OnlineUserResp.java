package top.continew.admin.auth.model.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 在线用户响应参数
 *
 * @author Charles7c
 * @since 1.0.0
 */
@Data
@Schema(description = "在线用户响应参数")
public class OnlineUserResp implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Schema(description = "ID", example = "1")
    private Long id;

    /**
     * 登录会话 ID
     */
    @Schema(description = "登录会话 ID", example = "Zm9vYmFyYmF6cXV4MTIzNA")
    private String sessionId;

    /**
     * 用户名
     */
    @Schema(description = "用户名", example = "zhangsan")
    private String username;

    /**
     * 昵称
     */
    @Schema(description = "昵称", example = "张三")
    private String nickname;

    /**
     * 客户端类型
     */
    @Schema(description = "客户端类型", example = "PC")
    private String clientType;

    /**
     * 客户端 ID
     */
    @Schema(description = "客户端 ID", example = "ef51c9a3e9046c4f2ea45142c8a8344a")
    private String clientId;

    /**
     * 登录 IP
     */
    @Schema(description = "登录 IP", example = "")
    private String ip;

    /**
     * 登录地点
     */
    @Schema(description = "登录地点", example = "中国北京北京市")
    private String address;

    /**
     * 浏览器
     */
    @Schema(description = "浏览器", example = "Chrome 115.0.0.0")
    private String browser;

    /**
     * 操作系统
     */
    @Schema(description = "操作系统", example = "Windows 10")
    private String os;

    /**
     * 登录时间
     */
    @Schema(description = "登录时间", example = "2023-08-08 08:08:08", type = "string")
    private LocalDateTime loginTime;

    /**
     * 最后刷新时间
     */
    @Schema(description = "最后刷新时间", example = "2023-08-08 08:08:08", type = "string")
    private LocalDateTime lastRefreshTime;
}
