-- MySQL dump 10.13  Distrib 5.7.20, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: db_guagua
-- ------------------------------------------------------
-- Server version	5.7.21-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `t_backstage_capital`
--

DROP TABLE IF EXISTS `t_backstage_capital`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_backstage_capital` (
  `id`               INT(11) NOT NULL AUTO_INCREMENT,
  `total`            DOUBLE           DEFAULT '0',
  `last_update_time` DATETIME         DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_backstage_capital_id_uindex` (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8
  COMMENT ='后台资金';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_backstage_capital`
--

LOCK TABLES `t_backstage_capital` WRITE;
/*!40000 ALTER TABLE `t_backstage_capital`
  DISABLE KEYS */;
INSERT INTO `t_backstage_capital` VALUES (1, 10569, '2018-04-12 15:21:05');
/*!40000 ALTER TABLE `t_backstage_capital`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_backstage_cash_flow`
--

DROP TABLE IF EXISTS `t_backstage_cash_flow`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_backstage_cash_flow` (
  `id`        INT(11)  NOT NULL AUTO_INCREMENT,
  `object_id` INT(11)  NOT NULL,
  `value`     DOUBLE   NOT NULL,
  `total`     DOUBLE            DEFAULT NULL,
  `time`      DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `type`      INT(11)           DEFAULT NULL
  COMMENT '0. 企业流水, 1. 会员流水',
  `detail`    VARCHAR(256)      DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_backstage_cash_flow_id_uindex` (`id`),
  UNIQUE KEY `t_backstage_cash_flow_id_uindex_2` (`id`),
  KEY `t_backstage_cash_flow_t_user_id_fk` (`object_id`),
  CONSTRAINT `t_backstage_cash_flow_t_user_id_fk` FOREIGN KEY (`object_id`) REFERENCES `t_user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 25
  DEFAULT CHARSET = utf8
  COMMENT ='后台资金流水';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_backstage_cash_flow`
--

LOCK TABLES `t_backstage_cash_flow` WRITE;
/*!40000 ALTER TABLE `t_backstage_cash_flow`
  DISABLE KEYS */;
INSERT INTO `t_backstage_cash_flow` VALUES (1, 12, 224, 224, '2018-03-24 13:14:11', 0, '宁波红黄飞发起任务 : 这是测试'),
  (2, 12, 1122222, 1122446, '2018-03-24 13:16:22', 0, '宁波红黄飞发起任务 : 这是测试2'),
  (3, 12, -224, 1122222, '2018-03-24 15:16:14', 1, '任务这是测试取消, 资金退回'),
  (4, 12, -1122222, 0, '2018-03-24 15:17:26', 1, '任务这是测试2取消, 资金退回'),
  (5, 12, 3708, 3708, '2018-03-24 18:02:35', 0, '宁波红黄飞发起任务 : 数字标注'),
  (6, 12, -3708, 0, '2018-03-24 19:05:22', 1, '任务数字标注取消, 资金退回'),
  (8, 12, 496, 496, '2018-03-25 20:50:41', 0, '宁波红黄飞发起任务 : 宣传肉蟹煲'),
  (9, 18, -123.4, -3212, '2018-03-30 13:08:25', 1, '任务数字标注取消, 企业支付违约金'),
  (10, 15, -123.4, -6920, '2018-03-30 13:08:25', 1, '任务数字标注取消, 企业支付违约金'),
  (11, 12, -3461.2, -10628, '2018-03-30 13:08:25', 1, '任务数字标注取消, 资金退回'),
  (12, 15, -12.200000000000001, -11124, '2018-03-31 12:08:25', 1, '任务宣传肉蟹煲取消, 企业支付违约金'),
  (13, 12, -483.8, -11620, '2018-03-31 12:08:25', 1, '任务宣传肉蟹煲取消, 资金退回'),
  (14, 12, 1266, -10354, '2018-03-31 21:39:30', 0, '宁波红黄飞发起任务 : 这是关于一个市场调研的项目'),
  (15, 12, 1300, -9054, '2018-04-01 08:28:27', 0, '宁波红黄飞发起任务 : 调查附近居民的健身情况'),
  (16, 12, 1244, -7810, '2018-04-01 08:31:25', 0, '宁波红黄飞发起任务 : 这是一个关于蔬菜价格的任务'),
  (17, 12, -1244, -9054, '2018-04-04 12:09:31', 1, '任务这是一个关于蔬菜价格的任务取消, 资金退回'),
  (18, 12, 2145, -6909, '2018-04-08 19:30:39', 0, '宁波红黄飞发起任务 : 小黄车'),
  (19, 12, 112, -6797, '2018-04-08 20:49:36', 0, '宁波红黄飞发起任务 : 调查饭菜情况'),
  (20, 12, 1300, -5497, '2018-04-09 15:55:36', 0, '宁波红黄飞发起任务 : 查看学校附近的小黄车'),
  (21, 12, 12244, 6747, '2018-04-11 11:59:41', 0, '宁波红黄飞发起任务 : 调查大学生的月生活费情况'),
  (22, 12, 1300, 8047, '2018-04-11 18:19:24', 0, '宁波红黄飞发起任务 : 调查最近大学生的睡眠情况'),
  (23, 12, 1300, 9347, '2018-04-12 12:37:08', 0, '宁波红黄飞发起任务 : 小黄车的调查'),
  (24, 12, 1222, 10569, '2018-04-12 15:21:05', 0, '宁波红黄飞发起任务 : 小黄车的使用情况二');
/*!40000 ALTER TABLE `t_backstage_cash_flow`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_bind_task_knowledge`
--

DROP TABLE IF EXISTS `t_bind_task_knowledge`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_bind_task_knowledge` (
  `id`           INT(11)  NOT NULL AUTO_INCREMENT,
  `task_id`      INT(11)  NOT NULL,
  `knowledge_id` INT(11)  NOT NULL,
  `bind_time`    DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_bind_task_knowledge_id_uindex` (`id`),
  KEY `t_bind_task_knowledge_t_task_id_fk` (`task_id`),
  KEY `t_bind_task_knowledge_t_knowledge_base_id_fk` (`knowledge_id`),
  CONSTRAINT `t_bind_task_knowledge_t_knowledge_base_id_fk` FOREIGN KEY (`knowledge_id`) REFERENCES `t_knowledge_base` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `t_bind_task_knowledge_t_task_id_fk` FOREIGN KEY (`task_id`) REFERENCES `t_task` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 24
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_bind_task_knowledge`
--

LOCK TABLES `t_bind_task_knowledge` WRITE;
/*!40000 ALTER TABLE `t_bind_task_knowledge`
  DISABLE KEYS */;
INSERT INTO `t_bind_task_knowledge`
VALUES (10, 2, 1, '2018-03-29 21:07:17'), (12, 5, 1, '2018-03-31 19:34:23'), (14, 4, 8, '2018-03-31 19:52:17'),
  (15, 4, 1, '2018-03-31 19:52:17'), (17, 2, 8, '2018-03-31 19:54:11'), (18, 5, 8, '2018-03-31 19:54:16'),
  (20, 6, 1, '2018-03-31 21:40:16'), (21, 6, 12, '2018-04-03 16:52:30'), (22, 6, 13, '2018-04-10 18:49:37'),
  (23, 5, 13, '2018-04-10 22:05:02');
/*!40000 ALTER TABLE `t_bind_task_knowledge`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_customer_info`
--

DROP TABLE IF EXISTS `t_customer_info`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_customer_info` (
  `uuid`         VARCHAR(32) NOT NULL,
  `name`         VARCHAR(32) DEFAULT NULL,
  `sex`          INT(11)     DEFAULT NULL,
  `phone`        VARCHAR(11) DEFAULT NULL,
  `email`        VARCHAR(64) DEFAULT NULL,
  `age`          INT(11)     DEFAULT NULL,
  `create_time`  INT(11)     DEFAULT NULL,
  `task_id`      INT(11)     DEFAULT NULL,
  `processor_id` INT(11)     DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `t_customer_info_t_task_id_fk` (`task_id`),
  CONSTRAINT `t_customer_info_t_task_id_fk` FOREIGN KEY (`task_id`) REFERENCES `t_task` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_customer_info`
--

LOCK TABLES `t_customer_info` WRITE;
/*!40000 ALTER TABLE `t_customer_info`
  DISABLE KEYS */;
INSERT INTO `t_customer_info` VALUES ('07F6FFA1606DC650BED9E261BF61495B', '小猪', NULL, NULL, NULL, NULL, NULL, 12, 15),
  ('0A2DB23521ECBA1B02442D4C0512D8A7', '小猪', NULL, NULL, NULL, NULL, NULL, 12, NULL),
  ('1628793457C0E0AC3CF3814472B777F2', '小猪', NULL, NULL, NULL, NULL, NULL, 12, 15),
  ('179EF6596497ABCFEFDD9E6A590EF67E', '小猪', NULL, NULL, NULL, NULL, NULL, 12, 15),
  ('1ABE9A9B3919110F235FA73AD4B980F6', '小猪', NULL, NULL, NULL, NULL, NULL, 12, 15),
  ('231CA23B9996C22036A44DBDE80D0E26', '小猪', NULL, NULL, NULL, NULL, NULL, 12, 15),
  ('2558234D59DB6F4F35B5D6F6C0AA5DB1', '小猪', NULL, NULL, NULL, NULL, NULL, 12, NULL),
  ('2817A788AD91FCBA8CF30A01A8C34877', '小猪', NULL, NULL, NULL, NULL, NULL, 12, NULL),
  ('2A105A8CD9598B8C6ED518A8F1DF2062', '小猪', NULL, NULL, NULL, NULL, NULL, 12, 15),
  ('2AFA1DD79FA954DBF5C8986EF3112A3A', '小猪', NULL, NULL, NULL, NULL, NULL, 12, NULL),
  ('34E90FF6390765EDF99959472A350F1B', '小猪', NULL, NULL, NULL, NULL, NULL, 12, 15),
  ('35933D9EBADCDF2D5547E03370027370', '小猪', NULL, NULL, NULL, NULL, NULL, 12, 15),
  ('35AED09E7538AEEA7D2123AF251B0897', '小猪', NULL, NULL, NULL, NULL, NULL, 12, 15),
  ('40317109F7EB5FB408D3D7BE98125B66', '小猪', NULL, NULL, NULL, NULL, NULL, 12, 15),
  ('42495B0C820CDCE305EE840B487D57DD', '小猪', NULL, NULL, NULL, NULL, NULL, 12, NULL),
  ('47C68599076C6B48355A956C414D6040', '小猪', NULL, NULL, NULL, NULL, NULL, 12, 15),
  ('49977A1C02632F4FE4295442FC94ACEE', '小猪', NULL, NULL, NULL, NULL, NULL, 12, 15),
  ('4BD2389FD7871C64180AA97DC90BD7CC', '小猪', NULL, NULL, NULL, NULL, NULL, 12, 15),
  ('4E08B5186F706DE8686A725B27946352', '小猪', NULL, NULL, NULL, NULL, NULL, 12, 15),
  ('540C28026DE8CC8EE34AB244BC758A82', '小猪', NULL, NULL, NULL, NULL, NULL, 12, 15),
  ('561F50864169A2D83EB8C5AD1A000037', '小猪', NULL, NULL, NULL, NULL, NULL, 12, NULL),
  ('66AE019470AFBFB0F4A4D50EFEE6C7C0', '小猪', NULL, NULL, NULL, NULL, NULL, 12, 15),
  ('67F5AB344A6913D35267C9464E320605', '小猪', NULL, NULL, NULL, NULL, NULL, 12, 15),
  ('687FBB51A9F2C2D016EE0FF76D526CFC', '小猪', NULL, NULL, NULL, NULL, NULL, 12, 15),
  ('693FD4D9E583D50DFD25D8A36B9B04C6', '小猪', NULL, NULL, NULL, NULL, NULL, 12, NULL),
  ('8905D0EEE711132C31988B51D26C02C1', '小猪', NULL, NULL, NULL, NULL, NULL, 12, 15),
  ('8AFB5D58ED19A173FAD3C15D079CFC64', '小猪', NULL, NULL, NULL, NULL, NULL, 12, 15),
  ('8C553B4CCB07B567AE630DD8A63CE4B6', '小猪', NULL, NULL, NULL, NULL, NULL, 12, 15),
  ('94B6C95ACD5682E2CE46D7752D88C368', '小猪', NULL, NULL, NULL, NULL, NULL, 12, 15),
  ('981A3D3F8DAE3437501A12F6F7F053AA', '小猪', NULL, NULL, NULL, NULL, NULL, 12, NULL),
  ('9F27B78E2C5A26BEFE3BA64FF9496658', '小猪', NULL, NULL, NULL, NULL, NULL, 12, NULL),
  ('A9B925011827B1762F78265AFC18C7D6', '小猪', NULL, NULL, NULL, NULL, NULL, 12, 15),
  ('B59BFE7721F035103466F92694EF68FC', '小猪', NULL, NULL, NULL, NULL, NULL, 12, 15),
  ('B7748E291F24B2D21EBF0D01DCA307E7', '小猪', NULL, NULL, NULL, NULL, NULL, 12, 15),
  ('B91630D9675047BEB3B737935D108656', '小猪', NULL, NULL, NULL, NULL, NULL, 12, 15),
  ('BD0F23DDB0CA911173A4B8D47B96A1F0', '小猪', NULL, NULL, NULL, NULL, NULL, 12, NULL),
  ('C2D753B543CDB0BDF5C80E320A740B28', '小猪', NULL, NULL, NULL, NULL, NULL, 12, 15),
  ('C8492BA721D1EE8B389C511C74194A8D', '小猪', NULL, NULL, NULL, NULL, NULL, 12, NULL),
  ('D647210A3B93CE7737309A9278799B92', '小猪', NULL, NULL, NULL, NULL, NULL, 12, NULL),
  ('D747210A3B93CE7737309A9278799B92', '小猪', NULL, NULL, NULL, NULL, NULL, 12, NULL),
  ('D7D2D088C99733BAC05D3EDA3A089522', '小猪', NULL, NULL, NULL, NULL, NULL, 12, 15),
  ('D817D7D4A24ED9A16F358A2B2670C9BF', '小猪', NULL, NULL, NULL, NULL, NULL, 12, NULL),
  ('DD55788CFDB91753A6862656DE1D4837', '小猪', NULL, NULL, NULL, NULL, NULL, 12, 15),
  ('E14F9BE5D5776FE80528B49B7C88883C', '小猪', NULL, NULL, NULL, NULL, NULL, 12, 15),
  ('E150620E9073A7AC9BE1DAFBA574D091', '小猪', NULL, NULL, NULL, NULL, NULL, 12, 15),
  ('E381FCAA7D4184F819839CFCFF2898CB', '小猪', NULL, NULL, NULL, NULL, NULL, 12, 15),
  ('EE5EA0FCAEC3D372EA07383125AFA95B', '小猪', NULL, NULL, NULL, NULL, NULL, 12, NULL),
  ('EFB4CB6473EFDF77AF49F2BCA1714EF2', '小猪', NULL, NULL, NULL, NULL, NULL, 12, 15),
  ('F60E827D38B9D845934E6F85AF94A2B4', '小猪', NULL, NULL, NULL, NULL, NULL, 12, 15),
  ('F8A4D28E0C6C136C0396DE454BA88D46', '小猪', NULL, NULL, NULL, NULL, NULL, 12, NULL);
/*!40000 ALTER TABLE `t_customer_info`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_email_validate_code`
--

DROP TABLE IF EXISTS `t_email_validate_code`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_email_validate_code` (
  `id`        INT(11)    NOT NULL AUTO_INCREMENT,
  `email`     VARCHAR(128)        DEFAULT NULL,
  `code`      VARCHAR(6) NOT NULL,
  `send_time` DATETIME   NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id`   INT(11)    NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_email_validate_code_id_uindex` (`id`),
  UNIQUE KEY `t_email_validate_code_email_uindex` (`email`),
  KEY `t_email_validate_code_t_user_id_fk` (`user_id`),
  CONSTRAINT `t_email_validate_code_t_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='保存验证码';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_email_validate_code`
--

LOCK TABLES `t_email_validate_code` WRITE;
/*!40000 ALTER TABLE `t_email_validate_code`
  DISABLE KEYS */;
/*!40000 ALTER TABLE `t_email_validate_code`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_enterprise_cash_flow`
--

DROP TABLE IF EXISTS `t_enterprise_cash_flow`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_enterprise_cash_flow` (
  `id`          INT(11)  NOT NULL AUTO_INCREMENT,
  `property_id` INT(11)  NOT NULL,
  `value`       DOUBLE            DEFAULT NULL,
  `detail`      VARCHAR(128)      DEFAULT NULL,
  `type`        INT(11)           DEFAULT NULL,
  `time`        DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `balance`     DOUBLE            DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_enterprise_cash_flow_id_uindex` (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 49
  DEFAULT CHARSET = utf8
  COMMENT ='企业资金流明细';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_enterprise_cash_flow`
--

LOCK TABLES `t_enterprise_cash_flow` WRITE;
/*!40000 ALTER TABLE `t_enterprise_cash_flow`
  DISABLE KEYS */;
INSERT INTO `t_enterprise_cash_flow`
VALUES (18, 1, 12, '充值', 3, '2018-03-21 14:58:09', 12), (19, 1, 129999999, '充值', 3, '2018-03-21 14:58:13', 130000011),
  (20, 1, -123456, '提现操作', 1, '2018-03-21 14:58:24', 129876555),
  (21, 1, -1111, '提现操作', 1, '2018-03-21 14:58:29', 129875444),
  (22, 1, -111111, '提现操作', 1, '2018-03-21 14:58:32', 129764333),
  (23, 1, -11111111, '提现操作', 1, '2018-03-21 14:58:34', 118653222),
  (24, 1, -111111111, '提现操作', 1, '2018-03-21 14:58:37', 7542111),
  (25, 1, -200000, '提现操作', 1, '2018-03-21 15:44:06', 7342111),
  (26, 1, -90000, '提现操作', 1, '2018-03-21 15:44:40', 7252111),
  (27, 1, -52111, '提现操作', 1, '2018-03-21 15:46:54', 7200000),
  (28, 1, -224, '发布任务: 这是测试', 2, '2018-03-24 13:14:10', 7199776),
  (29, 1, -1122222, '发布任务: 这是测试2', 2, '2018-03-24 13:16:22', 6077554),
  (30, 1, 6, '充值', 3, '2018-03-24 15:12:28', 6077560),
  (31, 1, 224, '任务#这是测试#取消, 退回资金', 4, '2018-03-24 15:16:13', 6077784),
  (32, 1, 1122222, '任务#这是测试2#取消, 退回资金', 4, '2018-03-24 15:17:26', 7200006),
  (33, 1, -3708, '发布任务: 数字标注', 2, '2018-03-24 18:02:35', 7196298),
  (34, 1, 3708, '任务#数字标注#取消, 退回资金', 4, '2018-03-24 19:05:22', 7200006),
  (35, 1, 3708, '任务#数字标注#取消, 退回资金', 4, '2018-03-24 19:05:22', 7200006),
  (36, 1, -496, '发布任务: 宣传肉蟹煲', 2, '2018-03-25 20:50:41', 7199510),
  (37, 1, -1266, '发布任务: 这是关于一个市场调研的项目', 2, '2018-03-31 21:39:30', 7202189),
  (38, 1, -1300, '发布任务: 调查附近居民的健身情况', 2, '2018-04-01 08:28:27', 7200889),
  (39, 1, -1244, '发布任务: 这是一个关于蔬菜价格的任务', 2, '2018-04-01 08:31:25', 7199645),
  (40, 1, 20000, '充值', 3, '2018-04-03 19:19:29', 7219645),
  (41, 1, -2145, '发布任务: 小黄车', 2, '2018-04-08 19:30:39', 7218744),
  (42, 1, -112, '发布任务: 调查饭菜情况', 2, '2018-04-08 20:49:36', 7218632),
  (43, 1, -1300, '发布任务: 查看学校附近的小黄车', 2, '2018-04-09 15:55:36', 7217332),
  (44, 1, 1000, '充值', 3, '2018-04-09 16:08:35', 7218332),
  (45, 1, -12244, '发布任务: 调查大学生的月生活费情况', 2, '2018-04-11 11:59:41', 7206088),
  (46, 1, -1300, '发布任务: 调查最近大学生的睡眠情况', 2, '2018-04-11 18:19:24', 7204788),
  (47, 1, -1300, '发布任务: 小黄车的调查', 2, '2018-04-12 12:37:08', 7203488),
  (48, 1, -1222, '发布任务: 小黄车的使用情况二', 2, '2018-04-12 15:21:05', 7202266);
/*!40000 ALTER TABLE `t_enterprise_cash_flow`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_enterprise_property`
--

DROP TABLE IF EXISTS `t_enterprise_property`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_enterprise_property` (
  `id`                  INT(11)  NOT NULL AUTO_INCREMENT,
  `user_id`             INT(11)  NOT NULL,
  `balance`             DOUBLE   NOT NULL DEFAULT '0',
  `recharge_total`      DOUBLE   NOT NULL DEFAULT '0',
  `withdraw_cash_total` DOUBLE            DEFAULT '0',
  `pay_total`           DOUBLE   NOT NULL DEFAULT '0',
  `create_time`         DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_update_time`    DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_enterprise_property_id_uindex` (`id`),
  KEY `t_enterprise_property_t_user_id_fk` (`user_id`),
  CONSTRAINT `t_enterprise_property_t_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8
  COMMENT ='企业资产信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_enterprise_property`
--

LOCK TABLES `t_enterprise_property` WRITE;
/*!40000 ALTER TABLE `t_enterprise_property`
  DISABLE KEYS */;
INSERT INTO `t_enterprise_property`
VALUES (1, 12, 7202266, 130021017, 122800011, 1150083, '2018-03-19 22:12:05', '2018-04-12 15:21:05');
/*!40000 ALTER TABLE `t_enterprise_property`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_handle_enterprise_authentication`
--

DROP TABLE IF EXISTS `t_handle_enterprise_authentication`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_handle_enterprise_authentication` (
  `id`             INT(11)  NOT NULL AUTO_INCREMENT,
  `apply_id`       INT(11)  NOT NULL,
  `apply_user_id`  INT(11)  NOT NULL,
  `handle_user_id` INT(11)  NOT NULL,
  `result`         TINYINT(1)        DEFAULT NULL,
  `comment`        VARCHAR(256)      DEFAULT NULL,
  `handle_time`    DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_handle_enterprise_authentication_id_uindex` (`id`),
  KEY `t_handle_enterprise_authentication_t_user_id_fk` (`apply_user_id`),
  KEY `t_handle_enterprise_authentication_t_user_id_fk_2` (`handle_user_id`),
  KEY `tt_handle_enterprise_authentication___fk` (`apply_id`),
  CONSTRAINT `t_handle_enterprise_authentication_t_user_id_fk` FOREIGN KEY (`apply_user_id`) REFERENCES `t_user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `t_handle_enterprise_authentication_t_user_id_fk_2` FOREIGN KEY (`handle_user_id`) REFERENCES `t_user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `tt_handle_enterprise_authentication___fk` FOREIGN KEY (`apply_id`) REFERENCES `t_real_name_authentication_of_enterprise` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8
  COMMENT ='企业信息认证结果';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_handle_enterprise_authentication`
--

LOCK TABLES `t_handle_enterprise_authentication` WRITE;
/*!40000 ALTER TABLE `t_handle_enterprise_authentication`
  DISABLE KEYS */;
INSERT INTO `t_handle_enterprise_authentication`
VALUES (1, 3, 12, 1, 0, 'ojbk', '2018-03-18 20:47:35'), (2, 4, 17, 1, 0, '信息不准确', '2018-03-24 14:07:54');
/*!40000 ALTER TABLE `t_handle_enterprise_authentication`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_handle_member_authentication`
--

DROP TABLE IF EXISTS `t_handle_member_authentication`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_handle_member_authentication` (
  `id`             INT(11)  NOT NULL AUTO_INCREMENT,
  `apply_id`       INT(11)  NOT NULL,
  `apply_user_id`  INT(11)  NOT NULL,
  `handle_user_id` INT(11)  NOT NULL,
  `result`         TINYINT(1)        DEFAULT NULL,
  `comment`        VARCHAR(256)      DEFAULT NULL,
  `handle_time`    DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_handle_member_authentication_id_uindex` (`id`),
  KEY `t_handle_member_authentication_t_user_id_fk` (`apply_user_id`),
  KEY `t_handle_member_authentication_t_user_id_fk_2` (`handle_user_id`),
  KEY `tt_handle_member_authentication___fk` (`apply_id`),
  CONSTRAINT `t_handle_member_authentication_t_user_id_fk` FOREIGN KEY (`apply_user_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `t_handle_member_authentication_t_user_id_fk_2` FOREIGN KEY (`handle_user_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `tt_handle_member_authentication___fk` FOREIGN KEY (`apply_id`) REFERENCES `t_real_name_authentication_of_member` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8
  COMMENT ='处理会员认证信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_handle_member_authentication`
--

LOCK TABLES `t_handle_member_authentication` WRITE;
/*!40000 ALTER TABLE `t_handle_member_authentication`
  DISABLE KEYS */;
INSERT INTO `t_handle_member_authentication`
VALUES (1, 5, 15, 1, 0, '证件照错误', '2018-03-18 13:21:05'), (2, 5, 15, 1, 0, 'ojbk', '2018-03-18 14:22:37'),
  (3, 6, 16, 1, 0, '', '2018-03-24 13:33:47');
/*!40000 ALTER TABLE `t_handle_member_authentication`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_knowledge_base`
--

DROP TABLE IF EXISTS `t_knowledge_base`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_knowledge_base` (
  `id`          INT(11)     NOT NULL AUTO_INCREMENT,
  `name`        VARCHAR(32) NOT NULL,
  `description` VARCHAR(512)         DEFAULT NULL,
  `creator_id`  INT(11)     NOT NULL,
  `create_time` DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_knowledge_base_id_uindex` (`id`),
  KEY `t_knowledge_base_t_user_id_fk` (`creator_id`),
  CONSTRAINT `t_knowledge_base_t_user_id_fk` FOREIGN KEY (`creator_id`) REFERENCES `t_user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 14
  DEFAULT CHARSET = utf8
  COMMENT ='知识库';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_knowledge_base`
--

LOCK TABLES `t_knowledge_base` WRITE;
/*!40000 ALTER TABLE `t_knowledge_base`
  DISABLE KEYS */;
INSERT INTO `t_knowledge_base`
VALUES (1, '测试知识库', '这是一个测试的知识库', 12, '2018-03-18 20:48:48'), (8, '测试知识库', '这是一个测试的知识库', 12, '2018-03-26 15:41:17'),
  (10, '这是一个知识库12', '12', 12, '2018-04-03 16:51:36'), (11, '这是一个知识库13', '13', 12, '2018-04-03 16:51:48'),
  (12, '这是一个知识库14', '14', 12, '2018-04-03 16:52:03'), (13, '小米产品知识库', '关于小米生态链产品的介绍', 12, '2018-04-10 15:54:56');
/*!40000 ALTER TABLE `t_knowledge_base`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_knowledge_base_item`
--

DROP TABLE IF EXISTS `t_knowledge_base_item`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_knowledge_base_item` (
  `id`                INT(11)                        NOT NULL AUTO_INCREMENT,
  `knowledge_base_id` INT(11)                                 DEFAULT NULL,
  `question`          VARCHAR(32) CHARACTER SET utf8 NOT NULL,
  `answer`            TEXT CHARACTER SET utf8        NOT NULL,
  `similar_question1` VARCHAR(32) CHARACTER SET utf8          DEFAULT NULL,
  `similar_question2` VARCHAR(32) CHARACTER SET utf8          DEFAULT NULL,
  `similar_question3` VARCHAR(32) CHARACTER SET utf8          DEFAULT NULL,
  `similar_question4` VARCHAR(32) CHARACTER SET utf8          DEFAULT NULL,
  `similar_question5` VARCHAR(32) CHARACTER SET utf8          DEFAULT NULL,
  `create_time`       DATETIME                       NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_knowledge_base_item_id_uindex` (`id`),
  KEY `t_knowledge_base_item_t_knowledge_base_id_fk` (`knowledge_base_id`),
  FULLTEXT KEY `question` (`question`, `answer`, `similar_question1`, `similar_question2`, `similar_question3`, `similar_question4`, `similar_question5`) /*!50100 WITH PARSER `ngram` */
)
  ENGINE = MyISAM
  AUTO_INCREMENT = 20
  DEFAULT CHARSET = utf8mb4
  COMMENT ='知识库条目';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_knowledge_base_item`
--

LOCK TABLES `t_knowledge_base_item` WRITE;
/*!40000 ALTER TABLE `t_knowledge_base_item`
  DISABLE KEYS */;
INSERT INTO `t_knowledge_base_item`
VALUES (6, 1, '你觉得怎么样', '好很好非常好', '啥', '怎么样', '怎样', '么样', '到底怎么样', '2018-03-20 20:25:18'),
  (8, 8, '你觉得学习食堂 饭好吃嘛', '还行', '好吃吗', '怎么样', '到底怎么样', '你还想怎么样', '你还', '2018-03-31 19:45:15'),
  (9, 8, '你觉得 学校的寝室干净吗', '干净 都可以睡在地上啦', '寝室干净吗', '干净吗？', '到底干不干净', '你说到底干净吗?', '可以住嘛？', '2018-03-31 19:47:23'),
  (12, 12, '123', '123', '1233', '12333', '333', '3333', '22222', '2018-04-03 16:52:21'),
  (13, 1, '你好好啊', '12312312', '12312', '123 123123', '123123', '123123', '123123', '2018-04-05 13:21:39'),
  (14, 13, '小米6配置如何?', '小米6采用了5.15英寸1080P护眼屏，搭载骁龙835处理器，辅以6GB内存和64/128GB机身存储；内置3350mAh电池，支持18W快充；后置双摄为1200万像素长焦镜头和1200万像素广角镜头，支持2倍光学变焦和四轴防抖，前置800万像素摄像头，支持自拍实时美颜。\n此外该机还支持生活防水、多功能NFC，支持微信和支付宝指纹支付，网络方面采用2×2双路WiFi，支持双卡双待和全网通4.0。', '小米6怎么样?', '小米6性能如何?', '小米6好用么', NULL, NULL, '2018-04-10 15:57:59'),
  (15, 13, '小米note3配置如何?', '小米Note3支持了基于AI算法开发的人脸解锁功能，匹配精度更高，安全性更好，搭载1600万高像素前置镜头，支持更加有针对性的人工智能算法美颜。\n采用5.5英寸全高清显示屏，配备3500mAh大容量电池，采用和小米6相同的广角镜头+长焦镜头的双摄方案，双1200万像素，支持2倍光学变焦拍摄。\n小米Note 3搭载高通最新一代定位中高端市场的骁龙660八核移动平台，采用14纳米低功耗制程工艺打造，主频高达2.2GHz。小米Note3全系配备6GB超大运存，任务多开更流畅。', '小米note3怎么样?', '小米note3值得买么?', NULL, NULL, NULL, '2018-04-10 16:00:07'),
  (16, 13, '小爱同学是什么?', '小爱同学是小米公司于2017年7月26日发布的首款人工智能（AI）音箱的唤醒词及二次元人物形象，音箱售价299元，2017年8月将开启公测，9月26日正式开售.\n作为唤醒词，小爱同学已经成为小米AI音箱的代名词，只要用户对着音箱说出“小爱同学”几个字便可唤醒音箱并与其进行语音交流，完成多种预设技能。\n内容收听\n小米AI音箱可以播放音乐、电台点播，还有相声、小说、脱口秀，教育学习、儿童类多种有声读物内容。\n技术中心\n小米AI音箱是支持语音交互，内容包括在线音乐、网络电台、有声读物、广播电台等，提供新闻、天气、闹钟、倒计时、备忘、提醒、时间、汇率、股票、限行、算数、查找手机、百科/问答、闲聊、笑话、菜谱、翻译等各类功能。\n智能家居\n小米AI（人工智能）音箱可控制小米电视、扫地机器人、空气净化器等小米及生态链设备，也可通过小米插座、插线板来控制第三方产品。', '小爱同学什么样的产品?', '小爱同学能干嘛?', '小爱同学都会做什么?', NULL, NULL, '2018-04-10 16:15:48'),
  (17, 13, '小米6售价多少?', '小米6 全网通 4GB+64GB 亮黑色 移动联通电信4G手机 双卡双待 2199元\n小米6 全网通 4GB+64GB 亮蓝色 移动联通电信4G手机 双卡双待 2199元\n小米6 全网通 6GB+64GB 亮黑色 移动联通电信4G手机 双卡双待 2599元\n小米6 全网通 6GB+128GB 亮黑色 移动联通电信4G手机 双卡双待 2899元\n小米6 全网通 6GB+128GB 陶瓷黑尊享版 移动联通电信4G手机 双卡双待 3099元\n', '小米6多少钱?', '小米6价格多少', NULL, NULL, NULL, '2018-04-10 16:15:48'),
  (18, 13, '小米note3售价多少?', '小米Note3 美颜双摄拍照手机 4GB+64GB 亮黑色 全网通4G手机 双卡双待 1799元\n小米Note3 美颜双摄拍照手机 6GB+64GB 黑色 全网通4G手机 双卡双待 1899元\n小米Note3 美颜双摄拍照手机 6GB+128GB 黑色 全网通4G手机 双卡双待 2599元', '小米note3多少钱?', '小米note3价格多少', NULL, NULL, NULL, '2018-04-10 16:15:48'),
  (19, 13, '小爱同学售价多少?', '小米AI音箱 299元\n小米小爱音箱mini 169元\n', '小爱同学多少钱?', '小爱同学价格多少?', NULL, NULL, NULL, '2018-04-10 16:15:48');
/*!40000 ALTER TABLE `t_knowledge_base_item`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_member_cash_flow`
--

