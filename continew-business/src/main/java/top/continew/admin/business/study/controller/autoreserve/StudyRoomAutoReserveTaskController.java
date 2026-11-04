package top.continew.admin.business.study.controller.autoreserve;

import cn.dev33.satoken.annotation.SaCheckPermission;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.continew.admin.business.study.model.req.StudyRoomAutoReserveTaskReq;
import top.continew.admin.business.study.model.resp.StudyRoomAutoReserveTaskInfoResp;
import top.continew.admin.business.study.service.autoreserve.StudyRoomAutoReserveTaskService;
import top.continew.admin.common.context.UserContextHolder;
import top.continew.starter.extension.crud.model.query.PageQuery;
import top.continew.starter.extension.crud.model.resp.PageResp;
import top.continew.starter.web.model.R;

import jakarta.validation.Valid;

/**
 * 自动预约任务
 *
 * @author bixiu
 * @since 4.2.0
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/business/study/auto-reserve/task")
public class StudyRoomAutoReserveTaskController {

    private final StudyRoomAutoReserveTaskService taskService;

    /**
     * 任务分页列表
     *
     * @param pageQuery 分页查询条件
     * @return 列表数据
     */
    @SaCheckPermission("business:study:autoReserveTask:list")
    @GetMapping(value = "/list")
    public PageResp<StudyRoomAutoReserveTaskInfoResp> list(PageQuery pageQuery) {
        return PageResp.build(taskService.pageList(UserContextHolder.getUserId(), pageQuery));
    }

    /**
     * 任务详情
     *
     * @param id ID
     * @return 详情数据
     */
    @SaCheckPermission("business:study:autoReserveTask:get")
    @GetMapping(value = "/{id}")
    public R<StudyRoomAutoReserveTaskInfoResp> detail(@PathVariable(value = "id") Long id) {
        return R.ok(taskService.getTask(id, UserContextHolder.getUserId()));
    }

    /**
     * 创建任务
     *
     * @param dto 请求数据
     * @return 响应数据
     */
    @SaCheckPermission("business:study:autoReserveTask:create")
    @PostMapping
    public R<Long> create(@Valid @RequestBody StudyRoomAutoReserveTaskReq dto) {
        return R.ok(taskService.createTask(UserContextHolder.getUserId(), dto));
    }

    /**
     * 修改任务
     *
     * @param id ID
     * @param dto 请求数据
     * @return 响应数据
     */
    @SaCheckPermission("business:study:autoReserveTask:update")
    @PutMapping(value = "/{id}")
    public R<Void> update(@PathVariable(value = "id") Long id,
        @Valid @RequestBody StudyRoomAutoReserveTaskReq dto) {
        taskService.updateTask(id, UserContextHolder.getUserId(), dto);
        return R.ok();
    }

    /**
     * 删除任务，执行历史一并删除
     *
     * @param id ID
     * @return 响应数据
     */
    @SaCheckPermission("business:study:autoReserveTask:delete")
    @DeleteMapping(value = "/{id}")
    public R<Void> remove(@PathVariable(value = "id") Long id) {
        taskService.deleteTask(id, UserContextHolder.getUserId());
        return R.ok();
    }

    /**
     * 暂停任务
     *
     * @param id ID
     * @return 响应数据
     */
    @SaCheckPermission("business:study:autoReserveTask:update")
    @PostMapping(value = "/{id}/pause")
    public R<Void> pause(@PathVariable(value = "id") Long id) {
        taskService.pauseTask(id, UserContextHolder.getUserId(), "用户手动暂停");
        return R.ok();
    }

    /**
     * 恢复任务
     *
     * @param id ID
     * @return 响应数据
     */
    @SaCheckPermission("business:study:autoReserveTask:update")
    @PostMapping(value = "/{id}/resume")
    public R<Void> resume(@PathVariable(value = "id") Long id) {
        taskService.resumeTask(id, UserContextHolder.getUserId());
        return R.ok();
    }

    /**
     * 立即执行一次
     *
     * @param id ID
     * @return 响应数据
     */
    @SaCheckPermission("business:study:autoReserveTask:execute")
    @PostMapping(value = "/{id}/execute")
    public R<Void> execute(@PathVariable(value = "id") Long id) {
        taskService.executeTask(id, UserContextHolder.getUserId());
        return R.ok();
    }
}
