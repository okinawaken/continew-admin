package top.continew.admin.system.service;

import top.continew.admin.common.base.service.BaseService;
import top.continew.admin.system.model.entity.SmsConfigDO;
import top.continew.admin.system.model.query.SmsConfigQuery;
import top.continew.admin.system.model.req.SmsConfigReq;
import top.continew.admin.system.model.resp.SmsConfigResp;

/**
 * 短信配置业务接口
 *
 * @author bixiu
 * @since 3.6.0
 */
public interface SmsConfigService
    extends BaseService<SmsConfigResp, SmsConfigResp, SmsConfigQuery, SmsConfigReq> {

    /**
     * 设置默认配置
     *
     * @param id ID
     */
    void setDefaultConfig(Long id);

    /**
     * 获取默认短信配置
     *
     * @return 默认短信配置
     */
    SmsConfigDO getDefaultConfig();
}
