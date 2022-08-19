-- MySQL dump 10.13  Distrib 5.7.39, for Linux (x86_64)
--
-- Host: localhost    Database: recourta
-- ------------------------------------------------------
-- Server version	5.7.39

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
-- Table structure for table `Attendance`
--

DROP TABLE IF EXISTS `Attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Attendance` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `attType` int(11) DEFAULT NULL,
  `sessionId` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKht1s2t1tjwk15iolot7lndhk4` (`sessionId`),
  KEY `FKnmt31ovkt5nv39oiesbkndgq2` (`userId`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Attendance`
--

LOCK TABLES `Attendance` WRITE;
/*!40000 ALTER TABLE `Attendance` DISABLE KEYS */;
INSERT INTO `Attendance` VALUES (1,3,57,18),(2,1,70,14),(3,3,80,19),(4,1,80,16),(5,3,80,17),(6,1,80,14);
/*!40000 ALTER TABLE `Attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CheckInOut`
--

DROP TABLE IF EXISTS `CheckInOut`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CheckInOut` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `enterTime` datetime DEFAULT NULL,
  `exitTime` datetime DEFAULT NULL,
  `sessionId` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKma39gq89hm3qlj93ntq6vdolv` (`sessionId`),
  KEY `FKlxhdbrxh2r9np3dqw1khyvio9` (`userId`)
) ENGINE=MyISAM AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CheckInOut`
--

LOCK TABLES `CheckInOut` WRITE;
/*!40000 ALTER TABLE `CheckInOut` DISABLE KEYS */;
INSERT INTO `CheckInOut` VALUES (1,'2022-08-19 08:26:37',NULL,29,17),(2,'2022-08-19 08:30:56','2022-08-19 08:31:20',1,13),(3,'2022-08-19 08:39:38',NULL,12,17),(4,'2022-08-19 08:58:01','2022-08-19 08:58:17',53,17),(5,'2022-08-19 08:58:25','2022-08-19 08:58:33',57,17),(6,'2022-08-19 09:09:02','2022-08-19 09:09:21',57,18),(7,'2022-08-19 09:09:45','2022-08-19 09:12:26',57,17),(8,'2022-08-19 09:09:50','2022-08-19 09:10:49',57,18),(9,'2022-08-19 09:23:50','2022-08-19 09:26:03',57,17),(10,'2022-08-19 09:35:30','2022-08-19 09:41:46',70,16),(11,'2022-08-19 09:35:34','2022-08-19 09:37:10',70,14),(12,'2022-08-19 09:37:28','2022-08-19 09:38:22',70,14),(13,'2022-08-19 09:38:38','2022-08-19 09:39:17',70,14),(14,'2022-08-19 09:39:40','2022-08-19 09:41:02',70,14),(15,'2022-08-19 09:41:52','2022-08-19 09:42:06',70,14),(16,'2022-08-19 09:58:44','2022-08-19 09:58:59',70,14),(17,'2022-08-19 09:59:08','2022-08-19 09:59:20',49,14),(18,'2022-08-19 09:59:50','2022-08-19 10:04:17',57,17),(19,'2022-08-19 09:59:55','2022-08-19 10:01:50',57,18),(20,'2022-08-19 10:00:33','2022-08-19 10:01:27',57,19),(21,'2022-08-19 10:01:29','2022-08-19 10:02:19',57,19),(22,'2022-08-19 10:01:49','2022-08-19 10:01:54',71,7),(23,'2022-08-19 10:02:07',NULL,71,7),(24,'2022-08-19 10:02:30','2022-08-19 10:03:39',57,19),(25,'2022-08-19 10:02:47','2022-08-19 10:03:48',57,18),(26,'2022-08-19 10:02:59','2022-08-19 10:03:19',71,7),(27,'2022-08-19 10:04:50',NULL,57,17),(28,'2022-08-19 10:04:54','2022-08-19 10:06:40',57,18),(29,'2022-08-19 10:04:54','2022-08-19 10:06:40',57,19),(30,'2022-08-19 10:22:24','2022-08-19 10:22:35',80,19),(31,'2022-08-19 10:22:42','2022-08-19 10:22:51',80,17),(32,'2022-08-19 10:23:16','2022-08-19 10:23:22',80,14),(33,'2022-08-19 10:23:51','2022-08-19 10:40:09',80,20),(34,'2022-08-19 10:24:03','2022-08-19 10:29:32',80,19),(35,'2022-08-19 10:24:05','2022-08-19 10:29:02',80,16),(36,'2022-08-19 10:24:13','2022-08-19 10:40:06',80,14),(37,'2022-08-19 10:29:08','2022-08-19 10:34:07',80,16),(38,'2022-08-19 10:29:33','2022-08-19 10:29:40',80,19),(39,'2022-08-19 10:33:34','2022-08-19 10:34:33',71,7),(40,'2022-08-19 10:34:11','2022-08-19 10:39:54',80,16);
/*!40000 ALTER TABLE `CheckInOut` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Registration`
--

DROP TABLE IF EXISTS `Registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Registration` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lectureId` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKiykb3ffq2pmafi86f2cea2wy8` (`lectureId`),
  KEY `FKcslvfc1v0r8u1sfsrugxlx3n` (`userId`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Registration`
--

LOCK TABLES `Registration` WRITE;
/*!40000 ALTER TABLE `Registration` DISABLE KEYS */;
INSERT INTO `Registration` VALUES (3,8,17),(4,6,14),(5,11,14),(6,10,16),(7,10,18),(8,10,19),(9,16,19),(10,16,16),(11,16,17),(12,16,14);
/*!40000 ALTER TABLE `Registration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User_roles`
--

DROP TABLE IF EXISTS `User_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User_roles` (
  `User_userId` int(11) NOT NULL,
  `roles` varchar(255) DEFAULT NULL,
  KEY `FK3l33u1l6oeeyqk6tvu37w0bxi` (`User_userId`),
  CONSTRAINT `FK3l33u1l6oeeyqk6tvu37w0bxi` FOREIGN KEY (`User_userId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User_roles`
--

LOCK TABLES `User_roles` WRITE;
/*!40000 ALTER TABLE `User_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `User_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (21);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lecture`
--

DROP TABLE IF EXISTS `lecture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lecture` (
  `lectureId` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  `endDate` date NOT NULL,
  `lectureImg` varchar(255) DEFAULT NULL,
  `lectureTime` varchar(255) NOT NULL,
  `startDate` date NOT NULL,
  `title` varchar(255) NOT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`lectureId`),
  KEY `FKa70qkh549egfnnj71kosr4u5m` (`userId`),
  CONSTRAINT `FKa70qkh549egfnnj71kosr4u5m` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecture`
--

LOCK TABLES `lecture` WRITE;
/*!40000 ALTER TABLE `lecture` DISABLE KEYS */;
INSERT INTO `lecture` VALUES (1,'예시 강의입니다.','2022-08-30','0de8de3a-f098-4c6d-8e04-41d4bad37f6a.png','[월 14:00 ~ 16:00, 수 12:00 ~ 14:00]','2022-08-17','예시 강의1',13),(2,'예시 강의입니다.','2022-08-31','4b706142-d32d-4705-8bfb-0f2a71bdd7c0.png','[월 15:00 ~ 16:30, 수 15:00 ~ 16:30]','2022-08-17','예시 강의2',13),(3,'dsdsd','2022-08-20','c30372a4-6c40-41fb-82e7-34b6b524d471.png','[목 3:00 ~ 5:00, 금 3:00 ~ 5:00]','2022-08-18','Date 테스트',16),(4,'설명','2022-12-18','6d480213-25bd-41e6-a261-fcab2a2774a2.png','[화 13:00 ~ 15:00]','2022-08-18','테스트 강의',17),(5,'테스트','2022-10-26','7c7bf0cd-747e-4e7c-ae83-c3742ba4f4bf.png','[월 12:00 ~ 13:00, 수 14:00 ~ 16:00]','2022-08-18','테스트 강의 2',17),(6,'desc','2022-08-30','59544bac-5426-48d0-875f-85f601bac63c.png','[목 0:00 ~ 13:00, 금 12:30 ~ 14:00]','2022-08-24','강의3',17),(7,'ddd','2022-08-27','bd551cc3-d503-42ea-a5ec-bd0b6bd009e8.png','[월 0:00 ~ 2:00, 수 0:00 ~ 2:00]','2022-08-19','dddd',16),(8,'ssss','2022-08-26','d899f968-77a6-495f-a77d-438227fc387e.png','[월 0:00 ~ 3:00, 수 0:00 ~ 3:00]','2022-08-19','ssss',16),(9,'ㅎㅎ','2022-08-31','93e8ce0d-cfb3-439c-b49a-a23c7bfb5403.png','[수 1:00 ~ 2:00]','2022-08-18','강의 4',17),(10,'테스트테스트','2022-12-01','68438229-4034-4532-83ed-6f1608a475e7.png','[월 0:30 ~ 12:08]','2022-08-31','강의 5',17),(11,'ss','2022-08-26','8dbc7723-aab6-410e-bb17-7a55ad3c2d99.png','[수 0:00 ~ 3:00]','2022-08-20','ss',16),(12,'알고리즘 설명','2022-08-31','22fcc5ee-cc69-4052-83fa-44ddbdcf587d.png','[월 0:00 ~ 0:00]','2022-08-19','알고리즘',7),(13,'dd','2022-08-27','15ef87a8-c52a-437e-a687-5b0a788bfe8a.png','[수 0:00 ~ 3:00]','2022-08-19','ddddd',16),(14,'네트워크 수업입니다.','2022-08-31','00acb159-2172-40b1-af62-ad9585bbdd26.png','[수 14:01 ~ 16:00, 목 14:01 ~ 16:00]','2022-08-19','네트워크',15),(15,'desc','2022-09-08','e3753849-ae2f-42e9-8696-1eca1e05efab.png','[목 17:00 ~ 6:00, 금 11:00 ~ 13:00]','2022-09-01','강의 6',17),(16,'인공지능 개론','2022-09-01','cc0227f6-c65a-4d5e-a24f-462eac31c039.png','[월 12:00 ~ 13:00, 수 12:00 ~ 13:00, 금 12:00 ~ 13:00]','2022-08-20','인공지능',20);
/*!40000 ALTER TABLE `lecture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notice` (
  `noticeId` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `datetime` datetime DEFAULT NULL,
  `isRead` bit(1) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `lectureId` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`noticeId`),
  KEY `FKcdwjyuwpn1w2fayel20qty9ix` (`lectureId`),
  KEY `FKd9j6ggbhsxxmd276rrxsysmyn` (`userId`),
  CONSTRAINT `FKcdwjyuwpn1w2fayel20qty9ix` FOREIGN KEY (`lectureId`) REFERENCES `lecture` (`lectureId`),
  CONSTRAINT `FKd9j6ggbhsxxmd276rrxsysmyn` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `session`
--

DROP TABLE IF EXISTS `session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `session` (
  `sessionId` int(11) NOT NULL AUTO_INCREMENT,
  `endTime` datetime DEFAULT NULL,
  `startTime` datetime DEFAULT NULL,
  `lectureId` int(11) DEFAULT NULL,
  PRIMARY KEY (`sessionId`),
  KEY `FK78nwc3eyeyhumsoos4yebsdsa` (`lectureId`),
  CONSTRAINT `FK78nwc3eyeyhumsoos4yebsdsa` FOREIGN KEY (`lectureId`) REFERENCES `lecture` (`lectureId`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `session`
--

LOCK TABLES `session` WRITE;
/*!40000 ALTER TABLE `session` DISABLE KEYS */;
INSERT INTO `session` VALUES (1,'2022-08-23 01:00:00','2022-08-22 23:00:00',1),(2,'2022-08-30 01:00:00','2022-08-29 23:00:00',1),(3,'2022-08-17 23:00:00','2022-08-17 21:00:00',1),(4,'2022-08-24 23:00:00','2022-08-24 21:00:00',1),(5,'2022-08-23 01:30:00','2022-08-23 00:00:00',2),(6,'2022-08-30 01:30:00','2022-08-30 00:00:00',2),(7,'2022-08-18 01:30:00','2022-08-18 00:00:00',2),(8,'2022-08-25 01:30:00','2022-08-25 00:00:00',2),(9,'2022-09-01 01:30:00','2022-09-01 00:00:00',2),(10,'2022-08-18 14:00:00','2022-08-18 12:00:00',3),(11,'2022-08-19 14:00:00','2022-08-19 12:00:00',3),(12,'2022-08-24 00:00:00','2022-08-23 22:00:00',4),(13,'2022-08-31 00:00:00','2022-08-30 22:00:00',4),(14,'2022-09-07 00:00:00','2022-09-06 22:00:00',4),(15,'2022-09-14 00:00:00','2022-09-13 22:00:00',4),(16,'2022-09-21 00:00:00','2022-09-20 22:00:00',4),(17,'2022-09-28 00:00:00','2022-09-27 22:00:00',4),(18,'2022-10-05 00:00:00','2022-10-04 22:00:00',4),(19,'2022-10-12 00:00:00','2022-10-11 22:00:00',4),(20,'2022-10-19 00:00:00','2022-10-18 22:00:00',4),(21,'2022-10-26 00:00:00','2022-10-25 22:00:00',4),(22,'2022-11-02 00:00:00','2022-11-01 22:00:00',4),(23,'2022-11-09 00:00:00','2022-11-08 22:00:00',4),(24,'2022-11-16 00:00:00','2022-11-15 22:00:00',4),(25,'2022-11-23 00:00:00','2022-11-22 22:00:00',4),(26,'2022-11-30 00:00:00','2022-11-29 22:00:00',4),(27,'2022-12-07 00:00:00','2022-12-06 22:00:00',4),(28,'2022-12-14 00:00:00','2022-12-13 22:00:00',4),(29,'2022-08-22 22:00:00','2022-08-22 21:00:00',5),(30,'2022-08-29 22:00:00','2022-08-29 21:00:00',5),(31,'2022-09-05 22:00:00','2022-09-05 21:00:00',5),(32,'2022-09-12 22:00:00','2022-09-12 21:00:00',5),(33,'2022-09-19 22:00:00','2022-09-19 21:00:00',5),(34,'2022-09-26 22:00:00','2022-09-26 21:00:00',5),(35,'2022-10-03 22:00:00','2022-10-03 21:00:00',5),(36,'2022-10-10 22:00:00','2022-10-10 21:00:00',5),(37,'2022-10-17 22:00:00','2022-10-17 21:00:00',5),(38,'2022-10-24 22:00:00','2022-10-24 21:00:00',5),(39,'2022-08-25 01:00:00','2022-08-24 23:00:00',5),(40,'2022-09-01 01:00:00','2022-08-31 23:00:00',5),(41,'2022-09-08 01:00:00','2022-09-07 23:00:00',5),(42,'2022-09-15 01:00:00','2022-09-14 23:00:00',5),(43,'2022-09-22 01:00:00','2022-09-21 23:00:00',5),(44,'2022-09-29 01:00:00','2022-09-28 23:00:00',5),(45,'2022-10-06 01:00:00','2022-10-05 23:00:00',5),(46,'2022-10-13 01:00:00','2022-10-12 23:00:00',5),(47,'2022-10-20 01:00:00','2022-10-19 23:00:00',5),(48,'2022-10-27 01:00:00','2022-10-26 23:00:00',5),(49,'2022-08-25 22:00:00','2022-08-25 09:00:00',6),(50,'2022-08-26 23:00:00','2022-08-26 21:30:00',6),(51,'2022-08-22 11:00:00','2022-08-22 09:00:00',7),(52,'2022-08-24 11:00:00','2022-08-24 09:00:00',7),(53,'2022-08-22 12:00:00','2022-08-22 09:00:00',8),(54,'2022-08-24 12:00:00','2022-08-24 09:00:00',8),(55,'2022-08-24 11:00:00','2022-08-24 10:00:00',9),(56,'2022-08-31 11:00:00','2022-08-31 10:00:00',9),(57,'2022-09-05 21:08:00','2022-09-05 09:30:00',10),(58,'2022-09-12 21:08:00','2022-09-12 09:30:00',10),(59,'2022-09-19 21:08:00','2022-09-19 09:30:00',10),(60,'2022-09-26 21:08:00','2022-09-26 09:30:00',10),(61,'2022-10-03 21:08:00','2022-10-03 09:30:00',10),(62,'2022-10-10 21:08:00','2022-10-10 09:30:00',10),(63,'2022-10-17 21:08:00','2022-10-17 09:30:00',10),(64,'2022-10-24 21:08:00','2022-10-24 09:30:00',10),(65,'2022-10-31 21:08:00','2022-10-31 09:30:00',10),(66,'2022-11-07 21:08:00','2022-11-07 09:30:00',10),(67,'2022-11-14 21:08:00','2022-11-14 09:30:00',10),(68,'2022-11-21 21:08:00','2022-11-21 09:30:00',10),(69,'2022-11-28 21:08:00','2022-11-28 09:30:00',10),(70,'2022-08-24 12:00:00','2022-08-24 09:00:00',11),(71,'2022-08-22 09:00:00','2022-08-22 09:00:00',12),(72,'2022-08-29 09:00:00','2022-08-29 09:00:00',12),(73,'2022-08-24 12:00:00','2022-08-24 09:00:00',13),(74,'2022-08-25 01:00:00','2022-08-24 23:01:00',14),(75,'2022-09-01 01:00:00','2022-08-31 23:01:00',14),(76,'2022-08-26 01:00:00','2022-08-25 23:01:00',14),(77,'2022-09-01 15:00:00','2022-09-02 02:00:00',15),(78,'2022-09-08 15:00:00','2022-09-09 02:00:00',15),(79,'2022-09-02 22:00:00','2022-09-02 20:00:00',15),(80,'2022-08-22 22:00:00','2022-08-22 21:00:00',16),(81,'2022-08-29 22:00:00','2022-08-29 21:00:00',16),(82,'2022-08-24 22:00:00','2022-08-24 21:00:00',16),(83,'2022-08-31 22:00:00','2022-08-31 21:00:00',16),(84,'2022-08-26 22:00:00','2022-08-26 21:00:00',16);
/*!40000 ALTER TABLE `session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userId` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `isStudent` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `userImg` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (4,'jioung93@naver.com',0,'이지영','$2a$10$6AOSNo.7TDHAW1vW2hwZEOYGcnxUv1SeOUID/B41KSpB20dvzCtbm','85053940-003f-43af-86a1-e61ca5bc5031.png'),(5,'8663luv@gmail.com',1,'김이박','$2a$10$u5xB7Ada1jXPu4SV1gVn6.clGS67j08L70QOZw3Lhhs.MubIWiSja','2604874b-0764-4a29-bcfc-ba8ba1647040.png'),(6,'',-1,'유지슬','',''),(7,'jemegom603@offsala.com',0,'최인국','$2a$10$evLuaH6EQdcE4TBm7pUFGu73R.AvQ1vlVIPxmJrUBj1nFsVvAZD5y','db9f87fd-9dca-4bf3-9b59-64d0366a2779.png'),(8,'haxemi4964@offsala.com',0,'최하영','$2a$10$a03UbhejhZ0Xv8beMXtbLORlENHWQICbZNNxgHrYdej8uqccgJ5qG','be5cf617-1398-4e53-bd56-03edf72447c3.png'),(9,'kideg90670@upshopt.com',1,'김싸피수강생','$2a$10$.FmuI3O3xR8DuTz9B2y7d.UEgKAVyZUyTcajqovj5M0SiGd5A80KK','ad52864a-9f18-452b-bcc3-79a4778fe133.png'),(10,'navihih509@ukgent.com',1,'안영진','$2a$10$suE31jIubzIqIoH8oruzfuAgjemPmQ0agCPpQC5tG.hwXypPnXl.G','5b460258-bb77-4e4d-a2be-1578c4cba91c.png'),(12,'fufru@naver.com',1,'타파타','$2a$10$Y1ZqkRP/fSpsJqAxfTradeen1ShK3RCSkEnof5XooO4Q2Tgg5p9Mq','cc91b858-759d-4131-a6eb-189480a0a9f8.png'),(13,'terrykim96@naver.com',0,'김우석','$2a$10$8EFRbecloOWKTbwCKl0SWuhed3zqWwXB9ejnJbBTck9snq6Fc1GQi','e83bfd1f-b8c7-4a97-b55e-0305860c6c34.png'),(14,'melon0soda@gmail.com',1,'남궁준','$2a$10$VYLF5dLVYdJGtTSu3kaolO8bOt2gZimnD.i0svMz/Y/dotGral0Ie','8c0042b7-e367-444f-9ad1-f8a7656e3d00.png'),(15,'seul_ll@naver.com',0,'김싸피','$2a$10$3iUS1kfmQ48yAGXqlmZJT.2wh5/OoC5LAUTtzfiAH0.7yT20B3VN2','3a9195a2-2abb-42d7-a4e5-d5765f8be3a0.png'),(16,'2753dudwns@naver.com',0,'김영준','$2a$10$8ehkncrjj3/omWs/YxdzEercSgm47PHr6LAyLexhFRRpk6ztrp2x.','2348763a-f09f-4d91-a5fd-daa59b82f4d4.png'),(17,'joon5555@naver.com',0,'김싸피','$2a$10$nTmSkZGSppA0cCuLpkvDxetWGS3UcSljBnCBxBSK9QnTlX1AO/hFG','27fe2ae4-265a-4846-80d0-53523e1a092c.png'),(18,'2753dudwns@gmail.com',1,'김그작','$2a$10$wDmN9yWKMlgAyjF7x.qiGOjyY27vbN.il7Z14zB66APs958p6AYW2','bcff163b-8ee2-40fe-afda-9ce10c469ea2.png'),(19,'terrykim96@gmail.com',1,'김우석','$2a$10$lg/DeBaqE5PuRI/ZgVkMd.QDySEc9sVSfL9oWz88/zMnEqbqWQaHS','34ea118a-40cf-4678-979c-62c704d082bc.png'),(20,'jiwan1121@naver.com',0,'이지완','$2a$10$U19gmmHroKwULEFd0z2Yo.N4.gDtIP5aMcCCdih8SNmxcP7dIy4vG','0a1d049e-e044-4d10-ac0c-771e5d9e568a.png');
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

-- Dump completed on 2022-08-19  1:54:39
