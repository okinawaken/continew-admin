package top.continew.admin.business.study.controller.client;

import cn.dev33.satoken.annotation.SaCheckPermission;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.continew.admin.business.study.client.http.v1.StudyRoomRepairClient;
import top.continew.admin.business.study.model.req.StudyRoomRepairSubmitClientReq;
import top.continew.admin.business.study.model.resp.StudyRoomRepairClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomRepairListClientResp;
import top.continew.starter.web.model.R;

/**
 * 自习室报修域接口
 *
 * @author bixiu
 * @since 4.2.0
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/business/study/repair")
public class StudyRoomRepairController {

    private final StudyRoomRepairClient repairClient;

    /**
     * 提交报修
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:repair:create")
    @PostMapping(value = "/submit")
    public R<Void> submitRepair(@RequestBody StudyRoomRepairSubmitClientReq form) {
        repairClient.submitRepair(form);
        return R.ok();
    }

    /**
     * 报修列表
     *
     * @return 响应数据
     */
    @SaCheckPermission("business:study:repair:list")
    @GetMapping(value = "/list")
    public R<StudyRoomRepairListClientResp> repairList() {
        return R.ok(repairClient.repairList().getData());
    }

    /**
     * 报修详情
     *
     * @param id ID
     * @return 响应数据
     */
    @SaCheckPermission("business:study:repair:list")
    @GetMapping(value = "/detail/{id}")
    public R<StudyRoomRepairClientResp> repairDetail(@PathVariable(value = "id") Long id) {
        return R.ok(repairClient.repairDetail(id).getData());
    }
}
