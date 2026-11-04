package top.continew.admin.business.study.model.resp;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * StudyRoomDouYinVenueResp 视图对象
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomDouYinVenueVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 门店ID
     */
    private Long id;

    /**
     * 抖音 POI ID
     */
    private String poiId;

    /**
     * 抖音 POI 名称
     */
    private String poiName;

    /**
     * 门店名称
     */
    private String venueName;
}
