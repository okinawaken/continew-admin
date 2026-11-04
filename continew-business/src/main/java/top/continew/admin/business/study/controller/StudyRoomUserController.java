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
import top.continew.admin.business.study.client.http.StudyRoomUserClient;
import top.continew.admin.business.study.client.model.StudyRoomLoginRespDTO;
import top.continew.admin.business.study.client.model.StudyRoomUserDetailRespDTO;
import top.continew.admin.business.study.client.model.StudyRoomUserFeeRespDTO;
import top.continew.admin.business.study.client.model.StudyRoomUserIndexRespDTO;
import top.continew.admin.business.study.client.model.StudyRoomUserSignListRespDTO;
import top.continew.admin.business.study.model.req.StudyRoomAuthorizeHeadImgReqDTO;
import top.continew.admin.business.study.model.req.StudyRoomBindMobileReqDTO;
import top.continew.admin.business.study.model.req.StudyRoomLoginReqDTO;
import top.continew.admin.business.study.model.req.StudyRoomRefreshSessionReqDTO;
import top.continew.admin.business.study.model.req.StudyRoomUpdateUserInfoReqDTO;
import top.continew.admin.business.study.model.resp.StudyRoomLoginVO;
import top.continew.admin.business.study.model.resp.StudyRoomUserDetailVO;
import top.continew.admin.business.study.model.resp.StudyRoomUserFeeVO;
import top.continew.admin.business.study.model.resp.StudyRoomUserIndexVO;
import top.continew.admin.business.study.model.resp.StudyRoomUserSignListVO;
import top.continew.starter.core.exception.BusinessException;
import top.continew.starter.web.model.R;

/**
 * 自习室用户域接口：登录、资料、头像、签到记录
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/business/study/user")
public class StudyRoomUserController {

    private final StudyRoomUserClient userClient;

    /**
     * 小程序登录：用 wx.login 的 code 换 token
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Deprecated
    @SaCheckPermission("business:study:user:create")
    @PostMapping(value = "/login")
    public R<StudyRoomLoginVO> login(@RequestBody StudyRoomLoginReqDTO form) {
        StudyRoomLoginRespDTO dto = userClient.login(form).getData();
        StudyRoomLoginVO vo = BeanUtil.copyProperties(dto, StudyRoomLoginVO.class);
        return R.ok(vo);
    }

    /**
     * 授权手机号登录（拿 token）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Deprecated
    @SaCheckPermission("business:study:user:create")
    @PostMapping(value = "/bind-mobile")
    public R<StudyRoomLoginVO> bindMobile(@RequestBody StudyRoomBindMobileReqDTO form) {
        StudyRoomLoginRespDTO dto = userClient.bindMobile(form).getData();
        StudyRoomLoginVO vo = BeanUtil.copyProperties(dto, StudyRoomLoginVO.class);
        return R.ok(vo);
    }

    /**
     * 刷新 sessionKey
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Deprecated
    @SaCheckPermission("business:study:user:create")
    @PostMapping(value = "/refresh-session-key")
    public R<Void> refreshSessionKey(@RequestBody StudyRoomRefreshSessionReqDTO form) {
        userClient.refreshSessionKey(form);
        return R.ok();
    }

    /**
     * 用户概览：头像、手机号、累计签到次数、累计学习时长
     *
     * @return 响应数据
     */
    @SaCheckPermission("business:study:user:list")
    @GetMapping(value = "/index")
    public R<StudyRoomUserIndexVO> userIndex() {
        StudyRoomUserIndexRespDTO dto = userClient.userIndex().getData();
        StudyRoomUserIndexVO vo = BeanUtil.copyProperties(dto, StudyRoomUserIndexVO.class);
        return R.ok(vo);
    }

    /**
     * 用户详情
     *
     * @return 响应数据
     */
    @SaCheckPermission("business:study:user:list")
    @GetMapping(value = "/detail")
    public R<StudyRoomUserDetailVO> userDetail() {
        StudyRoomUserDetailRespDTO dto = userClient.userDetail().getData();
        StudyRoomUserDetailVO vo = BeanUtil.copyProperties(dto, StudyRoomUserDetailVO.class);
        return R.ok(vo);
    }

    /**
     * 欠费 / 当前签到状态
     *
     * @return 响应数据
     */
    @SaCheckPermission("business:study:user:list")
    @GetMapping(value = "/fee")
    public R<StudyRoomUserFeeVO> userFee() {
        StudyRoomUserFeeRespDTO dto = userClient.userFee().getData();
        StudyRoomUserFeeVO vo = BeanUtil.copyProperties(dto, StudyRoomUserFeeVO.class);
        return R.ok(vo);
    }

    /**
     * 修改资料
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:user:update")
    @PostMapping(value = "/update-info")
    public R<Integer> updateUserInfo(@RequestBody StudyRoomUpdateUserInfoReqDTO form) {
        Integer data = userClient.updateUserInfo(form).getData();
        return R.ok(data);
    }

    /**
     * 更新授权头像（URL 形式）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:user:update")
    @PostMapping(value = "/authorize/head-img")
    public R<Void> authorizeHeadImg(@RequestBody StudyRoomAuthorizeHeadImgReqDTO form) {
        userClient.authorizeHeadImg(form);
        return R.ok();
    }

    /**
     * 上传授权头像（当前不支持）
     *
     * @return 响应数据
     */
    @SaCheckPermission("business:study:user:update")
    @PostMapping(value = "/authorize/head-img/upload")
    public R<Void> uploadAuthorizeHeadImg() {
        throw new BusinessException("暂不支持文件上传");
    }

    /**
     * 解除授权（退出登录）
     *
     * @return 响应数据
     */
    @SaCheckPermission("business:study:user:delete")
    @PostMapping(value = "/authorize/cancel")
    public R<Void> cancelAuthorize() {
        userClient.cancelAuthorize();
        return R.ok();
    }

    /**
     * 签到记录列表（最近 N 条），id 传 0 即可
     *
     * @param id ID
     * @return 响应数据
     */
    @SaCheckPermission("business:study:user:list")
    @GetMapping(value = "/sign/list")
    public R<StudyRoomUserSignListVO> userSignList(@RequestParam(value = "id") Long id) {
        StudyRoomUserSignListRespDTO dto = userClient.userSignList(id).getData();
        StudyRoomUserSignListVO vo = BeanUtil.copyProperties(dto, StudyRoomUserSignListVO.class);
        return R.ok(vo);
    }

    /**
     * 更早的签到记录（分页续拉，无入参）
     *
     * @return 响应数据
     */
    @SaCheckPermission("business:study:user:list")
    @GetMapping(value = "/sign/list/more")
    public R<StudyRoomUserSignListVO> userSignListMore() {
        StudyRoomUserSignListRespDTO dto = userClient.userSignListMore().getData();
        StudyRoomUserSignListVO vo = BeanUtil.copyProperties(dto, StudyRoomUserSignListVO.class);
        return R.ok(vo);
    }
}
