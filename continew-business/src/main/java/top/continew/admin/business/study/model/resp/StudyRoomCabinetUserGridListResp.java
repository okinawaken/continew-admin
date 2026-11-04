package top.continew.admin.business.study.model.resp;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * StudyRoomCabinetUserGridListResp 视图对象
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomCabinetUserGridListResp implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 我的柜格
     */
    private List<StudyRoomCabinetUserGridResp> list;
}
