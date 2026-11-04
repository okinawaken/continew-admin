package top.continew.admin.business.study.model.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 门店下拉列表（/mp/venue/select、/mp/reserve/venue/select、/mp/receipt/venue/select）
 *
 * @author bixiu
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomVenueSelectClientResp {

    /**
     * 门店列表
     */
    private List<StudyRoomVenueSimpleClientResp> venueList;
}
