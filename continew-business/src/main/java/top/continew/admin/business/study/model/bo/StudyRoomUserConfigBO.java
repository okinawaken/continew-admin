package top.continew.admin.business.study.model.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;
import top.continew.admin.business.study.enums.StudyRoomAutoSignInEnableEnum;

import java.time.LocalTime;

/**
 * 用户级配置，对应 {@code study_room_user_setting.config_json}
 * <p>
 * 微信小程序 token 也保存在这里，与放号时刻、自动签到开关共用同一条用户配置记录。
 *
 * @author bixiu
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomUserConfigBO {

    /**
     * 放号时刻，默认 20:00:00
     * <p>
     * 字段类型必须与 getter/setter 一致：若声明为 {@code String} 却提供返回 {@code LocalTime} 的 getter，
     * Jackson 会以数组形式（{@code [20,0]}）序列化、再按 {@code String} 反序列化失败，导致配置写成后读不回来。
     */
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime releaseTime;

    /**
     * 是否启用自动签到，见 {@link StudyRoomAutoSignInEnableEnum}
     * <p>
     * 默认关闭，避免老用户被动开启。
     */
    private Integer signInEnable;

    /**
     * 微信小程序 token（JWT），属于凭据，禁止输出到日志
     */
    @ToString.Exclude
    private String token;
}
