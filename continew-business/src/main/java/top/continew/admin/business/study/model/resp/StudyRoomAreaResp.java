package top.continew.admin.business.study.model.resp;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * StudyRoomAreaResp 视图对象
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomAreaResp implements Serializable {

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
}
