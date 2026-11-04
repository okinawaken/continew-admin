package top.continew.admin.business.study.controller.client;

import cn.dev33.satoken.annotation.SaCheckPermission;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.continew.admin.business.study.client.http.StudyRoomReserveClient;
import top.continew.admin.business.study.model.req.StudyRoomReserveConfirmClientReq;
import top.continew.admin.business.study.model.req.StudyRoomReserveConfirmV1ClientReq;
import top.continew.admin.business.study.model.req.StudyRoomSeatQueryClientReq;
import top.continew.admin.business.study.model.req.StudyRoomSeatQueryV1ClientReq;
import top.continew.admin.business.study.model.req.StudyRoomUserReserveCancelClientReq;
import top.continew.admin.business.study.model.resp.StudyRoomReserveAnotherCheckClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomReserveCheckClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomReserveConfirmClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomReserveDateClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomSeatMapClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomUserReserveCancelTipClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomUserReserveListClientResp;
import top.continew.starter.web.model.R;

/**
 * 自习室预约域接口：座位图、下单、我的预约、取消
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/business/study/reserve")
public class StudyRoomReserveController {

    private final StudyRoomReserveClient reserveClient;

    /**
     * 预约资格校验（是否有可预约套餐）
     *
     * @return 响应数据
     */
    @SaCheckPermission("business:study:reserve:list")
    @GetMapping(value = "/check")
    public R<StudyRoomReserveCheckClientResp> reserveCheck() {
        return R.ok(reserveClient.reserveCheck().getData());
    }

    /**
     * 可预约日期列表
     *
     * @param venueId 门店 ID
     * @return 响应数据
     */
    @SaCheckPermission("business:study:reserve:list")
    @GetMapping(value = "/v2/date/select")
    public R<StudyRoomReserveDateClientResp> reserveDateList(
        @RequestParam(value = "venueId") Integer venueId) {
        return R.ok(reserveClient.reserveDateList(venueId).getData());
    }

    /**
     * 座位图（v2，推荐）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:reserve:list")
    @PostMapping(value = "/v2/seat/list")
    public R<StudyRoomSeatMapClientResp> reserveV2SeatList(
        @RequestBody StudyRoomSeatQueryClientReq form) {
        return R.ok(reserveClient.reserveV2SeatList(form).getData());
    }

    /**
     * 提交预约（v2，推荐）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:reserve:create")
    @PostMapping(value = "/v2/seat/confirm")
    public R<StudyRoomReserveConfirmClientResp> reserveV2SeatConfirm(
        @RequestBody StudyRoomReserveConfirmClientReq form) {
        return R.ok(reserveClient.reserveV2SeatConfirm(form).getData());
    }

    /**
     * 座位图（v1，旧版，时间格式 {@code yyyy-MM-dd HH:mm}）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:reserve:list")
    @PostMapping(value = "/seat/list")
    public R<StudyRoomSeatMapClientResp> reserveSeatList(
        @RequestBody StudyRoomSeatQueryV1ClientReq form) {
        return R.ok(reserveClient.reserveSeatList(form).getData());
    }

    /**
     * 提交预约（v1，旧版，时间格式 {@code yyyy-MM-dd HH:mm}）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:reserve:create")
    @PostMapping(value = "/seat/confirm")
    public R<StudyRoomReserveConfirmClientResp> reserveSeatConfirm(
        @RequestBody StudyRoomReserveConfirmV1ClientReq form) {
        return R.ok(reserveClient.reserveSeatConfirm(form).getData());
    }

    /**
     * 是否还能再预约一个座位
     *
     * @param userReserveId 用户预约 ID
     * @return 响应数据
     */
    @SaCheckPermission("business:study:reserve:list")
    @GetMapping(value = "/another/check")
    public R<StudyRoomReserveAnotherCheckClientResp> reserveAnotherCheck(
        @RequestParam(value = "userReserveId") Long userReserveId) {
        return R.ok(reserveClient.reserveAnotherCheck(userReserveId).getData());
    }

    /**
     * 我的预约列表（一次性返回全部，无分页）
     *
     * @return 响应数据
     */
    @SaCheckPermission("business:study:reserve:list")
    @GetMapping(value = "/user/list")
    public R<StudyRoomUserReserveListClientResp> userReserveList() {
        return R.ok(reserveClient.userReserveList().getData());
    }

    /**
     * 取消预约前的提示
     *
     * @param userReserveId 用户预约 ID
     * @return 响应数据
     */
    @SaCheckPermission("business:study:reserve:list")
    @GetMapping(value = "/user/cancel/tip")
    public R<StudyRoomUserReserveCancelTipClientResp> userReserveCancelTip(
        @RequestParam(value = "userReserveId") Long userReserveId) {
        return R.ok(reserveClient.userReserveCancelTip(userReserveId).getData());
    }

    /**
     * 取消预约
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:reserve:delete")
    @PostMapping(value = "/user/cancel")
    public R<Integer> userReserveCancel(@RequestBody StudyRoomUserReserveCancelClientReq form) {
        return R.ok(reserveClient.userReserveCancel(form).getData());
    }
}
