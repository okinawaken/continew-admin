package top.continew.admin.business.study.model.resp;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * StudyRoomUserSignListResp 视图对象
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomUserSignListResp implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 签到记录
     */
    private List<StudyRoomUserSignResp> list;
}
