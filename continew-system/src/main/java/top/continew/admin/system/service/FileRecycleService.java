package top.continew.admin.system.service;

import top.continew.admin.system.model.query.FileQuery;
import top.continew.admin.system.model.resp.file.FileResp;
import top.continew.starter.extension.crud.model.query.PageQuery;
import top.continew.starter.extension.crud.model.resp.PageResp;

import jakarta.validation.Valid;

/**
 * 文件回收站业务接口
 *
 * @author bixiu
 * @since 4.1.0
 */
public interface FileRecycleService {

    /**
     * 分页查询列表
     *
     * @param query     查询参数
     * @param pageQuery 分页参数
     * @return 文件列表
     */
    PageResp<FileResp> page(@Valid FileQuery query, @Valid PageQuery pageQuery);

    /**
     * 还原文件
     *
     * @param id ID
     */
    void restore(Long id);

    /**
     * 删除文件
     *
     * @param id ID
     */
    void delete(Long id);

    /**
     * 清空回收站
     */
    void clean();
}
