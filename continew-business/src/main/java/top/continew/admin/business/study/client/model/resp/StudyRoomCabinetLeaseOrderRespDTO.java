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

package top.continew.admin.business.study.client.model.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 储物柜租赁订单
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomCabinetLeaseOrderRespDTO {

    /**
     * 订单ID
     */
    private Long id;

    /**
     * 租赁ID
     */
    private Long leaseId;

    /**
     * 门店名称
     */
    private String venueName;

    /**
     * 柜号
     */
    private String cabinetNo;

    /**
     * 开始日期
     */
    private String startDateStr;

    /**
     * 结束日期
     */
    private String endDateStr;

    /**
     * 金额（元）
     */
    private BigDecimal priceYuan;

    /**
     * 支付状态
     */
    private Integer payState;

    /**
     * 支付状态名称
     */
    private String payStateStr;

    /**
     * 创建时间
     */
    private String createTimeStr;
}
