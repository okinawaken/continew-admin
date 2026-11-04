package top.continew.admin.business.study.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.continew.admin.business.study.client.http.StudyRoomGuardClient;
import top.continew.admin.business.study.client.model.req.StudyRoomGuardOpenReqDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomGuardListRespDTO;
import top.continew.admin.business.study.model.resp.StudyRoomGuardListVO;
import top.continew.starter.web.model.R;

import jakarta.validation.Valid;

/**
 * 自习室门禁域接口：门禁区域列表、开门
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/business/study/guard")
public class StudyRoomGuardController {

    private final StudyRoomGuardClient guardClient;

    /**
     * 门店门禁区域列表
     *
     * @param venueId 门店 ID
     * @return 响应数据
     */
    @SaCheckPermission("business:study:guard:list")
    @GetMapping(value = "/select")
    public R<StudyRoomGuardListVO> guardSelect(@RequestParam(value = "venueId") Integer venueId) {
        StudyRoomGuardListRespDTO dto = guardClient.guardSelect(venueId).getData();
        StudyRoomGuardListVO vo = BeanUtil.copyProperties(dto, StudyRoomGuardListVO.class);
        return R.ok(vo);
    }

    /**
     * 门禁开门（服务端校验/记录）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:guard:create")
    @PostMapping(value = "/open")
    public R<Void> guardOpen(@Valid @RequestBody StudyRoomGuardOpenReqDTO form) {
        guardClient.guardOpen(form);
        return R.ok();
    }

}
