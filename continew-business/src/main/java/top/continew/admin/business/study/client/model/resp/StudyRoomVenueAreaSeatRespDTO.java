package top.continew.admin.business.study.client.model.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 区域与座位号
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomVenueAreaSeatRespDTO {

    /**
     * 区域名称
     */
    private String areaName;

    /**
     * 座位号
     */
    private String seatNumber;

}
