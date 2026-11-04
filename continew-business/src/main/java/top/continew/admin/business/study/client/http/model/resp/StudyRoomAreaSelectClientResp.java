package top.continew.admin.business.study.client.http.model.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 区域筛选列表（/mp/reserve/area/select）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomAreaSelectClientResp {

    /**
     * 区域列表
     */
    private List<StudyRoomAreaClientResp> areaList;
}
