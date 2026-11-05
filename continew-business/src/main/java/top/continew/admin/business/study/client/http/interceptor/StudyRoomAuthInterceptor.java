package top.continew.admin.business.study.client.http.interceptor;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.dtflys.forest.http.ForestRequest;
import com.dtflys.forest.interceptor.ForestInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.continew.admin.business.study.constant.StudyRoomApiConstants;
import top.continew.admin.business.study.model.bo.StudyRoomUserConfigBO;
import top.continew.admin.business.study.model.resp.StudyRoomUserConfigResp;
import top.continew.admin.business.study.service.user.StudyRoomUserSettingService;
import top.continew.admin.common.context.UserContextHolder;

import java.util.Objects;

/**
 * 小程序鉴权拦截器
 *
 * @author bixiu
 * @since 4.2.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class StudyRoomAuthInterceptor implements ForestInterceptor {

    private final StudyRoomUserSettingService settingService;

    @Override
    public boolean beforeExecute(ForestRequest request) {
        request.addHeader(StudyRoomApiConstants.Header.HEADER_APPID,
            StudyRoomApiConstants.Gateway.APPID);

        String token = request.getHeaderValue(StudyRoomApiConstants.Header.HEADER_TOKEN);
        if (StrUtil.isEmpty(token)) {
            Long userId = this.resolveUserId(request);
            StudyRoomUserConfigResp configResp = settingService.get(userId);
            if (Objects.nonNull(configResp)) {
                StudyRoomUserConfigBO config = configResp.getConfig();
                if (Objects.nonNull(config)) {
                    request.addHeader(StudyRoomApiConstants.Header.HEADER_TOKEN, config.getToken());
                }
            }
        }
        return true;
    }

    private Long resolveUserId(ForestRequest<?> request) {
        Long requestUserId =
            Convert.toLong(request.getVariableValue(StudyRoomApiConstants.Variable.USER_ID));
        if (Objects.nonNull(requestUserId)) {
            return requestUserId;
        }
        return UserContextHolder.getUserId();
    }
}
