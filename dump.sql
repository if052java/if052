-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: water
-- ------------------------------------------------------
-- Server version	5.6.17

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
-- Table structure for table `adress`
--
CREATE DATABASE water;
USE water;

DROP TABLE IF EXISTS `adress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `adress` (
  `adress_id` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(45) NOT NULL,
  `street` varchar(45) NOT NULL,
  `building` varchar(45) NOT NULL,
  `appartment` varchar(45) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`adress_id`),
  KEY `fk_adress_user_idx` (`user_id`),
  CONSTRAINT `fk_adress_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adress`
--

LOCK TABLES `adress` WRITE;
/*!40000 ALTER TABLE `adress` DISABLE KEYS */;
INSERT INTO `adress` VALUES (1,'CITY1','STREET1','BUILDING1','APPARTMENT1',1),(2,'CITY2','STREET2','BUILDING2','APPARTMENT2',1),(3,'CITY3','STREET3','BUILDING3','APPARTMENT3',1),(4,'CITY4','STREET4','BUILDING4','APPARTMENT4',2),(5,'CITY5','STREET5','BUILDING5','APPARTMENT5',2),(6,'CITY6','STREET6','BUILDING6','APPARTMENT6',2),(7,'CITY7','STREET7','BUILDING7','APPARTMENT7',3),(8,'CITY8','STREET8','BUILDING8','APPARTMENT8',3);
/*!40000 ALTER TABLE `adress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `indicator`
--

DROP TABLE IF EXISTS `indicator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `indicator` (
  `indicator_id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `value` int(11) NOT NULL,
  `is_paid` bit(1) NOT NULL DEFAULT b'0',
  `is_published` bit(1) NOT NULL DEFAULT b'0',
  `water_meter_id` int(11) NOT NULL,
  PRIMARY KEY (`indicator_id`),
  KEY `fk_indicator_water_meter_idx` (`water_meter_id`),
  CONSTRAINT `fk_indicator_water_meter` FOREIGN KEY (`water_meter_id`) REFERENCES `watermeter` (`water_meter_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=303 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `indicator`
--

LOCK TABLES `indicator` WRITE;
/*!40000 ALTER TABLE `indicator` DISABLE KEYS */;
INSERT INTO `indicator` VALUES (1,'2015-01-01 00:00:00',101,'','',1),(2,'2015-02-02 18:02:09',1234,'','\0',1),(3,'2015-01-02 00:00:00',101,'','',2),(4,'2015-01-03 00:00:00',101,'','',3),(5,'2015-01-04 00:00:00',101,'','',4),(6,'2015-01-05 00:00:00',101,'','',5),(7,'2015-01-06 00:00:00',101,'','',6),(11,'2015-01-10 00:00:00',101,'','',10),(12,'2015-01-11 00:00:00',101,'','',11),(13,'2015-01-12 00:00:00',101,'','',12),(14,'2015-01-13 00:00:00',101,'','',13),(15,'2015-01-14 00:00:00',101,'','',14),(16,'2015-01-01 00:00:00',101,'','',15),(17,'2015-01-01 00:00:00',101,'','',16),(18,'2015-01-15 00:00:00',101,'','',17),(19,'2015-01-14 00:00:00',101,'','',18),(20,'2015-01-13 00:00:00',101,'','',19),(21,'2015-01-12 00:00:00',101,'','',20),(22,'2015-01-11 00:00:00',101,'','',21),(23,'2015-01-01 00:00:00',101,'','',22),(24,'2015-01-10 00:00:00',101,'','',23),(25,'2015-01-09 00:00:00',101,'','',24),(26,'2015-01-08 00:00:00',101,'','',25),(27,'2015-01-13 00:00:00',101,'','',24),(28,'2015-01-12 00:00:00',101,'','',23),(29,'2015-01-11 00:00:00',101,'','',22),(30,'2015-01-01 00:00:00',101,'','',22),(31,'2015-01-10 00:00:00',101,'','',22),(32,'2015-01-09 00:00:00',101,'','',21),(33,'2015-01-08 00:00:00',101,'','',20),(34,'2015-01-13 00:00:00',101,'','',19),(35,'2015-01-12 00:00:00',101,'','',11),(36,'2015-01-11 00:00:00',101,'','',12),(37,'2015-01-01 00:00:00',101,'','',13),(38,'2015-01-10 00:00:00',101,'','',14),(39,'2015-01-09 00:00:00',101,'','',15),(40,'2015-01-08 00:00:00',101,'','',16),(41,'2015-01-29 00:00:00',1000,'','\0',1),(42,'2015-01-29 00:00:00',1000,'','\0',1),(43,'2015-01-29 00:00:00',1000,'','\0',1),(300,'2015-01-30 00:00:00',1000,'','\0',2),(301,'2015-01-30 00:00:00',1000,'','\0',2),(302,'2015-01-30 16:34:34',1000,'','\0',2);
/*!40000 ALTER TABLE `indicator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `middle_name` varchar(45) DEFAULT NULL,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `tariff` float DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Name1','SURNAME1','MIDDLENAME1','LOGIN1','PASS1',0.1),(2,'Name2','SURNAME2','MIDDLENAME2','LOGIN2','PASS2',0.2),(3,'Name3','SURNAME3','MIDDLENAME3','LOGIN3','PASS3',0.3);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `watermeter`
--

DROP TABLE IF EXISTS `watermeter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `watermeter` (
  `water_meter_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  `adress_id` int(11) NOT NULL,
  PRIMARY KEY (`water_meter_id`),
  KEY `fk_WaterMeter_Adress1_idx` (`adress_id`),
  CONSTRAINT `fk_water_meter_adress` FOREIGN KEY (`adress_id`) REFERENCES `adress` (`adress_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `watermeter`
--

LOCK TABLES `watermeter` WRITE;
/*!40000 ALTER TABLE `watermeter` DISABLE KEYS */;
INSERT INTO `watermeter` VALUES (1,'NAME1','DESCRIPTION1',1),(2,'NAME2','DESCRIPTION2',1),(3,'NAME3','DESCRIPTION3',1),(4,'NAME4','DESCRIPTION4',2),(5,'NAME5','DESCRIPTION5',2),(6,'NAME6','DESCRIPTION6',3),(7,'NAME7','DESCRIPTION7',3),(8,'NAME8','DESCRIPTION8',4),(9,'NAME9','DESCRIPTION9',4),(10,'NAME10','DESCRIPTION10',5),(11,'NAME11','DESCRIPTION11',5),(12,'NAME12','DESCRIPTION12',6),(13,'NAME13','DESCRIPTION13',6),(14,'NAME14','DESCRIPTION14',7),(15,'NAME15','DESCRIPTION15',7),(16,'NAME16','DESCRIPTION16',8),(17,'NAME17','DESCRIPTION17',8),(18,'NAME18','DESCRIPTION18',8),(19,'NAME19','DESCRIPTION19',7),(20,'NAME20','DESCRIPTION20',6),(21,'NAME21','DESCRIPTION21',5),(22,'NAME22','DESCRIPTION22',4),(23,'NAME23','DESCRIPTION23',3),(24,'NAME24','DESCRIPTION24',2),(25,'NAME25','DESCRIPTION25',1);
/*!40000 ALTER TABLE `watermeter` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-02-02 19:36:19
