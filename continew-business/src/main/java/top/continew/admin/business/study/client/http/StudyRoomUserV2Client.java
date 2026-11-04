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

import com.dtflys.forest.annotation.DataFile;
import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.JSONBody;
import com.dtflys.forest.annotation.Post;
import com.dtflys.forest.annotation.Query;
import com.dtflys.forest.annotation.Var;
import top.continew.admin.business.study.client.model.req.StudyRoomAuthorizeHeadImgReqDTO;
import top.continew.admin.business.study.client.model.req.StudyRoomBindMobileReqDTO;
import top.continew.admin.business.study.client.model.req.StudyRoomLoginReqDTO;
import top.continew.admin.business.study.client.model.req.StudyRoomRefreshSessionReqDTO;
import top.continew.admin.business.study.client.model.req.StudyRoomUpdateUserInfoReqDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomApiResultDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomFileRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomLoginRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomUserDetailRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomUserFeeRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomUserIndexRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomUserSignListRespDTO;
import top.continew.admin.business.study.constant.StudyRoomApiConstants;

import java.io.File;

/**
 * 用户域：登录、token、资料、头像（显式用户上下文）
 *
 * @author Amadeus
 * @since 4.2.0
 */
public interface StudyRoomUserV2Client extends StudyRoomBaseClient {

    /**
     * 小程序登录：用 wx.login 的 code 换 token
     * <p>
     * 入参：{@code {wechatCode}}（实测）
     *
     * @param form 请求参数
     * @param userId 用户 ID
     * @return 响应数据
     */
    @Deprecated
    @Post(url = "/mp/user/login")
    StudyRoomApiResultDTO<StudyRoomLoginRespDTO> login(
        @JSONBody StudyRoomLoginReqDTO form,
        @Var(value = StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 授权手机号登录（拿 token）
     * <p>
     * 入参：{@code {wechatCode, wechatData}}（实测）
     *
     * @param form 请求参数
     * @param userId 用户 ID
     * @return 响应数据
     */
    @Deprecated
    @Post(url = "/mp/user/authorize/bindMobile")
    StudyRoomApiResultDTO<StudyRoomLoginRespDTO> bindMobile(
        @JSONBody StudyRoomBindMobileReqDTO form,
        @Var(value = StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 刷新 sessionKey
     * <p>
     * 入参：{@code {wechatCode}}（实测）；成功时 {@code data} 为 {@code null}
     *
     * @param form 请求参数
     * @param userId 用户 ID
     * @return 响应数据
     */
    @Deprecated
    @Post(url = "/mp/user/refresh/sessionKey")
    StudyRoomApiResultDTO<Void> refreshSessionKey(
        @JSONBody StudyRoomRefreshSessionReqDTO form,
        @Var(value = StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 用户概览：头像、手机号、累计签到次数、累计学习时长
     *
     * @param userId 用户 ID
     * @return 响应数据
     */
    @Get(url = "/mp/user/index")
    StudyRoomApiResultDTO<StudyRoomUserIndexRespDTO> userIndex(
        @Var(value = StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 用户详情
     *
     * @param userId 用户 ID
     * @return 响应数据
     */
    @Get(url = "/mp/user/detailInfo")
    StudyRoomApiResultDTO<StudyRoomUserDetailRespDTO> userDetail(
        @Var(value = StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 欠费 / 当前签到状态
     *
     * @param userId 用户 ID
     * @return 响应数据
     */
    @Get(url = "/mp/user/info/fee")
    StudyRoomApiResultDTO<StudyRoomUserFeeRespDTO> userFee(
        @Var(value = StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 修改资料
     * <p>
     * 实测：传空对象也返回 {@code code=200, data=1}，说明服务端忽略空字段；{@code data} 是整数
     *
     * @param form 请求参数
     * @param userId 用户 ID
     * @return 响应数据
     */
    @Post(url = "/mp/user/updateInfo")
    StudyRoomApiResultDTO<Integer> updateUserInfo(
        @JSONBody StudyRoomUpdateUserInfoReqDTO form,
        @Var(value = StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 更新授权头像（URL 形式）
     * <p>
     * 入参：{@code {nickName, headImg}}（实测）；成功时 {@code data} 为 {@code null}
     *
     * @param form 请求参数
     * @param userId 用户 ID
     * @return 响应数据
     */
    @Post(url = "/mp/user/authorize/headImg")
    StudyRoomApiResultDTO<Void> authorizeHeadImg(
        @JSONBody StudyRoomAuthorizeHeadImgReqDTO form,
        @Var(value = StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 上传授权头像（文件形式，multipart 字段名 {@code files}）
     *
     * @param file 参数 file
     * @param userId 用户 ID
     * @return 响应数据
     */
    @Post(url = "/mp/user/authorize/headImg/upload", contentType = "multipart/form-data")
    StudyRoomApiResultDTO<StudyRoomFileRespDTO> uploadAuthorizeHeadImg(
        @DataFile(value = "files") File file,
        @Var(value = StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 解除授权（退出登录）
     *
     * @param userId 用户 ID
     * @return 响应数据
     */
    @Post(url = "/mp/user/authorize/cancel")
    StudyRoomApiResultDTO<Void> cancelAuthorize(
        @Var(value = StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 签到记录列表（最近 N 条）
     * <p>
     * 入参 {@code id} 传 0 即可；返回 {@code {list:[StudyRoomUserSignRespDTO]}}。
     *
     * @param id ID
     * @param userId 用户 ID
     * @return 响应数据
     */
    @Get(url = "/mp/user/userSign/list")
    StudyRoomApiResultDTO<StudyRoomUserSignListRespDTO> userSignList(
        @Query(value = "id") Long id,
        @Var(value = StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 更早的签到记录（分页续拉，无入参）
     *
     * @param userId 用户 ID
     * @return 响应数据
     */
    @Get(url = "/mp/user/userSign/list/more")
    StudyRoomApiResultDTO<StudyRoomUserSignListRespDTO> userSignListMore(
        @Var(value = StudyRoomApiConstants.Variable.USER_ID) Long userId);

}
