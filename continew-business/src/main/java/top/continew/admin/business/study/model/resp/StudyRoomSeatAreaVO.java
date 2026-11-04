package top.continew.admin.business.study.model.resp;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * StudyRoomSeatAreaResp 视图对象
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomSeatAreaVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 区域ID
     */
    private Long id;

    /**
     * 区域名称
     */
    private String areaName;

    /**
     * 该区域是否绑定套餐：1 是 0 否
     */
    private Integer hasAreaPackage;

    /**
     * 区域座位列表
     */
    private List<StudyRoomSeatVO> venueAreaSeatList;
}
