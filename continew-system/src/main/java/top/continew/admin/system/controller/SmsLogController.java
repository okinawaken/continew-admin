package top.continew.admin.system.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;
import top.continew.admin.common.base.controller.BaseController;
import top.continew.admin.system.model.query.SmsLogQuery;
import top.continew.admin.system.model.req.SmsLogReq;
import top.continew.admin.system.model.resp.SmsLogResp;
import top.continew.admin.system.service.SmsLogService;
import top.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.continew.starter.extension.crud.enums.Api;

/**
 * 短信日志管理 API
 *
 * @author bixiu
 * @author bixiu
 * @since 3.6.0
 */
@Tag(name = "短信日志管理 API")
@RestController
@CrudRequestMapping(value = "/system/sms/log",
    api = {Api.PAGE, Api.GET, Api.BATCH_DELETE, Api.EXPORT})
public class SmsLogController
    extends BaseController<SmsLogService, SmsLogResp, SmsLogResp, SmsLogQuery, SmsLogReq> {
}
