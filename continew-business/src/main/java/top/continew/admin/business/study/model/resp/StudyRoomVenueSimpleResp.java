package top.continew.admin.business.study.model.resp;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * StudyRoomVenueSimpleResp 视图对象
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomVenueSimpleResp implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 门店ID
     */
    private Integer id;

    /**
     * 门店名称
     */
    private String venueName;

    /**
     * 美团门店编码
     */
    private String meituanShopCode;

    /**
     * 美团门店名称
     */
    private String meituanShopName;

    /**
     * 抖音门店 UUID
     */
    private String openShopUuid;
}
