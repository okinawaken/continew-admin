package top.continew.admin.business.study.client.model.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 换座确认结果（/mp/study/change/seat/confirm）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomChangeSeatConfirmRespDTO {

    /**
     * 换座成功后的提示文案
     */
    private String studyTipText;

}
