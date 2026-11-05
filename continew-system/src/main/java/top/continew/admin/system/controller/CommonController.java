package top.continew.admin.system.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.continew.admin.system.model.resp.file.FileUploadResp;
import top.continew.admin.system.service.DictItemService;
import top.continew.admin.system.service.FileService;
import top.continew.admin.system.service.OptionService;
import top.continew.starter.core.util.validation.ValidationUtils;
import top.continew.starter.extension.crud.model.resp.LabelValueResp;
import top.continew.starter.extension.tenant.annotation.TenantIgnore;
import top.continew.starter.extension.tenant.context.TenantContextHolder;
import top.continew.starter.log.annotation.Log;
import top.continew.starter.storage.domain.model.resp.FileInfo;
import top.continew.starter.web.model.R;

import jakarta.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

/**
 * 公共 API
 *
 * @author bixiu
 * @since 1.0.0
 */
@Tag(name = "公共 API")
@Log(ignore = true)
@Validated
@RestController("systemCommonController")
@RequiredArgsConstructor
@RequestMapping("/system/common")
public class CommonController {

    private final FileService fileService;
    private final DictItemService dictItemService;
    private final OptionService optionService;

    /**
     * 上传文件
     *
     * @param file 文件
     * @param parentPath 上级目录
     * @return 上传结果
     */
    @Operation(summary = "上传文件", description = "上传文件")
    @Parameter(name = "parentPath", description = "上级目录", example = "/", in = ParameterIn.QUERY)
    @PostMapping("/file")
    public R<FileUploadResp> upload(
        @RequestPart @NotNull(message = "文件不能为空") MultipartFile file,
        @RequestParam(required = false) String parentPath) throws IOException {
        ValidationUtils.throwIf(file::isEmpty, "文件不能为空");
        FileInfo fileInfo = fileService.upload(file, parentPath);
        return R.ok(FileUploadResp.builder()
            .id(fileInfo.getFileId())
            .url(fileInfo.getUrl())
            .thUrl(fileInfo.getThumbnailPath())
            .metadata(fileInfo.getMetadata())
            .build());
    }

    @Operation(summary = "查询字典", description = "查询字典列表")
    @Parameter(name = "code", description = "字典编码", example = "notice_type", in = ParameterIn.PATH)
    @GetMapping("/dict/{code}")
    public R<List<LabelValueResp>> listDict(@PathVariable String code) {
        return R.ok(dictItemService.listByDictCode(code));
    }

    /**
     * 查询系统配置参数
     *
     * @return 系统配置参数列表
     */
    @TenantIgnore
    @SaIgnore
    @Operation(summary = "查询系统配置参数", description = "查询系统配置参数")
    @GetMapping("/dict/option/site")
    public R<List<LabelValueResp<String>>> listSiteOptionDict() {
        return R.ok(optionService.listSiteOptions());
    }

    @TenantIgnore
    @SaIgnore
    @Operation(summary = "查询租户开启状态", description = "查询租户开启状态")
    @GetMapping("/dict/option/tenant")
    public R<Boolean> tenantEnabled() {
        return R.ok(TenantContextHolder.isTenantEnabled());
    }
}
