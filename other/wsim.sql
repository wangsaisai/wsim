-- MySQL dump 10.13  Distrib 5.5.35, for Win32 (x86)
--
-- Host: localhost    Database: wsim
-- ------------------------------------------------------
-- Server version	5.5.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `password` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'1@1.com','85195AEF9A17D72949181C4C3A1B26B1'),(2,'2@2.com','85195AEF9A17D72949181C4C3A1B26B1'),(3,'5@5.com','85195AEF9A17D72949181C4C3A1B26B1'),(4,'3@3.com','85195AEF9A17D72949181C4C3A1B26B1'),(5,'4@4.com','85195AEF9A17D72949181C4C3A1B26B1');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friend`
--

DROP TABLE IF EXISTS `friend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friend`
--

LOCK TABLES `friend` WRITE;
/*!40000 ALTER TABLE `friend` DISABLE KEYS */;
INSERT INTO `friend` VALUES (1,1,2,'bbbb'),(2,2,1,'aaa'),(5,4,1,NULL),(6,5,1,NULL),(8,1,6,NULL),(9,7,1,'aaa'),(10,9,1,NULL),(11,1,5,NULL),(16,2,5,NULL),(17,9,2,NULL),(18,7,5,NULL),(19,9,6,NULL),(20,2,6,NULL),(21,7,2,NULL),(22,1,3,NULL),(23,2,3,NULL),(24,4,2,NULL);
/*!40000 ALTER TABLE `friend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friendgroup`
--

DROP TABLE IF EXISTS `friendgroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friendgroup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `friendGroup_user` (`userId`),
  CONSTRAINT `friendGroup_user` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friendgroup`
--

LOCK TABLES `friendgroup` WRITE;
/*!40000 ALTER TABLE `friendgroup` DISABLE KEYS */;
INSERT INTO `friendgroup` VALUES (1,'My Friends',1),(2,'My Friends',2),(3,'大学同学',1),(4,'My Friends',3),(5,'My Friends',4),(6,'test1',1),(7,'My Friends',6),(8,'同学',1),(9,'My Friends',5),(13,'test2',1),(14,'My Friends',10),(15,'aaa',5);
/*!40000 ALTER TABLE `friendgroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friendrequest`
--

DROP TABLE IF EXISTS `friendrequest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friendrequest`
--

LOCK TABLES `friendrequest` WRITE;
/*!40000 ALTER TABLE `friendrequest` DISABLE KEYS */;
INSERT INTO `friendrequest` VALUES (1,1,3,3,NULL),(2,4,1,3,NULL),(4,5,1,3,NULL),(5,1,6,3,NULL),(6,6,2,3,NULL),(8,2,5,3,'我是2'),(12,1,5,3,'aaaaa'),(13,1,3,3,'cccc'),(14,1,3,3,'ccc'),(15,1,3,3,'ccc'),(16,2,5,3,'test'),(17,6,5,3,'a'),(18,2,6,3,'b'),(19,2,3,3,'b');
/*!40000 ALTER TABLE `friendrequest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group`
--

DROP TABLE IF EXISTS `group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  `number` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group`
--

LOCK TABLES `group` WRITE;
/*!40000 ALTER TABLE `group` DISABLE KEYS */;
INSERT INTO `group` VALUES (1,'qun1',5429313),(2,'qun2',70263068),(4,'1班群',84783109),(5,'2班群',24851645),(6,'3班群',25119792);
/*!40000 ALTER TABLE `group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_user`
--

DROP TABLE IF EXISTS `group_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_user`
--

LOCK TABLES `group_user` WRITE;
/*!40000 ALTER TABLE `group_user` DISABLE KEYS */;
INSERT INTO `group_user` VALUES (2,2,2,'b',0),(4,4,4,'d',0),(5,5,5,'e',0),(7,6,3,'c',0),(9,4,3,'c',2),(10,1,1,'a',0),(11,2,1,'a',1),(12,2,3,'a',2),(14,2,3,NULL,2),(17,5,1,NULL,2);
/*!40000 ALTER TABLE `group_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groupmessage`
--

DROP TABLE IF EXISTS `groupmessage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groupmessage`
--

LOCK TABLES `groupmessage` WRITE;
/*!40000 ALTER TABLE `groupmessage` DISABLE KEYS */;
INSERT INTO `groupmessage` VALUES (1,1,'hello','2014-02-14 12:33:57',1),(2,2,'hi','2014-02-14 12:34:34',1),(11,1,'​111111111','2014-03-01 15:33:09',2),(12,2,'​22222222','2014-03-01 15:33:13',2),(13,1,'11111<br>','2014-03-01 15:33:56',2),(14,1,'​aaaaaaaaaaaaaaa','2014-03-15 11:55:15',2),(15,2,'​qwwwwwwwwwwwww','2014-03-15 11:55:19',2),(16,3,'​qwdqwd','2014-03-15 11:57:55',2),(17,2,'qwqw','2014-03-15 11:58:00',2),(18,1,'http://192.168.23.1:8080/wsim/module/room/joinRoom.jsp?roomId=23539168','2014-03-15 11:58:42',2),(19,2,'http://192.168.23.1:8080/wsim/module/room/joinRoom.jsp?roomId=66396321','2014-03-15 12:00:22',2),(20,1,'http://192.168.23.1:8080/wsim/module/room/joinRoom.jsp?roomId=8046824','2014-03-15 12:04:58',2),(21,2,'1222222222','2014-03-15 15:07:45',2),(22,3,'​12122222222222222','2014-03-15 15:08:13',2),(23,1,'​drrfdgfd','2014-05-24 15:08:24',5);
/*!40000 ALTER TABLE `groupmessage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grouprequest`
--

DROP TABLE IF EXISTS `grouprequest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grouprequest`
--

LOCK TABLES `grouprequest` WRITE;
/*!40000 ALTER TABLE `grouprequest` DISABLE KEYS */;
INSERT INTO `grouprequest` VALUES (4,3,4,1,NULL),(5,3,2,1,NULL),(7,1,5,1,NULL),(8,1,5,1,NULL);
/*!40000 ALTER TABLE `grouprequest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=180 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1,1,2,'aaa','2014-02-12 16:14:58',1),(2,2,1,'bbb','2014-02-12 16:15:16',1),(3,1,2,'​aaaa','2014-02-28 12:18:01',1),(4,1,2,'aaaaaaaaa<br>','2014-02-28 12:19:15',1),(5,1,2,'​aaa<br>','2014-02-28 12:20:25',1),(6,1,2,'啊啊啊啊啊<br>','2014-02-28 12:21:52',1),(7,1,2,'11111<br>','2014-02-28 12:25:59',1),(8,1,2,'1111111<br>','2014-02-28 12:26:00',1),(9,1,2,'111111111<br>','2014-02-28 12:26:02',1),(10,1,2,'aaa','2014-02-28 12:30:19',1),(11,1,2,'wwwww<br>','2014-02-28 12:33:09',1),(12,1,2,'​aaaaa','2014-02-28 12:36:58',1),(13,1,2,'aaaaaaaa<br>','2014-02-28 12:37:03',1),(134,2,1,'11111111111111111111111<br>','2014-02-28 18:52:02',1),(135,1,2,'zzzz','2014-02-28 18:53:05',1),(136,2,1,'zzzzzzzz<br>','2014-02-28 18:53:09',1),(137,2,1,'<p>aaaaaa</p>','2014-03-01 15:29:35',1),(138,1,5,'​2222222222','2014-03-01 15:30:08',0),(139,1,2,'​2222222','2014-03-01 15:32:56',1),(140,2,1,'​11111111','2014-03-01 15:33:00',1),(141,1,2,'​aaaaaaaaaaaaa&nbsp;&nbsp;http://www.baidu.com','2014-03-14 10:32:06',0),(142,1,2,'好友friendGroupIdnamefriends1My FriendsfriendIdfriendGroupIdfriendUserNameremark文字通信语音通信11My Friendse语音视频8My Friendsf语音视频1My Friendsbbbbb语音视频3大学同学friendIdfriendGroupIdfriendUserNameremark文字通信语音通信6test1friendIdfriendGroupIdfriendUserNameremark文字通信语音通信8同学frie','2014-03-14 10:32:54',0),(143,1,2,'ndIdfriendGroupIdfriendUserNameremark文字通信语音通信7同学dddd语音视频群namenumber群名片通信qun15429313aqun270263068aqun32353093a','2014-03-14 10:32:54',0),(144,1,2,'','2014-03-14 10:35:10',0),(145,1,2,'+你搜索图片地图PlayYouTube新闻Gmail更多登录&nbsp;谷歌Google.com.hk 使用下列语言：&nbsp;中文（繁體）&nbsp;English隐私权和使用条款&nbsp;设置&nbsp;Google.com广告&nbsp;商务&nbsp;Google 大全','2014-03-14 10:35:55',0),(146,1,2,'','2014-03-14 10:38:28',0),(147,1,2,'','2014-03-14 10:40:12',1),(148,2,1,'​','2014-03-14 10:40:55',1),(149,1,2,'aaaaaaaa','2014-03-14 10:41:20',1),(150,1,2,'http://g.hiphotos.baidu.com/image/w%3D1366%3Bcrop%3D0%2C0%2C1366%2C768/sign=3d6ee3f3d762853592e0d622a6d94da5/242dd42a2834349ba505f784cbea15ce36d3beb4.jpg','2014-03-14 10:42:11',1),(151,2,1,'aaaaaaaaaaaaaaaaaa','2014-03-14 10:42:26',1),(152,1,2,'','2014-03-14 10:43:37',1),(153,1,6,'​&lt;br&gt;','2014-03-14 10:50:14',0),(154,1,6,'&lt;a&gt;&lt;/a&gt;','2014-03-14 10:50:32',0),(155,1,2,'​aaaaaaaaaaa','2014-03-15 11:25:33',1),(156,1,2,'​aaaaaaaaaaaaaa','2014-03-15 11:26:16',1),(157,1,2,'111122222','2014-03-15 11:28:08',1),(158,1,2,'111111111111','2014-03-15 11:30:05',1),(159,1,2,'33333333333','2014-03-15 11:30:15',1),(160,1,2,'​1111','2014-03-15 11:33:32',1),(161,2,1,'​dfgh','2014-03-15 11:33:42',1),(162,1,2,'​aaaaaaaaa','2014-03-15 11:54:46',1),(163,2,1,'​wqqww','2014-03-15 11:54:52',1),(164,2,1,'​http://192.168.23.1:8080/wsim/module/room/joinRoom.jsp?roomId=14351860','2014-03-15 15:02:44',1),(165,2,1,'http://192.168.23.1:8080/wsim/module/room/joinRoom.jsp?roomId=96697491','2014-03-15 15:04:49',1),(166,2,1,'​122222222222','2014-03-15 15:06:05',1),(167,1,2,'​zzzzzzzzzzz','2014-03-15 15:06:13',1),(168,2,1,'​aaaaaaaaa','2014-05-04 23:38:53',1),(169,2,1,'bbbbbbbbb','2014-05-04 23:39:16',1),(170,1,2,'​aaaaaaaaa','2014-05-04 23:39:23',1),(171,1,2,'​aaaaaaa','2014-05-08 23:08:50',1),(172,1,2,'​aaaaaaa','2014-05-08 23:25:57',1),(173,3,1,'​aaaaaa','2014-05-24 10:18:04',1),(174,2,1,'​sdfsaf','2014-05-24 12:37:21',1),(175,1,2,'​dfsdfasfas','2014-05-24 12:37:33',1),(176,2,1,'ddddddddddddddddddddddd','2014-05-24 12:37:46',1),(177,2,1,'df','2014-05-24 12:41:46',0),(178,5,1,'ddddd','2014-05-24 15:06:56',1),(179,1,5,'cccc','2014-05-24 15:07:05',1);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `password` varchar(32) NOT NULL,
  `name` varchar(10) DEFAULT NULL,
  `status` int(1) unsigned zerofill DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'a@a.com','E337C6EE26F94EDE73E4806A252E0171','AAA',0),(2,'b@b.com','E337C6EE26F94EDE73E4806A252E0171','b',0),(3,'c@c.com','E337C6EE26F94EDE73E4806A252E0171','c',1),(4,'d@d.com','E337C6EE26F94EDE73E4806A252E0171','d',0),(5,'e@e.com','E337C6EE26F94EDE73E4806A252E0171','ee',0),(6,'f@f.com','E337C6EE26F94EDE73E4806A252E0171','f',0),(10,'g@g.com','E337C6EE26F94EDE73E4806A252E0171','g',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-05-24 15:26:07
