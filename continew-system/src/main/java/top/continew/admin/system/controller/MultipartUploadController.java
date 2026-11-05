package top.continew.admin.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.continew.admin.system.model.entity.FileDO;
import top.continew.admin.system.model.req.MultipartUploadCreateReq;
import top.continew.admin.system.model.resp.file.MultipartUploadCreateResp;
import top.continew.admin.system.model.resp.file.MultipartUploadPartResp;
import top.continew.admin.system.service.MultipartUploadService;
import top.continew.starter.web.model.R;

import jakarta.validation.Valid;

/**
 * 分片上传控制器
 *
 * @author bixiu
 * @since 4.1.0
 */
@RestController
@RequestMapping("/system/multipart-upload")
@RequiredArgsConstructor
public class MultipartUploadController {

    private final MultipartUploadService multipartUploadService;

    /**
     * 初始化分片上传
     *
     * @param multiPartUploadInitReq 分片上传信息
     * @return 初始化响应
     */
    @Operation(summary = "初始化分片上传", description = "初始化分片上传，返回uploadId等信息")
    @SaCheckPermission("system:file:upload")
    @PostMapping("/init")
    public R<MultipartUploadCreateResp> initMultipartUpload(
        @RequestBody @Valid MultipartUploadCreateReq multiPartUploadInitReq) {
        return R.ok(multipartUploadService.initMultipartUpload(multiPartUploadInitReq));
    }

    /**
     * 上传分片
     *
     * @param file       分片文件
     * @param uploadId   上传ID
     * @param partNumber 分片编号
     * @param path       文件路径
     * @return 上传结果
     */
    @Operation(summary = "上传分片", description = "上传单个分片")
    @SaCheckPermission("system:file:upload")
    @PostMapping("/part")
    public R<MultipartUploadPartResp> uploadPart(@RequestPart("file") MultipartFile file,
        @RequestParam("uploadId") String uploadId,
        @RequestParam("partNumber") Integer partNumber,
        @RequestParam("path") String path) {
        return R.ok(multipartUploadService.uploadPart(file, uploadId, partNumber, path));
    }

    /**
     * 合并分片
     *
     * @param uploadId 上传ID
     * @return 文件信息
     */
    @Operation(summary = "完成分片上传", description = "合并所有分片，完成上传")
    @SaCheckPermission("system:file:upload")
    @GetMapping("/complete/{uploadId}")
    public R<FileDO> completeMultipartUpload(@PathVariable String uploadId) {
        return R.ok(multipartUploadService.completeMultipartUpload(uploadId));
    }

    /**
     * 取消分片上传
     *
     * @param uploadId 上传ID
     * @return 操作结果
     */
    @Operation(summary = "取消分片上传", description = "删除缓存信息,分片数据")
    @SaCheckPermission("system:file:upload")
    @GetMapping("/cancel/{uploadId}")
    public R<Void> cancelMultipartUpload(@PathVariable String uploadId) {
        multipartUploadService.cancelMultipartUpload(uploadId);
        return R.ok();
    }

}
