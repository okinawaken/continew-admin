package top.continew.admin.business.study.model.req;

import lombok.Data;

/**
 * 更新授权头像（/mp/user/authorize/headImg）
 *
 * @author bixiu
 * @since 4.2.0
 */
@Data
public class StudyRoomAuthorizeHeadImgClientReq {

    /**
     * 微信昵称（必填）
     */
    private String nickName;

    /**
     * 微信头像地址（必填）
     */
    private String headImg;
}
