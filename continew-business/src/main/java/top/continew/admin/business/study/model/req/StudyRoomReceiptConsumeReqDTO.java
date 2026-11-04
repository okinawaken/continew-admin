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
 * 美团团购券核销（/mp/receipt/consume）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomReceiptConsumeReqDTO {

    /**
     * 门店ID（必填）
     */
    private Integer venueId;

    /**
     * 团购券码（必填）
     */
    private String receiptCode;
}
