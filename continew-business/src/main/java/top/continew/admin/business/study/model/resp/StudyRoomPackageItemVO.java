package top.continew.admin.business.study.model.resp;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * StudyRoomPackageItemRespDTO 视图对象
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomPackageItemVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 套餐ID
     */
    private Long id;

    /**
     * 套餐名称
     */
    private String packageName;

    /**
     * 套餐类型
     */
    private Integer packageType;

    /**
     * 套餐类型名称
     */
    private String packageTypeStr;

    /**
     * 套餐说明
     */
    private String packageDesc;

    /**
     * 套餐图片
     */
    private String packageImgPath;

    /**
     * 原价（元）
     */
    private BigDecimal originalPriceYuan;

    /**
     * 实际售价（元）
     */
    private BigDecimal packageAccountPriceYuan;

    /**
     * 角标文案
     */
    private String specialTag;
}
