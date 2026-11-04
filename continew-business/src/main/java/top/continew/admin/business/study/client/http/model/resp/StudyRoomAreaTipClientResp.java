package top.continew.admin.business.study.client.http.model.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 区域提示
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomAreaTipClientResp {

    /**
     * 0 禁止 / 1 允许
     */
    private Integer allowType;

    /**
     * 提示内容
     */
    private String content;
}
