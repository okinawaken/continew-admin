package top.continew.admin.business.study.client.http.model.req;

import lombok.Data;

import jakarta.validation.constraints.NotNull;

/**
 * 门禁开门（/mp/guard/open）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomGuardOpenClientReq {

    /**
     * 门禁 ID（/mp/guard/select 返回的 id）（必填）
     */
    @NotNull(message = "门禁ID不能为空")
    private Long guardId;

}
