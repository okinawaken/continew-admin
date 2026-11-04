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

package top.continew.admin.business.study.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.continew.admin.business.study.client.http.StudyRoomConfigClient;
import top.continew.admin.business.study.client.model.resp.StudyRoomConfigRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomNoticeRespDTO;
import top.continew.admin.business.study.model.resp.StudyRoomConfigVO;
import top.continew.admin.business.study.model.resp.StudyRoomNoticeVO;
import top.continew.starter.web.model.R;

/**
 * 自习室配置域接口
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/business/study/config")
public class StudyRoomConfigController {

    private final StudyRoomConfigClient configClient;

    /**
     * 小程序全局配置（含可提前预约天数、保留时长等）
     *
     * @return 响应数据
     */
    @SaCheckPermission("business:study:config:list")
    @GetMapping(value = "/sys")
    public R<StudyRoomConfigVO> sysConfig() {
        StudyRoomConfigRespDTO dto = configClient.sysConfig().getData();
        StudyRoomConfigVO vo = BeanUtil.copyProperties(dto, StudyRoomConfigVO.class);
        return R.ok(vo);
    }

    /**
     * 公告栏
     *
     * @param venueId 门店 ID
     * @return 响应数据
     */
    @SaCheckPermission("business:study:config:list")
    @GetMapping(value = "/notice")
    public R<StudyRoomNoticeVO> noticeBoard(@RequestParam(value = "venueId") Integer venueId) {
        StudyRoomNoticeRespDTO dto = configClient.noticeBoard(venueId).getData();
        StudyRoomNoticeVO vo = BeanUtil.copyProperties(dto, StudyRoomNoticeVO.class);
        return R.ok(vo);
    }
}
