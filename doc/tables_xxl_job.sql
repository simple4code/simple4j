/*
 Navicat Premium Data Transfer

 Source Server         : jfinal
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : jfinal

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 29/10/2020 14:25:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for xxl_job_group
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_group`;
CREATE TABLE `xxl_job_group`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '执行器AppName',
  `title` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '执行器名称',
  `address_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '执行器地址类型：0=自动注册、1=手动录入',
  `address_list` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行器地址列表，多地址逗号分隔',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_group
-- ----------------------------
INSERT INTO `xxl_job_group` VALUES (1, 'xxl-job-executor-sample', '示例执行器', 0, NULL);
INSERT INTO `xxl_job_group` VALUES (2, 'manageTestExecutor', 'manage项目测试', 0, 'http://127.0.0.1:9999/');

-- ----------------------------
-- Table structure for xxl_job_info
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_info`;
CREATE TABLE `xxl_job_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_group` int(11) NOT NULL COMMENT '执行器主键ID',
  `job_cron` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务执行CRON',
  `job_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `add_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `author` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '作者',
  `alarm_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '报警邮件',
  `executor_route_strategy` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行器路由策略',
  `executor_handler` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行器任务参数',
  `executor_block_strategy` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '阻塞处理策略',
  `executor_timeout` int(11) NOT NULL DEFAULT 0 COMMENT '任务执行超时时间，单位秒',
  `executor_fail_retry_count` int(11) NOT NULL DEFAULT 0 COMMENT '失败重试次数',
  `glue_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT 'GLUE源代码',
  `glue_remark` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'GLUE备注',
  `glue_updatetime` datetime(0) NULL DEFAULT NULL COMMENT 'GLUE更新时间',
  `child_jobid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '子任务ID，多个逗号分隔',
  `trigger_status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '调度状态：0-停止，1-运行',
  `trigger_last_time` bigint(13) NOT NULL DEFAULT 0 COMMENT '上次调度时间',
  `trigger_next_time` bigint(13) NOT NULL DEFAULT 0 COMMENT '下次调度时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_info
-- ----------------------------
INSERT INTO `xxl_job_info` VALUES (1, 1, '0 0 0 * * ? *', '测试任务1', '2018-11-03 22:21:31', '2018-11-03 22:21:31', 'XXL', '', 'FIRST', 'demoJobHandler', '', 'SERIAL_EXECUTION', 0, 0, 'BEAN', '', 'GLUE代码初始化', '2018-11-03 22:21:31', '', 0, 0, 0);
INSERT INTO `xxl_job_info` VALUES (2, 2, '0/5 * * * * ?', '测试任务', '2020-10-29 14:13:01', '2020-10-29 14:21:02', 'manage', 'manage@qqq.com', 'FIRST', 'demoJobHandler', '', 'SERIAL_EXECUTION', 0, 0, 'BEAN', '', 'GLUE代码初始化', '2020-10-29 14:13:01', '', 0, 0, 0);
INSERT INTO `xxl_job_info` VALUES (3, 2, '0/5 * * * * ?', 'manage测试share', '2020-10-29 14:20:18', '2020-10-29 14:20:58', 'aa', 'aa@aaa.com', 'FIRST', 'shardingJobHandler', '', 'SERIAL_EXECUTION', 0, 0, 'BEAN', '', 'GLUE代码初始化', '2020-10-29 14:20:18', '', 0, 0, 0);

-- ----------------------------
-- Table structure for xxl_job_lock
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_lock`;
CREATE TABLE `xxl_job_lock`  (
  `lock_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '锁名称',
  PRIMARY KEY (`lock_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_lock
-- ----------------------------
INSERT INTO `xxl_job_lock` VALUES ('schedule_lock');

-- ----------------------------
-- Table structure for xxl_job_log
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_log`;
CREATE TABLE `xxl_job_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `job_group` int(11) NOT NULL COMMENT '执行器主键ID',
  `job_id` int(11) NOT NULL COMMENT '任务，主键ID',
  `executor_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行器地址，本次执行的地址',
  `executor_handler` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行器任务参数',
  `executor_sharding_param` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行器任务分片参数，格式如 1/2',
  `executor_fail_retry_count` int(11) NOT NULL DEFAULT 0 COMMENT '失败重试次数',
  `trigger_time` datetime(0) NULL DEFAULT NULL COMMENT '调度-时间',
  `trigger_code` int(11) NOT NULL COMMENT '调度-结果',
  `trigger_msg` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '调度-日志',
  `handle_time` datetime(0) NULL DEFAULT NULL COMMENT '执行-时间',
  `handle_code` int(11) NOT NULL COMMENT '执行-状态',
  `handle_msg` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '执行-日志',
  `alarm_status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '告警状态：0-默认、1-无需告警、2-告警成功、3-告警失败',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `I_trigger_time`(`trigger_time`) USING BTREE,
  INDEX `I_handle_code`(`handle_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_log
-- ----------------------------
INSERT INTO `xxl_job_log` VALUES (1, 2, 2, 'http://127.0.0.1:9999/', 'demoJobHandler', '', NULL, 0, '2020-10-29 14:14:05', 200, '任务触发类型：Cron触发<br>调度机器：192.168.12.88<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999/<br>code：200<br>msg：null', '2020-10-29 14:14:16', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (2, 2, 2, 'http://127.0.0.1:9999/', 'demoJobHandler', '', NULL, 0, '2020-10-29 14:15:05', 200, '任务触发类型：Cron触发<br>调度机器：192.168.12.88<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999/<br>code：200<br>msg：null', '2020-10-29 14:15:15', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (3, 2, 2, 'http://127.0.0.1:9999/', 'demoJobHandler', '', NULL, 0, '2020-10-29 14:16:05', 200, '任务触发类型：Cron触发<br>调度机器：192.168.12.88<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999/<br>code：200<br>msg：null', '2020-10-29 14:16:15', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (4, 2, 2, 'http://127.0.0.1:9999/', 'demoJobHandler', '', NULL, 0, '2020-10-29 14:17:05', 200, '任务触发类型：Cron触发<br>调度机器：192.168.12.88<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999/<br>code：200<br>msg：null', '2020-10-29 14:17:15', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (5, 2, 2, 'http://127.0.0.1:9999/', 'demoJobHandler', '', NULL, 0, '2020-10-29 14:18:10', 200, '任务触发类型：Cron触发<br>调度机器：192.168.12.88<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999/<br>code：200<br>msg：null', '2020-10-29 14:18:28', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (6, 2, 2, 'http://127.0.0.1:9999/', 'demoJobHandler', '', NULL, 0, '2020-10-29 14:18:15', 500, '任务触发类型：Cron触发<br>调度机器：192.168.12.88<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999/<br>code：500<br>msg：xxl-rpc remoting error(Read timed out), for url : http://127.0.0.1:9999/run', '2020-10-29 14:18:38', 200, '', 3);
INSERT INTO `xxl_job_log` VALUES (7, 2, 2, 'http://127.0.0.1:9999/', 'demoJobHandler', '', NULL, 0, '2020-10-29 14:18:20', 200, '任务触发类型：Cron触发<br>调度机器：192.168.12.88<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999/<br>code：200<br>msg：null', '2020-10-29 14:18:48', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (8, 2, 2, 'http://127.0.0.1:9999/', 'demoJobHandler', '', NULL, 0, '2020-10-29 14:18:25', 200, '任务触发类型：Cron触发<br>调度机器：192.168.12.88<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999/<br>code：200<br>msg：null', '2020-10-29 14:18:58', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (9, 2, 2, 'http://127.0.0.1:9999/', 'demoJobHandler', '', NULL, 0, '2020-10-29 14:18:30', 200, '任务触发类型：Cron触发<br>调度机器：192.168.12.88<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999/<br>code：200<br>msg：null', '2020-10-29 14:19:08', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (10, 2, 2, 'http://127.0.0.1:9999/', 'demoJobHandler', '', NULL, 0, '2020-10-29 14:18:35', 200, '任务触发类型：Cron触发<br>调度机器：192.168.12.88<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999/<br>code：200<br>msg：null', '2020-10-29 14:19:18', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (11, 2, 2, 'http://127.0.0.1:9999/', 'demoJobHandler', '', NULL, 0, '2020-10-29 14:18:40', 200, '任务触发类型：Cron触发<br>调度机器：192.168.12.88<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999/<br>code：200<br>msg：null', '2020-10-29 14:19:28', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (12, 2, 2, 'http://127.0.0.1:9999/', 'demoJobHandler', '', NULL, 0, '2020-10-29 14:18:45', 200, '任务触发类型：Cron触发<br>调度机器：192.168.12.88<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999/<br>code：200<br>msg：null', '2020-10-29 14:19:38', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (13, 2, 2, 'http://127.0.0.1:9999/', 'demoJobHandler', '', NULL, 0, '2020-10-29 14:18:50', 200, '任务触发类型：Cron触发<br>调度机器：192.168.12.88<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999/<br>code：200<br>msg：null', '2020-10-29 14:19:48', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (14, 2, 2, 'http://127.0.0.1:9999/', 'demoJobHandler', '', NULL, 0, '2020-10-29 14:19:00', 200, '任务触发类型：Cron触发<br>调度机器：192.168.12.88<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999/<br>code：200<br>msg：null', '2020-10-29 14:19:58', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (15, 2, 2, 'http://127.0.0.1:9999/', 'demoJobHandler', '', NULL, 0, '2020-10-29 14:19:05', 200, '任务触发类型：Cron触发<br>调度机器：192.168.12.88<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999/<br>code：200<br>msg：null', '2020-10-29 14:20:08', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (16, 2, 2, 'http://127.0.0.1:9999/', 'demoJobHandler', '', NULL, 0, '2020-10-29 14:19:10', 200, '任务触发类型：Cron触发<br>调度机器：192.168.12.88<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999/<br>code：200<br>msg：null', '2020-10-29 14:20:18', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (17, 2, 2, 'http://127.0.0.1:9999/', 'demoJobHandler', '', NULL, 0, '2020-10-29 14:19:15', 200, '任务触发类型：Cron触发<br>调度机器：192.168.12.88<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999/<br>code：200<br>msg：null', '2020-10-29 14:20:28', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (18, 2, 2, 'http://127.0.0.1:9999/', 'demoJobHandler', '', NULL, 0, '2020-10-29 14:19:20', 200, '任务触发类型：Cron触发<br>调度机器：192.168.12.88<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999/<br>code：200<br>msg：null', '2020-10-29 14:20:38', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (19, 2, 2, 'http://127.0.0.1:9999/', 'demoJobHandler', '', NULL, 0, '2020-10-29 14:19:25', 200, '任务触发类型：Cron触发<br>调度机器：192.168.12.88<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999/<br>code：200<br>msg：null', '2020-10-29 14:20:48', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (20, 2, 2, 'http://127.0.0.1:9999/', 'demoJobHandler', '', NULL, 0, '2020-10-29 14:19:30', 200, '任务触发类型：Cron触发<br>调度机器：192.168.12.88<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999/<br>code：200<br>msg：null', '2020-10-29 14:20:58', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (21, 2, 2, 'http://127.0.0.1:9999/', 'demoJobHandler', '', NULL, 0, '2020-10-29 14:19:35', 200, '任务触发类型：Cron触发<br>调度机器：192.168.12.88<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999/<br>code：200<br>msg：null', '2020-10-29 14:21:08', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (22, 2, 2, 'http://127.0.0.1:9999/', 'demoJobHandler', '', NULL, 0, '2020-10-29 14:19:40', 200, '任务触发类型：Cron触发<br>调度机器：192.168.12.88<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999/<br>code：200<br>msg：null', '2020-10-29 14:21:18', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (23, 2, 2, 'http://127.0.0.1:9999/', 'demoJobHandler', '', NULL, 0, '2020-10-29 14:19:45', 200, '任务触发类型：Cron触发<br>调度机器：192.168.12.88<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999/<br>code：200<br>msg：null', '2020-10-29 14:21:28', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (24, 2, 2, 'http://127.0.0.1:9999/', 'demoJobHandler', '', NULL, 0, '2020-10-29 14:19:50', 200, '任务触发类型：Cron触发<br>调度机器：192.168.12.88<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999/<br>code：200<br>msg：null', '2020-10-29 14:21:38', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (25, 2, 2, 'http://127.0.0.1:9999/', 'demoJobHandler', '', NULL, 0, '2020-10-29 14:19:55', 200, '任务触发类型：Cron触发<br>调度机器：192.168.12.88<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999/<br>code：200<br>msg：null', '2020-10-29 14:21:48', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (26, 2, 2, 'http://127.0.0.1:9999/', 'demoJobHandler', '', NULL, 0, '2020-10-29 14:20:00', 200, '任务触发类型：Cron触发<br>调度机器：192.168.12.88<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999/<br>code：200<br>msg：null', '2020-10-29 14:21:58', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (27, 2, 2, 'http://127.0.0.1:9999/', 'demoJobHandler', '', NULL, 0, '2020-10-29 14:20:05', 200, '任务触发类型：Cron触发<br>调度机器：192.168.12.88<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999/<br>code：200<br>msg：null', '2020-10-29 14:22:08', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (28, 2, 2, 'http://127.0.0.1:9999/', 'demoJobHandler', '', NULL, 0, '2020-10-29 14:20:10', 200, '任务触发类型：Cron触发<br>调度机器：192.168.12.88<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999/<br>code：200<br>msg：null', '2020-10-29 14:22:18', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (29, 2, 2, 'http://127.0.0.1:9999/', 'demoJobHandler', '', NULL, 0, '2020-10-29 14:20:15', 200, '任务触发类型：Cron触发<br>调度机器：192.168.12.88<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999/<br>code：200<br>msg：null', NULL, 0, NULL, 0);
INSERT INTO `xxl_job_log` VALUES (30, 2, 2, 'http://127.0.0.1:9999/', 'demoJobHandler', '', NULL, 0, '2020-10-29 14:20:20', 200, '任务触发类型：Cron触发<br>调度机器：192.168.12.88<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999/<br>code：200<br>msg：null', NULL, 0, NULL, 0);
INSERT INTO `xxl_job_log` VALUES (31, 2, 2, 'http://127.0.0.1:9999/', 'demoJobHandler', '', NULL, 0, '2020-10-29 14:20:25', 200, '任务触发类型：Cron触发<br>调度机器：192.168.12.88<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999/<br>code：200<br>msg：null', NULL, 0, NULL, 0);
INSERT INTO `xxl_job_log` VALUES (32, 2, 3, 'http://127.0.0.1:9999/', 'shardingJobHandler', '', NULL, 0, '2020-10-29 14:20:30', 200, '任务触发类型：Cron触发<br>调度机器：192.168.12.88<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999/<br>code：200<br>msg：null', '2020-10-29 14:20:30', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (33, 2, 2, 'http://127.0.0.1:9999/', 'demoJobHandler', '', NULL, 0, '2020-10-29 14:20:30', 200, '任务触发类型：Cron触发<br>调度机器：192.168.12.88<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999/<br>code：200<br>msg：null', NULL, 0, NULL, 0);
INSERT INTO `xxl_job_log` VALUES (34, 2, 2, 'http://127.0.0.1:9999/', 'demoJobHandler', '', NULL, 0, '2020-10-29 14:20:35', 200, '任务触发类型：Cron触发<br>调度机器：192.168.12.88<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999/<br>code：200<br>msg：null', NULL, 0, NULL, 0);
INSERT INTO `xxl_job_log` VALUES (35, 2, 3, 'http://127.0.0.1:9999/', 'shardingJobHandler', '', NULL, 0, '2020-10-29 14:20:35', 200, '任务触发类型：Cron触发<br>调度机器：192.168.12.88<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999/<br>code：200<br>msg：null', '2020-10-29 14:20:35', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (36, 2, 3, 'http://127.0.0.1:9999/', 'shardingJobHandler', '', NULL, 0, '2020-10-29 14:20:40', 200, '任务触发类型：Cron触发<br>调度机器：192.168.12.88<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999/<br>code：200<br>msg：null', '2020-10-29 14:20:40', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (37, 2, 2, 'http://127.0.0.1:9999/', 'demoJobHandler', '', NULL, 0, '2020-10-29 14:20:40', 200, '任务触发类型：Cron触发<br>调度机器：192.168.12.88<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999/<br>code：200<br>msg：null', NULL, 0, NULL, 0);
INSERT INTO `xxl_job_log` VALUES (38, 2, 2, 'http://127.0.0.1:9999/', 'demoJobHandler', '', NULL, 0, '2020-10-29 14:20:45', 200, '任务触发类型：Cron触发<br>调度机器：192.168.12.88<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999/<br>code：200<br>msg：null', NULL, 0, NULL, 0);
INSERT INTO `xxl_job_log` VALUES (39, 2, 3, 'http://127.0.0.1:9999/', 'shardingJobHandler', '', NULL, 0, '2020-10-29 14:20:45', 200, '任务触发类型：Cron触发<br>调度机器：192.168.12.88<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999/<br>code：200<br>msg：null', '2020-10-29 14:20:45', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (40, 2, 2, 'http://127.0.0.1:9999/', 'demoJobHandler', '', NULL, 0, '2020-10-29 14:20:50', 200, '任务触发类型：Cron触发<br>调度机器：192.168.12.88<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999/<br>code：200<br>msg：null', NULL, 0, NULL, 0);
INSERT INTO `xxl_job_log` VALUES (41, 2, 3, 'http://127.0.0.1:9999/', 'shardingJobHandler', '', NULL, 0, '2020-10-29 14:20:50', 200, '任务触发类型：Cron触发<br>调度机器：192.168.12.88<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999/<br>code：200<br>msg：null', '2020-10-29 14:20:50', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (42, 2, 2, 'http://127.0.0.1:9999/', 'demoJobHandler', '', NULL, 0, '2020-10-29 14:20:55', 200, '任务触发类型：Cron触发<br>调度机器：192.168.12.88<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999/<br>code：200<br>msg：null', NULL, 0, NULL, 0);
INSERT INTO `xxl_job_log` VALUES (43, 2, 3, 'http://127.0.0.1:9999/', 'shardingJobHandler', '', NULL, 0, '2020-10-29 14:20:55', 200, '任务触发类型：Cron触发<br>调度机器：192.168.12.88<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999/<br>code：200<br>msg：null', '2020-10-29 14:20:55', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (44, 2, 2, 'http://127.0.0.1:9999/', 'demoJobHandler', '', NULL, 0, '2020-10-29 14:21:00', 200, '任务触发类型：Cron触发<br>调度机器：192.168.12.88<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999/<br>code：200<br>msg：null', NULL, 0, NULL, 0);
INSERT INTO `xxl_job_log` VALUES (45, 2, 3, 'http://127.0.0.1:9999/', 'shardingJobHandler', '', NULL, 0, '2020-10-29 14:21:00', 200, '任务触发类型：Cron触发<br>调度机器：192.168.12.88<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://127.0.0.1:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:9999/<br>code：200<br>msg：null', '2020-10-29 14:21:00', 200, '', 0);

-- ----------------------------
-- Table structure for xxl_job_log_report
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_log_report`;
CREATE TABLE `xxl_job_log_report`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `trigger_day` datetime(0) NULL DEFAULT NULL COMMENT '调度-时间',
  `running_count` int(11) NOT NULL DEFAULT 0 COMMENT '运行中-日志数量',
  `suc_count` int(11) NOT NULL DEFAULT 0 COMMENT '执行成功-日志数量',
  `fail_count` int(11) NOT NULL DEFAULT 0 COMMENT '执行失败-日志数量',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `i_trigger_day`(`trigger_day`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_log_report
-- ----------------------------
INSERT INTO `xxl_job_log_report` VALUES (1, '2020-10-29 00:00:00', 11, 34, 0);
INSERT INTO `xxl_job_log_report` VALUES (2, '2020-10-28 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (3, '2020-10-27 00:00:00', 0, 0, 0);

-- ----------------------------
-- Table structure for xxl_job_logglue
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_logglue`;
CREATE TABLE `xxl_job_logglue`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_id` int(11) NOT NULL COMMENT '任务，主键ID',
  `glue_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT 'GLUE源代码',
  `glue_remark` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'GLUE备注',
  `add_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xxl_job_registry
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_registry`;
CREATE TABLE `xxl_job_registry`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `registry_group` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `registry_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `registry_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `i_g_k_v`(`registry_group`, `registry_key`, `registry_value`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_registry
-- ----------------------------
INSERT INTO `xxl_job_registry` VALUES (3, 'EXECUTOR', 'manageTestExecutor', 'http://127.0.0.1:9999/', '2020-10-29 14:22:09');

-- ----------------------------
-- Table structure for xxl_job_user
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_user`;
CREATE TABLE `xxl_job_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `role` tinyint(4) NOT NULL COMMENT '角色：0-普通用户、1-管理员',
  `permission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限：执行器ID列表，多个逗号分割',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `i_username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_user
-- ----------------------------
INSERT INTO `xxl_job_user` VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', 1, NULL);

SET FOREIGN_KEY_CHECKS = 1;