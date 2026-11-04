package top.continew.admin.business.study.client.http.model.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 预约首页（/mp/reserve/index）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomReserveIndexResp {

    /**
     * 区域筛选按钮是否展示（字符串 true/false）
     */
    private String areaIdBtn;

    /**
     * 渠道比例开关（字符串 true/false）
     */
    private String channelBiliBtn;

    /**
     * banner 图
     */
    private List<StudyRoomFileResp> bannerImgList;

    /**
     * 背景图
     */
    private List<StudyRoomFileResp> backImgList;

    /**
     * 门店搜索按钮是否展示（字符串 true/false）
     */
    private String venueNameBtn;

    /**
     * 门店列表
     */
    private List<StudyRoomVenueResp> venueList;
}
