package top.continew.admin.business.study.model.resp;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * StudyRoomCabinetGridRespDTO 视图对象
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomCabinetGridVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 格口ID
     */
    private Integer gridId;

    /**
     * 格口编号
     */
    private String gridCode;

    /**
     * 格口状态
     */
    private Integer gridStatus;
}
