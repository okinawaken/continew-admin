package top.continew.admin.common.config.excel;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字典字段注解
 *
 * @author Charles7c
 * @since 3.6.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DictExcelProperty {

    /**
     * 字典编码
     *
     * @return 字典编码
     */
    String value();
}
