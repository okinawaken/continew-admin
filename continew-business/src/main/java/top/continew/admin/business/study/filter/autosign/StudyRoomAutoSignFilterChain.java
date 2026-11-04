package top.continew.admin.business.study.filter.autosign;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import top.continew.admin.business.study.filter.FilterResultDTO;
import top.continew.admin.business.study.model.context.StudyRoomAutoSignContext;

import java.util.List;

/**
 * 自动签到过滤器链，按顺序执行过滤器，遇到失败立即返回。
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Component
@RequiredArgsConstructor
public class StudyRoomAutoSignFilterChain {

    private final List<StudyRoomAutoSignFilter> filters;

    /**
     * 按顺序执行过滤器链，遇到失败立即返回
     *
     * @param context 执行上下文
     * @return 执行结果
     */
    public FilterResultDTO<?> doFilter(StudyRoomAutoSignContext context) {
        for (StudyRoomAutoSignFilter filter : this.filters) {
            FilterResultDTO<?> result = filter.doFilter(context);
            if (!result.isSuccess()) {
                return result;
            }
        }
        return FilterResultDTO.success();
    }
}
