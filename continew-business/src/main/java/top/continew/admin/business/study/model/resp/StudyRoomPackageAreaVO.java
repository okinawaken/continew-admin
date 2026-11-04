package top.continew.admin.business.study.model.resp;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * StudyRoomPackageAreaRespDTO 视图对象
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomPackageAreaVO implements Serializable {

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
     * 区域说明
     */
    private String areaDesc;

    /**
     * 小时单价（元）
     */
    private BigDecimal priceHourYuan;

    /**
     * 日单价（元）
     */
    private BigDecimal priceDayYuan;

    /**
     * 月单价（元）
     */
    private BigDecimal priceMonthYuan;
}
