package top.continew.admin.business.study.filter.autoreserve;

import org.springframework.core.Ordered;
import top.continew.admin.business.study.filter.FilterResultDTO;
import top.continew.admin.business.study.model.context.StudyRoomAutoReserveContext;

/**
 * 自动预约执行过滤器
 *
 * @author Amadeus
 * @since 4.2.0
 */
public interface StudyRoomAutoReserveFilter extends Ordered {

    /**
     * 执行当前过滤环节
     *
     * @param context 执行上下文
     * @return 响应数据
     */
    FilterResultDTO<?> doFilter(StudyRoomAutoReserveContext context);
}
