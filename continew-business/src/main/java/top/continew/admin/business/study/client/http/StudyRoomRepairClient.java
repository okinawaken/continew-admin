package top.continew.admin.business.study.client.http;

import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.JSONBody;
import com.dtflys.forest.annotation.Post;
import com.dtflys.forest.annotation.Var;
import top.continew.admin.business.study.client.http.model.req.StudyRoomRepairSubmitClientReq;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomClientResult;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomRepairClientResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomRepairListClientResp;

/**
 * 报修域：提交报修、报修记录
 *
 * @author Amadeus
 * @since 4.2.0
 */
public interface StudyRoomRepairClient extends StudyRoomBaseClient {

    /**
     * 提交报修（实测成功时 {@code data} 为 {@code null}）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/userRepair/submit")
    StudyRoomClientResult<Void> submitRepair(@JSONBody StudyRoomRepairSubmitClientReq form);

    /**
     * 报修列表
     *
     * @return 响应数据
     */
    @Get(url = "/mp/userRepair/list")
    StudyRoomClientResult<StudyRoomRepairListClientResp> repairList();

    /**
     * 报修详情
     *
     * @param id ID
     * @return 响应数据
     */
    @Get(url = "/mp/userRepair/detail/${id}")
    StudyRoomClientResult<StudyRoomRepairClientResp> repairDetail(@Var(value = "id") Long id);

}
