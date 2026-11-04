package top.continew.admin.business.study.client.http.model.req;

import lombok.Data;

/**
 * 小程序登录（/mp/user/login）
 * <p>
 * 小程序侧用 wx.login 拿到的 code 换取 openid 并返回 token； smart-admin 作为中转层时，一般直接使用用户绑定/粘贴的 token，此接口备用。
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomLoginReq {

    /**
     * wx.login 返回的 code（必填）
     */
    private String wechatCode;
}
