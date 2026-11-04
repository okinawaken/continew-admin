package top.continew.admin.business.study.client.http.model.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 按区域分组的套餐（/mp/buy/package/list）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomVenueAreaPackageResp {

    /**
     * 区域名称
     */
    private String areaName;

    /**
     * 区域图片
     */
    private String areaImgUrl;

    /**
     * 套餐列表
     */
    private List<StudyRoomPackageItemResp> packageList;
}