DROP TABLE IF EXISTS `t_member_cash_flow`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_member_cash_flow` (
  `id`          INT(11)  NOT NULL AUTO_INCREMENT,
  `property_id` INT(11)  NOT NULL,
  `vale`        DOUBLE   NOT NULL,
  `type`        INT(11)           DEFAULT NULL,
  `time`        DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `detail`      VARCHAR(128)      DEFAULT NULL,
  `balance`     DOUBLE            DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_member_cash_flow_id_uindex` (`id`),
  KEY `t_member_cash_flow_t_member_property_id_fk` (`property_id`),
  CONSTRAINT `t_member_cash_flow_t_member_property_id_fk` FOREIGN KEY (`property_id`) REFERENCES `t_member_property` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 8
  DEFAULT CHARSET = utf8
  COMMENT ='会员资金流明细';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_member_cash_flow`
--

LOCK TABLES `t_member_cash_flow` WRITE;
/*!40000 ALTER TABLE `t_member_cash_flow`
  DISABLE KEYS */;
INSERT INTO `t_member_cash_flow` VALUES (3, 2, 0, 1, '2018-03-21 22:51:48', '提现操作', 0),
  (4, 5, 123.4, 0, '2018-03-30 13:08:25', '任务#数字标注#取消, 企业支付违约金', 123.4),
  (5, 2, 123.4, 0, '2018-03-30 13:08:25', '任务#数字标注#取消, 企业支付违约金', 123.4),
  (6, 2, 12.200000000000001, 0, '2018-03-31 12:08:25', '任务#宣传肉蟹煲#取消, 企业支付违约金', 135.6),
  (7, 2, -0.6, 1, '2018-03-31 21:19:41', '提现操作', 135);
/*!40000 ALTER TABLE `t_member_cash_flow`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_member_property`
--

