package top.continew.admin.business.study.model.resp;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * StudyRoomUserIndexResp 视图对象
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomUserIndexVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 头像
     */
    private String headImg;

    /**
     * 头像是否已授权：1 是 0 否
     */
    private Integer headImgAuth;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 微信昵称
     */
    private String nickName;

    /**
     * 累计签到次数
     */
    private Integer signTotalNum;

    /**
     * 是否开启学习排行：true/false（字符串）
     */
    private String studyRankOpen;

    /**
     * 累计学习时长，如 12小时30分钟
     */
    private String studyTotalTimeStr;

    /**
     * 真实姓名
     */
    private String userName;
}
