package top.continew.admin.business.study.model.resp;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * StudyRoomUserReserveCancelTipRespDTO 视图对象
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomUserReserveCancelTipVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 确认弹窗文案
     */
    private String tipDesc;

    /**
     * 预约记录ID
     */
    private Long userReserveId;
}
