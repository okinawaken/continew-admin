package top.continew.admin.business.study.model.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 门店简要信息（/mp/reserve/venue/select、/mp/venue/select、签到记录里的 venue）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomVenueSimpleClientResp {

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
