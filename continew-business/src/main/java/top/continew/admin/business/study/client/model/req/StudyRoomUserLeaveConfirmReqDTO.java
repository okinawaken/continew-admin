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
 * 确认请假（/mp/user/userLeave/confirmLeave）
 * <p>
 * 字段与小程序源码保持一致：{@code leaveStartTimeStr}/{@code leaveEndTimeStr}/{@code leaveReason}。
 * 日期格式为 {@code yyyy-M-d}（月、日不补零）。
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomUserLeaveConfirmReqDTO {

    /**
     * 用户套餐ID（必填）
     */
    private Long userPackageId;

    /**
     * 请假开始日期 {@code yyyy-M-d}（必填）
     */
    private String leaveStartTimeStr;

    /**
     * 请假结束日期 {@code yyyy-M-d}（必填）
     */
    private String leaveEndTimeStr;

    /**
     * 请假原因（必填）
     */
    private String leaveReason;
}
