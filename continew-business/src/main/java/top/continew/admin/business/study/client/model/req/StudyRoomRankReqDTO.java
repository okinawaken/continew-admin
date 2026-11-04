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
 * 学习排行榜（/mp/study/rank/index）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomRankReqDTO {

    /**
     * 门店ID
     */
    private Integer venueId;

    /**
     * 排行类型
     */
    private Integer rankType;
}
