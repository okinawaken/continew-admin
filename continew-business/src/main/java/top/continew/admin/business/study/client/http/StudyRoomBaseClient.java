package top.continew.admin.business.study.client.http;

import com.dtflys.forest.annotation.BaseRequest;
import top.continew.admin.business.study.client.http.interceptor.StudyRoomAuthInterceptor;
import top.continew.admin.business.study.client.http.interceptor.StudyRoomResponseInterceptor;
import top.continew.admin.business.study.constant.StudyRoomApiConstants;

/**
 * 自习室小程序接口的公共请求配置
 *
 * @author Amadeus
 * @since 4.2.0
 */
@BaseRequest(baseURL = StudyRoomApiConstants.Gateway.BASE_URL, contentType = "application/json",
    interceptor = {StudyRoomAuthInterceptor.class, StudyRoomResponseInterceptor.class})
public interface StudyRoomBaseClient {
}
