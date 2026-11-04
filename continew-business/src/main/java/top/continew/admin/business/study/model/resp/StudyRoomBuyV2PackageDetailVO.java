/*
 * Copyright (c) 2022-present Charles7c Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package top.continew.admin.business.study.model.resp;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * StudyRoomBuyV2PackageDetailRespDTO 视图对象
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomBuyV2PackageDetailVO implements Serializable {

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
     * 套餐计费说明
     */
    private String packageTypeDesc;

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
     * 可用时长说明
     */
    private String consumeUseNumDesc;

    /**
     * 有效期说明
     */
    private String validTimeDesc;

    /**
     * 可用时段
     */
    private String settleHourDesc;

    /**
     * 可用门店/区域说明
     */
    private String useAreasDesc;

    /**
     * 限购说明
     */
    private String buyPerNumDesc;

    /**
     * 最多购买份数
     */
    private Integer maxPerBuyNum;

    /**
     * 详情页内容
     */
    private String descPageContent;

    /**
     * 详情页链接
     */
    private String descPageUrl;

    /**
     * 不可用日期说明
     */
    private String failureDateDesc;

    /**
     * 不可用星期说明
     */
    private String failureWeekdayDesc;

    /**
     * 不可用节假日说明
     */
    private String failureHolidayDesc;

    /**
     * 可用门店
     */
    private List<StudyRoomPackageVenueVO> venueList;
}
