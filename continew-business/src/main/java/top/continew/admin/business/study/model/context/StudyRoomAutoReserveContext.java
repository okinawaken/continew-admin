package top.continew.admin.business.study.model.context;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import top.continew.admin.business.study.client.model.resp.StudyRoomReserveConfirmRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomSeatRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomVenueRespDTO;
import top.continew.admin.business.study.model.resp.StudyRoomAutoReserveTaskRespDTO;

import java.time.LocalDate;

/**
 * 自动预约执行上下文
 * <p>
 * 在过滤器链中传递任务、目标日期、座位与终态信息。
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@RequiredArgsConstructor
public class StudyRoomAutoReserveContext {

    /**
     * 任务数据
     */
    private final StudyRoomAutoReserveTaskRespDTO taskRespDTO;

    /**
     * 触发方式
     */
    private final Integer triggerType;

    /**
     * 目标预约日期
     */
    private LocalDate targetDate;

    /**
     * 选中的门店
     */
    private StudyRoomVenueRespDTO venueRespDTO;

    /**
     * 选中的座位
     */
    private StudyRoomSeatRespDTO seatRespDTO;

    /**
     * 预约确认结果
     */
    private StudyRoomReserveConfirmRespDTO confirmRespDTO;
}
