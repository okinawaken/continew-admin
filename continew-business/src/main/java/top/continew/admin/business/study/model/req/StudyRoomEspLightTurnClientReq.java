package top.continew.admin.business.study.model.req;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 座位灯控（/mp/espLight/turn）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomEspLightTurnClientReq {

    /**
     * 座位ID（study/index 里 seatCurrent.id）（必填）
     */
    @NotNull(message = "座位ID不能为空")
    private Long seatId;

    /**
     * 开关灯：turnOn 开 / turnOff 关（必填）
     */
    @NotBlank(message = "开关灯参数不能为空")
    private String turnFlag;

}
