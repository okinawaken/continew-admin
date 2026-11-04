package top.continew.admin.business.study.client.http.model.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 抖音核销门店列表（/mp/douYin/venue/select）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomDouYinVenueSelectResp {

    /**
     * 门店列表
     */
    private List<StudyRoomDouYinVenueResp> venueList;
}
