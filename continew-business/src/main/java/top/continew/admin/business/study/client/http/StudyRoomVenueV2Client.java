package top.continew.admin.business.study.client.http;

import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.Query;
import com.dtflys.forest.annotation.Var;
import top.continew.admin.business.study.constant.StudyRoomApiConstants;
import top.continew.admin.business.study.model.resp.StudyRoomAreaSelectClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomClientResult;
import top.continew.admin.business.study.model.resp.StudyRoomDouYinVenueSelectClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomReserveIndexClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomVenueClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomVenueSelectClientResp;

/**
 * 门店域（显式用户上下文）：门店列表、门店详情、区域
 *
 * @author bixiu
 * @since 4.2.0
 */
public interface StudyRoomVenueV2Client extends StudyRoomBaseClient {

    /**
     * 按定位查询附近门店（签到首页、报修页用）
     *
     * @param currentLng 当前经度
     * @param currentLat 当前纬度
     * @param userId 用户 ID
     * @return 响应数据
     */
    @Get(url = "/mp/venue/select")
    StudyRoomClientResult<StudyRoomVenueSelectClientResp> venueSelect(
        @Query(value = "currentLng") Double currentLng,
        @Query(value = "currentLat") Double currentLat,
        @Var(value = StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 预约可选门店列表（无定位参数）
     *
     * @param userId 用户 ID
     * @return 响应数据
     */
    @Get(url = "/mp/reserve/venue/select")
    StudyRoomClientResult<StudyRoomVenueSelectClientResp> reserveVenueSelect(
        @Var(value = StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 门店详情
     *
     * @param venueId 门店 ID
     * @param userId 用户 ID
     * @return 响应数据
     */
    @Get(url = "/mp/reserve/venue/detail")
    StudyRoomClientResult<StudyRoomVenueClientResp> reserveVenueDetail(
        @Query(value = "venueId") Integer venueId,
        @Var(value = StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 预约首页（banner + 门店列表 + 区域筛选）
     *
     * @param currentLng 当前经度
     * @param currentLat 当前纬度
     * @param venueName 门店名称
     * @param channelBili 渠道标识
     * @param areaId 区域 ID
     * @param userId 用户 ID
     * @return 响应数据
     */
    @Get(url = "/mp/reserve/index")
    StudyRoomClientResult<StudyRoomReserveIndexClientResp> reserveIndex(
        @Query(value = "currentLng") Double currentLng,
        @Query(value = "currentLat") Double currentLat,
        @Query(value = "venueName") String venueName,
        @Query(value = "channelBili") Integer channelBili, @Query(value = "areaId") Long areaId,
        @Var(value = StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 区域列表（预约首页筛选）
     *
     * @param userId 用户 ID
     * @return 响应数据
     */
    @Get(url = "/mp/reserve/area/select")
    StudyRoomClientResult<StudyRoomAreaSelectClientResp> reserveAreaSelect(
        @Var(value = StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 收银台门店列表（美团团购核销）
     *
     * @param currentLng 当前经度
     * @param currentLat 当前纬度
     * @param userId 用户 ID
     * @return 响应数据
     */
    @Get(url = "/mp/receipt/venue/select")
    StudyRoomClientResult<StudyRoomVenueSelectClientResp> receiptVenueSelect(
        @Query(value = "currentLng") Double currentLng,
        @Query(value = "currentLat") Double currentLat,
        @Var(value = StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 抖音门店列表（比普通门店多 poiId / poiName）
     *
     * @param currentLng 当前经度
     * @param currentLat 当前纬度
     * @param userId 用户 ID
     * @return 响应数据
     */
    @Get(url = "/mp/douYin/venue/select")
    StudyRoomClientResult<StudyRoomDouYinVenueSelectClientResp> douYinVenueSelect(
        @Query(value = "currentLng") Double currentLng,
        @Query(value = "currentLat") Double currentLat,
        @Var(value = StudyRoomApiConstants.Variable.USER_ID) Long userId);

}
