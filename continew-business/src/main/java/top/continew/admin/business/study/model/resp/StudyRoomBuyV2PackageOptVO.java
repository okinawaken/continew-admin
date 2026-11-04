package top.continew.admin.business.study.model.resp;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * StudyRoomBuyV2PackageOptRespDTO 视图对象
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomBuyV2PackageOptVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

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
    private List<StudyRoomIdNameVO> packageTypeList;

    /**
     * 套餐标签
     */
    private List<StudyRoomIdNameVO> packageLabelList;

    /**
     * 套餐标签（类型2）
     */
    private List<StudyRoomIdNameVO> packageLabelListOfType2;

    /**
     * 门店
     */
    private List<StudyRoomIdNameVO> venueList;
}
