CREATE DATABASE  IF NOT EXISTS `wichf` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `wichf`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: wichf
-- ------------------------------------------------------
-- Server version	5.1.72-community

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
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appointment` (
  `appointment_id` int(11) NOT NULL AUTO_INCREMENT,
  `time_slot` int(11) DEFAULT NULL,
  `booked_through` varchar(45) DEFAULT NULL,
  `appointment_date` date DEFAULT NULL,
  `booked_date` date DEFAULT NULL,
  `doctor_id` int(11) DEFAULT NULL,
  `room_id` int(11) DEFAULT NULL,
  `parent_appointment_id` int(11) DEFAULT NULL,
  `user_id` varchar(10) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `visit_type_id` int(11) DEFAULT NULL,
  `active` int(11) DEFAULT '0',
  PRIMARY KEY (`appointment_id`),
  KEY `fk_Appointment_Doctor1_idx` (`doctor_id`),
  KEY `fk_Appointment_Room1_idx` (`room_id`),
  KEY `fk_Appointment_Appointment1_idx` (`parent_appointment_id`),
  KEY `fk_Appointment_User1_idx` (`user_id`),
  KEY `fk_Appointment_Visit_Type1_idx` (`visit_type_id`),
  CONSTRAINT `fk_Appointment_Appointment1` FOREIGN KEY (`parent_appointment_id`) REFERENCES `appointment` (`appointment_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Appointment_Doctor1` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`doctor_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Appointment_Room1` FOREIGN KEY (`room_id`) REFERENCES `room` (`room_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Appointment_User1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Appointment_Visit_Type1` FOREIGN KEY (`visit_type_id`) REFERENCES `visit_type` (`visit_type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment`
--

LOCK TABLES `appointment` WRITE;
/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;
INSERT INTO `appointment` VALUES (1,1,'WEB','2014-03-03','2014-03-03',1,1,NULL,'swapnil','2014-03-03 09:20:00',1,0),(2,1,'WEB','2014-03-03','2014-03-03',2,2,NULL,'swapnil','2014-03-03 09:20:00',1,0),(3,2,'WEB','2014-03-03','2014-03-03',3,3,NULL,'swapnil','2014-03-03 09:00:00',1,0),(4,1,'WEB','2014-03-03','2014-03-03',4,4,NULL,'swapnil','2014-03-03 09:20:00',1,0),(5,1,'WEB','2014-03-03','2014-03-03',5,5,NULL,'swapnil','2014-03-03 09:20:00',1,0),(25,20,'Nurse',NULL,NULL,1,2,NULL,'swapnil','2014-03-03 09:40:00',1,0),(26,20,'Nurse',NULL,NULL,1,2,NULL,'swapnil','2014-03-03 09:40:00',1,0),(27,20,'Nurse',NULL,NULL,1,2,NULL,'swapnil','2014-03-03 09:40:00',1,0),(28,20,'Nurse',NULL,NULL,1,2,NULL,'swapnil','2014-03-03 09:40:00',1,0),(29,20,'patient','2014-03-03',NULL,3,3,NULL,'asda','2014-03-03 00:00:00',1,0),(30,20,'patient','2014-03-03',NULL,3,3,NULL,'asda','2014-03-03 00:00:00',1,0),(31,20,'patient','2014-03-03',NULL,1,1,NULL,'asda','2014-03-03 09:40:00',1,0),(32,60,'patient','2014-03-03','2014-03-03',1,1,NULL,'asda','2014-03-03 11:00:00',2,0);
/*!40000 ALTER TABLE `appointment` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-03-03 14:05:29
