package top.continew.admin.business.study.model.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 抖音核销门店列表（/mp/douYin/venue/select）
 *
 * @author bixiu
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomDouYinVenueSelectClientResp {

    /**
     * 门店列表
     */
    private List<StudyRoomDouYinVenueClientResp> venueList;
}
