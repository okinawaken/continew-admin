package top.continew.admin.business.study.model.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 门店营业时间点
 *
 * @author bixiu
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomTimePointClientResp {

    /**
     * 小时
     */
    private Integer hour;

    /**
     * 分钟
     */
    private Integer minute;
}
