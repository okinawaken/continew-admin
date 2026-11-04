package top.continew.admin.system.model.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 头像上传响应参数
 *
 * @author bixiu
 * @since 1.0.0
 */
@Data
@Builder
@Schema(description = "头像上传响应参数")
public class AvatarResp implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 头像地址
     */
    @Schema(description = "头像地址",
        example = "https://himg.bdimg.com/sys/portrait/item/public.1.81ac9a9e.rf1ix17UfughLQjNo7XQ_w.jpg")
    private String avatar;
}
