package top.continew.admin.system.model.resp.file;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.continew.admin.system.enums.StorageTypeEnum;

import java.io.Serial;
import java.io.Serializable;

/**
 * 默认存储上传配置响应参数
 *
 * @author bixiu
 * @since 4.2.0
 */
@Data
@Schema(description = "默认存储上传配置响应参数")
public class FileUploadConfigResp implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 默认存储 ID
     */
    @Schema(description = "默认存储 ID", example = "1")
    private Long storageId;

    /**
     * 默认存储名称
     */
    @Schema(description = "默认存储名称", example = "本地存储")
    private String storageName;

    /**
     * 默认存储编码
     */
    @Schema(description = "默认存储编码", example = "local")
    private String storageCode;

    /**
     * 存储类型（1: 本地存储，2: 对象存储）
     */
    @Schema(description = "存储类型（1: 本地存储，2: 对象存储）", example = "1")
    private StorageTypeEnum storageType;

    /**
     * 分片上传阈值（字节）
     */
    @Schema(description = "分片上传阈值（字节）", example = "10485760")
    private Long multipartUploadThreshold;

    /**
     * 分片上传大小（字节）
     */
    @Schema(description = "分片上传大小（字节）", example = "5242880")
    private Long multipartUploadPartSize;

    /**
     * 本地分片临时目录（仅本地存储返回）
     */
    @Schema(description = "本地分片临时目录（仅本地存储返回）", example = "/tmp/continew-multipart")
    private String multipartTempDir;
}
