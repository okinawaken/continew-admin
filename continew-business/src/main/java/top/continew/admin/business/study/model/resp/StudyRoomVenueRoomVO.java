package top.continew.admin.business.study.model.resp;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * StudyRoomVenueRoomResp 视图对象
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomVenueRoomVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 区域名称
     */
    private String title;

    /**
     * 区域图片
     */
    private String imagePath;

    /**
     * 区域须知
     */
    private List<StudyRoomAreaTipVO> areaTipsList;
}
