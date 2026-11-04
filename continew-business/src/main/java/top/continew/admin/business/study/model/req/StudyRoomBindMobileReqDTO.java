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
 * 授权手机号登录（/mp/user/authorize/bindMobile）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomBindMobileReqDTO {

    /**
     * wx.login 返回的 code（必填）
     */
    private String wechatCode;

    /**
     * getPhoneNumber / getUserProfile 回调中的 detail（含 encryptedData / iv / userInfo）
     */
    private StudyRoomWxDataReqDTO wechatData;
}