DROP TABLE IF EXISTS `t_member_property`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_member_property` (
  `id`                  INT(11)  NOT NULL AUTO_INCREMENT,
  `user_id`             INT(11)  NOT NULL,
  `balance`             DOUBLE            DEFAULT '0'
  COMMENT '余额',
  `withdraw_cash_total` DOUBLE            DEFAULT '0'
  COMMENT '提现总额',
  `income_total`        DOUBLE   NOT NULL DEFAULT '0'
  COMMENT '收入总额',
  `createTime`          DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_update_time`    DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
  COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_member_property_id_uindex` (`id`),
  KEY `t_member_property_t_user_id_fk` (`user_id`),
  CONSTRAINT `t_member_property_t_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARSET = utf8
  COMMENT ='会员(客服)财富资产表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_member_property`
--

LOCK TABLES `t_member_property` WRITE;
/*!40000 ALTER TABLE `t_member_property`
  DISABLE KEYS */;
INSERT INTO `t_member_property` VALUES (2, 15, 135, 0.6, 135.6, '2018-03-19 20:03:57', '2018-03-31 21:19:41'),
  (4, 16, 0, 0, 0, '2018-03-24 13:08:36', '2018-03-24 13:08:36'),
  (5, 18, 123.4, 0, 123.4, '2018-03-25 10:39:48', '2018-03-30 13:08:25');
/*!40000 ALTER TABLE `t_member_property`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_menu`
--

DROP TABLE IF EXISTS `t_menu`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_menu` (
  `id`          INT(11)     NOT NULL AUTO_INCREMENT,
  `name`        VARCHAR(32) NOT NULL,
  `description` VARCHAR(128)         DEFAULT NULL,
  `parent_id`   INT(11)              DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 9
  DEFAULT CHARSET = utf8
  COMMENT ='菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_menu`
--

LOCK TABLES `t_menu` WRITE;
/*!40000 ALTER TABLE `t_menu`
  DISABLE KEYS */;
INSERT INTO `t_menu` VALUES (1, '信息维护', '个人信息维护, 一级菜单', NULL), (2, '个人信息', '维护用户基本信息', 1), (3, '实名认证', '实名认证', 1),
  (5, '认证管理', '查看和审批认证请求', NULL), (6, '会员用户认证', '针对会员用户', 5), (7, '企业用户认证', '针对企业用户', 5), (8, '消息处理', '查询消息', NULL);
/*!40000 ALTER TABLE `t_menu`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_message`
--

DROP TABLE IF EXISTS `t_message`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_message` (
  `id`          INT(11)    NOT NULL AUTO_INCREMENT,
  `sender_id`   INT(11)             DEFAULT NULL,
  `receiver_id` INT(11)    NOT NULL,
  `title`       VARCHAR(32)         DEFAULT NULL,
  `content`     VARCHAR(512)        DEFAULT NULL,
  `send_time`   DATETIME   NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_read`     TINYINT(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_message_id_uindex` (`id`),
  KEY `t_message_t_user_id_fk` (`sender_id`),
  KEY `t_message_t_user_id_fk_2` (`receiver_id`),
  CONSTRAINT `t_message_t_user_id_fk` FOREIGN KEY (`sender_id`) REFERENCES `t_user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `t_message_t_user_id_fk_2` FOREIGN KEY (`receiver_id`) REFERENCES `t_user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 43
  DEFAULT CHARSET = utf8
  COMMENT ='消息通知';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_message`
--

LOCK TABLES `t_message` WRITE;
/*!40000 ALTER TABLE `t_message`
  DISABLE KEYS */;
INSERT INTO `t_message` VALUES (1, NULL, 15, '[系统通知] 实名认证审核未通过!', '审核不通过!证件照错误', '2018-03-18 13:21:05', 1),
  (2, NULL, 15, '[系统通知] 实名认证审核通过!', '审核通过!ojbk', '2018-03-18 14:22:38', 0),
  (3, NULL, 12, '[系统通知] 实名认证审核通过!', '审核通过!ojbk', '2018-03-18 20:47:35', 0),
  (4, NULL, 16, '[系统通知] 实名认证审核通过!', '审核通过!', '2018-03-24 13:33:47', 0),
  (5, NULL, 17, '[系统通知] 实名认证审核未通过!', '审核不通过!信息不准确', '2018-03-24 14:07:54', 0),
  (6, 15, 12, '新的客服参与竞标, 请处理', '您的任务:数字标注有新的客服参与了竞标, 请及时处理!', '2018-03-24 22:02:12', 0),
  (7, 12, 15, '任务竞标失败通知', '您于2018年03月24日 22时02分参与任务\"数字标注\"的投标, 已被发起者拒绝!', '2018-03-25 09:35:28', 0),
  (8, 18, 12, '新的客服参与竞标, 请处理', '您的任务:数字标注有新的客服参与了竞标, 请及时处理!', '2018-03-25 10:42:31', 0),
  (9, 12, 18, '任务竞标成功通知', '您于2018年03月25日 10时42分参与任务\"数字标注\"的投标, 已被发起者同意申请!请前往我的任务查看详情', '2018-03-25 10:43:40', 0),
  (10, 15, 12, '新的客服参与竞标, 请处理', '您的任务:宣传肉蟹煲有新的客服参与了竞标, 请及时处理!', '2018-03-25 20:51:13', 0),
  (11, 12, 15, '任务竞标成功通知', '您于2018年03月25日 20时51分参与任务\"宣传肉蟹煲\"的投标, 已被发起者同意申请!请前往我的任务查看详情', '2018-03-25 21:13:02', 0),
  (23, 12, 15, '任务竞标失败通知', '您于2018年03月25日 20时51分参与任务\"宣传肉蟹煲\"的投标, 已被发起者拒绝!', '2018-03-31 14:30:40', 0),
  (24, 12, 15, '任务竞标失败通知', '您于2018年03月25日 20时51分参与任务\"宣传肉蟹煲\"的投标, 已被发起者拒绝!', '2018-03-31 14:36:05', 0),
  (25, 12, 15, '任务竞标失败通知', '您于2018年03月25日 20时51分参与任务\"宣传肉蟹煲\"的投标, 已被发起者拒绝!', '2018-03-31 14:36:15', 0),
  (26, 12, 15, '任务竞标失败通知', '您于2018年03月25日 20时51分参与任务\"宣传肉蟹煲\"的投标, 已被发起者拒绝!', '2018-03-31 14:36:15', 0),
  (27, 12, 15, '任务竞标失败通知', '您于2018年03月25日 20时51分参与任务\"宣传肉蟹煲\"的投标, 已被发起者拒绝!', '2018-03-31 14:36:37', 0),
  (28, 16, 12, '新的客服参与竞标, 请处理', '您的任务:这是关于一个市场调研的项目有新的客服参与了竞标, 请及时处理!', '2018-03-31 21:42:26', 0),
  (29, 12, 16, '任务竞标成功通知', '您于2018年03月31日 21时42分参与任务\"这是关于一个市场调研的项目\"的投标, 已被发起者同意申请!请前往我的任务查看详情', '2018-04-01 08:13:30', 0),
  (30, 15, 12, '新的客服参与竞标, 请处理', '您的任务:调查附近居民的健身情况有新的客服参与了竞标, 请及时处理!', '2018-04-01 08:32:57', 0),
  (31, 12, 15, '任务竞标成功通知', '您于2018年04月01日 08时32分参与任务\"调查附近居民的健身情况\"的投标, 已被发起者同意申请!请前往我的任务查看详情', '2018-04-01 08:33:54', 0),
  (32, 15, 12, '新的客服参与竞标, 请处理', '您的任务:调查饭菜情况有新的客服参与了竞标, 请及时处理!', '2018-04-09 13:28:08', 0),
  (33, 15, 12, '新的客服参与竞标, 请处理', '您的任务:查看学校附近的小黄车有新的客服参与了竞标, 请及时处理!', '2018-04-09 15:58:11', 0),
  (34, 12, 15, '任务竞标成功通知', '您于2018年04月09日 15时58分参与任务\"查看学校附近的小黄车\"的投标, 已被发起者同意申请!请前往我的任务查看详情', '2018-04-09 15:59:09', 0),
  (35, 15, 12, '新的客服参与竞标, 请处理', '您的任务:调查大学生的月生活费情况有新的客服参与了竞标, 请及时处理!', '2018-04-11 11:59:54', 0),
  (36, 12, 15, '任务竞标成功通知', '您于2018年04月11日 11时59分参与任务\"调查大学生的月生活费情况\"的投标, 已被发起者同意申请!请前往我的任务查看详情', '2018-04-11 12:00:03', 0),
  (37, 15, 12, '新的客服参与竞标, 请处理', '您的任务:调查最近大学生的睡眠情况有新的客服参与了竞标, 请及时处理!', '2018-04-11 18:20:53', 0),
  (38, 12, 15, '任务竞标成功通知', '您于2018年04月11日 18时20分参与任务\"调查最近大学生的睡眠情况\"的投标, 已被发起者同意申请!请前往我的任务查看详情', '2018-04-11 18:21:16', 0),
  (39, 15, 12, '新的客服参与竞标, 请处理', '您的任务:小黄车的调查有新的客服参与了竞标, 请及时处理!', '2018-04-12 12:38:38', 0),
  (40, 12, 15, '任务竞标成功通知', '您于2018年04月12日 12时38分参与任务\"小黄车的调查\"的投标, 已被发起者同意申请!请前往我的任务查看详情', '2018-04-12 12:39:12', 0),
  (41, 15, 12, '新的客服参与竞标, 请处理', '您的任务:小黄车的使用情况二有新的客服参与了竞标, 请及时处理!', '2018-04-12 15:22:54', 0),
  (42, 12, 15, '任务竞标成功通知', '您于2018年04月12日 15时22分参与任务\"小黄车的使用情况二\"的投标, 已被发起者同意申请!请前往我的任务查看详情', '2018-04-12 15:23:17', 0);
/*!40000 ALTER TABLE `t_message`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_permission`
--

DROP TABLE IF EXISTS `t_permission`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_permission` (
  `id`          INT(11)      NOT NULL AUTO_INCREMENT,
  `name`        VARCHAR(32)           DEFAULT NULL,
  `url`         VARCHAR(128) NOT NULL,
  `description` VARCHAR(128)          DEFAULT NULL,
  `menu_id`     INT(11)               DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_permission_url_uindex` (`url`),
  KEY `t_permission_t_menu_id_fk` (`menu_id`),
  CONSTRAINT `t_permission_t_menu_id_fk` FOREIGN KEY (`menu_id`) REFERENCES `t_menu` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 2022
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_permission`
--

LOCK TABLES `t_permission` WRITE;
/*!40000 ALTER TABLE `t_permission`
  DISABLE KEYS */;
