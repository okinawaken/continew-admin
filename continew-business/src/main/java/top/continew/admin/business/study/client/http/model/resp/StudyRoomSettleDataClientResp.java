package top.continew.admin.business.study.client.http.model.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 签退结算明细（settlement / userPackage/confirm 的 settleData）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomSettleDataClientResp {

    /**
     * 结算时长文案
     */
    private String settleTimeStr;

    /**
     * 学习时长描述
     */
    private String studyTimeDesc;

    /**
     * 学习分钟描述
     */
    private String studyMinuteDesc;

    /**
     * 已使用时长
     */
    private String usedTimeStr;

}
