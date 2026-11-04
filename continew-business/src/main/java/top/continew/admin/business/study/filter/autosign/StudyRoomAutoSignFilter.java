package top.continew.admin.business.study.filter.autosign;

import org.springframework.core.Ordered;
import top.continew.admin.business.study.filter.FilterResultDTO;
import top.continew.admin.business.study.model.context.StudyRoomAutoSignContext;

/**
 * 自动签到执行过滤器
 *
 * @author bixiu
 * @since 4.2.0
 */
public interface StudyRoomAutoSignFilter extends Ordered {

    /**
     * 执行当前过滤环节
     *
     * @param context 执行上下文
     * @return 响应数据
     */
    FilterResultDTO<?> doFilter(StudyRoomAutoSignContext context);
}
