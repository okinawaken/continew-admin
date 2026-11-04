package top.continew.admin.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.continew.admin.common.base.model.entity.BaseCreateDO;
import top.continew.admin.common.enums.SuccessFailureStatusEnum;

import java.io.Serial;

/**
 * 短信日志实体
 *
 * @author bixiu
 * @author bixiu
 * @since 3.6.0
 */
@Data
@TableName("sys_sms_log")
public class SmsLogDO extends BaseCreateDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 配置 ID
     */
    private Long configId;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 参数配置
     */
    private String params;

    /**
     * 发送状态
     */
    private SuccessFailureStatusEnum status;

    /**
     * 返回数据
     */
    private String resMsg;
}
