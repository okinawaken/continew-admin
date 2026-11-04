package top.continew.admin.common.api.system;

import top.continew.starter.extension.crud.model.resp.LabelValueResp;

import java.util.List;

/**
 * 字典业务 API
 *
 * @author bixiu
 * @since 4.0.0
 */
public interface DictApi {

    /**
     * 查询字典列表
     *
     * @return 字典列表（包含枚举字典列表）
     */
    List<LabelValueResp> listAll();
}
