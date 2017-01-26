/*
Navicat MySQL Data Transfer

Source Server         : laitao
Source Server Version : 50509
Source Host           : localhost:3306
Source Database       : laitao

Target Server Type    : MYSQL
Target Server Version : 50509
File Encoding         : 65001

Date: 2016-12-09 18:44:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_base_new
-- ----------------------------
DROP TABLE IF EXISTS `t_base_new`;
CREATE TABLE `t_base_new` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '新闻id',
  `TITLE` varchar(255) NOT NULL COMMENT '新闻标题',
  `CONTENT` text NOT NULL COMMENT '新闻内容',
  `CREATETIME` varchar(20) NOT NULL COMMENT '新闻创建时间',
  `STATUS` varchar(1) DEFAULT '1' COMMENT '新闻状态',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_base_photo
-- ----------------------------
DROP TABLE IF EXISTS `t_base_photo`;
CREATE TABLE `t_base_photo` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '图片id',
  `NAME` varchar(255) NOT NULL COMMENT '图片名称',
  `URL` text NOT NULL COMMENT '图片url',
  `PTYPE` varchar(1) DEFAULT '1' COMMENT '图片所属类型',
  `STATUS` varchar(1) DEFAULT '1' COMMENT '图片状态',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_base_product
-- ----------------------------
DROP TABLE IF EXISTS `t_base_product`;
CREATE TABLE `t_base_product` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '产品id',
  `NUMBER` varchar(255) NOT NULL COMMENT '产品编号',
  `NAME` varchar(255) NOT NULL COMMENT '产品名称',
  `DOSE` varchar(255) NOT NULL COMMENT '产品剂量',
  `CONTENT` text NOT NULL COMMENT '产品内容',
  `CONSISTENCE` varchar(255) NOT NULL COMMENT '产品浓度',
  `STATUS` varchar(1) NOT NULL DEFAULT '1' COMMENT '产品状态',
  `TYPE` char(2) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_base_producttype
-- ----------------------------
DROP TABLE IF EXISTS `t_base_producttype`;
CREATE TABLE `t_base_producttype` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '产品类型id',
  `TYPENAME` varchar(255) NOT NULL COMMENT '产品类型名称',
  `TYPEDESC` varchar(1000) DEFAULT NULL,
  `STATUS` varchar(1) DEFAULT '1' COMMENT '产品类型状态',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_base_techdata
-- ----------------------------
DROP TABLE IF EXISTS `t_base_techdata`;
CREATE TABLE `t_base_techdata` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '技术资料id',
  `TITLE` varchar(255) NOT NULL COMMENT '技术资料标题',
  `CONTENT` text NOT NULL COMMENT '技术资料内容',
  `CREATETIME` varchar(20) NOT NULL COMMENT '技术资料创建时间',
  `STATUS` varchar(1) DEFAULT '1' COMMENT '技术资料状态',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_base_user
-- ----------------------------
DROP TABLE IF EXISTS `t_base_user`;
CREATE TABLE `t_base_user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `USERNAME` varchar(50) NOT NULL COMMENT '用户登入名',
  `PASSWORD` varchar(100) NOT NULL COMMENT '用户登入密码',
  `STATUS` varchar(1) DEFAULT '1' COMMENT '用户状态',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `t_base_img`;
CREATE TABLE `t_base_img` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '图片id',
  `NAME` varchar(255) DEFAULT NULL COMMENT '图片名称',
  `URL` varchar(255) DEFAULT NULL COMMENT '图片路径',
  `STATUS` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
