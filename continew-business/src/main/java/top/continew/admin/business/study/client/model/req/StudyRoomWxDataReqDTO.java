package top.continew.admin.business.study.client.model.req;

import lombok.Data;

/**
 * 微信授权数据（bindMobile 的 wechatData）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomWxDataReqDTO {

    /**
     * 用户信息
     */
    private StudyRoomWxUserInfoReqDTO userInfo;

    /**
     * 原始数据
     */
    private String rawData;

    /**
     * 签名
     */
    private String signature;

    /**
     * 加密数据
     */
    private String encryptedData;

    /**
     * 加密向量
     */
    private String iv;
}
