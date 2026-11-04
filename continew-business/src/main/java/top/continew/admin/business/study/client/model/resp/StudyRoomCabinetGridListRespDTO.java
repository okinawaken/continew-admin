package top.continew.admin.business.study.client.model.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 柜体格口列表（/mp/cabinetGrip/v2/cabinet/grid/list）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomCabinetGridListRespDTO {

    /**
     * 格口列表
     */
    private List<StudyRoomCabinetGridRespDTO> list;

}
