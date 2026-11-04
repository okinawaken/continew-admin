package top.continew.admin.business.study.client.http;

import com.dtflys.forest.annotation.DataFile;
import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.JSONBody;
import com.dtflys.forest.annotation.Post;
import com.dtflys.forest.annotation.Query;
import com.dtflys.forest.annotation.Var;
import top.continew.admin.business.study.constant.StudyRoomApiConstants;
import top.continew.admin.business.study.model.req.StudyRoomAuthorizeHeadImgClientReq;
import top.continew.admin.business.study.model.req.StudyRoomBindMobileClientReq;
import top.continew.admin.business.study.model.req.StudyRoomLoginClientReq;
import top.continew.admin.business.study.model.req.StudyRoomRefreshSessionClientReq;
import top.continew.admin.business.study.model.req.StudyRoomUpdateUserInfoClientReq;
import top.continew.admin.business.study.model.resp.StudyRoomClientResult;
import top.continew.admin.business.study.model.resp.StudyRoomFileClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomLoginClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomUserDetailClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomUserFeeClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomUserIndexClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomUserSignListClientResp;

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
    StudyRoomClientResult<StudyRoomLoginClientResp> login(
        @JSONBody StudyRoomLoginClientReq form,
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
    StudyRoomClientResult<StudyRoomLoginClientResp> bindMobile(
        @JSONBody StudyRoomBindMobileClientReq form,
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
    StudyRoomClientResult<Void> refreshSessionKey(
        @JSONBody StudyRoomRefreshSessionClientReq form,
        @Var(value = StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 用户概览：头像、手机号、累计签到次数、累计学习时长
     *
     * @param userId 用户 ID
     * @return 响应数据
     */
    @Get(url = "/mp/user/index")
    StudyRoomClientResult<StudyRoomUserIndexClientResp> userIndex(
        @Var(value = StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 用户详情
     *
     * @param userId 用户 ID
     * @return 响应数据
     */
    @Get(url = "/mp/user/detailInfo")
    StudyRoomClientResult<StudyRoomUserDetailClientResp> userDetail(
        @Var(value = StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 欠费 / 当前签到状态
     *
     * @param userId 用户 ID
     * @return 响应数据
     */
    @Get(url = "/mp/user/info/fee")
    StudyRoomClientResult<StudyRoomUserFeeClientResp> userFee(
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
    StudyRoomClientResult<Integer> updateUserInfo(
        @JSONBody StudyRoomUpdateUserInfoClientReq form,
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
    StudyRoomClientResult<Void> authorizeHeadImg(
        @JSONBody StudyRoomAuthorizeHeadImgClientReq form,
        @Var(value = StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 上传授权头像（文件形式，multipart 字段名 {@code files}）
     *
     * @param file 参数 file
     * @param userId 用户 ID
     * @return 响应数据
     */
    @Post(url = "/mp/user/authorize/headImg/upload", contentType = "multipart/form-data")
    StudyRoomClientResult<StudyRoomFileClientResp> uploadAuthorizeHeadImg(
        @DataFile(value = "files") File file,
        @Var(value = StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 解除授权（退出登录）
     *
     * @param userId 用户 ID
     * @return 响应数据
     */
    @Post(url = "/mp/user/authorize/cancel")
    StudyRoomClientResult<Void> cancelAuthorize(
        @Var(value = StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 签到记录列表（最近 N 条）
     * <p>
     * 入参 {@code id} 传 0 即可；返回 {@code {list:[StudyRoomUserSignClientResp]}}。
     *
     * @param id ID
     * @param userId 用户 ID
     * @return 响应数据
     */
    @Get(url = "/mp/user/userSign/list")
    StudyRoomClientResult<StudyRoomUserSignListClientResp> userSignList(
        @Query(value = "id") Long id,
        @Var(value = StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 更早的签到记录（分页续拉，无入参）
     *
     * @param userId 用户 ID
     * @return 响应数据
     */
    @Get(url = "/mp/user/userSign/list/more")
    StudyRoomClientResult<StudyRoomUserSignListClientResp> userSignListMore(
        @Var(value = StudyRoomApiConstants.Variable.USER_ID) Long userId);

}
