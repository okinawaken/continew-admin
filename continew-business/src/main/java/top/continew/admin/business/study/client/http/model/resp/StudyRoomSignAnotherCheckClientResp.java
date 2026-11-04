package top.continew.admin.business.study.client.http.model.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 是否可再签一座（/mp/study/signAnother/check）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomSignAnotherCheckClientResp {

    /**
     * 是否可再签：1 可以
     */
    private Integer canSignAnother;

    /**
     * 是否有可用的加签套餐：1 有
     */
    private Integer hasSignAnotherPackage;

    /**
     * 不可再签原因
     */
    private String descNoSignAnother;

    /**
     * 可选结算套餐
     */
    private List<StudyRoomSettlePackageClientResp> settlePackageList;
}
