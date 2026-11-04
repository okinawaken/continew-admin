package top.continew.admin.business.study.client.http.interceptor;

import com.dtflys.forest.http.ForestRequest;
import com.dtflys.forest.http.ForestResponse;
import com.dtflys.forest.interceptor.ForestInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomApiResult;
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
            throw new BusinessException(StudyRoomApiErrorCodeEnum.EMPTY_RESPONSE.getDescription());
        }
        if (resultData instanceof StudyRoomApiResult<?> result) {
            String code = result.getCode();
            if (!Objects.equals(code, StudyRoomApiConstants.ApiCode.SUCCESS_CODE)
                && !Objects.equals(code, StudyRoomApiConstants.ApiCode.BUSINESS_CODE)) {
                throw new BusinessException(result.getContent());
            }
        }
    }
}
