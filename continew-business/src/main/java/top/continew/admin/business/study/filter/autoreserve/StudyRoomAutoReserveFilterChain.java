package top.continew.admin.business.study.filter.autoreserve;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import top.continew.admin.business.study.filter.FilterResultDTO;
import top.continew.admin.business.study.model.context.StudyRoomAutoReserveContext;

import java.util.List;

/**
 * 自动预约过滤器链，按顺序执行过滤器，遇到失败立即返回。
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Component
@RequiredArgsConstructor
public class StudyRoomAutoReserveFilterChain {

    private final List<StudyRoomAutoReserveFilter> filters;

    /**
     * 按顺序执行过滤器链，遇到失败立即返回
     *
     * @param context 执行上下文
     * @return 执行结果
     */
    public FilterResultDTO<?> doFilter(StudyRoomAutoReserveContext context) {
        for (StudyRoomAutoReserveFilter filter : this.filters) {
            FilterResultDTO<?> result = filter.doFilter(context);
            if (!result.isSuccess()) {
                return result;
            }
        }
        return FilterResultDTO.success();
    }
}
