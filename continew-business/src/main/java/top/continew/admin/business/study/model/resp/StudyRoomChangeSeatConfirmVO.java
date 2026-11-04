package top.continew.admin.business.study.model.resp;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * StudyRoomChangeSeatConfirmResp 视图对象
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomChangeSeatConfirmVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 换座成功后的提示文案
     */
    private String studyTipText;
}
