package top.continew.admin.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.continew.admin.common.base.model.entity.BaseDO;
import top.continew.admin.common.enums.DisEnableStatusEnum;

import java.io.Serial;

/**
 * 字典项实体
 *
 * @author bixiu
 * @since 1.2.0
 */
@Data
@TableName("sys_dict_item")
public class DictItemDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 标签
     */
    private String label;

    /**
     * 值
     */
    private String value;

    /**
     * 标签颜色
     */
    private String color;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态
     */
    private DisEnableStatusEnum status;

    /**
     * 字典ID
     */
    private Long dictId;
}
