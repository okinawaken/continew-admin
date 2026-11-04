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

package top.continew.admin.business.study.model.req;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 确认下单 v1（/mp/buy/package/confirm）
 * <p>
 * 字段与小程序源码保持一致。
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomBuyPackageConfirmReqDTO {

    /**
     * 套餐ID（必填）
     */
    private Long packageId;

    /**
     * 购买数量（必填）
     */
    private Integer packageNum;

    /**
     * 支付金额（必填，前端按套餐单价 × 数量计算）
     */
    private BigDecimal packagePrice;

    /**
     * 当前经度
     */
    private Double currentLng;

    /**
     * 当前纬度
     */
    private Double currentLat;

}
