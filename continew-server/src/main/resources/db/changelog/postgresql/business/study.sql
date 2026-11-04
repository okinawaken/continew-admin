-- liquibase formatted sql

-- changeset bixiu:1
-- comment 初始化自习室业务表结构
CREATE TABLE IF NOT EXISTS "study_room_user_setting" (
    "id"          int8          NOT NULL,
    "tenant_id"   int8          NOT NULL DEFAULT 0,
    "user_id"     int8          NOT NULL DEFAULT 0,
    "config_json" varchar(2048) NOT NULL DEFAULT '{}',
    "create_user" int8          NOT NULL,
    "create_time" timestamp     NOT NULL,
    "update_user" int8          DEFAULT NULL,
    "update_time" timestamp     DEFAULT NULL,
    "deleted"     int8          NOT NULL DEFAULT 0,
    PRIMARY KEY ("id")
);
CREATE UNIQUE INDEX "uk_user_setting_tenant_user_id" ON "study_room_user_setting" ("tenant_id", "user_id", "deleted");
CREATE INDEX "idx_user_setting_tenant_id"            ON "study_room_user_setting" ("tenant_id");
CREATE INDEX "idx_user_setting_create_user"          ON "study_room_user_setting" ("create_user");
CREATE INDEX "idx_user_setting_update_user"          ON "study_room_user_setting" ("update_user");
CREATE INDEX "idx_user_setting_deleted"              ON "study_room_user_setting" ("deleted");
COMMENT ON COLUMN "study_room_user_setting"."id"          IS 'ID';
COMMENT ON COLUMN "study_room_user_setting"."user_id"     IS '系统用户ID';
COMMENT ON COLUMN "study_room_user_setting"."config_json" IS '用户配置（JSON，含小程序 token）';
COMMENT ON COLUMN "study_room_user_setting"."tenant_id"   IS '租户ID';
COMMENT ON COLUMN "study_room_user_setting"."create_user" IS '创建人';
COMMENT ON COLUMN "study_room_user_setting"."create_time" IS '创建时间';
COMMENT ON COLUMN "study_room_user_setting"."update_user" IS '修改人';
COMMENT ON COLUMN "study_room_user_setting"."update_time" IS '修改时间';
COMMENT ON COLUMN "study_room_user_setting"."deleted"     IS '是否已删除（0：否；id：是）';
COMMENT ON TABLE  "study_room_user_setting"               IS '自习室用户设置表';

CREATE TABLE IF NOT EXISTS "study_room_auto_reserve_task" (
    "id"                    int8        NOT NULL,
    "tenant_id"             int8        NOT NULL DEFAULT 0,
    "user_id"               int8        NOT NULL DEFAULT 0,
    "venue_id"              int4        NOT NULL DEFAULT 0,
    "venue_name"            varchar(128) NOT NULL DEFAULT '',
    "task_json"             varchar(2048) NOT NULL DEFAULT '{}',
    "enable_status"         int2        NOT NULL DEFAULT 0,
    "pause_reason"          varchar(32) NOT NULL DEFAULT '',
    "last_max_reserve_date" date        NOT NULL DEFAULT '1970-01-01',
    "create_user"           int8        NOT NULL,
    "create_time"           timestamp   NOT NULL,
    "update_user"           int8        DEFAULT NULL,
    "update_time"           timestamp   DEFAULT NULL,
    "deleted"               int8        NOT NULL DEFAULT 0,
    PRIMARY KEY ("id")
);
CREATE INDEX "idx_task_tenant_user_status" ON "study_room_auto_reserve_task" ("tenant_id", "user_id", "enable_status");
CREATE INDEX "idx_task_tenant_id"           ON "study_room_auto_reserve_task" ("tenant_id");
CREATE INDEX "idx_task_create_user"         ON "study_room_auto_reserve_task" ("create_user");
CREATE INDEX "idx_task_update_user"         ON "study_room_auto_reserve_task" ("update_user");
CREATE INDEX "idx_task_deleted"             ON "study_room_auto_reserve_task" ("deleted");
COMMENT ON COLUMN "study_room_auto_reserve_task"."id"                    IS 'ID';
COMMENT ON COLUMN "study_room_auto_reserve_task"."user_id"               IS '系统用户ID';
COMMENT ON COLUMN "study_room_auto_reserve_task"."venue_id"              IS '门店ID';
COMMENT ON COLUMN "study_room_auto_reserve_task"."venue_name"            IS '门店名称';
COMMENT ON COLUMN "study_room_auto_reserve_task"."task_json"             IS '任务详情（JSON）';
COMMENT ON COLUMN "study_room_auto_reserve_task"."enable_status"         IS '启用状态（0：暂停；1：启用）';
COMMENT ON COLUMN "study_room_auto_reserve_task"."pause_reason"          IS '暂停原因';
COMMENT ON COLUMN "study_room_auto_reserve_task"."last_max_reserve_date" IS '放号基线日期';
COMMENT ON COLUMN "study_room_auto_reserve_task"."tenant_id"             IS '租户ID';
COMMENT ON COLUMN "study_room_auto_reserve_task"."create_user"           IS '创建人';
COMMENT ON COLUMN "study_room_auto_reserve_task"."create_time"           IS '创建时间';
COMMENT ON COLUMN "study_room_auto_reserve_task"."update_user"           IS '修改人';
COMMENT ON COLUMN "study_room_auto_reserve_task"."update_time"           IS '修改时间';
COMMENT ON COLUMN "study_room_auto_reserve_task"."deleted"               IS '是否已删除（0：否；id：是）';
COMMENT ON TABLE  "study_room_auto_reserve_task"                         IS '自动预约任务表';