INSERT INTO `t_permission` VALUES (0, '刷新token', 'post:/token/refresh', '刷新token, 保持登录', NULL),
  (1, '获取用户基本信息', 'get:/user/profile/info/simple', '获取最基本的信息, 包括头像和用户名', 2),
  (2, '获取用户信息', 'get:/user/profile/info', '获取用户的详细的信息', 2), (3, '更新头像', 'put:/user/profile/head', '更新用户头像', 2),
  (4, '更新用户信息', 'put:/user/profile/info', '更新用户信息', 2),
  (5, '主动更新用户密码', 'put:/user/profile/password', '用户主动更新密码. 需要旧密码, 新密码和重复新密码', 2),
  (6, '更新用户名', 'put:/user/profile/username', '', 2), (7, '更新性别', 'put:/user/profile/sex', NULL, 2),
  (8, '更新qq', 'put:/user/profile/qq', NULL, 2), (9, '更新微信号', 'put:/user/profile/wechat', NULL, 2),
  (10, '更新地址', 'put:/user/profile/address', NULL, 2), (11, '更新个人信息', 'put:/user/profile/description', NULL, 2),
  (12, '更新教育信息', 'put:/user/profile/educational', NULL, 2), (13, '查询用户最近的五条消息记录', 'get:/message/latest', NULL, NULL),
  (14, '查询用户所有消息', 'get:/message/all', NULL, 8), (15, '查询用户所有的已读消息', 'get:/message/read', NULL, NULL),
  (16, '查询用户所有的未读消息', 'get:/message/unread', NULL, NULL), (17, '统计用户未读消息的条数', 'get:/message/unread/count', NULL, NULL),
  (30, '发送邮件验证码', 'post:/email/code', NULL, 2), (31, '绑定邮箱', 'put:/email/bind', NULL, 2),
  (50, '创建后台管理用户', 'post:/admin/user/background_user/create', NULL, NULL),
  (51, '查询所有后台用户', 'get:/admin/user/background_user/list', NULL, NULL),
  (52, '查询所有企业用户', 'get:/admin/user/enterprise_user/list', NULL, NULL),
  (53, '查询所有客服用户', 'get:/admin/user/member_user/list', NULL, NULL),
  (54, '查询用户详情', 'get:/admin/user/*/detail', NULL, NULL), (55, '删除一个用户', 'delete:/admin/user/*/delete', NULL, NULL),
  (56, '查询所有角色', 'get:/admin/role/list', NULL, NULL), (57, '创建一个角色', 'post:/admin/role/create', NULL, NULL),
  (58, '查询一个角色的信息', 'get:/admin/role/*/detail', NULL, NULL), (59, '更新一个角色信息', 'put:/admin/role/*/update', NULL, NULL),
  (60, '删除一个角色', 'delete:/admin/role/*/delete', NULL, NULL), (61, '查询所有权限', 'get:/admin/permission/list', NULL, NULL),
  (62, '创建一个权限', 'post:/admin/permission/create', NULL, NULL),
  (63, '更新一个权限信息', 'put:/admin/permission/*/update', NULL, NULL),
  (64, '删除一个权限', 'delete:/admin/permission/*/delete', NULL, NULL),
  (65, '建立用户角色关系', 'post:/admin/user_role/create/contact', NULL, NULL),
  (66, '解除用户角色关系', 'delete:/admin/user_role/delete/contact', NULL, NULL),
  (67, '建立角色权限关系', 'post:/admin/role_permission/create/contact', NULL, NULL),
  (68, '解除角色权限关系', 'delete:/admin/user_permission/delete/contact', NULL, NULL),
  (69, '查询用户的登录信息', 'get:/admin/user/*/login_info', NULL, NULL),
  (70, '查询网站的概要数据信息', 'get:/admin/data_statistics/summary', NULL, NULL),
  (71, '查询网站时间范围内的概要数据信息', 'get:/admin/data_statistics/summary/*/to/*', NULL, NULL),
  (100, '查询所有会员用户认证信息', 'get:/admin/auditing/member/authentication/all', '查询所有的会员用户认证信息, 包括审核中, 审核\n\n通过和不通过的', 6),
  (101, '查询所有企业用户认证信息', 'get:/admin/auditing/enterprise/authentication/all', '查询所有的企业用户认证信息, 包括审核中, 审核\n\n通过和不通过的', 7),
  (102, '查询所有未审核的会员认证申请', 'get:/admin/auditing/member/authentication/unaudited', NULL, 6),
  (103, '查询所有未审核的企业认证申请', 'get:/admin/auditing/enterprise/authentication/unaudited', NULL, 7),
  (104, '查询所有通过审核的会员认证申请', 'get:/admin/auditing/member/authentication/pass', NULL, 6),
  (105, '查询所有通过审核的企业认证申请', 'get:/admin/auditing/enterprise/authentication/pass', NULL, 7),
  (106, '查询所有未通过审核的会员认证申请', 'get:/admin/auditing/member/authentication/fail_pass', NULL, 6),
  (107, '查询所有未通过审核的企业认证申请', 'get:/admin/auditing/enterprise/authentication/fail_pass', NULL, 7),
  (108, '审核会员用户认证信息', 'post:/admin/auditing/member/verify/*', NULL, 6),
  (109, '审核企业用户认证信息', 'post:/admin/auditing/enterprise/verify/*', NULL, 7),
  (200, '创建知识库', 'post:/knowledge_base/create', '创建知识库', NULL),
  (201, '删除知识库', 'delete:/knowledge_base/delete/*', '删除知识库', NULL),
  (202, '浏览创建的知识库', 'get:/knowledge_base/list', '查询自己创建的知识库', NULL),
  (203, '更新知识库', 'put:/knowledge_base/update/*', NULL, NULL),
  (204, '创建知识库条目', 'post:/knowledge_base/*/item/create', NULL, NULL),
  (205, '删除知识库条目', 'delete:/knowledge_base/*/item/delete/*', '', NULL),
  (206, '更新知识库条目', 'put:/knowledge_base/*/item/update/*', NULL, NULL),
  (207, '查询某个知识库下的所有条目', 'get:/knowledge_base/*/item/list', NULL, NULL),
  (208, '查询一条知识库', 'get:/knowledge_base/query/*', NULL, NULL),
  (209, '查询单个任务下的关联的知识库信息', 'get:/knowledge_base/task/*/about', NULL, NULL),
  (210, '查询魔调具体的知识库', 'get:/knowledge_base/*/item/*/detail', NULL, NULL),
  (1000, '企业用户查询自身的认证信息', 'get:/enterprise/authentication/info', NULL, 3),
  (1001, '企业用户申请认证', 'post:/enterprise/authentication/apply', '未申请过的认证的情况下申请认证', 3),
  (1002, '企业用户重新申请认证', 'put:/enterprise/authentication/reapply', '申请认证不通过的情况下, 需要修改信息重新认证', 3),
  (1003, '查询企业财产信息', 'get:/enterprise/property/query', NULL, NULL),
  (1004, '查询企业资金流信息', 'post:/enterprise/property/detail/query', NULL, NULL),
  (1005, '企业提现操作', 'put:/enterprise/property/withdraw', NULL, NULL),
  (1006, '企业重置操作', 'put:/enterprise/property/recharge', NULL, NULL),
  (1010, '发布任务', 'post:/enterprise/task/create', '创建发布一个任务', NULL),
  (1011, '查询企业自己创建的任务', 'get:/enterprise/task/query', NULL, NULL),
  (1012, '企业查询指定状态的任务', 'get:/enterprise/task/query/status/*', NULL, NULL),
  (1013, '查询具体某个任务', 'get:/enterprise/task/query/detail/*', NULL, NULL),
  (1014, '取消发布任务', 'put:/enterprise/task/cancel/*', NULL, NULL),
  (1015, '查询任务的竞标记录', 'get:/enterprise/task/*/applications', NULL, NULL),
  (1016, '同意客服申请', 'put:/enterprise/task/*/application/agree/*', NULL, NULL),
  (1017, '拒绝客服的申请(竞标)', 'put:/enterprise/task/*/application/refuse/*', NULL, NULL),
  (1018, '企业查询任务对应的客服', 'get:/enterprise/task/*/customer_service', NULL, NULL),
  (1019, '查询任务未处理的竞标记录', 'get:/enterprise/task/*/applications/untreated', NULL, NULL),
  (1020, '绑定任务和知识库', 'post:/enterprise/task/*/bind/knowledge/*', NULL, NULL),
  (1021, '批量绑定任务和知识库', 'post:/enterprise/task/*/bind/knowledge/batch/*', NULL, NULL),
  (1022, '查询任务下的所有建立雇佣关系的客服', 'get:/enterprise/task/all/customer_service', NULL, NULL),
  (1023, '获取任务外链, 普通用户通过外链获取服务', 'get:/enterprise/task/*/customer_access_url', NULL, NULL),
  (1024, '获取数据统计', 'get:/enterprise/data_statistics/summary', NULL, NULL),
  (1200, '企业创建题库', 'post:/enterprise/question_bank/create', NULL, NULL),
  (1201, '更新题库', 'put:/enterprise/question_bank/*/update', NULL, NULL),
  (1202, '删除题库', 'delete:/enterprise/question_bank/*/delete', NULL, NULL),
  (1203, '查询所有题库', 'get:/enterprise/question_bank/list', NULL, NULL),
  (1204, '查询单个题库', 'get:/enterprise/question_bank/*/detail', NULL, NULL),
  (1205, '插入一个问题', 'post:/enterprise/question_bank/*/question/create', NULL, NULL),
  (1206, '更新一个问题', 'put:/enterprise/question_bank/*/question/*/update', NULL, NULL),
  (1207, '删除一个问题', 'delete:/enterprise/question_bank/*/question/*/delete', NULL, NULL),
  (1208, '添加一个选项', 'post:/enterprise/question_bank/*question/*/item/create', NULL, NULL),
  (1209, '删除一个选项', 'delete:/enterprise/question_bank/*/question/*/item/*/delete', NULL, NULL),
  (1210, '设置问题的标准答案', 'put:/enterprise/question_bank/*/question/*/set_answer', NULL, NULL),
  (1211, '查询题库下的所有问题', 'get:/enterprise/question_bank/*/question/list', NULL, NULL),
  (1212, '查询题库下的单个问题', 'get/enterprise/question_bank/*/question/*/detail', NULL, NULL),
  (1213, '查询问题下的所有选项', 'get:/enterprise/question_bank/*/question/*/items', NULL, NULL),
  (1214, '查询题库下指定类型的所有问题', 'get:/enterprise/question_bank/*/question/list/type/*', '', NULL),
  (1215, '手动创建试卷', 'post:/enterprise/question_bank/*/examination/create/manual', NULL, NULL),
  (1216, '建立试卷和题目的单条联系', 'post:/enterprise/question_bank/*/examination/*/question/add', NULL, NULL),
  (1217, '查询试卷的详细信息', 'get:/enterprise/question_bank/*/examination/*/detail', NULL, NULL),
  (1218, '建立试卷和题目的批量联系', 'post:/enterprise/question_bank/*/examination/*/question/add/batch', NULL, NULL),
  (1219, '查询题库下所有手动生成的试卷', 'get:/enterprise/question_bank/*/examination/list/manual', NULL, NULL),
  (1220, '创建一个生成试卷的规则', 'post:/enterprise/question_bank/*/rule/create', NULL, NULL),
  (1221, '删除一个规则', 'delete:/enterprise/question_bank/*/rule/*/delete', NULL, NULL),
  (1222, '查询某个知识库下所有的规则', 'get:/enterprise/question_bank/*/rule/list', NULL, NULL),
  (1223, '查询某个用户创建的所有任务', 'get:/enterprise/question_bank/rule/list', NULL, NULL),
  (1224, '查询某个客服在某个问卷下提交的回单', 'get:/enterprise/sheet/task/*/answerer/*', NULL, NULL),
  (1225, '给某份答卷评分', 'put:/enterprise/sheet/*/score', NULL, NULL),
  (2000, '会员查询自身的认证信息', 'get:/member/authentication/info', '', 3),
  (2001, '会员申请认证', 'post:/member/authentication/apply', '未申请过的认证的情况下申请认证', 3),
  (2002, '会员重新认证', 'put:/member/authentication/reapply', '申请认证不通过的情况下, 需要修改信息重新认证', 3),
  (2003, '查询会员财产信息', 'get:/member/property/query', NULL, NULL),
  (2004, '会员查询财产信息详情', 'get:/member/property/detail/query', NULL, NULL),
  (2005, '会员提现操作', 'put:/member/property/withdraw', NULL, NULL),
  (2010, '会员查询所有可以参与竞标的任务', 'get:/member/task_hall/all', NULL, NULL),
  (2011, '会员查询单条任务', 'get:/member/task_hall/query/detail/*', '会员只能查询任务状态为1(任务竞标中)以及该用户参与过的任务', NULL),
  (2012, '竞选任务', 'post:/member/task_hall/task/*/application', NULL, NULL),
  (2013, '查询用户所有参与过的任务', 'get:/member/task/participate/all', NULL, NULL),
  (2014, '查询用户所有参与过未开始的任务', 'get:/member/task/participate/not_start', NULL, NULL),
  (2015, '查询用户所有参与过的进行中的任务', 'get:/member/task/participate/have_in_hand', NULL, NULL),
  (2016, '查询用户所有参与过的已经结束的任务', 'get:/member/task/participate/end', NULL, NULL),
  (2017, '查询用户所有已经参加过的已取消的任务', 'get:/member/task/participate/cancel', NULL, NULL),
  (2018, '在任务下获取试卷', 'get:/member/task_hall/task/*/get_examination', NULL, NULL),
  (2019, '提交答卷', 'post:/member/task_hall/task/*/examination/*/hand_exam', NULL, NULL),
  (2020, '根据关键字搜索知识库内容', 'get:/search/search_base_by_task/*/keyword/*', NULL, NULL),
  (2021, '查询会员用户的数据信息', 'get:/member/data_statistics/summary', NULL, NULL);
/*!40000 ALTER TABLE `t_permission`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_phone_validate_code`
--

DROP TABLE IF EXISTS `t_phone_validate_code`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_phone_validate_code` (
  `id`        INT(11)     NOT NULL AUTO_INCREMENT,
  `phone`     VARCHAR(32) NOT NULL,
  `code`      VARCHAR(6)  NOT NULL,
  `send_time` DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='存储手机号和短信验证码';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_phone_validate_code`
--

LOCK TABLES `t_phone_validate_code` WRITE;
/*!40000 ALTER TABLE `t_phone_validate_code`
  DISABLE KEYS */;
/*!40000 ALTER TABLE `t_phone_validate_code`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_question`
--

DROP TABLE IF EXISTS `t_question`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_question` (
  `id`                  INT(11)      NOT NULL AUTO_INCREMENT,
  `bank_id`             INT(11)      NOT NULL,
  `creator`             INT(11)      NOT NULL,
  `question`            VARCHAR(256) NOT NULL,
  `remarks`             VARCHAR(256)          DEFAULT NULL,
  `type`                INT(11)      NOT NULL
  COMMENT '0 单选题 1 多选题 2 判断题 3 文字题',
  `has_standard_answer` INT(11)      NOT NULL DEFAULT '0',
  `standard_answer`     VARCHAR(32)           DEFAULT NULL,
  `create_time`         DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status`              INT(11)      NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_question_id_uindex` (`id`),
  KEY `t_question_t_question_bank_id_fk` (`bank_id`),
  KEY `t_question_t_user_id_fk` (`creator`),
  CONSTRAINT `t_question_t_question_bank_id_fk` FOREIGN KEY (`bank_id`) REFERENCES `t_question_bank` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `t_question_t_user_id_fk` FOREIGN KEY (`creator`) REFERENCES `t_user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 12
  DEFAULT CHARSET = utf8
  COMMENT ='问题';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_question`
--

LOCK TABLES `t_question` WRITE;
/*!40000 ALTER TABLE `t_question`
  DISABLE KEYS */;
INSERT INTO `t_question` VALUES (2, 1, 12, '早餐', '营养点的', 0, 1, '0', '2018-04-04 19:32:16', 1),
  (3, 1, 12, '午餐', '吃饱就行', 0, 0, NULL, '2018-04-04 19:32:46', 0),
  (4, 1, 12, '晚餐', '少吃点', 3, 0, NULL, '2018-04-04 19:33:06', 1),
  (6, 1, 12, '去哪玩', '泰山', 1, 1, '7-8', '2018-04-04 19:36:24', 1),
  (7, 1, 12, '天气', '很好', 2, 1, 'true', '2018-04-04 19:36:50', 1),
  (8, 1, 12, '水质', '不错', 2, 1, 'false', '2018-04-04 19:37:05', 1),
  (10, 1, 12, '点心', '无', 0, 0, NULL, '2018-04-05 13:32:20', 0),
  (11, 1, 12, '夜宵', '无', 0, 0, NULL, '2018-04-05 13:32:26', 0);
/*!40000 ALTER TABLE `t_question`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_question_answer`
--

DROP TABLE IF EXISTS `t_question_answer`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_question_answer` (
  `id`          INT(11) NOT NULL AUTO_INCREMENT,
  `sheet_id`    INT(11) NOT NULL
  COMMENT '答卷表id',
  `question_id` INT(11) NOT NULL
  COMMENT '问题id',
  `answer`      VARCHAR(1024)    DEFAULT NULL
  COMMENT '答案内容,单选题对应itemId, 多选题对应itemIds,使用''-''连接, 判断题答案为true或者false, 文字题对应回答',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_question_answer_id_uindex` (`id`),
  KEY `t_question_answer_t_question_answer_sheet_id_fk` (`sheet_id`),
  KEY `t_question_answer_t_question_id_fk` (`question_id`),
  CONSTRAINT `t_question_answer_t_question_answer_sheet_id_fk` FOREIGN KEY (`sheet_id`) REFERENCES `t_question_answer_sheet` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `t_question_answer_t_question_id_fk` FOREIGN KEY (`question_id`) REFERENCES `t_question` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 26
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_question_answer`
--

LOCK TABLES `t_question_answer` WRITE;
/*!40000 ALTER TABLE `t_question_answer`
  DISABLE KEYS */;
INSERT INTO `t_question_answer`
VALUES (1, 1, 7, 'true'), (2, 1, 8, 'true'), (3, 1, 6, '7'), (4, 1, 2, '4'), (5, 1, 4, '123'), (6, 2, 7, 'true'),
  (7, 2, 8, 'flase'), (8, 2, 6, '9'), (9, 2, 2, 'B'), (10, 2, 4, '我觉得晚上少吃点是对的'), (11, 3, 7, 'flase'),
  (12, 3, 8, 'flase'), (13, 3, 6, '9'), (14, 3, 2, 'B'), (15, 3, 4, '我觉得是对的'), (16, 4, 7, 'flase'), (17, 4, 8, 'flase'),
  (18, 4, 6, '8'), (19, 4, 2, 'B'), (20, 4, 4, '我觉得 这是对的'), (21, 5, 7, 'true'), (22, 5, 8, 'true'), (23, 5, 6, '7'),
  (24, 5, 2, '4'), (25, 5, 4, '234');
/*!40000 ALTER TABLE `t_question_answer`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_question_answer_sheet`
--

DROP TABLE IF EXISTS `t_question_answer_sheet`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_question_answer_sheet` (
  `id`                INT(11)  NOT NULL AUTO_INCREMENT,
  `paper_id`          INT(11)  NOT NULL
  COMMENT '试卷id',
  `answerer`          INT(11)  NOT NULL
  COMMENT '回答者id',
  `task_id`           INT(11)  NOT NULL
  COMMENT '对应任务id',
  `create_time`       DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `score`             INT(11)           DEFAULT NULL
  COMMENT '评分,最高十分, 最低一分',
  `remark`            VARCHAR(1024)     DEFAULT NULL
  COMMENT '评语',
  `mark_time`         DATETIME          DEFAULT NULL
  COMMENT '评价时间',
  `evaluation_status` INT(11)  NOT NULL DEFAULT '0'
  COMMENT '评价状态 0 未评价 1已评价',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_question_answer_sheet_id_uindex` (`id`),
  UNIQUE KEY `t_question_answer_sheet_answerer_task_id_uindex` (`answerer`, `task_id`),
  KEY `t_question_answer_sheet_t_question_examination_paper_id_fk` (`paper_id`),
  KEY `t_question_answer_sheet_t_task_id_fk` (`task_id`),
  CONSTRAINT `t_question_answer_sheet_t_question_examination_paper_id_fk` FOREIGN KEY (`paper_id`) REFERENCES `t_question_examination_paper` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `t_question_answer_sheet_t_task_id_fk` FOREIGN KEY (`task_id`) REFERENCES `t_task` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `t_question_answer_sheet_t_user_id_fk` FOREIGN KEY (`answerer`) REFERENCES `t_user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARSET = utf8
  COMMENT ='答卷表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_question_answer_sheet`
--

LOCK TABLES `t_question_answer_sheet` WRITE;
/*!40000 ALTER TABLE `t_question_answer_sheet`
  DISABLE KEYS */;
