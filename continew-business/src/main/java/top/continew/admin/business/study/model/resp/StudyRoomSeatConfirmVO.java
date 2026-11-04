package top.continew.admin.business.study.model.resp;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * StudyRoomSeatConfirmResp 视图对象
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomSeatConfirmVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 签到成功后的提示文案（签到成功页直接展示）
     */
    private String studyTipText;
}
