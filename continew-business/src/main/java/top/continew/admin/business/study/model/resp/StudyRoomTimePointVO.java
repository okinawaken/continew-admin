package top.continew.admin.business.study.model.resp;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * StudyRoomTimePointRespDTO 视图对象
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomTimePointVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 小时
     */
    private Integer hour;

    /**
     * 分钟
     */
    private Integer minute;
}
