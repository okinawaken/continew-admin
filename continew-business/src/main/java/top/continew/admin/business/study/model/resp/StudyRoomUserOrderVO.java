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

/**
 * StudyRoomUserOrderRespDTO 视图对象
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomUserOrderVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    private Long id;

    /**
     * 订单号
     */
    private String orderNumber;

    /**
     * 套餐名称
     */
    private String packageName;

    /**
     * 套餐类型
     */
    private Integer packageType;

    /**
     * 套餐总价（元）
     */
    private BigDecimal packagePriceTotal;

    /**
     * 应付金额（元）
     */
    private BigDecimal payPrice;

    /**
     * 实付金额（元）
     */
    private BigDecimal payRealMoney;

    /**
     * 优惠金额（元）
     */
    private BigDecimal discountPrice;

    /**
     * 支付状态
     */
    private Integer payState;

    /**
     * 支付状态名称
     */
    private String payStateStr;

    /**
     * 支付方式
     */
    private String payTypeStr;

    /**
     * 支付流水号
     */
    private String payNumber;

    /**
     * 下单时间
     */
    private String buyTimeStr;

    /**
     * 支付时间
     */
    private String payTimeStr;

    /**
     * 创建时间
     */
    private String createTimeStr;
}
