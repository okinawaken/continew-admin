package top.continew.admin.business.study.model.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 储物柜（柜体）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomCabinetVenueClientResp {

    /**
     * 柜子ID（上游字段名为 cabinetId）
     */
    @JsonProperty(value = "cabinetId")
    private Integer cabinetId;

    /**
     * 柜子名称
     */
    private String cabinetName;

    /**
     * 门店ID
     */
    private Integer venueId;

    /**
     * 在售格口数
     */
    private Integer saleCount;

}