CREATE TABLE IF NOT EXISTS "study_room_auto_reserve_task_record" (
    "id"           int8        NOT NULL,
    "tenant_id"    int8        NOT NULL DEFAULT 0,
    "task_id"      int8        NOT NULL DEFAULT 0,
    "user_id"      int8        NOT NULL DEFAULT 0,
    "trigger_type" int2        NOT NULL DEFAULT 0,
    "target_date"  date        NOT NULL DEFAULT '1970-01-01',
    "status"       int2        NOT NULL DEFAULT 0,
    "detail_json"  varchar(2048) NOT NULL DEFAULT '{}',
    "create_user"  int8        NOT NULL,
    "create_time"  timestamp   NOT NULL,
    "update_user"  int8        DEFAULT NULL,
    "update_time"  timestamp   DEFAULT NULL,
    "deleted"      int8        NOT NULL DEFAULT 0,
    PRIMARY KEY ("id")
);
CREATE INDEX "idx_record_tenant_task_id" ON "study_room_auto_reserve_task_record" ("tenant_id", "task_id");
CREATE INDEX "idx_record_tenant_id"      ON "study_room_auto_reserve_task_record" ("tenant_id");
CREATE INDEX "idx_record_create_user"    ON "study_room_auto_reserve_task_record" ("create_user");
CREATE INDEX "idx_record_update_user"    ON "study_room_auto_reserve_task_record" ("update_user");
CREATE INDEX "idx_record_deleted"        ON "study_room_auto_reserve_task_record" ("deleted");
COMMENT ON COLUMN "study_room_auto_reserve_task_record"."id"           IS 'ID';
COMMENT ON COLUMN "study_room_auto_reserve_task_record"."task_id"      IS '任务ID';
COMMENT ON COLUMN "study_room_auto_reserve_task_record"."user_id"      IS '系统用户ID';
COMMENT ON COLUMN "study_room_auto_reserve_task_record"."trigger_type" IS '触发方式（0：定时；1：手动）';
COMMENT ON COLUMN "study_room_auto_reserve_task_record"."target_date"  IS '目标预约日期';
COMMENT ON COLUMN "study_room_auto_reserve_task_record"."status"       IS '执行状态（0：跳过；1：成功；2：失败）';
COMMENT ON COLUMN "study_room_auto_reserve_task_record"."detail_json"  IS '尝试明细（JSON）';
COMMENT ON COLUMN "study_room_auto_reserve_task_record"."tenant_id"    IS '租户ID';
COMMENT ON COLUMN "study_room_auto_reserve_task_record"."create_user"  IS '创建人';
COMMENT ON COLUMN "study_room_auto_reserve_task_record"."create_time"  IS '创建时间';
COMMENT ON COLUMN "study_room_auto_reserve_task_record"."update_user"  IS '修改人';
COMMENT ON COLUMN "study_room_auto_reserve_task_record"."update_time"  IS '修改时间';
COMMENT ON COLUMN "study_room_auto_reserve_task_record"."deleted"      IS '是否已删除（0：否；id：是）';
COMMENT ON TABLE  "study_room_auto_reserve_task_record"                IS '自动预约执行记录表';
