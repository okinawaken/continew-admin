package top.continew.admin.schedule.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import top.continew.admin.schedule.api.JobClient;
import top.continew.admin.schedule.constant.JobConstants;

/**
 * Feign 请求拦截器
 *
 * @author Charles7c
 * @since 3.6.0
 */
@Component
@RequiredArgsConstructor
public class FeignRequestInterceptor implements RequestInterceptor {

    private final JobClient jobClient;

    @Value("${snail-job.namespace}")
    private String namespace;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(JobConstants.NAMESPACE_ID_HEADER, namespace);
        requestTemplate.header(JobConstants.AUTH_TOKEN_HEADER, jobClient.getToken());
    }
}
