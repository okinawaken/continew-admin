package top.continew.admin.business.study.client.model.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 签到记录列表（/mp/user/userSign/list、/list/more）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomUserSignListRespDTO {

    /**
     * 签到记录
     */
    private List<StudyRoomUserSignRespDTO> list;

}
