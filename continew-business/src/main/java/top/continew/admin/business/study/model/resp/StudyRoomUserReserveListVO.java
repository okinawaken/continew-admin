package top.continew.admin.business.study.model.resp;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * StudyRoomUserReserveListRespDTO 视图对象
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomUserReserveListVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 预约记录
     */
    private List<StudyRoomUserReserveVO> list;
}
