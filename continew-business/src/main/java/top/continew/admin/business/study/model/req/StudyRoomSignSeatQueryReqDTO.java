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
 * 签到选座座位图（/mp/study/seat/list）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomSignSeatQueryReqDTO {

    /**
     * 门店前台二维码内容（必填）
     */
    private String qrCode;

    /**
     * 门店ID
     */
    private Integer venueId;

    /**
     * 签到扣费套餐ID（用户套餐ID）
     */
    private Long signInUserPackageId;

    /**
     * 是否加签：1 是 0 否
     */
    private Integer signAnotherFlag;
}
