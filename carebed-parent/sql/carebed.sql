/*
Navicat MySQL Data Transfer

Source Server         : 艾伊斯克信管开发
Source Server Version : 50718
Source Host           : 10.0.20.57:3306
Source Database       : carebed

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2020-07-13 11:26:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `config_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='参数配置表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('1', '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
INSERT INTO `sys_config` VALUES ('2', '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '初始化密码 123456');
INSERT INTO `sys_config` VALUES ('3', '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '深黑主题theme-dark，浅色主题theme-light，深蓝主题theme-blue');
INSERT INTO `sys_config` VALUES ('4', '账号自助-是否开启用户注册功能', 'sys.account.registerUser', 'false', 'Y', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '是否开启注册用户功能');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父部门id',
  `ancestors` varchar(50) DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) DEFAULT '' COMMENT '部门名称',
  `dept_code` varchar(20) NOT NULL DEFAULT '' COMMENT '机构编码',
  `business_time` date DEFAULT NULL COMMENT '部门成立时间',
  `city` varchar(32) NOT NULL DEFAULT '' COMMENT '城市',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `status` char(1) DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志(0代表存在,1代表删除)',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `leader` varchar(20) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`dept_id`),
  UNIQUE KEY `parent_id` (`parent_id`,`dept_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data` (
  `dict_code` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(4) DEFAULT '0' COMMENT '字典排序',
  `dict_label` varchar(100) DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`)
) ENGINE=InnoDB AUTO_INCREMENT=154 DEFAULT CHARSET=utf8 COMMENT='字典数据表';

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES ('1', '1', '男', '0', 'sys_user_sex', '', '', 'Y', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '性别男');
INSERT INTO `sys_dict_data` VALUES ('2', '2', '女', '1', 'sys_user_sex', '', '', 'N', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '性别女');
INSERT INTO `sys_dict_data` VALUES ('4', '1', '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '显示菜单');
INSERT INTO `sys_dict_data` VALUES ('5', '2', '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '隐藏菜单');
INSERT INTO `sys_dict_data` VALUES ('6', '1', '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES ('7', '2', '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '停用状态');
INSERT INTO `sys_dict_data` VALUES ('8', '1', '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES ('9', '2', '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '停用状态');
INSERT INTO `sys_dict_data` VALUES ('10', '1', '默认', 'DEFAULT', 'sys_job_group', '', '', 'Y', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '默认分组');
INSERT INTO `sys_dict_data` VALUES ('11', '2', '系统', 'SYSTEM', 'sys_job_group', '', '', 'N', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '系统分组');
INSERT INTO `sys_dict_data` VALUES ('12', '1', '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '系统默认是');
INSERT INTO `sys_dict_data` VALUES ('13', '2', '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '系统默认否');
INSERT INTO `sys_dict_data` VALUES ('14', '1', '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '通知');
INSERT INTO `sys_dict_data` VALUES ('15', '2', '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '公告');
INSERT INTO `sys_dict_data` VALUES ('16', '1', '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES ('17', '2', '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '关闭状态');
INSERT INTO `sys_dict_data` VALUES ('18', '99', '其他', '0', 'sys_oper_type', '', 'info', 'N', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '其他操作');
INSERT INTO `sys_dict_data` VALUES ('19', '1', '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '新增操作');
INSERT INTO `sys_dict_data` VALUES ('20', '2', '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '修改操作');
INSERT INTO `sys_dict_data` VALUES ('21', '3', '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '删除操作');
INSERT INTO `sys_dict_data` VALUES ('22', '4', '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '授权操作');
INSERT INTO `sys_dict_data` VALUES ('23', '5', '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '导出操作');
INSERT INTO `sys_dict_data` VALUES ('24', '6', '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '导入操作');
INSERT INTO `sys_dict_data` VALUES ('25', '7', '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '强退操作');
INSERT INTO `sys_dict_data` VALUES ('26', '8', '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '生成操作');
INSERT INTO `sys_dict_data` VALUES ('27', '9', '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '清空操作');
INSERT INTO `sys_dict_data` VALUES ('28', '1', '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES ('29', '2', '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '停用状态');
INSERT INTO `sys_dict_data` VALUES ('50', '1', '个人', '0', 'certificate_type', '', '', 'Y', '0', '1', '2020-04-01 13:53:09', '1', '2020-05-26 09:48:59', '证件类型:身份证');
INSERT INTO `sys_dict_data` VALUES ('51', '2', '企业', '1', 'certificate_type', '', '', 'Y', '0', '1', '2020-04-01 13:53:09', '1', '2020-05-26 09:49:06', '证件类型:护照');
INSERT INTO `sys_dict_data` VALUES ('52', '3', '军官证', '3', 'certificate_type', '', '', 'Y', '0', '1', '2020-04-01 13:53:09', '1', '2020-04-01 13:53:09', '证件类型:军官证');
INSERT INTO `sys_dict_data` VALUES ('53', '4', '港澳台回乡证或台胞证', '4', 'certificate_type', '', '', 'Y', '0', '1', '2020-04-01 13:53:09', '1', '2020-04-01 13:53:09', '证件类型:港澳台回乡证或台胞证');
INSERT INTO `sys_dict_data` VALUES ('54', '5', '统一社会信用代码', '5', 'certificate_type', '', '', 'Y', '0', '1', '2020-04-01 13:53:09', '1', '2020-04-01 13:53:09', '证件类型:统一社会信用代码');
INSERT INTO `sys_dict_data` VALUES ('121', '1', '参数配置缓存', 'zkyk-config', 'redis_cache_info', null, null, 'Y', '0', '1', '2020-04-01 13:53:09', '1', '2020-04-01 13:53:09', '');
INSERT INTO `sys_dict_data` VALUES ('122', '2', '数据字典缓存', 'zkyk-dict', 'redis_cache_info', null, null, 'Y', '0', '1', '2020-04-01 13:53:09', '1', '2020-04-01 13:53:09', '');
INSERT INTO `sys_dict_data` VALUES ('123', '3', '岗位及职级缓存', 'zkyk-post', 'redis_cache_info', null, null, 'Y', '0', '1', '2020-04-01 13:53:09', '1', '2020-04-01 13:53:09', '');
INSERT INTO `sys_dict_data` VALUES ('145', '1', '维护中', '00', 'cot_status', '', '', 'Y', '0', '1', '2020-06-17 16:37:34', '1', '2020-06-17 16:37:48', '');
INSERT INTO `sys_dict_data` VALUES ('146', '2', '闲置中', '01', 'cot_status', null, null, 'Y', '0', '1', '2020-06-17 16:38:02', '', null, null);
INSERT INTO `sys_dict_data` VALUES ('147', '3', '使用中', '02', 'cot_status', null, null, 'Y', '0', '1', '2020-06-17 16:38:13', '', null, null);
INSERT INTO `sys_dict_data` VALUES ('148', '4', '保修中', '03', 'cot_status', null, null, 'Y', '0', '1', '2020-06-17 16:38:28', '', null, null);
INSERT INTO `sys_dict_data` VALUES ('149', '1', '租用中', '00', 'order_status', null, null, 'Y', '0', '1', '2020-06-17 18:40:00', '', null, null);
INSERT INTO `sys_dict_data` VALUES ('150', '2', '已完成', '01', 'order_status', null, null, 'Y', '0', '1', '2020-06-17 18:40:12', '', null, null);
INSERT INTO `sys_dict_data` VALUES ('151', '1', '待处理', '00', 'question_status', null, null, 'Y', '0', '1', '2020-06-18 09:57:31', '', null, null);
INSERT INTO `sys_dict_data` VALUES ('152', '2', '已处理', '01', 'question_status', null, null, 'Y', '0', '1', '2020-06-18 09:57:44', '', null, null);
INSERT INTO `sys_dict_data` VALUES ('153', '5', '已作废', '04', 'cot_status', null, null, 'Y', '0', '1', '2020-06-18 10:16:59', '', null, null);

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type` (
  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`),
  UNIQUE KEY `dict_type` (`dict_type`)
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8 COMMENT='字典类型表';

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES ('1', '用户性别', 'sys_user_sex', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '用户性别列表');
INSERT INTO `sys_dict_type` VALUES ('2', '菜单状态', 'sys_show_hide', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '菜单状态列表');
INSERT INTO `sys_dict_type` VALUES ('3', '系统开关', 'sys_normal_disable', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '系统开关列表');
INSERT INTO `sys_dict_type` VALUES ('4', '任务状态', 'sys_job_status', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '任务状态列表');
INSERT INTO `sys_dict_type` VALUES ('5', '任务分组', 'sys_job_group', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '任务分组列表');
INSERT INTO `sys_dict_type` VALUES ('6', '系统是否', 'sys_yes_no', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '系统是否列表');
INSERT INTO `sys_dict_type` VALUES ('7', '通知类型', 'sys_notice_type', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '通知类型列表');
INSERT INTO `sys_dict_type` VALUES ('8', '通知状态', 'sys_notice_status', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '通知状态列表');
INSERT INTO `sys_dict_type` VALUES ('9', '操作类型', 'sys_oper_type', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '操作类型列表');
INSERT INTO `sys_dict_type` VALUES ('10', '系统状态', 'sys_common_status', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '登录状态列表');
INSERT INTO `sys_dict_type` VALUES ('100', '证件类型', 'certificate_type', '0', '1', '2020-04-01 12:02:02', '1', '2020-04-01 12:02:02', '证件类型');
INSERT INTO `sys_dict_type` VALUES ('112', 'redis缓存名称', 'redis_cache_info', '0', '1', '2020-04-23 09:31:25', '', null, 'redis缓存名称');
INSERT INTO `sys_dict_type` VALUES ('119', '陪护床状态', 'cot_status', '0', '1', '2020-06-17 16:36:23', '', null, '陪护床状态');
INSERT INTO `sys_dict_type` VALUES ('120', '订单状态', 'order_status', '0', '1', '2020-06-17 18:39:16', '1', '2020-06-17 18:39:26', '订单状态');
INSERT INTO `sys_dict_type` VALUES ('121', '问题状态', 'question_status', '0', '1', '2020-06-18 09:57:14', '', null, '问题反馈状态');

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
  `invoke_target` varchar(500) NOT NULL COMMENT '调用目标字符串',
  `cron_expression` varchar(255) DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `concurrent` char(1) DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1暂停）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`job_id`,`job_name`,`job_group`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='定时任务调度表';

-- ----------------------------
-- Records of sys_job
-- ----------------------------

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log` (
  `job_log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_name` varchar(64) NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) NOT NULL COMMENT '任务组名',
  `invoke_target` varchar(500) NOT NULL COMMENT '调用目标字符串',
  `job_message` varchar(500) DEFAULT NULL COMMENT '日志信息',
  `status` char(1) DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `exception_info` varchar(2000) DEFAULT '' COMMENT '异常信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务调度日志表';

-- ----------------------------
-- Records of sys_job_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor` (
  `info_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `login_name` varchar(50) DEFAULT '' COMMENT '登录账号',
  `ipaddr` varchar(50) DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) DEFAULT '' COMMENT '操作系统',
  `status` char(1) DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) DEFAULT '' COMMENT '提示消息',
  `login_time` datetime DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=236 DEFAULT CHARSET=utf8 COMMENT='系统访问记录';

-- ----------------------------
-- Records of sys_logininfor
-- ----------------------------
INSERT INTO `sys_logininfor` VALUES ('181', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2020-06-17 13:54:25');
INSERT INTO `sys_logininfor` VALUES ('182', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2020-06-17 16:18:43');
INSERT INTO `sys_logininfor` VALUES ('183', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2020-06-17 16:19:09');
INSERT INTO `sys_logininfor` VALUES ('184', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2020-06-17 16:19:12');
INSERT INTO `sys_logininfor` VALUES ('185', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome 8', 'Windows 7', '0', '登录成功', '2020-06-17 16:23:15');
INSERT INTO `sys_logininfor` VALUES ('186', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2020-06-17 16:27:35');
INSERT INTO `sys_logininfor` VALUES ('187', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome 8', 'Windows 7', '0', '退出成功', '2020-06-17 16:31:13');
INSERT INTO `sys_logininfor` VALUES ('188', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome 8', 'Windows 7', '0', '登录成功', '2020-06-17 16:31:17');
INSERT INTO `sys_logininfor` VALUES ('189', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome 8', 'Windows 7', '0', '退出成功', '2020-06-17 16:32:02');
INSERT INTO `sys_logininfor` VALUES ('190', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome 8', 'Windows 7', '0', '登录成功', '2020-06-17 16:32:05');
INSERT INTO `sys_logininfor` VALUES ('191', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2020-06-17 17:08:16');
INSERT INTO `sys_logininfor` VALUES ('192', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2020-06-17 17:30:34');
INSERT INTO `sys_logininfor` VALUES ('193', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome 8', 'Windows 7', '0', '退出成功', '2020-06-17 18:21:34');
INSERT INTO `sys_logininfor` VALUES ('194', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome 8', 'Windows 7', '0', '登录成功', '2020-06-17 18:21:37');
INSERT INTO `sys_logininfor` VALUES ('195', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2020-06-17 18:44:11');
INSERT INTO `sys_logininfor` VALUES ('196', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome 8', 'Windows 7', '0', '登录成功', '2020-06-18 09:04:18');
INSERT INTO `sys_logininfor` VALUES ('197', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2020-06-18 09:09:42');
INSERT INTO `sys_logininfor` VALUES ('198', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2020-06-18 09:37:25');
INSERT INTO `sys_logininfor` VALUES ('199', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome 8', 'Windows 7', '0', '退出成功', '2020-06-18 10:17:25');
INSERT INTO `sys_logininfor` VALUES ('200', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome 8', 'Windows 7', '0', '登录成功', '2020-06-18 10:17:28');
INSERT INTO `sys_logininfor` VALUES ('201', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2020-06-18 11:03:16');
INSERT INTO `sys_logininfor` VALUES ('202', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2020-06-18 11:48:07');
INSERT INTO `sys_logininfor` VALUES ('203', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2020-06-18 12:49:32');
INSERT INTO `sys_logininfor` VALUES ('204', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome 8', 'Windows 7', '0', '登录成功', '2020-06-18 13:26:10');
INSERT INTO `sys_logininfor` VALUES ('205', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2020-06-18 13:43:54');
INSERT INTO `sys_logininfor` VALUES ('206', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2020-06-18 13:47:04');
INSERT INTO `sys_logininfor` VALUES ('207', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome 8', 'Windows 7', '0', '登录成功', '2020-06-18 14:14:30');
INSERT INTO `sys_logininfor` VALUES ('208', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2020-06-18 14:18:09');
INSERT INTO `sys_logininfor` VALUES ('209', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2020-06-18 15:05:46');
INSERT INTO `sys_logininfor` VALUES ('210', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2020-06-18 15:05:55');
INSERT INTO `sys_logininfor` VALUES ('211', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2020-06-18 15:06:02');
INSERT INTO `sys_logininfor` VALUES ('212', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2020-06-18 15:06:17');
INSERT INTO `sys_logininfor` VALUES ('213', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2020-06-18 15:07:25');
INSERT INTO `sys_logininfor` VALUES ('214', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2020-06-18 15:07:34');
INSERT INTO `sys_logininfor` VALUES ('215', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2020-06-18 15:08:27');
INSERT INTO `sys_logininfor` VALUES ('216', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2020-06-18 15:08:29');
INSERT INTO `sys_logininfor` VALUES ('217', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2020-06-18 15:09:39');
INSERT INTO `sys_logininfor` VALUES ('218', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2020-06-18 15:09:41');
INSERT INTO `sys_logininfor` VALUES ('219', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome 8', 'Windows 7', '0', '登录成功', '2020-06-18 15:46:24');
INSERT INTO `sys_logininfor` VALUES ('220', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2020-06-18 16:19:08');
INSERT INTO `sys_logininfor` VALUES ('221', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2020-06-18 16:19:13');
INSERT INTO `sys_logininfor` VALUES ('222', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2020-06-18 16:20:30');
INSERT INTO `sys_logininfor` VALUES ('223', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2020-06-18 16:20:33');
INSERT INTO `sys_logininfor` VALUES ('224', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2020-06-18 16:24:34');
INSERT INTO `sys_logininfor` VALUES ('225', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2020-06-18 16:24:38');
INSERT INTO `sys_logininfor` VALUES ('226', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2020-06-18 16:25:02');
INSERT INTO `sys_logininfor` VALUES ('227', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2020-06-18 16:25:07');
INSERT INTO `sys_logininfor` VALUES ('228', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2020-06-18 17:18:35');
INSERT INTO `sys_logininfor` VALUES ('229', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome 8', 'Windows 7', '0', '登录成功', '2020-06-18 17:46:20');
INSERT INTO `sys_logininfor` VALUES ('230', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2020-06-18 17:55:42');
INSERT INTO `sys_logininfor` VALUES ('231', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome 8', 'Windows 7', '0', '登录成功', '2020-06-19 09:09:17');
INSERT INTO `sys_logininfor` VALUES ('232', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2020-06-19 09:11:54');
INSERT INTO `sys_logininfor` VALUES ('233', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2020-06-19 11:34:03');
INSERT INTO `sys_logininfor` VALUES ('234', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2020-06-19 13:34:29');
INSERT INTO `sys_logininfor` VALUES ('235', 'admin', '0:0:0:0:0:0:0:1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2020-06-19 17:56:17');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `url` varchar(200) DEFAULT '#' COMMENT '请求地址',
  `target` varchar(20) DEFAULT '' COMMENT '打开方式（menuItem页签 menuBlank新窗口）',
  `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=163 DEFAULT CHARSET=utf8 COMMENT='菜单权限表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', '0', '13', '#', 'menuItem', 'M', '0', '', 'fa fa-gear', 'admin', '2018-03-16 11:33:00', 'admin', '2020-05-26 09:22:19', '系统管理目录');
INSERT INTO `sys_menu` VALUES ('2', '系统监控', '0', '14', '#', 'menuItem', 'M', '0', '', 'fa fa-video-camera', 'admin', '2018-03-16 11:33:00', 'admin', '2020-05-26 09:22:13', '系统监控目录');
INSERT INTO `sys_menu` VALUES ('3', '系统工具', '0', '15', '#', 'menuItem', 'M', '0', '', 'fa fa-bars', 'admin', '2018-03-16 11:33:00', 'admin', '2020-05-26 09:22:07', '系统工具目录');
INSERT INTO `sys_menu` VALUES ('4', '用户管理', '100', '2', '/system/user', 'menuItem', 'C', '0', 'system:user:view', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2020-04-16 12:36:24', '用户管理菜单');
INSERT INTO `sys_menu` VALUES ('5', '角色管理', '1', '2', '/system/role', '', 'C', '0', 'system:role:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '角色管理菜单');
INSERT INTO `sys_menu` VALUES ('6', '菜单管理', '1', '3', '/system/menu', '', 'C', '0', 'system:menu:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '菜单管理菜单');
INSERT INTO `sys_menu` VALUES ('8', '岗位管理', '1', '5', '/system/post', '', 'C', '0', 'system:post:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '岗位管理菜单');
INSERT INTO `sys_menu` VALUES ('9', '字典管理', '1', '6', '/system/dict', '', 'C', '0', 'system:dict:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '字典管理菜单');
INSERT INTO `sys_menu` VALUES ('10', '参数设置', '1', '7', '/system/config', '', 'C', '0', 'system:config:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '参数设置菜单');
INSERT INTO `sys_menu` VALUES ('11', '通知公告', '1', '8', '/system/notice', '', 'C', '0', 'system:notice:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知公告菜单');
INSERT INTO `sys_menu` VALUES ('12', '日志管理', '1', '9', '#', '', 'M', '0', '', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '日志管理菜单');
INSERT INTO `sys_menu` VALUES ('14', '定时任务', '2', '2', '/monitor/job', '', 'C', '0', 'monitor:job:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '定时任务菜单');
INSERT INTO `sys_menu` VALUES ('17', '表单构建', '3', '1', '/tool/build', '', 'C', '0', 'tool:build:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '表单构建菜单');
INSERT INTO `sys_menu` VALUES ('19', '系统接口', '3', '3', '/tool/swagger', '', 'C', '0', 'tool:swagger:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统接口菜单');
INSERT INTO `sys_menu` VALUES ('20', '操作日志', '12', '1', '/monitor/operlog', '', 'C', '0', 'monitor:operlog:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '操作日志菜单');
INSERT INTO `sys_menu` VALUES ('21', '登录日志', '12', '2', '/monitor/logininfor', '', 'C', '0', 'monitor:logininfor:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '登录日志菜单');
INSERT INTO `sys_menu` VALUES ('22', '用户查询', '4', '1', '#', '', 'F', '0', 'system:user:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('23', '用户新增', '4', '2', '#', '', 'F', '0', 'system:user:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('24', '用户修改', '4', '3', '#', '', 'F', '0', 'system:user:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('25', '用户删除', '4', '4', '#', '', 'F', '0', 'system:user:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('26', '用户导出', '4', '5', '#', '', 'F', '0', 'system:user:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('28', '重置密码', '4', '7', '#', '', 'F', '0', 'system:user:resetPwd', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('29', '角色查询', '5', '1', '#', '', 'F', '0', 'system:role:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('30', '角色新增', '5', '2', '#', '', 'F', '0', 'system:role:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('31', '角色修改', '5', '3', '#', '', 'F', '0', 'system:role:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('32', '角色删除', '5', '4', '#', '', 'F', '0', 'system:role:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('33', '角色导出', '5', '5', '#', '', 'F', '0', 'system:role:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('34', '菜单查询', '6', '1', '#', '', 'F', '0', 'system:menu:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('35', '菜单新增', '6', '2', '#', '', 'F', '0', 'system:menu:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('36', '菜单修改', '6', '3', '#', '', 'F', '0', 'system:menu:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('37', '菜单删除', '6', '4', '#', '', 'F', '0', 'system:menu:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('42', '岗位查询', '8', '1', '#', '', 'F', '0', 'system:post:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('43', '岗位新增', '8', '2', '#', '', 'F', '0', 'system:post:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('44', '岗位修改', '8', '3', '#', '', 'F', '0', 'system:post:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('45', '岗位删除', '8', '4', '#', '', 'F', '0', 'system:post:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('46', '岗位导出', '8', '5', '#', '', 'F', '0', 'system:post:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('47', '字典查询', '9', '1', '#', '', 'F', '0', 'system:dict:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('48', '字典新增', '9', '2', '#', '', 'F', '0', 'system:dict:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('49', '字典修改', '9', '3', '#', '', 'F', '0', 'system:dict:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('50', '字典删除', '9', '4', '#', '', 'F', '0', 'system:dict:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('51', '字典导出', '9', '5', '#', '', 'F', '0', 'system:dict:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('52', '参数查询', '10', '1', '#', '', 'F', '0', 'system:config:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('53', '参数新增', '10', '2', '#', '', 'F', '0', 'system:config:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('54', '参数修改', '10', '3', '#', '', 'F', '0', 'system:config:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('55', '参数删除', '10', '4', '#', '', 'F', '0', 'system:config:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('56', '参数导出', '10', '5', '#', '', 'F', '0', 'system:config:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('57', '公告查询', '11', '1', '#', '', 'F', '0', 'system:notice:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('58', '公告新增', '11', '2', '#', '', 'F', '0', 'system:notice:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('59', '公告修改', '11', '3', '#', '', 'F', '0', 'system:notice:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('60', '公告删除', '11', '4', '#', '', 'F', '0', 'system:notice:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('61', '操作查询', '20', '1', '#', '', 'F', '0', 'monitor:operlog:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('62', '操作删除', '20', '2', '#', '', 'F', '0', 'monitor:operlog:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('63', '详细信息', '20', '3', '#', '', 'F', '0', 'monitor:operlog:detail', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('64', '日志导出', '20', '4', '#', '', 'F', '0', 'monitor:operlog:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('65', '登录查询', '21', '1', '#', '', 'F', '0', 'monitor:logininfor:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('66', '登录删除', '21', '2', '#', '', 'F', '0', 'monitor:logininfor:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('67', '日志导出', '21', '3', '#', '', 'F', '0', 'monitor:logininfor:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('68', '账户解锁', '21', '4', '#', '', 'F', '0', 'monitor:logininfor:unlock', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('72', '任务查询', '14', '1', '#', '', 'F', '0', 'monitor:job:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('73', '任务新增', '14', '2', '#', '', 'F', '0', 'monitor:job:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('74', '任务修改', '14', '3', '#', '', 'F', '0', 'monitor:job:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('75', '任务删除', '14', '4', '#', '', 'F', '0', 'monitor:job:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('76', '状态修改', '14', '5', '#', '', 'F', '0', 'monitor:job:changeStatus', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('77', '任务详细', '14', '6', '#', '', 'F', '0', 'monitor:job:detail', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('78', '任务导出', '14', '7', '#', '', 'F', '0', 'monitor:job:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('100', '用户管理', '0', '1', '#', 'menuItem', 'M', '0', '', 'fa fa-user-circle', 'admin', '2020-04-16 12:33:16', 'admin', '2020-06-18 15:28:25', '');
INSERT INTO `sys_menu` VALUES ('103', '缓存管理', '1', '10', '/system/cache', 'menuItem', 'C', '0', 'system:cache:view', 'fa fa-barcode', 'admin', '2020-04-23 18:18:32', 'admin', '2020-04-23 18:19:42', '');
INSERT INTO `sys_menu` VALUES ('104', '缓存查询', '103', '1', '#', 'menuItem', 'F', '0', 'system:cache:list', '#', 'admin', '2020-04-23 18:20:43', 'admin', '2020-04-24 10:04:28', '');
INSERT INTO `sys_menu` VALUES ('105', '缓存刷新', '103', '2', '#', 'menuItem', 'F', '0', 'system:cache:refresh', '#', 'admin', '2020-04-24 14:52:05', '', null, '');
INSERT INTO `sys_menu` VALUES ('134', '干系组管理', '0', '6', '#', 'menuItem', 'M', '0', '', 'fa fa-group', 'admin', '2020-06-17 16:20:18', 'admin', '2020-06-18 15:19:43', '');
INSERT INTO `sys_menu` VALUES ('135', '干系组管理', '134', '1', '/business/stakeholderGroup', 'menuItem', 'C', '0', 'business:stakeholderGroup:view', '#', 'admin', '2020-06-17 16:22:13', '', null, '');
INSERT INTO `sys_menu` VALUES ('136', '陪护床管理', '0', '2', '#', 'menuItem', 'M', '0', '', 'fa fa-bed', 'admin', '2020-06-17 16:26:58', 'admin', '2020-06-18 15:18:17', '');
INSERT INTO `sys_menu` VALUES ('137', '陪护床管理', '136', '1', '/business/cot', 'menuItem', 'C', '0', 'business:cot:view', '#', 'admin', '2020-06-17 16:28:27', 'admin', '2020-06-17 16:32:00', '');
INSERT INTO `sys_menu` VALUES ('138', '新增', '137', '1', '#', 'menuItem', 'F', '0', 'business:cot:add', '#', 'admin', '2020-06-17 16:29:34', '', null, '');
INSERT INTO `sys_menu` VALUES ('139', '问题反馈', '0', '5', '#', 'menuItem', 'M', '0', '', 'fa fa-question-circle', 'admin', '2020-06-17 16:29:46', 'admin', '2020-06-18 15:24:55', '');
INSERT INTO `sys_menu` VALUES ('140', '删除', '137', '2', '#', 'menuItem', 'F', '0', 'business:cot:remove', '#', 'admin', '2020-06-17 16:30:07', '', null, '');
INSERT INTO `sys_menu` VALUES ('141', '问题反馈', '139', '1', '/business/question', 'menuItem', 'C', '0', 'business:question:view', 'fa fa-assistive-listening-systems', 'admin', '2020-06-17 16:30:29', '', null, '');
INSERT INTO `sys_menu` VALUES ('142', '导入', '137', '3', '#', 'menuItem', 'F', '0', 'business:cot:import', '#', 'admin', '2020-06-17 16:30:38', '', null, '');
INSERT INTO `sys_menu` VALUES ('143', '导出', '137', '4', '#', 'menuItem', 'F', '0', 'business:cot:export', '#', 'admin', '2020-06-17 16:30:58', '', null, '');
INSERT INTO `sys_menu` VALUES ('145', '订单管理', '0', '4', '#', 'menuItem', 'M', '0', '', 'fa fa-cart-arrow-down', 'admin', '2020-06-17 16:46:50', 'admin', '2020-06-18 15:30:19', '');
INSERT INTO `sys_menu` VALUES ('146', '订单管理', '145', '1', '/business/order', 'menuItem', 'C', '0', 'business:order:view', 'fa fa-balance-scale', 'admin', '2020-06-17 16:47:48', '', null, '');
INSERT INTO `sys_menu` VALUES ('147', '导出', '146', '1', '#', 'menuItem', 'F', '0', 'business:order:export', '#', 'admin', '2020-06-17 16:48:59', 'admin', '2020-06-17 16:49:24', '');
INSERT INTO `sys_menu` VALUES ('148', '陪护床查询', '137', '5', '#', 'menuItem', 'F', '0', 'business:cot:list', '#', 'admin', '2020-06-17 17:08:17', 'admin', '2020-06-17 17:08:42', '');
INSERT INTO `sys_menu` VALUES ('149', '医生管理', '0', '3', '#', 'menuItem', 'M', '0', '', 'fa fa-plus-square', 'admin', '2020-06-17 18:18:23', 'admin', '2020-06-18 15:23:48', '');
INSERT INTO `sys_menu` VALUES ('150', '医生管理', '149', '1', 'business/doctor', 'menuItem', 'C', '0', 'business:doctor:view', '#', 'admin', '2020-06-17 18:19:20', '', null, '');
INSERT INTO `sys_menu` VALUES ('151', '医生查询', '150', '1', '#', 'menuItem', 'F', '0', 'business:doctor:list', '#', 'admin', '2020-06-17 18:19:56', '', null, '');
INSERT INTO `sys_menu` VALUES ('152', '新增', '150', '2', '#', 'menuItem', 'F', '0', 'business:doctor:add', '#', 'admin', '2020-06-17 18:20:15', '', null, '');
INSERT INTO `sys_menu` VALUES ('153', '修改', '150', '3', '#', 'menuItem', 'F', '0', 'business:doctor:edit', '#', 'admin', '2020-06-17 18:20:30', '', null, '');
INSERT INTO `sys_menu` VALUES ('154', '删除', '150', '4', '#', 'menuItem', 'F', '0', 'business:doctor:remove', '#', 'admin', '2020-06-17 18:21:00', '', null, '');
INSERT INTO `sys_menu` VALUES ('155', '导出', '150', '5', '#', 'menuItem', 'F', '0', 'business:doctor:export', '#', 'admin', '2020-06-17 18:21:30', '', null, '');
INSERT INTO `sys_menu` VALUES ('156', '分润管理', '0', '7', '#', 'menuItem', 'M', '0', '', 'fa fa-jpy', 'admin', '2020-06-18 13:31:03', 'admin', '2020-06-18 15:26:08', '');
INSERT INTO `sys_menu` VALUES ('157', '分润管理', '156', '1', 'business/profitSharing', 'menuItem', 'C', '0', 'business:profitSharing:view', '#', 'admin', '2020-06-18 13:31:58', '', null, '');
INSERT INTO `sys_menu` VALUES ('158', '查询', '157', '1', '#', 'menuItem', 'F', '0', 'business:profitSharing:list', '#', 'admin', '2020-06-18 13:32:23', '', null, '');
INSERT INTO `sys_menu` VALUES ('159', '导出', '157', '2', '#', 'menuItem', 'F', '0', 'business:profitSharing:export', '#', 'admin', '2020-06-18 13:32:48', '', null, '');
INSERT INTO `sys_menu` VALUES ('160', '分润统计', '156', '1', 'business/profitSharingCensus', 'menuItem', 'C', '0', 'business:profitSharingCensus:view', '#', 'admin', '2020-06-18 13:34:54', 'admin', '2020-06-18 13:36:04', '');
INSERT INTO `sys_menu` VALUES ('161', '查询', '160', '1', '#', 'menuItem', 'F', '0', 'business:profitSharingCensus:list', '#', 'admin', '2020-06-18 13:35:26', '', null, '');
INSERT INTO `sys_menu` VALUES ('162', '导出', '160', '2', '#', 'menuItem', 'F', '0', 'business:profitSharingCensus:export', '#', 'admin', '2020-06-18 13:35:49', '', null, '');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice` (
  `notice_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) NOT NULL COMMENT '公告标题',
  `notice_type` char(1) NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` varchar(2000) DEFAULT NULL COMMENT '公告内容',
  `status` char(1) DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通知公告表';

-- ----------------------------
-- Records of sys_notice
-- ----------------------------

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log` (
  `oper_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) DEFAULT '' COMMENT '请求方式',
  `operator_type` int(1) DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(50) DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) DEFAULT '' COMMENT '返回参数',
  `status` int(1) DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`oper_id`)
) ENGINE=InnoDB AUTO_INCREMENT=652 DEFAULT CHARSET=utf8 COMMENT='操作日志记录';

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------
INSERT INTO `sys_oper_log` VALUES ('383', '菜单管理', '3', 'com.carebed.web.controller.manage.SysMenuController.remove()', 'GET', '1', 'admin', null, '/system/menu/remove/18', '0:0:0:0:0:0:0:1', '内网IP', '{ }', '{\r\n  \"msg\" : \"存在子菜单,不允许删除\",\r\n  \"code\" : 301\r\n}', '0', null, '2020-06-17 13:57:35');
INSERT INTO `sys_oper_log` VALUES ('384', '菜单管理', '3', 'com.carebed.web.controller.manage.SysMenuController.remove()', 'GET', '1', 'admin', null, '/system/menu/remove/79', '0:0:0:0:0:0:0:1', '内网IP', '{ }', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 13:57:40');
INSERT INTO `sys_oper_log` VALUES ('385', '菜单管理', '3', 'com.carebed.web.controller.manage.SysMenuController.remove()', 'GET', '1', 'admin', null, '/system/menu/remove/80', '0:0:0:0:0:0:0:1', '内网IP', '{ }', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 13:57:46');
INSERT INTO `sys_oper_log` VALUES ('386', '菜单管理', '3', 'com.carebed.web.controller.manage.SysMenuController.remove()', 'GET', '1', 'admin', null, '/system/menu/remove/81', '0:0:0:0:0:0:0:1', '内网IP', '{ }', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 13:57:52');
INSERT INTO `sys_oper_log` VALUES ('387', '菜单管理', '3', 'com.carebed.web.controller.manage.SysMenuController.remove()', 'GET', '1', 'admin', null, '/system/menu/remove/82', '0:0:0:0:0:0:0:1', '内网IP', '{ }', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 13:57:57');
INSERT INTO `sys_oper_log` VALUES ('388', '菜单管理', '3', 'com.carebed.web.controller.manage.SysMenuController.remove()', 'GET', '1', 'admin', null, '/system/menu/remove/83', '0:0:0:0:0:0:0:1', '内网IP', '{ }', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 13:58:03');
INSERT INTO `sys_oper_log` VALUES ('389', '菜单管理', '3', 'com.carebed.web.controller.manage.SysMenuController.remove()', 'GET', '1', 'admin', null, '/system/menu/remove/18', '0:0:0:0:0:0:0:1', '内网IP', '{ }', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 13:58:09');
INSERT INTO `sys_oper_log` VALUES ('390', '菜单管理', '1', 'com.carebed.web.controller.manage.SysMenuController.addSave()', 'POST', '1', 'admin', null, '/system/menu/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"干系组管理\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"6\" ],\r\n  \"icon\" : [ \"fa fa-bank\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 16:20:18');
INSERT INTO `sys_oper_log` VALUES ('391', '菜单管理', '1', 'com.carebed.web.controller.manage.SysMenuController.addSave()', 'POST', '1', 'admin', null, '/system/menu/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"parentId\" : [ \"134\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"干系组管理\" ],\r\n  \"url\" : [ \"/business/stakeholderGroup\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:stakeholderGroup:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 16:22:14');
INSERT INTO `sys_oper_log` VALUES ('392', '菜单管理', '1', 'com.carebed.web.controller.manage.SysMenuController.addSave()', 'POST', '1', 'admin', null, '/system/menu/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"陪护床管理\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"fa fa-folder-open\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 16:26:58');
INSERT INTO `sys_oper_log` VALUES ('393', '菜单管理', '1', 'com.carebed.web.controller.manage.SysMenuController.addSave()', 'POST', '1', 'admin', null, '/system/menu/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"parentId\" : [ \"136\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"陪护床管理\" ],\r\n  \"url\" : [ \"/business/cot/list\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:cot:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 16:28:27');
INSERT INTO `sys_oper_log` VALUES ('394', '菜单管理', '1', 'com.carebed.web.controller.manage.SysMenuController.addSave()', 'POST', '1', 'admin', null, '/system/menu/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"parentId\" : [ \"137\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"新增\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:cot:add\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 16:29:34');
INSERT INTO `sys_oper_log` VALUES ('395', '菜单管理', '1', 'com.carebed.web.controller.manage.SysMenuController.addSave()', 'POST', '1', 'admin', null, '/system/menu/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"问题反馈\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"5\" ],\r\n  \"icon\" : [ \"fa fa-leanpub\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 16:29:46');
INSERT INTO `sys_oper_log` VALUES ('396', '菜单管理', '1', 'com.carebed.web.controller.manage.SysMenuController.addSave()', 'POST', '1', 'admin', null, '/system/menu/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"parentId\" : [ \"137\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"删除\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:cot:remove\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 16:30:07');
INSERT INTO `sys_oper_log` VALUES ('397', '菜单管理', '1', 'com.carebed.web.controller.manage.SysMenuController.addSave()', 'POST', '1', 'admin', null, '/system/menu/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"parentId\" : [ \"139\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"问题反馈\" ],\r\n  \"url\" : [ \"/business/question\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:question:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"fa fa-assistive-listening-systems\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 16:30:29');
INSERT INTO `sys_oper_log` VALUES ('398', '菜单管理', '1', 'com.carebed.web.controller.manage.SysMenuController.addSave()', 'POST', '1', 'admin', null, '/system/menu/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"parentId\" : [ \"137\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"导入\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:cot:import\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 16:30:38');
INSERT INTO `sys_oper_log` VALUES ('399', '菜单管理', '1', 'com.carebed.web.controller.manage.SysMenuController.addSave()', 'POST', '1', 'admin', null, '/system/menu/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"parentId\" : [ \"137\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"导出\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:cot:export\" ],\r\n  \"orderNum\" : [ \"4\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 16:30:58');
INSERT INTO `sys_oper_log` VALUES ('400', '菜单管理', '1', 'com.carebed.web.controller.manage.SysMenuController.addSave()', 'POST', '1', 'admin', null, '/system/menu/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"parentId\" : [ \"139\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"问题处理\" ],\r\n  \"url\" : [ \"/business/question\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"fa fa-book\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 16:31:03');
INSERT INTO `sys_oper_log` VALUES ('401', '菜单管理', '2', 'com.carebed.web.controller.manage.SysMenuController.editSave()', 'POST', '1', 'admin', null, '/system/menu/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"menuId\" : [ \"137\" ],\r\n  \"parentId\" : [ \"136\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"陪护床管理\" ],\r\n  \"url\" : [ \"/business/cot\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:cot:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 16:32:00');
INSERT INTO `sys_oper_log` VALUES ('402', '字典类型', '1', 'com.carebed.web.controller.manage.SysDictTypeController.addSave()', 'POST', '1', 'admin', null, '/system/dict/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"dictName\" : [ \"陪护床状态\" ],\r\n  \"dictType\" : [ \"cot_status\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"陪护床状态\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 16:36:23');
INSERT INTO `sys_oper_log` VALUES ('403', '字典数据', '1', 'com.carebed.web.controller.manage.SysDictDataController.addSave()', 'POST', '1', 'admin', null, '/system/dict/data/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"dictLabel\" : [ \"00\" ],\r\n  \"dictValue\" : [ \"维护中\" ],\r\n  \"dictType\" : [ \"cot_status\" ],\r\n  \"cssClass\" : [ \"\" ],\r\n  \"dictSort\" : [ \"1\" ],\r\n  \"listClass\" : [ \"\" ],\r\n  \"isDefault\" : [ \"Y\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 16:37:34');
INSERT INTO `sys_oper_log` VALUES ('404', '字典数据', '2', 'com.carebed.web.controller.manage.SysDictDataController.editSave()', 'POST', '1', 'admin', null, '/system/dict/data/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"dictCode\" : [ \"145\" ],\r\n  \"dictLabel\" : [ \"维护中\" ],\r\n  \"dictValue\" : [ \"00\" ],\r\n  \"dictType\" : [ \"cot_status\" ],\r\n  \"cssClass\" : [ \"\" ],\r\n  \"dictSort\" : [ \"1\" ],\r\n  \"listClass\" : [ \"\" ],\r\n  \"isDefault\" : [ \"Y\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 16:37:48');
INSERT INTO `sys_oper_log` VALUES ('405', '字典数据', '1', 'com.carebed.web.controller.manage.SysDictDataController.addSave()', 'POST', '1', 'admin', null, '/system/dict/data/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"dictLabel\" : [ \"闲置中\" ],\r\n  \"dictValue\" : [ \"01\" ],\r\n  \"dictType\" : [ \"cot_status\" ],\r\n  \"cssClass\" : [ \"\" ],\r\n  \"dictSort\" : [ \"2\" ],\r\n  \"listClass\" : [ \"\" ],\r\n  \"isDefault\" : [ \"Y\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 16:38:02');
INSERT INTO `sys_oper_log` VALUES ('406', '字典数据', '1', 'com.carebed.web.controller.manage.SysDictDataController.addSave()', 'POST', '1', 'admin', null, '/system/dict/data/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"dictLabel\" : [ \"使用中\" ],\r\n  \"dictValue\" : [ \"02\" ],\r\n  \"dictType\" : [ \"cot_status\" ],\r\n  \"cssClass\" : [ \"\" ],\r\n  \"dictSort\" : [ \"3\" ],\r\n  \"listClass\" : [ \"\" ],\r\n  \"isDefault\" : [ \"Y\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 16:38:13');
INSERT INTO `sys_oper_log` VALUES ('407', '字典数据', '1', 'com.carebed.web.controller.manage.SysDictDataController.addSave()', 'POST', '1', 'admin', null, '/system/dict/data/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"dictLabel\" : [ \"保修中\" ],\r\n  \"dictValue\" : [ \"03\" ],\r\n  \"dictType\" : [ \"cot_status\" ],\r\n  \"cssClass\" : [ \"\" ],\r\n  \"dictSort\" : [ \"4\" ],\r\n  \"listClass\" : [ \"\" ],\r\n  \"isDefault\" : [ \"Y\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 16:38:28');
INSERT INTO `sys_oper_log` VALUES ('408', '菜单管理', '1', 'com.carebed.web.controller.manage.SysMenuController.addSave()', 'POST', '1', 'admin', null, '/system/menu/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"订单管理\" ],\r\n  \"url\" : [ \"/business/order\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"4\" ],\r\n  \"icon\" : [ \"fa fa-archive\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 16:46:50');
INSERT INTO `sys_oper_log` VALUES ('409', '菜单管理', '2', 'com.carebed.web.controller.manage.SysMenuController.editSave()', 'POST', '1', 'admin', null, '/system/menu/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"menuId\" : [ \"145\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"订单管理\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"4\" ],\r\n  \"icon\" : [ \"fa fa-archive\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 16:47:04');
INSERT INTO `sys_oper_log` VALUES ('410', '菜单管理', '2', 'com.carebed.web.controller.manage.SysMenuController.editSave()', 'POST', '1', 'admin', null, '/system/menu/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"menuId\" : [ \"145\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"订单管理\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"4\" ],\r\n  \"icon\" : [ \"fa fa-archive\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 16:47:13');
INSERT INTO `sys_oper_log` VALUES ('411', '菜单管理', '1', 'com.carebed.web.controller.manage.SysMenuController.addSave()', 'POST', '1', 'admin', null, '/system/menu/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"parentId\" : [ \"145\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"订单管理\" ],\r\n  \"url\" : [ \"/business/order\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:order:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"fa fa-balance-scale\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 16:47:48');
INSERT INTO `sys_oper_log` VALUES ('412', '菜单管理', '3', 'com.carebed.web.controller.manage.SysMenuController.remove()', 'GET', '1', 'admin', null, '/system/menu/remove/144', '0:0:0:0:0:0:0:1', '内网IP', '{ }', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 16:48:29');
INSERT INTO `sys_oper_log` VALUES ('413', '菜单管理', '1', 'com.carebed.web.controller.manage.SysMenuController.addSave()', 'POST', '1', 'admin', null, '/system/menu/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"parentId\" : [ \"146\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"导出\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 16:48:59');
INSERT INTO `sys_oper_log` VALUES ('414', '菜单管理', '2', 'com.carebed.web.controller.manage.SysMenuController.editSave()', 'POST', '1', 'admin', null, '/system/menu/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"menuId\" : [ \"147\" ],\r\n  \"parentId\" : [ \"146\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"导出\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:order:export\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 16:49:24');
INSERT INTO `sys_oper_log` VALUES ('415', '菜单管理', '1', 'com.carebed.web.controller.manage.SysMenuController.addSave()', 'POST', '1', 'admin', null, '/system/menu/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"parentId\" : [ \"137\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"订单查询\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"system:order:list\" ],\r\n  \"orderNum\" : [ \"5\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 17:08:17');
INSERT INTO `sys_oper_log` VALUES ('416', '菜单管理', '2', 'com.carebed.web.controller.manage.SysMenuController.editSave()', 'POST', '1', 'admin', null, '/system/menu/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"menuId\" : [ \"148\" ],\r\n  \"parentId\" : [ \"137\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"陪护床查询\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:cot:list\" ],\r\n  \"orderNum\" : [ \"5\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 17:08:42');
INSERT INTO `sys_oper_log` VALUES ('417', '陪护床', '1', 'com.carebed.web.controller.business.TCotController.addSave()', 'POST', '1', 'admin', null, '/business/cot/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"cotNo\" : [ \"BJ000001\" ],\r\n  \"memberName\" : [ \"sa\" ],\r\n  \"stakeholderGroupId\" : [ \"1\" ],\r\n  \"status\" : [ \"\" ]\r\n}', 'null', '1', 'org.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.reflection.ReflectionException: There is no getter for property named \'delSta\' in \'class com.carebed.business.entity.TCot\'\r\norg.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.reflection.ReflectionException: There is no getter for property named \'delSta\' in \'class com.carebed.business.entity.TCot\'\r\n	at org.mybatis.spring.MyBatisExceptionTranslator.translateExceptionIfPossible(MyBatisExceptionTranslator.java:77)\r\n	at org.mybatis.spring.SqlSessionTemplate$SqlSessionInterceptor.invoke(SqlSessionTemplate.java:446)\r\n	at com.sun.proxy.$Proxy79.insert(Unknown Source)\r\n	at org.mybatis.spring.SqlSessionTemplate.insert(SqlSessionTemplate.java:278)\r\n	at org.apache.ibatis.binding.MapperMethod.execute(MapperMethod.java:58)\r\n	at org.apache.ibatis.binding.MapperProxy.invoke(MapperProxy.java:59)\r\n	at com.sun.proxy.$Proxy111.insertTCot(Unknown Source)\r\n	at com.carebed.business.service.impl.TCotServiceImpl.insertTCot(TCotServiceImpl.java:89)\r\n	at com.alibaba.dubbo.common.bytecode.Wrapper1.invokeMethod(Wrapper1.java)\r\n	at com.alibaba.dubbo.rpc.proxy.javassist.JavassistProxyFactory$1.doInvoke(JavassistProxyFactory.java:47)\r\n	at com.alibaba.dubbo.rpc.proxy.AbstractProxyInvoker.invoke(AbstractProxyInvoker.java:76)\r\n	at com.alibaba.dubbo.config.invoker.DelegateProviderMetaDataInvoker.invoke(DelegateProviderMetaDataInvoker.java:52)\r\n	at com.alibaba.dubbo.rpc.protocol.InvokerWrapper.invoke(InvokerWrapper.java:56)\r\n	at com.alibaba.dubbo.rpc.filter.ExceptionFilter.invoke(ExceptionFilter.java:62)\r\n	at com.alibaba.dubbo.rpc.protocol.ProtocolFilterWrapper$1.invoke(ProtocolFilterWrapper.java:72)\r\n	at com.alibaba.dubbo.monitor.support.MonitorFilter.invoke(MonitorFilter.java:75)\r\n	at com.alibaba.dubbo.rpc.protocol.ProtocolFilterWrapper$1.invoke(ProtocolFilterWrapper.java:72)\r\n	at com.alibaba.dubbo.rpc.filter.TimeoutFilter.invoke(TimeoutFilter.java:42)\r\n	at com.alibaba.dubbo.rpc.protocol.Pro', '2020-06-17 17:11:33');
INSERT INTO `sys_oper_log` VALUES ('418', '陪护床', '1', 'com.carebed.web.controller.business.TCotController.addSave()', 'POST', '1', 'admin', null, '/business/cot/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"cotNo\" : [ \"BN00001\" ],\r\n  \"status\" : [ \"01\" ],\r\n  \"remark\" : [ \"dsafads\" ]\r\n}', 'null', '1', 'org.springframework.jdbc.BadSqlGrammarException: \r\n### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: Unknown column \'remark\' in \'field list\'\r\n### The error may involve com.carebed.business.mapper.TCotMapper.insertTCot-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into t_cot          ( cot_no,                                       status,                           remark,             create_time )           values ( ?,                                       ?,                           ?,             ? )\r\n### Cause: java.sql.SQLSyntaxErrorException: Unknown column \'remark\' in \'field list\'\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: Unknown column \'remark\' in \'field list\'\r\norg.springframework.jdbc.BadSqlGrammarException: \r\n### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: Unknown column \'remark\' in \'field list\'\r\n### The error may involve com.carebed.business.mapper.TCotMapper.insertTCot-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into t_cot          ( cot_no,                                       status,                           remark,             create_time )           values ( ?,                                       ?,                           ?,             ? )\r\n### Cause: java.sql.SQLSyntaxErrorException: Unknown column \'remark\' in \'field list\'\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: Unknown column \'remark\' in \'field list\'\r\n	at org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator.doTranslate(SQLErrorCodeSQLExceptionTranslator.java:234)\r\n	at org.springframework.jdbc.support.AbstractFallbackSQLExceptionTranslator.translate(AbstractFallbackSQLExceptionTranslator.java:72)\r\n	at org.mybatis.spring.MyBatisExceptionTranslator.translateExceptionIfPossible(MyBatisExceptionTranslator.java:73)\r\n	at org.mybatis.spring.SqlSessionTemplate$SqlSessionInterceptor.invoke(SqlSessionTemplate.java:446)\r\n', '2020-06-17 17:33:42');
INSERT INTO `sys_oper_log` VALUES ('419', '陪护床', '1', 'com.carebed.web.controller.business.TCotController.addSave()', 'POST', '1', 'admin', null, '/business/cot/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"cotNo\" : [ \"BN00001\" ],\r\n  \"status\" : [ \"01\" ],\r\n  \"remark\" : [ \"dsafads\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 17:34:18');
INSERT INTO `sys_oper_log` VALUES ('420', '陪护床', '1', 'com.carebed.web.controller.business.TCotController.addSave()', 'POST', '1', 'admin', null, '/business/cot/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"cotNo\" : [ \"BN00002\" ],\r\n  \"status\" : [ \"01\" ],\r\n  \"remark\" : [ \"adsf\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 17:34:31');
INSERT INTO `sys_oper_log` VALUES ('421', '陪护床', '1', 'com.carebed.web.controller.business.TCotController.addSave()', 'POST', '1', 'admin', null, '/business/cot/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"cotNo\" : [ \"BN00003\" ],\r\n  \"status\" : [ \"01\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 17:34:40');
INSERT INTO `sys_oper_log` VALUES ('422', '陪护床', '1', 'com.carebed.web.controller.business.TCotController.addSave()', 'POST', '1', 'admin', null, '/business/cot/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"cotNo\" : [ \"BN00004\" ],\r\n  \"status\" : [ \"02\" ],\r\n  \"remark\" : [ \"BN00004\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 17:35:56');
INSERT INTO `sys_oper_log` VALUES ('423', '陪护床', '5', 'com.carebed.web.controller.business.TCotController.export()', 'POST', '1', 'admin', null, '/business/cot/export', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"cotNo\" : [ \"\" ],\r\n  \"groupName\" : [ \"\" ],\r\n  \"status\" : [ \"\" ],\r\n  \"orderByColumn\" : [ \"\" ],\r\n  \"isAsc\" : [ \"asc\" ]\r\n}', '{\r\n  \"msg\" : \"3dbcca67-3de6-46a6-9f7c-d1937b88cff3_cot.xlsx\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 17:37:05');
INSERT INTO `sys_oper_log` VALUES ('424', '陪护床', '5', 'com.carebed.web.controller.business.TCotController.export()', 'POST', '1', 'admin', null, '/business/cot/export', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"checkBoxIds\" : [ \"3,4\" ],\r\n  \"cotNo\" : [ \"\" ],\r\n  \"groupName\" : [ \"\" ],\r\n  \"status\" : [ \"\" ],\r\n  \"orderByColumn\" : [ \"\" ],\r\n  \"isAsc\" : [ \"asc\" ]\r\n}', '{\r\n  \"msg\" : \"92ce1878-13de-43de-ac83-9c2b66f2fcb6_cot.xlsx\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 17:46:46');
INSERT INTO `sys_oper_log` VALUES ('425', '陪护床', '3', 'com.carebed.web.controller.business.TCotController.remove()', 'POST', '1', 'admin', null, '/business/cot/remove', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"ids\" : [ \"2,3\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 17:47:05');
INSERT INTO `sys_oper_log` VALUES ('426', '陪护床', '3', 'com.carebed.web.controller.business.TCotController.remove()', 'POST', '1', 'admin', null, '/business/cot/remove', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"ids\" : [ \"4\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 17:47:21');
INSERT INTO `sys_oper_log` VALUES ('427', '干系组', '1', 'com.carebed.web.controller.business.TStakeholderGroupController.addSave()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"groupName\" : [ \"哈哈\" ],\r\n  \"remark\" : [ \"哈哈哈哈哈哈\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 17:52:35');
INSERT INTO `sys_oper_log` VALUES ('428', '干系组', '1', 'com.carebed.web.controller.business.TStakeholderGroupController.addSave()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"groupName\" : [ \"sss\" ],\r\n  \"remark\" : [ \"sssss\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 18:01:28');
INSERT INTO `sys_oper_log` VALUES ('429', '干系组', '1', 'com.carebed.web.controller.business.TStakeholderGroupController.addSave()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"groupName\" : [ \"12122\" ],\r\n  \"remark\" : [ \"sddsdsd\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 18:04:20');
INSERT INTO `sys_oper_log` VALUES ('430', '干系组', '2', 'com.carebed.web.controller.business.TStakeholderGroupController.editSave()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"2\" ],\r\n  \"groupName\" : [ \"哈哈\" ],\r\n  \"remark\" : [ \"sadfsaf\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 18:05:49');
INSERT INTO `sys_oper_log` VALUES ('431', '干系组', '2', 'com.carebed.web.controller.business.TStakeholderGroupController.editSave()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"2\" ],\r\n  \"groupName\" : [ \"\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 18:06:03');
INSERT INTO `sys_oper_log` VALUES ('432', '干系组', '2', 'com.carebed.web.controller.business.TStakeholderGroupController.editSave()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"3\" ],\r\n  \"groupName\" : [ \"sss\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 18:06:18');
INSERT INTO `sys_oper_log` VALUES ('433', '干系组', '2', 'com.carebed.web.controller.business.TStakeholderGroupController.editSave()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"3\" ],\r\n  \"groupName\" : [ \"\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 18:06:48');
INSERT INTO `sys_oper_log` VALUES ('434', '干系组', '2', 'com.carebed.web.controller.business.TStakeholderGroupController.editSave()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"3\" ],\r\n  \"groupName\" : [ \"sss\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 18:07:24');
INSERT INTO `sys_oper_log` VALUES ('435', '干系组', '2', 'com.carebed.web.controller.business.TStakeholderGroupController.editSave()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"4\" ],\r\n  \"groupName\" : [ \"12122\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 18:07:54');
INSERT INTO `sys_oper_log` VALUES ('436', '菜单管理', '2', 'com.carebed.web.controller.manage.SysMenuController.editSave()', 'POST', '1', 'admin', null, '/system/menu/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"menuId\" : [ \"100\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"用户管理\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"fa fa-user-secret\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 18:17:37');
INSERT INTO `sys_oper_log` VALUES ('437', '菜单管理', '2', 'com.carebed.web.controller.manage.SysMenuController.editSave()', 'POST', '1', 'admin', null, '/system/menu/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"menuId\" : [ \"136\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"陪护床管理\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"fa fa-folder-open\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 18:17:41');
INSERT INTO `sys_oper_log` VALUES ('438', '菜单管理', '1', 'com.carebed.web.controller.manage.SysMenuController.addSave()', 'POST', '1', 'admin', null, '/system/menu/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"医生管理\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"fa fa-address-book\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 18:18:23');
INSERT INTO `sys_oper_log` VALUES ('439', '菜单管理', '1', 'com.carebed.web.controller.manage.SysMenuController.addSave()', 'POST', '1', 'admin', null, '/system/menu/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"parentId\" : [ \"149\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"医生管理\" ],\r\n  \"url\" : [ \"business/doctor\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:doctor:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 18:19:20');
INSERT INTO `sys_oper_log` VALUES ('440', '菜单管理', '1', 'com.carebed.web.controller.manage.SysMenuController.addSave()', 'POST', '1', 'admin', null, '/system/menu/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"parentId\" : [ \"150\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"医生查询\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:doctor:list\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 18:19:56');
INSERT INTO `sys_oper_log` VALUES ('441', '菜单管理', '1', 'com.carebed.web.controller.manage.SysMenuController.addSave()', 'POST', '1', 'admin', null, '/system/menu/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"parentId\" : [ \"150\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"新增\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:doctor:add\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 18:20:15');
INSERT INTO `sys_oper_log` VALUES ('442', '菜单管理', '1', 'com.carebed.web.controller.manage.SysMenuController.addSave()', 'POST', '1', 'admin', null, '/system/menu/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"parentId\" : [ \"150\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"修改\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:doctor:edit\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 18:20:30');
INSERT INTO `sys_oper_log` VALUES ('443', '菜单管理', '1', 'com.carebed.web.controller.manage.SysMenuController.addSave()', 'POST', '1', 'admin', null, '/system/menu/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"parentId\" : [ \"150\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"删除\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:doctor:remove\" ],\r\n  \"orderNum\" : [ \"4\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 18:21:00');
INSERT INTO `sys_oper_log` VALUES ('444', '菜单管理', '1', 'com.carebed.web.controller.manage.SysMenuController.addSave()', 'POST', '1', 'admin', null, '/system/menu/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"parentId\" : [ \"150\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"导出\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:doctor:export\" ],\r\n  \"orderNum\" : [ \"5\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 18:21:30');
INSERT INTO `sys_oper_log` VALUES ('445', '医生', '5', 'com.carebed.web.controller.business.TDoctorController.export()', 'POST', '1', 'admin', null, '/business/doctor/export', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"doctorName\" : [ \"\" ],\r\n  \"phone\" : [ \"\" ],\r\n  \"department\" : [ \"1\" ],\r\n  \"orderByColumn\" : [ \"\" ],\r\n  \"isAsc\" : [ \"asc\" ]\r\n}', '{\r\n  \"msg\" : \"64f05000-57b8-497c-8b08-dca281a06bcd_doctor.xlsx\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 18:22:29');
INSERT INTO `sys_oper_log` VALUES ('446', '医生', '5', 'com.carebed.web.controller.business.TDoctorController.export()', 'POST', '1', 'admin', null, '/business/doctor/export', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"checkBoxIds\" : [ \"1\" ],\r\n  \"doctorName\" : [ \"\" ],\r\n  \"phone\" : [ \"\" ],\r\n  \"department\" : [ \"\" ],\r\n  \"orderByColumn\" : [ \"\" ],\r\n  \"isAsc\" : [ \"asc\" ]\r\n}', '{\r\n  \"msg\" : \"66da7a9a-fc65-41f9-9ee7-49dd17eaa174_doctor.xlsx\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 18:24:18');
INSERT INTO `sys_oper_log` VALUES ('447', '字典类型', '1', 'com.carebed.web.controller.manage.SysDictTypeController.addSave()', 'POST', '1', 'admin', null, '/system/dict/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"dictName\" : [ \"订单状态\" ],\r\n  \"dictType\" : [ \"order_status\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 18:39:16');
INSERT INTO `sys_oper_log` VALUES ('448', '字典类型', '2', 'com.carebed.web.controller.manage.SysDictTypeController.editSave()', 'POST', '1', 'admin', null, '/system/dict/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"dictId\" : [ \"120\" ],\r\n  \"dictName\" : [ \"订单状态\" ],\r\n  \"dictType\" : [ \"order_status\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"订单状态\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 18:39:26');
INSERT INTO `sys_oper_log` VALUES ('449', '字典数据', '1', 'com.carebed.web.controller.manage.SysDictDataController.addSave()', 'POST', '1', 'admin', null, '/system/dict/data/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"dictLabel\" : [ \"租用中\" ],\r\n  \"dictValue\" : [ \"00\" ],\r\n  \"dictType\" : [ \"order_status\" ],\r\n  \"cssClass\" : [ \"\" ],\r\n  \"dictSort\" : [ \"1\" ],\r\n  \"listClass\" : [ \"\" ],\r\n  \"isDefault\" : [ \"Y\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 18:40:00');
INSERT INTO `sys_oper_log` VALUES ('450', '字典数据', '1', 'com.carebed.web.controller.manage.SysDictDataController.addSave()', 'POST', '1', 'admin', null, '/system/dict/data/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"dictLabel\" : [ \"已完成\" ],\r\n  \"dictValue\" : [ \"01\" ],\r\n  \"dictType\" : [ \"order_status\" ],\r\n  \"cssClass\" : [ \"\" ],\r\n  \"dictSort\" : [ \"2\" ],\r\n  \"listClass\" : [ \"\" ],\r\n  \"isDefault\" : [ \"Y\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-17 18:40:12');
INSERT INTO `sys_oper_log` VALUES ('451', '医生', '1', 'com.carebed.web.controller.business.TDoctorController.addSave()', 'POST', '1', 'admin', null, '/business/doctor/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"hospitalName\" : [ \"天坛医院\" ],\r\n  \"doctorName\" : [ \"张晓\" ],\r\n  \"cardNo\" : [ \"123456789012345678\" ],\r\n  \"phone\" : [ \"15011071933\" ],\r\n  \"department\" : [ \"呼吸科\" ],\r\n  \"major\" : [ \"手术\" ],\r\n  \"title\" : [ \"主任医生\" ],\r\n  \"remark\" : [ \"阿斯蒂芬\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 09:06:31');
INSERT INTO `sys_oper_log` VALUES ('452', '干系组', '1', 'com.carebed.web.controller.business.TStakeholderGroupController.addSave()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"groupName\" : [ \"测试服收工时费\" ],\r\n  \"remark\" : [ \"111\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 09:10:05');
INSERT INTO `sys_oper_log` VALUES ('453', '医生', '2', 'com.carebed.web.controller.business.TDoctorController.editSave()', 'POST', '1', 'admin', null, '/business/doctor/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"3\" ],\r\n  \"hospitalName\" : [ \"天坛医院\" ],\r\n  \"doctorName\" : [ \"张晓\" ],\r\n  \"cardNo\" : [ \"123456789012345678\" ],\r\n  \"phone\" : [ \"15011071933\" ],\r\n  \"department\" : [ \"呼吸科\" ],\r\n  \"major\" : [ \"手术\" ],\r\n  \"title\" : [ \"主任医生\" ],\r\n  \"remark\" : [ \"阿斯蒂芬2213\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 09:10:09');
INSERT INTO `sys_oper_log` VALUES ('454', '干系组', '2', 'com.carebed.web.controller.business.TStakeholderGroupController.editSave()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"5\" ],\r\n  \"groupName\" : [ \"测试服收工时费\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 09:10:13');
INSERT INTO `sys_oper_log` VALUES ('455', '干系组', '3', 'com.carebed.web.controller.business.TStakeholderGroupController.remove()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/remove', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"ids\" : [ \"5\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 09:11:46');
INSERT INTO `sys_oper_log` VALUES ('456', '干系组', '3', 'com.carebed.web.controller.business.TStakeholderGroupController.remove()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/remove', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"ids\" : [ \"1\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 09:32:26');
INSERT INTO `sys_oper_log` VALUES ('457', '字典类型', '1', 'com.carebed.web.controller.manage.SysDictTypeController.addSave()', 'POST', '1', 'admin', null, '/system/dict/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"dictName\" : [ \"问题状态\" ],\r\n  \"dictType\" : [ \"question_status\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"问题反馈状态\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 09:57:14');
INSERT INTO `sys_oper_log` VALUES ('458', '字典数据', '1', 'com.carebed.web.controller.manage.SysDictDataController.addSave()', 'POST', '1', 'admin', null, '/system/dict/data/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"dictLabel\" : [ \"待处理\" ],\r\n  \"dictValue\" : [ \"00\" ],\r\n  \"dictType\" : [ \"question_status\" ],\r\n  \"cssClass\" : [ \"\" ],\r\n  \"dictSort\" : [ \"1\" ],\r\n  \"listClass\" : [ \"\" ],\r\n  \"isDefault\" : [ \"Y\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 09:57:31');
INSERT INTO `sys_oper_log` VALUES ('459', '字典数据', '1', 'com.carebed.web.controller.manage.SysDictDataController.addSave()', 'POST', '1', 'admin', null, '/system/dict/data/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"dictLabel\" : [ \"已处理\" ],\r\n  \"dictValue\" : [ \"01\" ],\r\n  \"dictType\" : [ \"question_status\" ],\r\n  \"cssClass\" : [ \"\" ],\r\n  \"dictSort\" : [ \"2\" ],\r\n  \"listClass\" : [ \"\" ],\r\n  \"isDefault\" : [ \"Y\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 09:57:44');
INSERT INTO `sys_oper_log` VALUES ('460', '陪护床', '2', 'com.carebed.web.controller.business.TCotController.editSave()', 'POST', '1', 'admin', null, '/business/cot/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"cotNo\" : [ \"BN00001\" ],\r\n  \"status\" : [ \"02\" ],\r\n  \"remark\" : [ \"安抚\" ]\r\n}', '{\r\n  \"msg\" : \"操作失败\",\r\n  \"code\" : 500\r\n}', '0', null, '2020-06-18 10:14:30');
INSERT INTO `sys_oper_log` VALUES ('461', '陪护床', '2', 'com.carebed.web.controller.business.TCotController.editSave()', 'POST', '1', 'admin', null, '/business/cot/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"cotNo\" : [ \"BN00003\" ],\r\n  \"status\" : [ \"03\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作失败\",\r\n  \"code\" : 500\r\n}', '0', null, '2020-06-18 10:14:56');
INSERT INTO `sys_oper_log` VALUES ('462', '陪护床', '2', 'com.carebed.web.controller.business.TCotController.editSave()', 'POST', '1', 'admin', null, '/business/cot/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"5\" ],\r\n  \"cotNo\" : [ \"BN00003\" ],\r\n  \"status\" : [ \"02\" ],\r\n  \"remark\" : [ \"阿斯蒂芬\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 10:15:42');
INSERT INTO `sys_oper_log` VALUES ('463', '字典数据', '1', 'com.carebed.web.controller.manage.SysDictDataController.addSave()', 'POST', '1', 'admin', null, '/system/dict/data/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"dictLabel\" : [ \"已作废\" ],\r\n  \"dictValue\" : [ \"04\" ],\r\n  \"dictType\" : [ \"cot_status\" ],\r\n  \"cssClass\" : [ \"\" ],\r\n  \"dictSort\" : [ \"5\" ],\r\n  \"listClass\" : [ \"\" ],\r\n  \"isDefault\" : [ \"Y\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 10:17:00');
INSERT INTO `sys_oper_log` VALUES ('464', '陪护床', '2', 'com.carebed.web.controller.business.TCotController.editSave()', 'POST', '1', 'admin', null, '/business/cot/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"3\" ],\r\n  \"cotNo\" : [ \"BN00001\" ],\r\n  \"status\" : [ \"04\" ],\r\n  \"remark\" : [ \"撒旦法\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 10:17:21');
INSERT INTO `sys_oper_log` VALUES ('465', '陪护床', '2', 'com.carebed.web.controller.business.TCotController.editSave()', 'POST', '1', 'admin', null, '/business/cot/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"3\" ],\r\n  \"cotNo\" : [ \"BN00001\" ],\r\n  \"status\" : [ \"04\" ],\r\n  \"remark\" : [ \"答复\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 10:18:07');
INSERT INTO `sys_oper_log` VALUES ('466', '陪护床', '2', 'com.carebed.web.controller.business.TCotController.editSave()', 'POST', '1', 'admin', null, '/business/cot/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"3\" ],\r\n  \"cotNo\" : [ \"BN00001\" ],\r\n  \"status\" : [ \"04\" ],\r\n  \"remark\" : [ \"撒旦法\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 10:18:37');
INSERT INTO `sys_oper_log` VALUES ('467', '陪护床', '2', 'com.carebed.web.controller.business.TCotController.editSave()', 'POST', '1', 'admin', null, '/business/cot/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"3\" ],\r\n  \"cotNo\" : [ \"BN00001\" ],\r\n  \"status\" : [ \"04\" ],\r\n  \"remark\" : [ \"撒旦法\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 10:19:04');
INSERT INTO `sys_oper_log` VALUES ('468', '陪护床', '2', 'com.carebed.web.controller.business.TCotController.editSave()', 'POST', '1', 'admin', null, '/business/cot/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"3\" ],\r\n  \"cotNo\" : [ \"BN00001\" ],\r\n  \"status\" : [ \"04\" ],\r\n  \"remark\" : [ \"大师傅\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 10:19:47');
INSERT INTO `sys_oper_log` VALUES ('469', '陪护床', '3', 'com.carebed.web.controller.business.TCotController.remove()', 'POST', '1', 'admin', null, '/business/cot/remove', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"ids\" : [ \"1\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 10:46:41');
INSERT INTO `sys_oper_log` VALUES ('470', '陪护床', '3', 'com.carebed.web.controller.business.TCotController.remove()', 'POST', '1', 'admin', null, '/business/cot/remove', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"ids\" : [ \"5,6\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 10:46:54');
INSERT INTO `sys_oper_log` VALUES ('471', '陪护床', '1', 'com.carebed.web.controller.business.TCotController.addSave()', 'POST', '1', 'admin', null, '/business/cot/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"cotNo\" : [ \"BN00001\" ],\r\n  \"status\" : [ \"02\" ],\r\n  \"remark\" : [ \"sdaf\" ]\r\n}', 'null', '1', 'org.springframework.jdbc.BadSqlGrammarException: \r\n### Error querying database.  Cause: java.sql.SQLSyntaxErrorException: Unknown column \'cotNo\' in \'where clause\'\r\n### The error may exist in file [D:\\iesco\\carebed-parent\\carebed-service\\system-service\\target\\classes\\mapper\\business\\TCotMapper.xml]\r\n### The error may involve com.carebed.business.mapper.TCotMapper.selectTCotByCotNo-Inline\r\n### The error occurred while setting parameters\r\n### SQL: select id, cot_no, member_name, stakeholder_group_id, status,del_sta, create_time, create_by,remark from t_cot               where cotNo = ?\r\n### Cause: java.sql.SQLSyntaxErrorException: Unknown column \'cotNo\' in \'where clause\'\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: Unknown column \'cotNo\' in \'where clause\'\r\norg.springframework.jdbc.BadSqlGrammarException: \r\n### Error querying database.  Cause: java.sql.SQLSyntaxErrorException: Unknown column \'cotNo\' in \'where clause\'\r\n### The error may exist in file [D:\\iesco\\carebed-parent\\carebed-service\\system-service\\target\\classes\\mapper\\business\\TCotMapper.xml]\r\n### The error may involve com.carebed.business.mapper.TCotMapper.selectTCotByCotNo-Inline\r\n### The error occurred while setting parameters\r\n### SQL: select id, cot_no, member_name, stakeholder_group_id, status,del_sta, create_time, create_by,remark from t_cot               where cotNo = ?\r\n### Cause: java.sql.SQLSyntaxErrorException: Unknown column \'cotNo\' in \'where clause\'\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: Unknown column \'cotNo\' in \'where clause\'\r\n	at org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator.doTranslate(SQLErrorCodeSQLExceptionTranslator.java:234)\r\n	at org.springframework.jdbc.support.AbstractFallbackSQLExceptionTranslator.translate(AbstractFallbackSQLExceptionTranslator.java:72)\r\n	at org.mybatis.spring.MyBatisExceptionTranslator.translateExceptionIfPossible(MyBatisExceptionTranslator.java:73)\r\n	at org.mybatis.spring.SqlSes', '2020-06-18 10:47:20');
INSERT INTO `sys_oper_log` VALUES ('472', '陪护床', '1', 'com.carebed.web.controller.business.TCotController.addSave()', 'POST', '1', 'admin', null, '/business/cot/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"cotNo\" : [ \"BN00001\" ],\r\n  \"status\" : [ \"02\" ],\r\n  \"remark\" : [ \"sdaf\" ]\r\n}', '{\r\n  \"msg\" : \"陪护床号已存在，请确认\",\r\n  \"code\" : 500\r\n}', '0', null, '2020-06-18 10:48:27');
INSERT INTO `sys_oper_log` VALUES ('473', '陪护床', '1', 'com.carebed.web.controller.business.TCotController.addSave()', 'POST', '1', 'admin', null, '/business/cot/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"cotNo\" : [ \"BN00002\" ],\r\n  \"status\" : [ \"02\" ],\r\n  \"remark\" : [ \"sdaf\" ]\r\n}', '{\r\n  \"msg\" : \"陪护床号已存在，请确认\",\r\n  \"code\" : 500\r\n}', '0', null, '2020-06-18 10:48:32');
INSERT INTO `sys_oper_log` VALUES ('474', '陪护床', '1', 'com.carebed.web.controller.business.TCotController.addSave()', 'POST', '1', 'admin', null, '/business/cot/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"cotNo\" : [ \"BN00003\" ],\r\n  \"status\" : [ \"02\" ],\r\n  \"remark\" : [ \"sdaf\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 10:48:36');
INSERT INTO `sys_oper_log` VALUES ('475', '陪护床', '5', 'com.carebed.web.controller.business.TCotController.export()', 'POST', '1', 'admin', null, '/business/cot/export', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"checkBoxIds\" : [ \"7\" ],\r\n  \"cotNo\" : [ \"\" ],\r\n  \"groupName\" : [ \"\" ],\r\n  \"status\" : [ \"\" ],\r\n  \"orderByColumn\" : [ \"\" ],\r\n  \"isAsc\" : [ \"asc\" ]\r\n}', '{\r\n  \"msg\" : \"cca3ab8d-f4be-4825-a4ae-bfa3d809932c_cot.xlsx\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 10:48:52');
INSERT INTO `sys_oper_log` VALUES ('476', '医生', '1', 'com.carebed.web.controller.business.TDoctorController.addSave()', 'POST', '1', 'admin', null, '/business/doctor/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"hospitalName\" : [ \"aew\" ],\r\n  \"doctorName\" : [ \"sd\" ],\r\n  \"cardNo\" : [ \"123456789012345678\" ],\r\n  \"phone\" : [ \"15011071933\" ],\r\n  \"department\" : [ \"1\" ],\r\n  \"major\" : [ \"234\" ],\r\n  \"title\" : [ \"234\" ],\r\n  \"remark\" : [ \"324\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 10:53:41');
INSERT INTO `sys_oper_log` VALUES ('477', '医生', '1', 'com.carebed.web.controller.business.TDoctorController.addSave()', 'POST', '1', 'admin', null, '/business/doctor/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"hospitalName\" : [ \"海淀医院\" ],\r\n  \"doctorName\" : [ \"王淼\" ],\r\n  \"cardNo\" : [ \"123456789012345679\" ],\r\n  \"phone\" : [ \"15011071934\" ],\r\n  \"department\" : [ \"儿科\" ],\r\n  \"major\" : [ \"儿科\" ],\r\n  \"title\" : [ \"主任\" ],\r\n  \"remark\" : [ \"大师傅\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 11:28:17');
INSERT INTO `sys_oper_log` VALUES ('478', '医生', '2', 'com.carebed.web.controller.business.TDoctorController.editSave()', 'POST', '1', 'admin', null, '/business/doctor/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"5\" ],\r\n  \"hospitalName\" : [ \"海淀医院\" ],\r\n  \"doctorName\" : [ \"王淼\" ],\r\n  \"cardNo\" : [ \"123456789012345677\" ],\r\n  \"phone\" : [ \"15011071935\" ],\r\n  \"department\" : [ \"儿科\" ],\r\n  \"major\" : [ \"儿科\" ],\r\n  \"title\" : [ \"主任\" ],\r\n  \"remark\" : [ \"大师傅\" ]\r\n}', 'null', '1', 'org.springframework.jdbc.BadSqlGrammarException: \r\n### Error querying database.  Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \' \n          and status = \'1\' \n         \n          and phone = \'15011071935\'\' at line 3\r\n### The error may exist in file [D:\\iesco\\carebed-parent\\carebed-service\\system-service\\target\\classes\\mapper\\business\\TDoctorMapper.xml]\r\n### The error may involve com.carebed.business.mapper.TDoctorMapper.checkDoctorUnique-Inline\r\n### The error occurred while setting parameters\r\n### SQL: select count(1) from t_doctor          where 1=1          and id != ?,            and status = ?                      and phone = ?\r\n### Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \' \n          and status = \'1\' \n         \n          and phone = \'15011071935\'\' at line 3\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \' \n          and status = \'1\' \n         \n          and phone = \'15011071935\'\' at line 3\r\norg.springframework.jdbc.BadSqlGrammarException: \r\n### Error querying database.  Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \' \n          and status = \'1\' \n         \n          and phone = \'15011071935\'\' at line 3\r\n### The error may exist in file [D:\\iesco\\carebed-parent\\carebed-service\\system-service\\target\\classes\\mapper\\business\\TDoctorMapper.xml]\r\n### The error may involve com.carebed.business.mapper.TDoctorMapper.checkDoctorUnique-Inline\r\n### The error occurred while setting parameters\r\n### SQL: select count(1) from t_doctor', '2020-06-18 11:28:50');
INSERT INTO `sys_oper_log` VALUES ('479', '医生', '2', 'com.carebed.web.controller.business.TDoctorController.editSave()', 'POST', '1', 'admin', null, '/business/doctor/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"5\" ],\r\n  \"hospitalName\" : [ \"海淀医院\" ],\r\n  \"doctorName\" : [ \"王淼\" ],\r\n  \"cardNo\" : [ \"123456789012345677\" ],\r\n  \"phone\" : [ \"15011071935\" ],\r\n  \"department\" : [ \"儿科\" ],\r\n  \"major\" : [ \"儿科\" ],\r\n  \"title\" : [ \"主任\" ],\r\n  \"remark\" : [ \"大师傅\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 11:30:18');
INSERT INTO `sys_oper_log` VALUES ('480', '医生', '5', 'com.carebed.web.controller.business.TDoctorController.export()', 'POST', '1', 'admin', null, '/business/doctor/export', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"checkBoxIds\" : [ \"5\" ],\r\n  \"doctorName\" : [ \"\" ],\r\n  \"phone\" : [ \"\" ],\r\n  \"department\" : [ \"\" ],\r\n  \"orderByColumn\" : [ \"\" ],\r\n  \"isAsc\" : [ \"asc\" ]\r\n}', '{\r\n  \"msg\" : \"ed1b3db4-f339-4933-a8d2-8eb419cdbc77_doctor.xlsx\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 11:30:57');
INSERT INTO `sys_oper_log` VALUES ('481', '医生', '3', 'com.carebed.web.controller.business.TDoctorController.remove()', 'POST', '1', 'admin', null, '/business/doctor/remove', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"ids\" : [ \"5\" ]\r\n}', 'null', '1', 'org.springframework.jdbc.BadSqlGrammarException: \r\n### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'=\'0\' where id in \n         (  \n            \'5\'\n         )\' at line 1\r\n### The error may involve com.carebed.business.mapper.TDoctorMapper.deleteTDoctorByIds-Inline\r\n### The error occurred while setting parameters\r\n### SQL: update t_doctor status=\'0\' where id in           (               ?          )\r\n### Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'=\'0\' where id in \n         (  \n            \'5\'\n         )\' at line 1\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'=\'0\' where id in \n         (  \n            \'5\'\n         )\' at line 1\r\norg.springframework.jdbc.BadSqlGrammarException: \r\n### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'=\'0\' where id in \n         (  \n            \'5\'\n         )\' at line 1\r\n### The error may involve com.carebed.business.mapper.TDoctorMapper.deleteTDoctorByIds-Inline\r\n### The error occurred while setting parameters\r\n### SQL: update t_doctor status=\'0\' where id in           (               ?          )\r\n### Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'=\'0\' where id in \n         (  \n            \'5\'\n         )\' at line 1\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: You have an error in your S', '2020-06-18 11:37:17');
INSERT INTO `sys_oper_log` VALUES ('482', '医生', '3', 'com.carebed.web.controller.business.TDoctorController.remove()', 'POST', '1', 'admin', null, '/business/doctor/remove', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"ids\" : [ \"5\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 11:38:19');
INSERT INTO `sys_oper_log` VALUES ('483', '医生', '3', 'com.carebed.web.controller.business.TDoctorController.remove()', 'POST', '1', 'admin', null, '/business/doctor/remove', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"ids\" : [ \"3,4\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 11:38:25');
INSERT INTO `sys_oper_log` VALUES ('484', '医生', '5', 'com.carebed.web.controller.business.TDoctorController.export()', 'POST', '1', 'admin', null, '/business/doctor/export', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"checkBoxIds\" : [ \"1,2\" ],\r\n  \"doctorName\" : [ \"\" ],\r\n  \"phone\" : [ \"\" ],\r\n  \"department\" : [ \"\" ],\r\n  \"orderByColumn\" : [ \"\" ],\r\n  \"isAsc\" : [ \"asc\" ]\r\n}', '{\r\n  \"msg\" : \"159b9d99-b3ed-434d-8f79-d15f24b92f08_doctor.xlsx\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 11:38:28');
INSERT INTO `sys_oper_log` VALUES ('485', '医生', '5', 'com.carebed.web.controller.business.TDoctorController.export()', 'POST', '1', 'admin', null, '/business/doctor/export', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"checkBoxIds\" : [ \"1\" ],\r\n  \"doctorName\" : [ \"\" ],\r\n  \"phone\" : [ \"\" ],\r\n  \"department\" : [ \"\" ],\r\n  \"orderByColumn\" : [ \"\" ],\r\n  \"isAsc\" : [ \"asc\" ]\r\n}', '{\r\n  \"msg\" : \"5522b2b3-28b2-4708-baef-b386235b281a_doctor.xlsx\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 11:40:04');
INSERT INTO `sys_oper_log` VALUES ('486', '医生', '1', 'com.carebed.web.controller.business.TDoctorController.addSave()', 'POST', '1', 'admin', null, '/business/doctor/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"hospitalName\" : [ \"海淀医院\" ],\r\n  \"doctorName\" : [ \"王淼\" ],\r\n  \"cardNo\" : [ \"123456789012345677\" ],\r\n  \"phone\" : [ \"15011071935\" ],\r\n  \"department\" : [ \"水电费\" ],\r\n  \"major\" : [ \"撒旦法\" ],\r\n  \"title\" : [ \"是的发生\" ],\r\n  \"remark\" : [ \"撒答复水电费\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 11:42:30');
INSERT INTO `sys_oper_log` VALUES ('487', '医生', '1', 'com.carebed.web.controller.business.TDoctorController.addSave()', 'POST', '1', 'admin', null, '/business/doctor/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"hospitalName\" : [ \"海淀医院\" ],\r\n  \"doctorName\" : [ \"王淼\" ],\r\n  \"cardNo\" : [ \"123456789012345677\" ],\r\n  \"phone\" : [ \"15011071935\" ],\r\n  \"department\" : [ \"大师傅\" ],\r\n  \"major\" : [ \"士大夫\" ],\r\n  \"title\" : [ \"大凤飞飞\" ],\r\n  \"remark\" : [ \"大封禅大典\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 11:43:30');
INSERT INTO `sys_oper_log` VALUES ('488', '岗位管理', '5', 'com.carebed.web.controller.manage.SysPostController.export()', 'POST', '1', 'admin', null, '/system/post/export', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"checkBoxIds\" : [ \"8\" ],\r\n  \"postCode\" : [ \"\" ],\r\n  \"postName\" : [ \"\" ],\r\n  \"status\" : [ \"\" ],\r\n  \"orderByColumn\" : [ \"postSort\" ],\r\n  \"isAsc\" : [ \"asc\" ]\r\n}', '{\r\n  \"msg\" : \"f2087fd7-feae-444b-8d4a-b6ae94face72_岗位数据.xlsx\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 13:26:48');
INSERT INTO `sys_oper_log` VALUES ('489', '菜单管理', '1', 'com.carebed.web.controller.manage.SysMenuController.addSave()', 'POST', '1', 'admin', null, '/system/menu/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"分润管理\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"7\" ],\r\n  \"icon\" : [ \"fa fa-archive\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 13:31:03');
INSERT INTO `sys_oper_log` VALUES ('490', '菜单管理', '1', 'com.carebed.web.controller.manage.SysMenuController.addSave()', 'POST', '1', 'admin', null, '/system/menu/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"parentId\" : [ \"156\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"分润管理\" ],\r\n  \"url\" : [ \"business/profitSharing\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:profitSharing:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 13:31:58');
INSERT INTO `sys_oper_log` VALUES ('491', '菜单管理', '1', 'com.carebed.web.controller.manage.SysMenuController.addSave()', 'POST', '1', 'admin', null, '/system/menu/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"parentId\" : [ \"157\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"查询\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:profitSharing:list\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 13:32:23');
INSERT INTO `sys_oper_log` VALUES ('492', '菜单管理', '1', 'com.carebed.web.controller.manage.SysMenuController.addSave()', 'POST', '1', 'admin', null, '/system/menu/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"parentId\" : [ \"157\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"导出\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:profitSharing:export\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 13:32:48');
INSERT INTO `sys_oper_log` VALUES ('493', '菜单管理', '1', 'com.carebed.web.controller.manage.SysMenuController.addSave()', 'POST', '1', 'admin', null, '/system/menu/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"parentId\" : [ \"156\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"分润统计\" ],\r\n  \"url\" : [ \"business/profitSharingCensus\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business/profitSharingCensus:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 13:34:54');
INSERT INTO `sys_oper_log` VALUES ('494', '菜单管理', '1', 'com.carebed.web.controller.manage.SysMenuController.addSave()', 'POST', '1', 'admin', null, '/system/menu/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"parentId\" : [ \"160\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"查询\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:profitSharingCensus:list\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 13:35:26');
INSERT INTO `sys_oper_log` VALUES ('495', '菜单管理', '1', 'com.carebed.web.controller.manage.SysMenuController.addSave()', 'POST', '1', 'admin', null, '/system/menu/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"parentId\" : [ \"160\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"导出\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:profitSharingCensus:export\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 13:35:49');
INSERT INTO `sys_oper_log` VALUES ('496', '菜单管理', '2', 'com.carebed.web.controller.manage.SysMenuController.editSave()', 'POST', '1', 'admin', null, '/system/menu/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"menuId\" : [ \"160\" ],\r\n  \"parentId\" : [ \"156\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"分润统计\" ],\r\n  \"url\" : [ \"business/profitSharingCensus\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"business:profitSharingCensus:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 13:36:04');
INSERT INTO `sys_oper_log` VALUES ('497', '干系组管理-医生绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.needBoundDoctorAll()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/needBoundDoctorAll', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"4\" ],\r\n  \"doctorIds\" : [ \"2,1,5,4\" ]\r\n}', 'null', '1', 'org.springframework.jdbc.BadSqlGrammarException: \r\n### Error querying database.  Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'from t_stakeholder_group_doctor where doctor_id in\n         (  \n            \'2\'\n\' at line 1\r\n### The error may exist in file [E:\\iysk-project-svn-idea\\carebed-parent\\carebed-service\\system-service\\target\\classes\\mapper\\business\\TStakeholderGroupDoctorMapper.xml]\r\n### The error may involve com.carebed.business.mapper.TStakeholderGroupDoctorMapper.findSGDByDoctorIds-Inline\r\n### The error occurred while setting parameters\r\n### SQL: select from t_stakeholder_group_doctor where doctor_id in          (               ?          ,              ?          ,              ?          ,              ?          )\r\n### Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'from t_stakeholder_group_doctor where doctor_id in\n         (  \n            \'2\'\n\' at line 1\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'from t_stakeholder_group_doctor where doctor_id in\n         (  \n            \'2\'\n\' at line 1\r\norg.springframework.jdbc.BadSqlGrammarException: \r\n### Error querying database.  Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'from t_stakeholder_group_doctor where doctor_id in\n         (  \n            \'2\'\n\' at line 1\r\n### The error may exist in file [E:\\iysk-project-svn-idea\\carebed-parent\\carebed-service\\system-service\\target\\classes\\mapper\\business\\TStakeholderGroupDoctorMapper.xml]\r\n### The error may involve com.carebed.', '2020-06-18 13:44:07');
INSERT INTO `sys_oper_log` VALUES ('498', '干系组管理-医生绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.needBoundDoctorAll()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/needBoundDoctorAll', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"4\" ],\r\n  \"doctorIds\" : [ \"2,1\" ]\r\n}', 'null', '1', 'org.springframework.jdbc.BadSqlGrammarException: \r\n### Error querying database.  Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'from t_stakeholder_group_doctor where doctor_id in\n         (  \n            \'2\'\n\' at line 1\r\n### The error may exist in file [E:\\iysk-project-svn-idea\\carebed-parent\\carebed-service\\system-service\\target\\classes\\mapper\\business\\TStakeholderGroupDoctorMapper.xml]\r\n### The error may involve com.carebed.business.mapper.TStakeholderGroupDoctorMapper.findSGDByDoctorIds-Inline\r\n### The error occurred while setting parameters\r\n### SQL: select from t_stakeholder_group_doctor where doctor_id in          (               ?          ,              ?          )\r\n### Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'from t_stakeholder_group_doctor where doctor_id in\n         (  \n            \'2\'\n\' at line 1\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'from t_stakeholder_group_doctor where doctor_id in\n         (  \n            \'2\'\n\' at line 1\r\norg.springframework.jdbc.BadSqlGrammarException: \r\n### Error querying database.  Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'from t_stakeholder_group_doctor where doctor_id in\n         (  \n            \'2\'\n\' at line 1\r\n### The error may exist in file [E:\\iysk-project-svn-idea\\carebed-parent\\carebed-service\\system-service\\target\\classes\\mapper\\business\\TStakeholderGroupDoctorMapper.xml]\r\n### The error may involve com.carebed.business.mapper.TStakeholderGroupDoctorMapper.findSG', '2020-06-18 13:46:45');
INSERT INTO `sys_oper_log` VALUES ('499', '问题', '2', 'com.carebed.web.controller.business.TQuestionController.editSave()', 'POST', '1', 'admin', null, '/business/question/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"questionIdea\" : [ \"好大的问题大声道\" ],\r\n  \"status\" : [ \"01\" ],\r\n  \"cotStatus\" : [ \"00\" ],\r\n  \"handlePlan\" : [ \"使劲修呢\" ]\r\n}', '{\r\n  \"msg\" : \"操作失败\",\r\n  \"code\" : 500\r\n}', '0', null, '2020-06-18 13:58:16');
INSERT INTO `sys_oper_log` VALUES ('500', '问题', '2', 'com.carebed.web.controller.business.TQuestionController.editSave()', 'POST', '1', 'admin', null, '/business/question/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"cotNo\" : [ \"BN00001\" ],\r\n  \"orderNo\" : [ \"2\" ],\r\n  \"phone\" : [ \"13333333333\" ],\r\n  \"address\" : [ \"北京市\" ],\r\n  \"questionIdea\" : [ \"好大的问题\" ],\r\n  \"status\" : [ \"01\" ],\r\n  \"cotStatus\" : [ \"03\" ],\r\n  \"handlePlan\" : [ \"使劲修呢\" ]\r\n}', '{\r\n  \"msg\" : \"操作失败\",\r\n  \"code\" : 500\r\n}', '0', null, '2020-06-18 14:01:20');
INSERT INTO `sys_oper_log` VALUES ('501', '问题', '2', 'com.carebed.web.controller.business.TQuestionController.editSave()', 'POST', '1', 'admin', null, '/business/question/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"cotNo\" : [ \"BN00001\" ],\r\n  \"orderNo\" : [ \"2\" ],\r\n  \"phone\" : [ \"13333333333\" ],\r\n  \"address\" : [ \"北京市\" ],\r\n  \"questionIdea\" : [ \"好大的问题\" ],\r\n  \"status\" : [ \"01\" ],\r\n  \"cotStatus\" : [ \"03\" ],\r\n  \"handlePlan\" : [ \"使劲修呢\" ]\r\n}', '{\r\n  \"msg\" : \"操作失败\",\r\n  \"code\" : 500\r\n}', '0', null, '2020-06-18 14:01:48');
INSERT INTO `sys_oper_log` VALUES ('502', '问题', '2', 'com.carebed.web.controller.business.TQuestionController.editSave()', 'POST', '1', 'admin', null, '/business/question/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"3\" ],\r\n  \"cotNo\" : [ \"BN00001\" ],\r\n  \"stakeholderGroupId\" : [ \"2\" ],\r\n  \"phone\" : [ \"13333333333\" ],\r\n  \"address\" : [ \"北京市\" ],\r\n  \"questionIdea\" : [ \"好大的问题\" ],\r\n  \"status\" : [ \"01\" ],\r\n  \"cotStatus\" : [ \"00\" ],\r\n  \"handlePlan\" : [ \"使劲修呢\" ]\r\n}', '{\r\n  \"msg\" : \"操作失败\",\r\n  \"code\" : 500\r\n}', '0', null, '2020-06-18 14:07:38');
INSERT INTO `sys_oper_log` VALUES ('503', '问题', '2', 'com.carebed.web.controller.business.TQuestionController.editSave()', 'POST', '1', 'admin', null, '/business/question/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"3\" ],\r\n  \"cotNo\" : [ \"BN00001\" ],\r\n  \"stakeholderGroupId\" : [ \"2\" ],\r\n  \"phone\" : [ \"13333333333\" ],\r\n  \"address\" : [ \"北京市\" ],\r\n  \"questionIdea\" : [ \"好大的问题\" ],\r\n  \"status\" : [ \"01\" ],\r\n  \"cotStatus\" : [ \"00\" ],\r\n  \"handlePlan\" : [ \"使劲修呢\" ]\r\n}', '{\r\n  \"msg\" : \"操作失败\",\r\n  \"code\" : 500\r\n}', '0', null, '2020-06-18 14:07:49');
INSERT INTO `sys_oper_log` VALUES ('504', '问题', '2', 'com.carebed.web.controller.business.TQuestionController.editSave()', 'POST', '1', 'admin', null, '/business/question/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"cotId\" : [ \"3\" ],\r\n  \"cotNo\" : [ \"BN00001\" ],\r\n  \"stakeholderGroupId\" : [ \"2\" ],\r\n  \"phone\" : [ \"13333333333\" ],\r\n  \"address\" : [ \"北京市\" ],\r\n  \"questionIdea\" : [ \"好大的问题\" ],\r\n  \"status\" : [ \"01\" ],\r\n  \"cotStatus\" : [ \"00\" ],\r\n  \"handlePlan\" : [ \"使劲修呢\" ]\r\n}', '{\r\n  \"msg\" : \"操作失败\",\r\n  \"code\" : 500\r\n}', '0', null, '2020-06-18 14:08:35');
INSERT INTO `sys_oper_log` VALUES ('505', '问题', '2', 'com.carebed.web.controller.business.TQuestionController.editSave()', 'POST', '1', 'admin', null, '/business/question/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"1\" ],\r\n  \"cotId\" : [ \"3\" ],\r\n  \"cotNo\" : [ \"BN00001\" ],\r\n  \"stakeholderGroupId\" : [ \"2\" ],\r\n  \"phone\" : [ \"13333333333\" ],\r\n  \"address\" : [ \"北京市\" ],\r\n  \"questionIdea\" : [ \"好大的问题\" ],\r\n  \"status\" : [ \"00\" ],\r\n  \"cotStatus\" : [ \"02\" ],\r\n  \"handlePlan\" : [ \"修呢\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 14:09:12');
INSERT INTO `sys_oper_log` VALUES ('506', '问题', '2', 'com.carebed.web.controller.business.TQuestionController.editSave()', 'POST', '1', 'admin', null, '/business/question/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"1\" ],\r\n  \"cotId\" : [ \"3\" ],\r\n  \"cotNo\" : [ \"BN00001\" ],\r\n  \"stakeholderGroupId\" : [ \"2\" ],\r\n  \"phone\" : [ \"13333333333\" ],\r\n  \"address\" : [ \"北京市\" ],\r\n  \"questionIdea\" : [ \"好大的问题\" ],\r\n  \"status\" : [ \"01\" ],\r\n  \"cotStatus\" : [ \"02\" ],\r\n  \"handlePlan\" : [ \"修呢\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 14:11:35');
INSERT INTO `sys_oper_log` VALUES ('507', '问题', '2', 'com.carebed.web.controller.business.TQuestionController.editSave()', 'POST', '1', 'admin', null, '/business/question/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"1\" ],\r\n  \"cotId\" : [ \"3\" ],\r\n  \"cotNo\" : [ \"BN00001\" ],\r\n  \"stakeholderGroupId\" : [ \"2\" ],\r\n  \"phone\" : [ \"13333333333\" ],\r\n  \"address\" : [ \"北京市\" ],\r\n  \"questionIdea\" : [ \"好大的问题\" ],\r\n  \"status\" : [ \"00\" ],\r\n  \"cotStatus\" : [ \"02\" ],\r\n  \"handlePlan\" : [ \"修呢\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 14:11:48');
INSERT INTO `sys_oper_log` VALUES ('508', '干系组管理-医生绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.needBoundDoctorAll()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/needBoundDoctorAll', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"4\" ],\r\n  \"doctorIds\" : [ \"4,2\" ]\r\n}', 'null', '1', 'org.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.executor.ExecutorException: A query was run and no Result Maps were found for the Mapped Statement \'com.carebed.business.mapper.TStakeholderGroupDoctorMapper.findSGDByDoctorIds\'.  It\'s likely that neither a Result Type nor a Result Map was specified.\r\norg.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.executor.ExecutorException: A query was run and no Result Maps were found for the Mapped Statement \'com.carebed.business.mapper.TStakeholderGroupDoctorMapper.findSGDByDoctorIds\'.  It\'s likely that neither a Result Type nor a Result Map was specified.\r\n	at org.mybatis.spring.MyBatisExceptionTranslator.translateExceptionIfPossible(MyBatisExceptionTranslator.java:77)\r\n	at org.mybatis.spring.SqlSessionTemplate$SqlSessionInterceptor.invoke(SqlSessionTemplate.java:446)\r\n	at com.sun.proxy.$Proxy79.selectOne(Unknown Source)\r\n	at org.mybatis.spring.SqlSessionTemplate.selectOne(SqlSessionTemplate.java:166)\r\n	at org.apache.ibatis.binding.MapperMethod.execute(MapperMethod.java:83)\r\n	at org.apache.ibatis.binding.MapperProxy.invoke(MapperProxy.java:59)\r\n	at com.sun.proxy.$Proxy109.findSGDByDoctorIds(Unknown Source)\r\n	at com.carebed.business.service.impl.TStakeholderGroupDoctorServiceImpl.insertTStakeholderGroupDoctors(TStakeholderGroupDoctorServiceImpl.java:117)\r\n	at com.carebed.business.service.impl.TStakeholderGroupDoctorServiceImpl$$FastClassBySpringCGLIB$$4215ffbb.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\r\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:749)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\r\n	at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:294)\r\n	at org.springframework.transaction.interceptor.Transacti', '2020-06-18 14:18:20');
INSERT INTO `sys_oper_log` VALUES ('509', '干系组管理-医生绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.needBoundDoctorAll()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/needBoundDoctorAll', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"4\" ],\r\n  \"doctorIds\" : [ \"4,2\" ]\r\n}', 'null', '1', 'org.springframework.jdbc.BadSqlGrammarException: \r\n### Error updating database.  Cause: java.sql.SQLException: Column count doesn\'t match value count at row 1\r\n### The error may involve com.carebed.business.mapper.TStakeholderGroupDoctorMapper.insertTStakeholderGroupDoctorBatch-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into t_stakeholder_group_doctor             (doctor_id, stakeholder_group_id, create_time)         values          (               (?,?,?)          ,              (?,?,?)          )\r\n### Cause: java.sql.SQLException: Column count doesn\'t match value count at row 1\n; bad SQL grammar []; nested exception is java.sql.SQLException: Column count doesn\'t match value count at row 1\r\norg.springframework.jdbc.BadSqlGrammarException: \r\n### Error updating database.  Cause: java.sql.SQLException: Column count doesn\'t match value count at row 1\r\n### The error may involve com.carebed.business.mapper.TStakeholderGroupDoctorMapper.insertTStakeholderGroupDoctorBatch-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into t_stakeholder_group_doctor             (doctor_id, stakeholder_group_id, create_time)         values          (               (?,?,?)          ,              (?,?,?)          )\r\n### Cause: java.sql.SQLException: Column count doesn\'t match value count at row 1\n; bad SQL grammar []; nested exception is java.sql.SQLException: Column count doesn\'t match value count at row 1\r\n	at org.springframework.jdbc.support.SQLStateSQLExceptionTranslator.doTranslate(SQLStateSQLExceptionTranslator.java:101)\r\n	at org.springframework.jdbc.support.AbstractFallbackSQLExceptionTranslator.translate(AbstractFallbackSQLExceptionTranslator.java:72)\r\n	at org.springframework.jdbc.support.AbstractFallbackSQLExceptionTranslator.translate(AbstractFallbackSQLExceptionTranslator.java:81)\r\n	at org.springframework.jdbc.support.AbstractFallbackSQLExceptionTranslator.translate(AbstractFallbackSQLExceptionTranslator.java:81)\r\n	at org.myb', '2020-06-18 14:20:30');
INSERT INTO `sys_oper_log` VALUES ('510', '分润', '5', 'com.carebed.web.controller.business.TProfitSharingController.export()', 'POST', '1', 'admin', null, '/business/profitSharing/export', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"cotNo\" : [ \"\" ],\r\n  \"doctorId\" : [ \"\" ],\r\n  \"params[beginLeaseEndTime]\" : [ \"\" ],\r\n  \"params[endLeaseEndTime]\" : [ \"\" ],\r\n  \"orderByColumn\" : [ \"\" ],\r\n  \"isAsc\" : [ \"asc\" ]\r\n}', '{\r\n  \"msg\" : \"80aaa94a-6740-47c8-a073-07d3575b9bd1_profitSharing.xlsx\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 14:22:51');
INSERT INTO `sys_oper_log` VALUES ('511', '干系组管理-医生绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.needBoundDoctorAll()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/needBoundDoctorAll', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"4\" ],\r\n  \"doctorIds\" : [ \"5,4\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 14:23:41');
INSERT INTO `sys_oper_log` VALUES ('512', '分润', '5', 'com.carebed.web.controller.business.TProfitSharingController.export()', 'POST', '1', 'admin', null, '/business/profitSharing/export', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"checkBoxIds\" : [ \"1\" ],\r\n  \"doctorName\" : [ \"\" ],\r\n  \"phone\" : [ \"\" ],\r\n  \"cotNo\" : [ \"\" ],\r\n  \"orderByColumn\" : [ \"\" ],\r\n  \"isAsc\" : [ \"asc\" ]\r\n}', '{\r\n  \"msg\" : \"60c8c474-6521-4d3b-ae56-257679d68865_profitSharing.xlsx\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 14:29:16');
INSERT INTO `sys_oper_log` VALUES ('513', '问题', '2', 'com.carebed.web.controller.business.TQuestionController.editSave()', 'POST', '1', 'admin', null, '/business/question/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"1\" ],\r\n  \"cotId\" : [ \"3\" ],\r\n  \"cotNo\" : [ \"BN00001\" ],\r\n  \"stakeholderGroupId\" : [ \"2\" ],\r\n  \"phone\" : [ \"13333333333\" ],\r\n  \"address\" : [ \"北京市\" ],\r\n  \"questionIdea\" : [ \"好大的问题\" ],\r\n  \"status\" : [ \"01\" ],\r\n  \"cotStatus\" : [ \"02\" ],\r\n  \"handlePlan\" : [ \"修呢\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 14:44:46');
INSERT INTO `sys_oper_log` VALUES ('514', '干系组管理-批量取消医生绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.cancelAllDoctor()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/cancelAllDoctor', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"4\" ],\r\n  \"doctorIds\" : [ \"5,4\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 15:06:01');
INSERT INTO `sys_oper_log` VALUES ('515', '问题', '2', 'com.carebed.web.controller.business.TQuestionController.editSave()', 'POST', '1', 'admin', null, '/business/question/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"1\" ],\r\n  \"cotId\" : [ \"3\" ],\r\n  \"cotNo\" : [ \"BN00001\" ],\r\n  \"stakeholderGroupId\" : [ \"2\" ],\r\n  \"phone\" : [ \"13333333333\" ],\r\n  \"address\" : [ \"北京市\" ],\r\n  \"questionIdea\" : [ \"好大的问题\" ],\r\n  \"status\" : [ \"01\" ],\r\n  \"cotStatus\" : [ \"02\" ],\r\n  \"handlePlan\" : [ \"修呢\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 15:06:46');
INSERT INTO `sys_oper_log` VALUES ('516', '问题', '2', 'com.carebed.web.controller.business.TQuestionController.editSave()', 'POST', '1', 'admin', null, '/business/question/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"1\" ],\r\n  \"cotId\" : [ \"3\" ],\r\n  \"cotNo\" : [ \"BN00001\" ],\r\n  \"stakeholderGroupId\" : [ \"2\" ],\r\n  \"phone\" : [ \"13333333333\" ],\r\n  \"address\" : [ \"北京市\" ],\r\n  \"questionIdea\" : [ \"好大的问题\" ],\r\n  \"status\" : [ \"00\" ],\r\n  \"cotStatus\" : [ \"02\" ],\r\n  \"handlePlan\" : [ \"修呢\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 15:09:07');
INSERT INTO `sys_oper_log` VALUES ('517', '问题', '2', 'com.carebed.web.controller.business.TQuestionController.editSave()', 'POST', '1', 'admin', null, '/business/question/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"1\" ],\r\n  \"cotId\" : [ \"3\" ],\r\n  \"cotNo\" : [ \"BN00001\" ],\r\n  \"stakeholderGroupId\" : [ \"2\" ],\r\n  \"phone\" : [ \"13333333333\" ],\r\n  \"address\" : [ \"北京市\" ],\r\n  \"questionIdea\" : [ \"好大的问题\" ],\r\n  \"status\" : [ \"01\" ],\r\n  \"cotStatus\" : [ \"02\" ],\r\n  \"handlePlan\" : [ \"修呢\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 15:09:33');
INSERT INTO `sys_oper_log` VALUES ('518', '分润', '5', 'com.carebed.web.controller.business.TProfitSharingController.export()', 'POST', '1', 'admin', null, '/business/profitSharing/export', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"checkBoxIds\" : [ \"\" ],\r\n  \"doctorName\" : [ \"\" ],\r\n  \"phone\" : [ \"\" ],\r\n  \"cotNo\" : [ \"\" ],\r\n  \"startTime\" : [ \"\" ],\r\n  \"endTime\" : [ \"2020-06-19\" ],\r\n  \"orderByColumn\" : [ \"\" ],\r\n  \"isAsc\" : [ \"asc\" ]\r\n}', '{\r\n  \"msg\" : \"1c528648-3bfc-49ea-9587-5adebe598815_profitSharing.xlsx\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 15:10:19');
INSERT INTO `sys_oper_log` VALUES ('519', '干系组管理-取消医生绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.cancelDoctor()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/cancelDoctor', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"2\" ],\r\n  \"doctorId\" : [ \"3\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 15:10:35');
INSERT INTO `sys_oper_log` VALUES ('520', '干系组管理-批量取消医生绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.cancelAllDoctor()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/cancelAllDoctor', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"4\" ],\r\n  \"doctorIds\" : [ \"5,4\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 15:10:51');
INSERT INTO `sys_oper_log` VALUES ('521', '干系组管理-批量取消医生绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.cancelAllDoctor()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/cancelAllDoctor', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"4\" ],\r\n  \"doctorIds\" : [ \"5,4\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 15:12:34');
INSERT INTO `sys_oper_log` VALUES ('522', '菜单管理', '2', 'com.carebed.web.controller.manage.SysMenuController.editSave()', 'POST', '1', 'admin', null, '/system/menu/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"menuId\" : [ \"100\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"用户管理\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"fa fa-user\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 15:13:16');
INSERT INTO `sys_oper_log` VALUES ('523', '干系组管理-批量取消医生绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.cancelAllDoctor()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/cancelAllDoctor', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"4\" ],\r\n  \"doctorIds\" : [ \"5,4\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 15:13:37');
INSERT INTO `sys_oper_log` VALUES ('524', '干系组管理-医生绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.needBoundDoctorAll()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/needBoundDoctorAll', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"4\" ],\r\n  \"doctorIds\" : [ \"4,2,5,3,1\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 15:13:41');
INSERT INTO `sys_oper_log` VALUES ('525', '干系组管理-取消医生绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.cancelDoctor()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/cancelDoctor', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"4\" ],\r\n  \"doctorId\" : [ \"2\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 15:13:45');
INSERT INTO `sys_oper_log` VALUES ('526', '菜单管理', '2', 'com.carebed.web.controller.manage.SysMenuController.editSave()', 'POST', '1', 'admin', null, '/system/menu/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"menuId\" : [ \"136\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"陪护床管理\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"fa fa-bed\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 15:18:17');
INSERT INTO `sys_oper_log` VALUES ('527', '干系组管理-批量取消医生绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.cancelAllDoctor()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/cancelAllDoctor', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"4\" ],\r\n  \"doctorIds\" : [ \"4,5,3,1\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 15:19:12');
INSERT INTO `sys_oper_log` VALUES ('528', '干系组管理-医生绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.needBoundDoctorAll()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/needBoundDoctorAll', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"4\" ],\r\n  \"doctorIds\" : [ \"4,2,5,3,1\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 15:19:20');
INSERT INTO `sys_oper_log` VALUES ('529', '菜单管理', '2', 'com.carebed.web.controller.manage.SysMenuController.editSave()', 'POST', '1', 'admin', null, '/system/menu/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"menuId\" : [ \"134\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"干系组管理\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"6\" ],\r\n  \"icon\" : [ \"fa fa-group\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 15:19:43');
INSERT INTO `sys_oper_log` VALUES ('530', '菜单管理', '2', 'com.carebed.web.controller.manage.SysMenuController.editSave()', 'POST', '1', 'admin', null, '/system/menu/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"menuId\" : [ \"149\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"医生管理\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"fa fa-plus-square\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 15:23:48');
INSERT INTO `sys_oper_log` VALUES ('531', '菜单管理', '2', 'com.carebed.web.controller.manage.SysMenuController.editSave()', 'POST', '1', 'admin', null, '/system/menu/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"menuId\" : [ \"145\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"订单管理\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"4\" ],\r\n  \"icon\" : [ \"fa fa-bars\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 15:24:05');
INSERT INTO `sys_oper_log` VALUES ('532', '菜单管理', '2', 'com.carebed.web.controller.manage.SysMenuController.editSave()', 'POST', '1', 'admin', null, '/system/menu/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"menuId\" : [ \"139\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"问题反馈\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"5\" ],\r\n  \"icon\" : [ \"fa fa-question-circle\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 15:24:55');
INSERT INTO `sys_oper_log` VALUES ('533', '菜单管理', '2', 'com.carebed.web.controller.manage.SysMenuController.editSave()', 'POST', '1', 'admin', null, '/system/menu/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"menuId\" : [ \"156\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"分润管理\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"7\" ],\r\n  \"icon\" : [ \"fa fa-jpy\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 15:26:08');
INSERT INTO `sys_oper_log` VALUES ('534', '菜单管理', '2', 'com.carebed.web.controller.manage.SysMenuController.editSave()', 'POST', '1', 'admin', null, '/system/menu/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"menuId\" : [ \"100\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"用户管理\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"fa fa-user-circle\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 15:28:25');
INSERT INTO `sys_oper_log` VALUES ('535', '菜单管理', '2', 'com.carebed.web.controller.manage.SysMenuController.editSave()', 'POST', '1', 'admin', null, '/system/menu/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"menuId\" : [ \"145\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"订单管理\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"4\" ],\r\n  \"icon\" : [ \"fa fa-cart-arrow-down\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 15:30:19');
INSERT INTO `sys_oper_log` VALUES ('536', '干系组管理-医生维护', '5', 'com.carebed.web.controller.business.TStakeholderGroupController.exportAuthDoctor()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/exportAuthDoctor', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"params[sgId]\" : [ \"4\" ],\r\n  \"doctorName\" : [ \"\" ],\r\n  \"phone\" : [ \"\" ],\r\n  \"department\" : [ \"\" ],\r\n  \"orderByColumn\" : [ \"\" ],\r\n  \"isAsc\" : [ \"asc\" ]\r\n}', '{\r\n  \"msg\" : \"730021e0-2dcd-4d4a-b45a-2cf03f062f9b_绑定医生.xlsx\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 15:30:34');
INSERT INTO `sys_oper_log` VALUES ('537', '干系组管理-医生维护', '5', 'com.carebed.web.controller.business.TStakeholderGroupController.exportAuthDoctor()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/exportAuthDoctor', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"params[sgId]\" : [ \"4\" ],\r\n  \"doctorName\" : [ \"\" ],\r\n  \"phone\" : [ \"\" ],\r\n  \"department\" : [ \"\" ],\r\n  \"orderByColumn\" : [ \"\" ],\r\n  \"isAsc\" : [ \"asc\" ]\r\n}', '{\r\n  \"msg\" : \"0325fa5b-3f60-4ad5-b8dc-ecaecd0bc8a6_绑定医生.xlsx\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 15:31:07');
INSERT INTO `sys_oper_log` VALUES ('538', '问题', '2', 'com.carebed.web.controller.business.TQuestionController.editSave()', 'POST', '1', 'admin', null, '/business/question/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"1\" ],\r\n  \"cotId\" : [ \"3\" ],\r\n  \"cotNo\" : [ \"BN00001\" ],\r\n  \"stakeholderGroupId\" : [ \"2\" ],\r\n  \"phone\" : [ \"13333333333\" ],\r\n  \"address\" : [ \"北京市\" ],\r\n  \"questionIdea\" : [ \"好大的问题\" ],\r\n  \"status\" : [ \"01\" ],\r\n  \"cotStatus\" : [ \"00\" ],\r\n  \"handlePlan\" : [ \"修呢\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 15:32:35');
INSERT INTO `sys_oper_log` VALUES ('539', '订单', '5', 'com.carebed.web.controller.business.TOrderController.export()', 'POST', '1', 'admin', null, '/business/order/export', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"checkBoxIds\" : [ \"\" ],\r\n  \"orderNo\" : [ \"\" ],\r\n  \"cotNo\" : [ \"\" ],\r\n  \"status\" : [ \"\" ],\r\n  \"orderByColumn\" : [ \"\" ],\r\n  \"isAsc\" : [ \"asc\" ]\r\n}', '{\r\n  \"msg\" : \"1f627df8-e934-4612-bf79-d7d0382608b7_order.xlsx\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 15:36:55');
INSERT INTO `sys_oper_log` VALUES ('540', '订单', '5', 'com.carebed.web.controller.business.TOrderController.export()', 'POST', '1', 'admin', null, '/business/order/export', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"checkBoxIds\" : [ \"\" ],\r\n  \"orderNo\" : [ \"\" ],\r\n  \"cotNo\" : [ \"\" ],\r\n  \"status\" : [ \"\" ],\r\n  \"orderByColumn\" : [ \"\" ],\r\n  \"isAsc\" : [ \"asc\" ]\r\n}', '{\r\n  \"msg\" : \"52657565-c57a-4653-af8a-aab34eed32ec_订单信息.xlsx\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 15:41:20');
INSERT INTO `sys_oper_log` VALUES ('541', '订单', '5', 'com.carebed.web.controller.business.TOrderController.export()', 'POST', '1', 'admin', null, '/business/order/export', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"checkBoxIds\" : [ \"\" ],\r\n  \"orderNo\" : [ \"\" ],\r\n  \"cotNo\" : [ \"\" ],\r\n  \"status\" : [ \"\" ],\r\n  \"orderByColumn\" : [ \"\" ],\r\n  \"isAsc\" : [ \"asc\" ]\r\n}', '{\r\n  \"msg\" : \"88c4e95a-55c8-47ec-b1c9-058c94b326a6_订单信息.xlsx\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 15:45:11');
INSERT INTO `sys_oper_log` VALUES ('542', '干系组管理-医生维护', '5', 'com.carebed.web.controller.business.TStakeholderGroupController.exportAuthDoctor()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/exportAuthDoctor', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"params[sgId]\" : [ \"4\" ],\r\n  \"doctorName\" : [ \"\" ],\r\n  \"phone\" : [ \"\" ],\r\n  \"department\" : [ \"\" ],\r\n  \"orderByColumn\" : [ \"\" ],\r\n  \"isAsc\" : [ \"asc\" ]\r\n}', '{\r\n  \"msg\" : \"341acd52-3962-4740-87fc-0ce02aaa2d40_绑定医生.xlsx\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 15:50:25');
INSERT INTO `sys_oper_log` VALUES ('543', '干系组管理-医生维护', '5', 'com.carebed.web.controller.business.TStakeholderGroupController.exportAuthDoctor()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/exportAuthDoctor', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"params[sgId]\" : [ \"4\" ],\r\n  \"doctorName\" : [ \"\" ],\r\n  \"phone\" : [ \"\" ],\r\n  \"department\" : [ \"\" ],\r\n  \"orderByColumn\" : [ \"\" ],\r\n  \"isAsc\" : [ \"asc\" ]\r\n}', '{\r\n  \"msg\" : \"04582f0d-22f9-4a9b-949b-21538c3c6b24_绑定医生.xlsx\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 15:51:10');
INSERT INTO `sys_oper_log` VALUES ('544', '分润', '5', 'com.carebed.web.controller.business.TProfitSharingCensusController.export()', 'POST', '1', 'admin', null, '/business/profitSharingCensus/export', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"checkBoxIds\" : [ \"\" ],\r\n  \"doctorName\" : [ \"\" ],\r\n  \"phone\" : [ \"\" ],\r\n  \"hospitalName\" : [ \"\" ],\r\n  \"startTime\" : [ \"2020-06-18\" ],\r\n  \"endTime\" : [ \"2020-06-19\" ],\r\n  \"orderByColumn\" : [ \"\" ],\r\n  \"isAsc\" : [ \"asc\" ]\r\n}', '{\r\n  \"msg\" : \"fd06da84-8507-4960-ba00-5a499524bfbb_profitSharingCensus.xlsx\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 15:51:48');
INSERT INTO `sys_oper_log` VALUES ('545', '干系组管理-取消医生绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.cancelDoctor()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/cancelDoctor', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"4\" ],\r\n  \"doctorId\" : [ \"4\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 15:52:30');
INSERT INTO `sys_oper_log` VALUES ('546', '分润', '5', 'com.carebed.web.controller.business.TProfitSharingCensusController.export()', 'POST', '1', 'admin', null, '/business/profitSharingCensus/export', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"checkBoxIds\" : [ \"\" ],\r\n  \"doctorName\" : [ \"\" ],\r\n  \"phone\" : [ \"\" ],\r\n  \"hospitalName\" : [ \"\" ],\r\n  \"startTime\" : [ \"2020-06-18\" ],\r\n  \"endTime\" : [ \"2020-06-19\" ],\r\n  \"orderByColumn\" : [ \"\" ],\r\n  \"isAsc\" : [ \"asc\" ]\r\n}', '{\r\n  \"msg\" : \"e1aa8002-d341-42d1-b2bb-62d9011933e4_profitSharingCensus.xlsx\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 15:59:12');
INSERT INTO `sys_oper_log` VALUES ('547', '干系组管理-医生维护', '5', 'com.carebed.web.controller.business.TStakeholderGroupController.exportAuthDoctor()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/exportAuthDoctor', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"params[sgId]\" : [ \"4\" ],\r\n  \"doctorName\" : [ \"\" ],\r\n  \"phone\" : [ \"\" ],\r\n  \"department\" : [ \"\" ],\r\n  \"orderByColumn\" : [ \"\" ],\r\n  \"isAsc\" : [ \"asc\" ]\r\n}', '{\r\n  \"msg\" : \"4026e9a8-efa6-475d-b031-eeedf9d1a734_绑定医生.xlsx\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 16:00:19');
INSERT INTO `sys_oper_log` VALUES ('548', '干系组管理-医生维护', '5', 'com.carebed.web.controller.business.TStakeholderGroupController.exportAuthDoctor()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/exportAuthDoctor', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"params[sgId]\" : [ \"4\" ],\r\n  \"doctorName\" : [ \"\" ],\r\n  \"phone\" : [ \"\" ],\r\n  \"department\" : [ \"\" ],\r\n  \"orderByColumn\" : [ \"\" ],\r\n  \"isAsc\" : [ \"asc\" ]\r\n}', '{\r\n  \"msg\" : \"8a009c6d-f40a-4b57-9173-55eb4557765e_绑定医生.xlsx\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 16:00:55');
INSERT INTO `sys_oper_log` VALUES ('549', '分润', '5', 'com.carebed.web.controller.business.TProfitSharingCensusController.export()', 'POST', '1', 'admin', null, '/business/profitSharingCensus/export', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"checkBoxIds\" : [ \"\" ],\r\n  \"doctorName\" : [ \"\" ],\r\n  \"phone\" : [ \"\" ],\r\n  \"hospitalName\" : [ \"\" ],\r\n  \"startTime\" : [ \"\" ],\r\n  \"endTime\" : [ \"\" ],\r\n  \"orderByColumn\" : [ \"\" ],\r\n  \"isAsc\" : [ \"asc\" ]\r\n}', '{\r\n  \"msg\" : \"61a77be5-f6fa-422a-941b-b2f40642f6a7_profitSharingCensus.xlsx\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 16:01:20');
INSERT INTO `sys_oper_log` VALUES ('550', '干系组管理-医生维护', '5', 'com.carebed.web.controller.business.TStakeholderGroupController.exportAuthDoctor()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/exportAuthDoctor', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"checkBoxIds\" : [ \"11,12\" ],\r\n  \"params[sgId]\" : [ \"4\" ],\r\n  \"doctorName\" : [ \"\" ],\r\n  \"phone\" : [ \"\" ],\r\n  \"department\" : [ \"\" ],\r\n  \"orderByColumn\" : [ \"\" ],\r\n  \"isAsc\" : [ \"asc\" ]\r\n}', '{\r\n  \"msg\" : \"82c622be-6810-4160-b4f5-354e43a19ca7_绑定医生.xlsx\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 16:05:36');
INSERT INTO `sys_oper_log` VALUES ('551', '干系组管理-医生绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.needBoundDoctorAll()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/needBoundDoctorAll', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"4\" ],\r\n  \"doctorIds\" : [ \"4\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 16:18:21');
INSERT INTO `sys_oper_log` VALUES ('552', '干系组管理-取消医生绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.cancelDoctor()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/cancelDoctor', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"4\" ],\r\n  \"doctorId\" : [ \"5\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 16:18:25');
INSERT INTO `sys_oper_log` VALUES ('553', '干系组管理-取消医生绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.cancelDoctor()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/cancelDoctor', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"4\" ],\r\n  \"doctorId\" : [ \"3\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 16:18:27');
INSERT INTO `sys_oper_log` VALUES ('554', '干系组管理-批量取消医生绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.cancelAllDoctor()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/cancelAllDoctor', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"4\" ],\r\n  \"doctorIds\" : [ \"2,1,4\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 16:18:30');
INSERT INTO `sys_oper_log` VALUES ('555', '干系组管理-医生绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.needBoundDoctorAll()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/needBoundDoctorAll', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"4\" ],\r\n  \"doctorIds\" : [ \"2,5,1,4,3\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 16:18:35');
INSERT INTO `sys_oper_log` VALUES ('556', '分润', '5', 'com.carebed.web.controller.business.TProfitSharingCensusController.export()', 'POST', '1', 'admin', null, '/business/profitSharingCensus/export', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"checkBoxIds\" : [ \"\" ],\r\n  \"doctorName\" : [ \"\" ],\r\n  \"phone\" : [ \"\" ],\r\n  \"hospitalName\" : [ \"\" ],\r\n  \"startTime\" : [ \"\" ],\r\n  \"endTime\" : [ \"2020-06-19\" ],\r\n  \"orderByColumn\" : [ \"\" ],\r\n  \"isAsc\" : [ \"asc\" ]\r\n}', '{\r\n  \"msg\" : \"145c5942-7219-435c-9983-644484077b22_profitSharingCensus.xlsx\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 16:19:59');
INSERT INTO `sys_oper_log` VALUES ('557', '干系组管理-批量取消医生绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.cancelAllDoctor()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/cancelAllDoctor', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"4\" ],\r\n  \"doctorIds\" : [ \"2\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 16:20:31');
INSERT INTO `sys_oper_log` VALUES ('558', '干系组管理-医生维护', '5', 'com.carebed.web.controller.business.TStakeholderGroupController.exportAuthDoctor()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/exportAuthDoctor', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"checkBoxIds\" : [ \"17\" ],\r\n  \"params[sgId]\" : [ \"4\" ],\r\n  \"doctorName\" : [ \"\" ],\r\n  \"phone\" : [ \"\" ],\r\n  \"department\" : [ \"\" ],\r\n  \"orderByColumn\" : [ \"\" ],\r\n  \"isAsc\" : [ \"asc\" ]\r\n}', '{\r\n  \"msg\" : \"b70201bf-a463-4cc8-9723-19b7f25fa6de_绑定医生.xlsx\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 16:20:37');
INSERT INTO `sys_oper_log` VALUES ('559', '分润', '5', 'com.carebed.web.controller.business.TProfitSharingCensusController.export()', 'POST', '1', 'admin', null, '/business/profitSharingCensus/export', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"checkBoxIds\" : [ \"1\" ],\r\n  \"doctorName\" : [ \"\" ],\r\n  \"phone\" : [ \"\" ],\r\n  \"hospitalName\" : [ \"\" ],\r\n  \"startTime\" : [ \"\" ],\r\n  \"endTime\" : [ \"\" ],\r\n  \"orderByColumn\" : [ \"\" ],\r\n  \"isAsc\" : [ \"asc\" ]\r\n}', '{\r\n  \"msg\" : \"cec16623-bd4b-4dd5-acb7-5498ea38e0ab_profitSharingCensus.xlsx\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 16:21:26');
INSERT INTO `sys_oper_log` VALUES ('560', '问题', '2', 'com.carebed.web.controller.business.TQuestionController.editSave()', 'POST', '1', 'admin', null, '/business/question/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"1\" ],\r\n  \"cotId\" : [ \"3\" ],\r\n  \"cotNo\" : [ \"BN00001\" ],\r\n  \"stakeholderGroupId\" : [ \"2\" ],\r\n  \"phone\" : [ \"13333333333\" ],\r\n  \"address\" : [ \"北京市\" ],\r\n  \"questionIdea\" : [ \"好大的问题\" ],\r\n  \"status\" : [ \"01\" ],\r\n  \"cotStatus\" : [ \"03\" ],\r\n  \"handlePlan\" : [ \"修呢\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 16:24:58');
INSERT INTO `sys_oper_log` VALUES ('561', '分润', '5', 'com.carebed.web.controller.business.TProfitSharingController.export()', 'POST', '1', 'admin', null, '/business/profitSharing/export', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"checkBoxIds\" : [ \"1\" ],\r\n  \"doctorId\" : [ \"1\" ],\r\n  \"doctorName\" : [ \"\" ],\r\n  \"phone\" : [ \"\" ],\r\n  \"cotNo\" : [ \"\" ],\r\n  \"startTime\" : [ \"\" ],\r\n  \"endTime\" : [ \"\" ],\r\n  \"orderByColumn\" : [ \"\" ],\r\n  \"isAsc\" : [ \"asc\" ]\r\n}', '{\r\n  \"msg\" : \"0fc6d680-cd1f-4de4-98fc-68e0af173756_profitSharing.xlsx\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 16:40:35');
INSERT INTO `sys_oper_log` VALUES ('562', '干系组管理-批量取消医生绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.cancelAllDoctor()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/cancelAllDoctor', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"4\" ],\r\n  \"doctorIds\" : [ \"5,1,4,3\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 17:18:47');
INSERT INTO `sys_oper_log` VALUES ('563', '干系组管理-医生绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.needBoundDoctorAll()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/needBoundDoctorAll', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"4\" ],\r\n  \"doctorIds\" : [ \"2,5,1,4,3\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 17:18:52');
INSERT INTO `sys_oper_log` VALUES ('564', '干系组管理-取消医生绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.cancelDoctor()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/cancelDoctor', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"4\" ],\r\n  \"doctorId\" : [ \"2\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 17:18:55');
INSERT INTO `sys_oper_log` VALUES ('565', '干系组管理-取消医生绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.cancelDoctor()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/cancelDoctor', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"4\" ],\r\n  \"doctorId\" : [ \"1\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 17:18:56');
INSERT INTO `sys_oper_log` VALUES ('566', '干系组管理-医生维护', '5', 'com.carebed.web.controller.business.TStakeholderGroupController.exportAuthDoctor()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/exportAuthDoctor', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"checkBoxIds\" : [ \"\" ],\r\n  \"params[sgId]\" : [ \"4\" ],\r\n  \"doctorName\" : [ \"\" ],\r\n  \"phone\" : [ \"\" ],\r\n  \"department\" : [ \"\" ],\r\n  \"orderByColumn\" : [ \"\" ],\r\n  \"isAsc\" : [ \"asc\" ]\r\n}', '{\r\n  \"msg\" : \"cbe1e7dc-f9bd-4e94-b3ca-d9103c50af01_绑定医生.xlsx\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 17:18:59');
INSERT INTO `sys_oper_log` VALUES ('567', '干系组管理-医生维护', '5', 'com.carebed.web.controller.business.TStakeholderGroupController.exportAuthDoctor()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/exportAuthDoctor', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"checkBoxIds\" : [ \"22,24\" ],\r\n  \"params[sgId]\" : [ \"4\" ],\r\n  \"doctorName\" : [ \"\" ],\r\n  \"phone\" : [ \"\" ],\r\n  \"department\" : [ \"\" ],\r\n  \"orderByColumn\" : [ \"\" ],\r\n  \"isAsc\" : [ \"asc\" ]\r\n}', '{\r\n  \"msg\" : \"d7c3bcb0-de1d-4b30-9a59-5d02840dc487_绑定医生.xlsx\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 17:19:14');
INSERT INTO `sys_oper_log` VALUES ('568', '干系组管理-陪护床绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.needBindingCotAll()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/needBindingCotAll', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"4\" ],\r\n  \"cotIds\" : [ \"2,4,7\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 17:56:26');
INSERT INTO `sys_oper_log` VALUES ('569', '干系组管理-解除陪护床绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.cancelCot()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/cancelCot', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"4\" ],\r\n  \"cotId\" : [ \"2\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 18:15:18');
INSERT INTO `sys_oper_log` VALUES ('570', '干系组管理-解除陪护床绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.cancelCot()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/cancelCot', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"4\" ],\r\n  \"cotId\" : [ \"4\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 18:15:23');
INSERT INTO `sys_oper_log` VALUES ('571', '干系组管理-解除陪护床绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.cancelCot()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/cancelCot', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"4\" ],\r\n  \"cotId\" : [ \"7\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 18:15:27');
INSERT INTO `sys_oper_log` VALUES ('572', '干系组管理-陪护床绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.needBindingCotAll()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/needBindingCotAll', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"4\" ],\r\n  \"cotIds\" : [ \"2,4,7\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 18:15:31');
INSERT INTO `sys_oper_log` VALUES ('573', '干系组管理-批量解除陪护床绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.cancelAllCot()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/cancelAllCot', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"4\" ],\r\n  \"cotIds\" : [ \"2,4,7\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-18 18:15:35');
INSERT INTO `sys_oper_log` VALUES ('574', '干系组管理-陪护床绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.needBindingCotAll()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/needBindingCotAll', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"4\" ],\r\n  \"cotIds\" : [ \"2,4,7\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 09:12:17');
INSERT INTO `sys_oper_log` VALUES ('575', '干系组管理-解除陪护床绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.cancelCot()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/cancelCot', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"4\" ],\r\n  \"cotId\" : [ \"2\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 09:12:21');
INSERT INTO `sys_oper_log` VALUES ('576', '干系组管理-批量解除陪护床绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.cancelAllCot()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/cancelAllCot', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"4\" ],\r\n  \"cotIds\" : [ \"4,7\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 09:12:26');
INSERT INTO `sys_oper_log` VALUES ('577', '干系组管理-陪护床绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.needBindingCotAll()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/needBindingCotAll', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"4\" ],\r\n  \"cotIds\" : [ \"2,4,7\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 09:20:00');
INSERT INTO `sys_oper_log` VALUES ('578', '干系组管理-陪护床维护-导出', '5', 'com.carebed.web.controller.business.TStakeholderGroupController.exportAuthCot()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/exportAuthCot', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"checkBoxIds\" : [ \"\" ],\r\n  \"params[sgId]\" : [ \"4\" ],\r\n  \"cotNo\" : [ \"\" ],\r\n  \"status\" : [ \"\" ],\r\n  \"orderByColumn\" : [ \"\" ],\r\n  \"isAsc\" : [ \"asc\" ]\r\n}', '{\r\n  \"msg\" : \"e889195c-c0a1-4680-99f8-534385cd6e32_绑定医生.xlsx\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 09:20:22');
INSERT INTO `sys_oper_log` VALUES ('579', '干系组管理-陪护床维护-导出', '5', 'com.carebed.web.controller.business.TStakeholderGroupController.exportAuthCot()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/exportAuthCot', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"checkBoxIds\" : [ \"2,4,7\" ],\r\n  \"params[sgId]\" : [ \"4\" ],\r\n  \"cotNo\" : [ \"\" ],\r\n  \"status\" : [ \"\" ],\r\n  \"orderByColumn\" : [ \"\" ],\r\n  \"isAsc\" : [ \"asc\" ]\r\n}', '{\r\n  \"msg\" : \"9da97c49-97ef-4a63-bda0-3f7df90dd706_绑定医生.xlsx\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 09:20:40');
INSERT INTO `sys_oper_log` VALUES ('580', '干系组管理-陪护床维护-导出', '5', 'com.carebed.web.controller.business.TStakeholderGroupController.exportAuthCot()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/exportAuthCot', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"checkBoxIds\" : [ \"2,4\" ],\r\n  \"params[sgId]\" : [ \"4\" ],\r\n  \"cotNo\" : [ \"\" ],\r\n  \"status\" : [ \"\" ],\r\n  \"orderByColumn\" : [ \"\" ],\r\n  \"isAsc\" : [ \"asc\" ]\r\n}', '{\r\n  \"msg\" : \"f0c8094b-dfd6-4e7e-847a-6e14ef86f2dc_绑定医生.xlsx\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 09:20:52');
INSERT INTO `sys_oper_log` VALUES ('581', '陪护床', '3', 'com.carebed.web.controller.business.TCotController.remove()', 'POST', '1', 'admin', null, '/business/cot/remove', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"ids\" : [ \"14,15,16,17\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 09:22:28');
INSERT INTO `sys_oper_log` VALUES ('582', '干系组管理-解除医生绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.cancelDoctor()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/cancelDoctor', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"4\" ],\r\n  \"doctorId\" : [ \"5\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 09:22:35');
INSERT INTO `sys_oper_log` VALUES ('583', '干系组管理-批量解除医生绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.cancelAllDoctor()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/cancelAllDoctor', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"4\" ],\r\n  \"doctorIds\" : [ \"4,3\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 09:22:39');
INSERT INTO `sys_oper_log` VALUES ('584', '干系组管理-医生绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.needBindingDoctorAll()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/needBindingDoctorAll', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"4\" ],\r\n  \"doctorIds\" : [ \"2,5,1,4,3\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 09:22:43');
INSERT INTO `sys_oper_log` VALUES ('585', '干系组管理-医生维护', '5', 'com.carebed.web.controller.business.TStakeholderGroupController.exportAuthDoctor()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/exportAuthDoctor', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"checkBoxIds\" : [ \"26,27\" ],\r\n  \"params[sgId]\" : [ \"4\" ],\r\n  \"doctorName\" : [ \"\" ],\r\n  \"phone\" : [ \"\" ],\r\n  \"department\" : [ \"\" ],\r\n  \"orderByColumn\" : [ \"\" ],\r\n  \"isAsc\" : [ \"asc\" ]\r\n}', '{\r\n  \"msg\" : \"00a7e369-99d5-4750-b390-5bb038c1a996_绑定医生.xlsx\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 09:23:07');
INSERT INTO `sys_oper_log` VALUES ('586', '干系组医生分润比例', '2', 'com.carebed.web.controller.business.TStakeholderGroupDoctorController.saveShareProportion()', 'POST', '1', 'admin', null, '/business/stakeholderGroupDoctor/saveShareProportion', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"26\" ],\r\n  \"shareProportion\" : [ \"11\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 10:38:58');
INSERT INTO `sys_oper_log` VALUES ('587', '干系组医生分润比例', '2', 'com.carebed.web.controller.business.TStakeholderGroupDoctorController.editShareProportion()', 'POST', '1', 'admin', null, '/business/stakeholderGroupDoctor/editShareProportion', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"27\" ],\r\n  \"shareProportion\" : [ \"100\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 10:51:02');
INSERT INTO `sys_oper_log` VALUES ('588', '干系组医生分润比例', '2', 'com.carebed.web.controller.business.TStakeholderGroupDoctorController.editShareProportion()', 'POST', '1', 'admin', null, '/business/stakeholderGroupDoctor/editShareProportion', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"28\" ],\r\n  \"shareProportion\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 10:51:06');
INSERT INTO `sys_oper_log` VALUES ('589', '干系组医生分润比例', '2', 'com.carebed.web.controller.business.TStakeholderGroupDoctorController.editShareProportion()', 'POST', '1', 'admin', null, '/business/stakeholderGroupDoctor/editShareProportion', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"29\" ],\r\n  \"shareProportion\" : [ \"66\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 10:51:09');
INSERT INTO `sys_oper_log` VALUES ('590', '干系组医生分润比例', '2', 'com.carebed.web.controller.business.TStakeholderGroupDoctorController.editShareProportion()', 'POST', '1', 'admin', null, '/business/stakeholderGroupDoctor/editShareProportion', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"30\" ],\r\n  \"shareProportion\" : [ \"55\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 10:51:12');
INSERT INTO `sys_oper_log` VALUES ('591', '干系组医生分润比例', '2', 'com.carebed.web.controller.business.TStakeholderGroupDoctorController.editShareProportion()', 'POST', '1', 'admin', null, '/business/stakeholderGroupDoctor/editShareProportion', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"27\" ],\r\n  \"shareProportion\" : [ \"22\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 10:58:43');
INSERT INTO `sys_oper_log` VALUES ('592', '干系组医生分润比例', '2', 'com.carebed.web.controller.business.TStakeholderGroupDoctorController.editShareProportion()', 'POST', '1', 'admin', null, '/business/stakeholderGroupDoctor/editShareProportion', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"26\" ],\r\n  \"shareProportion\" : [ \"44\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 10:59:18');
INSERT INTO `sys_oper_log` VALUES ('593', '干系组医生分润比例', '2', 'com.carebed.web.controller.business.TStakeholderGroupDoctorController.editShareProportion()', 'POST', '1', 'admin', null, '/business/stakeholderGroupDoctor/editShareProportion', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"26\" ],\r\n  \"shareProportion\" : [ \"22\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:03:32');
INSERT INTO `sys_oper_log` VALUES ('594', '干系组管理-解除医生绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.cancelDoctor()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/cancelDoctor', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"4\" ],\r\n  \"doctorId\" : [ \"2\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:03:43');
INSERT INTO `sys_oper_log` VALUES ('595', '干系组管理-解除医生绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.cancelDoctor()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/cancelDoctor', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"4\" ],\r\n  \"doctorId\" : [ \"5\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:03:48');
INSERT INTO `sys_oper_log` VALUES ('596', '干系组管理-解除医生绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.cancelDoctor()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/cancelDoctor', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"4\" ],\r\n  \"doctorId\" : [ \"4\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:03:50');
INSERT INTO `sys_oper_log` VALUES ('597', '干系组管理-解除医生绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.cancelDoctor()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/cancelDoctor', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"4\" ],\r\n  \"doctorId\" : [ \"3\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:03:51');
INSERT INTO `sys_oper_log` VALUES ('598', '干系组管理-解除医生绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.cancelDoctor()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/cancelDoctor', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"4\" ],\r\n  \"doctorId\" : [ \"1\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:03:53');
INSERT INTO `sys_oper_log` VALUES ('599', '干系组管理-医生绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.needBindingDoctorAll()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/needBindingDoctorAll', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"4\" ],\r\n  \"doctorIds\" : [ \"3,2,5,1,4\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:03:57');
INSERT INTO `sys_oper_log` VALUES ('600', '干系组医生分润比例', '2', 'com.carebed.web.controller.business.TStakeholderGroupDoctorController.editShareProportion()', 'POST', '1', 'admin', null, '/business/stakeholderGroupDoctor/editShareProportion', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"31\" ],\r\n  \"shareProportion\" : [ \"11\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:04:10');
INSERT INTO `sys_oper_log` VALUES ('601', '干系组医生分润比例', '2', 'com.carebed.web.controller.business.TStakeholderGroupDoctorController.editShareProportion()', 'POST', '1', 'admin', null, '/business/stakeholderGroupDoctor/editShareProportion', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"32\" ],\r\n  \"shareProportion\" : [ \"11\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:04:12');
INSERT INTO `sys_oper_log` VALUES ('602', '干系组医生分润比例', '2', 'com.carebed.web.controller.business.TStakeholderGroupDoctorController.editShareProportion()', 'POST', '1', 'admin', null, '/business/stakeholderGroupDoctor/editShareProportion', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"33\" ],\r\n  \"shareProportion\" : [ \"1\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:04:14');
INSERT INTO `sys_oper_log` VALUES ('603', '干系组医生分润比例', '2', 'com.carebed.web.controller.business.TStakeholderGroupDoctorController.editShareProportion()', 'POST', '1', 'admin', null, '/business/stakeholderGroupDoctor/editShareProportion', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"34\" ],\r\n  \"shareProportion\" : [ \"1\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:04:17');
INSERT INTO `sys_oper_log` VALUES ('604', '干系组医生分润比例', '2', 'com.carebed.web.controller.business.TStakeholderGroupDoctorController.editShareProportion()', 'POST', '1', 'admin', null, '/business/stakeholderGroupDoctor/editShareProportion', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"35\" ],\r\n  \"shareProportion\" : [ \"1\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:04:18');
INSERT INTO `sys_oper_log` VALUES ('605', '干系组管理-批量解除医生绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.cancelAllDoctor()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/cancelAllDoctor', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"4\" ],\r\n  \"doctorIds\" : [ \"3,2,5,1,4\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:04:23');
INSERT INTO `sys_oper_log` VALUES ('606', '干系组管理-医生绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.needBindingDoctorAll()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/needBindingDoctorAll', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"4\" ],\r\n  \"doctorIds\" : [ \"3,2,5,1,4\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:04:27');
INSERT INTO `sys_oper_log` VALUES ('607', '干系组管理-陪护床绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.needBindingCotAll()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/needBindingCotAll', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"4\" ],\r\n  \"cotIds\" : [ \"8,9,10,11,12,13\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:08:01');
INSERT INTO `sys_oper_log` VALUES ('608', '干系组管理-批量解除陪护床绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.cancelAllCot()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/cancelAllCot', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"4\" ],\r\n  \"cotIds\" : [ \"2,4,7,8,9,10,11,12,13\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:08:27');
INSERT INTO `sys_oper_log` VALUES ('609', '干系组', '1', 'com.carebed.web.controller.business.TStakeholderGroupController.addSave()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"groupName\" : [ \"11111111111111111111\" ],\r\n  \"remark\" : [ \"gdg\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:08:36');
INSERT INTO `sys_oper_log` VALUES ('610', '干系组', '2', 'com.carebed.web.controller.business.TStakeholderGroupController.editSave()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"6\" ],\r\n  \"groupName\" : [ \"sff\" ],\r\n  \"remark\" : [ \"11\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:08:45');
INSERT INTO `sys_oper_log` VALUES ('611', '干系组', '3', 'com.carebed.web.controller.business.TStakeholderGroupController.remove()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/remove', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"ids\" : [ \"4\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:08:58');
INSERT INTO `sys_oper_log` VALUES ('612', '陪护床', '1', 'com.carebed.web.controller.business.TCotController.addSave()', 'POST', '1', 'admin', null, '/business/cot/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"cotNo\" : [ \"BN00009\" ],\r\n  \"status\" : [ \"01\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:11:23');
INSERT INTO `sys_oper_log` VALUES ('613', '陪护床', '1', 'com.carebed.web.controller.business.TCotController.addSave()', 'POST', '1', 'admin', null, '/business/cot/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"cotNo\" : [ \"BN000010\" ],\r\n  \"status\" : [ \"00\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:11:36');
INSERT INTO `sys_oper_log` VALUES ('614', '医生', '1', 'com.carebed.web.controller.business.TDoctorController.addSave()', 'POST', '1', 'admin', null, '/business/doctor/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"hospitalName\" : [ \"F\" ],\r\n  \"doctorName\" : [ \"F\" ],\r\n  \"cardNo\" : [ \"123456789876543234\" ],\r\n  \"phone\" : [ \"13322221331\" ],\r\n  \"department\" : [ \"11\" ],\r\n  \"major\" : [ \"\" ],\r\n  \"title\" : [ \"\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:12:04');
INSERT INTO `sys_oper_log` VALUES ('615', '医生', '1', 'com.carebed.web.controller.business.TDoctorController.addSave()', 'POST', '1', 'admin', null, '/business/doctor/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"hospitalName\" : [ \"FF\" ],\r\n  \"doctorName\" : [ \"FF\" ],\r\n  \"cardNo\" : [ \"123456766554234332\" ],\r\n  \"phone\" : [ \"13324444444\" ],\r\n  \"department\" : [ \"44\" ],\r\n  \"major\" : [ \"\" ],\r\n  \"title\" : [ \"\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:12:18');
INSERT INTO `sys_oper_log` VALUES ('616', '医生', '1', 'com.carebed.web.controller.business.TDoctorController.addSave()', 'POST', '1', 'admin', null, '/business/doctor/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"hospitalName\" : [ \"SFASF\" ],\r\n  \"doctorName\" : [ \"FASDF\" ],\r\n  \"cardNo\" : [ \"123456789876543212\" ],\r\n  \"phone\" : [ \"13322223232\" ],\r\n  \"department\" : [ \"SDFA\" ],\r\n  \"major\" : [ \"\" ],\r\n  \"title\" : [ \"\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:13:09');
INSERT INTO `sys_oper_log` VALUES ('617', '医生', '1', 'com.carebed.web.controller.business.TDoctorController.addSave()', 'POST', '1', 'admin', null, '/business/doctor/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"hospitalName\" : [ \"SFDAF\" ],\r\n  \"doctorName\" : [ \"123456\" ],\r\n  \"cardNo\" : [ \"134234686978877654\" ],\r\n  \"phone\" : [ \"13323333333\" ],\r\n  \"department\" : [ \"FASDF\" ],\r\n  \"major\" : [ \"\" ],\r\n  \"title\" : [ \"\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:13:24');
INSERT INTO `sys_oper_log` VALUES ('618', '医生', '1', 'com.carebed.web.controller.business.TDoctorController.addSave()', 'POST', '1', 'admin', null, '/business/doctor/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"hospitalName\" : [ \"FDSDF\" ],\r\n  \"doctorName\" : [ \"FS\" ],\r\n  \"cardNo\" : [ \"123457543323232323\" ],\r\n  \"phone\" : [ \"13327777777\" ],\r\n  \"department\" : [ \"ddf\" ],\r\n  \"major\" : [ \"\" ],\r\n  \"title\" : [ \"\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:13:39');
INSERT INTO `sys_oper_log` VALUES ('619', '医生', '1', 'com.carebed.web.controller.business.TDoctorController.addSave()', 'POST', '1', 'admin', null, '/business/doctor/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"hospitalName\" : [ \"SDFSDF\" ],\r\n  \"doctorName\" : [ \"DFSDF\" ],\r\n  \"cardNo\" : [ \"123456787654323456\" ],\r\n  \"phone\" : [ \"13322226662\" ],\r\n  \"department\" : [ \"dsfsfd\" ],\r\n  \"major\" : [ \"\" ],\r\n  \"title\" : [ \"\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:13:53');
INSERT INTO `sys_oper_log` VALUES ('620', '医生', '1', 'com.carebed.web.controller.business.TDoctorController.addSave()', 'POST', '1', 'admin', null, '/business/doctor/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"hospitalName\" : [ \"FDFSF\" ],\r\n  \"doctorName\" : [ \"DF\" ],\r\n  \"cardNo\" : [ \"234568787645342312\" ],\r\n  \"phone\" : [ \"15523456789\" ],\r\n  \"department\" : [ \"ddf\" ],\r\n  \"major\" : [ \"\" ],\r\n  \"title\" : [ \"\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:14:16');
INSERT INTO `sys_oper_log` VALUES ('621', '医生', '1', 'com.carebed.web.controller.business.TDoctorController.addSave()', 'POST', '1', 'admin', null, '/business/doctor/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"hospitalName\" : [ \"FDSF\" ],\r\n  \"doctorName\" : [ \"FDF\" ],\r\n  \"cardNo\" : [ \"234678988776533236\" ],\r\n  \"phone\" : [ \"13322221333\" ],\r\n  \"department\" : [ \"2323\" ],\r\n  \"major\" : [ \"\" ],\r\n  \"title\" : [ \"\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:14:29');
INSERT INTO `sys_oper_log` VALUES ('622', '干系组', '1', 'com.carebed.web.controller.business.TStakeholderGroupController.addSave()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"groupName\" : [ \"SDS\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:14:39');
INSERT INTO `sys_oper_log` VALUES ('623', '干系组', '1', 'com.carebed.web.controller.business.TStakeholderGroupController.addSave()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"groupName\" : [ \"FFF\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:14:42');
INSERT INTO `sys_oper_log` VALUES ('624', '干系组', '1', 'com.carebed.web.controller.business.TStakeholderGroupController.addSave()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"groupName\" : [ \"FFF\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:14:46');
INSERT INTO `sys_oper_log` VALUES ('625', '干系组', '1', 'com.carebed.web.controller.business.TStakeholderGroupController.addSave()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"groupName\" : [ \"FFFFF\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:14:49');
INSERT INTO `sys_oper_log` VALUES ('626', '干系组', '1', 'com.carebed.web.controller.business.TStakeholderGroupController.addSave()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"groupName\" : [ \"FFFFFFFF\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:14:55');
INSERT INTO `sys_oper_log` VALUES ('627', '干系组', '1', 'com.carebed.web.controller.business.TStakeholderGroupController.addSave()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"groupName\" : [ \"FFFFFFFFFFFFF\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:14:59');
INSERT INTO `sys_oper_log` VALUES ('628', '干系组', '1', 'com.carebed.web.controller.business.TStakeholderGroupController.addSave()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"groupName\" : [ \"FFFFFFFFFFFFFF\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:15:04');
INSERT INTO `sys_oper_log` VALUES ('629', '干系组', '1', 'com.carebed.web.controller.business.TStakeholderGroupController.addSave()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"groupName\" : [ \"FFFFFFFFFFF\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:15:09');
INSERT INTO `sys_oper_log` VALUES ('630', '干系组管理-陪护床绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.needBindingCotAll()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/needBindingCotAll', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"14\" ],\r\n  \"cotIds\" : [ \"19\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:16:04');
INSERT INTO `sys_oper_log` VALUES ('631', '干系组管理-陪护床绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.needBindingCotAll()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/needBindingCotAll', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"14\" ],\r\n  \"cotIds\" : [ \"2,4,7,8,9,10,11,12,13,18\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:16:07');
INSERT INTO `sys_oper_log` VALUES ('632', '干系组管理-医生绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.needBindingDoctorAll()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/needBindingDoctorAll', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"14\" ],\r\n  \"doctorIds\" : [ \"7,3,10,6,13,2,9,5,12,1,8,4,11\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:16:19');
INSERT INTO `sys_oper_log` VALUES ('633', '干系组医生分润比例', '2', 'com.carebed.web.controller.business.TStakeholderGroupDoctorController.editShareProportion()', 'POST', '1', 'admin', null, '/business/stakeholderGroupDoctor/editShareProportion', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"41\" ],\r\n  \"shareProportion\" : [ \"1\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:35:32');
INSERT INTO `sys_oper_log` VALUES ('634', '干系组', '2', 'com.carebed.web.controller.business.TStakeholderGroupController.editSave()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"9\" ],\r\n  \"groupName\" : [ \"FFF\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:39:40');
INSERT INTO `sys_oper_log` VALUES ('635', '干系组', '2', 'com.carebed.web.controller.business.TStakeholderGroupController.editSave()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"9\" ],\r\n  \"groupName\" : [ \"FFFFFF\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:39:48');
INSERT INTO `sys_oper_log` VALUES ('636', '干系组', '2', 'com.carebed.web.controller.business.TStakeholderGroupController.editSave()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"8\" ],\r\n  \"groupName\" : [ \"FFFFFF\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:40:06');
INSERT INTO `sys_oper_log` VALUES ('637', '干系组', '2', 'com.carebed.web.controller.business.TStakeholderGroupController.editSave()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"9\" ],\r\n  \"groupName\" : [ \"FFFFFF\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:40:39');
INSERT INTO `sys_oper_log` VALUES ('638', '问题', '2', 'com.carebed.web.controller.business.TQuestionController.editSave()', 'POST', '1', 'admin', null, '/business/question/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"1\" ],\r\n  \"cotId\" : [ \"3\" ],\r\n  \"cotNo\" : [ \"BN00001\" ],\r\n  \"stakeholderGroupId\" : [ \"2\" ],\r\n  \"phone\" : [ \"13333333333\" ],\r\n  \"address\" : [ \"北京市\" ],\r\n  \"questionIdea\" : [ \"好大的问题\" ],\r\n  \"status\" : [ \"01\" ],\r\n  \"cotStatus\" : [ \"03\" ],\r\n  \"handlePlan\" : [ \"在修呢\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:41:11');
INSERT INTO `sys_oper_log` VALUES ('639', '干系组', '2', 'com.carebed.web.controller.business.TStakeholderGroupController.editSave()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"9\" ],\r\n  \"groupName\" : [ \"FFFFFF\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:41:47');
INSERT INTO `sys_oper_log` VALUES ('640', '干系组', '2', 'com.carebed.web.controller.business.TStakeholderGroupController.editSave()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"9\" ],\r\n  \"groupName\" : [ \"FFFFFF\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:42:08');
INSERT INTO `sys_oper_log` VALUES ('641', '干系组', '2', 'com.carebed.web.controller.business.TStakeholderGroupController.editSave()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"14\" ],\r\n  \"groupName\" : [ \"FFFFFFFFFFF\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:43:40');
INSERT INTO `sys_oper_log` VALUES ('642', '干系组', '2', 'com.carebed.web.controller.business.TStakeholderGroupController.editSave()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"14\" ],\r\n  \"groupName\" : [ \"FFFFFFFFFFF\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:45:55');
INSERT INTO `sys_oper_log` VALUES ('643', '干系组', '2', 'com.carebed.web.controller.business.TStakeholderGroupController.editSave()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"9\" ],\r\n  \"groupName\" : [ \"FFFFFF\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:46:05');
INSERT INTO `sys_oper_log` VALUES ('644', '干系组', '2', 'com.carebed.web.controller.business.TStakeholderGroupController.editSave()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"8\" ],\r\n  \"groupName\" : [ \"FFFFFF\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:46:15');
INSERT INTO `sys_oper_log` VALUES ('645', '干系组', '2', 'com.carebed.web.controller.business.TStakeholderGroupController.editSave()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"9\" ],\r\n  \"groupName\" : [ \"FFFFFF\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:47:33');
INSERT INTO `sys_oper_log` VALUES ('646', '干系组', '1', 'com.carebed.web.controller.business.TStakeholderGroupController.addSave()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/add', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"groupName\" : [ \"FFFFFssssss\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:52:52');
INSERT INTO `sys_oper_log` VALUES ('647', '干系组', '2', 'com.carebed.web.controller.business.TStakeholderGroupController.editSave()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/edit', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"10\" ],\r\n  \"groupName\" : [ \"FFFFF\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 11:53:58');
INSERT INTO `sys_oper_log` VALUES ('648', '干系组', '3', 'com.carebed.web.controller.business.TStakeholderGroupController.remove()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/remove', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"ids\" : [ \"15,14,13,12,11,10,9,8,7,6\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 13:34:39');
INSERT INTO `sys_oper_log` VALUES ('649', '干系组管理-医生绑定', '4', 'com.carebed.web.controller.business.TStakeholderGroupController.needBindingDoctorAll()', 'POST', '1', 'admin', null, '/business/stakeholderGroup/needBindingDoctorAll', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"sgId\" : [ \"2\" ],\r\n  \"doctorIds\" : [ \"7,3,10,6,13,2,9,5,12,1\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 13:41:34');
INSERT INTO `sys_oper_log` VALUES ('650', '干系组医生分润比例', '2', 'com.carebed.web.controller.business.TStakeholderGroupDoctorController.editShareProportion()', 'POST', '1', 'admin', null, '/business/stakeholderGroupDoctor/editShareProportion', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"54\" ],\r\n  \"shareProportion\" : [ \"11\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 13:41:48');
INSERT INTO `sys_oper_log` VALUES ('651', '干系组医生分润比例', '2', 'com.carebed.web.controller.business.TStakeholderGroupDoctorController.editShareProportion()', 'POST', '1', 'admin', null, '/business/stakeholderGroupDoctor/editShareProportion', '0:0:0:0:0:0:0:1', '内网IP', '{\r\n  \"id\" : [ \"55\" ],\r\n  \"shareProportion\" : [ \"22\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-06-19 13:41:59');

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post` (
  `post_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) NOT NULL COMMENT '岗位名称',
  `post_sort` int(4) NOT NULL COMMENT '显示顺序',
  `role_id` varchar(50) DEFAULT NULL COMMENT '角色权限多个用，隔开',
  `status` char(1) NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='岗位信息表';

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES ('8', '01', '管理员', '1', '1', '0', 'admin', '2020-04-02 11:33:00', '1', '2020-05-27 15:30:13', '管理员');
INSERT INTO `sys_post` VALUES ('14', '02', '业务员', '1', '11', '0', '1', '2020-05-27 15:30:39', '1', '2020-05-27 15:32:10', '业务员');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `status` char(1) NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='角色信息表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员', 'admin', '1', '1', '0', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '管理员');
INSERT INTO `sys_role` VALUES ('11', '业务人员', 'ywry', '2', '1', '0', '0', '1', '2020-05-27 15:31:47', '1', '2020-05-29 10:19:42', '业务人员');

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`,`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色和部门关联表';

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色和菜单关联表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `dept_code` varchar(20) DEFAULT '' COMMENT '机构编码',
  `login_name` varchar(30) NOT NULL COMMENT '登录账号',
  `user_name` varchar(30) DEFAULT '' COMMENT '用户昵称',
  `user_code` varchar(20) DEFAULT '' COMMENT '员工工号',
  `user_card_code` varchar(18) NOT NULL DEFAULT '' COMMENT '员工身份证号',
  `entry_date` datetime DEFAULT NULL COMMENT '入职日期',
  `post_code` varchar(20) NOT NULL COMMENT '岗位代码 01:运营中心经理,02:事业部经理,03:大区经理,04:分公司经理,05:营业部经理,06:团队经理,07:客户经理',
  `level_code` varchar(20) DEFAULT NULL COMMENT '岗位职级代码 00:筹备,01:一级,02:二级,03:三级,04:四级,05:五级,06:高级',
  `recommend_user_id` bigint(20) DEFAULT NULL COMMENT '推荐员工id',
  `recommend_user_name` varchar(30) DEFAULT NULL COMMENT '推荐员工姓名',
  `recommend_user_code` varchar(20) DEFAULT NULL COMMENT '推荐员工工号',
  `recommend_entry_date` datetime DEFAULT NULL COMMENT '推荐人入职日期',
  `user_type` varchar(2) DEFAULT '00' COMMENT '用户类型（00系统用户 01注册用户）',
  `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) DEFAULT '' COMMENT '手机号码',
  `sex` char(1) DEFAULT '0' COMMENT '用户性别（0男 1女）',
  `avatar` varchar(100) DEFAULT '' COMMENT '头像路径',
  `password` varchar(50) DEFAULT '' COMMENT '密码',
  `salt` varchar(20) DEFAULT '' COMMENT '盐加密',
  `status` char(1) DEFAULT '0' COMMENT '帐号状态(0正式,1离职,2试用)',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志(0代表存在,1代表删除)',
  `login_ip` varchar(50) DEFAULT '' COMMENT '最后登陆IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_card_code` (`user_card_code`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', null, '', 'admin', '管理员', '00001', '14243119930213245X', null, '01', '0', '0', null, null, null, '00', 'ry@163.com', 'admin', '1', '', 'be1324016673a0524e14d252a5bd7804', '6c9db9', '0', '0', '0:0:0:0:0:0:0:1', '2020-06-19 18:02:00', 'admin', '2018-03-16 11:33:00', 'ry', '2020-06-19 17:56:16', '管理员');

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`,`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与岗位关联表';

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户和角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for t_cot
-- ----------------------------
DROP TABLE IF EXISTS `t_cot`;
CREATE TABLE `t_cot` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `cot_no` varchar(32) NOT NULL COMMENT '陪护床号',
  `member_name` varchar(64) DEFAULT NULL COMMENT '会员姓名(使用者姓名,微信昵称)',
  `stakeholder_group_id` int(11) DEFAULT NULL COMMENT '干系组Id',
  `status` varchar(2) DEFAULT NULL COMMENT '状态:00:维护中;01:闲置中:02:使用中;03:保修中;',
  `del_sta` char(1) DEFAULT '1' COMMENT '删除状态:0无效;1有效',
  `remark` varchar(225) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(11) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_cot_no` (`cot_no`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='陪护床表';

-- ----------------------------
-- Records of t_cot
-- ----------------------------
INSERT INTO `t_cot` VALUES ('2', '22', '22', null, '01', '1', null, '2020-06-17 16:59:39', '1');
INSERT INTO `t_cot` VALUES ('3', 'BN00001', null, '2', '03', '1', '大师傅', '2020-06-17 17:34:17', null);
INSERT INTO `t_cot` VALUES ('4', 'BN00002', null, null, '01', '1', 'adsf', '2020-06-17 17:34:30', null);
INSERT INTO `t_cot` VALUES ('7', 'BN00003', null, null, '02', '1', 'sdaf', '2020-06-18 10:48:35', null);
INSERT INTO `t_cot` VALUES ('8', '123456', null, null, '00', '1', '样例：导入时，删除样式', '2020-06-19 09:21:53', '1');
INSERT INTO `t_cot` VALUES ('9', 'BN00004', null, null, '00', '1', 'werwe', '2020-06-19 09:21:53', '1');
INSERT INTO `t_cot` VALUES ('10', 'BN00005', null, null, '00', '1', 'werwe', '2020-06-19 09:21:53', '1');
INSERT INTO `t_cot` VALUES ('11', 'BN00006', null, null, '00', '1', 'werwe', '2020-06-19 09:21:53', '1');
INSERT INTO `t_cot` VALUES ('12', 'BN00007', null, null, '00', '1', 'werwe', '2020-06-19 09:21:53', '1');
INSERT INTO `t_cot` VALUES ('13', 'BN00008', null, null, '00', '1', 'werwe', '2020-06-19 09:21:53', '1');
INSERT INTO `t_cot` VALUES ('18', 'BN00009', null, null, '01', '1', null, '2020-06-19 11:17:08', '1');
INSERT INTO `t_cot` VALUES ('19', 'BN000010', null, null, '00', '1', null, '2020-06-19 11:17:20', '1');

-- ----------------------------
-- Table structure for t_doctor
-- ----------------------------
DROP TABLE IF EXISTS `t_doctor`;
CREATE TABLE `t_doctor` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `doctor_name` varchar(32) NOT NULL COMMENT '医生姓名',
  `phone` varchar(11) NOT NULL COMMENT '电话',
  `hospital_name` varchar(96) NOT NULL COMMENT '医院名称',
  `card_no` varchar(18) NOT NULL COMMENT '身份证号',
  `department` varchar(64) NOT NULL COMMENT '科室',
  `major` varchar(120) DEFAULT NULL COMMENT '专业',
  `title` varchar(64) DEFAULT NULL COMMENT '职称',
  `status` char(1) DEFAULT '1' COMMENT '医生状态:0:失效:1:有效;',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(11) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(11) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `Index_name_phone` (`doctor_name`,`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='医生表';

-- ----------------------------
-- Records of t_doctor
-- ----------------------------
INSERT INTO `t_doctor` VALUES ('1', 'ss', '13322221111', '222', '232323232311111111', '1', '1', '1', '1', '1', '2020-06-17 17:06:52', '1', '2020-06-17 17:06:59', '1');
INSERT INTO `t_doctor` VALUES ('2', 'tt', '13311112222', '111', '111111111111111111', '1', '1', '1', '1', '1', '2020-06-17 17:07:37', '1', '2020-06-17 17:07:40', '1');
INSERT INTO `t_doctor` VALUES ('3', '张晓', '15011071933', '天坛医院', '123456789012345678', '呼吸科', '手术', '主任医生', '0', '阿斯蒂芬2213', '2020-06-18 09:06:30', null, '2020-06-18 09:10:07', null);
INSERT INTO `t_doctor` VALUES ('4', 'sd', '15011071933', 'aew', '123456789012345678', '1', '234', '234', '0', '324', '2020-06-18 10:53:39', null, null, null);
INSERT INTO `t_doctor` VALUES ('5', '王淼', '15011071935', '海淀医院', '123456789012345677', '大师傅', '士大夫', '大凤飞飞', '1', '大封禅大典', '2020-06-18 11:28:16', null, '2020-06-18 11:43:29', null);
INSERT INTO `t_doctor` VALUES ('6', 'F', '13322221331', 'F', '123456789876543234', '11', null, null, '1', null, '2020-06-19 11:17:49', null, null, null);
INSERT INTO `t_doctor` VALUES ('7', 'FF', '13324444444', 'FF', '123456766554234332', '44', null, null, '1', null, '2020-06-19 11:18:03', null, null, null);
INSERT INTO `t_doctor` VALUES ('8', 'FASDF', '13322223232', 'SFASF', '123456789876543212', 'SDFA', null, null, '1', null, '2020-06-19 11:18:54', null, null, null);
INSERT INTO `t_doctor` VALUES ('9', '123456', '13323333333', 'SFDAF', '134234686978877654', 'FASDF', null, null, '1', null, '2020-06-19 11:19:09', null, null, null);
INSERT INTO `t_doctor` VALUES ('10', 'FS', '13327777777', 'FDSDF', '123457543323232323', 'ddf', null, null, '1', null, '2020-06-19 11:19:24', null, null, null);
INSERT INTO `t_doctor` VALUES ('11', 'DFSDF', '13322226662', 'SDFSDF', '123456787654323456', 'dsfsfd', null, null, '1', null, '2020-06-19 11:19:37', null, null, null);
INSERT INTO `t_doctor` VALUES ('12', 'DF', '15523456789', 'FDFSF', '234568787645342312', 'ddf', null, null, '1', null, '2020-06-19 11:20:00', null, null, null);
INSERT INTO `t_doctor` VALUES ('13', 'FDF', '13322221333', 'FDSF', '234678988776533236', '2323', null, null, '1', null, '2020-06-19 11:20:14', null, null, null);

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_no` varchar(32) DEFAULT NULL COMMENT '订单编号',
  `cot_no` varchar(32) DEFAULT NULL COMMENT '陪护床号',
  `wechat_id` int(11) DEFAULT NULL COMMENT '微信用户关联表中的id',
  `lease_start_time` datetime DEFAULT NULL COMMENT '租用开始时间',
  `lease_end_time` datetime DEFAULT NULL COMMENT '租用结束时间',
  `rental_time` int(11) DEFAULT NULL COMMENT '租用耗时(小时)',
  `rental_deposit` int(11) DEFAULT NULL COMMENT '租用押金（元）',
  `rental_amt` int(11) DEFAULT NULL COMMENT '租用金额(元)',
  `return_amt` int(11) DEFAULT NULL COMMENT '退回金额(元)',
  `status` char(2) DEFAULT NULL COMMENT '00:租用中;01:完成;',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_no` (`order_no`),
  KEY `Index_cot_no` (`cot_no`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='订单表';

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('1', 'BJ00000000001', 'BN00001', '1', '2020-06-17 17:13:36', '2020-06-26 17:13:38', '2', '100', '80', '20', '01', '2020-06-17 17:14:28', '2020-06-17 17:14:34');

-- ----------------------------
-- Table structure for t_profit_sharing
-- ----------------------------
DROP TABLE IF EXISTS `t_profit_sharing`;
CREATE TABLE `t_profit_sharing` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `order_no` varchar(32) NOT NULL COMMENT '订单编号',
  `cot_no` varchar(32) NOT NULL COMMENT '陪护床号',
  `doctor_id` int(11) NOT NULL COMMENT '医生id',
  `rental_amt` int(11) DEFAULT NULL COMMENT '租用金额',
  `share_proportion` decimal(16,2) DEFAULT '0.00' COMMENT '分润比例',
  `share_amt` decimal(16,2) DEFAULT '0.00' COMMENT '分润金额',
  `lease_end_time` datetime DEFAULT NULL COMMENT '订单完成时间',
  `settle_status` char(2) DEFAULT NULL COMMENT '结算状态:00未结算;01已结算',
  PRIMARY KEY (`id`),
  KEY `index_order_cot_no` (`order_no`,`cot_no`),
  KEY `index_time` (`lease_end_time`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='分润表';

-- ----------------------------
-- Records of t_profit_sharing
-- ----------------------------
INSERT INTO `t_profit_sharing` VALUES ('1', '123456', 'BN00001', '1', '120', '1.00', '1.20', '2020-06-19 13:56:41', '0');
INSERT INTO `t_profit_sharing` VALUES ('2', '123457', 'BN00001', '1', '100', '1.00', '1.00', '2020-06-18 13:57:22', '0');
INSERT INTO `t_profit_sharing` VALUES ('3', '123458', 'BN00002', '2', '200', '1.00', '2.00', '2020-06-18 13:58:24', '0');

-- ----------------------------
-- Table structure for t_question
-- ----------------------------
DROP TABLE IF EXISTS `t_question`;
CREATE TABLE `t_question` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `cot_no` varchar(32) NOT NULL COMMENT '陪护床号',
  `address` varchar(225) DEFAULT NULL COMMENT '地址',
  `phone` varchar(11) DEFAULT NULL COMMENT '联系方式',
  `question_idea` varchar(500) DEFAULT NULL COMMENT '问题意见',
  `status` char(2) DEFAULT NULL COMMENT '处理状态:00待处理;01:已处理',
  `handle_plan` varchar(500) DEFAULT NULL COMMENT '处理方案',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='问题表';

-- ----------------------------
-- Records of t_question
-- ----------------------------
INSERT INTO `t_question` VALUES ('1', 'BN00001', '北京市', '13333333333', '好大的问题', '01', '在修呢', '2020-06-18 09:59:16');

-- ----------------------------
-- Table structure for t_stakeholder_group
-- ----------------------------
DROP TABLE IF EXISTS `t_stakeholder_group`;
CREATE TABLE `t_stakeholder_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `group_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '组名称',
  `status` char(1) DEFAULT NULL COMMENT '状态:0:无效;1:有效',
  `create_time` datetime DEFAULT NULL COMMENT '创建人',
  `create_by` varchar(11) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(11) DEFAULT NULL COMMENT '更新人',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='干系组表';

-- ----------------------------
-- Records of t_stakeholder_group
-- ----------------------------
INSERT INTO `t_stakeholder_group` VALUES ('1', 'cesji', '0', '2020-06-17 16:59:55', '1', '2020-06-18 09:38:09', '1', null);
INSERT INTO `t_stakeholder_group` VALUES ('2', '哈哈', '1', '2020-06-17 17:58:18', '1', '2020-06-17 18:11:46', '1', '');
INSERT INTO `t_stakeholder_group` VALUES ('3', 'sss', '1', '2020-06-17 18:07:11', '1', '2020-06-17 18:13:06', '1', '');
INSERT INTO `t_stakeholder_group` VALUES ('4', '12122', '0', '2020-06-17 18:10:03', '1', '2020-06-19 11:14:42', '1', '');
INSERT INTO `t_stakeholder_group` VALUES ('5', '测试服收工时费', '0', '2020-06-18 09:15:50', '1', '2020-06-18 09:17:28', '1', '');
INSERT INTO `t_stakeholder_group` VALUES ('6', 'sff', '0', '2020-06-19 11:14:20', '1', '2020-06-19 13:40:23', '1', '11');
INSERT INTO `t_stakeholder_group` VALUES ('7', 'SDS', '0', '2020-06-19 11:20:24', '1', '2020-06-19 13:40:23', '1', '');
INSERT INTO `t_stakeholder_group` VALUES ('8', 'FFFFFF', '0', '2020-06-19 11:20:27', '1', '2020-06-19 13:40:23', '1', '');
INSERT INTO `t_stakeholder_group` VALUES ('9', 'FFFFFF', '0', '2020-06-19 11:20:30', '1', '2020-06-19 13:40:23', '1', '');
INSERT INTO `t_stakeholder_group` VALUES ('10', 'FFFFF', '0', '2020-06-19 11:20:34', '1', '2020-06-19 13:40:23', '1', '');
INSERT INTO `t_stakeholder_group` VALUES ('11', 'FFFFFFFF', '0', '2020-06-19 11:20:40', '1', '2020-06-19 13:40:23', '1', '');
INSERT INTO `t_stakeholder_group` VALUES ('12', 'FFFFFFFFFFFFF', '0', '2020-06-19 11:20:44', '1', '2020-06-19 13:40:23', '1', '');
INSERT INTO `t_stakeholder_group` VALUES ('13', 'FFFFFFFFFFFFFF', '0', '2020-06-19 11:20:49', '1', '2020-06-19 13:40:23', '1', '');
INSERT INTO `t_stakeholder_group` VALUES ('14', 'FFFFFFFFFFF', '0', '2020-06-19 11:20:54', '1', '2020-06-19 13:40:23', '1', '');
INSERT INTO `t_stakeholder_group` VALUES ('15', 'FFFFFssssss', '0', '2020-06-19 11:58:37', '1', '2020-06-19 13:40:23', '1', '');

-- ----------------------------
-- Table structure for t_stakeholder_group_doctor
-- ----------------------------
DROP TABLE IF EXISTS `t_stakeholder_group_doctor`;
CREATE TABLE `t_stakeholder_group_doctor` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `doctor_id` int(11) DEFAULT NULL COMMENT '医生id',
  `stakeholder_group_id` int(11) DEFAULT NULL COMMENT '干系组id',
  `share_proportion` decimal(16,2) DEFAULT NULL COMMENT '分润比例(%)',
  `create_time` datetime DEFAULT NULL COMMENT '绑定时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8 COMMENT='干系组和医生关联表';

-- ----------------------------
-- Records of t_stakeholder_group_doctor
-- ----------------------------
INSERT INTO `t_stakeholder_group_doctor` VALUES ('54', '7', '2', '11.00', '2020-06-19 13:47:19');
INSERT INTO `t_stakeholder_group_doctor` VALUES ('55', '3', '2', '22.00', '2020-06-19 13:47:19');
INSERT INTO `t_stakeholder_group_doctor` VALUES ('56', '10', '2', null, '2020-06-19 13:47:19');
INSERT INTO `t_stakeholder_group_doctor` VALUES ('57', '6', '2', null, '2020-06-19 13:47:19');
INSERT INTO `t_stakeholder_group_doctor` VALUES ('58', '13', '2', null, '2020-06-19 13:47:19');
INSERT INTO `t_stakeholder_group_doctor` VALUES ('59', '2', '2', null, '2020-06-19 13:47:19');
INSERT INTO `t_stakeholder_group_doctor` VALUES ('60', '9', '2', null, '2020-06-19 13:47:19');
INSERT INTO `t_stakeholder_group_doctor` VALUES ('61', '5', '2', null, '2020-06-19 13:47:19');
INSERT INTO `t_stakeholder_group_doctor` VALUES ('62', '12', '2', null, '2020-06-19 13:47:19');
INSERT INTO `t_stakeholder_group_doctor` VALUES ('63', '1', '2', null, '2020-06-19 13:47:19');

-- ----------------------------
-- Table structure for t_wechat_user
-- ----------------------------
DROP TABLE IF EXISTS `t_wechat_user`;
CREATE TABLE `t_wechat_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `open_id` varchar(32) NOT NULL COMMENT '微信open_id',
  `sex` char(1) DEFAULT NULL COMMENT '性别',
  `nick` varchar(64) DEFAULT NULL COMMENT '昵称',
  `country` varchar(32) DEFAULT NULL COMMENT '国家',
  `province` varchar(32) DEFAULT NULL COMMENT '省份',
  `city` varchar(32) DEFAULT NULL COMMENT '城市',
  `head_img_url` varchar(256) DEFAULT NULL COMMENT '头像',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_open_id` (`open_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='微信用户';

-- ----------------------------
-- Records of t_wechat_user
-- ----------------------------
