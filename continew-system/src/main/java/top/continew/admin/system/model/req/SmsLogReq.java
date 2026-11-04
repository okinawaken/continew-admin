package top.continew.admin.system.model.req;

import lombok.Data;
import top.continew.admin.common.enums.SuccessFailureStatusEnum;

import java.io.Serial;
import java.io.Serializable;

/**
 * 短信日志创建或修改请求参数
 *
 * @author bixiu
 * @author bixiu
 * @since 3.6.0
 */
@Data
public class SmsLogReq implements Serializable {

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
