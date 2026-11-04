package top.continew.admin.business.study.model.resp;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * StudyRoomCabinetVenueListResp 视图对象
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomCabinetVenueListVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 柜子列表
     */
    private List<StudyRoomCabinetVenueVO> list;
}