INSERT INTO `t_question_answer_sheet` VALUES (1, 1, 15, 10, '2018-04-09 13:11:19', 1, '123', '2018-04-09 14:29:45', 1),
  (2, 1, 15, 11, '2018-04-09 15:57:21', 10, '还行 可以通过这份测试', '2018-04-09 15:59:02', 1),
  (3, 1, 15, 13, '2018-04-11 18:20:39', 8, '可以', '2018-04-11 18:21:14', 1),
  (4, 1, 15, 14, '2018-04-12 12:38:30', 9, '回答的好', '2018-04-12 12:39:09', 1),
  (5, 1, 15, 15, '2018-04-12 15:22:47', 9, '很棒', '2018-04-12 15:23:15', 1);
/*!40000 ALTER TABLE `t_question_answer_sheet`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_question_bank`
--

DROP TABLE IF EXISTS `t_question_bank`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_question_bank` (
  `id`          INT(11)     NOT NULL AUTO_INCREMENT,
  `bank_name`   VARCHAR(64) NOT NULL
  COMMENT '题库名字',
  `description` VARCHAR(256)         DEFAULT NULL
  COMMENT '题库描述',
  `creator`     INT(11)     NOT NULL
  COMMENT '创建者',
  `create_time` DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_question_bank_id_uindex` (`id`),
  KEY `t_question_bank_t_user_id_fk` (`creator`),
  CONSTRAINT `t_question_bank_t_user_id_fk` FOREIGN KEY (`creator`) REFERENCES `t_user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 8
  DEFAULT CHARSET = utf8
  COMMENT ='题库';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_question_bank`
--

LOCK TABLES `t_question_bank` WRITE;
/*!40000 ALTER TABLE `t_question_bank`
  DISABLE KEYS */;
INSERT INTO `t_question_bank`
VALUES (1, '测试题库', '这是一个测试题库, 用于测试', 12, '2018-04-04 17:25:34'), (4, '交际', '交际能力测试', 12, '2018-04-05 19:54:53'),
  (7, '数字检索', '关于数字检索 ppt  excel的制作', 12, '2018-04-05 19:57:26');
/*!40000 ALTER TABLE `t_question_bank`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_question_examination_paper`
--

DROP TABLE IF EXISTS `t_question_examination_paper`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_question_examination_paper` (
  `id`            INT(11)     NOT NULL AUTO_INCREMENT,
  `bank_id`       INT(11)     NOT NULL,
  `creator`       INT(11)     NOT NULL,
  `name`          VARCHAR(32) NOT NULL,
  `description`   VARCHAR(256)         DEFAULT NULL,
  `type`          INT(11)     NOT NULL DEFAULT '0'
  COMMENT '0自动生成, 1手动生成',
  `status`        INT(11)     NOT NULL DEFAULT '0'
  COMMENT '0 不生效, 1生效',
  `num`           INT(11)              DEFAULT NULL,
  `single_num`    INT(11)              DEFAULT NULL,
  `multiple_num`  INT(11)              DEFAULT NULL,
  `judgement_num` INT(11)              DEFAULT NULL,
  `text_num`      INT(11)              DEFAULT NULL,
  `create_time`   DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_question_examination_paper_id_uindex` (`id`),
  KEY `t_question_examination_paper_t_question_bank_id_fk` (`bank_id`),
  KEY `t_question_examination_paper_t_user_id_fk` (`creator`),
  CONSTRAINT `t_question_examination_paper_t_question_bank_id_fk` FOREIGN KEY (`bank_id`) REFERENCES `t_question_bank` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `t_question_examination_paper_t_user_id_fk` FOREIGN KEY (`creator`) REFERENCES `t_user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 9
  DEFAULT CHARSET = utf8
  COMMENT ='试卷';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_question_examination_paper`
--

LOCK TABLES `t_question_examination_paper` WRITE;
/*!40000 ALTER TABLE `t_question_examination_paper`
  DISABLE KEYS */;
INSERT INTO `t_question_examination_paper`
VALUES (1, 1, 12, '天气预测', '预测每天的天气', 1, 0, 12, 3, 3, 3, 3, '2018-04-05 13:13:04'),
  (2, 1, 12, '英语作文命题', '无', 1, 1, 1, 0, 0, 0, 1, '2018-04-05 13:14:08'),
  (3, 1, 12, '语文作文命题', '无', 1, 0, 3, 0, 3, 0, 0, '2018-04-05 13:15:25'),
  (6, 1, 12, 'test', '1111', 1, 0, 4, 1, 1, 1, 1, '2018-04-06 18:06:37'),
  (7, 1, 12, '1', '1', 1, 0, 4, 1, 1, 1, 1, '2018-04-06 18:06:47'),
  (8, 1, 12, '数字检索', '数字检索', 1, 0, 4, 1, 1, 1, 1, '2018-04-07 19:43:36');
/*!40000 ALTER TABLE `t_question_examination_paper`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_question_examination_paper_question`
--

DROP TABLE IF EXISTS `t_question_examination_paper_question`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_question_examination_paper_question` (
  `id`             INT(11) NOT NULL AUTO_INCREMENT,
  `examination_id` INT(11) NOT NULL,
  `question_id`    INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_qwestion_examination_paper_question_id_uindex` (`id`),
  KEY `t_qwestion_examination_paper_q_id_fk` (`examination_id`),
  KEY `t_qwestion_examination_paper_question_t_question_id_fk` (`question_id`),
  CONSTRAINT `t_qwestion_examination_paper_q_id_fk` FOREIGN KEY (`examination_id`) REFERENCES `t_question_examination_paper` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `t_qwestion_examination_paper_question_t_question_id_fk` FOREIGN KEY (`question_id`) REFERENCES `t_question` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 38
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_question_examination_paper_question`
--

LOCK TABLES `t_question_examination_paper_question` WRITE;
/*!40000 ALTER TABLE `t_question_examination_paper_question`
  DISABLE KEYS */;
INSERT INTO `t_question_examination_paper_question`
VALUES (29, 1, 2), (30, 1, 6), (31, 1, 7), (32, 1, 8), (33, 1, 4), (35, 2, 4), (36, 1, 2), (37, 1, 2);
/*!40000 ALTER TABLE `t_question_examination_paper_question`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_question_item`
--

DROP TABLE IF EXISTS `t_question_item`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_question_item` (
  `id`          INT(11)     NOT NULL AUTO_INCREMENT,
  `question_id` INT(11)              DEFAULT NULL,
  `item`        VARCHAR(32) NOT NULL,
  `content`     VARCHAR(64)          DEFAULT NULL,
  `remarks`     VARCHAR(64)          DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_question_item_id_uindex` (`id`),
  KEY `t_question_item_t_question_id_fk` (`question_id`),
  CONSTRAINT `t_question_item_t_question_id_fk` FOREIGN KEY (`question_id`) REFERENCES `t_question` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 12
  DEFAULT CHARSET = utf8
  COMMENT ='问题选项';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_question_item`
--

LOCK TABLES `t_question_item` WRITE;
/*!40000 ALTER TABLE `t_question_item`
  DISABLE KEYS */;
INSERT INTO `t_question_item`
VALUES (0, NULL, '错误', NULL, '判断题专供'), (1, NULL, '正确', NULL, '判断题专供'), (4, 2, 'A', '粥', '无'), (5, 2, 'B', '馒头', '无'),
  (7, 6, 'A', '泰山', '无'), (8, 6, 'B', '黄山', '无'), (9, 6, 'C', '华山', '无'), (10, 6, 'D', '武夷山', '无'),
  (11, 2, 'C', '肉包', '无');
/*!40000 ALTER TABLE `t_question_item`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_question_rule_of_generation_examination`
--

DROP TABLE IF EXISTS `t_question_rule_of_generation_examination`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_question_rule_of_generation_examination` (
  `id`            INT(11)     NOT NULL AUTO_INCREMENT,
  `bank_id`       INT(11)     NOT NULL,
  `creator`       INT(11)     NOT NULL,
  `name`          VARCHAR(64) NOT NULL,
  `description`   VARCHAR(256)         DEFAULT NULL,
  `num`           INT(11)              DEFAULT NULL,
  `single_num`    INT(11)              DEFAULT NULL,
  `multiple_num`  INT(11)              DEFAULT NULL,
  `judgement_num` INT(11)              DEFAULT NULL,
  `text_num`      INT(11)              DEFAULT NULL,
  `create_time`   DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_question_rule_of_generation_examination_id_uindex` (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARSET = utf8
  COMMENT ='试卷自动生成规则';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_question_rule_of_generation_examination`
--

LOCK TABLES `t_question_rule_of_generation_examination` WRITE;
/*!40000 ALTER TABLE `t_question_rule_of_generation_examination`
  DISABLE KEYS */;
INSERT INTO `t_question_rule_of_generation_examination`
VALUES (1, 1, 12, '测试规则生成', 'test', 4, 1, 1, 1, 1, '2018-04-06 15:15:34'),
  (2, 4, 12, '宁波工程学院', '123', 4, 1, 1, 1, 1, '2018-04-08 14:46:11'),
  (3, 1, 12, '123', '123', 4, 1, 1, 1, 1, '2018-04-08 14:51:56'),
  (5, 1, 12, '规则1', '规则1', 1, 1, 0, 0, 0, '2018-04-09 16:02:07');
/*!40000 ALTER TABLE `t_question_rule_of_generation_examination`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_real_name_authentication_of_enterprise`
--

DROP TABLE IF EXISTS `t_real_name_authentication_of_enterprise`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_real_name_authentication_of_enterprise` (
  `id`                     INT(11) NOT NULL AUTO_INCREMENT,
  `user_id`                INT(11) NOT NULL,
  `real_name`              VARCHAR(16)      DEFAULT NULL
  COMMENT '负责人真实姓名',
  `id_number`              VARCHAR(18)      DEFAULT NULL
  COMMENT '负责人证件号',
  `enterprise_name`        VARCHAR(128)     DEFAULT NULL
  COMMENT '企业名称',
  `business_license`       VARCHAR(128)     DEFAULT NULL
  COMMENT '营业执照',
  `licensed_residence`     VARCHAR(128)     DEFAULT NULL
  COMMENT '营业执照住所',
  `postal_address`         VARCHAR(128)     DEFAULT NULL
  COMMENT '通信地址',
  `legal_person`           VARCHAR(32)      DEFAULT NULL
  COMMENT '法人姓名',
  `office_phone`           VARCHAR(32)      DEFAULT NULL
  COMMENT '办公室电话',
  `enterprise_description` TEXT COMMENT '公司名称',
  `business_license_img`   VARCHAR(128)     DEFAULT NULL
  COMMENT '营业执照',
  `positive_img`           VARCHAR(128)     DEFAULT NULL
  COMMENT '负责人证件照正面照',
  `negative_img`           VARCHAR(128)     DEFAULT NULL
  COMMENT '反面照',
  `status`                 INT(11)          DEFAULT '0',
  `apply_time`             DATETIME         DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_real_name_authentication_of_enterprise_id_uindex` (`id`),
  KEY `t_real_name_authentication_of_enterprise_t_user_id_fk` (`user_id`),
  CONSTRAINT `t_real_name_authentication_of_enterprise_t_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_real_name_authentication_of_enterprise`
--

LOCK TABLES `t_real_name_authentication_of_enterprise` WRITE;
/*!40000 ALTER TABLE `t_real_name_authentication_of_enterprise`
  DISABLE KEYS */;
INSERT INTO `t_real_name_authentication_of_enterprise` VALUES
  (3, 12, '斯晓冬', '330724199610311313', '宁波红黄飞', '1564654654', '阿斯顿', '额为', '为文人', '123456', '            哇哇哇',
   'http://p5etjjbs6.bkt.clouddn.com/img/authentication/enterprise/cb3a4b8d2f374fbea45a604180c4de36.jpg',
   'http://p5etjjbs6.bkt.clouddn.com/img/authentication/enterprise/f76d655c89c24dfebf3c51da844935f4.jpg',
   'http://p5etjjbs6.bkt.clouddn.com/img/authentication/enterprise/ce12164d2c1e432eb54250ccc2b989ca.jpg', 1,
   '2018-03-18 18:43:55'),
  (4, 17, '菜键行', '330724199610311214', '温州皮革城', '123123123123', '温州皮革城', '温州皮革城', '斯晓冬', '212222222', '            很好',
   'http://p5etjjbs6.bkt.clouddn.com/img/authentication/enterprise/93e1892eef354e53bc1361047b2e02b6.jpg',
   'http://p5etjjbs6.bkt.clouddn.com/img/authentication/enterprise/7f03c4137a904125bdf7448d5b8be252.jpg',
   'http://p5etjjbs6.bkt.clouddn.com/img/authentication/enterprise/eb156d57bd214dceb876946a07eb40c0.jpg', 2,
   '2018-03-24 13:59:01');
/*!40000 ALTER TABLE `t_real_name_authentication_of_enterprise`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_real_name_authentication_of_member`
--

DROP TABLE IF EXISTS `t_real_name_authentication_of_member`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_real_name_authentication_of_member` (
  `id`                    INT(11)  NOT NULL AUTO_INCREMENT,
  `user_id`               INT(11)           DEFAULT NULL,
  `real_name`             VARCHAR(16)       DEFAULT NULL,
  `id_number`             VARCHAR(20)       DEFAULT NULL,
  `certificates_positive` VARCHAR(128)      DEFAULT NULL,
  `certificates_negative` VARCHAR(128)      DEFAULT NULL,
  `apply_time`            DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status`                INT(11)           DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_real_name_authentication_of_member_id_uindex` (`id`),
  UNIQUE KEY `t_real_name_authentication_of_member_user_id_uindex` (`user_id`),
  CONSTRAINT `t_real_name_authentication_of_member_t_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 7
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_real_name_authentication_of_member`
--

LOCK TABLES `t_real_name_authentication_of_member` WRITE;
/*!40000 ALTER TABLE `t_real_name_authentication_of_member`
  DISABLE KEYS */;
INSERT INTO `t_real_name_authentication_of_member` VALUES (5, 15, '董凌浩', '330424199608102639',
                                                           'http://p5etjjbs6.bkt.clouddn.com/img/authentication/member/e595712a019b4e43bb50d9454b24f106.jpg',
                                                           'http://p5etjjbs6.bkt.clouddn.com/img/authentication/member/b5a7904e09084b58b25462b791af8877.jpg',
                                                           '2018-03-17 10:38:13', 1),
  (6, 16, '唐秀娟', '330724199610311313',
   'http://p5etjjbs6.bkt.clouddn.com/img/authentication/member/e3cfd794ee8246429d63b2e08ce7e22c.jpg',
   'http://p5etjjbs6.bkt.clouddn.com/img/authentication/member/564b2ba9abbc4d71933a70a2906aa530.jpg',
   '2018-03-24 13:10:44', 1);
/*!40000 ALTER TABLE `t_real_name_authentication_of_member`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role`
--

DROP TABLE IF EXISTS `t_role`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_role` (
  `id`          INT(11)     NOT NULL AUTO_INCREMENT,
  `role_name`   VARCHAR(32) NOT NULL,
  `description` VARCHAR(256)         DEFAULT NULL,
  `name`        VARCHAR(32)          DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_role_role_name_uindex` (`role_name`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 202
  DEFAULT CHARSET = utf8
  COMMENT ='角色信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role`
--

LOCK TABLES `t_role` WRITE;
/*!40000 ALTER TABLE `t_role`
  DISABLE KEYS */;
INSERT INTO `t_role` VALUES (1, 'base', '基础用户角色, 拥有通用的权限, 比如维护个人信息, 更新头像密码等等', '基本用户'),
  (2, 'user_admin', '用户管理员,对用户有真删查改的功能, 可以给用户绑定或删除角色', '用户管理员'),
  (3, 'role_admin', '角色管理员, 对角色有增删查改, 可以对角色进行绑定或者删除权限', '角色管理员'),
  (4, 'permission_admin', '权限管理员, 可以对权限进行增删查改', '权限管理员'), (5, 'auditor', '认证管理员, 负责会员用户和企业用户的认证', '审核员'),
  (6, 'user_role_admin', '用户角色管理员', '用户角色管理员'), (7, 'role_permission_admin', '角色权限管理员', '角色权限管理员'),
  (8, 'data_statistics_admin', '查看网站数据', '后台数据查看者'), (100, 'uncertified_enterprise', '未认证企业用户, 权限受限制', '未认证企业用户'),
  (101, 'enterprise', '企业用户', '企业用户'), (200, 'uncertified_member', '未认证会员, 权限受限制', '未认证会员用户'),
  (201, 'member', '会员用户', '会员用户');
/*!40000 ALTER TABLE `t_role`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role_permission`
--

DROP TABLE IF EXISTS `t_role_permission`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_role_permission` (
  `id`            INT(11) NOT NULL AUTO_INCREMENT,
  `role_id`       INT(11) NOT NULL,
  `permission_id` INT(11) NOT NULL,
  UNIQUE KEY `t_role_permission_id_uindex` (`id`),
  KEY `t_role_permission_t_role_id_fk` (`role_id`),
  KEY `t_role_permission_t_permission_id_fk` (`permission_id`),
  CONSTRAINT `t_role_permission_t_permission_id_fk` FOREIGN KEY (`permission_id`) REFERENCES `t_permission` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `t_role_permission_t_role_id_fk` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 136
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role_permission`
--

LOCK TABLES `t_role_permission` WRITE;
/*!40000 ALTER TABLE `t_role_permission`
  DISABLE KEYS */;
