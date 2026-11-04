package top.continew.admin.business.study.model.resp;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * StudyRoomBuyPackageListResp 视图对象
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomBuyPackageListResp implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 按区域分组的套餐
     */
    private List<StudyRoomVenueAreaPackageResp> venueAreaList;
}
