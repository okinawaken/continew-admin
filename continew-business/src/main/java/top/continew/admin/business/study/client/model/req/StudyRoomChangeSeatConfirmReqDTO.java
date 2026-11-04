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

package top.continew.admin.business.study.client.model.req;

import lombok.Data;

/**
 * 换座确认（/mp/study/change/seat/confirm）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomChangeSeatConfirmReqDTO {

    /**
     * 目标座位ID（换座座位图中的 seat.id）（必填）
     */
    private Long seatId;

    /**
     * 当前经度（可传门店经度）
     */
    private Double currentLng;

    /**
     * 当前纬度（可传门店纬度）
     */
    private Double currentLat;

    /**
     * 当前签到ID（必填）
     */
    private Long userSignId;

}
