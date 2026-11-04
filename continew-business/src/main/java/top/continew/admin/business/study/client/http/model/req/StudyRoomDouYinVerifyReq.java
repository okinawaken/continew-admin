package top.continew.admin.business.study.client.http.model.req;

import lombok.Data;

/**
 * 抖音券核销（/mp/douYin/certificate/prepareAndVerify）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomDouYinVerifyReq {

    /**
     * 门店ID（必填）
     */
    private Integer venueId;

    /**
     * 券码（必填）
     */
    private String receiptCode;
}
