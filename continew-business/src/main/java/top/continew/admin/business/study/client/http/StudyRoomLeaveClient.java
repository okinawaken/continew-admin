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

package top.continew.admin.business.study.client.http;

import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.JSONBody;
import com.dtflys.forest.annotation.Post;
import com.dtflys.forest.annotation.Query;
import top.continew.admin.business.study.client.model.req.StudyRoomUserLeaveCancelReqDTO;
import top.continew.admin.business.study.client.model.req.StudyRoomUserLeaveConfirmReqDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomApiResultDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomUserLeaveEnterRespDTO;

/**
 * 请假域：套餐请假
 *
 * @author Amadeus
 * @since 4.2.0
 */
public interface StudyRoomLeaveClient extends StudyRoomBaseClient {

    /**
     * 进入请假页（校验可请假套餐）
     *
     * @param userPackageId 参数 userPackageId
     * @return 响应数据
     */
    @Get(url = "/mp/user/userLeave/enterLeave")
    StudyRoomApiResultDTO<StudyRoomUserLeaveEnterRespDTO> userLeaveEnter(
        @Query(value = "userPackageId") Long userPackageId);

    /**
     * 确认请假
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/user/userLeave/confirmLeave")
    StudyRoomApiResultDTO<Integer> userLeaveConfirm(@JSONBody StudyRoomUserLeaveConfirmReqDTO form);

    /**
     * 取消请假
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/user/userLeave/cancelLeave")
    StudyRoomApiResultDTO<Integer> userLeaveCancel(@JSONBody StudyRoomUserLeaveCancelReqDTO form);

}
