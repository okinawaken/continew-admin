package top.continew.admin.business.study.client.http.model.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 门店储物柜列表（/mp/cabinetGrip/v2/venue/cabinet/list）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomCabinetVenueListResp {

    /**
     * 柜子列表
     */
    private List<StudyRoomCabinetVenueResp> list;

}
