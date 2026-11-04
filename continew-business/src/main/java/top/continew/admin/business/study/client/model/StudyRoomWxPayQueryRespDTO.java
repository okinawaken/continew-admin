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

package top.continew.admin.business.study.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 微信支付状态查询（/mp/wx/payment/v2/wx/query/status/{payId}）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomWxPayQueryRespDTO {

    /**
     * 支付单ID
     */
    private Long id;

    /**
     * 支付状态（-10 表示查不到该单）
     */
    private Integer status;

    /**
     * 微信侧交易状态
     */
    private String tradeState;

    /**
     * 商户订单号
     */
    private String outTradeNo;

    /**
     * 微信支付交易号
     */
    private String transactionId;

    /**
     * 订单总金额（分）
     */
    private Integer totalFee;

    /**
     * 业务订单ID
     */
    private Long orderId;

    /**
     * 支付用户 openid
     */
    private String openid;

    /**
     * 商品描述
     */
    private String body;

    /**
     * 附加数据
     */
    private String attach;

}
