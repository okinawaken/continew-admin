package top.continew.admin.business.study.controller.client;

import cn.dev33.satoken.annotation.SaCheckPermission;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.continew.admin.business.study.client.http.StudyRoomLeaveClient;
import top.continew.admin.business.study.model.req.StudyRoomUserLeaveCancelClientReq;
import top.continew.admin.business.study.model.req.StudyRoomUserLeaveConfirmClientReq;
import top.continew.admin.business.study.model.resp.StudyRoomUserLeaveEnterClientResp;
import top.continew.starter.web.model.R;

/**
 * 自习室请假域接口
 *
 * @author bixiu
 * @since 4.2.0
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/business/study/leave")
public class StudyRoomLeaveController {

    private final StudyRoomLeaveClient leaveClient;

    /**
     * 进入请假页（校验可请假套餐）
     *
     * @param userPackageId 参数 userPackageId
     * @return 响应数据
     */
    @SaCheckPermission("business:study:leave:list")
    @GetMapping(value = "/enter")
    public R<StudyRoomUserLeaveEnterClientResp> userLeaveEnter(
        @RequestParam(value = "userPackageId") Long userPackageId) {
        return R.ok(leaveClient.userLeaveEnter(userPackageId).getData());
    }

    /**
     * 确认请假
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:leave:create")
    @PostMapping(value = "/confirm")
    public R<Integer> userLeaveConfirm(@RequestBody StudyRoomUserLeaveConfirmClientReq form) {
        return R.ok(leaveClient.userLeaveConfirm(form).getData());
    }

    /**
     * 取消请假
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:leave:delete")
    @PostMapping(value = "/cancel")
    public R<Integer> userLeaveCancel(@RequestBody StudyRoomUserLeaveCancelClientReq form) {
        return R.ok(leaveClient.userLeaveCancel(form).getData());
    }
}
