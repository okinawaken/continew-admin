package top.continew.admin.business.study.model.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import java.time.LocalTime;

/**
 * 用户设置写请求
 * <p>
 * <b>整对象覆盖</b>：未传（null）的配置项不会保留原值。
 *
 * @author bixiu
 * @since 4.2.0
 */
@Data
public class StudyRoomUserSettingReq {

    /**
     * 放号时刻 {@code HH:mm[:ss]}
     */
    @JsonFormat(pattern = "HH:mm[:ss]")
    private LocalTime releaseTime;

    /**
     * 是否启用自动签到：0 关闭，1 开启
     */
    @Min(value = 0, message = "自动签到开关取值不合法")
    @Max(value = 1, message = "自动签到开关取值不合法")
    private Integer signInEnable;

    /**
     * 微信小程序 token（JWT）
     */
    @Size(max = 1024, message = "小程序 token 长度不能超过{max}个字符")
    private String token;
}
