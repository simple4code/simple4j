/*
 Navicat Premium Data Transfer

 Source Server         : simple4j
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : simple4j

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 01/11/2020 12:00:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pe_permission
-- ----------------------------
DROP TABLE IF EXISTS `pe_permission`;
CREATE TABLE `pe_permission`  (
  `id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '权限描述',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `type` tinyint(4) NULL DEFAULT NULL COMMENT '权限类型 1为菜单 2为功能 3为API',
  `pid` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '主键',
  `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `en_visible` int(1) NULL DEFAULT NULL COMMENT '企业可见性 0：不可见，1：可见',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pe_permission
-- ----------------------------
INSERT INTO `pe_permission` VALUES ('1', '3', 'SAAS企业管理', 1, '0', 'saas-clients', 0);
INSERT INTO `pe_permission` VALUES ('1063313020819738624', '查看部门的按钮', '查看部门', 2, '1', 'point-dept', 0);
INSERT INTO `pe_permission` VALUES ('1063315016368918528', '用户管理菜单', '员工管理', 1, '0', 'employees', 1);
INSERT INTO `pe_permission` VALUES ('1063315194329042944', '用户删除按钮', '用户删除按钮', 2, '1063315016368918528', 'point-user-delete', 1);
INSERT INTO `pe_permission` VALUES ('1063322760811515904', '删除api', '删除用户api', 3, '1063315194329042944', 'API-USER-DELETE', 1);
INSERT INTO `pe_permission` VALUES ('1063327833876729856', '组织架构菜单', '组织架构', 1, '0', 'departments', 1);
INSERT INTO `pe_permission` VALUES ('1063328114689576960', '公司设置菜单', '公司设置', 1, '0', 'settings', 1);
INSERT INTO `pe_permission` VALUES ('1063328310592933888', '用户添加按钮', '用户添加按钮', 2, '1063315016368918528', 'POINT-USER-ADD', 1);
INSERT INTO `pe_permission` VALUES ('1063328532492587008', '用户修改按钮', '用户修改按钮', 2, '1063315016368918528', 'POINT-USER-UPDATE', 1);
INSERT INTO `pe_permission` VALUES ('1064104257952813056', NULL, '权限管理', 1, '0', 'permissions', 1);
INSERT INTO `pe_permission` VALUES ('1064553683007705088', 'test', 'test', 1, '0', 'test', 1);
INSERT INTO `pe_permission` VALUES ('1067396481381625856', '啊啊啊', '啊啊啊', 1, '0', '啊啊啊', 1);

-- ----------------------------
-- Table structure for pe_permission_api
-- ----------------------------
DROP TABLE IF EXISTS `pe_permission_api`;
CREATE TABLE `pe_permission_api`  (
  `id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
  `api_level` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限等级，1为通用接口权限，2为需校验接口权限',
  `api_method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求类型',
  `api_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '链接',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pe_permission_api
-- ----------------------------
INSERT INTO `pe_permission_api` VALUES ('1063322760811515904', '1', 'delete', '/sys/user/delete');

-- ----------------------------
-- Table structure for pe_permission_menu
-- ----------------------------
DROP TABLE IF EXISTS `pe_permission_menu`;
CREATE TABLE `pe_permission_menu`  (
  `id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
  `menu_icon` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限代码',
  `menu_order` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pe_permission_menu
-- ----------------------------
INSERT INTO `pe_permission_menu` VALUES ('1', 'abc5', '14');
INSERT INTO `pe_permission_menu` VALUES ('1063315016368918528', 'menu-user', '1');
INSERT INTO `pe_permission_menu` VALUES ('1063327833876729856', '2', '2');
INSERT INTO `pe_permission_menu` VALUES ('1063328114689576960', '3', '3');
INSERT INTO `pe_permission_menu` VALUES ('1064104257952813056', NULL, NULL);
INSERT INTO `pe_permission_menu` VALUES ('1064553683007705088', '1', '1');
INSERT INTO `pe_permission_menu` VALUES ('1067396481381625856', '1', '1');
INSERT INTO `pe_permission_menu` VALUES ('2', 'def', '2');

-- ----------------------------
-- Table structure for pe_permission_point
-- ----------------------------
DROP TABLE IF EXISTS `pe_permission_point`;
CREATE TABLE `pe_permission_point`  (
  `id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
  `point_class` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '按钮类型',
  `point_icon` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '按钮icon',
  `point_status` int(11) NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pe_permission_point
-- ----------------------------
INSERT INTO `pe_permission_point` VALUES ('1063313020819738624', 'dept', 'dept', 1);
INSERT INTO `pe_permission_point` VALUES ('1063315194329042944', 'point-user-delete', 'point-user-delete', 1);
INSERT INTO `pe_permission_point` VALUES ('1063328310592933888', 'POINT-USER-ADD', 'POINT-USER-ADD', 1);
INSERT INTO `pe_permission_point` VALUES ('1063328532492587008', 'POINT-USER-UPDATE', 'POINT-USER-UPDATE', 1);

-- ----------------------------
-- Table structure for pe_role
-- ----------------------------
DROP TABLE IF EXISTS `pe_role`;
CREATE TABLE `pe_role`  (
  `id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
  `name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '说明',
  `company_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '企业id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_k3beff7qglfn58qsf2yvbg41i`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pe_role
-- ----------------------------
INSERT INTO `pe_role` VALUES ('1062944989845262336', '人事经理', '负责整合人事部门', '1');
INSERT INTO `pe_role` VALUES ('1064098829009293312', '系统管理员', '管理整合平台，可以操作企业所有功能', '1');
INSERT INTO `pe_role` VALUES ('1064098935443951616', '人事专员', '只能操作员工菜单', '1');
INSERT INTO `pe_role` VALUES ('1064099035536822272', '薪资专员', '绩效管理', '1');

-- ----------------------------
-- Table structure for pe_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `pe_role_permission`;
CREATE TABLE `pe_role_permission`  (
  `role_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色ID',
  `permission_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`role_id`, `permission_id`) USING BTREE,
  INDEX `FK74qx7rkbtq2wqms78gljv87a0`(`permission_id`) USING BTREE,
  INDEX `FKee9dk0vg99shvsytflym6egxd`(`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pe_role_permission
-- ----------------------------
INSERT INTO `pe_role_permission` VALUES ('1062944989845262336', '1063315016368918528');
INSERT INTO `pe_role_permission` VALUES ('1062944989845262336', '1063328310592933888');
INSERT INTO `pe_role_permission` VALUES ('1062944989845262336', '1063328532492587008');

-- ----------------------------
-- Table structure for pe_user
-- ----------------------------
DROP TABLE IF EXISTS `pe_user`;
CREATE TABLE `pe_user`  (
  `id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `mobile` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号码',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名称',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `enable_state` int(2) NULL DEFAULT 1 COMMENT '启用状态 0是禁用，1是启用',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `department_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门ID',
  `time_of_entry` datetime(0) NULL DEFAULT NULL COMMENT '入职时间',
  `form_of_employment` int(1) NULL DEFAULT NULL COMMENT '聘用形式',
  `work_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '工号',
  `form_of_management` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '管理形式',
  `working_city` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '工作城市',
  `correction_time` datetime(0) NULL DEFAULT NULL COMMENT '转正时间',
  `in_service_status` int(1) NULL DEFAULT NULL COMMENT '在职状态 1.在职  2.离职',
  `company_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '企业ID',
  `company_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `department_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `role_ids` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_user_phone`(`mobile`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pe_user
-- ----------------------------
INSERT INTO `pe_user` VALUES ('1066370498633486336', '12344123', '123', '123', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for pe_user_role
-- ----------------------------
DROP TABLE IF EXISTS `pe_user_role`;
CREATE TABLE `pe_user_role`  (
  `role_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色ID',
  `user_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`role_id`, `user_id`) USING BTREE,
  INDEX `FK74qx7rkbtq2wqms78gljv87a1`(`role_id`) USING BTREE,
  INDEX `FKee9dk0vg99shvsytflym6egx1`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pe_user_role
-- ----------------------------
INSERT INTO `pe_user_role` VALUES ('1062944989845262336', '1066370498633486336');

SET FOREIGN_KEY_CHECKS = 1;
