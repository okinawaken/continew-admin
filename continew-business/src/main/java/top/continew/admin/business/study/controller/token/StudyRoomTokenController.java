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

package top.continew.admin.business.study.controller.token;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.continew.admin.business.study.model.req.StudyRoomTokenBindReqDTO;
import top.continew.admin.business.study.model.resp.StudyRoomTokenVO;
import top.continew.admin.business.study.service.token.StudyRoomTokenService;
import top.continew.admin.common.context.UserContextHolder;
import top.continew.starter.web.model.R;

/**
 * 自习室小程序 token 绑定
 * <p>
 * token 始终绑定到当前登录用户，不接受前端传入的 userId，避免越权绑定他人 token。
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/business/study/token")
public class StudyRoomTokenController {

    private final StudyRoomTokenService tokenService;

    /**
     * 绑定或更新当前登录用户的小程序 token
     *
     * @param req 绑定请求
     * @return 响应数据
     */
    @SaCheckPermission("business:study:token:update")
    @PostMapping(value = "/bind")
    public R<Void> bind(@Validated @RequestBody StudyRoomTokenBindReqDTO req) {
        tokenService.save(UserContextHolder.getUserId(), req.getToken());
        return R.ok();
    }

    /**
     * 查询当前登录用户的 token 绑定状态
     *
     * @return 绑定状态及完整 token
     */
    @SaCheckPermission("business:study:token:list")
    @GetMapping(value = "/detail")
    public R<StudyRoomTokenVO> get() {
        String token = tokenService.get(UserContextHolder.getUserId());
        StudyRoomTokenVO vo = new StudyRoomTokenVO();
        vo.setBound(StrUtil.isNotEmpty(token));
        vo.setToken(token);
        return R.ok(vo);
    }

    /**
     * 解绑当前登录用户的小程序 token
     *
     * @return 响应数据
     */
    @SaCheckPermission("business:study:token:delete")
    @DeleteMapping(value = "/unbind")
    public R<Void> unbind() {
        tokenService.remove(UserContextHolder.getUserId());
        return R.ok();
    }
}
