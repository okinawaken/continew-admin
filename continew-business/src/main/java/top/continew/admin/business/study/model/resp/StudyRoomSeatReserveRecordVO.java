package top.continew.admin.business.study.model.resp;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * StudyRoomSeatReserveRecordRespDTO 视图对象
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomSeatReserveRecordVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 预约记录ID
     */
    private Long id;

    /**
     * 预约日期 yyyy-MM-dd
     */
    private String reserveDateStr;

    /**
     * 占用时长，如 13小时
     */
    private String reserveMinuteStr;

    /**
     * 占用时段，如 10:00-23:00
     */
    private String reserveTimeStr;
}
