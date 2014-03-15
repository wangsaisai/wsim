/*
Navicat MySQL Data Transfer

Source Server         : wsim
Source Server Version : 50535
Source Host           : 127.0.0.1:3306
Source Database       : wsim

Target Server Type    : MYSQL
Target Server Version : 50535
File Encoding         : 65001

Date: 2014-03-15 11:16:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `password` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', '1@1.com', '9FC539C795335701A65C32BE39A289F2');
INSERT INTO `admin` VALUES ('2', '2@2.com', '9FC539C795335701A65C32BE39A289F2');
INSERT INTO `admin` VALUES ('3', '5@5.com', '9FC539C795335701A65C32BE39A289F2');

-- ----------------------------
-- Table structure for `friend`
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

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
INSERT INTO `friend` VALUES ('10', '9', '1', null);
INSERT INTO `friend` VALUES ('11', '1', '5', null);

-- ----------------------------
-- Table structure for `friendgroup`
-- ----------------------------
DROP TABLE IF EXISTS `friendgroup`;
CREATE TABLE `friendgroup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `friendGroup_user` (`userId`),
  CONSTRAINT `friendGroup_user` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

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
INSERT INTO `friendgroup` VALUES ('12', 'My Friends', '9');

-- ----------------------------
-- Table structure for `friendrequest`
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
INSERT INTO `friendrequest` VALUES ('4', '5', '1', '3', null);
INSERT INTO `friendrequest` VALUES ('5', '1', '6', '3', null);
INSERT INTO `friendrequest` VALUES ('6', '6', '2', '0', null);
INSERT INTO `friendrequest` VALUES ('8', '2', '5', '0', '我是2');
INSERT INTO `friendrequest` VALUES ('12', '1', '5', '0', 'aaaaa');

-- ----------------------------
-- Table structure for `group`
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  `number` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

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
-- Table structure for `groupmessage`
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of groupmessage
-- ----------------------------
INSERT INTO `groupmessage` VALUES ('1', '1', 'hello', '2014-02-14 12:33:57', '1');
INSERT INTO `groupmessage` VALUES ('2', '2', 'hi', '2014-02-14 12:34:34', '1');
INSERT INTO `groupmessage` VALUES ('11', '1', '​111111111', '2014-03-01 15:33:09', '2');
INSERT INTO `groupmessage` VALUES ('12', '2', '​22222222', '2014-03-01 15:33:13', '2');
INSERT INTO `groupmessage` VALUES ('13', '1', '11111<br>', '2014-03-01 15:33:56', '2');

-- ----------------------------
-- Table structure for `grouprequest`
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
-- Table structure for `group_user`
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of group_user
-- ----------------------------
INSERT INTO `group_user` VALUES ('2', '2', '2', 'b', '0');
INSERT INTO `group_user` VALUES ('3', '3', '3', 'c', '0');
INSERT INTO `group_user` VALUES ('4', '4', '4', 'd', '0');
INSERT INTO `group_user` VALUES ('5', '5', '5', 'e', '0');
INSERT INTO `group_user` VALUES ('7', '6', '3', 'c', '0');
INSERT INTO `group_user` VALUES ('9', '4', '3', 'c', '2');
INSERT INTO `group_user` VALUES ('10', '1', '1', 'a', '0');
INSERT INTO `group_user` VALUES ('11', '2', '1', 'a', '1');
INSERT INTO `group_user` VALUES ('12', '3', '1', 'a', '2');

-- ----------------------------
-- Table structure for `message`
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
) ENGINE=InnoDB AUTO_INCREMENT=155 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1', '1', '2', 'aaa', '2014-02-12 16:14:58', '1');
INSERT INTO `message` VALUES ('2', '2', '1', 'bbb', '2014-02-12 16:15:16', '1');
INSERT INTO `message` VALUES ('3', '1', '2', '​aaaa', '2014-02-28 12:18:01', '1');
INSERT INTO `message` VALUES ('4', '1', '2', 'aaaaaaaaa<br>', '2014-02-28 12:19:15', '1');
INSERT INTO `message` VALUES ('5', '1', '2', '​aaa<br>', '2014-02-28 12:20:25', '1');
INSERT INTO `message` VALUES ('6', '1', '2', '啊啊啊啊啊<br>', '2014-02-28 12:21:52', '1');
INSERT INTO `message` VALUES ('7', '1', '2', '11111<br>', '2014-02-28 12:25:59', '1');
INSERT INTO `message` VALUES ('8', '1', '2', '1111111<br>', '2014-02-28 12:26:00', '1');
INSERT INTO `message` VALUES ('9', '1', '2', '111111111<br>', '2014-02-28 12:26:02', '1');
INSERT INTO `message` VALUES ('10', '1', '2', 'aaa', '2014-02-28 12:30:19', '1');
INSERT INTO `message` VALUES ('11', '1', '2', 'wwwww<br>', '2014-02-28 12:33:09', '1');
INSERT INTO `message` VALUES ('12', '1', '2', '​aaaaa', '2014-02-28 12:36:58', '1');
INSERT INTO `message` VALUES ('13', '1', '2', 'aaaaaaaa<br>', '2014-02-28 12:37:03', '1');
INSERT INTO `message` VALUES ('134', '2', '1', '11111111111111111111111<br>', '2014-02-28 18:52:02', '1');
INSERT INTO `message` VALUES ('135', '1', '2', 'zzzz', '2014-02-28 18:53:05', '1');
INSERT INTO `message` VALUES ('136', '2', '1', 'zzzzzzzz<br>', '2014-02-28 18:53:09', '1');
INSERT INTO `message` VALUES ('137', '2', '1', '<p>aaaaaa</p>', '2014-03-01 15:29:35', '1');
INSERT INTO `message` VALUES ('138', '1', '5', '​2222222222', '2014-03-01 15:30:08', '0');
INSERT INTO `message` VALUES ('139', '1', '2', '​2222222', '2014-03-01 15:32:56', '1');
INSERT INTO `message` VALUES ('140', '2', '1', '​11111111', '2014-03-01 15:33:00', '1');
INSERT INTO `message` VALUES ('141', '1', '2', '​aaaaaaaaaaaaa&nbsp;&nbsp;http://www.baidu.com', '2014-03-14 10:32:06', '0');
INSERT INTO `message` VALUES ('142', '1', '2', '好友friendGroupIdnamefriends1My FriendsfriendIdfriendGroupIdfriendUserNameremark文字通信语音通信11My Friendse语音视频8My Friendsf语音视频1My Friendsbbbbb语音视频3大学同学friendIdfriendGroupIdfriendUserNameremark文字通信语音通信6test1friendIdfriendGroupIdfriendUserNameremark文字通信语音通信8同学frie', '2014-03-14 10:32:54', '0');
INSERT INTO `message` VALUES ('143', '1', '2', 'ndIdfriendGroupIdfriendUserNameremark文字通信语音通信7同学dddd语音视频群namenumber群名片通信qun15429313aqun270263068aqun32353093a', '2014-03-14 10:32:54', '0');
INSERT INTO `message` VALUES ('144', '1', '2', '', '2014-03-14 10:35:10', '0');
INSERT INTO `message` VALUES ('145', '1', '2', '+你搜索图片地图PlayYouTube新闻Gmail更多登录&nbsp;谷歌Google.com.hk 使用下列语言：&nbsp;中文（繁體）&nbsp;English隐私权和使用条款&nbsp;设置&nbsp;Google.com广告&nbsp;商务&nbsp;Google 大全', '2014-03-14 10:35:55', '0');
INSERT INTO `message` VALUES ('146', '1', '2', '', '2014-03-14 10:38:28', '0');
INSERT INTO `message` VALUES ('147', '1', '2', '', '2014-03-14 10:40:12', '1');
INSERT INTO `message` VALUES ('148', '2', '1', '​', '2014-03-14 10:40:55', '1');
INSERT INTO `message` VALUES ('149', '1', '2', 'aaaaaaaa', '2014-03-14 10:41:20', '1');
INSERT INTO `message` VALUES ('150', '1', '2', 'http://g.hiphotos.baidu.com/image/w%3D1366%3Bcrop%3D0%2C0%2C1366%2C768/sign=3d6ee3f3d762853592e0d622a6d94da5/242dd42a2834349ba505f784cbea15ce36d3beb4.jpg', '2014-03-14 10:42:11', '1');
INSERT INTO `message` VALUES ('151', '2', '1', 'aaaaaaaaaaaaaaaaaa', '2014-03-14 10:42:26', '1');
INSERT INTO `message` VALUES ('152', '1', '2', '', '2014-03-14 10:43:37', '1');
INSERT INTO `message` VALUES ('153', '1', '6', '​&lt;br&gt;', '2014-03-14 10:50:14', '0');
INSERT INTO `message` VALUES ('154', '1', '6', '&lt;a&gt;&lt;/a&gt;', '2014-03-14 10:50:32', '0');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `password` varchar(32) NOT NULL,
  `name` varchar(10) DEFAULT NULL,
  `status` int(1) unsigned zerofill DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'a@a.com', '30911D9C25439190BDF45FC0B3A43D5B', 'a', '1');
INSERT INTO `user` VALUES ('2', 'b@b.com', '30911D9C25439190BDF45FC0B3A43D5B', 'b', '0');
INSERT INTO `user` VALUES ('3', 'c@c.com', '30911D9C25439190BDF45FC0B3A43D5B', 'c', '1');
INSERT INTO `user` VALUES ('4', 'd@d.com', '30911D9C25439190BDF45FC0B3A43D5B', 'd', '0');
INSERT INTO `user` VALUES ('5', 'e@e.com', '30911D9C25439190BDF45FC0B3A43D5B', 'e', '0');
INSERT INTO `user` VALUES ('6', 'f@f.com', '30911D9C25439190BDF45FC0B3A43D5B', 'f', '0');
INSERT INTO `user` VALUES ('9', 'ws6937@qq.com', '30911D9C25439190BDF45FC0B3A43D5B', 'ws6937', '0');
