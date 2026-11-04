package top.continew.admin.business.study.client.model.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 套餐列表（/mp/buy/v2/package/list）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomBuyV2PackageListRespDTO {

    /**
     * 套餐列表
     */
    private List<StudyRoomPackageItemRespDTO> packageList;
}
