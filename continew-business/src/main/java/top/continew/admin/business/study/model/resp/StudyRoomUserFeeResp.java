package top.continew.admin.business.study.model.resp;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * StudyRoomUserFeeResp 视图对象
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomUserFeeResp implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 是否有欠费：1 有
     */
    private Integer hasOwnFee;

    /**
     * 当前签到ID，0 表示未签到
     */
    private Integer userSignId;
}
