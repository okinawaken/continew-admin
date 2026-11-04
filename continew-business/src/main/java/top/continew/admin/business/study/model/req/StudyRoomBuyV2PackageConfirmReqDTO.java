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

/**
 * 确认下单 v2（/mp/buy/v2/package/confirm）
 * <p>
 * 字段与小程序源码保持一致。
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomBuyV2PackageConfirmReqDTO {

    /**
     * 套餐ID（必填）
     */
    private Long packageId;

    /**
     * 门店ID（必填）
     */
    private Integer venueId;

    /**
     * 当前经度
     */
    private Double currentLng;

    /**
     * 当前纬度
     */
    private Double currentLat;

    /**
     * 购买数量（必填）
     */
    private Integer purchaseNum;

    /**
     * 座位区域ID（可选，取自套餐标签接口的 seatAreaList）
     */
    private Long seatAreaId;

    /**
     * 套餐类型（可选，取自套餐详情的 packageType）
     */
    private Integer packageType;

}
