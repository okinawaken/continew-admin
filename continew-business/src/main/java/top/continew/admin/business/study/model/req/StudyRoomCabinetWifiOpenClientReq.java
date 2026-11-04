package top.continew.admin.business.study.model.req;

import lombok.Data;

/**
 * 储物柜开锁（/mp/cabinetGrip/v2/wifi/open）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomCabinetWifiOpenClientReq {

    /**
     * 柜格ID（我的柜格列表里的 gripId）（必填）
     */
    private Long gripId;

}
