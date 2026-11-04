package top.continew.admin.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.continew.admin.common.base.model.entity.BaseDO;
import top.continew.admin.common.enums.DisEnableStatusEnum;
import top.continew.starter.encrypt.field.annotation.FieldEncrypt;

import java.io.Serial;

/**
 * 短信配置实体
 *
 * @author bixiu
 * @author bixiu
 * @since 3.6.0
 */
@Data
@TableName("sys_sms_config")
public class SmsConfigDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    private String name;

    /**
     * 厂商
     */
    private String supplier;

    /**
     * Access Key
     */
    private String accessKey;

    /**
     * Secret Key
     */
    @FieldEncrypt
    private String secretKey;

    /**
     * 短信签名
     */
    private String signature;

    /**
     * 模板 ID
     */
    private String templateId;

    /**
     * 负载均衡权重
     */
    private Integer weight;

    /**
     * 重试间隔（单位：秒）
     */
    private Integer retryInterval;

    /**
     * 重试次数
     */
    private Integer maxRetries;

    /**
     * 发送上限
     */
    private Integer maximum;

    /**
     * 各个厂商独立配置
     */
    private String supplierConfig;

    /**
     * 是否为默认存储
     */
    private Boolean isDefault;

    /**
     * 状态
     */
    private DisEnableStatusEnum status;
}
