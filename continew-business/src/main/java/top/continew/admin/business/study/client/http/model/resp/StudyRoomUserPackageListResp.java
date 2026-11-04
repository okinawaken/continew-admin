package top.continew.admin.business.study.client.http.model.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 我的套餐（/mp/user/userPackage/list）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomUserPackageListResp {

    /**
     * 已购套餐
     */
    private List<StudyRoomUserPackageItemResp> list;

    /**
     * 未购套餐推荐
     */
    private List<StudyRoomNotBuyPackageResp> notBuyList;
}
