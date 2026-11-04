package top.continew.admin.business.study.model.req;

import lombok.Data;

/**
 * 刷新 sessionKey（/mp/user/refresh/sessionKey）
 *
 * @author bixiu
 * @since 4.2.0
 */
@Data
public class StudyRoomRefreshSessionClientReq {

    /**
     * wx.login 返回的 code（必填）
     */
    private String wechatCode;
}
