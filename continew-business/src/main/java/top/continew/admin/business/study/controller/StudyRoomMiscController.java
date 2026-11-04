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
import top.continew.admin.business.study.client.http.model.req.StudyRoomCabinetLeaseCommitReq;
import top.continew.admin.business.study.client.http.model.req.StudyRoomCabinetWifiOpenReq;
import top.continew.admin.business.study.client.http.model.req.StudyRoomDouYinVerifyReq;
import top.continew.admin.business.study.client.http.model.req.StudyRoomReceiptConsumeReq;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomCabinetGridListResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomCabinetGridPriceResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomCabinetLeaseCommitResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomCabinetLeaseOrderListResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomCabinetLeaseOverduePayResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomCabinetUserGridListResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomCabinetVenueListResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomConsumeResultResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomWxPayParamResp;
import top.continew.admin.business.study.model.resp.StudyRoomCabinetGridListVO;
import top.continew.admin.business.study.model.resp.StudyRoomCabinetGridPriceVO;
import top.continew.admin.business.study.model.resp.StudyRoomCabinetLeaseCommitVO;
import top.continew.admin.business.study.model.resp.StudyRoomCabinetLeaseOrderListVO;
import top.continew.admin.business.study.model.resp.StudyRoomCabinetLeaseOverduePayVO;
import top.continew.admin.business.study.model.resp.StudyRoomCabinetUserGridListVO;
import top.continew.admin.business.study.model.resp.StudyRoomCabinetVenueListVO;
import top.continew.admin.business.study.model.resp.StudyRoomConsumeResultVO;
import top.continew.admin.business.study.model.resp.StudyRoomWxPayParamVO;
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
    public R<StudyRoomCabinetLeaseOrderListVO> cabinetLeaseOrderList() {
        StudyRoomCabinetLeaseOrderListResp dto = miscClient.cabinetLeaseOrderList().getData();
        StudyRoomCabinetLeaseOrderListVO vo =
            BeanUtil.copyProperties(dto, StudyRoomCabinetLeaseOrderListVO.class);
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
    public R<StudyRoomWxPayParamVO> cabinetLeasePay(
        @RequestParam(value = "leaseId") Long leaseId,
        @RequestParam(value = "userId") Long userId) {
        StudyRoomWxPayParamResp dto = miscClient.cabinetLeasePay(leaseId, userId).getData();
        StudyRoomWxPayParamVO vo = BeanUtil.copyProperties(dto, StudyRoomWxPayParamVO.class);
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
    public R<StudyRoomCabinetLeaseCommitVO> cabinetLeaseCommit(
        @RequestBody StudyRoomCabinetLeaseCommitReq form) {
        StudyRoomCabinetLeaseCommitResp dto = miscClient.cabinetLeaseCommit(form).getData();
        StudyRoomCabinetLeaseCommitVO vo =
            BeanUtil.copyProperties(dto, StudyRoomCabinetLeaseCommitVO.class);
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
    public R<StudyRoomCabinetLeaseOverduePayVO> cabinetLeaseOverduePay(
        @RequestParam(value = "leaseId") Long leaseId,
        @RequestParam(value = "userCode") String userCode) {
        StudyRoomCabinetLeaseOverduePayResp dto =
            miscClient.cabinetLeaseOverduePay(leaseId, userCode).getData();
        StudyRoomCabinetLeaseOverduePayVO vo =
            BeanUtil.copyProperties(dto, StudyRoomCabinetLeaseOverduePayVO.class);
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
    public R<Void> cabinetWifiOpen(@RequestBody StudyRoomCabinetWifiOpenReq form) {
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
    public R<StudyRoomCabinetVenueListVO> cabinetVenueList(
        @RequestParam(value = "venueId") Integer venueId) {
        StudyRoomCabinetVenueListResp dto = miscClient.cabinetVenueList(venueId).getData();
        StudyRoomCabinetVenueListVO vo =
            BeanUtil.copyProperties(dto, StudyRoomCabinetVenueListVO.class);
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
    public R<StudyRoomCabinetGridListVO> cabinetGridList(
        @RequestParam(value = "cabinetId") Integer cabinetId) {
        StudyRoomCabinetGridListResp dto = miscClient.cabinetGridList(cabinetId).getData();
        StudyRoomCabinetGridListVO vo =
            BeanUtil.copyProperties(dto, StudyRoomCabinetGridListVO.class);
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
    public R<StudyRoomCabinetUserGridListVO> cabinetUserGridList(
        @RequestParam(value = "venueId") Integer venueId) {
        StudyRoomCabinetUserGridListResp dto = miscClient.cabinetUserGridList(venueId).getData();
        StudyRoomCabinetUserGridListVO vo =
            BeanUtil.copyProperties(dto, StudyRoomCabinetUserGridListVO.class);
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
    public R<StudyRoomCabinetGridPriceVO> cabinetGridPrice(
        @RequestParam(value = "gridId") Integer gridId) {
        StudyRoomCabinetGridPriceResp dto = miscClient.cabinetGridPrice(gridId).getData();
        StudyRoomCabinetGridPriceVO vo =
            BeanUtil.copyProperties(dto, StudyRoomCabinetGridPriceVO.class);
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
    public R<StudyRoomConsumeResultVO> receiptConsume(
        @RequestBody StudyRoomReceiptConsumeReq form) {
        StudyRoomConsumeResultResp dto = miscClient.receiptConsume(form).getData();
        StudyRoomConsumeResultVO vo = BeanUtil.copyProperties(dto, StudyRoomConsumeResultVO.class);
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
    public R<StudyRoomConsumeResultVO> douYinVerify(@RequestBody StudyRoomDouYinVerifyReq form) {
        StudyRoomConsumeResultResp dto = miscClient.douYinVerify(form).getData();
        StudyRoomConsumeResultVO vo = BeanUtil.copyProperties(dto, StudyRoomConsumeResultVO.class);
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