INSERT INTO `t_role_permission`
VALUES (1, 1, 1), (3, 1, 2), (4, 1, 0), (5, 1, 3), (6, 1, 4), (7, 1, 5), (8, 1, 6), (9, 1, 7), (10, 1, 8), (11, 1, 9),
  (12, 1, 10), (13, 1, 11), (14, 1, 12), (15, 1, 30), (16, 1, 31), (17, 200, 2000), (18, 201, 2000), (19, 200, 2001),
  (20, 200, 2002), (21, 100, 1000), (22, 100, 1001), (23, 100, 1002), (24, 101, 1000), (25, 5, 100), (26, 5, 101),
  (28, 5, 102), (29, 5, 103), (30, 5, 104), (31, 5, 105), (32, 5, 106), (33, 5, 107), (34, 5, 108), (35, 5, 109),
  (36, 1, 13), (37, 1, 14), (38, 101, 200), (39, 101, 201), (40, 101, 202), (41, 101, 203), (42, 101, 204),
  (43, 101, 205), (44, 101, 206), (45, 101, 207), (46, 101, 208), (47, 201, 2003), (48, 201, 2004), (49, 201, 2005),
  (50, 101, 1003), (51, 101, 1004), (52, 101, 1005), (53, 101, 1006), (54, 101, 1010), (55, 101, 1011), (56, 101, 1012),
  (57, 101, 1013), (58, 101, 1014), (59, 1, 15), (60, 1, 16), (61, 1, 17), (62, 201, 2010), (63, 201, 2011),
  (64, 201, 2012), (65, 101, 1015), (66, 101, 1016), (67, 101, 1017), (68, 101, 1018), (69, 101, 1019), (70, 101, 1020),
  (71, 101, 1021), (72, 101, 209), (73, 201, 209), (74, 101, 1022), (75, 201, 2013), (76, 201, 2014), (77, 201, 2015),
  (78, 201, 2016), (79, 201, 2017), (80, 101, 1200), (81, 101, 1201), (82, 101, 1202), (83, 101, 1203), (84, 101, 1204),
  (85, 101, 1205), (86, 101, 1206), (87, 101, 1207), (88, 101, 1208), (89, 101, 1209), (90, 101, 1210), (91, 101, 1211),
  (92, 101, 1212), (93, 101, 1213), (94, 101, 1214), (95, 101, 1215), (96, 101, 1216), (97, 101, 1217), (98, 101, 1218),
  (99, 101, 1219), (100, 101, 210), (101, 201, 210), (102, 101, 1220), (103, 101, 1221), (104, 101, 1222),
  (105, 101, 1223), (106, 201, 2018), (107, 201, 2019), (108, 101, 1224), (109, 101, 1225), (110, 2, 50), (111, 2, 51),
  (112, 2, 52), (113, 2, 53), (114, 2, 54), (115, 2, 55), (116, 3, 56), (117, 3, 57), (118, 3, 58), (119, 3, 59),
  (120, 3, 60), (121, 4, 61), (122, 4, 62), (123, 4, 63), (124, 4, 64), (125, 6, 65), (126, 6, 66), (127, 7, 67),
  (128, 7, 68), (129, 201, 2020), (130, 101, 1023), (131, 2, 69), (132, 7, 70), (133, 7, 71), (134, 101, 1024),
  (135, 201, 2021);
/*!40000 ALTER TABLE `t_role_permission`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_satisfaction_evaluation`
--

DROP TABLE IF EXISTS `t_satisfaction_evaluation`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_satisfaction_evaluation` (
  `id`          INT(11)  NOT NULL AUTO_INCREMENT,
  `task_id`     INT(11)  NOT NULL,
  `member_id`   INT(11)           DEFAULT NULL,
  `score`       INT(11)  NOT NULL DEFAULT '10'
  COMMENT '评分, 十分制, 最高十分',
  `content`     VARCHAR(128)      DEFAULT NULL,
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_satisfaction_evaluation_id_uindex` (`id`),
  KEY `t_satisfaction_evaluation_t_task_id_fk` (`task_id`),
  KEY `t_satisfaction_evaluation_t_user_id_fk` (`member_id`),
  CONSTRAINT `t_satisfaction_evaluation_t_task_id_fk` FOREIGN KEY (`task_id`) REFERENCES `t_task` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `t_satisfaction_evaluation_t_user_id_fk` FOREIGN KEY (`member_id`) REFERENCES `t_user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARSET = latin1
  COMMENT ='客服满意度评价';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_satisfaction_evaluation`
--

LOCK TABLES `t_satisfaction_evaluation` WRITE;
/*!40000 ALTER TABLE `t_satisfaction_evaluation`
  DISABLE KEYS */;
INSERT INTO `t_satisfaction_evaluation`
VALUES (1, 12, 15, 10, NULL, '2018-04-11 15:48:09'), (2, 12, 15, 10, NULL, '2018-04-11 18:25:20'),
  (3, 12, 15, 10, NULL, '2018-04-12 12:25:16'), (4, 12, 15, 10, NULL, '2018-04-12 12:43:33'),
  (5, 12, 15, 10, NULL, '2018-04-12 15:26:19');
/*!40000 ALTER TABLE `t_satisfaction_evaluation`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_task`
--

DROP TABLE IF EXISTS `t_task`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_task` (
  `id`                  INT(11)     NOT NULL AUTO_INCREMENT,
  `enterprise_id`       INT(11)     NOT NULL,
  `title`               VARCHAR(64) NOT NULL,
  `description`         TEXT        NOT NULL,
  `base_salary`         DOUBLE      NOT NULL,
  `bonus`               DOUBLE      NOT NULL,
  `total_salary`        DOUBLE      NOT NULL,
  `task_start_time`     DATETIME    NOT NULL,
  `task_end_time`       DATETIME    NOT NULL,
  `create_time`         DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `type`                INT(11)              DEFAULT NULL,
  `number`              INT(11)     NOT NULL DEFAULT '1',
  `status`              INT(11)     NOT NULL DEFAULT '1',
  `upload_flie_path`    VARCHAR(256)         DEFAULT NULL,
  `need_upload_file`    TINYINT(1)           DEFAULT '0',
  `working_time_start1` TIME        NOT NULL,
  `working_time_end1`   TIME        NOT NULL,
  `working_time_start2` TIME                 DEFAULT NULL,
  `working_time_end2`   TIME                 DEFAULT NULL,
  `working_time_start3` TIME                 DEFAULT NULL,
  `working_time_end3`   TIME                 DEFAULT NULL,
  `working_time_start4` TIME                 DEFAULT NULL,
  `working_time_end4`   TIME                 DEFAULT NULL,
  `is_need_examination` INT(11)     NOT NULL DEFAULT '0'
  COMMENT '是否需要笔试 0 不要要 1 需要',
  `examination_type`    INT(11)              DEFAULT NULL
  COMMENT '试卷类型 0 自动生成的 1 手动创建的\n',
  `examination_id`      INT(11)              DEFAULT NULL
  COMMENT '试卷id 针对type 为1',
  `rule_id`             INT(11)              DEFAULT NULL
  COMMENT '生成规则id 针对type为0的情况',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_task_id_uindex` (`id`),
  KEY `t_task_t_user_id_fk` (`enterprise_id`),
  CONSTRAINT `t_task_t_user_id_fk` FOREIGN KEY (`enterprise_id`) REFERENCES `t_user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 16
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_task`
--

LOCK TABLES `t_task` WRITE;
/*!40000 ALTER TABLE `t_task`
  DISABLE KEYS */;
INSERT INTO `t_task` VALUES
  (2, 12, '这是测试', '123123', 100, 12, 224, '2018-04-01 00:00:00', '2018-04-03 00:00:00', '2018-03-24 13:14:10', NULL, 2,
                                                                                                                     5,
                                                                                                                     NULL,
                                                                                                                     0,
                                                                                                                     '08:00:00',
                                                                                                                     '12:00:00',
                                                                                                                     '14:00:00',
                                                                                                                     '18:00:00',
                                                                                                                     NULL,
                                                                                                                     NULL,
   NULL, NULL, 0, NULL, NULL, NULL),
  (3, 12, '这是测试2', '123123', 1000000, 122222, 1122222, '2018-04-01 00:00:00', '2018-04-03 00:00:00', '2018-03-24 13:16:22', NULL, 1, 5, NULL, 0, '08:00:00', '12:00:00', '14:00:00', '18:00:00', NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL),
  (4, 12, '数字标注', '数字标注', 1234, 2, 3708, '2018-03-30 12:00:00', '2018-04-14 12:00:00', '2018-03-24 18:02:35', NULL, 3, 5, NULL, 0, '12:00:00', '13:00:00', '14:00:00', '15:00:00', '16:00:00', '17:00:00', '18:00:00', '19:00:00', 0, NULL, NULL, NULL),
  (5, 12, '宣传肉蟹煲', '在各大网站贴吧上留言说肉蟹煲真好吃', 122, 1, 496, '2018-03-31 12:00:00', '2018-04-20 12:00:00', '2018-03-25 20:50:41', NULL, 4, 5, NULL, 0, '12:00:00', '13:00:00', '14:00:00', '15:00:00', '16:00:00', '17:00:00', '18:00:00', '19:00:00', 0, NULL, NULL, NULL),
  (6, 12, '这是关于一个市场调研的项目', '这个项目在于调查宁波周边各个城市交通状况的情况。详情请发到邮箱：33450236968@qq.com', 1233, 33, 1266, '2018-04-04 12:00:00', '2018-06-05 12:00:00', '2018-03-31 21:39:30', NULL, 1, 3, NULL, 0, '12:00:00', '13:00:00', '14:00:00', '15:00:00', '16:00:00', '17:00:00', '18:00:00', '19:00:00', 0, NULL, NULL, NULL),
  (7, 12, '调查附近居民的健身情况', '为我们提供有意向健身的会员的信息。', 1200, 100, 1300, '2018-04-19 12:00:00', '2018-04-30 12:00:00', '2018-04-01 08:28:27', NULL, 1, 2, NULL, 0, '12:00:00', '13:00:00', '14:00:00', '15:00:00', '16:00:00', '17:00:00', '18:00:00', '19:00:00', 0, NULL, NULL, NULL),
  (8, 12, '这是一个关于蔬菜价格的任务', '调查附近菜市场蔬菜价格的波动。并且制作好excel表格。发给我 12312312@qq.com', 1222, 22, 1244, '2018-04-04 12:00:00', '2018-04-27 12:00:00', '2018-04-01 08:31:25', NULL, 1, 5, NULL, 0, '12:00:00', '13:00:00', '14:00:00', '15:00:00', '16:00:00', '17:00:00', '18:00:00', '19:00:00', 0, NULL, NULL, NULL),
  (9, 12, '小黄车', '调查附近小黄车的使用情况', 2133, 12, 2145, '2018-04-27 12:00:00', '2018-05-17 12:00:00', '2018-04-08 19:30:39', NULL, 1, 1, NULL, 0, '12:00:00', '13:00:00', '14:00:00', '15:00:00', '16:00:00', '17:00:00', '18:00:00', '19:00:00', 1, 1, 2, NULL),
  (10, 12, '调查饭菜情况', '调查您附近的菜市场蔬菜的价格', 100, 12, 112, '2018-04-13 12:00:00', '2018-05-10 12:00:00', '2018-04-08 20:49:35', NULL, 1, 1, NULL, 0, '12:00:00', '13:00:00', '14:00:00', '15:00:00', '16:00:00', '17:00:00', '18:00:00', '19:00:00', 1, 1, 1, 3),
  (11, 12, '查看学校附近的小黄车', '观察小黄车使用的情况以及小黄车的分布情况', 1200, 100, 1300, '2018-04-12 12:00:00', '2018-05-31 12:00:00', '2018-04-09 15:55:35', NULL, 1, 3, NULL, 0, '12:00:00', '13:00:00', '14:00:00', '15:00:00', '16:00:00', '17:00:00', '18:00:00', '19:00:00', 1, 1, 1, NULL),
  (12, 12, '调查大学生的月生活费情况', '生活费统计好发我qq邮箱 3450236968@qq.com', 12222, 22, 12244, '2018-04-11 12:00:00', '2018-04-18 12:00:00', '2018-04-11 11:59:41', NULL, 1, 3, NULL, 0, '12:00:00', '13:00:00', '14:00:00', '15:00:00', '16:00:00', '17:00:00', '18:00:00', '19:00:00', 0, 0, NULL, NULL),
  (13, 12, '调查最近大学生的睡眠情况', '调查每天大约大学生睡多少时间以及睡眠的时间段，详情发我qq邮箱3450236968@qq.com', 1200, 100, 1300, '2018-04-13 12:00:00',
       '2018-04-28 12:00:00', '2018-04-11 18:19:24', NULL, 1, 2, NULL, 0, '12:00:00', '13:00:00', '14:00:00',
                                                           '15:00:00', '16:00:00', '17:00:00', '18:00:00', '19:00:00',
   1, 1, 1, NULL), (14, 12, '小黄车的调查', '调查小黄车的使用情况 详情发我qq邮箱3450236968@qq.com', 1200, 100, 1300, '2018-04-13 12:00:00',
                        '2018-04-27 12:00:00', '2018-04-12 12:37:08', NULL, 1, 2, NULL, 0, '12:00:00', '13:00:00',
                                                                            '14:00:00', '15:00:00', '16:00:00',
                                                                            '17:00:00', '18:00:00', '19:00:00', 1, 1, 1,
                    NULL),
  (15, 12, '小黄车的使用情况二', '调查附近小黄车的使用情况详情发我qq邮箱3450236968@qq.com', 1111, 111, 1222, '2018-04-13 12:00:00',
       '2018-04-19 12:00:00', '2018-04-12 15:21:05', NULL, 1, 2, NULL, 0, '12:00:00', '13:00:00', '14:00:00',
                                                           '15:00:00', '16:00:00', '17:00:00', '18:00:00', '19:00:00',
   1, 1, 1, NULL);
/*!40000 ALTER TABLE `t_task`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_task_application`
--

DROP TABLE IF EXISTS `t_task_application`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_task_application` (
  `id`           INT(11)  NOT NULL AUTO_INCREMENT,
  `task_id`      INT(11)  NOT NULL,
  `member_id`    INT(11)  NOT NULL,
  `status`       INT(11)  NOT NULL DEFAULT '0'
  COMMENT '0未处理, 1审核通过2审核不通过3.未处理自动失效4.时间冲突, 自动生效',
  `create_time`  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `publisher_id` INT(11)  NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_task_application_id_uindex` (`id`),
  KEY `t_task_application_t_task_id_fk` (`task_id`),
  KEY `t_task_application_t_user_id_fk` (`member_id`),
  CONSTRAINT `t_task_application_t_task_id_fk` FOREIGN KEY (`task_id`) REFERENCES `t_task` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `t_task_application_t_user_id_fk` FOREIGN KEY (`member_id`) REFERENCES `t_user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 12
  DEFAULT CHARSET = utf8
  COMMENT ='任务申请表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_task_application`
--

LOCK TABLES `t_task_application` WRITE;
/*!40000 ALTER TABLE `t_task_application`
  DISABLE KEYS */;
INSERT INTO `t_task_application`
VALUES (1, 4, 15, 1, '2018-03-24 22:02:12', 12), (2, 4, 18, 1, '2018-03-25 10:42:31', 12),
  (3, 5, 15, 2, '2018-03-25 20:51:13', 12), (4, 6, 16, 1, '2018-03-31 21:42:26', 12),
  (5, 7, 15, 1, '2018-04-01 08:32:57', 12), (6, 10, 15, 0, '2018-04-09 13:28:08', 12),
  (7, 11, 15, 1, '2018-04-09 15:58:11', 12), (8, 12, 15, 1, '2018-04-11 11:59:54', 12),
  (9, 13, 15, 1, '2018-04-11 18:20:53', 12), (10, 14, 15, 1, '2018-04-12 12:38:38', 12),
  (11, 15, 15, 1, '2018-04-12 15:22:54', 12);
/*!40000 ALTER TABLE `t_task_application`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_task_employment`
--

DROP TABLE IF EXISTS `t_task_employment`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_task_employment` (
  `id`                 INT(11) NOT NULL AUTO_INCREMENT,
  `task_id`            INT(11) NOT NULL,
  `member_id`          INT(11) NOT NULL,
  `status`             INT(11) NOT NULL DEFAULT '0'
  COMMENT '0 任务未开始, 1任务进行中, 2任务结束, 3任务取消',
  `create_time`        DATETIME         DEFAULT CURRENT_TIMESTAMP,
  `publisher_id`       INT(11) NOT NULL,
  `customer_avg_score` DOUBLE           DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_task_employment_id_uindex` (`id`),
  KEY `t_task_employment_t_task_id_fk` (`task_id`),
  KEY `t_task_employment_t_user_id_fk` (`member_id`),
  CONSTRAINT `t_task_employment_t_task_id_fk` FOREIGN KEY (`task_id`) REFERENCES `t_task` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `t_task_employment_t_user_id_fk` FOREIGN KEY (`member_id`) REFERENCES `t_user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 21
  DEFAULT CHARSET = utf8
  COMMENT ='任务雇佣关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_task_employment`
--

LOCK TABLES `t_task_employment` WRITE;
/*!40000 ALTER TABLE `t_task_employment`
  DISABLE KEYS */;
