package top.continew.admin.business.study.model.req;

import lombok.Data;

/**
 * 微信用户信息（bindMobile 的 wechatData.userInfo）
 *
 * @author bixiu
 * @since 4.2.0
 */
@Data
public class StudyRoomWxUserInfoClientReq {

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像地址
     */
    private String avatarUrl;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 城市
     */
    private String city;

    /**
     * 省份
     */
    private String province;

    /**
     * 国家
     */
    private String country;

    /**
     * 语言
     */
    private String language;
}
