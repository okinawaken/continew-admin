package top.continew.admin.business.study.client.http;

import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.Query;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomApiResult;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomConfigResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomNoticeResp;

/**
 * 配置域：全局配置、公告
 *
 * @author Amadeus
 * @since 4.2.0
 */
public interface StudyRoomConfigClient extends StudyRoomBaseClient {

    /**
     * 小程序全局配置（含可提前预约天数、保留时长等）
     *
     * @return 响应数据
     */
    @Get(url = "/mp/sys/config/data")
    StudyRoomApiResult<StudyRoomConfigResp> sysConfig();

    /**
     * 公告栏
     * <p>
     * 小程序传 {@code venueId=0} 表示不区分门店
     *
     * @param venueId 门店 ID
     * @return 响应数据
     */
    @Get(url = "/mp/notice/board/info")
    StudyRoomApiResult<StudyRoomNoticeResp> noticeBoard(
        @Query(value = "venueId") Integer venueId);

}
