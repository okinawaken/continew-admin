-- liquibase formatted sql

-- changeset bixiu:2
-- comment 初始化自习室业务菜单与权限数据
-- 注意：component 必须是前端 src/views 下的相对路径（不含 .vue、不含 src/views/ 前缀），
-- 否则动态路由拿不到组件，页面会呈现空白。
INSERT INTO `sys_menu`
(`id`, `title`, `parent_id`, `type`, `path`, `name`, `component`, `redirect`, `icon`, `is_external`, `is_cache`, `is_hidden`, `permission`, `sort`, `status`, `create_user`, `create_time`)
VALUES
-- 目录
(4000, '自习室管理', 0, 1, '/study', 'Study', 'Layout', '/study/dashboard', 'calendar', b'0', b'0', b'0', NULL, 4, 1, 1, NOW()),

-- 学习概览（聚合页，接口权限随下方各菜单一并授予）
(4010, '学习概览', 4000, 2, '/study/dashboard', 'StudyDashboard', 'study/dashboard/index', NULL, 'dashboard', b'0', b'0', b'0', NULL, 1, 1, 1, NOW()),

-- 门店
(4020, '门店查询', 4000, 2, '/study/venue', 'StudyVenue', 'study/venue/index', NULL, 'location', b'0', b'0', b'0', NULL, 2, 1, 1, NOW()),
(4021, '查询', 4020, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:study:venue:list', 1, 1, 1, NOW()),

-- 座位
(4030, '座位查询', 4000, 2, '/study/seat', 'StudySeat', 'study/seat/index', NULL, 'layout', b'0', b'0', b'0', NULL, 3, 1, 1, NOW()),

-- 套餐
(4040, '套餐查询', 4000, 2, '/study/package', 'StudyPackage', 'study/package/index', NULL, 'tags', b'0', b'0', b'0', NULL, 4, 1, 1, NOW()),
(4041, '查询', 4040, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:study:package:list', 1, 1, 1, NOW()),
(4042, '下单支付', 4040, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:study:package:create', 2, 1, 1, NOW()),

-- 订单
(4050, '订单查询', 4000, 2, '/study/order', 'StudyOrder', 'study/order/index', NULL, 'ordered-list', b'0', b'0', b'0', NULL, 5, 1, 1, NOW()),

-- 预约
(4060, '预约管理', 4000, 2, '/study/reserve', 'StudyReserve', 'study/reserve/index', NULL, 'schedule', b'0', b'0', b'0', NULL, 6, 1, 1, NOW()),
(4061, '查询', 4060, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:study:reserve:list', 1, 1, 1, NOW()),
(4062, '预约', 4060, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:study:reserve:create', 2, 1, 1, NOW()),
(4063, '取消预约', 4060, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:study:reserve:delete', 3, 1, 1, NOW()),

-- 签到（含门禁、历史签到记录）
(4070, '签到管理', 4000, 2, '/study/sign', 'StudySign', 'study/sign/index', NULL, 'check-circle', b'0', b'0', b'0', NULL, 7, 1, 1, NOW()),
(4071, '查询', 4070, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:study:sign:list', 1, 1, 1, NOW()),
(4072, '签到签退', 4070, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:study:sign:create', 2, 1, 1, NOW()),
(4073, '历史签到记录', 4070, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:study:user:list', 3, 1, 1, NOW()),
(4074, '查询门禁', 4070, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:study:guard:list', 4, 1, 1, NOW()),
(4075, '开门', 4070, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:study:guard:create', 5, 1, 1, NOW()),

-- 报修
(4080, '报修管理', 4000, 2, '/study/repair', 'StudyRepair', 'study/repair/index', NULL, 'tool', b'0', b'0', b'0', NULL, 8, 1, 1, NOW()),
(4081, '查询', 4080, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:study:repair:list', 1, 1, 1, NOW()),
(4082, '提交报修', 4080, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:study:repair:create', 2, 1, 1, NOW()),

-- 储物柜
(4090, '储物柜', 4000, 2, '/study/cabinet', 'StudyCabinet', 'study/cabinet/index', NULL, 'storage', b'0', b'0', b'0', NULL, 9, 1, 1, NOW()),
(4091, '查询', 4090, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:study:misc:list', 1, 1, 1, NOW()),
(4092, '操作', 4090, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:study:misc:create', 2, 1, 1, NOW()),

-- 自动预约任务
(4100, '自动预约任务', 4000, 2, '/study/task', 'StudyTask', 'study/task/index', NULL, 'clock-circle', b'0', b'0', b'0', NULL, 10, 1, 1, NOW()),
(4101, '列表', 4100, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:studyAuto-reserveTask:list', 1, 1, 1, NOW()),
(4102, '详情', 4100, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:studyAuto-reserveTask:get', 2, 1, 1, NOW()),
(4103, '新增', 4100, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:studyAuto-reserveTask:create', 3, 1, 1, NOW()),
(4104, '修改', 4100, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:studyAuto-reserveTask:update', 4, 1, 1, NOW()),
(4105, '删除', 4100, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:studyAuto-reserveTask:delete', 5, 1, 1, NOW()),
(4106, '执行', 4100, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:studyAuto-reserveTask:execute', 6, 1, 1, NOW()),

-- 自动预约执行记录
(4110, '执行记录', 4000, 2, '/study/record', 'StudyRecord', 'study/record/index', NULL, 'history', b'0', b'0', b'0', NULL, 11, 1, 1, NOW()),
(4111, '列表', 4110, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:studyAuto-reserveTaskRecord:list', 1, 1, 1, NOW()),

-- 用户设置
(4120, '用户设置', 4000, 2, '/study/setting', 'StudySetting', 'study/setting/index', NULL, 'settings', b'0', b'0', b'0', NULL, 12, 1, 1, NOW()),
(4121, '查询', 4120, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:studyUser-setting:get', 1, 1, 1, NOW()),
(4122, '修改', 4120, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'business:studyUser-setting:update', 2, 1, 1, NOW());
