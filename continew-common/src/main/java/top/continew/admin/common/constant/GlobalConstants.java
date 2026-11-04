package top.continew.admin.common.constant;

import java.time.ZoneId;

/**
 * 全局常量
 *
 * @author bixiu
 * @since 1.0.0
 */
public class GlobalConstants {

    /**
     * 根父级 ID
     */
    public static final Long ROOT_PARENT_ID = 0L;

    /**
     * 默认业务时区（与数据库连接、雪花算法配置保持一致，统一为 Asia/Shanghai）
     */
    public static final ZoneId DEFAULT_ZONE_ID = ZoneId.of("Asia/Shanghai");

    /**
     * 布尔值常量
     */
    public static class Boolean {

        /**
         * 否
         */
        public static final Integer NO = 0;

        /**
         * 是
         */
        public static final Integer YES = 1;

        private Boolean() {
        }
    }

    private GlobalConstants() {
    }
}