INSERT INTO `t_task_employment`
VALUES (1, 4, 18, 3, '2018-03-25 10:43:40', 12, NULL), (2, 5, 15, 3, '2018-03-25 21:13:02', 12, NULL),
  (10, 4, 15, 3, '2018-03-26 12:48:50', 12, NULL), (14, 6, 16, 1, '2018-04-01 08:13:30', 12, NULL),
  (15, 7, 15, 0, '2018-04-01 08:33:54', 12, NULL), (16, 11, 15, 1, '2018-04-09 15:59:09', 12, NULL),
  (17, 12, 15, 1, '2018-04-11 12:00:03', 12, NULL), (18, 13, 15, 0, '2018-04-11 18:21:16', 12, NULL),
  (19, 14, 15, 0, '2018-04-12 12:39:12', 12, NULL), (20, 15, 15, 0, '2018-04-12 15:23:17', 12, NULL);
/*!40000 ALTER TABLE `t_task_employment`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_test`
--

DROP TABLE IF EXISTS `t_test`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_test` (
  `id`       INT(11) NOT NULL AUTO_INCREMENT,
  `content`  VARCHAR(128)     DEFAULT NULL,
  `time_set` TIME             DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_test_id_uindex` (`id`),
  FULLTEXT KEY `t_test` (`content`) /*!50100 WITH PARSER `ngram` */
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 19
  DEFAULT CHARSET = utf8
  COMMENT ='测试表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_test`
--

LOCK TABLES `t_test` WRITE;
/*!40000 ALTER TABLE `t_test`
  DISABLE KEYS */;
INSERT INTO `t_test`
VALUES (1, 'this is test', NULL), (2, 'this is test', NULL), (3, 'this is test', NULL), (4, 'this is test', '10:29:54'),
  (5, 'this is test', '05:29:54'), (6, 'aaaaa你好', NULL), (7, '数据库系统概念', NULL), (8, '深入解析Oracle', NULL),
  (9, '高性能MySQL', NULL), (10, 'SQL必知必会', NULL), (11, '数据库管理', NULL), (12, '数据库应用开发', NULL), (13, 'hhhhhhb', NULL),
  (14, '数据库与事务处理', NULL), (15, ' NoSQL精髓', NULL), (16, 'SQL 语言详解', NULL), (17, 'MySQL 从入门到删库', NULL),
  (18, 'mybatis 快速入门', NULL);
/*!40000 ALTER TABLE `t_test`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `id`                     INT(11)      NOT NULL AUTO_INCREMENT,
  `username`               VARCHAR(32)  NOT NULL,
  `password`               VARCHAR(32)  NOT NULL,
  `phone`                  VARCHAR(32)  NOT NULL,
  `create_time`            DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `head_image`             VARCHAR(128) NOT NULL DEFAULT 'http://p5etjjbs6.bkt.clouddn.com/img/head/default.jpg',
  `salt`                   VARCHAR(32)           DEFAULT NULL,
  `type`                   INT(11)      NOT NULL DEFAULT '1',
  `email`                  VARCHAR(128)          DEFAULT NULL,
  `sex`                    INT(11)               DEFAULT '0',
  `qq`                     VARCHAR(32)           DEFAULT NULL,
  `wechat`                 VARCHAR(64)           DEFAULT NULL,
  `address`                VARCHAR(128)          DEFAULT NULL,
  `description`            VARCHAR(256)          DEFAULT NULL,
  `educational_experience` VARCHAR(128)          DEFAULT NULL,
  `is_delete`              INT(11)      NOT NULL DEFAULT '0'
  COMMENT '0 用户未删除 1 用户已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_user_username_uindex` (`username`),
  UNIQUE KEY `t_user_phone_uindex` (`phone`),
  UNIQUE KEY `t_user_email_uindex` (`email`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 19
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user`
  DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1, 'admin', '9fa565c587af86012a65346ef3e53927', '12345678901', '2018-03-16 16:15:55',
                                'http://p5etjjbs6.bkt.clouddn.com/img/head/default.jpg', '1234567890', 0, NULL, 0, NULL,
                             NULL, NULL, NULL, NULL, 0),
  (12, '斯晓冬', 'c0cd7f3c2cd5f08c9243f72225486e17', '18329067503', '2018-03-14 10:39:16',
       'http://p5etjjbs6.bkt.clouddn.com/img/head/3fc06269664a4d82a810253fd23cc9af.jpg',
       '152099515625137f3cfef385c4168b6c', 1, '3450236968@qq.com', 0, '15757469452', NULL, '山东省临沂市兰山区空铺',
   '我是网络151的斯晓冬11111111好几个胡歌', '宁夏吴忠市宁夏民族职业技术学院', 0),
  (15, '董凌浩', '549a41d8e76b57acbfc134f49093a826', '18888643093', '2018-03-14 14:40:32',
       'http://p5etjjbs6.bkt.clouddn.com/img/head/00db0c60981d4a5a9d0887a48e67678e.jpg',
       '1521009632112b03aa9cf7f3342f1a5b', 2, '18888643093@163.com', 1, '3450236968', NULL, '浙江省嘉兴市海盐县海盐小学', 'ride',
   '浙江省宁波市宁波工程学院', 0), (16, '唐秀娟', '46107f00b5b62ee6e729940516cbce0a', '15757469452', '2018-03-24 13:08:36',
                            'http://p5etjjbs6.bkt.clouddn.com/img/head/default.jpg', '152186811635501149a4bbd014d95a07',
                            2, NULL, 0, NULL, NULL, NULL, NULL, NULL, 0),
  (17, '我是小菜', '31eb4499f622107c8b8765e6938d88a8', '13758875742', '2018-03-24 13:54:44',
       'http://p5etjjbs6.bkt.clouddn.com/img/head/default.jpg', '1521870883863221ead975e73482e9f4', 1,
       '1825001770@qq.com', 0, '123123123123', NULL, '天津市天津市河西区哈2日访问量就', '234', '北京市北京市北京交通大学', 0),
  (18, '小方', 'a841dae0f8ae064a5ac6f99867104db8', '15757468642', '2018-03-25 10:39:48',
       'http://p5etjjbs6.bkt.clouddn.com/img/head/default.jpg', '1521945588169a2ec01ef4064484baa8', 2, NULL, 0, NULL,
   NULL, NULL, NULL, NULL, 0);
/*!40000 ALTER TABLE `t_user`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_login_log`
--

DROP TABLE IF EXISTS `t_user_login_log`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user_login_log` (
  `id`         INT(11)  NOT NULL AUTO_INCREMENT,
  `login_ip`   VARCHAR(150)      DEFAULT NULL,
  `login_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id`    INT(11)  NOT NULL,
  PRIMARY KEY (`id`),
  KEY `t_user_login_log_t_user_id_fk` (`user_id`),
  CONSTRAINT `t_user_login_log_t_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 345
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_login_log`
--

LOCK TABLES `t_user_login_log` WRITE;
/*!40000 ALTER TABLE `t_user_login_log`
  DISABLE KEYS */;
