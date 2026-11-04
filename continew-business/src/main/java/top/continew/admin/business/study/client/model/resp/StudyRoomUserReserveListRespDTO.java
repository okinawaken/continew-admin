package top.continew.admin.business.study.client.model.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 我的预约列表（/mp/user/userReserve/list）
 * <p>
 * 注意：该接口不支持分页参数，一次返回全部预约记录。
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomUserReserveListRespDTO {

    /**
     * 预约记录
     */
    private List<StudyRoomUserReserveRespDTO> list;
}
