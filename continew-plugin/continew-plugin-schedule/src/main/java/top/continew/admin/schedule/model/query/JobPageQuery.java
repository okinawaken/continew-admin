package top.continew.admin.schedule.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.Min;
import java.io.Serial;
import java.io.Serializable;

/**
 * 任务分页查询条件
 *
 * @author bixiu
 * @since 3.6.0
 */
@Data
public class JobPageQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 页码
     */
    @Schema(description = "页码", example = "1")
    @Min(value = 1, message = "页码最小值为 {value}")
    private Integer page = 1;

    /**
     * 每页条数
     */
    @Schema(description = "每页条数", example = "10")
    @Range(min = 1, max = 1000, message = "每页条数（取值范围 {min}-{max}）")
    private Integer size = 10;
}
