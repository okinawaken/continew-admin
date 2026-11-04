package top.continew.admin.business.study.model.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 套餐列表（/mp/buy/package/list，旧版）
 *
 * @author bixiu
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomBuyPackageListClientResp {

    /**
     * 按区域分组的套餐
     */
    private List<StudyRoomVenueAreaPackageClientResp> venueAreaList;
}
