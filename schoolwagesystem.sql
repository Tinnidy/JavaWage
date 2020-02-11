-- MySQL dump 10.13  Distrib 5.7.21, for Win64 (x86_64)
--
-- Host: localhost    Database: schoolwagesystem
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `award`
--

DROP TABLE IF EXISTS `award`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `award` (
  `Award_ID` int(10) NOT NULL AUTO_INCREMENT,
  `Worker_ID` varchar(15) NOT NULL,
  `Award_numm` float NOT NULL,
  `Deduction_num` float NOT NULL,
  `Award_info` tinytext,
  PRIMARY KEY (`Award_ID`,`Worker_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `award`
--

LOCK TABLES `award` WRITE;
/*!40000 ALTER TABLE `award` DISABLE KEYS */;
INSERT INTO `award` VALUES (11,'001',0,940,'奖金 : 全勤奖金  扣款：deduction = Absent_days * 200 + Leave_days * 100 + Late_days * 20 + Leave_early_days * 20'),(12,'002',0,800,'奖金 : 全勤奖金  扣款：deduction = Absent_days * 200 + Leave_days * 100 + Late_days * 20 + Leave_early_days * 20');
/*!40000 ALTER TABLE `award` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bank_info`
--

DROP TABLE IF EXISTS `bank_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bank_info` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `Worker_ID` varchar(10) NOT NULL,
  `Time` time NOT NULL,
  `Base_pay` float NOT NULL,
  `Reward` float NOT NULL,
  `Deduct_Check` float NOT NULL,
  `Income_tax` float NOT NULL,
  PRIMARY KEY (`ID`,`Worker_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank_info`
--

LOCK TABLES `bank_info` WRITE;
/*!40000 ALTER TABLE `bank_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `bank_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `bank_info_view`
--

DROP TABLE IF EXISTS `bank_info_view`;
/*!50001 DROP VIEW IF EXISTS `bank_info_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `bank_info_view` AS SELECT 
 1 AS `ID`,
 1 AS `Worker_name`,
 1 AS `Time`,
 1 AS `Equip_pay`,
 1 AS `ID_number`,
 1 AS `Bank_account`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `checks`
--

DROP TABLE IF EXISTS `checks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `checks` (
  `ID` int(6) NOT NULL AUTO_INCREMENT,
  `Worker_ID` varchar(15) NOT NULL,
  `Time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `Month_days` int(2) NOT NULL,
  `Work_days` int(2) NOT NULL DEFAULT '0',
  `Absent_days` int(2) NOT NULL,
  `Leave_days` int(2) NOT NULL DEFAULT '0',
  `Late_days` int(2) NOT NULL DEFAULT '0',
  `Leave_early_days` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`,`Worker_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checks`
--

LOCK TABLES `checks` WRITE;
/*!40000 ALTER TABLE `checks` DISABLE KEYS */;
INSERT INTO `checks` VALUES (8,'001','2019-05-16 02:34:30',31,0,4,1,1,1),(9,'002','2019-05-05 01:42:51',31,0,4,0,0,0),(10,'001','2019-06-01 00:32:06',30,0,0,0,0,0),(11,'002','2019-06-01 00:32:06',30,0,0,0,0,0);
/*!40000 ALTER TABLE `checks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `Dept_id` varchar(10) NOT NULL,
  `Dept_name` varchar(15) NOT NULL,
  PRIMARY KEY (`Dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES ('001','人事部'),('002','财务处');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payroll`
--

DROP TABLE IF EXISTS `payroll`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payroll` (
  `ID` int(6) NOT NULL AUTO_INCREMENT,
  `Worker_ID` varchar(15) NOT NULL,
  `Time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `Base_pay` float NOT NULL,
  `Award_ID` int(10) NOT NULL,
  `Income_tax` float DEFAULT NULL,
  `Equip_pay` float DEFAULT NULL,
  PRIMARY KEY (`ID`,`Award_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payroll`
--

LOCK TABLES `payroll` WRITE;
/*!40000 ALTER TABLE `payroll` DISABLE KEYS */;
INSERT INTO `payroll` VALUES (11,'001','2019-05-31 03:36:52',6000,11,0,5060),(12,'002','2019-05-31 04:18:58',6000,12,0,5200);
/*!40000 ALTER TABLE `payroll` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `payroll_award_view`
--

DROP TABLE IF EXISTS `payroll_award_view`;
/*!50001 DROP VIEW IF EXISTS `payroll_award_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `payroll_award_view` AS SELECT 
 1 AS `ID`,
 1 AS `Worker_ID`,
 1 AS `Worker_name`,
 1 AS `Time`,
 1 AS `Base_pay`,
 1 AS `Award_numm`,
 1 AS `Deduction_num`,
 1 AS `Income_tax`,
 1 AS `Equip_pay`,
 1 AS `Award_info`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `worker`
--

DROP TABLE IF EXISTS `worker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `worker` (
  `Worker_ID` varchar(15) NOT NULL,
  `Worker_name` varchar(15) NOT NULL,
  `Sex` varchar(2) NOT NULL,
  `Department_ID` varchar(15) NOT NULL,
  `ID_number` varchar(18) NOT NULL,
  `Identity` varchar(10) NOT NULL,
  `Bank_account` varchar(19) NOT NULL,
  `Pwd` varchar(15) NOT NULL,
  `Mail` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Worker_ID`,`Department_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `worker`
--

LOCK TABLES `worker` WRITE;
/*!40000 ALTER TABLE `worker` DISABLE KEYS */;
INSERT INTO `worker` VALUES ('001','abc','男','001','440982199609254091','管理员','6228481426696628672','123456','2492736825@qq.com'),('002','aaa','男','001','440982199609254091','用户','6228481426696628672','123456','2492736825@qq.com');
/*!40000 ALTER TABLE `worker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'schoolwagesystem'
--

--
-- Dumping routines for database 'schoolwagesystem'
--

--
-- Final view structure for view `bank_info_view`
--

/*!50001 DROP VIEW IF EXISTS `bank_info_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `bank_info_view` AS select `payroll`.`ID` AS `ID`,`worker`.`Worker_name` AS `Worker_name`,`payroll`.`Time` AS `Time`,`payroll`.`Equip_pay` AS `Equip_pay`,`worker`.`ID_number` AS `ID_number`,`worker`.`Bank_account` AS `Bank_account` from (`payroll` join `worker`) where (`payroll`.`Worker_ID` = `worker`.`Worker_ID`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `payroll_award_view`
--

/*!50001 DROP VIEW IF EXISTS `payroll_award_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `payroll_award_view` AS select `payroll`.`ID` AS `ID`,`worker`.`Worker_ID` AS `Worker_ID`,`worker`.`Worker_name` AS `Worker_name`,`payroll`.`Time` AS `Time`,`payroll`.`Base_pay` AS `Base_pay`,`award`.`Award_numm` AS `Award_numm`,`award`.`Deduction_num` AS `Deduction_num`,`payroll`.`Income_tax` AS `Income_tax`,`payroll`.`Equip_pay` AS `Equip_pay`,`award`.`Award_info` AS `Award_info` from ((`payroll` join `worker`) join `award`) where ((`payroll`.`Worker_ID` = `worker`.`Worker_ID`) and (`payroll`.`Worker_ID` = `award`.`Worker_ID`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-05 21:20:01
