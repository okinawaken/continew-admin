package top.continew.admin.tenant.model.resp;

import lombok.Data;

import java.util.List;

/**
 * 租户通用信息返回
 *
 * @author bixiu
 * @since 3.4.1
 */
@Data
public class TenantCommonResp {

    /**
     * 是否开启了租户
     */
    private Boolean isEnabled;

    /**
     * 可用租户列表
     */
    private List<TenantAvailableResp> availableList;

}
