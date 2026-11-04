package top.continew.admin.business.study.model.resp;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * StudyRoomVenueSelectRespDTO 视图对象
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomVenueSelectVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 门店列表
     */
    private List<StudyRoomVenueSimpleVO> venueList;
}
