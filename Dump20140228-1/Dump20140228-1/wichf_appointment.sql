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
  `active` tinyint(1) DEFAULT NULL,
  `appointment_date` date DEFAULT NULL,
  `booked_date` date DEFAULT NULL,
  `doctor_id` int(11) DEFAULT NULL,
  `room_id` int(11) DEFAULT NULL,
  `parent_appointment_id` int(11) DEFAULT NULL,
  `user_id` varchar(10) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `visit_type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`appointment_id`),
  KEY `fk_Appointment_Doctor1_idx` (`doctor_id`),
  KEY `fk_Appointment_Room1_idx` (`room_id`),
  KEY `fk_Appointment_Appointment1_idx` (`parent_appointment_id`),
  KEY `fk_Appointment_User1_idx` (`user_id`),
  KEY `fk_Appointment_Visit_Type1_idx` (`visit_type_id`),
  CONSTRAINT `fk_Appointment_Doctor1` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`doctor_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Appointment_Room1` FOREIGN KEY (`room_id`) REFERENCES `room` (`room_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Appointment_Appointment1` FOREIGN KEY (`parent_appointment_id`) REFERENCES `appointment` (`appointment_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Appointment_User1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Appointment_Visit_Type1` FOREIGN KEY (`visit_type_id`) REFERENCES `visit_type` (`visit_type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment`
--

LOCK TABLES `appointment` WRITE;
/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;
INSERT INTO `appointment` VALUES (1,0,'0',0,'2000-01-01','2000-01-01',1,1,1,'sahil','2014-01-22 12:00:00',1),(2,1,'web',1,'2014-03-01','2014-02-28',1,2,1,'sahil','2014-03-01 13:20:00',1);
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

-- Dump completed on 2014-02-28 23:55:09
