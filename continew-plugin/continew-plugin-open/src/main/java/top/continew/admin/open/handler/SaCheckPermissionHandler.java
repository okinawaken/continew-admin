package top.continew.admin.open.handler;

import static cn.dev33.satoken.annotation.handler.SaCheckPermissionHandler._checkMethod;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.handler.SaAnnotationHandlerInterface;
import org.springframework.stereotype.Component;
import top.continew.admin.open.util.OpenApiUtils;

import java.lang.reflect.AnnotatedElement;

/**
 * 重定义注解 SaCheckPermission 的处理器
 *
 * @author chengzi
 * @since 3.4.0
 */
@Component
public class SaCheckPermissionHandler implements SaAnnotationHandlerInterface<SaCheckPermission> {

    @Override
    public Class<SaCheckPermission> getHandlerAnnotationClass() {
        return SaCheckPermission.class;
    }

    @Override
    public void checkMethod(SaCheckPermission saCheckPermission,
        AnnotatedElement annotatedElement) {
        if (!OpenApiUtils.isSignParamExists()) {
            _checkMethod(saCheckPermission.type(), saCheckPermission.value(), saCheckPermission
                .mode(), saCheckPermission.orRole());
        }
    }
}
