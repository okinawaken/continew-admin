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

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 储物柜下单（/mp/cabinetGrip/v2/lease/commit）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomCabinetLeaseCommitReqDTO {

    /**
     * 应付金额（元）（必填）
     */
    private BigDecimal amount;

    /**
     * 租用开始日期 yyyy-MM-dd（必填）
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    /**
     * 租用结束日期 yyyy-MM-dd（必填）
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    /**
     * 柜格ID（必填）
     */
    private Integer gridId;

    /**
     * 租用类型：1 新租 2 续租（必填）
     */
    private Integer leaseType;

    /**
     * 续租时的原租约ID（leaseType=2 时必传）
     */
    private Integer originLeaseId;

}
