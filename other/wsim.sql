/*
Navicat MySQL Data Transfer

Source Server         : wsim
Source Server Version : 50535
Source Host           : 127.0.0.1:3306
Source Database       : wsim

Target Server Type    : MYSQL
Target Server Version : 50535
File Encoding         : 65001

Date: 2014-02-26 10:48:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', '1@1.com', '1');
INSERT INTO `admin` VALUES ('2', '2@2.com', '2');
INSERT INTO `admin` VALUES ('3', '5@5.com', '5');

-- ----------------------------
-- Table structure for friend
-- ----------------------------
DROP TABLE IF EXISTS `friend`;
CREATE TABLE `friend` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `friendGroupId` int(11) NOT NULL,
  `friendId` int(11) NOT NULL,
  `remark` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `friend_user` (`friendId`),
  KEY `friend_fd` (`friendGroupId`),
  CONSTRAINT `friend_fd` FOREIGN KEY (`friendGroupId`) REFERENCES `friendgroup` (`id`) ON DELETE CASCADE,
  CONSTRAINT `friend_user` FOREIGN KEY (`friendId`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of friend
-- ----------------------------
INSERT INTO `friend` VALUES ('1', '1', '2', 'bbbb');
INSERT INTO `friend` VALUES ('2', '2', '1', 'aaa');
INSERT INTO `friend` VALUES ('5', '4', '1', null);
INSERT INTO `friend` VALUES ('6', '5', '1', null);
INSERT INTO `friend` VALUES ('7', '8', '4', 'ddd');
INSERT INTO `friend` VALUES ('8', '1', '6', null);
INSERT INTO `friend` VALUES ('9', '7', '1', 'aaa');

-- ----------------------------
-- Table structure for friendgroup
-- ----------------------------
DROP TABLE IF EXISTS `friendgroup`;
CREATE TABLE `friendgroup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `friendGroup_user` (`userId`),
  CONSTRAINT `friendGroup_user` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of friendgroup
-- ----------------------------
INSERT INTO `friendgroup` VALUES ('1', 'My Friends', '1');
INSERT INTO `friendgroup` VALUES ('2', 'My Friends', '2');
INSERT INTO `friendgroup` VALUES ('3', '大学同学', '1');
INSERT INTO `friendgroup` VALUES ('4', 'My Friends', '3');
INSERT INTO `friendgroup` VALUES ('5', 'My Friends', '4');
INSERT INTO `friendgroup` VALUES ('6', 'test1', '1');
INSERT INTO `friendgroup` VALUES ('7', 'My Friends', '6');
INSERT INTO `friendgroup` VALUES ('8', '同学', '1');
INSERT INTO `friendgroup` VALUES ('9', 'My Friends', '5');

-- ----------------------------
-- Table structure for friendrequest
-- ----------------------------
DROP TABLE IF EXISTS `friendrequest`;
CREATE TABLE `friendrequest` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `requester` int(11) NOT NULL,
  `responder` int(11) NOT NULL,
  `result` int(1) NOT NULL,
  `remark` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fr_req` (`requester`),
  KEY `fr_res` (`responder`),
  CONSTRAINT `fr_req` FOREIGN KEY (`requester`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fr_res` FOREIGN KEY (`responder`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of friendrequest
-- ----------------------------
INSERT INTO `friendrequest` VALUES ('1', '1', '3', '3', null);
INSERT INTO `friendrequest` VALUES ('2', '4', '1', '3', null);
INSERT INTO `friendrequest` VALUES ('4', '5', '1', '0', null);
INSERT INTO `friendrequest` VALUES ('5', '1', '6', '3', null);
INSERT INTO `friendrequest` VALUES ('6', '6', '2', '0', null);
INSERT INTO `friendrequest` VALUES ('8', '2', '5', '0', '我是2');
INSERT INTO `friendrequest` VALUES ('12', '1', '5', '0', 'aaaaa');

-- ----------------------------
-- Table structure for group
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  `number` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of group
-- ----------------------------
INSERT INTO `group` VALUES ('1', 'qun1', '5429313');
INSERT INTO `group` VALUES ('2', 'qun2', '70263068');
INSERT INTO `group` VALUES ('3', 'qun3', '2353093');
INSERT INTO `group` VALUES ('4', '1班群', '84783109');
INSERT INTO `group` VALUES ('5', '2班群', '24851645');
INSERT INTO `group` VALUES ('6', '3班群', '25119792');

-- ----------------------------
-- Table structure for groupmessage
-- ----------------------------
DROP TABLE IF EXISTS `groupmessage`;
CREATE TABLE `groupmessage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sender` int(11) NOT NULL,
  `content` varchar(255) NOT NULL,
  `time` datetime NOT NULL,
  `groupId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `groupMessage_user` (`sender`),
  KEY `groupMessage_group` (`groupId`),
  CONSTRAINT `groupMessage_group` FOREIGN KEY (`groupId`) REFERENCES `group` (`id`) ON DELETE CASCADE,
  CONSTRAINT `groupMessage_user` FOREIGN KEY (`sender`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of groupmessage
-- ----------------------------
INSERT INTO `groupmessage` VALUES ('1', '1', 'hello', '2014-02-14 12:33:57', '1');
INSERT INTO `groupmessage` VALUES ('2', '2', 'hi', '2014-02-14 12:34:34', '1');

-- ----------------------------
-- Table structure for grouprequest
-- ----------------------------
DROP TABLE IF EXISTS `grouprequest`;
CREATE TABLE `grouprequest` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `groupId` int(11) NOT NULL,
  `result` int(1) NOT NULL,
  `remark` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `gr_user` (`userId`),
  KEY `gr_group` (`groupId`),
  CONSTRAINT `gr_group` FOREIGN KEY (`groupId`) REFERENCES `group` (`id`) ON DELETE CASCADE,
  CONSTRAINT `gr_user` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of grouprequest
-- ----------------------------
INSERT INTO `grouprequest` VALUES ('3', '1', '3', '0', null);
INSERT INTO `grouprequest` VALUES ('4', '3', '4', '1', null);

-- ----------------------------
-- Table structure for group_user
-- ----------------------------
DROP TABLE IF EXISTS `group_user`;
CREATE TABLE `group_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `groupId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `remark` varchar(5) DEFAULT NULL,
  `role` int(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `gu_user` (`userId`),
  KEY `gu_group` (`groupId`),
  CONSTRAINT `gu_group` FOREIGN KEY (`groupId`) REFERENCES `group` (`id`) ON DELETE CASCADE,
  CONSTRAINT `gu_user` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of group_user
-- ----------------------------
INSERT INTO `group_user` VALUES ('2', '2', '2', 'b', '0');
INSERT INTO `group_user` VALUES ('3', '3', '3', 'c', '0');
INSERT INTO `group_user` VALUES ('4', '4', '4', 'd', '0');
INSERT INTO `group_user` VALUES ('5', '5', '5', 'e', '0');
INSERT INTO `group_user` VALUES ('7', '6', '3', 'c', '0');
INSERT INTO `group_user` VALUES ('9', '4', '3', null, '2');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sender` int(11) NOT NULL,
  `receiver` int(11) NOT NULL,
  `content` varchar(255) NOT NULL,
  `time` datetime NOT NULL,
  `readed` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `message_user_s` (`sender`),
  KEY `message_user_r` (`receiver`),
  CONSTRAINT `message_user_r` FOREIGN KEY (`receiver`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `message_user_s` FOREIGN KEY (`sender`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1', '1', '2', 'aaa', '2014-02-12 16:14:58', '1');
INSERT INTO `message` VALUES ('2', '2', '1', 'bbb', '2014-02-12 16:15:16', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `name` varchar(5) DEFAULT NULL,
  `status` int(1) unsigned zerofill DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'a@a.com', 'a', 'a', '1');
INSERT INTO `user` VALUES ('2', 'b@b.com', 'b', 'b', '0');
INSERT INTO `user` VALUES ('3', 'c@c.com', 'c', 'c', '1');
INSERT INTO `user` VALUES ('4', 'd@d.com', 'd', 'd', '0');
INSERT INTO `user` VALUES ('5', 'e@e.com', 'e', 'e', '0');
INSERT INTO `user` VALUES ('6', 'f@f.com', 'f', 'f', '0');
