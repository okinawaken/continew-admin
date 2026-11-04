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
import com.dtflys.forest.annotation.Var;
import top.continew.admin.business.study.client.model.req.StudyRoomRepairSubmitReqDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomApiResultDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomRepairListRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomRepairRespDTO;

/**
 * 报修域：提交报修、报修记录
 *
 * @author Amadeus
 * @since 4.2.0
 */
public interface StudyRoomRepairClient extends StudyRoomBaseClient {

    /**
     * 提交报修（实测成功时 {@code data} 为 {@code null}）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/userRepair/submit")
    StudyRoomApiResultDTO<Void> submitRepair(@JSONBody StudyRoomRepairSubmitReqDTO form);

    /**
     * 报修列表
     *
     * @return 响应数据
     */
    @Get(url = "/mp/userRepair/list")
    StudyRoomApiResultDTO<StudyRoomRepairListRespDTO> repairList();

    /**
     * 报修详情
     *
     * @param id ID
     * @return 响应数据
     */
    @Get(url = "/mp/userRepair/detail/${id}")
    StudyRoomApiResultDTO<StudyRoomRepairRespDTO> repairDetail(@Var(value = "id") Long id);

}