INSERT INTO `t_user_login_log`
VALUES (7, '192.168.1.104', '2018-03-14 10:48:58', 12), (8, '192.168.1.104', '2018-03-14 10:49:00', 12),
  (9, '192.168.1.104', '2018-03-14 10:49:51', 12), (10, '192.168.1.104', '2018-03-14 10:53:37', 12),
  (11, '192.168.1.104', '2018-03-14 10:53:50', 12), (12, '192.168.1.104', '2018-03-14 10:54:40', 12),
  (13, '192.168.1.104', '2018-03-14 10:55:03', 12), (14, '192.168.1.104', '2018-03-14 10:55:34', 12),
  (15, '192.168.1.104', '2018-03-14 10:55:46', 12), (16, '192.168.1.104', '2018-03-14 12:40:29', 12),
  (17, '192.168.1.104', '2018-03-14 12:41:21', 12), (18, '192.168.1.104', '2018-03-14 12:43:07', 12),
  (19, '192.168.1.104', '2018-03-14 12:51:19', 12), (20, '192.168.1.104', '2018-03-14 12:53:11', 12),
  (21, '192.168.1.104', '2018-03-14 12:55:02', 12), (22, '192.168.1.104', '2018-03-14 12:55:42', 12),
  (23, '192.168.1.104', '2018-03-14 12:57:34', 12), (24, '192.168.1.104', '2018-03-14 12:58:10', 12),
  (25, '192.168.1.104', '2018-03-14 12:59:47', 12), (26, '192.168.1.104', '2018-03-14 13:34:37', 12),
  (27, '192.168.1.104', '2018-03-14 13:35:26', 12), (28, '0:0:0:0:0:0:0:1', '2018-03-14 18:48:54', 15),
  (29, '0:0:0:0:0:0:0:1', '2018-03-15 12:19:21', 15), (30, '0:0:0:0:0:0:0:1', '2018-03-15 12:19:47', 15),
  (31, '192.168.1.104', '2018-03-15 16:01:35', 12), (32, '192.168.1.104', '2018-03-15 16:11:37', 12),
  (33, '192.168.1.104', '2018-03-15 16:19:46', 12), (34, '192.168.1.104', '2018-03-15 16:23:44', 12),
  (35, '192.168.1.104', '2018-03-15 16:25:15', 12), (36, '192.168.1.104', '2018-03-15 16:25:15', 12),
  (37, '192.168.1.104', '2018-03-15 16:35:27', 12), (38, '0:0:0:0:0:0:0:1', '2018-03-15 17:24:01', 15),
  (39, '192.168.1.104', '2018-03-15 18:09:30', 12), (40, '192.168.1.104', '2018-03-15 18:13:46', 12),
  (41, '192.168.1.104', '2018-03-15 18:32:20', 12), (42, '192.168.1.104', '2018-03-15 18:33:02', 12),
  (43, '192.168.1.104', '2018-03-15 18:35:58', 12), (44, '192.168.1.104', '2018-03-15 18:36:04', 12),
  (45, '192.168.1.104', '2018-03-15 18:36:20', 12), (46, '192.168.1.104', '2018-03-15 18:42:28', 12),
  (47, '192.168.1.104', '2018-03-15 18:49:38', 12), (48, '0:0:0:0:0:0:0:1', '2018-03-16 15:22:15', 15),
  (49, '0:0:0:0:0:0:0:1', '2018-03-16 16:19:22', 1), (50, '0:0:0:0:0:0:0:1', '2018-03-16 20:33:32', 15),
  (51, '192.168.1.104', '2018-03-16 20:53:31', 12), (52, '192.168.1.104', '2018-03-16 21:22:22', 12),
  (53, '192.168.1.104', '2018-03-17 09:34:24', 12), (54, '192.168.1.104', '2018-03-17 09:53:42', 12),
  (55, '0:0:0:0:0:0:0:1', '2018-03-17 10:35:45', 15), (56, '192.168.1.104', '2018-03-17 13:27:42', 12),
  (57, '192.168.1.104', '2018-03-17 14:02:29', 12), (58, '0:0:0:0:0:0:0:1', '2018-03-17 20:07:08', 1),
  (59, '192.168.1.104', '2018-03-18 09:37:56', 12), (60, '192.168.1.104', '2018-03-18 10:30:27', 12),
  (61, '192.168.1.104', '2018-03-18 18:18:33', 12), (62, '192.168.1.104', '2018-03-18 18:18:46', 12),
  (63, '192.168.1.104', '2018-03-18 18:22:37', 12), (64, '192.168.1.104', '2018-03-18 18:45:36', 12),
  (65, '0:0:0:0:0:0:0:1', '2018-03-18 20:46:01', 12), (66, '192.168.1.104', '2018-03-19 12:58:42', 12),
  (67, '0:0:0:0:0:0:0:1', '2018-03-19 21:42:09', 15), (68, '0:0:0:0:0:0:0:1', '2018-03-19 21:43:27', 15),
  (69, '0:0:0:0:0:0:0:1', '2018-03-19 21:45:54', 15), (70, '0:0:0:0:0:0:0:1', '2018-03-19 22:59:15', 12),
  (71, '192.168.1.104', '2018-03-20 12:34:34', 12), (72, '192.168.1.104', '2018-03-20 15:28:15', 12),
  (73, '192.168.1.104', '2018-03-20 22:15:22', 12), (74, '192.168.1.104', '2018-03-21 12:04:59', 12),
  (75, '192.168.1.104', '2018-03-21 21:56:23', 12), (76, '192.168.1.104', '2018-03-21 21:57:55', 12),
  (77, '192.168.1.104', '2018-03-21 22:04:19', 15), (78, '192.168.1.104', '2018-03-21 22:04:36', 15),
  (79, '192.168.1.104', '2018-03-21 22:05:25', 15), (80, '192.168.1.104', '2018-03-21 22:07:57', 15),
  (81, '192.168.1.104', '2018-03-21 22:08:29', 15), (82, '192.168.1.104', '2018-03-21 22:32:58', 15),
  (83, '192.168.1.104', '2018-03-21 22:33:46', 15), (84, '192.168.1.104', '2018-03-21 22:34:35', 15),
  (85, '192.168.1.104', '2018-03-21 22:34:55', 15), (86, '192.168.1.104', '2018-03-22 10:04:30', 12),
  (87, '192.168.1.104', '2018-03-22 10:11:15', 15), (88, '192.168.1.104', '2018-03-22 10:11:42', 15),
  (89, '192.168.1.104', '2018-03-22 10:12:45', 15), (90, '192.168.1.104', '2018-03-22 10:13:27', 15),
  (91, '192.168.1.104', '2018-03-22 10:13:58', 12), (92, '192.168.1.104', '2018-03-22 10:14:15', 15),
  (93, '192.168.1.104', '2018-03-22 10:32:31', 12), (94, '192.168.1.104', '2018-03-22 10:48:33', 15),
  (95, '192.168.1.104', '2018-03-22 12:59:02', 15), (96, '192.168.1.104', '2018-03-22 15:26:42', 15),
  (97, '192.168.1.104', '2018-03-22 18:08:15', 12), (98, '0:0:0:0:0:0:0:1', '2018-03-23 20:09:33', 12),
  (99, '192.168.1.104', '2018-03-23 20:43:40', 12), (100, '192.168.1.104', '2018-03-23 20:48:30', 15),
  (101, '192.168.1.104', '2018-03-23 20:49:06', 12), (102, '192.168.1.104', '2018-03-23 20:49:49', 12),
  (103, '192.168.1.104', '2018-03-23 21:24:33', 1), (104, '192.168.1.104', '2018-03-23 21:27:46', 1),
  (105, '192.168.1.104', '2018-03-23 21:29:19', 1), (106, '192.168.1.104', '2018-03-23 21:36:20', 12),
  (107, '192.168.1.104', '2018-03-23 21:37:18', 12), (108, '192.168.1.104', '2018-03-24 12:10:32', 1),
  (109, '192.168.1.104', '2018-03-24 12:32:28', 1), (110, '192.168.1.104', '2018-03-24 13:09:03', 16),
  (111, '192.168.1.104', '2018-03-24 13:11:08', 1), (112, '192.168.1.104', '2018-03-24 13:33:35', 1),
  (113, '192.168.1.104', '2018-03-24 13:55:11', 17), (114, '192.168.1.104', '2018-03-24 13:59:24', 1),
  (115, '192.168.1.104', '2018-03-24 14:09:48', 12), (116, '192.168.1.104', '2018-03-24 16:10:19', 12),
  (117, '192.168.1.104', '2018-03-24 18:10:45', 12), (118, '0:0:0:0:0:0:0:1', '2018-03-24 18:14:19', 12),
  (119, '192.168.1.104', '2018-03-24 20:18:51', 12), (120, '0:0:0:0:0:0:0:1', '2018-03-24 20:28:39', 15),
  (121, '192.168.1.100', '2018-03-25 08:44:15', 12), (122, '192.168.1.100', '2018-03-25 09:30:28', 15),
  (123, '0:0:0:0:0:0:0:1', '2018-03-25 10:42:16', 18), (124, '192.168.1.100', '2018-03-25 20:23:05', 12),
  (125, '192.168.1.100', '2018-03-25 20:51:05', 15), (126, '192.168.1.100', '2018-03-25 20:51:59', 12),
  (127, '192.168.1.100', '2018-03-25 21:10:47', 12), (128, '192.168.1.102', '2018-03-26 12:44:44', 1),
  (129, '192.168.1.102', '2018-03-26 12:46:09', 12), (130, '192.168.1.102', '2018-03-26 15:54:33', 15),
  (131, '192.168.1.102', '2018-03-26 16:08:09', 1), (132, '192.168.1.102', '2018-03-26 16:11:50', 12),
  (133, '192.168.1.102', '2018-03-26 21:08:18', 12), (134, '192.168.1.102', '2018-03-26 21:47:15', 15),
  (135, '192.168.1.102', '2018-03-26 21:50:02', 12), (136, '192.168.1.102', '2018-03-26 21:50:36', 12),
  (137, '192.168.1.102', '2018-03-26 21:50:49', 15), (138, '192.168.1.102', '2018-03-27 09:29:22', 12),
  (139, '192.168.1.102', '2018-03-27 09:31:31', 15), (140, '192.168.1.102', '2018-03-27 09:32:27', 1),
  (141, '192.168.1.100', '2018-03-28 21:11:38', 12), (142, '0:0:0:0:0:0:0:1', '2018-03-29 20:50:29', 12),
  (143, '0:0:0:0:0:0:0:1', '2018-03-30 13:48:33', 12), (144, '192.168.1.100', '2018-03-31 13:22:29', 12),
  (145, '192.168.1.100', '2018-03-31 14:08:30', 12), (146, '192.168.1.100', '2018-03-31 14:08:42', 12),
  (147, '192.168.1.100', '2018-03-31 18:41:34', 12), (148, '192.168.1.100', '2018-03-31 21:19:15', 15),
  (149, '192.168.1.100', '2018-03-31 21:20:40', 1), (150, '192.168.1.100', '2018-03-31 21:23:16', 17),
  (151, '192.168.1.100', '2018-03-31 21:30:59', 1), (152, '192.168.1.100', '2018-03-31 21:31:45', 17),
  (153, '192.168.1.100', '2018-03-31 21:32:19', 1), (154, '192.168.1.100', '2018-03-31 21:35:44', 17),
  (155, '192.168.1.100', '2018-03-31 21:37:18', 1), (156, '192.168.1.100', '2018-03-31 21:37:59', 12),
  (157, '192.168.1.100', '2018-03-31 21:41:02', 1), (158, '192.168.1.100', '2018-03-31 21:41:45', 16),
  (159, '192.168.1.100', '2018-03-31 21:44:29', 15), (160, '192.168.1.100', '2018-04-01 08:12:54', 12),
  (161, '192.168.1.100', '2018-04-01 08:23:56', 15), (162, '192.168.1.100', '2018-04-01 08:25:26', 12),
  (163, '192.168.1.100', '2018-04-01 08:27:28', 12), (164, '192.168.1.100', '2018-04-01 08:31:51', 15),
  (165, '192.168.1.100', '2018-04-01 08:33:30', 12), (166, '192.168.1.100', '2018-04-01 08:43:56', 15),
  (167, '192.168.1.100', '2018-04-01 08:46:30', 1), (168, '192.168.1.100', '2018-04-01 08:56:51', 12),
  (169, '192.168.1.101', '2018-04-02 12:59:52', 12), (170, '192.168.1.101', '2018-04-02 13:17:49', 12),
  (171, '192.168.1.101', '2018-04-02 15:07:34', 15), (172, '192.168.1.101', '2018-04-02 15:08:10', 1),
  (173, '192.168.1.101', '2018-04-02 15:12:10', 12), (174, '0:0:0:0:0:0:0:1', '2018-04-02 16:12:47', 12),
  (175, '192.168.1.101', '2018-04-02 20:09:36', 15), (176, '192.168.1.101', '2018-04-02 20:28:36', 12),
  (177, '192.168.1.101', '2018-04-02 20:29:19', 15), (178, '0:0:0:0:0:0:0:1', '2018-04-02 20:51:12', 15),
  (179, '192.168.1.101', '2018-04-02 21:39:38', 12), (180, '192.168.1.101', '2018-04-02 22:17:39', 15),
  (181, '192.168.1.101', '2018-04-02 23:51:57', 12), (182, '192.168.1.101', '2018-04-03 08:59:47', 12),
  (183, '192.168.1.101', '2018-04-03 09:24:38', 15), (184, '192.168.1.101', '2018-04-03 09:38:18', 12),
  (185, '192.168.1.101', '2018-04-03 09:38:56', 12), (186, '192.168.1.101', '2018-04-03 09:42:41', 15),
  (187, '192.168.1.101', '2018-04-03 11:57:08', 12), (188, '192.168.1.101', '2018-04-03 12:04:50', 12),
  (189, '192.168.1.101', '2018-04-03 12:05:23', 15), (190, '192.168.1.101', '2018-04-03 12:36:23', 12),
  (191, '192.168.1.101', '2018-04-03 12:51:10', 12), (192, '192.168.1.101', '2018-04-03 12:54:34', 15),
  (193, '192.168.1.101', '2018-04-03 13:02:12', 12), (194, '192.168.1.101', '2018-04-03 13:02:59', 12),
  (195, '192.168.1.101', '2018-04-03 15:48:15', 12), (196, '192.168.1.101', '2018-04-03 15:53:15', 15),
  (197, '192.168.1.101', '2018-04-03 16:25:02', 12), (198, '192.168.1.101', '2018-04-03 16:26:14', 15),
  (199, '192.168.1.101', '2018-04-03 16:54:54', 15), (200, '192.168.1.101', '2018-04-03 18:02:43', 12),
  (201, '192.168.1.101', '2018-04-03 18:37:37', 12), (202, '192.168.1.101', '2018-04-03 18:38:02', 15),
  (203, '192.168.1.101', '2018-04-03 18:38:13', 1), (204, '192.168.1.101', '2018-04-03 18:39:27', 12),
  (205, '192.168.1.101', '2018-04-03 19:02:20', 12), (206, '192.168.1.101', '2018-04-03 19:30:06', 15),
  (207, '192.168.1.101', '2018-04-03 19:35:38', 1), (208, '192.168.1.101', '2018-04-03 19:45:22', 12),
  (209, '192.168.1.101', '2018-04-03 20:31:24', 12), (210, '192.168.1.101', '2018-04-03 20:32:38', 15),
  (211, '192.168.1.101', '2018-04-03 20:38:29', 12), (212, '192.168.1.101', '2018-04-03 20:58:59', 12),
  (213, '192.168.1.101', '2018-04-03 21:09:29', 12), (214, '192.168.1.101', '2018-04-04 09:34:41', 12),
  (215, '192.168.1.101', '2018-04-04 17:03:48', 15), (216, '192.168.1.101', '2018-04-04 17:04:15', 1),
  (217, '192.168.1.101', '2018-04-04 17:04:43', 12), (218, '192.168.1.104', '2018-04-04 19:09:12', 12),
  (219, '192.168.1.104', '2018-04-04 19:19:05', 12), (220, '192.168.1.101', '2018-04-05 10:53:22', 12),
  (221, '192.168.1.101', '2018-04-05 10:53:22', 12), (222, '0:0:0:0:0:0:0:1', '2018-04-05 20:22:54', 15),
  (223, '0:0:0:0:0:0:0:1', '2018-04-05 20:23:29', 12), (224, '0:0:0:0:0:0:0:1', '2018-04-06 15:10:34', 12),
  (225, '192.168.1.101', '2018-04-06 22:54:37', 12), (226, '192.168.1.101', '2018-04-07 11:53:29', 12),
  (227, '192.168.1.101', '2018-04-07 18:44:46', 12), (228, '192.168.1.101', '2018-04-08 10:15:59', 12),
  (229, '192.168.1.101', '2018-04-08 14:37:05', 15), (230, '192.168.1.101', '2018-04-08 14:38:05', 12),
  (231, '192.168.1.101', '2018-04-08 16:00:43', 15), (232, '192.168.1.101', '2018-04-08 16:01:23', 12),
  (233, '0:0:0:0:0:0:0:1', '2018-04-08 16:29:15', 12), (234, '192.168.1.101', '2018-04-08 19:40:58', 15),
  (235, '192.168.1.101', '2018-04-08 20:47:58', 12), (236, '192.168.1.101', '2018-04-08 20:50:10', 12),
  (237, '192.168.1.101', '2018-04-08 20:50:46', 15), (238, '192.168.1.101', '2018-04-09 09:05:16', 15),
  (239, '192.168.1.100', '2018-04-09 11:57:17', 15), (240, '192.168.1.100', '2018-04-09 12:10:43', 15),
  (241, '192.168.1.100', '2018-04-09 13:00:18', 15), (242, '192.168.1.100', '2018-04-09 13:00:18', 15),
  (243, '192.168.1.100', '2018-04-09 13:15:21', 12), (244, '0:0:0:0:0:0:0:1', '2018-04-09 13:15:49', 12),
  (245, '192.168.1.100', '2018-04-09 13:20:59', 15), (246, '192.168.1.100', '2018-04-09 13:28:43', 12),
  (247, '192.168.1.100', '2018-04-09 13:29:23', 15), (248, '192.168.1.100', '2018-04-09 13:30:02', 12),
  (249, '192.168.1.100', '2018-04-09 15:02:13', 12), (250, '192.168.1.100', '2018-04-09 15:54:00', 12),
  (251, '192.168.1.100', '2018-04-09 15:56:01', 15), (252, '192.168.1.100', '2018-04-09 15:57:41', 12),
  (253, '192.168.1.100', '2018-04-09 15:58:05', 15), (254, '192.168.1.100', '2018-04-09 15:58:34', 12),
  (255, '192.168.1.100', '2018-04-09 16:03:12', 1), (256, '192.168.1.100', '2018-04-09 16:03:53', 15),
  (257, '192.168.1.100', '2018-04-09 16:05:10', 12), (258, '192.168.1.100', '2018-04-09 16:30:11', 12),
  (259, '192.168.1.100', '2018-04-09 16:35:12', 15), (260, '192.168.1.100', '2018-04-09 23:56:20', 12),
  (261, '192.168.1.100', '2018-04-09 23:58:41', 15), (262, '192.168.1.100', '2018-04-09 23:58:54', 1),
  (263, '192.168.1.100', '2018-04-09 23:59:19', 1), (264, '192.168.1.100', '2018-04-10 00:04:08', 12),
  (265, '192.168.1.100', '2018-04-10 00:05:45', 15), (266, '192.168.1.100', '2018-04-10 00:07:21', 1),
  (267, '192.168.1.100', '2018-04-10 10:27:13', 15), (268, '192.168.1.100', '2018-04-10 10:31:28', 12),
  (269, '192.168.1.100', '2018-04-10 10:35:01', 15), (270, '192.168.1.100', '2018-04-10 10:43:33', 1),
  (271, '192.168.1.100', '2018-04-10 11:45:53', 12), (272, '192.168.1.100', '2018-04-10 12:10:26', 1),
  (273, '192.168.1.100', '2018-04-10 12:22:56', 1), (274, '192.168.1.100', '2018-04-10 18:51:50', 1),
  (275, '192.168.1.100', '2018-04-10 20:09:49', 1), (276, '192.168.1.100', '2018-04-10 20:15:48', 12),
  (277, '192.168.1.100', '2018-04-10 20:16:07', 15), (278, '192.168.1.100', '2018-04-10 20:16:29', 1),
  (279, '192.168.1.100', '2018-04-10 20:45:53', 12), (280, '192.168.1.100', '2018-04-10 20:49:24', 12),
  (281, '192.168.1.100', '2018-04-10 20:50:57', 15), (282, '192.168.1.100', '2018-04-10 20:55:45', 1),
  (283, '192.168.1.100', '2018-04-10 20:58:50', 15), (284, '192.168.1.100', '2018-04-10 21:49:39', 15),
  (285, '192.168.1.100', '2018-04-11 10:36:52', 12), (286, '192.168.1.100', '2018-04-11 10:52:40', 12),
  (287, '192.168.1.100', '2018-04-11 11:06:40', 12), (288, '192.168.1.100', '2018-04-11 11:07:47', 15),
  (289, '192.168.1.100', '2018-04-11 11:08:52', 16), (290, '192.168.1.100', '2018-04-11 11:14:20', 12),
  (291, '192.168.1.100', '2018-04-11 11:54:15', 12), (292, '192.168.1.100', '2018-04-11 11:58:39', 15),
  (293, '192.168.1.100', '2018-04-11 12:02:17', 1), (294, '192.168.1.100', '2018-04-11 12:04:15', 15),
  (295, '192.168.1.100', '2018-04-11 12:04:35', 16), (296, '192.168.1.100', '2018-04-11 12:48:09', 12),
  (297, '192.168.1.100', '2018-04-11 12:49:42', 15), (298, '192.168.1.100', '2018-04-11 13:07:52', 15),
  (299, '192.168.1.100', '2018-04-11 13:39:50', 15), (300, '192.168.1.100', '2018-04-11 13:40:58', 1),
  (301, '192.168.1.100', '2018-04-11 13:48:01', 15), (302, '0:0:0:0:0:0:0:1', '2018-04-11 13:56:46', 1),
  (303, '192.168.1.100', '2018-04-11 14:50:30', 12), (304, '192.168.1.100', '2018-04-11 17:52:42', 12),
  (305, '192.168.1.100', '2018-04-11 17:58:16', 12), (306, '192.168.1.100', '2018-04-11 17:58:37', 1),
  (307, '192.168.1.100', '2018-04-11 18:02:11', 15), (308, '192.168.1.100', '2018-04-11 18:07:10', 12),
  (309, '192.168.1.100', '2018-04-11 18:07:23', 15), (310, '192.168.1.100', '2018-04-11 18:13:50', 12),
  (311, '192.168.1.100', '2018-04-11 18:17:39', 12), (312, '192.168.1.100', '2018-04-11 18:19:56', 15),
  (313, '192.168.1.100', '2018-04-11 18:27:03', 1), (314, '192.168.1.100', '2018-04-11 18:46:06', 12),
  (315, '192.168.1.100', '2018-04-11 18:46:55', 1), (316, '192.168.1.100', '2018-04-11 19:00:52', 1),
  (317, '0:0:0:0:0:0:0:1', '2018-04-11 19:07:52', 12), (318, '192.168.1.100', '2018-04-11 19:34:55', 12),
  (319, '0:0:0:0:0:0:0:1', '2018-04-11 19:41:36', 15), (320, '192.168.1.100', '2018-04-11 20:07:06', 15),
  (321, '192.168.1.100', '2018-04-11 20:15:52', 12), (322, '192.168.1.100', '2018-04-11 20:16:11', 1),
  (323, '192.168.1.100', '2018-04-11 20:21:11', 12), (324, '192.168.1.100', '2018-04-11 20:24:49', 15),
  (325, '192.168.1.100', '2018-04-11 20:32:57', 1), (326, '192.168.1.101', '2018-04-12 12:15:19', 12),
  (327, '192.168.1.101', '2018-04-12 12:15:33', 15), (328, '192.168.1.101', '2018-04-12 12:15:48', 1),
  (329, '192.168.1.101', '2018-04-12 12:34:02', 12), (330, '192.168.1.101', '2018-04-12 12:35:43', 12),
  (331, '192.168.1.101', '2018-04-12 12:37:38', 15), (332, '192.168.1.101', '2018-04-12 12:44:28', 1),
  (333, '192.168.1.101', '2018-04-12 15:18:58', 12), (334, '192.168.1.101', '2018-04-12 15:21:52', 15),
  (335, '192.168.1.101', '2018-04-12 15:27:50', 1), (336, '192.168.1.101', '2018-04-12 16:25:31', 15),
  (337, '192.168.1.101', '2018-04-12 16:25:55', 1), (338, '192.168.1.100', '2018-04-12 16:41:32', 15),
  (339, '192.168.1.100', '2018-04-12 16:45:12', 15), (340, '192.168.1.100', '2018-04-12 16:46:02', 12),
  (341, '192.168.1.100', '2018-04-12 16:52:44', 15), (342, '192.168.1.100', '2018-04-12 18:02:55', 15),
  (343, '192.168.1.100', '2018-04-12 18:18:57', 12), (344, '192.168.1.100', '2018-04-12 18:45:48', 12);
/*!40000 ALTER TABLE `t_user_login_log`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_role`
--

DROP TABLE IF EXISTS `t_user_role`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user_role` (
  `id`      INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `role_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `t_user_role_t_user_id_fk` (`user_id`),
  KEY `t_user_role_t_role_id_fk` (`role_id`),
  CONSTRAINT `t_user_role_t_role_id_fk` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `t_user_role_t_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 24
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_role`
--

LOCK TABLES `t_user_role` WRITE;
/*!40000 ALTER TABLE `t_user_role`
  DISABLE KEYS */;
INSERT INTO `t_user_role`
VALUES (2, 12, 1), (4, 15, 1), (5, 1, 1), (6, 1, 2), (7, 1, 3), (8, 1, 4), (9, 1, 5), (12, 15, 201), (13, 12, 101),
  (14, 16, 1), (16, 16, 201), (17, 17, 1), (18, 17, 100), (19, 18, 1), (20, 18, 201), (21, 1, 6), (22, 1, 7),
  (23, 1, 8);
/*!40000 ALTER TABLE `t_user_role`
  ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2018-04-12 19:31:34
