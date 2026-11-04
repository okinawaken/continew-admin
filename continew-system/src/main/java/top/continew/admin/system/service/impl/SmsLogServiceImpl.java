package top.continew.admin.system.service.impl;

import org.springframework.stereotype.Service;
import top.continew.admin.common.base.service.BaseServiceImpl;
import top.continew.admin.system.mapper.SmsLogMapper;
import top.continew.admin.system.model.entity.SmsLogDO;
import top.continew.admin.system.model.query.SmsLogQuery;
import top.continew.admin.system.model.req.SmsLogReq;
import top.continew.admin.system.model.resp.SmsLogResp;
import top.continew.admin.system.service.SmsLogService;

/**
 * 短信日志业务实现
 *
 * @author luoqiz
 * @since 3.6.0
 */
@Service
public class SmsLogServiceImpl
    extends BaseServiceImpl<SmsLogMapper, SmsLogDO, SmsLogResp, SmsLogResp, SmsLogQuery, SmsLogReq>
    implements SmsLogService {
}
