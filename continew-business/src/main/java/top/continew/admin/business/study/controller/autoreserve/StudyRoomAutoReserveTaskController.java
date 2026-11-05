package top.continew.admin.business.study.controller.autoreserve;

import cn.dev33.satoken.annotation.SaCheckPermission;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.continew.admin.business.study.model.query.StudyRoomAutoReserveTaskQuery;
import top.continew.admin.business.study.model.req.StudyRoomAutoReserveTaskReq;
import top.continew.admin.business.study.model.resp.StudyRoomAutoReserveTaskInfoResp;
import top.continew.admin.business.study.service.autoreserve.StudyRoomAutoReserveTaskService;
import top.continew.admin.common.base.controller.BaseController;
import top.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.continew.starter.extension.crud.enums.Api;

/**
 * 自动预约任务 API
 *
 * @author bixiu
 * @since 4.2.0
 */
@Tag(name = "自动预约任务 API")
@RestController
@CrudRequestMapping(value = "/business/study/auto-reserve/task",
    api = {Api.PAGE, Api.GET, Api.CREATE, Api.UPDATE, Api.DELETE})
// @formatter:off
public class StudyRoomAutoReserveTaskController extends
    BaseController<StudyRoomAutoReserveTaskService, StudyRoomAutoReserveTaskInfoResp,
        StudyRoomAutoReserveTaskInfoResp, StudyRoomAutoReserveTaskQuery,
        StudyRoomAutoReserveTaskReq> {
    // @formatter:on

    @Operation(summary = "暂停任务", description = "暂停任务")
    @Parameter(name = "id", description = "ID", example = "1", in = ParameterIn.PATH)
    @SaCheckPermission("business:studyAuto-reserveTask:update")
    @PostMapping("/{id}/pause")
    public void pause(@PathVariable("id") Long id) {
        baseService.pauseTask(id, "用户手动暂停");
    }

    @Operation(summary = "恢复任务", description = "恢复任务")
    @Parameter(name = "id", description = "ID", example = "1", in = ParameterIn.PATH)
    @SaCheckPermission("business:studyAuto-reserveTask:update")
    @PostMapping("/{id}/resume")
    public void resume(@PathVariable("id") Long id) {
        baseService.resumeTask(id);
    }

    @Operation(summary = "立即执行一次", description = "立即执行一次")
    @Parameter(name = "id", description = "ID", example = "1", in = ParameterIn.PATH)
    @SaCheckPermission("business:studyAuto-reserveTask:execute")
    @PostMapping("/{id}/execute")
    public void execute(@PathVariable("id") Long id) {
        baseService.executeTask(id);
    }
}
