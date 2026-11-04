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
import top.continew.admin.business.study.client.http.StudyRoomMiscClient;
import top.continew.admin.business.study.client.http.model.req.StudyRoomCabinetLeaseCommitClientReq;
import top.continew.admin.business.study.client.http.model.req.StudyRoomCabinetWifiOpenClientReq;
import top.continew.admin.business.study.client.http.model.req.StudyRoomDouYinVerifyClientReq;
import top.continew.admin.business.study.client.http.model.req.StudyRoomReceiptConsumeClientReq;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomCabinetGridListClientResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomCabinetGridPriceClientResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomCabinetLeaseCommitClientResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomCabinetLeaseOrderListClientResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomCabinetLeaseOverduePayClientResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomCabinetUserGridListClientResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomCabinetVenueListClientResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomConsumeResultClientResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomWxPayParamClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomCabinetGridListResp;
import top.continew.admin.business.study.model.resp.StudyRoomCabinetGridPriceResp;
import top.continew.admin.business.study.model.resp.StudyRoomCabinetLeaseCommitResp;
import top.continew.admin.business.study.model.resp.StudyRoomCabinetLeaseOrderListResp;
import top.continew.admin.business.study.model.resp.StudyRoomCabinetLeaseOverduePayResp;
import top.continew.admin.business.study.model.resp.StudyRoomCabinetUserGridListResp;
import top.continew.admin.business.study.model.resp.StudyRoomCabinetVenueListResp;
import top.continew.admin.business.study.model.resp.StudyRoomConsumeResultResp;
import top.continew.admin.business.study.model.resp.StudyRoomWxPayParamResp;
import top.continew.starter.core.exception.BusinessException;
import top.continew.starter.web.model.R;

