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
 * 微信支付下单（/mp/buy/package/payOrder）
 * <p>
 * 字段与小程序源码保持一致。
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomBuyPayOrderReqDTO {

    /**
     * 用户订单ID（必填，取自确认下单返回的 userOrderId）
     */
    private Long userOrderId;

}
