package top.continew.admin.business.study.model.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 柜体格口列表（/mp/cabinetGrip/v2/cabinet/grid/list）
 *
 * @author bixiu
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomCabinetGridListClientResp {

    /**
     * 格口列表
     */
    private List<StudyRoomCabinetGridClientResp> list;

}
