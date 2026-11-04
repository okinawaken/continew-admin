package top.continew.admin.business.study.client.http;

import com.dtflys.forest.annotation.DataFile;
import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.JSONBody;
import com.dtflys.forest.annotation.Post;
import com.dtflys.forest.annotation.Query;
import top.continew.admin.business.study.client.model.req.StudyRoomCabinetLeaseCommitReqDTO;
import top.continew.admin.business.study.client.model.req.StudyRoomCabinetWifiOpenReqDTO;
import top.continew.admin.business.study.client.model.req.StudyRoomDouYinVerifyReqDTO;
import top.continew.admin.business.study.client.model.req.StudyRoomReceiptConsumeReqDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomApiResultDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomCabinetGridListRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomCabinetGridPriceRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomCabinetLeaseCommitRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomCabinetLeaseOrderListRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomCabinetLeaseOverduePayRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomCabinetUserGridListRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomCabinetVenueListRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomConsumeResultRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomFileRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomWxPayParamRespDTO;

import java.io.File;
import java.util.List;

/**
 * 其他域：储物柜、团购券核销、文件上传
 *
 * @author Amadeus
 * @since 4.2.0
 */
public interface StudyRoomMiscClient extends StudyRoomBaseClient {

    /**
     * 储物柜租赁订单
     * <p>
     * 实测该接口不接收业务参数（传空对象即可），因此无请求体
     *
     * @return 响应数据
     */
    @Post(url = "/mp/cabinetGrip/v2/lease/order/list")
    StudyRoomApiResultDTO<StudyRoomCabinetLeaseOrderListRespDTO> cabinetLeaseOrderList();

    /**
     * 储物柜租赁支付（参数走 query，且 leaseId 必须是整数）
     *
     * @param leaseId 租约 ID
     * @param userId 用户 ID
     * @return 响应数据
     */
    @Post(url = "/mp/cabinetGrip/v2/lease/pay")
    StudyRoomApiResultDTO<StudyRoomWxPayParamRespDTO> cabinetLeasePay(
        @Query(value = "leaseId") Long leaseId, @Query(value = "userId") Long userId);

    /**
     * 美团团购券核销
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/receipt/consume")
    StudyRoomApiResultDTO<StudyRoomConsumeResultRespDTO> receiptConsume(
        @JSONBody StudyRoomReceiptConsumeReqDTO form);

    /**
     * 抖音券核销
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/douYin/certificate/prepareAndVerify")
    StudyRoomApiResultDTO<StudyRoomConsumeResultRespDTO> douYinVerify(
        @JSONBody StudyRoomDouYinVerifyReqDTO form);

    /**
     * 图片上传（报修图片等，multipart 字段名 {@code files}）
     * <p>
     * 注意：上游 OSS 的 AccessKey 已被禁用（2026-10-26 实测返回 {@code InvalidAccessKeyId}），该接口当前不可用
     *
     * @param file 参数 file
     * @return 响应数据
     */
    @Post(url = "/mp/image/upload", contentType = "multipart/form-data")
    StudyRoomApiResultDTO<List<StudyRoomFileRespDTO>> uploadImage(
        @DataFile(value = "files") File file);

    // ------------------------------------------------------------------
    // 储物柜（/mp/cabinetGrip/v2/**）：门店柜子 → 格口 → 定价 → 下单 → 支付 → 开锁 → 退租
    // ------------------------------------------------------------------

    /**
     * 门店的储物柜列表
     *
     * @param venueId 门店 ID
     * @return 响应数据
     */
    @Get(url = "/mp/cabinetGrip/v2/venue/cabinet/list")
    StudyRoomApiResultDTO<StudyRoomCabinetVenueListRespDTO> cabinetVenueList(
        @Query(value = "venueId") Integer venueId);

    /**
     * 柜体的格口列表（挑空柜格）
     *
     * @param cabinetId 柜体 ID
     * @return 响应数据
     */
    @Get(url = "/mp/cabinetGrip/v2/cabinet/grid/list")
    StudyRoomApiResultDTO<StudyRoomCabinetGridListRespDTO> cabinetGridList(
        @Query(value = "cabinetId") Integer cabinetId);

    /**
     * 我当前已租的柜格列表
     *
     * @param venueId 门店 ID
     * @return 响应数据
     */
    @Get(url = "/mp/cabinetGrip/v2/user/cabinet/grid/list")
    StudyRoomApiResultDTO<StudyRoomCabinetUserGridListRespDTO> cabinetUserGridList(
        @Query(value = "venueId") Integer venueId);

    /**
     * 柜格定价（下单前先查，拿最早可租日期与价格）
     *
     * @param gridId 格口 ID
     * @return 响应数据
     */
    @Get(url = "/mp/cabinetGrip/v2/grid/price")
    StudyRoomApiResultDTO<StudyRoomCabinetGridPriceRespDTO> cabinetGridPrice(
        @Query(value = "gridId") Integer gridId);

    /**
     * 储物柜下单，返回租约ID，随后用它调 {@link #cabinetLeasePay}
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/cabinetGrip/v2/lease/commit")
    StudyRoomApiResultDTO<StudyRoomCabinetLeaseCommitRespDTO> cabinetLeaseCommit(
        @JSONBody StudyRoomCabinetLeaseCommitReqDTO form);

    /**
     * 储物柜开锁（WiFi 开锁，按柜格）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/cabinetGrip/v2/wifi/open")
    StudyRoomApiResultDTO<Void> cabinetWifiOpen(@JSONBody StudyRoomCabinetWifiOpenReqDTO form);

    /**
     * 储物柜自动退租（按最近一次租约）
     *
     * @param lastLeaseId 最近一次租约 ID
     * @return 响应数据
     */
    @Get(url = "/mp/cabinetGrip/v2/lease/autoCheckout")
    StudyRoomApiResultDTO<Void> cabinetLeaseAutoCheckout(
        @Query(value = "lastLeaseId") Long lastLeaseId);

    /**
     * 储物柜逾期支付（参数走 query，返回微信支付参数）
     *
     * @param leaseId 租约 ID
     * @param userCode 用户编码
     * @return 响应数据
     */
    @Post(url = "/mp/cabinetGrip/v2/lease/overdue/pay")
    StudyRoomApiResultDTO<StudyRoomCabinetLeaseOverduePayRespDTO> cabinetLeaseOverduePay(
        @Query(value = "leaseId") Long leaseId,
        @Query(value = "userCode") String userCode);

}
