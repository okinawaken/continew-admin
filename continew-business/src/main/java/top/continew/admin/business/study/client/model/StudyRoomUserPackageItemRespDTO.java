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
 * 我的套餐项（/mp/user/userPackage/list）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomUserPackageItemRespDTO {

    /**
     * 用户套餐ID（即 userPackageId）
     */
    private Long id;

    /**
     * 套餐ID
     */
    private Long packageId;

    /**
     * 套餐名称
     */
    private String packageName;

    /**
     * 套餐类型
     */
    private Integer packageType;

    /**
     * 剩余次数
     */
    private Integer restNum;

    /**
     * 剩余时长
     */
    private String restTimeStr;

    /**
     * 结算时长
     */
    private String settleHourStr;

    /**
     * 已用时长
     */
    private String usedTimeStr;

    /**
     * 状态说明
     */
    private String showStatusDesc;

    /**
     * 有效期至
     */
    private String useExpiryDate;

    /**
     * 订单号
     */
    private String orderNumber;

    /**
     * 是否可用于扣费：1 可以
     */
    private Integer canUsePay;

    /**
     * 不可扣费说明
     */
    private String canNotUsePayDesc;

    /**
     * 是否可请假：1 可以
     */
    private Integer canLeaveBtn;

    /**
     * 请假撤销说明
     */
    private String leaveCancelDesc;

    /**
     * 已请假日期
     */
    private String leaveDateDesc;

    /**
     * 请假类型
     */
    private Integer leaveType;

    /**
     * 请假记录ID
     */
    private Long userLeaveId;

    /**
     * 购买时间
     */
    private String buyTimeStr;

    /**
     * 使用方式
     */
    private Integer usedType;
}
