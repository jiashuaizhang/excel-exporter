/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80015
Source Host           : localhost:3306
Source Database       : ecsorder

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2019-08-14 09:34:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for grid_area
-- ----------------------------
DROP TABLE IF EXISTS `grid_area`;
CREATE TABLE `grid_area` (
  `grid_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '网格id',
  `grid_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '网格名称',
  `province_code_post` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '6位省份编码',
  `city_code` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '地市',
  `grip_poinits` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '区域经纬度',
  `grid_state` tinyint(2) NOT NULL COMMENT ' 生效状态 1生效 2失效 0删除',
  `staff_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '操作员工',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `type` tinyint(2) DEFAULT '1' COMMENT '网格类型： 1 ：普通  2：宽带',
  PRIMARY KEY (`grid_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
