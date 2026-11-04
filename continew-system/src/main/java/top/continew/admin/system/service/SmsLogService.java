package top.continew.admin.system.service;

import top.continew.admin.common.base.service.BaseService;
import top.continew.admin.system.model.query.SmsLogQuery;
import top.continew.admin.system.model.req.SmsLogReq;
import top.continew.admin.system.model.resp.SmsLogResp;

/**
 * 短信日志业务接口
 *
 * @author luoqiz
 * @since 3.6.0
 */
public interface SmsLogService extends BaseService<SmsLogResp, SmsLogResp, SmsLogQuery, SmsLogReq> {
}
