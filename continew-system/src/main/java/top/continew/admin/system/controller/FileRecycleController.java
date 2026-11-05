package top.continew.admin.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.continew.admin.system.model.query.FileQuery;
import top.continew.admin.system.model.resp.file.FileResp;
import top.continew.admin.system.service.FileRecycleService;
import top.continew.starter.extension.crud.model.query.PageQuery;
import top.continew.starter.extension.crud.model.resp.PageResp;
import top.continew.starter.web.model.R;

import jakarta.validation.Valid;

/**
 * 文件回收站管理 API
 *
 * @author bixiu
 * @since 4.1.0
 */
@Tag(name = "文件回收站管理 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/file/recycle")
public class FileRecycleController {

    private final FileRecycleService baseService;

    @Operation(summary = "分页查询列表", description = "分页查询列表")
    @SaCheckPermission("system:fileRecycle:list")
    @GetMapping
    public PageResp<FileResp> page(@Valid FileQuery query, @Valid PageQuery pageQuery) {
        return baseService.page(query, pageQuery);
    }

    @Operation(summary = "还原文件", description = "还原文件")
    @SaCheckPermission("system:fileRecycle:restore")
    @PutMapping("/restore/{id}")
    public R<Void> restore(@PathVariable Long id) {
        baseService.restore(id);
        return R.ok();
    }

    @Operation(summary = "删除文件", description = "删除文件")
    @SaCheckPermission("system:fileRecycle:delete")
    @DeleteMapping("/{id}")
    public R<Void> delete(@PathVariable Long id) {
        baseService.delete(id);
        return R.ok();
    }

    @Operation(summary = "清空回收站", description = "清空回收站")
    @SaCheckPermission("system:fileRecycle:clean")
    @DeleteMapping("/clean")
    public R<Void> clean() {
        baseService.clean();
        return R.ok();
    }
}
