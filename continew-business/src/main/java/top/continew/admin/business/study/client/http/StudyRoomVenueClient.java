package top.continew.admin.business.study.client.http;

import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.Query;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomApiResult;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomAreaSelectResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomDouYinVenueSelectResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomReserveIndexResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomVenueResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomVenueSelectResp;

/**
 * 门店域：门店列表、门店详情、区域
 *
 * @author Amadeus
 * @since 4.2.0
 */
public interface StudyRoomVenueClient extends StudyRoomBaseClient {

    /**
     * 按定位查询附近门店（签到首页、报修页用）
     *
     * @param currentLng 当前经度
     * @param currentLat 当前纬度
     * @return 响应数据
     */
    @Get(url = "/mp/venue/select")
    StudyRoomApiResult<StudyRoomVenueSelectResp> venueSelect(
        @Query(value = "currentLng") Double currentLng,
        @Query(value = "currentLat") Double currentLat);

    /**
     * 预约可选门店列表（无定位参数）
     *
     * @return 响应数据
     */
    @Get(url = "/mp/reserve/venue/select")
    StudyRoomApiResult<StudyRoomVenueSelectResp> reserveVenueSelect();

    /**
     * 门店详情
     *
     * @param venueId 门店 ID
     * @return 响应数据
     */
    @Get(url = "/mp/reserve/venue/detail")
    StudyRoomApiResult<StudyRoomVenueResp> reserveVenueDetail(
        @Query(value = "venueId") Integer venueId);

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
    @Get(url = "/mp/reserve/index")
    StudyRoomApiResult<StudyRoomReserveIndexResp> reserveIndex(
        @Query(value = "currentLng") Double currentLng,
        @Query(value = "currentLat") Double currentLat,
        @Query(value = "venueName") String venueName,
        @Query(value = "channelBili") Integer channelBili, @Query(value = "areaId") Long areaId);

    /**
     * 区域列表（预约首页筛选）
     *
     * @return 响应数据
     */
    @Get(url = "/mp/reserve/area/select")
    StudyRoomApiResult<StudyRoomAreaSelectResp> reserveAreaSelect();

    /**
     * 收银台门店列表（美团团购核销）
     *
     * @param currentLng 当前经度
     * @param currentLat 当前纬度
     * @return 响应数据
     */
    @Get(url = "/mp/receipt/venue/select")
    StudyRoomApiResult<StudyRoomVenueSelectResp> receiptVenueSelect(
        @Query(value = "currentLng") Double currentLng,
        @Query(value = "currentLat") Double currentLat);

    /**
     * 抖音门店列表（比普通门店多 poiId / poiName）
     *
     * @param currentLng 当前经度
     * @param currentLat 当前纬度
     * @return 响应数据
     */
    @Get(url = "/mp/douYin/venue/select")
    StudyRoomApiResult<StudyRoomDouYinVenueSelectResp> douYinVenueSelect(
        @Query(value = "currentLng") Double currentLng,
        @Query(value = "currentLat") Double currentLat);

}
