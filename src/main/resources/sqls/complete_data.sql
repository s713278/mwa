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
-- Table structure for table `apartment_details`
--

DROP TABLE IF EXISTS `apartment_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `apartment_details` (
  `aprtment_name` varchar(255) DEFAULT NULL,
  `presedent_first_name` varchar(255) DEFAULT NULL,
  `presedent_last_name` varchar(255) DEFAULT NULL,
  `presedent_mobile_no` varchar(255) DEFAULT NULL,
  `secretery_first_name` varchar(255) DEFAULT NULL,
  `secretery_last_name` varchar(255) DEFAULT NULL,
  `secretery_mobile_no` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKqyg56ahl1n4cwrxe65t7antvt` FOREIGN KEY (`id`) REFERENCES `member_details` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apartment_details`
--

LOCK TABLES `apartment_details` WRITE;
/*!40000 ALTER TABLE `apartment_details` DISABLE KEYS */;
INSERT INTO `apartment_details` VALUES ('APARTMENT','FIRSTNAME','LASTNAME','9052252416',NULL,NULL,NULL,32825),('APARTMENT','FIRSTNAME','LASTNAME','8074155417',NULL,NULL,NULL,32826),('APARTMENT','FIRSTNAME','LASTNAME','9701967796',NULL,NULL,NULL,32827),('APARTMENT','FIRSTNAME','LASTNAME','9989600004',NULL,NULL,NULL,32828),('APARTMENT','FIRSTNAME','LASTNAME','9912324132',NULL,NULL,NULL,32829),('APARTMENT','FIRSTNAME','LASTNAME','9908909875',NULL,NULL,NULL,32830),('APARTMENT','FIRSTNAME','LASTNAME','9985459934',NULL,NULL,NULL,32831),('APARTMENT','FIRSTNAME','LASTNAME','9985465033',NULL,NULL,NULL,32832),('APARTMENT','FIRSTNAME','LASTNAME','9550250931',NULL,NULL,NULL,32833),('APARTMENT','FIRSTNAME','LASTNAME','9542733244',NULL,NULL,NULL,32834),('APARTMENT','FIRSTNAME','LASTNAME','8897222886',NULL,NULL,NULL,32835),('APARTMENT','FIRSTNAME','LASTNAME','8499994624',NULL,NULL,NULL,32836),('APARTMENT','FIRSTNAME','LASTNAME','8008555034',NULL,NULL,NULL,32837),('APARTMENT','FIRSTNAME','LASTNAME','9951094365',NULL,NULL,NULL,32838);
/*!40000 ALTER TABLE `apartment_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cash_payment_details`
--

DROP TABLE IF EXISTS `cash_payment_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cash_payment_details` (
  `collected_by` varchar(255) DEFAULT NULL,
  `mobile_no` varchar(255) DEFAULT NULL,
  `paid_by` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKh0vd6fjxgxhn0cj03xokfsew3` FOREIGN KEY (`id`) REFERENCES `payment_details` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cash_payment_details`
--

LOCK TABLES `cash_payment_details` WRITE;
/*!40000 ALTER TABLE `cash_payment_details` DISABLE KEYS */;
INSERT INTO `cash_payment_details` VALUES ('FEED','9052252416','Sai Metro Apartment',2),('FEED','8074155417','Saty Ratna Residency',3),('FEED','9989600004','Mayuri Hills',4),('FEED','9912324132','Lakshmi Classic',5),('FEED','9908909875','Srikara Residency',6),('FEED','9985459934','Sri Venkata Sai Brundavanam',7),('FEED','9985465033','Sai Enclave',8),('FEED','9550250931','Laasya Residency',9),('FEED','9542733244','SVS Sapphire',10),('FEED','8897222886','Sun Shine Residency',11),('FEED','8499994624','Ajay Complex',12),('FEED','8008555034','Prajapathi Elite -3',13),('FEED','9951094365','Subbiah park view Apt',14),('FEED','9494424648','Padma Hospital',15),('FEED','0000000000','Tejaswini General Stores',16),('FEED','9491956059','Shankar Rao CH',19),('FEED','9030002045','M Kishore Kumar',20),('FEED','9908439998','B Nageswara Rao',21),('FEED','9989832280','CH Veera Babu',22),('FEED','9440388040','Phani Kumar Raju',23),('FEED','7093321205','E Srinivas Reddy',24),('FEED','9441597254','P Tejo Lakshmi Tulasi',25),('FEED','9440276791','B Satyanarayana',26),('FEED','9866508190','Baskar Reddy',27),('FEED','9494494748','N Subramanyam',28),('FEED','9392961999','Vidya Sagar',29),('FEED','9849382259','Ranga Rao',30),('FEED','9292804999','A Ranga Raju',31),('FEED','9849318092','G Amara Jyothi',32),('FEED','9848737319','A Rama Krishna',33),('FEED','9866265492','K Chandrayudu',34),('FEED','0000000000','Ramesh N B N',35),('FEED','9000720567','G Sunil Kumar',36),('FEED','9493327254','S OM Prakash',37),('FEED','9441215217','E Shilpa',38),('FEED','8106390387','B Sudheer Kumar',39),('FEED','9848033202','M Raja Gopal',40),('FEED','8886114274','Chandrasekhar',41),('FEED','9885442202','G Sita Ram',42),('FEED','9959985194','T Wilson',43),('FEED','0000000000','D Narasimha Rao',44),('FEED','0000000000','GRK Reddy',45),('FEED','8106018356','A sivaji',46),('FEED','8008122348','M Surya Prakash',47),('FEED','9666234234','V Srinivasa Raju',48),('FEED','7702103419','Narasimha Rao',49),('FEED','8886772225','S Venkataramana',50),('FEED','8897507591','Raj Kumar',51),('FEED','9849377781','Nadikudi Balanna',52),('FEED','9440052890','Laxmi Narayana',53),('FEED','9949785678','N Narayana Rao',54),('FEED','9441803778','M Upender',55),('FEED','8106645529','RR Nayak',56),('FEED','8686635358','M V Shiva Reddy',57),('FEED','0000000000','Satyanarayana',58),('FEED','9177323292','Roja Rani',59),('FEED','8019188708','Krishna',60),('FEED','9849500300','N Prasadu',61),('FEED','9502162471','Ramachandran',62),('FEED','9440442456','K V Sashayah',63),('FEED','7207634600','I Latha',64),('FEED','7207634600','I Latha',65),('FEED','9676643131','Ramesh Babu',66),('FEED','9666234234','Raju Garu',67);
/*!40000 ALTER TABLE `cash_payment_details` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Table structure for table `cheque_payment_details`
--

DROP TABLE IF EXISTS `cheque_payment_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cheque_payment_details` (
  `bank_name` varchar(255) DEFAULT NULL,
  `cheque_no` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKh3q4xv7dtyh4niny8afbrn5bv` FOREIGN KEY (`id`) REFERENCES `payment_details` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cheque_payment_details`
--

LOCK TABLES `cheque_payment_details` WRITE;
/*!40000 ALTER TABLE `cheque_payment_details` DISABLE KEYS */;
INSERT INTO `cheque_payment_details` VALUES (NULL,'AB - 195',NULL,1),(NULL,'HDFC -55',NULL,17),(NULL,'357532',NULL,18);
/*!40000 ALTER TABLE `cheque_payment_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commercial_details`
--

DROP TABLE IF EXISTS `commercial_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `commercial_details` (
  `business_logo_path` varchar(255) DEFAULT NULL,
  `business_name` varchar(255) DEFAULT NULL,
  `type_of_business` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKtaygq8t6x9oidrlqb17u85uk7` FOREIGN KEY (`id`) REFERENCES `member_details` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commercial_details`
--

LOCK TABLES `commercial_details` WRITE;
/*!40000 ALTER TABLE `commercial_details` DISABLE KEYS */;
INSERT INTO `commercial_details` VALUES (NULL,'FIRSTNAME',NULL,32824);
/*!40000 ALTER TABLE `commercial_details` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=10010 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fee_details`
--

LOCK TABLES `fee_details` WRITE;
/*!40000 ALTER TABLE `fee_details` DISABLE KEYS */;
INSERT INTO `fee_details` VALUES (10001,'\0',1000,NULL,'2017-12-02 20:19:27','CC Camers fund for each independent plot','2016-03-03 00:00:00','Independent plot\'s CCCamera\'s fee','ONE_TIME','2017-12-02 20:19:27','2015-04-01 00:00:00',NULL,'\0','1',2),(10002,'\0',500,NULL,'2017-12-02 21:11:59','CC Camers fund for each apartment/flat plot',NULL,'Apartment/Flat CCCamer\'s fee','ONE_TIME','2017-12-02 21:11:59',NULL,NULL,'\0','1',1),(10003,'\0',500,NULL,'2017-12-02 21:14:22','Individual Annual Member Ship Fee','2016-03-31 00:00:00','Independent house membership fee','YEARLY','2017-12-02 21:14:22','2015-04-01 00:00:00',NULL,'\0','1',1),(10004,'\0',400,NULL,'2017-12-02 21:24:53','Apartment\'s annual membership fee','2016-03-31 00:00:00','Apartment\'s annual membership fee','YEARLY','2017-12-02 21:24:53','2015-04-01 00:00:00',NULL,'\0','1',NULL),(10005,'\0',500,NULL,'2017-12-02 21:14:22','Individual Annual Member Ship Fee','2017-03-31 00:00:00','Independent house membership fee','YEARLY','2017-12-02 21:14:22','2016-04-01 00:00:00',NULL,'\0','1',1),(10006,'\0',400,NULL,'2017-12-02 21:24:53','Apartment\'s annual membership fee','2017-03-31 00:00:00','Apartment\'s annual membership fee','YEARLY','2017-12-02 21:24:53','2016-04-01 00:00:00',NULL,'\0','1',NULL),(10007,'\0',500,NULL,'2017-12-02 21:14:22','Individual Annual Member Ship Fee','2018-03-31 00:00:00','Independent house membership fee','YEARLY','2017-12-02 21:14:22','2017-04-01 00:00:00',NULL,'\0','1',1),(10008,'\0',400,NULL,'2017-12-02 21:24:53','Apartment\'s annual membership fee','2018-03-31 00:00:00','Apartment\'s annual membership fee','YEARLY','2017-12-02 21:24:53','2017-04-01 00:00:00',NULL,'\0','1',NULL),(10009,'\0',-1,NULL,'2017-12-02 21:24:53','Volunteer Contribution towards CCCameras','2050-03-31 00:00:00','Volunteer Contribution towards CCCameras','ONE_TIME','2018-03-31 00:00:00','2017-04-01 00:00:00',NULL,'\0','1',1);
/*!40000 ALTER TABLE `fee_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) NOT NULL,
  `sequence_next_hi_value` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`sequence_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('member_details',2),('payment_details',1);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_details`
--

DROP TABLE IF EXISTS `member_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_details` (
  `type` varchar(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `is_active` smallint(6) DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `email_id` varchar(255) DEFAULT NULL,
  `last_update` datetime NOT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `mobile_no` varchar(255) DEFAULT NULL,
  `no_of_families` int(11) DEFAULT NULL,
  `owner_first_name` varchar(255) DEFAULT NULL,
  `owner_last_name` varchar(255) DEFAULT NULL,
  `plot_no` varchar(255) NOT NULL,
  `road_no` varchar(255) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl9ecge60ry23mw0oefvue5r8g` (`category_id`),
  CONSTRAINT `FKl9ecge60ry23mw0oefvue5r8g` FOREIGN KEY (`category_id`) REFERENCES `category_details` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_details`
--

LOCK TABLES `member_details` WRITE;
/*!40000 ALTER TABLE `member_details` DISABLE KEYS */;
INSERT INTO `member_details` VALUES ('INDEPENDENT',2,1,'2017-12-11 22:23:41',NULL,'2017-12-11 22:23:41','Rao','9491956059',1,'Shankar','CH','MIG-706','1',1),('INDEPENDENT',3,1,'2017-12-11 22:23:41',NULL,'2017-12-11 22:23:41','Kishore','9030002045',1,'M','Kumar','MIG-560','1',1),('INDEPENDENT',4,1,'2017-12-11 22:23:41',NULL,'2017-12-11 22:23:41','Nageswara','9908439998',1,'B','Rao','LIG-299','1',1),('INDEPENDENT',5,1,'2017-12-11 22:23:41',NULL,'2017-12-11 22:23:41','Veera','9989832280',1,'CH','Babu','MIG-715','1',1),('INDEPENDENT',6,1,'2017-12-11 22:23:41',NULL,'2017-12-11 22:23:41','Kumar','9440388040',1,'Phani','Raju','LIG-397','1',1),('INDEPENDENT',7,1,'2017-12-11 22:23:41',NULL,'2017-12-11 22:23:41','Srinivas','7093321205',1,'E','Reddy','LIG-238/B','1',1),('INDEPENDENT',32773,1,'2017-12-11 22:36:55',NULL,'2017-12-11 22:36:55','Tejo','9441597254',1,'P','Lakshmi','LIG-416','',1),('INDEPENDENT',32774,1,'2017-12-11 22:36:56',NULL,'2017-12-11 22:36:56','','9440276791',1,'B','Satyanarayana','MIG-1154','',1),('INDEPENDENT',32775,1,'2017-12-11 22:36:56',NULL,'2017-12-11 22:36:56','','0000000000',1,'Baskar','Reddy','LIG-414','',1),('INDEPENDENT',32776,1,'2017-12-11 22:36:56',NULL,'2017-12-11 22:36:56','','9494494748',1,'N','Subramanyam','MIG-238','',1),('INDEPENDENT',32777,1,'2017-12-11 22:36:56',NULL,'2017-12-11 22:36:56','','9392961999',1,'Vidya','Sagar','MIG-52','',1),('INDEPENDENT',32778,1,'2017-12-11 22:36:56',NULL,'2017-12-11 22:36:56','','9849382259',1,'Ranga','Rao','LIG-164','',1),('INDEPENDENT',32779,1,'2017-12-11 22:36:56',NULL,'2017-12-11 22:36:56','Ranga','9292804999',1,'A','Raju','MIG-319','',1),('INDEPENDENT',32780,1,'2017-12-11 22:36:56',NULL,'2017-12-11 22:36:56','Amara','9849318092',1,'G','Jyothi','MIG-480','',1),('INDEPENDENT',32781,1,'2017-12-11 22:36:56',NULL,'2017-12-11 22:36:56','Rama','9848737319',1,'A','Krishna','MIG-227','',1),('INDEPENDENT',32782,1,'2017-12-11 22:36:56',NULL,'2017-12-11 22:36:56','','9866265492',1,'K','Chandrayudu','MIG-306','',1),('INDEPENDENT',32783,1,'2017-12-11 22:36:56',NULL,'2017-12-11 22:36:56','N','0000000000',1,'Ramesh','B','MIG-305','',1),('INDEPENDENT',32784,1,'2017-12-11 22:36:56',NULL,'2017-12-11 22:36:56','Sunil','9000720567',1,'G','Kumar','LIG-48','',1),('INDEPENDENT',32785,1,'2017-12-11 22:36:56',NULL,'2017-12-11 22:36:56','OM','9493327254',1,'S','Prakash','MIG-130','',1),('INDEPENDENT',32786,1,'2017-12-11 22:36:56',NULL,'2017-12-11 22:36:56','','9441215217',1,'E','Shilpa','MIG-411','',1),('INDEPENDENT',32787,1,'2017-12-11 22:36:56',NULL,'2017-12-11 22:36:56','Sudheer','8106390387',1,'B','Kumar','MIG-412','',1),('INDEPENDENT',32788,1,'2017-12-11 22:36:56',NULL,'2017-12-11 22:36:56','Raja','9848033202',1,'M','Gopal','MIG-414','',1),('INDEPENDENT',32789,1,'2017-12-11 22:36:56',NULL,'2017-12-11 22:36:56','','8886114274',4,'Chandrasekhar','Chandrasekhar','MIG-201','',1),('INDEPENDENT',32790,1,'2017-12-11 22:36:56',NULL,'2017-12-11 22:36:56','Sita','9885442202',1,'G','Ram','LIG-183','',1),('INDEPENDENT',32791,1,'2017-12-11 22:36:56',NULL,'2017-12-11 22:36:56','','9959985194',1,'T','Wilson','LIG-209','27Q',1),('INDEPENDENT',32792,1,'2017-12-11 22:36:56',NULL,'2017-12-11 22:36:56','Narasimha','0000000000',1,'D','Rao','LIG-212','27Q',1),('INDEPENDENT',32793,1,'2017-12-11 22:36:56',NULL,'2017-12-11 22:36:56','','0000000000',1,'GRK','Reddy','LIG-214','27Q',1),('INDEPENDENT',32794,1,'2017-12-11 22:36:56',NULL,'2017-12-11 22:36:56','','8106018356',1,'A','sivaji','LIG-203','27Q',1),('INDEPENDENT',32795,1,'2017-12-11 22:36:57',NULL,'2017-12-11 22:36:57','Surya','8008122348',1,'M','Prakash','LIG-180','27E',1),('INDEPENDENT',32796,1,'2017-12-11 22:36:57',NULL,'2017-12-11 22:36:57','Srinivasa','9666234234',3,'V','Raju','MIG-973/K','',1),('INDEPENDENT',32797,1,'2017-12-11 22:36:57',NULL,'2017-12-11 22:36:57','','7702103419',3,'Narasimha','Rao','LIG-172','',1),('INDEPENDENT',32798,1,'2017-12-11 22:36:57',NULL,'2017-12-11 22:36:57','','8886772225',4,'S','Venkataramana','LIG-166','27L',1),('INDEPENDENT',32799,1,'2017-12-11 22:36:57',NULL,'2017-12-11 22:36:57','','8897507591',3,'Raj','Kumar','LIG-166/C/1','27L',1),('INDEPENDENT',32800,1,'2017-12-11 22:36:57',NULL,'2017-12-11 22:36:57','','9849377781',2,'Nadikudi','Balanna','LIG-162/F','',1),('INDEPENDENT',32801,1,'2017-12-11 22:36:57',NULL,'2017-12-11 22:36:57','','9440052890',2,'Laxmi','Narayana','LIG-161','',1),('INDEPENDENT',32802,1,'2017-12-11 22:36:57',NULL,'2017-12-11 22:36:57','Narayana','9949785678',1,'N','Rao','MIG-711','',1),('INDEPENDENT',32803,1,'2017-12-11 22:36:57',NULL,'2017-12-11 22:36:57','','9441803778',2,'M','Upender','MIG-718','',1),('INDEPENDENT',32804,1,'2017-12-11 22:36:57',NULL,'2017-12-11 22:36:57','','8106645529',1,'RR','Nayak','LIG-69','',1),('INDEPENDENT',32805,1,'2017-12-11 22:36:57',NULL,'2017-12-11 22:36:57','V','8686635358',2,'M','Shiva','LIG-67','',1),('INDEPENDENT',32806,1,'2017-12-11 22:36:57',NULL,'2017-12-11 22:36:57','','7675854236',1,'Guru','Raja','LIG-68','',1),('INDEPENDENT',32807,1,'2017-12-11 22:36:57',NULL,'2017-12-11 22:36:57','','0000000000',2,'Satyanarayana','Satyanarayana','MIG-440','',1),('INDEPENDENT',32808,1,'2017-12-11 22:36:57',NULL,'2017-12-11 22:36:57','','9177323292',2,'Roja','Rani','MIG-793','',1),('INDEPENDENT',32809,1,'2017-12-11 22:36:57',NULL,'2017-12-11 22:36:57','','8019188708',1,'Krishna','Krishna','MIG-708','',1),('INDEPENDENT',32810,1,'2017-12-11 22:36:57',NULL,'2017-12-11 22:36:57','','9849500300',1,'N','Prasadu','MIG-947','',1),('INDEPENDENT',32811,1,'2017-12-11 22:36:57',NULL,'2017-12-11 22:36:57','','9502162471',1,'Ramachandran','Ramachandran','MIG-321','',1),('INDEPENDENT',32812,1,'2017-12-11 22:36:57',NULL,'2017-12-11 22:36:57','V','9440442456',1,'K','Sashayah','LIG-154','',1),('INDEPENDENT',32813,1,'2017-12-11 22:36:57',NULL,'2017-12-11 22:36:57','','9494789002',1,'DVV','Satyaarayana','MIG-343','',1),('INDEPENDENT',32814,1,'2017-12-11 22:36:57',NULL,'2017-12-11 22:36:57','Vishnu','9000058456',1,'CH','Vardhan','LIG-152','',1),('INDEPENDENT',32815,1,'2017-12-11 22:36:57',NULL,'2017-12-11 22:36:57','','7207634600',1,'I','Latha','LIG-146','',1),('INDEPENDENT',32816,1,'2017-12-11 22:36:57',NULL,'2017-12-11 22:36:57','','7207634600',1,'I','Latha','LIG-170','',1),('INDEPENDENT',32817,1,'2017-12-11 22:36:57',NULL,'2017-12-11 22:36:57','','9676643131',1,'Ramesh','Babu','LIG-171','',1),('INDEPENDENT',32818,1,'2017-12-11 22:36:57',NULL,'2017-12-11 22:36:57','','0000000000',1,'Raju','Garu','MIG-192','',1),('INDEPENDENT',32819,1,'2017-12-11 22:36:57',NULL,'2017-12-11 22:36:57','Siva','9491628015',1,'CMA','Ramkrishna','MIG-534','',1),('INDEPENDENT',32820,1,'2017-12-11 22:36:57',NULL,'2017-12-11 22:36:57','','9849499555',1,'GSV','Ranga','MIG-3-1131','',1),('INDEPENDENT',32821,1,'2017-12-11 22:36:57',NULL,'2017-12-11 22:36:57','','9912149049',2,'K','Swamy','MIG-973/L','',1),('COMMERCIAL',32824,1,'2017-12-11 22:40:19',NULL,'2017-12-11 22:40:19',NULL,'0000000000',1,'FIRSTNAME','LASTNAME','MIG-312',NULL,3),('APARTMENT',32825,1,'2017-12-11 22:40:19',NULL,'2017-12-11 22:40:19',NULL,'9052252416',20,'FIRSTNAME','LASTNAME','MIG-197/198','',2),('APARTMENT',32826,1,'2017-12-11 22:40:19',NULL,'2017-12-11 22:40:19',NULL,'8074155417',13,'FIRSTNAME','LASTNAME','MIG-622/623','',2),('APARTMENT',32827,1,'2017-12-11 22:40:19',NULL,'2017-12-11 22:40:19',NULL,'9701967796',13,'FIRSTNAME','LASTNAME','MIG-149/150','',2),('APARTMENT',32828,1,'2017-12-11 22:40:19',NULL,'2017-12-11 22:40:19',NULL,'9989600004',90,'FIRSTNAME','LASTNAME','Mayuri Hills','',2),('APARTMENT',32829,1,'2017-12-11 22:40:19',NULL,'2017-12-11 22:40:19',NULL,'9912324132',30,'FIRSTNAME','LASTNAME','Lakshmi Classic','',2),('APARTMENT',32830,1,'2017-12-11 22:40:19',NULL,'2017-12-11 22:40:19',NULL,'9908909875',8,'FIRSTNAME','LASTNAME','Srikara Residency','',2),('APARTMENT',32831,1,'2017-12-11 22:40:19',NULL,'2017-12-11 22:40:19',NULL,'9985459934',8,'FIRSTNAME','LASTNAME','Sri Venkata Sai Brundavanam','',2),('APARTMENT',32832,1,'2017-12-11 22:40:19',NULL,'2017-12-11 22:40:19',NULL,'9985465033',8,'FIRSTNAME','LASTNAME','HIG-180','',2),('APARTMENT',32833,1,'2017-12-11 22:40:19',NULL,'2017-12-11 22:40:19',NULL,'9550250931',9,'FIRSTNAME','LASTNAME','HIG-182','',2),('APARTMENT',32834,1,'2017-12-11 22:40:20',NULL,'2017-12-11 22:40:20',NULL,'9542733244',8,'FIRSTNAME','LASTNAME','HIG-184','',2),('APARTMENT',32835,1,'2017-12-11 22:40:20',NULL,'2017-12-11 22:40:20',NULL,'8897222886',8,'FIRSTNAME','LASTNAME','Sun Shine Residency','',2),('APARTMENT',32836,1,'2017-12-11 22:40:20',NULL,'2017-12-11 22:40:20',NULL,'8499994624',8,'FIRSTNAME','LASTNAME','Ajay Complex','',2),('APARTMENT',32837,1,'2017-12-11 22:40:20',NULL,'2017-12-11 22:40:20',NULL,'8008555034',54,'FIRSTNAME','LASTNAME','Prajapathi Elite -3','',2),('APARTMENT',32838,1,'2017-12-11 22:40:20',NULL,'2017-12-11 22:40:20',NULL,'9951094365',20,'FIRSTNAME','LASTNAME','Subbiah park view Apt','',2);
/*!40000 ALTER TABLE `member_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `online_payment_details`
--

DROP TABLE IF EXISTS `online_payment_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `online_payment_details` (
  `from_bank` varchar(255) DEFAULT NULL,
  `transaction_id` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `verified_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK65o4lu77kyuw586c1r76ur1rb` FOREIGN KEY (`id`) REFERENCES `payment_details` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `online_payment_details`
--

LOCK TABLES `online_payment_details` WRITE;
/*!40000 ALTER TABLE `online_payment_details` DISABLE KEYS */;
INSERT INTO `online_payment_details` VALUES (NULL,NULL,68,NULL),(NULL,NULL,69,NULL),(NULL,NULL,70,NULL),('ICICI','1234567890',71,NULL);
/*!40000 ALTER TABLE `online_payment_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_details`
--

DROP TABLE IF EXISTS `payment_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment_details` (
  `type` varchar(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `last_update` datetime NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  `paid_amount` double DEFAULT NULL,
  `paid_date` datetime NOT NULL,
  `receipt_no` varchar(255) DEFAULT NULL,
  `fee_id` bigint(20) DEFAULT NULL,
  `member_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfey4fc1ebkvgutu3b97frxkrq` (`fee_id`),
  KEY `FKfrdxghrle7lce7kl9pdueeyy6` (`member_id`),
  CONSTRAINT `FKfey4fc1ebkvgutu3b97frxkrq` FOREIGN KEY (`fee_id`) REFERENCES `fee_details` (`id`),
  CONSTRAINT `FKfrdxghrle7lce7kl9pdueeyy6` FOREIGN KEY (`member_id`) REFERENCES `member_details` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_details`
--

LOCK TABLES `payment_details` WRITE;
/*!40000 ALTER TABLE `payment_details` DISABLE KEYS */;
INSERT INTO `payment_details` VALUES ('CHEQUE',1,'2017-12-12 09:18:06',NULL,6500,'2017-12-12 09:18:06','1929',10002,32827),('CASH',2,'2017-12-12 09:18:06',NULL,10000,'2017-12-12 09:18:06','1919',10002,32825),('CASH',3,'2017-12-12 09:18:06',NULL,6500,'2017-12-12 09:18:06','1925',10002,32826),('CASH',4,'2017-12-12 09:18:06',NULL,16500,'2017-12-12 09:18:06','1930',10002,32828),('CASH',5,'2017-12-12 09:18:06',NULL,12500,'2017-12-12 09:18:06','1931',10002,32829),('CASH',6,'2017-12-12 09:18:06',NULL,4000,'2017-12-12 09:18:06','1951',10002,32830),('CASH',7,'2017-12-12 09:18:06',NULL,4000,'2017-12-12 09:18:06','1952',10002,32831),('CASH',8,'2017-12-12 09:18:06',NULL,4000,'2017-12-12 09:18:06','1956',10002,32832),('CASH',9,'2017-12-12 09:18:07',NULL,4500,'2017-12-12 09:18:07','1958',10002,32833),('CASH',10,'2017-12-12 09:18:07',NULL,4000,'2017-12-12 09:18:07','1960',10002,32834),('CASH',11,'2017-12-12 09:18:07',NULL,2500,'2017-12-12 09:18:07','1962',10002,32835),('CASH',12,'2017-12-12 09:18:07',NULL,4000,'2017-12-12 09:18:07','1968',10002,32836),('CASH',13,'2017-12-12 09:18:07',NULL,23000,'2017-12-12 09:18:07','1969',10002,32837),('CASH',14,'2017-12-12 09:18:07',NULL,10000,'2017-12-12 09:18:07','1972',10002,32838),('CASH',15,'2017-12-12 09:18:07',NULL,0,'2017-12-12 09:18:07','1916',10009,32824),('CASH',16,'2017-12-12 09:18:07',NULL,1000,'2017-12-12 09:18:07','1954',10009,32808),('CHEQUE',17,'2017-12-12 09:18:07',NULL,1000,'2017-12-12 09:18:07','1949',10001,32806),('CHEQUE',18,'2017-12-12 09:18:07',NULL,1000,'2017-12-12 09:18:07','1964',10001,32814),('CASH',19,'2017-12-12 09:18:07',NULL,1000,'2017-12-12 09:18:07','1901',10001,2),('CASH',20,'2017-12-12 09:18:07',NULL,1000,'2017-12-12 09:18:07','1902',10001,3),('CASH',21,'2017-12-12 09:18:07',NULL,1000,'2017-12-12 09:18:07','1903',10001,4),('CASH',22,'2017-12-12 09:18:08',NULL,1000,'2017-12-12 09:18:08','1904',10001,5),('CASH',23,'2017-12-12 09:18:08',NULL,1000,'2017-12-12 09:18:08','1905',10001,6),('CASH',24,'2017-12-12 09:18:08',NULL,1000,'2017-12-12 09:18:08','1906',10001,7),('CASH',25,'2017-12-12 09:18:08',NULL,1000,'2017-12-12 09:18:08','1907',10001,32773),('CASH',26,'2017-12-12 09:18:08',NULL,1000,'2017-12-12 09:18:08','1908',10001,32774),('CASH',27,'2017-12-12 09:18:08',NULL,1000,'2017-12-12 09:18:08','1909',10001,32775),('CASH',28,'2017-12-12 09:18:08',NULL,1000,'2017-12-12 09:18:08','1910',10001,32776),('CASH',29,'2017-12-12 09:18:08',NULL,1000,'2017-12-12 09:18:08','1911',10001,32777),('CASH',30,'2017-12-12 09:18:08',NULL,1000,'2017-12-12 09:18:08','1912',10001,32778),('CASH',31,'2017-12-12 09:18:08',NULL,1000,'2017-12-12 09:18:08','1913',10001,32779),('CASH',32,'2017-12-12 09:18:08',NULL,1000,'2017-12-12 09:18:08','1914',10001,32780),('CASH',33,'2017-12-12 09:18:08',NULL,1000,'2017-12-12 09:18:08','1915',10001,32781),('CASH',34,'2017-12-12 09:18:08',NULL,1000,'2017-12-12 09:18:08','1917',10001,32782),('CASH',35,'2017-12-12 09:18:08',NULL,1000,'2017-12-12 09:18:08','1918',10001,32783),('CASH',36,'2017-12-12 09:18:08',NULL,1000,'2017-12-12 09:18:08','1920',10001,32784),('CASH',37,'2017-12-12 09:18:08',NULL,1000,'2017-12-12 09:18:08','1921',10001,32785),('CASH',38,'2017-12-12 09:18:08',NULL,1000,'2017-12-12 09:18:08','1922',10001,32786),('CASH',39,'2017-12-12 09:18:08',NULL,1000,'2017-12-12 09:18:08','1923',10001,32787),('CASH',40,'2017-12-12 09:18:08',NULL,1000,'2017-12-12 09:18:08','1924',10001,32788),('CASH',41,'2017-12-12 09:18:08',NULL,2000,'2017-12-12 09:18:08','1932',10001,32789),('CASH',42,'2017-12-12 09:18:08',NULL,1000,'2017-12-12 09:18:08','1933',10001,32790),('CASH',43,'2017-12-12 09:18:08',NULL,1000,'2017-12-12 09:18:08','1934',10001,32791),('CASH',44,'2017-12-12 09:18:08',NULL,1000,'2017-12-12 09:18:08','1935',10001,32792),('CASH',45,'2017-12-12 09:18:08',NULL,1000,'2017-12-12 09:18:08','1936',10001,32793),('CASH',46,'2017-12-12 09:18:08',NULL,1000,'2017-12-12 09:18:08','1937',10001,32794),('CASH',47,'2017-12-12 09:18:09',NULL,1000,'2017-12-12 09:18:09','1938',10001,32795),('CASH',48,'2017-12-12 09:18:09',NULL,2000,'2017-12-12 09:18:09','1939',10001,32796),('CASH',49,'2017-12-12 09:18:09',NULL,1000,'2017-12-12 09:18:09','1940',10001,32797),('CASH',50,'2017-12-12 09:18:09',NULL,1000,'2017-12-12 09:18:09','1941',10001,32798),('CASH',51,'2017-12-12 09:18:09',NULL,1000,'2017-12-12 09:18:09','1942',10001,32799),('CASH',52,'2017-12-12 09:18:09',NULL,1000,'2017-12-12 09:18:09','1943',10001,32800),('CASH',53,'2017-12-12 09:18:09',NULL,1000,'2017-12-12 09:18:09','1944',10001,32801),('CASH',54,'2017-12-12 09:18:09',NULL,1000,'2017-12-12 09:18:09','1945',10001,32802),('CASH',55,'2017-12-12 09:18:09',NULL,1000,'2017-12-12 09:18:09','1946',10001,32803),('CASH',56,'2017-12-12 09:18:09',NULL,1000,'2017-12-12 09:18:09','1947',10001,32804),('CASH',57,'2017-12-12 09:18:09',NULL,1000,'2017-12-12 09:18:09','1948',10001,32805),('CASH',58,'2017-12-12 09:18:09',NULL,1000,'2017-12-12 09:18:09','1950',10001,32807),('CASH',59,'2017-12-12 09:18:09',NULL,1000,'2017-12-12 09:18:09','1953',10001,32808),('CASH',60,'2017-12-12 09:18:09',NULL,1000,'2017-12-12 09:18:09','1955',10001,32809),('CASH',61,'2017-12-12 09:18:09',NULL,1000,'2017-12-12 09:18:09','1957',10001,32810),('CASH',62,'2017-12-12 09:18:09',NULL,1000,'2017-12-12 09:18:09','1959',10001,32811),('CASH',63,'2017-12-12 09:18:09',NULL,1000,'2017-12-12 09:18:09','1961',10001,32812),('CASH',64,'2017-12-12 09:18:09',NULL,1000,'2017-12-12 09:18:09','1965',10001,32815),('CASH',65,'2017-12-12 09:18:09',NULL,1000,'2017-12-12 09:18:09','1966',10001,32816),('CASH',66,'2017-12-12 09:18:09',NULL,1000,'2017-12-12 09:18:09','1967',10001,32817),('CASH',67,'2017-12-12 09:18:09',NULL,1000,'2017-12-12 09:18:09','1970',10001,32818),('ONLINE',68,'2017-12-12 09:19:56',NULL,1000,'2017-12-12 09:19:56','1963',10001,32813),('ONLINE',69,'2017-12-12 09:19:58',NULL,1000,'2017-12-12 09:19:58','1971',10001,32819),('ONLINE',70,'2017-12-12 09:19:58',NULL,1000,'2017-12-12 09:19:58','1973',10001,32820),('ONLINE',71,'2017-12-12 09:19:58',NULL,1000,'2017-12-12 09:19:58','1974',10001,32821);
/*!40000 ALTER TABLE `payment_details` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-12 22:50:05
