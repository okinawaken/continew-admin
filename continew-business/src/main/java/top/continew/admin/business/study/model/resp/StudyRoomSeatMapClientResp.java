package top.continew.admin.business.study.model.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 座位图（/mp/reserve/v2/seat/list、/mp/reserve/seat/list）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomSeatMapClientResp {

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
    private StudyRoomVenueClientResp venue;

    /**
     * 区域及座位
     */
    private List<StudyRoomSeatAreaClientResp> venueAreaList;
}
