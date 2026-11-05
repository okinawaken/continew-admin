-- liquibase formatted sql

-- changeset bixiu:2
-- comment 初始化自习室业务菜单与权限数据
INSERT INTO `sys_menu`
(`id`, `title`, `parent_id`, `type`, `path`, `name`, `component`, `redirect`, `icon`, `is_external`, `is_cache`, `is_hidden`, `permission`, `sort`, `status`, `create_user`, `create_time`)
VALUES
(4000, '自习室管理', 0, 1, '/study', 'Study', 'Layout', '/study/config', 'calendar', b'0', b'0', b'0', NULL, 4, 1, 1, NOW()),

(4010, '自习室配置', 4000, 2, '/study/config', 'StudyConfig', 'business/study/config/index', NULL, 'settings', b'0', b'0', b'0', NULL, 1, 1, 1, NOW()),
(4011, '查询', 4010, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:study:config:list', 1, 1, 1, NOW()),

(4020, '门店查询', 4000, 2, '/study/venue', 'StudyVenue', 'business/study/venue/index', NULL, 'location', b'0', b'0', b'0', NULL, 2, 1, 1, NOW()),
(4021, '查询', 4020, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:study:venue:list', 1, 1, 1, NOW()),

(4030, '套餐查询', 4000, 2, '/study/package', 'StudyPackage', 'business/study/package/index', NULL, 'layers', b'0', b'0', b'0', NULL, 3, 1, 1, NOW()),
(4031, '查询', 4030, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:study:package:list', 1, 1, 1, NOW()),
(4032, '下单支付', 4030, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:study:package:create', 2, 1, 1, NOW()),

(4040, '预约管理', 4000, 2, '/study/reserve', 'StudyReserve', 'business/study/reserve/index', NULL, 'calendar', b'0', b'0', b'0', NULL, 4, 1, 1, NOW()),
(4041, '查询', 4040, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:study:reserve:list', 1, 1, 1, NOW()),
(4042, '预约', 4040, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:study:reserve:create', 2, 1, 1, NOW()),
(4043, '取消预约', 4040, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:study:reserve:delete', 3, 1, 1, NOW()),

(4050, '签到管理', 4000, 2, '/study/sign', 'StudySign', 'business/study/sign/index', NULL, 'check-circle', b'0', b'0', b'0', NULL, 5, 1, 1, NOW()),
(4051, '查询', 4050, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:study:sign:list', 1, 1, 1, NOW()),
(4052, '签到签退', 4050, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:study:sign:create', 2, 1, 1, NOW()),

(4060, '请假管理', 4000, 2, '/study/leave', 'StudyLeave', 'business/study/leave/index', NULL, 'file', b'0', b'0', b'0', NULL, 6, 1, 1, NOW()),
(4061, '查询', 4060, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:study:leave:list', 1, 1, 1, NOW()),
(4062, '请假', 4060, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:study:leave:create', 2, 1, 1, NOW()),
(4063, '取消请假', 4060, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:study:leave:delete', 3, 1, 1, NOW()),

(4070, '报修管理', 4000, 2, '/study/repair', 'StudyRepair', 'business/study/repair/index', NULL, 'tool', b'0', b'0', b'0', NULL, 7, 1, 1, NOW()),
(4071, '查询', 4070, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:study:repair:list', 1, 1, 1, NOW()),
(4072, '提交报修', 4070, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:study:repair:create', 2, 1, 1, NOW()),

(4080, '门禁管理', 4000, 2, '/study/guard', 'StudyGuard', 'business/study/guard/index', NULL, 'lock', b'0', b'0', b'0', NULL, 8, 1, 1, NOW()),
(4081, '查询', 4080, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:study:guard:list', 1, 1, 1, NOW()),
(4082, '开门', 4080, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:study:guard:create', 2, 1, 1, NOW()),

(4090, '储物柜与其他', 4000, 2, '/study/misc', 'StudyMisc', 'business/study/misc/index', NULL, 'storage', b'0', b'0', b'0', NULL, 9, 1, 1, NOW()),
(4091, '查询', 4090, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:study:misc:list', 1, 1, 1, NOW()),
(4092, '操作', 4090, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:study:misc:create', 2, 1, 1, NOW()),

(4100, '用户中心', 4000, 2, '/study/user', 'StudyUser', 'business/study/user/index', NULL, 'user', b'0', b'0', b'0', NULL, 10, 1, 1, NOW()),
(4101, '查询', 4100, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:study:user:list', 1, 1, 1, NOW()),
(4102, '登录授权', 4100, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:study:user:create', 2, 1, 1, NOW()),
(4103, '资料维护', 4100, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:study:user:update', 3, 1, 1, NOW()),
(4104, '解除授权', 4100, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:study:user:delete', 4, 1, 1, NOW()),

(4120, '用户设置', 4000, 2, '/study/user-setting', 'StudyUserSetting', 'business/study/userSetting/index', NULL, 'clock-circle', b'0', b'0', b'0', NULL, 12, 1, 1, NOW()),
(4121, '查询', 4120, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:studyUser-setting:get', 1, 1, 1, NOW()),
(4122, '修改', 4120, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:studyUser-setting:update', 2, 1, 1, NOW()),

(4130, '自动预约任务', 4000, 2, '/study/auto-reserve/task', 'StudyAutoReserveTask', 'business/study/autoReserve/task/index', NULL, 'alarm-clock', b'0', b'0', b'0', NULL, 13, 1, 1, NOW()),
(4131, '列表', 4130, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:studyAuto-reserveTask:list', 1, 1, 1, NOW()),
(4132, '详情', 4130, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:studyAuto-reserveTask:get', 2, 1, 1, NOW()),
(4133, '新增', 4130, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:studyAuto-reserveTask:create', 3, 1, 1, NOW()),
(4134, '修改', 4130, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:studyAuto-reserveTask:update', 4, 1, 1, NOW()),
(4135, '删除', 4130, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:studyAuto-reserveTask:delete', 5, 1, 1, NOW()),
(4136, '执行', 4130, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:studyAuto-reserveTask:execute', 6, 1, 1, NOW()),

(4140, '执行记录', 4000, 2, '/study/auto-reserve/record', 'StudyAutoReserveRecord', 'business/study/autoReserve/record/index', NULL, 'history', b'0', b'0', b'0', NULL, 14, 1, 1, NOW()),
(4141, '列表', 4140, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:studyAuto-reserveTaskRecord:list', 1, 1, 1, NOW());
