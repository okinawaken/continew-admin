package top.continew.admin.business.study.controller.autoreserve;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;
import top.continew.admin.business.study.model.query.StudyRoomAutoReserveTaskRecordQuery;
import top.continew.admin.business.study.model.req.StudyRoomAutoReserveTaskRecordReq;
import top.continew.admin.business.study.model.resp.StudyRoomAutoReserveTaskRecordInfoResp;
import top.continew.admin.business.study.service.autoreserve.StudyRoomAutoReserveTaskRecordService;
import top.continew.admin.common.base.controller.BaseController;
import top.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.continew.starter.extension.crud.enums.Api;

/**
 * 自动预约执行记录 API
 *
 * @author bixiu
 * @since 4.2.0
 */
@Tag(name = "自动预约执行记录 API")
@RestController
@CrudRequestMapping(value = "/business/study/auto-reserve/task/record", api = {Api.PAGE})
// @formatter:off
public class StudyRoomAutoReserveTaskRecordController extends
    BaseController<StudyRoomAutoReserveTaskRecordService, StudyRoomAutoReserveTaskRecordInfoResp,
        StudyRoomAutoReserveTaskRecordInfoResp, StudyRoomAutoReserveTaskRecordQuery,
        StudyRoomAutoReserveTaskRecordReq> {
    // @formatter:on

}
