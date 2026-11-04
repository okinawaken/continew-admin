package top.continew.admin.business.study.client.model.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 门店区域介绍
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomVenueRoomRespDTO {

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
    private List<StudyRoomAreaTipRespDTO> areaTipsList;
}
