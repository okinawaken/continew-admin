package top.continew.admin.business.study.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.continew.admin.business.study.client.http.StudyRoomVenueClient;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomAreaSelectClientResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomDouYinVenueSelectClientResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomReserveIndexClientResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomVenueClientResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomVenueSelectClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomAreaSelectResp;
import top.continew.admin.business.study.model.resp.StudyRoomDouYinVenueSelectResp;
import top.continew.admin.business.study.model.resp.StudyRoomReserveIndexResp;
import top.continew.admin.business.study.model.resp.StudyRoomVenueResp;
import top.continew.admin.business.study.model.resp.StudyRoomVenueSelectResp;
import top.continew.starter.web.model.R;

/**
 * 自习室门店域接口
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/business/study/venue")
public class StudyRoomVenueController {

    private final StudyRoomVenueClient venueClient;

    /**
     * 按定位查询附近门店（签到首页、报修页用）
     *
     * @param currentLng 当前经度
     * @param currentLat 当前纬度
     * @return 响应数据
     */
    @SaCheckPermission("business:study:venue:list")
    @GetMapping(value = "/select")
    public R<StudyRoomVenueSelectResp> venueSelect(
        @RequestParam(value = "currentLng") Double currentLng,
        @RequestParam(value = "currentLat") Double currentLat) {
        StudyRoomVenueSelectClientResp dto =
            venueClient.venueSelect(currentLng, currentLat).getData();
        StudyRoomVenueSelectResp vo = BeanUtil.copyProperties(dto, StudyRoomVenueSelectResp.class);
        return R.ok(vo);
    }

    /**
     * 预约可选门店列表（无定位参数）
     *
     * @return 响应数据
     */
    @SaCheckPermission("business:study:venue:list")
    @GetMapping(value = "/reserve/select")
    public R<StudyRoomVenueSelectResp> reserveVenueSelect() {
        StudyRoomVenueSelectClientResp dto = venueClient.reserveVenueSelect().getData();
        StudyRoomVenueSelectResp vo = BeanUtil.copyProperties(dto, StudyRoomVenueSelectResp.class);
        return R.ok(vo);
    }

    /**
     * 门店详情
     *
     * @param venueId 门店 ID
     * @return 响应数据
     */
    @SaCheckPermission("business:study:venue:list")
    @GetMapping(value = "/reserve/detail")
    public R<StudyRoomVenueResp> reserveVenueDetail(
        @RequestParam(value = "venueId") Integer venueId) {
        StudyRoomVenueClientResp dto = venueClient.reserveVenueDetail(venueId).getData();
        StudyRoomVenueResp vo = BeanUtil.copyProperties(dto, StudyRoomVenueResp.class);
        return R.ok(vo);
    }

    /**
     * 预约首页（banner + 门店列表 + 区域筛选）
     *
     * @param currentLng 当前经度
     * @param currentLat 当前纬度
     * @param venueName 门店名称
     * @param channelBili 渠道标识
     * @param areaId 区域 ID
     * @return 响应数据
     */
    @SaCheckPermission("business:study:venue:list")
    @GetMapping(value = "/reserve/index")
    public R<StudyRoomReserveIndexResp> reserveIndex(
        @RequestParam(name = "currentLng", required = false) Double currentLng,
        @RequestParam(name = "currentLat", required = false) Double currentLat,
        @RequestParam(name = "venueName", required = false) String venueName,
        @RequestParam(name = "channelBili", required = false) Integer channelBili,
        @RequestParam(name = "areaId", required = false) Long areaId) {
        StudyRoomReserveIndexClientResp dto = venueClient
            .reserveIndex(currentLng, currentLat, venueName, channelBili, areaId).getData();
        StudyRoomReserveIndexResp vo =
            BeanUtil.copyProperties(dto, StudyRoomReserveIndexResp.class);
        return R.ok(vo);
    }

    /**
     * 区域列表（预约首页筛选）
     *
     * @return 响应数据
     */
    @SaCheckPermission("business:study:venue:list")
    @GetMapping(value = "/reserve/area/select")
    public R<StudyRoomAreaSelectResp> reserveAreaSelect() {
        StudyRoomAreaSelectClientResp dto = venueClient.reserveAreaSelect().getData();
        StudyRoomAreaSelectResp vo = BeanUtil.copyProperties(dto, StudyRoomAreaSelectResp.class);
        return R.ok(vo);
    }

    /**
     * 收银台门店列表（美团团购核销）
     *
     * @param currentLng 当前经度
     * @param currentLat 当前纬度
     * @return 响应数据
     */
    @SaCheckPermission("business:study:venue:list")
    @GetMapping(value = "/receipt/select")
    public R<StudyRoomVenueSelectResp> receiptVenueSelect(
        @RequestParam(value = "currentLng") Double currentLng,
        @RequestParam(value = "currentLat") Double currentLat) {
        StudyRoomVenueSelectClientResp dto =
            venueClient.receiptVenueSelect(currentLng, currentLat).getData();
        StudyRoomVenueSelectResp vo = BeanUtil.copyProperties(dto, StudyRoomVenueSelectResp.class);
        return R.ok(vo);
    }

    /**
     * 抖音门店列表（比普通门店多 poiId / poiName）
     *
     * @param currentLng 当前经度
     * @param currentLat 当前纬度
     * @return 响应数据
     */
    @SaCheckPermission("business:study:venue:list")
    @GetMapping(value = "/douyin/select")
    public R<StudyRoomDouYinVenueSelectResp> douYinVenueSelect(
        @RequestParam(value = "currentLng") Double currentLng,
        @RequestParam(value = "currentLat") Double currentLat) {
        StudyRoomDouYinVenueSelectClientResp dto =
            venueClient.douYinVenueSelect(currentLng, currentLat).getData();
        StudyRoomDouYinVenueSelectResp vo =
            BeanUtil.copyProperties(dto, StudyRoomDouYinVenueSelectResp.class);
        return R.ok(vo);
    }
}
