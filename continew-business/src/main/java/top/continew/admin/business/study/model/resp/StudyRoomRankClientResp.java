package top.continew.admin.business.study.model.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 学习排行榜（/mp/study/rank/index）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomRankClientResp {

    /**
     * 排行列表
     */
    private List<StudyRoomRankUserClientResp> rankUserList;
}
