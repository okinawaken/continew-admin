package top.continew.admin.business.study.model.resp;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * StudyRoomUserPackageListRespDTO 视图对象
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomUserPackageListVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 已购套餐
     */
    private List<StudyRoomUserPackageItemVO> list;

    /**
     * 未购套餐推荐
     */
    private List<StudyRoomNotBuyPackageVO> notBuyList;
}
