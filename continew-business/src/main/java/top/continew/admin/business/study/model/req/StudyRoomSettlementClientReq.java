package top.continew.admin.business.study.model.req;

import lombok.Data;

/**
 * 进入签退结算（/mp/study/settlement）
 *
 * @author bixiu
 * @since 4.2.0
 */
@Data
public class StudyRoomSettlementClientReq {

    /**
     * 当前签到ID（必填）
     */
    private Long userSignId;

}
