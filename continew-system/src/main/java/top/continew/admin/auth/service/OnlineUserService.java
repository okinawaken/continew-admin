package top.continew.admin.auth.service;

import top.continew.admin.auth.model.query.OnlineUserQuery;
import top.continew.admin.auth.model.resp.OnlineUserResp;
import top.continew.starter.extension.crud.model.query.PageQuery;
import top.continew.starter.extension.crud.model.resp.PageResp;

import java.util.List;

/**
 * 在线用户业务接口
 *
 * @author bixiu
 * @since 1.0.0
 */
public interface OnlineUserService {

    /**
     * 分页查询列表
     *
     * @param query     查询条件
     * @param pageQuery 分页查询条件
     * @return 分页列表信息
     */
    PageResp<OnlineUserResp> page(OnlineUserQuery query, PageQuery pageQuery);

    /**
     * 查询列表
     *
     * @param query 查询条件
     * @return 列表信息
     */
    List<OnlineUserResp> list(OnlineUserQuery query);

    /**
     * 踢出用户
     *
     * @param userId 用户 ID
     */
    void kickOut(Long userId);
}
