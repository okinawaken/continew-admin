package top.continew.admin.business.study.client.http;

import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.JSONBody;
import com.dtflys.forest.annotation.Post;
import com.dtflys.forest.annotation.Query;
import top.continew.admin.business.study.client.http.model.req.StudyRoomGuardOpenClientReq;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomClientResult;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomGuardListClientResp;

/**
 * 门禁域：门禁区域列表、开门
 *
 * @author Amadeus
 * @since 4.2.0
 */
public interface StudyRoomGuardClient extends StudyRoomBaseClient {

    /**
     * 门店门禁区域列表
     *
     * @param venueId 门店 ID
     * @return 响应数据
     */
    @Get(url = "/mp/guard/select")
    StudyRoomClientResult<StudyRoomGuardListClientResp> guardSelect(
        @Query(value = "venueId") Integer venueId);

    /**
     * 门禁开门（服务端校验/记录；物理开锁需客户端 BLE）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/guard/open")
    StudyRoomClientResult<Void> guardOpen(@JSONBody StudyRoomGuardOpenClientReq form);

}
