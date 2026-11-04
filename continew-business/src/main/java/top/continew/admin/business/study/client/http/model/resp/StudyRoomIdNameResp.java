package top.continew.admin.business.study.client.http.model.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 通用「ID + 名称」选项
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomIdNameResp {

    /**
     * ID
     */
    private Long id;

    /**
     * 名称
     */
    private String name;
}
