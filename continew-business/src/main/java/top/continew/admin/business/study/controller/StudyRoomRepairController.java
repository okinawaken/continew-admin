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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.continew.admin.business.study.client.http.StudyRoomRepairClient;
import top.continew.admin.business.study.client.model.StudyRoomRepairListRespDTO;
import top.continew.admin.business.study.client.model.StudyRoomRepairRespDTO;
import top.continew.admin.business.study.model.req.StudyRoomRepairSubmitReqDTO;
import top.continew.admin.business.study.model.resp.StudyRoomRepairListVO;
import top.continew.admin.business.study.model.resp.StudyRoomRepairVO;
import top.continew.starter.web.model.R;

/**
 * 自习室报修域接口
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/business/study/repair")
public class StudyRoomRepairController {

    private final StudyRoomRepairClient repairClient;

    /**
     * 提交报修
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:repair:create")
    @PostMapping(value = "/submit")
    public R<Void> submitRepair(@RequestBody StudyRoomRepairSubmitReqDTO form) {
        repairClient.submitRepair(form);
        return R.ok();
    }

    /**
     * 报修列表
     *
     * @return 响应数据
     */
    @SaCheckPermission("business:study:repair:list")
    @GetMapping(value = "/list")
    public R<StudyRoomRepairListVO> repairList() {
        StudyRoomRepairListRespDTO dto = repairClient.repairList().getData();
        StudyRoomRepairListVO vo = BeanUtil.copyProperties(dto, StudyRoomRepairListVO.class);
        return R.ok(vo);
    }

    /**
     * 报修详情
     *
     * @param id ID
     * @return 响应数据
     */
    @SaCheckPermission("business:study:repair:list")
    @GetMapping(value = "/detail/{id}")
    public R<StudyRoomRepairVO> repairDetail(@PathVariable(value = "id") Long id) {
        StudyRoomRepairRespDTO dto = repairClient.repairDetail(id).getData();
        StudyRoomRepairVO vo = BeanUtil.copyProperties(dto, StudyRoomRepairVO.class);
        return R.ok(vo);
    }
}
