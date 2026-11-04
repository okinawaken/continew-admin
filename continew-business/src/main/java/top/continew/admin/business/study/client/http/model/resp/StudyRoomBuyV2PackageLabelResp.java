package top.continew.admin.business.study.client.http.model.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 套餐标签 / 座位区域筛选项（/mp/buy/v2/package/label/selectList）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomBuyV2PackageLabelResp {

    /**
     * 套餐标签
     */
    private List<StudyRoomIdNameResp> packageLabelList;

    /**
     * 座位区域
     */
    private List<StudyRoomIdNameResp> seatAreaList;
}
