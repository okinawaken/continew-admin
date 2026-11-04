package top.continew.admin.system.model.resp.file;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.continew.admin.system.enums.FileUploadProgressStatusEnum;

import java.io.Serial;
import java.io.Serializable;

/**
 * 文件上传进度响应参数
 *
 * @author bixiu
 * @since 4.2.0
 */
@Data
@Schema(description = "文件上传进度响应参数")
public class FileUploadProgressResp implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 上传任务 ID
     */
    @Schema(description = "上传任务 ID", example = "upload-task-1")
    private String uploadTaskId;

    /**
     * 任务状态（INIT/UPLOADING/FINALIZING/COMPLETED/FAILED/NOT_FOUND）
     */
    @Schema(description = "任务状态（INIT/UPLOADING/FINALIZING/COMPLETED/FAILED/NOT_FOUND）",
        example = "UPLOADING")
    private FileUploadProgressStatusEnum status;

    /**
     * 上传进度百分比
     */
    @Schema(description = "上传进度百分比", example = "65")
    private Integer percentage;

    /**
     * 已上传字节数
     */
    @Schema(description = "已上传字节数", example = "3407872")
    private Long bytesRead;

    /**
     * 总字节数
     */
    @Schema(description = "总字节数", example = "5242880")
    private Long totalBytes;

    /**
     * 文件 ID（完成上传后返回）
     */
    @Schema(description = "文件 ID（完成上传后返回）", example = "1897293810343682049")
    private String fileId;

    /**
     * 文件 URL（完成上传后返回）
     */
    @Schema(description = "文件 URL（完成上传后返回）", example = "http://localhost:8000/file/example.png")
    private String url;

    /**
     * 错误信息
     */
    @Schema(description = "错误信息")
    private String message;
}
