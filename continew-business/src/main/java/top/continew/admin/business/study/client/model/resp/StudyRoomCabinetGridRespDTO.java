package top.continew.admin.business.study.client.model.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 柜体格口
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomCabinetGridRespDTO {

    /**
     * 格口ID
     */
    private Integer gridId;

    /**
     * 格口编号
     */
    private String gridCode;

    /**
     * 格口状态
     */
    private Integer gridStatus;

}
