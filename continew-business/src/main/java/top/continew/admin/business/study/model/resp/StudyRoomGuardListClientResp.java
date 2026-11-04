package top.continew.admin.business.study.model.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 门店门禁区域列表（/mp/guard/select）
 *
 * @author bixiu
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomGuardListClientResp {

    /**
     * 门禁区域列表
     */
    private List<StudyRoomGuardClientResp> guardList;

}
