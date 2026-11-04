package top.continew.admin.business.study.model.resp;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * StudyRoomReserveIndexResp 视图对象
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomReserveIndexResp implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

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
