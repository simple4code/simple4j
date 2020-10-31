/*
 Navicat Premium Data Transfer

 Source Server         : jfinal
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : heima_ihrm

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 31/10/2020 15:37:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for co_company
-- ----------------------------
DROP TABLE IF EXISTS `co_company`;
CREATE TABLE `co_company`  (
  `id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公司名称',
  `manager_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '企业登录账号ID',
  `version` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '当前版本',
  `renewal_date` datetime(0) NULL DEFAULT NULL COMMENT '续期时间',
  `expiration_date` datetime(0) NULL DEFAULT NULL COMMENT '到期时间',
  `company_area` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公司地区',
  `company_address` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '公司地址',
  `business_license_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '营业执照-图片ID',
  `legal_representative` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '法人代表',
  `company_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公司电话',
  `mailbox` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `company_size` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公司规模',
  `industry` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属行业',
  `remarks` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '备注',
  `audit_state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审核状态',
  `state` tinyint(2) NULL DEFAULT 1 COMMENT '状态',
  `balance` double NULL DEFAULT NULL COMMENT '当前余额',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of co_company
-- ----------------------------
INSERT INTO `co_company` VALUES ('1', '江苏传智播客教育科技股份有限公司', 'abc', '12', NULL, NULL, NULL, NULL, NULL, '张三', NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2018-11-07 13:30:05');
INSERT INTO `co_company` VALUES ('1060043412612452352', 'zhangsan', 'abc', '12', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2018-11-07 13:36:58');
INSERT INTO `co_company` VALUES ('1061532681482932224', 'zhangsan', 'abc', '12', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 0, '2018-11-11 16:14:48');
INSERT INTO `co_company` VALUES ('1065494996154650624', '江苏传智播客教育股份有限公司', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, NULL, NULL);

-- ----------------------------
-- Table structure for co_department
-- ----------------------------
DROP TABLE IF EXISTS `co_department`;
CREATE TABLE `co_department`  (
  `id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `company_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '企业ID',
  `pid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父级部门ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门编码',
  `manager` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门负责人',
  `introduce` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '介绍',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `manager_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '负责人ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of co_department
-- ----------------------------
INSERT INTO `co_department` VALUES ('1063676045212913664', '1', '0', '研发部', 'DEV-TECHNOLOG', '技术部总监', '包含开发部，测试部', '2018-11-17 14:11:45', NULL);
INSERT INTO `co_department` VALUES ('1063678149528784896', '1', '1063676045212913664', '测试部', 'DEPT-TEST', '测试部门领导', '所有测试人员统一划分到测试部', '2018-11-17 14:20:07', NULL);
INSERT INTO `co_department` VALUES ('1066238836272664576', '1', NULL, '财务部', 'DEPT-FIN', '李四', '包含出纳和会计', '2018-11-24 15:55:22', NULL);
INSERT INTO `co_department` VALUES ('1066239766607040512', '1', NULL, '行政中心', 'DEPT-XZ', '张三', '包含人力资源和行政部门', '2018-11-24 15:59:04', NULL);
INSERT INTO `co_department` VALUES ('1066239913642561536', '1', '1066239766607040512', '人力资源部', 'DEPT-HR', '李四', '人力资源部', '2018-11-24 15:59:39', NULL);
INSERT INTO `co_department` VALUES ('1066240303092076544', '1', '1066239766607040512', '行政部', 'DEPT-XZ', '王五', '行政部', '2018-11-24 16:01:12', NULL);
INSERT INTO `co_department` VALUES ('1066240656856453120', '1', '1063676045212913664', '开发部', 'DEPT-DEV', '研发', '全部java开发人员', '2018-11-24 16:02:37', NULL);
INSERT INTO `co_department` VALUES ('1066241293639880704', '1', '1066238836272664576', 'test1', 'test2', 'test', 'test4', '2018-11-24 16:05:08', NULL);
INSERT INTO `co_department` VALUES ('1066296800727531520', '1', NULL, 'ttt', 'ttt', 'ttt', 'ttt', NULL, NULL);
INSERT INTO `co_department` VALUES ('1066296884038991872', '1', NULL, 'aaa', 'aa', 'aa', 'aa', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
