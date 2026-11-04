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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.continew.admin.business.study.client.http.StudyRoomLeaveClient;
import top.continew.admin.business.study.client.model.StudyRoomUserLeaveEnterRespDTO;
import top.continew.admin.business.study.model.req.StudyRoomUserLeaveCancelReqDTO;
import top.continew.admin.business.study.model.req.StudyRoomUserLeaveConfirmReqDTO;
import top.continew.admin.business.study.model.resp.StudyRoomUserLeaveEnterVO;
import top.continew.starter.web.model.R;

/**
 * 自习室请假域接口
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/business/study/leave")
public class StudyRoomLeaveController {

    private final StudyRoomLeaveClient leaveClient;

    /**
     * 进入请假页（校验可请假套餐）
     *
     * @param userPackageId 参数 userPackageId
     * @return 响应数据
     */
    @SaCheckPermission("business:study:leave:list")
    @GetMapping(value = "/enter")
    public R<StudyRoomUserLeaveEnterVO> userLeaveEnter(
        @RequestParam(value = "userPackageId") Long userPackageId) {
        StudyRoomUserLeaveEnterRespDTO dto = leaveClient.userLeaveEnter(userPackageId).getData();
        StudyRoomUserLeaveEnterVO vo =
            BeanUtil.copyProperties(dto, StudyRoomUserLeaveEnterVO.class);
        return R.ok(vo);
    }

    /**
     * 确认请假
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:leave:create")
    @PostMapping(value = "/confirm")
    public R<Integer> userLeaveConfirm(@RequestBody StudyRoomUserLeaveConfirmReqDTO form) {
        Integer data = leaveClient.userLeaveConfirm(form).getData();
        return R.ok(data);
    }

    /**
     * 取消请假
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:leave:delete")
    @PostMapping(value = "/cancel")
    public R<Integer> userLeaveCancel(@RequestBody StudyRoomUserLeaveCancelReqDTO form) {
        Integer data = leaveClient.userLeaveCancel(form).getData();
        return R.ok(data);
    }
}
