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
 * StudyRoomReserveAnotherCheckRespDTO 视图对象
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomReserveAnotherCheckVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 是否可再约：1 可以
     */
    private Integer canReserveAnother;

    /**
     * 是否有可用的加座套餐：1 有
     */
    private Integer hasReserveAnotherPackage;

    /**
     * 不可再约原因
     */
    private String descNoReserveAnother;
}
