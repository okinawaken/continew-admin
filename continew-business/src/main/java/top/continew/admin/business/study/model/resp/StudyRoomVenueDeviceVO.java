package top.continew.admin.business.study.model.resp;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * StudyRoomVenueDeviceRespDTO 视图对象
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomVenueDeviceVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 图标地址
     */
    private String icon;

    /**
     * 设施名称
     */
    private String title;
}
