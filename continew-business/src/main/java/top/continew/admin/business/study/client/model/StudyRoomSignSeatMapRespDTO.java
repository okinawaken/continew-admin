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
 * 签到选座座位图（/mp/study/seat/list、/mp/study/change/seat/list）
 * <p>
 * 与 {@link StudyRoomSeatMapRespDTO} 结构一致，额外带上门店与当前签到信息。
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomSignSeatMapRespDTO extends StudyRoomSeatMapRespDTO {

    /**
     * 当前签到ID
     */
    private Long userSignId;

    /**
     * 签到扣费套餐
     */
    private StudyRoomUserPackageRespDTO signInUserPackage;

    /**
     * 是否再签一个座位：1 是
     */
    private Integer signAnotherFlag;
}
