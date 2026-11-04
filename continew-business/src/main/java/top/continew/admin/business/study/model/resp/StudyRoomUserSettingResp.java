package top.continew.admin.business.study.model.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalTime;

/**
 * 用户设置
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomUserSettingResp {

    /**
     * 放号时刻 {@code HH:mm:ss}
     */
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime releaseTime;

    /**
     * 是否启用自动签到：0 关闭，1 开启
     */
    private Integer signInEnable;

    /**
     * 微信小程序 token，未绑定时为 null
     */
    private String token;
}
