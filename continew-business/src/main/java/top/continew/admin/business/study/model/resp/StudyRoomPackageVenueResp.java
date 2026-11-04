package top.continew.admin.business.study.model.resp;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * StudyRoomPackageVenueResp 视图对象
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomPackageVenueResp implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 门店ID
     */
    private Long id;

    /**
     * 门店名称
     */
    private String venueName;

    /**
     * 门店地址
     */
    private String venueAddress;

    /**
     * 距离（km）
     */
    private Double distance;

    /**
     * 可用区域
     */
    private List<StudyRoomPackageAreaResp> venueAreaList;
}
