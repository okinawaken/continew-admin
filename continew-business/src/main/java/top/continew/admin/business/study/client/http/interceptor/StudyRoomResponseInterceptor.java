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

import cn.hutool.core.util.StrUtil;
import com.dtflys.forest.http.ForestRequest;
import com.dtflys.forest.http.ForestResponse;
import com.dtflys.forest.interceptor.ForestInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.continew.admin.business.study.client.model.StudyRoomApiResultDTO;
import top.continew.admin.business.study.constant.StudyRoomApiConstants;
import top.continew.admin.business.study.enums.StudyRoomApiErrorCodeEnum;
import top.continew.starter.core.exception.BusinessException;

import java.util.Objects;

/**
 * 自习室响应拦截器
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class StudyRoomResponseInterceptor implements ForestInterceptor {

    @Override
    public void afterExecute(ForestRequest request, ForestResponse response) {
        if (response.isError()) {
            throw new BusinessException("自习室接口调用失败(" + response.getStatusCode() + ")");
        }
        Object resultData = response.getResult();
        if (Objects.isNull(resultData)) {
            throw new BusinessException(StudyRoomApiErrorCodeEnum.EMPTY_RESPONSE.getMsg());
        }
        if (resultData instanceof StudyRoomApiResultDTO<?> result) {
            String code = result.getCode();
            if (!Objects.equals(code, StudyRoomApiConstants.ApiCode.SUCCESS_CODE)
                && !Objects.equals(code, StudyRoomApiConstants.ApiCode.BUSINESS_CODE)) {
                throw new BusinessException(this.resolveErrorMessage(result));
            }
        }
    }

    /**
     * 错误提示优先透传上游文案
     *
     * @param result 上游响应
     * @return 响应数据
     */
    private String resolveErrorMessage(StudyRoomApiResultDTO<?> result) {
        return StrUtil.isEmpty(result.getContent()) ? "自习室接口调用失败(" + result.getCode() + ")"
            : result.getContent();
    }
}
