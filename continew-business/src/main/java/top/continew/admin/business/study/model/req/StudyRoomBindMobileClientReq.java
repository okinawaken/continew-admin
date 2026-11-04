package top.continew.admin.business.study.model.req;

import lombok.Data;

/**
 * 授权手机号登录（/mp/user/authorize/bindMobile）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomBindMobileClientReq {

    /**
     * wx.login 返回的 code（必填）
     */
    private String wechatCode;

    /**
     * getPhoneNumber / getUserProfile 回调中的 detail（含 encryptedData / iv / userInfo）
     */
    private StudyRoomWxDataClientReq wechatData;
}
