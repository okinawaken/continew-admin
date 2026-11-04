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

package top.continew.admin.business.study.client.model.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 进入签退结算（/mp/study/settlement）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomSettlementRespDTO {

    /**
     * 签退类型：1 表示可直接签退（前端直接跳结算成功页）
     */
    private Integer signOutType;

    /**
     * 命中的用户套餐ID
     */
    private Long userPackageId;

    /**
     * 门店名称
     */
    private String venueName;

    /**
     * 门店地址
     */
    private String venueAddress;

    /**
     * 提示文案
     */
    private String studyTipText;

    /**
     * 是否需要购买套餐：1 需要
     */
    private Integer needBuyPackage;

    /**
     * 结算明细
     */
    private StudyRoomSettleDataRespDTO settleData;

    /**
     * 可选结算套餐列表
     */
    private List<StudyRoomSettlePackageRespDTO> userPackageList;

}
