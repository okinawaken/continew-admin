package top.continew.admin.open.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.continew.admin.common.base.mapper.DataPermissionMapper;
import top.continew.admin.open.model.entity.AppDO;
import top.continew.starter.encrypt.field.annotation.FieldEncrypt;

/**
 * 应用 Mapper
 *
 * @author bixiu
 * @since 3.4.0
 */
@Mapper
public interface AppMapper extends DataPermissionMapper<AppDO> {

    /**
     * 根据 Access Key 查询
     *
     * @param accessKey Access Key
     * @return 应用信息
     */
    @Select("select * from sys_app where deleted = 0 AND access_key = #{accessKey}")
    AppDO selectByAccessKey(@FieldEncrypt @Param("accessKey") String accessKey);
}
