package top.continew.admin.system.api;

import cn.hutool.core.lang.tree.Tree;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.continew.admin.common.api.system.MenuApi;
import top.continew.admin.common.enums.DisEnableStatusEnum;
import top.continew.admin.system.model.query.MenuQuery;
import top.continew.admin.system.service.MenuService;

import java.util.List;

/**
 * 菜单业务 API 实现
 *
 * @author Charles7c
 * @since 4.0.0
 */
@Service
@RequiredArgsConstructor
public class MenuApiImpl implements MenuApi {

    private final MenuService baseService;

    @Override
    public List<Tree<Long>> listTree(List<Long> excludeMenuIds, boolean isSimple) {
        MenuQuery query = new MenuQuery();
        query.setStatus(DisEnableStatusEnum.ENABLE);
        // 过滤掉租户不能使用的菜单
        query.setExcludeMenuIdList(excludeMenuIds);
        return baseService.tree(query, null, isSimple);
    }
}
