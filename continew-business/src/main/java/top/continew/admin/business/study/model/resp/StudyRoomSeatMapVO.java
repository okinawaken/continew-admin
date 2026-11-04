package top.continew.admin.business.study.model.resp;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * StudyRoomSeatMapResp 视图对象
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomSeatMapVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 预约后保留座位分钟数（字符串，如 30）
     */
    private String reserveHoldMinutes;

    /**
     * 预约可取消次数上限（字符串，如 100）
     */
    private String reserveCancelLimit;

    /**
     * 预约取消提前分钟数（字符串）
     */
    private String reserveCancelMinutes;

    /**
     * 门店信息
     */
    private StudyRoomVenueVO venue;

    /**
     * 区域及座位
     */
    private List<StudyRoomSeatAreaVO> venueAreaList;
}
