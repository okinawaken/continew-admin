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
 * 小程序登录（/mp/user/login）
 * <p>
 * 小程序侧用 wx.login 拿到的 code 换取 openid 并返回 token； smart-admin 作为中转层时，一般直接使用用户绑定/粘贴的 token，此接口备用。
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomLoginReqDTO {

    /**
     * wx.login 返回的 code（必填）
     */
    private String wechatCode;
}
