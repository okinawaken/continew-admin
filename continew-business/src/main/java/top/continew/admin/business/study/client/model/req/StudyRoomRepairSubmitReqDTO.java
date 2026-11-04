package top.continew.admin.business.study.client.model.req;

import lombok.Data;

import java.util.List;

/**
 * 提交报修（/mp/userRepair/submit）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomRepairSubmitReqDTO {

    /**
     * 门店ID（必填）
     */
    private Integer venueId;

    /**
     * 问题描述（必填）
     */
    private String repairDesc;

    /**
     * 图片地址列表（先调 /mp/image/upload 上传）
     */
    private List<String> repairImgList;
}
