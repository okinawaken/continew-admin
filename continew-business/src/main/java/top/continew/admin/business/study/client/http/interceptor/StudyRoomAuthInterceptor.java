/*
 * Copyright (c) 2022-present Charles7c Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package top.continew.admin.business.study.client.http.interceptor;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.dtflys.forest.http.ForestRequest;
import com.dtflys.forest.interceptor.ForestInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.continew.admin.business.study.constant.StudyRoomApiConstants;
import top.continew.admin.business.study.service.user.StudyRoomUserSettingService;
import top.continew.admin.common.context.UserContextHolder;

import java.util.Objects;

/**
 * 小程序鉴权拦截器
 *
 * @author Amadeus
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

        Long userId = this.resolveUserId(request);
        String token = settingService.getConfig(userId).getToken();
        if (StrUtil.isNotEmpty(token)) {
            request.addHeader(StudyRoomApiConstants.Header.HEADER_TOKEN, token);
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
