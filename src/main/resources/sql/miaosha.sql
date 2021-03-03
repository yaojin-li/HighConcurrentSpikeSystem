/*
 Navicat Premium Data Transfer

 Source Server         : localhost root&123456789
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : miaosha

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 03/03/2021 23:29:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `goods_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `goods_title` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品标题',
  `goods_img` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品图片',
  `goods_detail` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '商品详情',
  `goods_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '商品单价',
  `goods_stock` int(11) NULL DEFAULT NULL COMMENT '商品库存。-1表示没有限制',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, '华为Meta9', '华为Meta9 4GB+32GB', '/img/meta10.png', 'goods_detail_info 华为mate9', 3212.00, 1000, '2020-08-10 10:53:55', NULL, NULL);
INSERT INTO `goods` VALUES (2, 'iphoneX', 'Apple iPhone X', '/img/iphonex.png', 'goods_detail_info iphonex', 8765.00, 100, '2020-08-10 11:06:20', NULL, NULL);

-- ----------------------------
-- Table structure for miaosha_goods
-- ----------------------------
DROP TABLE IF EXISTS `miaosha_goods`;
CREATE TABLE `miaosha_goods`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `goods_id` bigint(20) NULL DEFAULT NULL COMMENT '商品id',
  `miaosha_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '秒杀价格',
  `stock_count` int(11) NULL DEFAULT NULL COMMENT '秒杀价格',
  `start_date` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '开始时间',
  `end_date` datetime(0) NULL DEFAULT NULL COMMENT '截止时间',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of miaosha_goods
-- ----------------------------
INSERT INTO `miaosha_goods` VALUES (1, 1, 0.02, 49, '2021-02-20 11:17:20', '2021-03-27 11:41:30', '2020-08-10 11:05:16', NULL, NULL);
INSERT INTO `miaosha_goods` VALUES (2, 2, 0.01, 58, '2021-02-20 11:05:26', '2021-03-28 17:40:21', '2020-08-10 11:05:26', NULL, NULL);

-- ----------------------------
-- Table structure for miaosha_order
-- ----------------------------
DROP TABLE IF EXISTS `miaosha_order`;
CREATE TABLE `miaosha_order`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `order_id` bigint(20) NULL DEFAULT NULL COMMENT '订单id',
  `goods_id` bigint(20) NULL DEFAULT NULL COMMENT '商品id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `INDEX_USERID_GOODSID`(`user_id`, `goods_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of miaosha_order
-- ----------------------------
INSERT INTO `miaosha_order` VALUES (30, 99, 52, 2);
INSERT INTO `miaosha_order` VALUES (31, 1, 1, 2);
INSERT INTO `miaosha_order` VALUES (32, 1, 1, 1);

-- ----------------------------
-- Table structure for miaosha_user
-- ----------------------------
DROP TABLE IF EXISTS `miaosha_user`;
CREATE TABLE `miaosha_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `mobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '昵称',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'MD5(MD5(pass明文+固定salt)+salt)',
  `salt` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `head` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像，云储存ID',
  `register_date` datetime(0) NULL DEFAULT NULL COMMENT '注册时间',
  `last_login_date` datetime(0) NULL DEFAULT NULL COMMENT '上次登录时间',
  `login_count` int(11) NULL DEFAULT NULL COMMENT '登录次数',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用字段',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of miaosha_user
-- ----------------------------
INSERT INTO `miaosha_user` VALUES (1, '15021952888', '张三', 'b7797cce01b4b131b433b6acf4add449', '1a2b3c4d', 'head', '2020-08-08 11:04:50', '2020-08-08 11:04:53', 1, '2020-08-08 11:04:17', '2020-08-11 09:56:02', '备注');

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `goods_id` bigint(20) NULL DEFAULT NULL COMMENT '商品id',
  `delivery_addr_id` bigint(20) NULL DEFAULT NULL COMMENT '收货地址',
  `goods_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '冗余过来的商品名称',
  `goods_count` int(11) NULL DEFAULT NULL COMMENT '商品数量',
  `goods_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品价格',
  `order_channel` int(4) NULL DEFAULT NULL COMMENT '订单渠道。1pc,2android,3ios',
  `status` int(4) NULL DEFAULT NULL COMMENT '订单状态。0新建未支付，1已支付，2已发货，3已收货，4已退款，5已完成',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `pay_date` datetime(0) NULL DEFAULT NULL COMMENT '付款日期',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO `order_info` VALUES (52, 99, 2, NULL, 'iphoneX', 1, 0.01, 1, 0, '2021-02-20 12:06:52', NULL, '2021-02-20 12:06:54', NULL, NULL);
INSERT INTO `order_info` VALUES (53, 1, 2, NULL, 'iphoneX', 1, 0.01, 1, 0, '2021-03-03 23:28:19', NULL, '2021-03-03 23:28:19', NULL, NULL);
INSERT INTO `order_info` VALUES (54, 1, 1, NULL, '华为Meta9', 1, 0.02, 1, 0, '2021-03-03 23:28:55', NULL, '2021-03-03 23:28:55', NULL, NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
