package top.continew.admin.business.study.controller.client;

import cn.dev33.satoken.annotation.SaCheckPermission;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.continew.admin.business.study.client.http.StudyRoomConfigClient;
import top.continew.admin.business.study.model.resp.StudyRoomConfigClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomNoticeClientResp;
import top.continew.starter.web.model.R;

/**
 * 自习室配置域接口
 *
 * @author bixiu
 * @since 4.2.0
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/business/study/config")
public class StudyRoomConfigController {

    private final StudyRoomConfigClient configClient;

    /**
     * 小程序全局配置（含可提前预约天数、保留时长等）
     *
     * @return 响应数据
     */
    @SaCheckPermission("business:study:config:list")
    @GetMapping(value = "/sys")
    public R<StudyRoomConfigClientResp> sysConfig() {
        return R.ok(configClient.sysConfig().getData());
    }

    /**
     * 公告栏
     *
     * @param venueId 门店 ID
     * @return 响应数据
     */
    @SaCheckPermission("business:study:config:list")
    @GetMapping(value = "/notice")
    public R<StudyRoomNoticeClientResp> noticeBoard(
        @RequestParam(value = "venueId") Integer venueId) {
        return R.ok(configClient.noticeBoard(venueId).getData());
    }
}
