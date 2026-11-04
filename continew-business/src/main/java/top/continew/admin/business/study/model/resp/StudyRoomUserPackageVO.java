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

/**
 * StudyRoomUserPackageRespDTO 视图对象
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomUserPackageVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户套餐ID（签到时作为 signInUserPackageId）
     */
    private Long id;

    /**
     * 签到记录里的用户套餐ID（/mp/study/index 的 signInUserPackage 返回）
     */
    private Long signInUserPackageId;

    /**
     * 套餐ID
     */
    private Long packageId;

    /**
     * 套餐名称
     */
    private String packageName;

    /**
     * 套餐类型：1 小时 2 天 3 月 7 其他
     */
    private Integer packageType;

    /**
     * 剩余可用次数/时长
     */
    private Integer restNum;

    /**
     * 总次数/时长
     */
    private Integer useNum;

    /**
     * 已使用次数
     */
    private Integer leaveRealNum;

    /**
     * 可请假次数
     */
    private Integer leaveTotalNum;

    /**
     * 有效期至（毫秒时间戳）
     */
    private Long useExpiryDate;

    /**
     * 有效期至（字符串，上游返回 yyyy-MM-dd HH:mm:ss）
     */
    private String useExpiryDateStr;

    /**
     * 适用门店ID
     */
    private Integer venueId;

    /**
     * 订单号
     */
    private String orderNumber;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 备注
     */
    private String remark;
}
