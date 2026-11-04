package top.continew.admin.business.study.controller.autoreserve;

import cn.dev33.satoken.annotation.SaCheckPermission;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.continew.admin.business.study.model.resp.StudyRoomAutoReserveTaskRecordVO;
import top.continew.admin.business.study.service.autoreserve.StudyRoomAutoReserveTaskRecordService;
import top.continew.starter.extension.crud.model.query.PageQuery;
import top.continew.starter.extension.crud.model.resp.PageResp;

/**
 * 自动预约执行记录
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/business/study/auto-reserve/task/record")
public class StudyRoomAutoReserveTaskRecordController {

    private final StudyRoomAutoReserveTaskRecordService recordService;

    /**
     * 执行历史分页
     *
     * @param taskId 任务 ID
     * @param pageQuery 分页查询条件
     * @return 响应数据
     */
    @SaCheckPermission("business:study:autoReserveRecord:list")
    @GetMapping(value = "/{taskId}")
    public PageResp<StudyRoomAutoReserveTaskRecordVO> record(
        @PathVariable(value = "taskId") Long taskId,
        PageQuery pageQuery) {
        return PageResp.build(recordService.pageByTaskId(taskId, pageQuery),
            StudyRoomAutoReserveTaskRecordVO.class);
    }
}
