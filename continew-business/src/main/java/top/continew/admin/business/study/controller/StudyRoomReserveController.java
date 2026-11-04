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
import top.continew.admin.business.study.client.http.StudyRoomReserveClient;
import top.continew.admin.business.study.client.http.model.req.StudyRoomReserveConfirmClientReq;
import top.continew.admin.business.study.client.http.model.req.StudyRoomReserveConfirmV1ClientReq;
import top.continew.admin.business.study.client.http.model.req.StudyRoomSeatQueryClientReq;
import top.continew.admin.business.study.client.http.model.req.StudyRoomSeatQueryV1ClientReq;
import top.continew.admin.business.study.client.http.model.req.StudyRoomUserReserveCancelClientReq;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomReserveAnotherCheckClientResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomReserveCheckClientResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomReserveConfirmClientResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomReserveDateClientResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomSeatMapClientResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomUserReserveCancelTipClientResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomUserReserveListClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomReserveAnotherCheckResp;
import top.continew.admin.business.study.model.resp.StudyRoomReserveCheckResp;
import top.continew.admin.business.study.model.resp.StudyRoomReserveConfirmResp;
import top.continew.admin.business.study.model.resp.StudyRoomReserveDateResp;
import top.continew.admin.business.study.model.resp.StudyRoomSeatMapResp;
import top.continew.admin.business.study.model.resp.StudyRoomUserReserveCancelTipResp;
import top.continew.admin.business.study.model.resp.StudyRoomUserReserveListResp;
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
    public R<StudyRoomReserveCheckResp> reserveCheck() {
        StudyRoomReserveCheckClientResp dto = reserveClient.reserveCheck().getData();
        StudyRoomReserveCheckResp vo =
            BeanUtil.copyProperties(dto, StudyRoomReserveCheckResp.class);
        return R.ok(vo);
    }

    /**
     * 可预约日期列表
     *
     * @param venueId 门店 ID
     * @return 响应数据
     */
    @SaCheckPermission("business:study:reserve:list")
    @GetMapping(value = "/v2/date/select")
    public R<StudyRoomReserveDateResp> reserveDateList(
        @RequestParam(value = "venueId") Integer venueId) {
        StudyRoomReserveDateClientResp dto = reserveClient.reserveDateList(venueId).getData();
        StudyRoomReserveDateResp vo = BeanUtil.copyProperties(dto, StudyRoomReserveDateResp.class);
        return R.ok(vo);
    }

    /**
     * 座位图（v2，推荐）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:reserve:list")
    @PostMapping(value = "/v2/seat/list")
    public R<StudyRoomSeatMapResp> reserveV2SeatList(
        @RequestBody StudyRoomSeatQueryClientReq form) {
        StudyRoomSeatMapClientResp dto = reserveClient.reserveV2SeatList(form).getData();
        StudyRoomSeatMapResp vo = BeanUtil.copyProperties(dto, StudyRoomSeatMapResp.class);
        return R.ok(vo);
    }

    /**
     * 提交预约（v2，推荐）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:reserve:create")
    @PostMapping(value = "/v2/seat/confirm")
    public R<StudyRoomReserveConfirmResp> reserveV2SeatConfirm(
        @RequestBody StudyRoomReserveConfirmClientReq form) {
        StudyRoomReserveConfirmClientResp dto = reserveClient.reserveV2SeatConfirm(form).getData();
        StudyRoomReserveConfirmResp vo =
            BeanUtil.copyProperties(dto, StudyRoomReserveConfirmResp.class);
        return R.ok(vo);
    }

    /**
     * 座位图（v1，旧版，时间格式 {@code yyyy-MM-dd HH:mm}）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:reserve:list")
    @PostMapping(value = "/seat/list")
    public R<StudyRoomSeatMapResp> reserveSeatList(
        @RequestBody StudyRoomSeatQueryV1ClientReq form) {
        StudyRoomSeatMapClientResp dto = reserveClient.reserveSeatList(form).getData();
        StudyRoomSeatMapResp vo = BeanUtil.copyProperties(dto, StudyRoomSeatMapResp.class);
        return R.ok(vo);
    }

    /**
     * 提交预约（v1，旧版，时间格式 {@code yyyy-MM-dd HH:mm}）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:reserve:create")
    @PostMapping(value = "/seat/confirm")
    public R<StudyRoomReserveConfirmResp> reserveSeatConfirm(
        @RequestBody StudyRoomReserveConfirmV1ClientReq form) {
        StudyRoomReserveConfirmClientResp dto = reserveClient.reserveSeatConfirm(form).getData();
        StudyRoomReserveConfirmResp vo =
            BeanUtil.copyProperties(dto, StudyRoomReserveConfirmResp.class);
        return R.ok(vo);
    }

    /**
     * 是否还能再预约一个座位
     *
     * @param userReserveId 用户预约 ID
     * @return 响应数据
     */
    @SaCheckPermission("business:study:reserve:list")
    @GetMapping(value = "/another/check")
    public R<StudyRoomReserveAnotherCheckResp> reserveAnotherCheck(
        @RequestParam(value = "userReserveId") Long userReserveId) {
        StudyRoomReserveAnotherCheckClientResp dto =
            reserveClient.reserveAnotherCheck(userReserveId).getData();
        StudyRoomReserveAnotherCheckResp vo =
            BeanUtil.copyProperties(dto, StudyRoomReserveAnotherCheckResp.class);
        return R.ok(vo);
    }

    /**
     * 我的预约列表（一次性返回全部，无分页）
     *
     * @return 响应数据
     */
    @SaCheckPermission("business:study:reserve:list")
    @GetMapping(value = "/user/list")
    public R<StudyRoomUserReserveListResp> userReserveList() {
        StudyRoomUserReserveListClientResp dto = reserveClient.userReserveList().getData();
        StudyRoomUserReserveListResp vo =
            BeanUtil.copyProperties(dto, StudyRoomUserReserveListResp.class);
        return R.ok(vo);
    }

    /**
     * 取消预约前的提示
     *
     * @param userReserveId 用户预约 ID
     * @return 响应数据
     */
    @SaCheckPermission("business:study:reserve:list")
    @GetMapping(value = "/user/cancel/tip")
    public R<StudyRoomUserReserveCancelTipResp> userReserveCancelTip(
        @RequestParam(value = "userReserveId") Long userReserveId) {
        StudyRoomUserReserveCancelTipClientResp dto =
            reserveClient.userReserveCancelTip(userReserveId).getData();
        StudyRoomUserReserveCancelTipResp vo =
            BeanUtil.copyProperties(dto, StudyRoomUserReserveCancelTipResp.class);
        return R.ok(vo);
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
        Integer data = reserveClient.userReserveCancel(form).getData();
        return R.ok(data);
    }
}
