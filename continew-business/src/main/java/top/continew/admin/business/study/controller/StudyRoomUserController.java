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
import top.continew.admin.business.study.client.http.model.req.StudyRoomAuthorizeHeadImgClientReq;
import top.continew.admin.business.study.client.http.model.req.StudyRoomBindMobileClientReq;
import top.continew.admin.business.study.client.http.model.req.StudyRoomLoginClientReq;
import top.continew.admin.business.study.client.http.model.req.StudyRoomRefreshSessionClientReq;
import top.continew.admin.business.study.client.http.model.req.StudyRoomUpdateUserInfoClientReq;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomLoginClientResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomUserDetailClientResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomUserFeeClientResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomUserIndexClientResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomUserSignListClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomLoginResp;
import top.continew.admin.business.study.model.resp.StudyRoomUserDetailResp;
import top.continew.admin.business.study.model.resp.StudyRoomUserFeeResp;
import top.continew.admin.business.study.model.resp.StudyRoomUserIndexResp;
import top.continew.admin.business.study.model.resp.StudyRoomUserSignListResp;
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
    public R<StudyRoomLoginResp> login(@RequestBody StudyRoomLoginClientReq form) {
        StudyRoomLoginClientResp dto = userClient.login(form).getData();
        StudyRoomLoginResp vo = BeanUtil.copyProperties(dto, StudyRoomLoginResp.class);
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
    public R<StudyRoomLoginResp> bindMobile(@RequestBody StudyRoomBindMobileClientReq form) {
        StudyRoomLoginClientResp dto = userClient.bindMobile(form).getData();
        StudyRoomLoginResp vo = BeanUtil.copyProperties(dto, StudyRoomLoginResp.class);
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
    public R<Void> refreshSessionKey(@RequestBody StudyRoomRefreshSessionClientReq form) {
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
    public R<StudyRoomUserIndexResp> userIndex() {
        StudyRoomUserIndexClientResp dto = userClient.userIndex().getData();
        StudyRoomUserIndexResp vo = BeanUtil.copyProperties(dto, StudyRoomUserIndexResp.class);
        return R.ok(vo);
    }

    /**
     * 用户详情
     *
     * @return 响应数据
     */
    @SaCheckPermission("business:study:user:list")
    @GetMapping(value = "/detail")
    public R<StudyRoomUserDetailResp> userDetail() {
        StudyRoomUserDetailClientResp dto = userClient.userDetail().getData();
        StudyRoomUserDetailResp vo = BeanUtil.copyProperties(dto, StudyRoomUserDetailResp.class);
        return R.ok(vo);
    }

    /**
     * 欠费 / 当前签到状态
     *
     * @return 响应数据
     */
    @SaCheckPermission("business:study:user:list")
    @GetMapping(value = "/fee")
    public R<StudyRoomUserFeeResp> userFee() {
        StudyRoomUserFeeClientResp dto = userClient.userFee().getData();
        StudyRoomUserFeeResp vo = BeanUtil.copyProperties(dto, StudyRoomUserFeeResp.class);
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
    public R<Integer> updateUserInfo(@RequestBody StudyRoomUpdateUserInfoClientReq form) {
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
    public R<Void> authorizeHeadImg(@RequestBody StudyRoomAuthorizeHeadImgClientReq form) {
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
    public R<StudyRoomUserSignListResp> userSignList(@RequestParam(value = "id") Long id) {
        StudyRoomUserSignListClientResp dto = userClient.userSignList(id).getData();
        StudyRoomUserSignListResp vo =
            BeanUtil.copyProperties(dto, StudyRoomUserSignListResp.class);
        return R.ok(vo);
    }

    /**
     * 更早的签到记录（分页续拉，无入参）
     *
     * @return 响应数据
     */
    @SaCheckPermission("business:study:user:list")
    @GetMapping(value = "/sign/list/more")
    public R<StudyRoomUserSignListResp> userSignListMore() {
        StudyRoomUserSignListClientResp dto = userClient.userSignListMore().getData();
        StudyRoomUserSignListResp vo =
            BeanUtil.copyProperties(dto, StudyRoomUserSignListResp.class);
        return R.ok(vo);
    }
}
