/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : supermarket_management

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 10/01/2020 21:35:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for employee_attendance
-- ----------------------------
DROP TABLE IF EXISTS `employee_attendance`;
CREATE TABLE `employee_attendance`  (
  `attendance_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '考勤表id',
  `employee_id` int(11) NOT NULL COMMENT '员工id（登陆账号）',
  `leave_days` double NULL DEFAULT 0 COMMENT '员工请假天数，默认为0天',
  `work_time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '员工发工资的每个月',
  PRIMARY KEY (`attendance_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee_attendance
-- ----------------------------
INSERT INTO `employee_attendance` VALUES (1, 1000, 1, '2019-10');
INSERT INTO `employee_attendance` VALUES (2, 1001, 0.5, '2019-11');
INSERT INTO `employee_attendance` VALUES (3, 1000, 2, '2019-11');

-- ----------------------------
-- Table structure for employee_auth
-- ----------------------------
DROP TABLE IF EXISTS `employee_auth`;
CREATE TABLE `employee_auth`  (
  `auth_id` int(255) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户_角色id',
  `employee_id` int(20) NOT NULL COMMENT '用户名（t_user外键）',
  `employee_authoritie` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色',
  PRIMARY KEY (`auth_id`) USING BTREE,
  INDEX `username`(`employee_id`) USING BTREE,
  CONSTRAINT `employee_id` FOREIGN KEY (`employee_id`) REFERENCES `employee_information` (`employee_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee_auth
-- ----------------------------
INSERT INTO `employee_auth` VALUES (1, 1000, '收银员');
INSERT INTO `employee_auth` VALUES (4, 1003, '总经理');
INSERT INTO `employee_auth` VALUES (5, 1001, '库存经理');
INSERT INTO `employee_auth` VALUES (7, 1006, '人事经理');
INSERT INTO `employee_auth` VALUES (8, 1007, '库存管理员');
INSERT INTO `employee_auth` VALUES (9, 1008, '店长');
INSERT INTO `employee_auth` VALUES (10, 1009, '收银员');
INSERT INTO `employee_auth` VALUES (13, 1003, '店长');
INSERT INTO `employee_auth` VALUES (14, 1005, '推销员');

-- ----------------------------
-- Table structure for employee_information
-- ----------------------------
DROP TABLE IF EXISTS `employee_information`;
CREATE TABLE `employee_information`  (
  `employee_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '员工id（登陆账号）',
  `employee_password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '员工密码',
  `employee_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '员工姓名',
  `employee_mobile` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `employee_id_card` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '员工省份证号',
  `employee_entry_time` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '员工入职时间',
  `employee_regular` tinyint(255) NULL DEFAULT 0 COMMENT '是否正式员工',
  PRIMARY KEY (`employee_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1010 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee_information
-- ----------------------------
INSERT INTO `employee_information` VALUES (1000, '$2a$10$7U/tnlWCR501zcpjbJOKsu9U4LTfTNOO.gucrfh7/R8/6xFfAy3sm', '张三', '369856', '41022419958259860', '2017-12-25', 1);
INSERT INTO `employee_information` VALUES (1001, '$2a$10$7U/tnlWCR501zcpjbJOKsu9U4LTfTNOO.gucrfh7/R8/6xFfAy3sm', '李四', '46544', '41022419958259860', '2017-12-25', 1);
INSERT INTO `employee_information` VALUES (1003, '$2a$10$jmzBzuDW85stqNBMUYTKj.3rARPBAAS99KmbiqnYRBgTwo0nSKckG', '夏宁', '14544', '0423456124654545454', '2017-5-12', 1);
INSERT INTO `employee_information` VALUES (1005, '$2a$10$3fvrRkhaqx0xDTbEfs4BU.niK/MRgvK5f5D9Ew7SShLPPDPVoS2la', '和晨阳', '15981856274', '410224199712109899', '2019-11-23 11:15', 1);
INSERT INTO `employee_information` VALUES (1006, '$2a$10$//WX4SxUOLSzhHeSNHvy/u9H3aui1KykBUW/WKIk1XZaLyGqCNnuO', '陆晨', '15655226', '3698514455289661', '2019-12-07 02:35', 1);
INSERT INTO `employee_information` VALUES (1007, '$2a$10$13DBxmPU0z.1YpHGpDQL6ey5uu25i9M535sFdcuyMF1.b6SjBgGdW', '唐睿', '3698752', '369871152665', '2019-12-07 03:23', 1);
INSERT INTO `employee_information` VALUES (1008, '$2a$10$H2SzBJP0R9xYz0t9TDcKFeNqF7y0oG5yZ8qmU4X4VC6jC5FxD7u7q', '小王', '36954', '41236919975624512', '2019-12-07 06:02', 0);
INSERT INTO `employee_information` VALUES (1009, '$2a$10$5.epLNXjEm1Vu2cBwl7o.OtfoQdIV3.Fwsvdh0PVR.5WTWpKeMQFS', '小李', '685932345', '12465234164', '2019-12-07 06:02', 1);

-- ----------------------------
-- Table structure for login_log
-- ----------------------------
DROP TABLE IF EXISTS `login_log`;
CREATE TABLE `login_log`  (
  `employee_id` int(20) NOT NULL COMMENT '登陆账号',
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `login_time` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账号登陆时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member`  (
  `membership_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '会员账号',
  `membership_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '会员姓名',
  `membership_phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '会员电话号码',
  `membership_level` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '会员等级',
  `membership_score` int(11) NULL DEFAULT 0 COMMENT '会员积分',
  `membership_balance` double(255, 0) NOT NULL DEFAULT 0 COMMENT '会员卡内余额',
  PRIMARY KEY (`membership_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES (1, '游客', '111111', '游客', 0, 0);
INSERT INTO `member` VALUES (2, '夏宁', '15981856274', '钻石会员', 190, 1718);
INSERT INTO `member` VALUES (3, '陆晨', '45645641264', '白银会员', 95, 200);
INSERT INTO `member` VALUES (6, '和晨阳', '15981852638', '黄金会员', 0, 400);
INSERT INTO `member` VALUES (7, '张琪', '126955', '黄金会员', 0, 200);
INSERT INTO `member` VALUES (8, '张琪', '126955', '黄金会员', 0, 200);
INSERT INTO `member` VALUES (9, '张琪', '126955', '黄金会员', 0, 200);
INSERT INTO `member` VALUES (10, '张琪', '126955', '黄金会员', 0, 200);
INSERT INTO `member` VALUES (11, '张琪', '126955', '黄金会员', 0, 200);
INSERT INTO `member` VALUES (12, '张琪', '126955', '黄金会员', 0, 200);
INSERT INTO `member` VALUES (13, '德宏', '369875214', '白银会员', 0, 110);

-- ----------------------------
-- Table structure for member_discount
-- ----------------------------
DROP TABLE IF EXISTS `member_discount`;
CREATE TABLE `member_discount`  (
  `discount_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '会员折扣id',
  `membership_level` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '会员等级',
  `membership_discount` double(10, 2) NULL DEFAULT NULL COMMENT '会员折扣值',
  PRIMARY KEY (`discount_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of member_discount
-- ----------------------------
INSERT INTO `member_discount` VALUES (1, '普通会员', 0.00);
INSERT INTO `member_discount` VALUES (2, '白银会员', 0.95);
INSERT INTO `member_discount` VALUES (3, '黄金会员', 0.90);
INSERT INTO `member_discount` VALUES (4, '钻石会员', 0.85);

-- ----------------------------
-- Table structure for month_total_price
-- ----------------------------
DROP TABLE IF EXISTS `month_total_price`;
CREATE TABLE `month_total_price`  (
  `id` int(11) NOT NULL,
  `month_time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '年-月',
  `total_price` double(10, 2) NOT NULL COMMENT '每月销售总额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`  (
  `client_id` varchar(48) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `resource_ids` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `client_secret` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `scope` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `authorized_grant_types` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `authorities` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `access_token_validity` int(11) NULL DEFAULT NULL,
  `refresh_token_validity` int(11) NULL DEFAULT NULL,
  `additional_information` varchar(4096) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `autoapprove` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('c1', 'CASH_REGISTER,STAFF,TRADE', '$2a$10$y6luju85ALe3rHegsP7kse/1Q5YHx8xiXRhO9dckgStNiUArKZp6K', 'ROLE_ADMIN,ROLE_USER,ROLE_API', 'authorization_code,password,client_credentials,implicit,refresh_token', 'http://www.baidu.com', 'ROLE_ADMIN,ROLE_USER,ROLE_API', NULL, NULL, NULL, NULL);
INSERT INTO `oauth_client_details` VALUES ('c2', 'CASH_REGISTER,STAFF,TRADE', '$2a$10$y6luju85ALe3rHegsP7kse/1Q5YHx8xiXRhO9dckgStNiUArKZp6K', 'ROLE_API', 'authorization_code,password,client_credentials,implicit,refresh_token', 'http://www.baidu.com', 'ROLE_API', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code`  (
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '授权码创建时间',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '授权码',
  `authentication` blob NULL,
  PRIMARY KEY (`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for purchase
-- ----------------------------
DROP TABLE IF EXISTS `purchase`;
CREATE TABLE `purchase`  (
  `purchase_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '进货批次id',
  `purchase_detailed` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '进货i详细内容',
  `purchase_total_price` double(10, 2) NOT NULL COMMENT '进货总额',
  `person_in_charge` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '负责人',
  `purchase_time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '进货时间',
  PRIMARY KEY (`purchase_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of purchase
-- ----------------------------
INSERT INTO `purchase` VALUES (1, '康师傅方便面50箱，拔丝蛋糕100箱，安慕希牛奶50箱', 20000.00, '小王', '2019-12-10');
INSERT INTO `purchase` VALUES (2, '生活用品', 1000.00, '夏宁', '2019-05-06');
INSERT INTO `purchase` VALUES (3, '不同种类饼干10箱', 500.00, '小李', '2019-12-08');

-- ----------------------------
-- Table structure for role_pay
-- ----------------------------
DROP TABLE IF EXISTS `role_pay`;
CREATE TABLE `role_pay`  (
  `id` int(11) NOT NULL COMMENT '角色工资表id',
  `employee_authoritie` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色',
  `pay` double NULL DEFAULT NULL COMMENT '每月角色基本工资',
  `one_day_deduction` double(10, 0) NOT NULL COMMENT '请假每天扣除费用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_pay
-- ----------------------------
INSERT INTO `role_pay` VALUES (1, '总经理', 10000, 350);
INSERT INTO `role_pay` VALUES (2, '人事经理', 8000, 300);
INSERT INTO `role_pay` VALUES (3, '库存经理', 8000, 300);
INSERT INTO `role_pay` VALUES (4, '店长', 20000, 0);
INSERT INTO `role_pay` VALUES (5, '收银员', 4000, 100);
INSERT INTO `role_pay` VALUES (6, '库存管理员', 5000, 100);
INSERT INTO `role_pay` VALUES (7, '保洁员', 4000, 100);
INSERT INTO `role_pay` VALUES (8, '客服人员', 4000, 100);
INSERT INTO `role_pay` VALUES (9, '理货员', 4000, 100);
INSERT INTO `role_pay` VALUES (10, '推销员', 4000, 100);
INSERT INTO `role_pay` VALUES (11, '保安', 5500, 150);

-- ----------------------------
-- Table structure for salary
-- ----------------------------
DROP TABLE IF EXISTS `salary`;
CREATE TABLE `salary`  (
  `salary_id` int(11) NOT NULL COMMENT '工资表id',
  `employee_id` int(11) NOT NULL COMMENT '员工工号',
  `employee_salary` double(10, 2) NULL DEFAULT NULL COMMENT '员工工资',
  PRIMARY KEY (`salary_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for supplier
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier`  (
  `supplier_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '供货商id',
  `company` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '公司',
  `deliverer` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '送货人',
  `trade_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品名称',
  `phone` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系电话',
  PRIMARY KEY (`supplier_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of supplier
-- ----------------------------
INSERT INTO `supplier` VALUES (3, '伊利公司', '小李', '伊利纯牛奶', '1324564521');
INSERT INTO `supplier` VALUES (4, '晨光文具', '小何', '文具用品', '111591');

-- ----------------------------
-- Table structure for trade
-- ----------------------------
DROP TABLE IF EXISTS `trade`;
CREATE TABLE `trade`  (
  `trade_member` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '商品编号，（无符号整型）',
  `trade_name` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品名称',
  `trade_category` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品种类',
  `purhase_price` double(10, 2) NOT NULL COMMENT '商品进价',
  `price` double(10, 2) NOT NULL COMMENT '商品售价',
  `trade_stock` int(11) NOT NULL COMMENT '商品库存',
  `manufacture_date` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品生产日期',
  `quality_guarantee_period` int(11) NULL DEFAULT NULL COMMENT '商品保质期',
  `while_member` tinyint(4) NULL DEFAULT 0 COMMENT '是否会员',
  `purchase_id` int(11) NOT NULL COMMENT '进货批次',
  PRIMARY KEY (`trade_member`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of trade
-- ----------------------------
INSERT INTO `trade` VALUES (2, '宫廷糕点拔丝蛋糕', '休闲食品', 30.00, 50.00, 65, '2019-8-25', 30, 1, 1);
INSERT INTO `trade` VALUES (4, '雨森卫生纸家庭装', '清洁用品', 10.00, 15.00, 50, '2019-8-25', 360, 0, 1);
INSERT INTO `trade` VALUES (5, '小王子米果', '休闲食品', 4.00, 5.00, 500, '2019-3-25', 270, 0, 1);
INSERT INTO `trade` VALUES (6, '蒙牛纯牛奶', '奶制品', 50.00, 60.00, 18, '2019-08-12', 180, 1, 1);
INSERT INTO `trade` VALUES (8, '蛇果', '生鲜食品', 1.00, 2.00, 90, '2019-12-11', 15, 1, 2);

-- ----------------------------
-- Table structure for transaction_details
-- ----------------------------
DROP TABLE IF EXISTS `transaction_details`;
CREATE TABLE `transaction_details`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `trade_member` int(150) NOT NULL COMMENT '顾客购买商品编号',
  `transaction_real_price` double(10, 2) NOT NULL COMMENT '顾客购买商品的真实价格',
  `trade_number` int(10) UNSIGNED NOT NULL COMMENT '商品数量',
  `transaction_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '交易编号',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `transaction_id`(`transaction_id`) USING BTREE,
  CONSTRAINT `transaction_id` FOREIGN KEY (`transaction_id`) REFERENCES `transaction_record` (`transaction_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 54 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of transaction_details
-- ----------------------------
INSERT INTO `transaction_details` VALUES (1, 5, 5.00, 2, '6c7db6d0-cdb7-4fde-ab79-ebc247a31734');
INSERT INTO `transaction_details` VALUES (2, 4, 15.00, 1, '6c7db6d0-cdb7-4fde-ab79-ebc247a31734');
INSERT INTO `transaction_details` VALUES (3, 5, 5.00, 3, '6c7db6d0-cdb7-4fde-ab79-ebc245854fg34');
INSERT INTO `transaction_details` VALUES (4, 4, 15.00, 1, '9496c763-f40e-4b6a-bce2-232ce5dd1ac5');
INSERT INTO `transaction_details` VALUES (5, 3, 30.00, 1, '9496c763-f40e-4b6a-bce2-232ce5dd1ac5');
INSERT INTO `transaction_details` VALUES (6, 4, 15.00, 1, '4789efe2-c098-4806-9801-09d7cb4a8770');
INSERT INTO `transaction_details` VALUES (7, 3, 30.00, 1, '4789efe2-c098-4806-9801-09d7cb4a8770');
INSERT INTO `transaction_details` VALUES (8, 5, 5.00, 2, '21700037-c8cf-463e-b027-0725864e914c');
INSERT INTO `transaction_details` VALUES (9, 4, 15.00, 2, '21700037-c8cf-463e-b027-0725864e914c');
INSERT INTO `transaction_details` VALUES (10, 3, 30.00, 1, '21700037-c8cf-463e-b027-0725864e914c');
INSERT INTO `transaction_details` VALUES (11, 3, 30.00, 1, '01dd491c-3ed0-46f8-abc8-afc1510dff41');
INSERT INTO `transaction_details` VALUES (12, 4, 15.00, 1, '01dd491c-3ed0-46f8-abc8-afc1510dff41');
INSERT INTO `transaction_details` VALUES (13, 3, 30.00, 2, '81915111-f293-4115-8157-d6a895813903');
INSERT INTO `transaction_details` VALUES (14, 4, 15.00, 1, '81915111-f293-4115-8157-d6a895813903');
INSERT INTO `transaction_details` VALUES (15, 5, 5.00, 1, 'c09b4522-a05f-45f4-a68d-859eeab794b3');
INSERT INTO `transaction_details` VALUES (16, 4, 15.00, 1, 'c09b4522-a05f-45f4-a68d-859eeab794b3');
INSERT INTO `transaction_details` VALUES (17, 6, 51.00, 2, '5fd9f4f7-63be-467d-9d6d-d0fba952e753');
INSERT INTO `transaction_details` VALUES (18, 2, 42.50, 1, 'a30d49b3-58bd-45cb-8ae7-e39db1cd04e7');
INSERT INTO `transaction_details` VALUES (19, 3, 25.50, 1, 'a30d49b3-58bd-45cb-8ae7-e39db1cd04e7');
INSERT INTO `transaction_details` VALUES (20, 4, 15.00, 1, 'a30d49b3-58bd-45cb-8ae7-e39db1cd04e7');
INSERT INTO `transaction_details` VALUES (21, 6, 51.00, 1, 'a30d49b3-58bd-45cb-8ae7-e39db1cd04e7');
INSERT INTO `transaction_details` VALUES (22, 2, 50.00, 3, '27f65c29-ab21-4be9-ad7d-58d867aa7ac5');
INSERT INTO `transaction_details` VALUES (23, 2, 50.00, 3, '27f65c29-ab21-4be9-ad7d-58d867aa7ac5');
INSERT INTO `transaction_details` VALUES (24, 3, 25.50, 1, '419aecd3-c7c2-489d-8d73-ac9e4377f01a');
INSERT INTO `transaction_details` VALUES (25, 6, 51.00, 1, '419aecd3-c7c2-489d-8d73-ac9e4377f01a');
INSERT INTO `transaction_details` VALUES (26, 4, 15.00, 2, 'c99b4515-b80b-4fe2-b720-3d245b276e5b');
INSERT INTO `transaction_details` VALUES (27, 3, 30.00, 1, '4917eccf-041b-47f8-a01a-fe1d211bb53f');
INSERT INTO `transaction_details` VALUES (28, 3, 25.50, 1, '3cc88d71-5887-474a-81c5-e769b9d23eb0');
INSERT INTO `transaction_details` VALUES (29, 5, 5.00, 1, '3cc88d71-5887-474a-81c5-e769b9d23eb0');
INSERT INTO `transaction_details` VALUES (30, 5, 5.00, 2, '08467985-4b95-48fa-bc5e-d96259bdd031');
INSERT INTO `transaction_details` VALUES (31, 4, 15.00, 1, '08467985-4b95-48fa-bc5e-d96259bdd031');
INSERT INTO `transaction_details` VALUES (32, 3, 25.50, 1, '54b17c8b-05b0-4464-9e0b-c109bd6c934d');
INSERT INTO `transaction_details` VALUES (33, 5, 5.00, 1, '54b17c8b-05b0-4464-9e0b-c109bd6c934d');
INSERT INTO `transaction_details` VALUES (34, 4, 15.00, 1, '54b17c8b-05b0-4464-9e0b-c109bd6c934d');
INSERT INTO `transaction_details` VALUES (35, 2, 42.50, 1, '849b6f80-b4bb-4f03-b9af-4d6278d458b3');
INSERT INTO `transaction_details` VALUES (36, 2, 42.50, 1, '794eb4f5-6e02-48b4-8c33-1643142aa633');
INSERT INTO `transaction_details` VALUES (37, 5, 5.00, 1, '794eb4f5-6e02-48b4-8c33-1643142aa633');
INSERT INTO `transaction_details` VALUES (38, 6, 51.00, 2, '794eb4f5-6e02-48b4-8c33-1643142aa633');
INSERT INTO `transaction_details` VALUES (39, 2, 50.00, 2, '5252034a-252a-489e-8c15-0a34a31c6ff5');
INSERT INTO `transaction_details` VALUES (40, 5, 5.00, 1, '5252034a-252a-489e-8c15-0a34a31c6ff5');
INSERT INTO `transaction_details` VALUES (41, 2, 45.00, 1, '70a47123-d262-4e1b-9d60-31fe5010f816');
INSERT INTO `transaction_details` VALUES (42, 3, 30.00, 1, 'ed107e46-798f-45b6-9bd3-eeedb47ed048');
INSERT INTO `transaction_details` VALUES (43, 4, 15.00, 1, 'ed107e46-798f-45b6-9bd3-eeedb47ed048');
INSERT INTO `transaction_details` VALUES (44, 5, 5.00, 2, 'ebe3d4a0-d2b9-4c2a-930b-e50b562613dc');
INSERT INTO `transaction_details` VALUES (45, 5, 5.00, 2, '485ea101-d1f0-4082-84b6-f0283573c4a4');
INSERT INTO `transaction_details` VALUES (46, 6, 51.00, 2, '2c42c8e9-5013-4e2a-9881-fb716a975346');
INSERT INTO `transaction_details` VALUES (47, 3, 25.50, 1, '0ec39f07-3758-4683-8f0c-e0999d98352a');
INSERT INTO `transaction_details` VALUES (48, 4, 15.00, 1, '0ec39f07-3758-4683-8f0c-e0999d98352a');
INSERT INTO `transaction_details` VALUES (49, 4, 15.00, 2, 'b99f9034-61af-4226-b20d-a8065a23a1cc');
INSERT INTO `transaction_details` VALUES (50, 2, 42.50, 2, 'd1489656-80f8-4da4-8ed0-2f3a400b65f0');
INSERT INTO `transaction_details` VALUES (51, 4, 15.00, 2, 'e02cf229-ea95-4800-854c-93bb1e3a5e58');
INSERT INTO `transaction_details` VALUES (52, 5, 5.00, 2, 'e02cf229-ea95-4800-854c-93bb1e3a5e58');
INSERT INTO `transaction_details` VALUES (53, 5, 5.00, 1, '4e4a8d7e-07de-42b9-a62d-5798ed326147');
INSERT INTO `transaction_details` VALUES (54, 8, 2.00, 2, '4e4a8d7e-07de-42b9-a62d-5798ed326147');

-- ----------------------------
-- Table structure for transaction_record
-- ----------------------------
DROP TABLE IF EXISTS `transaction_record`;
CREATE TABLE `transaction_record`  (
  `transaction_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '交易编号',
  `transaction_time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '交易时间',
  `transaction_total_price` double(10, 2) NULL DEFAULT NULL COMMENT '交易总额',
  PRIMARY KEY (`transaction_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of transaction_record
-- ----------------------------
INSERT INTO `transaction_record` VALUES ('01dd491c-3ed0-46f8-abc8-afc1510dff41', '2019-12-13 14:32:39', 45.00);
INSERT INTO `transaction_record` VALUES ('08467985-4b95-48fa-bc5e-d96259bdd031', '2019-12-15 17:32:59', 25.00);
INSERT INTO `transaction_record` VALUES ('0dd8b389-66ba-49fb-aa21-cb359fd841be', '2019-12-13 12:07:14', 0.00);
INSERT INTO `transaction_record` VALUES ('0ec39f07-3758-4683-8f0c-e0999d98352a', '2019-12-15 20:46:42', 40.50);
INSERT INTO `transaction_record` VALUES ('21700037-c8cf-463e-b027-0725864e914c', '2019-12-13 14:20:54', 70.00);
INSERT INTO `transaction_record` VALUES ('27f65c29-ab21-4be9-ad7d-58d867aa7ac5', '2019-12-13 22:24:35', 300.00);
INSERT INTO `transaction_record` VALUES ('2c42c8e9-5013-4e2a-9881-fb716a975346', '2019-12-15 20:36:24', 102.00);
INSERT INTO `transaction_record` VALUES ('32135c2c-6ec0-462a-a12e-3543d7e69a2f', '2019-12-13 12:01:24', 0.00);
INSERT INTO `transaction_record` VALUES ('3cc88d71-5887-474a-81c5-e769b9d23eb0', '2019-12-15 16:21:50', 30.50);
INSERT INTO `transaction_record` VALUES ('419aecd3-c7c2-489d-8d73-ac9e4377f01a', '2019-12-13 23:22:36', 76.50);
INSERT INTO `transaction_record` VALUES ('4789efe2-c098-4806-9801-09d7cb4a8770', '2019-12-13 14:09:35', 45.00);
INSERT INTO `transaction_record` VALUES ('485ea101-d1f0-4082-84b6-f0283573c4a4', '2019-12-15 20:34:55', 10.00);
INSERT INTO `transaction_record` VALUES ('4917eccf-041b-47f8-a01a-fe1d211bb53f', '2019-12-15 14:18:23', 30.00);
INSERT INTO `transaction_record` VALUES ('4e4a8d7e-07de-42b9-a62d-5798ed326147', '2019-12-29 09:44:16', 9.00);
INSERT INTO `transaction_record` VALUES ('5252034a-252a-489e-8c15-0a34a31c6ff5', '2019-12-15 20:05:12', 105.00);
INSERT INTO `transaction_record` VALUES ('54b17c8b-05b0-4464-9e0b-c109bd6c934d', '2019-12-15 17:42:49', 45.50);
INSERT INTO `transaction_record` VALUES ('5fd9f4f7-63be-467d-9d6d-d0fba952e753', '2019-12-13 21:40:04', 102.00);
INSERT INTO `transaction_record` VALUES ('6c7db6d0-cdb7-4fde-ab79-ebc245854fg34', '2019-12-5 22:17:50  ', 15.00);
INSERT INTO `transaction_record` VALUES ('6c7db6d0-cdb7-4fde-ab79-ebc247a31734', '2019-11-25 12:30:12', 25.00);
INSERT INTO `transaction_record` VALUES ('70a47123-d262-4e1b-9d60-31fe5010f816', '2019-12-15 20:13:57', 45.00);
INSERT INTO `transaction_record` VALUES ('78eae60b-4655-4b69-a84b-1f0789c559ed', '2019-12-13 21:14:06', 0.00);
INSERT INTO `transaction_record` VALUES ('794eb4f5-6e02-48b4-8c33-1643142aa633', '2019-12-15 20:01:32', 149.50);
INSERT INTO `transaction_record` VALUES ('81915111-f293-4115-8157-d6a895813903', '2019-12-13 14:36:30', 75.00);
INSERT INTO `transaction_record` VALUES ('849b6f80-b4bb-4f03-b9af-4d6278d458b3', '2019-12-15 17:48:39', 42.50);
INSERT INTO `transaction_record` VALUES ('887f923b-ac97-4e4d-89d0-149d48e025aa', '2019-12-15 20:54:57', 0.00);
INSERT INTO `transaction_record` VALUES ('9496c763-f40e-4b6a-bce2-232ce5dd1ac5', '2019-12-13 14:05:14', 45.00);
INSERT INTO `transaction_record` VALUES ('a30d49b3-58bd-45cb-8ae7-e39db1cd04e7', '2019-12-13 22:10:32', 134.00);
INSERT INTO `transaction_record` VALUES ('b99f9034-61af-4226-b20d-a8065a23a1cc', '2019-12-15 20:47:48', 30.00);
INSERT INTO `transaction_record` VALUES ('ba28aed4-929f-4015-a864-d163d0d922cb', '2019-12-15 20:37:36', 0.00);
INSERT INTO `transaction_record` VALUES ('c09b4522-a05f-45f4-a68d-859eeab794b3', '2019-12-13 21:35:17', 20.00);
INSERT INTO `transaction_record` VALUES ('c99b4515-b80b-4fe2-b720-3d245b276e5b', '2019-12-13 23:24:16', 30.00);
INSERT INTO `transaction_record` VALUES ('d1489656-80f8-4da4-8ed0-2f3a400b65f0', '2019-12-15 20:51:07', 85.00);
INSERT INTO `transaction_record` VALUES ('deccfa5a-df50-4bc7-bf37-7d6e07250873', '2019-12-13 12:00:59', 0.00);
INSERT INTO `transaction_record` VALUES ('e02cf229-ea95-4800-854c-93bb1e3a5e58', '2019-12-20 17:11:45', 40.00);
INSERT INTO `transaction_record` VALUES ('eaa445df-30d2-461e-a5dc-027ae61f82d6', '2019-12-13 12:08:24', 0.00);
INSERT INTO `transaction_record` VALUES ('ebe3d4a0-d2b9-4c2a-930b-e50b562613dc', '2019-12-15 20:34:41', 10.00);
INSERT INTO `transaction_record` VALUES ('ed107e46-798f-45b6-9bd3-eeedb47ed048', '2019-12-15 20:30:46', 45.00);

SET FOREIGN_KEY_CHECKS = 1;
