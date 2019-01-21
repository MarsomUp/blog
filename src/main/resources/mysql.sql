/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50724
Source Host           : localhost:3306
Source Database       : mycblog

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2019-01-17 22:59:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL,
  `pid` bigint(20) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `perm_code` varchar(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `perm_code_unique` (`perm_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1085555399953743872', '0', '人员管理', '0', '1', '/user/userList', '这是人员管理的权限', null, 'USER_MANAGER', '1547651550650', '1547651550650', '0');
INSERT INTO `sys_permission` VALUES ('1085555665201528832', '0', '博客管理', '0', '2', '/blog/blogList', '这是博客管理的权限', null, 'BLOG_MANAGER', '1547651613890', '1547651613890', '0');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `role_code` varchar(20) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_code_unique` (`role_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1085556699151990784', '组长', 'ZUZHANG', '这是组长角色', '1', '1547651860403', '0', '1547651860403');
INSERT INTO `sys_role` VALUES ('1085556954350223360', '社长', 'SHEZHANG', '这是设长角色', '2', '1547651921247', '0', '1547651921247');

-- ----------------------------
-- Table structure for `sys_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL,
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `login_account` varchar(20) NOT NULL COMMENT '登陆账号',
  `pwd` varchar(50) NOT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `category` int(5) DEFAULT NULL COMMENT '用户分类',
  `avatar` varchar(50) DEFAULT NULL,
  `status` int(4) DEFAULT '0' COMMENT '状态 0：正常 1：停用',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除 0：未删除 1：删除',
  `update_time` bigint(20) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `degree` int(1) DEFAULT NULL COMMENT '学位',
  `birthday` date DEFAULT NULL,
  `gender` int(1) DEFAULT NULL COMMENT '性别',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_account_unique` (`login_account`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1085551962352517120', '马玉财', 'mayc', '514de42b052ca20db4905303b06bbe2a', '21790953bd17d6d0a530cf632e6ea5e8', '0', null, '0', '0', '1547650731062', '1547650731062', '1', '1992-03-14', '0');
INSERT INTO `sys_user` VALUES ('1085552694388588544', '科比', 'kb', '2ec48e961155df2ff25fa7cc3660cfc7', '269de8445f383f5f21bf78189bd051ac', '0', null, '0', '0', '1547650905593', '1547650905593', '1', '1987-12-11', '0');

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
