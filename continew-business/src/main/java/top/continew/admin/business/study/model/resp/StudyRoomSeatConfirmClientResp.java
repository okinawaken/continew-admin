package top.continew.admin.business.study.model.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 签到选座确认结果（/mp/study/seat/confirm）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomSeatConfirmClientResp {

    /**
     * 签到成功后的提示文案（签到成功页直接展示）
     */
    private String studyTipText;

}
