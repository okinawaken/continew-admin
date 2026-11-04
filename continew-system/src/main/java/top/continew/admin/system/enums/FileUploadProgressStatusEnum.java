package top.continew.admin.system.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import top.continew.starter.core.enums.BaseEnum;

/**
 * 文件上传进度状态枚举
 *
 * @author bixiu
 * @since 4.2.0
 */
@Getter
@RequiredArgsConstructor
public enum FileUploadProgressStatusEnum implements BaseEnum<String> {

    /**
     * 初始化
     */
    INIT("INIT", "初始化"),

    /**
     * 上传中
     */
    UPLOADING("UPLOADING", "上传中"),

    /**
     * 收尾处理中（文件字节已传输完成，等待后端处理完成）
     */
    FINALIZING("FINALIZING", "收尾处理中"),

    /**
     * 已完成
     */
    COMPLETED("COMPLETED", "已完成"),

    /**
     * 失败
     */
    FAILED("FAILED", "失败"),

    /**
     * 未找到任务
     */
    NOT_FOUND("NOT_FOUND", "未找到任务");

    private final String value;
    private final String description;
}
