-- liquibase formatted sql

-- changeset bixiu:1
-- comment 初始化自习室业务表结构
CREATE TABLE IF NOT EXISTS `study_room_user_token` (
    `id`          bigint       AUTO_INCREMENT              COMMENT 'ID',
    `tenant_id`   bigint       NOT NULL DEFAULT 0          COMMENT '租户ID',
    `user_id`     bigint       NOT NULL DEFAULT 0          COMMENT '系统用户ID',
    `token`       varchar(1024) NOT NULL DEFAULT ''        COMMENT '微信小程序token',
    `create_user` bigint       NOT NULL                    COMMENT '创建人',
    `create_time` datetime     NOT NULL                    COMMENT '创建时间',
    `update_user` bigint       DEFAULT NULL                COMMENT '修改人',
    `update_time` datetime     DEFAULT NULL                COMMENT '修改时间',
    `deleted`     bigint       NOT NULL DEFAULT 0          COMMENT '是否已删除（0：否；id：是）',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `uk_user_token_tenant_user_id`(`tenant_id`, `user_id`, `deleted`),
    INDEX `idx_user_token_tenant_id`(`tenant_id`),
    INDEX `idx_user_token_create_user`(`create_user`),
    INDEX `idx_user_token_update_user`(`update_user`),
    INDEX `idx_user_token_deleted`(`deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统用户与微信小程序token映射表';

CREATE TABLE IF NOT EXISTS `study_room_user_setting` (
    `id`          bigint       AUTO_INCREMENT              COMMENT 'ID',
    `tenant_id`   bigint       NOT NULL DEFAULT 0          COMMENT '租户ID',
    `user_id`     bigint       NOT NULL DEFAULT 0          COMMENT '系统用户ID',
    `config_json` varchar(2048) NOT NULL DEFAULT '{}'      COMMENT '自动预约配置（JSON）',
    `create_user` bigint       NOT NULL                    COMMENT '创建人',
    `create_time` datetime     NOT NULL                    COMMENT '创建时间',
    `update_user` bigint       DEFAULT NULL                COMMENT '修改人',
    `update_time` datetime     DEFAULT NULL                COMMENT '修改时间',
    `deleted`     bigint       NOT NULL DEFAULT 0          COMMENT '是否已删除（0：否；id：是）',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `uk_user_setting_tenant_user_id`(`tenant_id`, `user_id`, `deleted`),
    INDEX `idx_user_setting_tenant_id`(`tenant_id`),
    INDEX `idx_user_setting_create_user`(`create_user`),
    INDEX `idx_user_setting_update_user`(`update_user`),
    INDEX `idx_user_setting_deleted`(`deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='自习室用户设置表';

CREATE TABLE IF NOT EXISTS `study_room_auto_reserve_task` (
    `id`                    bigint          AUTO_INCREMENT              COMMENT 'ID',
    `tenant_id`             bigint          NOT NULL DEFAULT 0          COMMENT '租户ID',
    `user_id`               bigint          NOT NULL DEFAULT 0          COMMENT '系统用户ID',
    `venue_id`              int             NOT NULL DEFAULT 0          COMMENT '门店ID',
    `venue_name`            varchar(128)    NOT NULL DEFAULT ''         COMMENT '门店名称',
    `task_json`             varchar(2048)   NOT NULL DEFAULT '{}'       COMMENT '任务详情（JSON）',
    `enable_status`         tinyint UNSIGNED NOT NULL DEFAULT 0        COMMENT '启用状态（0：暂停；1：启用）',
    `pause_reason`          varchar(32)     NOT NULL DEFAULT ''         COMMENT '暂停原因',
    `last_max_reserve_date` date            NOT NULL DEFAULT '1970-01-01' COMMENT '放号基线日期',
    `create_user`           bigint          NOT NULL                    COMMENT '创建人',
    `create_time`           datetime        NOT NULL                    COMMENT '创建时间',
    `update_user`           bigint          DEFAULT NULL                COMMENT '修改人',
    `update_time`           datetime        DEFAULT NULL                COMMENT '修改时间',
    `deleted`               bigint          NOT NULL DEFAULT 0          COMMENT '是否已删除（0：否；id：是）',
    PRIMARY KEY (`id`),
    INDEX `idx_task_tenant_user_status`(`tenant_id`, `user_id`, `enable_status`),
    INDEX `idx_task_tenant_id`(`tenant_id`),
    INDEX `idx_task_create_user`(`create_user`),
    INDEX `idx_task_update_user`(`update_user`),
    INDEX `idx_task_deleted`(`deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='自动预约任务表';

CREATE TABLE IF NOT EXISTS `study_room_auto_reserve_task_record` (
    `id`           bigint          AUTO_INCREMENT              COMMENT 'ID',
    `tenant_id`    bigint          NOT NULL DEFAULT 0          COMMENT '租户ID',
    `task_id`      bigint          NOT NULL DEFAULT 0          COMMENT '任务ID',
    `user_id`      bigint          NOT NULL DEFAULT 0          COMMENT '系统用户ID',
    `trigger_type` tinyint UNSIGNED NOT NULL DEFAULT 0        COMMENT '触发方式（0：定时；1：手动）',
    `target_date`  date            NOT NULL DEFAULT '1970-01-01' COMMENT '目标预约日期',
    `status`       tinyint UNSIGNED NOT NULL DEFAULT 0        COMMENT '执行状态（0：跳过；1：成功；2：失败）',
    `detail_json`  varchar(2048)   NOT NULL DEFAULT '{}'       COMMENT '尝试明细（JSON）',
    `create_user`  bigint          NOT NULL                    COMMENT '创建人',
    `create_time`  datetime        NOT NULL                    COMMENT '创建时间',
    `update_user`  bigint          DEFAULT NULL                COMMENT '修改人',
    `update_time`  datetime        DEFAULT NULL                COMMENT '修改时间',
    `deleted`      bigint          NOT NULL DEFAULT 0          COMMENT '是否已删除（0：否；id：是）',
    PRIMARY KEY (`id`),
    INDEX `idx_record_tenant_task_id`(`tenant_id`, `task_id`),
    INDEX `idx_record_tenant_id`(`tenant_id`),
    INDEX `idx_record_create_user`(`create_user`),
    INDEX `idx_record_update_user`(`update_user`),
    INDEX `idx_record_deleted`(`deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='自动预约执行记录表';
