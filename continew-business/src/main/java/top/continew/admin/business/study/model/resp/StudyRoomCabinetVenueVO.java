package top.continew.admin.business.study.model.resp;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * StudyRoomCabinetVenueRespDTO 视图对象
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomCabinetVenueVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 柜子ID
     */
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
