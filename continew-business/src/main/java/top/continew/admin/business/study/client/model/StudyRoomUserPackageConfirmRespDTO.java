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
 * 确认签退结算套餐（/mp/study/userPackage/confirm）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomUserPackageConfirmRespDTO {

    /**
     * 欠费分钟数
     */
    private Integer unpaidMinute;

    /**
     * 结算使用的用户套餐ID
     */
    private Long userPackageId;

    /**
     * 签退类型
     */
    private Integer signOutType;

    /**
     * 提示文案
     */
    private String studyTipText;

    /**
     * 结算明细
     */
    private StudyRoomSettleDataRespDTO settleData;

}
