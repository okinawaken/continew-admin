package top.continew.admin.business.study.client.http.model.req;

import lombok.Data;

/**
 * 换座座位图（/mp/study/change/seat/list）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomChangeSeatClientReq {

    /**
     * 当前签到ID（必填）
     */
    private Long userSignId;
}
