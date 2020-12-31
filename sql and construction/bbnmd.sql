/*
Navicat MySQL Data Transfer

Source Server         : 78
Source Server Version : 50513
Source Host           : localhost:3306
Source Database       : bbnmd

Target Server Type    : MYSQL
Target Server Version : 50513
File Encoding         : 65001

Date: 2020-12-13 15:11:41
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `board`
-- ----------------------------
DROP TABLE IF EXISTS `board`;
CREATE TABLE `board` (
  `bid` int(11) NOT NULL AUTO_INCREMENT,
  `bname` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `parentid` int(11) DEFAULT NULL,
  PRIMARY KEY (`bid`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of board
-- ----------------------------
INSERT INTO board VALUES ('1', '.net版块', '0');
INSERT INTO board VALUES ('2', 'java版块', '0');
INSERT INTO board VALUES ('3', '数据库版块', '0');
INSERT INTO board VALUES ('4', '软件工程版块', '0');
INSERT INTO board VALUES ('5', 'ado.net', '1');
INSERT INTO board VALUES ('6', 'asp.net', '1');
INSERT INTO board VALUES ('7', 'vb.net', '1');
INSERT INTO board VALUES ('8', 'jsp', '2');
INSERT INTO board VALUES ('9', 'struts', '2');
INSERT INTO board VALUES ('10', 'hibernate', '2');
INSERT INTO board VALUES ('11', 'sql', '3');
INSERT INTO board VALUES ('12', 'oracle', '3');
INSERT INTO board VALUES ('13', 'mysql', '3');
INSERT INTO board VALUES ('14', '其它', '0');
INSERT INTO board VALUES ('15', '面試', '15');

-- ----------------------------
-- Table structure for `replay`
-- ----------------------------
DROP TABLE IF EXISTS `replay`;
CREATE TABLE `replay` (
  `rid` int(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `content` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `publishtime` datetime DEFAULT NULL,
  `modifytime` datetime DEFAULT NULL,
  `tid` int(20) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`rid`),
  KEY `replay_tid` (`tid`),
  KEY `replay_uid` (`uid`),
  CONSTRAINT `replay_tid` FOREIGN KEY (`tid`) REFERENCES `topic` (`tid`),
  CONSTRAINT `replay_uid` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of replay
-- ----------------------------
INSERT INTO replay VALUES ('1', 'jsp is very good reply', 'very good,i agree, reply', '2020-12-08 20:00:18', '2020-12-08 20:00:18', '2', '1');
INSERT INTO replay VALUES ('2', 'jsp is very good reply', 'very good,i agree, reply', '2020-12-08 20:00:18', '2020-12-08 20:00:18', '2', '1');
INSERT INTO replay VALUES ('3', 'jsp is very good reply', 'very good,i agree, reply', '2020-12-08 20:00:18', '2020-12-08 20:00:18', '2', '1');
INSERT INTO replay VALUES ('4', 'jsp is very good reply', 'very good,i agree, reply', '2020-12-08 20:00:18', '2020-12-08 20:00:18', '2', '1');
INSERT INTO replay VALUES ('5', 'jsp is very good reply', 'very good,i agree, reply', '2020-12-08 20:00:18', '2020-12-08 20:00:18', '2', '1');

-- ----------------------------
-- Table structure for `topic`
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic` (
  `tid` int(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `content` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `publishtime` datetime DEFAULT NULL,
  `modifytime` datetime DEFAULT NULL,
  `uid` int(20) NOT NULL,
  `bid` int(11) DEFAULT NULL,
  `hot` int(20) DEFAULT '0',
  PRIMARY KEY (`tid`),
  KEY `topic_uid` (`uid`),
  KEY `topic_bid` (`bid`),
  CONSTRAINT `topic_bid` FOREIGN KEY (`bid`) REFERENCES `board` (`bid`),
  CONSTRAINT `topic_uid` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of topic
-- ----------------------------
INSERT INTO topic VALUES ('1', 'jsp  good', ' good,i agree', '2020-12-08 19:58:51', '2020-12-08 19:58:51', '1', '8', null);
INSERT INTO topic VALUES ('2', 'jsp is very good', 'very good,i agree', '2020-12-08 19:58:51', '2020-12-08 19:58:51', '1', '8', null);

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(20) NOT NULL AUTO_INCREMENT,
  `uname` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `upwd` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `role` varchar(20) COLLATE utf8_bin DEFAULT '普通用户',
  `head` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `regtime` datetime DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO user VALUES ('1', 'a', '0cc175b9c0f1b6a831c399e269772661', '普通用户', '1.gif', '2020-12-08 19:22:25', '1');
