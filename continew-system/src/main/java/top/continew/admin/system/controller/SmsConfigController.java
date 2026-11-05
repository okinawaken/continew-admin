package top.continew.admin.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import top.continew.admin.common.base.controller.BaseController;
import top.continew.admin.system.model.query.SmsConfigQuery;
import top.continew.admin.system.model.req.SmsConfigReq;
import top.continew.admin.system.model.resp.SmsConfigResp;
import top.continew.admin.system.service.SmsConfigService;
import top.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.continew.starter.extension.crud.enums.Api;
import top.continew.starter.web.model.R;

/**
 * 短信配置管理 API
 *
 * @author bixiu
 * @author bixiu
 * @since 3.6.0
 */
@Tag(name = "短信配置管理 API")
@RestController
@CrudRequestMapping(value = "/system/sms/config",
    api = {Api.PAGE, Api.GET, Api.CREATE, Api.UPDATE, Api.BATCH_DELETE})
public class SmsConfigController extends
    BaseController<SmsConfigService, SmsConfigResp, SmsConfigResp, SmsConfigQuery, SmsConfigReq> {

    @Operation(summary = "设为默认配置", description = "设为默认配置")
    @Parameter(name = "id", description = "ID", example = "1", in = ParameterIn.PATH)
    @SaCheckPermission("system:smsConfig:setDefault")
    @PutMapping({"/{id}/default"})
    public R<Void> setDefault(@PathVariable("id") Long id) {
        baseService.setDefaultConfig(id);
        return R.ok();
    }
}
