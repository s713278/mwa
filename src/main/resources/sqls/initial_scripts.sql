CREATE DATABASE  IF NOT EXISTS `mwa_dev` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mwa_dev`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: mwa_dev
-- ------------------------------------------------------
-- Server version	5.7.18-log

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
-- Table structure for table `category_details`
--

DROP TABLE IF EXISTS `category_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `description` varchar(255) DEFAULT NULL,
  `last_update` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_details`
--

LOCK TABLES `category_details` WRITE;
/*!40000 ALTER TABLE `category_details` DISABLE KEYS */;
INSERT INTO `category_details` VALUES (1,'INDEPENDENT','2017-12-03 22:09:45',NULL,'2017-12-03 22:09:45','Individual Plot'),(2,'APARTMENT','2017-12-03 22:09:45',NULL,'2017-12-03 22:09:45','Apartment Flat'),(3,'COMMERCIAL','2017-12-03 22:09:45',NULL,'2017-12-03 22:09:45','Commercial shops');
/*!40000 ALTER TABLE `category_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fee_details`
--

DROP TABLE IF EXISTS `fee_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fee_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_active` bit(1) DEFAULT b'1',
  `amount` double DEFAULT NULL,
  `bank_details` varchar(255) DEFAULT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `description` varchar(255) DEFAULT NULL,
  `expire_date` datetime DEFAULT NULL,
  `fund_name` varchar(255) NOT NULL,
  `fund_type` varchar(255) DEFAULT NULL,
  `last_update` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `start_date` datetime DEFAULT NULL,
  `terms_and_conditaions` varchar(255) DEFAULT NULL,
  `is_expired` bit(1) DEFAULT NULL,
  `fee_detailscol` varchar(45) DEFAULT '1',
  `category_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbjuwmsboiywgieaawh6a9ko3j` (`category_id`),
  CONSTRAINT `FKbjuwmsboiywgieaawh6a9ko3j` FOREIGN KEY (`category_id`) REFERENCES `category_details` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10009 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fee_details`
--

LOCK TABLES `fee_details` WRITE;
/*!40000 ALTER TABLE `fee_details` DISABLE KEYS */;
INSERT INTO `fee_details` VALUES (10001,'\0',1000,NULL,'2017-12-02 20:19:27','CC Camers fund for each independent plot','2016-03-03 00:00:00','Independent plot\'s CCCamera\'s fee','ONE_TIME','2017-12-02 20:19:27','2015-04-01 00:00:00',NULL,'\0','1',2),(10002,'\0',500,NULL,'2017-12-02 21:11:59','CC Camers fund for each apartment/flat plot',NULL,'Apartment/Flat CCCamer\'s fee','ONE_TIME','2017-12-02 21:11:59',NULL,NULL,'\0','1',1),(10003,'\0',500,NULL,'2017-12-02 21:14:22','Individual Annual Member Ship Fee','2016-03-31 00:00:00','Independent house membership fee','YEARLY','2017-12-02 21:14:22','2015-04-01 00:00:00',NULL,'\0','1',1),(10004,'\0',400,NULL,'2017-12-02 21:24:53','Apartment\'s annual membership fee','2016-03-31 00:00:00','Apartment\'s annual membership fee','YEARLY','2017-12-02 21:24:53','2015-04-01 00:00:00',NULL,'\0','1',NULL),(10005,'\0',500,NULL,'2017-12-02 21:14:22','Individual Annual Member Ship Fee','2017-03-31 00:00:00','Independent house membership fee','YEARLY','2017-12-02 21:14:22','2016-04-01 00:00:00',NULL,'\0','1',1),(10006,'\0',400,NULL,'2017-12-02 21:24:53','Apartment\'s annual membership fee','2017-03-31 00:00:00','Apartment\'s annual membership fee','YEARLY','2017-12-02 21:24:53','2016-04-01 00:00:00',NULL,'\0','1',NULL),(10007,'\0',500,NULL,'2017-12-02 21:14:22','Individual Annual Member Ship Fee','2018-03-31 00:00:00','Independent house membership fee','YEARLY','2017-12-02 21:14:22','2017-04-01 00:00:00',NULL,'\0','1',1),(10008,'\0',400,NULL,'2017-12-02 21:24:53','Apartment\'s annual membership fee','2018-03-31 00:00:00','Apartment\'s annual membership fee','YEARLY','2017-12-02 21:24:53','2017-04-01 00:00:00',NULL,'\0','1',NULL);
/*!40000 ALTER TABLE `fee_details` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-11 22:05:43
