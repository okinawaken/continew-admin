package top.continew.admin.business.study.client.http.model.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 套餐筛选项（/mp/buy/v2/package/optList）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomBuyV2PackageOptResp {

    /**
     * 购买页说明 HTML
     */
    private String packageBuyHtml;

    /**
     * 购买页图片
     */
    private String packageBuyImg;

    /**
     * 支付按钮文案
     */
    private String packagePayBtn;

    /**
     * 套餐类型
     */
    private List<StudyRoomIdNameResp> packageTypeList;

    /**
     * 套餐标签
     */
    private List<StudyRoomIdNameResp> packageLabelList;

    /**
     * 套餐标签（类型2）
     */
    private List<StudyRoomIdNameResp> packageLabelListOfType2;

    /**
     * 门店
     */
    private List<StudyRoomIdNameResp> venueList;
}
