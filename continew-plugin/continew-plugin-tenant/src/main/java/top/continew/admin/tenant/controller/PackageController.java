package top.continew.admin.tenant.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.lang.tree.Tree;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.continew.admin.common.api.system.MenuApi;
import top.continew.admin.common.base.controller.BaseController;
import top.continew.admin.common.config.TenantExtensionProperties;
import top.continew.admin.tenant.model.query.PackageQuery;
import top.continew.admin.tenant.model.req.PackageReq;
import top.continew.admin.tenant.model.resp.PackageDetailResp;
import top.continew.admin.tenant.model.resp.PackageResp;
import top.continew.admin.tenant.service.PackageService;
import top.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.continew.starter.extension.crud.enums.Api;

import java.util.List;

/**
 * 套餐管理 API
 *
 * @author 小熊
 * @author Charles7c
 * @since 3.4.1
 */
@Tag(name = "套餐管理 API")
@RestController
@RequiredArgsConstructor
@CrudRequestMapping(value = "/tenant/package",
    api = {Api.LIST, Api.PAGE, Api.GET, Api.CREATE, Api.UPDATE, Api.DELETE,
        Api.DICT})
public class PackageController extends
    BaseController<PackageService, PackageResp, PackageDetailResp, PackageQuery, PackageReq> {

    private final TenantExtensionProperties tenantExtensionProperties;
    private final MenuApi menuApi;

    @Operation(summary = "查询租户套餐菜单", description = "查询租户套餐菜单树列表")
    @SaCheckPermission("tenant:package:list")
    @GetMapping("/menu/tree")
    public List<Tree<Long>> listMenuTree(
        @RequestParam(required = false, defaultValue = "true") Boolean isSimple) {
        return menuApi.listTree(tenantExtensionProperties.getIgnoreMenus(), isSimple);
    }
}
