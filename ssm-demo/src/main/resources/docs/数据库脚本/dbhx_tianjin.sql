/*
Navicat MySQL Data Transfer

Source Server         : localhost-3306-root
Source Server Version : 50536
Source Host           : localhost:3306
Source Database       : dbhx_tianjin

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2018-07-18 13:40:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_gov
-- ----------------------------
DROP TABLE IF EXISTS `sys_gov`;
CREATE TABLE `sys_gov` (
  `id` bigint(18) NOT NULL AUTO_INCREMENT COMMENT '组织机构标识符',
  `parent_id` bigint(18) DEFAULT NULL COMMENT '父节点标识符',
  `full_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '中文全称 ',
  `short_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '中文简称',
  `address` varchar(255) DEFAULT NULL COMMENT '机构地址',
  `contacts` varchar(20) DEFAULT NULL COMMENT '联系人',
  `tel` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '电子邮箱',
  `weburl` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '网址',
  `area` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '行政区划',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标志（0-未删除，1-已删除）',
  `status` tinyint(1) DEFAULT '0' COMMENT '状态（0-未启用、1-启用、-1-停用）',
  `creator_id` bigint(18) DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期',
  `creator_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `updater_id` bigint(18) DEFAULT NULL COMMENT '修改人',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `updater_name` varchar(255) DEFAULT NULL COMMENT '修改人姓名',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织机构表';

-- ----------------------------
-- Records of sys_gov
-- ----------------------------

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(18) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `remote_host` varchar(15) DEFAULT NULL COMMENT '访问IP',
  `remote_port` int(10) DEFAULT NULL COMMENT '远程端口',
  `request_url` varchar(200) DEFAULT NULL COMMENT '请求路径',
  `request_uri` varchar(200) DEFAULT NULL COMMENT '请求地址',
  `gov_id` bigint(18) DEFAULT NULL COMMENT '部门ID',
  `gov_name` varchar(200) DEFAULT NULL COMMENT '部门名称',
  `username` varchar(30) NOT NULL COMMENT '访问人员名称',
  `real_name` varchar(200) DEFAULT NULL COMMENT '真实姓名',
  `area_name` varchar(200) DEFAULT NULL COMMENT '地区名称',
  `module_path` varchar(200) NOT NULL COMMENT '访问路径',
  `module_name` varchar(255) DEFAULT NULL COMMENT '模块名称',
  `result_type` tinyint(1) DEFAULT '1' COMMENT '访问结果（0-失败，1-成功）',
  `status` int(2) DEFAULT NULL COMMENT '状态',
  `creator_id` bigint(18) DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期',
  `creator_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `updater_id` bigint(18) DEFAULT NULL COMMENT '修改人',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `updater_name` varchar(255) DEFAULT NULL COMMENT '修改人姓名',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_module
-- ----------------------------
DROP TABLE IF EXISTS `sys_module`;
CREATE TABLE `sys_module` (
  `id` bigint(18) NOT NULL AUTO_INCREMENT COMMENT '权限标识符',
  `parent_id` bigint(18) DEFAULT NULL COMMENT '父节点标识符',
  `client_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '客户端ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '权限名称',
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '链接',
  `type` int(2) DEFAULT NULL COMMENT '类型 ',
  `status` int(2) DEFAULT NULL COMMENT '状态',
  `creator_id` bigint(18) DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期',
  `creator_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `updater_id` bigint(18) DEFAULT NULL COMMENT '修改人',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `updater_name` varchar(255) DEFAULT NULL COMMENT '修改人姓名',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of sys_module
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(18) NOT NULL AUTO_INCREMENT COMMENT '角色标识符',
  `parent_id` bigint(18) DEFAULT NULL COMMENT '父节点id',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '角色名称',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态（0-未启用、1-启用、1-停用）',
  `creator_id` bigint(18) DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期',
  `creator_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `updater_id` bigint(18) DEFAULT NULL COMMENT '修改人',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `updater_name` varchar(255) DEFAULT NULL COMMENT '修改人姓名',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_module
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_module`;
CREATE TABLE `sys_role_module` (
  `role_id` int(10) NOT NULL,
  `module_id` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_module
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `user_id` int(10) NOT NULL COMMENT '用户id',
  `role_id` int(10) NOT NULL COMMENT '角色id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色用户表';

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(18) NOT NULL AUTO_INCREMENT COMMENT '用户标识符',
  `username` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '登录名',
  `password` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '密码 ',
  `gov_id` bigint(18) DEFAULT NULL COMMENT '所属机构标识符',
  `gender` tinyint(1) DEFAULT NULL COMMENT '性别',
  `tel` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '固话',
  `mobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '手机号码 ',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '电子邮箱',
  `real_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '真实姓名',
  `is_admin` bit(1) DEFAULT b'0' COMMENT '''是否是管理员（0-否，1-是）''',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '地址',
  `status` tinyint(1) DEFAULT '0' COMMENT '状态（0-未启用、1-启用、-1-停用）',
  `creator_id` bigint(18) DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期',
  `creator_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `updater_id` bigint(18) DEFAULT NULL COMMENT '修改人',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `updater_name` varchar(255) DEFAULT NULL COMMENT '修改人姓名',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('17', 'admin', '$2a$10$IohWLuy.FiKrtOHqi9UWvOuA0OWKZwbJXuP14n4PhA23Og5qYP4xK', '1', '0', null, null, null, '系统管理员', '', null, '1', '1', '2018-07-16 17:12:06', null, '1', '2018-07-16 17:12:06', null, null);

-- ----------------------------
-- Table structure for token_client_details
-- ----------------------------
DROP TABLE IF EXISTS `token_client_details`;
CREATE TABLE `token_client_details` (
  `client_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '客户端ID',
  `client_secret` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '客户端密码',
  `resource_ids` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '资源ID',
  `scopes` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '作用范围',
  `grant_types` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '授权方式',
  `authorities` blob COMMENT '权限集合',
  `access_token_validity` int(11) DEFAULT NULL COMMENT 'access_token有效期',
  `refresh_token_validity` int(11) DEFAULT NULL COMMENT 'refresh_token有效期',
  PRIMARY KEY (`client_id`),
  UNIQUE KEY `unique_client_id` (`client_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of token_client_details
-- ----------------------------
INSERT INTO `token_client_details` VALUES ('app_client', '$2a$10$hjYyxsaSkVLtOw/oMTuB1.CzIhiB1z1zTrJWLQcVmV1h2DxeFsKT6', 'app_client', 'read,write,trust', 'password,refresh_token', null, '864000', '864000');
INSERT INTO `token_client_details` VALUES ('bigdata_client', '$2a$10$IbBFEIuJyLcBEUNo3zX6V.AWS6iVJKLh2vP.e7LPbK4Q327iMbIs2', 'bigdata_client', 'read,write,trust', 'password,refresh_token', null, '14400', '14400');
INSERT INTO `token_client_details` VALUES ('web_client', '$2a$10$K9VTyQ1OlvI3ltUCAHLXt.FPOL/jYN0MaP4goXeeHHFgkJ6YPFexC', 'web_client', 'read,write,trust', 'password,refresh_token', null, '7200', '7200');

-- ----------------------------
-- Table structure for token_user_details
-- ----------------------------
DROP TABLE IF EXISTS `token_user_details`;
CREATE TABLE `token_user_details` (
  `id` bigint(18) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(12) NOT NULL COMMENT '用户ID',
  `client_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '客户端ID',
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户名称',
  `realname` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '真实姓名',
  `authorities` blob COMMENT '权限列表',
  `access_token` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '认证token',
  `refresh_token` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '刷新token',
  `expires_in` int(10) NOT NULL COMMENT '有效期',
  `access_token_exp_time` datetime DEFAULT NULL COMMENT 'access_token失效时间',
  `refresh_token_exp_time` datetime DEFAULT NULL COMMENT 'refresh_token失效时间',
  `enabled` bit(1) DEFAULT b'1' COMMENT '是否启用（0-否,1-是）',
  `authenticated` bit(1) DEFAULT b'1' COMMENT '是否认证（0-否,1-是）',
  `account_non_locked` bit(1) DEFAULT b'0' COMMENT '是否未锁定（0-否,1-是）',
  `account_non_expired` bit(1) DEFAULT NULL COMMENT '账户是否未过期（0-否,1-是）',
  `credentials_non_expired` bit(1) DEFAULT b'1' COMMENT '认证是否未过期（0-否,1-是）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `access_token` (`access_token`),
  UNIQUE KEY `refresh_token` (`refresh_token`),
  UNIQUE KEY `client_id_username` (`client_id`,`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of token_user_details
-- ----------------------------

-- ----------------------------
-- Function structure for Get_StrArrayStrOfIndex
-- ----------------------------
DROP FUNCTION IF EXISTS `Get_StrArrayStrOfIndex`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `Get_StrArrayStrOfIndex`(str varchar(1024),
split varchar(10),
i int) RETURNS varchar(1024) CHARSET utf8
begin
declare location int;
declare s int;
declare next int;
declare seed int;
set str=ltrim(rtrim(str));
set s=1;
set next=1;
set seed=LENGTH(split); 
set location=substring_index(split,str,1);
while location<>0 and i>next do
set s=location+seed; 
set location=substring_index(split,str,s); 
set next=next+1; 
end while;
if location =0 then 
set location = LENGTH(str)+1;
end if;
return substring(str,s,location-s); 
end
;;
DELIMITER ;
