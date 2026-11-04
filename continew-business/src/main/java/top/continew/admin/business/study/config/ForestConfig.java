package top.continew.admin.business.study.config;

import com.dtflys.forest.springboot.annotation.ForestScan;
import org.springframework.context.annotation.Configuration;

/**
 * Forest 客户端扫描配置
 *
 * @author bixiu
 * @since 4.2.0
 */
@Configuration
@ForestScan(basePackages = "top.continew.admin.business.study.client.http")
public class ForestConfig {
}
