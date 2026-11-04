package top.continew.admin.business.study.model.resp;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * StudyRoomNotBuyPackageRespDTO 视图对象
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomNotBuyPackageVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 套餐名称
     */
    private String packageName;

    /**
     * 套餐类型
     */
    private Integer packageType;

    /**
     * 套餐简介
     */
    private String packageIntroDesc;
}
