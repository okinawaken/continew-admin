package top.continew.admin.business.study.model.resp;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * StudyRoomBuyV2PackageLabelResp 视图对象
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomBuyV2PackageLabelResp implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 套餐标签
     */
    private List<StudyRoomIdNameResp> packageLabelList;

    /**
     * 座位区域
     */
    private List<StudyRoomIdNameResp> seatAreaList;
}
