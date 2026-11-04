package top.continew.admin.business.study.model.req;

import lombok.Data;

/**
 * 美团团购券核销（/mp/receipt/consume）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomReceiptConsumeClientReq {

    /**
     * 门店ID（必填）
     */
    private Integer venueId;

    /**
     * 团购券码（必填）
     */
    private String receiptCode;
}
