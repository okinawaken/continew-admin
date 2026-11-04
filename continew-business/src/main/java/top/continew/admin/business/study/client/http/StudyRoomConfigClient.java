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
import com.dtflys.forest.annotation.Query;
import top.continew.admin.business.study.client.model.StudyRoomApiResultDTO;
import top.continew.admin.business.study.client.model.StudyRoomConfigRespDTO;
import top.continew.admin.business.study.client.model.StudyRoomNoticeRespDTO;

/**
 * 配置域：全局配置、公告
 *
 * @author Amadeus
 * @since 4.2.0
 */
public interface StudyRoomConfigClient extends StudyRoomBaseClient {

    /**
     * 小程序全局配置（含可提前预约天数、保留时长等）
     *
     * @return 响应数据
     */
    @Get(url = "/mp/sys/config/data")
    StudyRoomApiResultDTO<StudyRoomConfigRespDTO> sysConfig();

    /**
     * 公告栏
     * <p>
     * 小程序传 {@code venueId=0} 表示不区分门店
     *
     * @param venueId 门店 ID
     * @return 响应数据
     */
    @Get(url = "/mp/notice/board/info")
    StudyRoomApiResultDTO<StudyRoomNoticeRespDTO> noticeBoard(
        @Query(value = "venueId") Integer venueId);

}
