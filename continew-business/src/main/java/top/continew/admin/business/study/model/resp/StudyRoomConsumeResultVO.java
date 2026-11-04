package top.continew.admin.business.study.model.resp;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * StudyRoomConsumeResultRespDTO 视图对象
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomConsumeResultVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 核销状态
     */
    private Integer consumeState;

    /**
     * 核销说明
     */
    private String consumeMsg;

    /**
     * 核销套餐名称
     */
    private String packageName;

    /**
     * 券码
     */
    private String orderNumber;
}