/**
 * 自习室其他域接口：储物柜、团购券核销
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/business/study/misc")
public class StudyRoomMiscController {

    private final StudyRoomMiscClient miscClient;

    /**
     * 储物柜租赁订单（无业务参数）
     *
     * @return 响应数据
     */
    @SaCheckPermission("business:study:misc:list")
    @PostMapping(value = "/cabinet/lease/order/list")
    public R<StudyRoomCabinetLeaseOrderListResp> cabinetLeaseOrderList() {
        StudyRoomCabinetLeaseOrderListClientResp dto = miscClient.cabinetLeaseOrderList().getData();
        StudyRoomCabinetLeaseOrderListResp vo =
            BeanUtil.copyProperties(dto, StudyRoomCabinetLeaseOrderListResp.class);
        return R.ok(vo);
    }

    /**
     * 储物柜租赁支付（参数走 query，leaseId 必须是整数）
     *
     * @param leaseId 租约 ID
     * @param userId 用户 ID
     * @return 响应数据
     */
    @SaCheckPermission("business:study:misc:create")
    @PostMapping(value = "/cabinet/lease/pay")
    public R<StudyRoomWxPayParamResp> cabinetLeasePay(
        @RequestParam(value = "leaseId") Long leaseId,
        @RequestParam(value = "userId") Long userId) {
        StudyRoomWxPayParamClientResp dto = miscClient.cabinetLeasePay(leaseId, userId).getData();
        StudyRoomWxPayParamResp vo = BeanUtil.copyProperties(dto, StudyRoomWxPayParamResp.class);
        return R.ok(vo);
    }

    /**
     * 储物柜下单，返回租约 ID
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:misc:create")
    @PostMapping(value = "/cabinet/lease/commit")
    public R<StudyRoomCabinetLeaseCommitResp> cabinetLeaseCommit(
        @RequestBody StudyRoomCabinetLeaseCommitClientReq form) {
        StudyRoomCabinetLeaseCommitClientResp dto = miscClient.cabinetLeaseCommit(form).getData();
        StudyRoomCabinetLeaseCommitResp vo =
            BeanUtil.copyProperties(dto, StudyRoomCabinetLeaseCommitResp.class);
        return R.ok(vo);
    }

    /**
     * 储物柜逾期支付（参数走 query）
     *
     * @param leaseId 租约 ID
     * @param userCode 用户编码
     * @return 响应数据
     */
    @SaCheckPermission("business:study:misc:create")
    @PostMapping(value = "/cabinet/lease/overdue/pay")
    public R<StudyRoomCabinetLeaseOverduePayResp> cabinetLeaseOverduePay(
        @RequestParam(value = "leaseId") Long leaseId,
        @RequestParam(value = "userCode") String userCode) {
        StudyRoomCabinetLeaseOverduePayClientResp dto =
            miscClient.cabinetLeaseOverduePay(leaseId, userCode).getData();
        StudyRoomCabinetLeaseOverduePayResp vo =
            BeanUtil.copyProperties(dto, StudyRoomCabinetLeaseOverduePayResp.class);
        return R.ok(vo);
    }

    /**
     * 储物柜自动退租（按最近一次租约）
     *
     * @param lastLeaseId 最近一次租约 ID
     * @return 响应数据
     */
    @SaCheckPermission("business:study:misc:create")
    @GetMapping(value = "/cabinet/lease/auto-checkout")
    public R<Void> cabinetLeaseAutoCheckout(@RequestParam(value = "lastLeaseId") Long lastLeaseId) {
        miscClient.cabinetLeaseAutoCheckout(lastLeaseId);
        return R.ok();
    }

    /**
     * 储物柜开锁（WiFi 开锁，按柜格）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:misc:create")
    @PostMapping(value = "/cabinet/wifi/open")
    public R<Void> cabinetWifiOpen(@RequestBody StudyRoomCabinetWifiOpenClientReq form) {
        miscClient.cabinetWifiOpen(form);
        return R.ok();
    }

    /**
     * 门店的储物柜列表
     *
     * @param venueId 门店 ID
     * @return 响应数据
     */
    @SaCheckPermission("business:study:misc:list")
    @GetMapping(value = "/cabinet/venue/list")
    public R<StudyRoomCabinetVenueListResp> cabinetVenueList(
        @RequestParam(value = "venueId") Integer venueId) {
        StudyRoomCabinetVenueListClientResp dto = miscClient.cabinetVenueList(venueId).getData();
        StudyRoomCabinetVenueListResp vo =
            BeanUtil.copyProperties(dto, StudyRoomCabinetVenueListResp.class);
        return R.ok(vo);
    }

    /**
     * 柜体的格口列表
     *
     * @param cabinetId 柜体 ID
     * @return 响应数据
     */
    @SaCheckPermission("business:study:misc:list")
    @GetMapping(value = "/cabinet/grid/list")
    public R<StudyRoomCabinetGridListResp> cabinetGridList(
        @RequestParam(value = "cabinetId") Integer cabinetId) {
        StudyRoomCabinetGridListClientResp dto = miscClient.cabinetGridList(cabinetId).getData();
        StudyRoomCabinetGridListResp vo =
            BeanUtil.copyProperties(dto, StudyRoomCabinetGridListResp.class);
        return R.ok(vo);
    }

    /**
     * 我当前已租的柜格列表
     *
     * @param venueId 门店 ID
     * @return 响应数据
     */
    @SaCheckPermission("business:study:misc:list")
    @GetMapping(value = "/cabinet/user/grid/list")
    public R<StudyRoomCabinetUserGridListResp> cabinetUserGridList(
        @RequestParam(value = "venueId") Integer venueId) {
        StudyRoomCabinetUserGridListClientResp dto =
            miscClient.cabinetUserGridList(venueId).getData();
        StudyRoomCabinetUserGridListResp vo =
            BeanUtil.copyProperties(dto, StudyRoomCabinetUserGridListResp.class);
        return R.ok(vo);
    }

    /**
     * 柜格定价
     *
     * @param gridId 格口 ID
     * @return 响应数据
     */
    @SaCheckPermission("business:study:misc:list")
    @GetMapping(value = "/cabinet/grid/price")
    public R<StudyRoomCabinetGridPriceResp> cabinetGridPrice(
        @RequestParam(value = "gridId") Integer gridId) {
        StudyRoomCabinetGridPriceClientResp dto = miscClient.cabinetGridPrice(gridId).getData();
        StudyRoomCabinetGridPriceResp vo =
            BeanUtil.copyProperties(dto, StudyRoomCabinetGridPriceResp.class);
        return R.ok(vo);
    }

    /**
     * 美团团购券核销
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:misc:create")
    @PostMapping(value = "/receipt/consume")
    public R<StudyRoomConsumeResultResp> receiptConsume(
        @RequestBody StudyRoomReceiptConsumeClientReq form) {
        StudyRoomConsumeResultClientResp dto = miscClient.receiptConsume(form).getData();
        StudyRoomConsumeResultResp vo =
            BeanUtil.copyProperties(dto, StudyRoomConsumeResultResp.class);
        return R.ok(vo);
    }

    /**
     * 抖音券核销
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:misc:create")
    @PostMapping(value = "/douyin/verify")
    public R<StudyRoomConsumeResultResp> douYinVerify(
        @RequestBody StudyRoomDouYinVerifyClientReq form) {
        StudyRoomConsumeResultClientResp dto = miscClient.douYinVerify(form).getData();
        StudyRoomConsumeResultResp vo =
            BeanUtil.copyProperties(dto, StudyRoomConsumeResultResp.class);
        return R.ok(vo);
    }

    /**
     * 图片上传（当前不支持）
     *
     * @return 响应数据
     */
    @SaCheckPermission("business:study:misc:create")
    @PostMapping(value = "/image/upload")
    public R<Void> uploadImage() {
        throw new BusinessException("暂不支持文件上传");
    }
}
