-- MariaDB dump 10.19  Distrib 10.4.27-MariaDB, for Win64 (AMD64)
--
-- Host: 127.0.0.1    Database: passwordmanager
-- ------------------------------------------------------
-- Server version	10.4.27-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accounts` (
  `account_id` bigint(20) NOT NULL,
  `account_email` varchar(150) DEFAULT NULL,
  `account_name` varchar(100) NOT NULL,
  `account_number` bigint(20) NOT NULL,
  `account_password` varchar(255) NOT NULL,
  `account_password_blk` bit(1) NOT NULL,
  `account_password_t` int(11) NOT NULL,
  `account_phone` varchar(12) DEFAULT NULL,
  `account_strong_auth` bit(1) NOT NULL,
  `account_surname` varchar(100) NOT NULL,
  PRIMARY KEY (`account_id`),
  UNIQUE KEY `UK_6kplolsdtr3slnvx97xsy2kc8` (`account_number`),
  UNIQUE KEY `UK_45kux45stg4sdujw6f97r0f6g` (`account_email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES (1,'mariusz.plaskota@o2.pl','Mariusz',123456,'$2a$10$wnHhBIX6JY6POBu495YpVujvslpmkTXZuerYh7.E9DNxLt01abaxS','\0',0,'694694097','\0','Plaskota'),(13,'andrzej.nowicki@op.pl','Andrzej',553534,'$2a$10$.grv7MvnC8AXRHZcuYBQOemd5Tlw10xL.lZED5JZayFK/jcruL9pG','\0',0,'456321789','\0','Nowicki');
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accounts_roles`
--

DROP TABLE IF EXISTS `accounts_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accounts_roles` (
  `account_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`account_id`,`role_id`),
  KEY `FKpwest19ib22ux5gk54esw9qve` (`role_id`),
  CONSTRAINT `FKpwest19ib22ux5gk54esw9qve` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
  CONSTRAINT `FKt44duw96d6v8xrapfo4ff2up6` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts_roles`
--

LOCK TABLES `accounts_roles` WRITE;
/*!40000 ALTER TABLE `accounts_roles` DISABLE KEYS */;
INSERT INTO `accounts_roles` VALUES (1,1),(13,2);
/*!40000 ALTER TABLE `accounts_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (35);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logs`
--

DROP TABLE IF EXISTS `logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `logs` (
  `log_id` bigint(20) NOT NULL,
  `log_date` varchar(255) NOT NULL,
  `log_msg` varchar(255) NOT NULL,
  `account_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`log_id`),
  KEY `FKb7byyt1p5jplk1s9888kecy4r` (`account_id`),
  CONSTRAINT `FKb7byyt1p5jplk1s9888kecy4r` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logs`
--

LOCK TABLES `logs` WRITE;
/*!40000 ALTER TABLE `logs` DISABLE KEYS */;
INSERT INTO `logs` VALUES (2,'2023-02-16','Successfully logged in',1),(3,'2023-02-16','Successfully logged in',1),(4,'2023-02-16','Successfully logged in',1),(5,'2023-02-16','Log in - wrong password',1),(6,'2023-02-16','Log in - wrong password',1),(7,'2023-02-16','Log in - wrong password',1),(8,'2023-02-16','Log in - The account has been blocked',1),(9,'2023-02-16','Log in - The account has been blocked',1),(10,'2023-02-16','Successfully logged in',1),(11,'2023-02-16','Log in - wrong password',1),(12,'2023-02-16','Successfully logged in',1),(14,'2023-02-16','Log in - wrong password',13),(15,'2023-02-16','Log in - wrong password',13),(16,'2023-02-16','Log in - wrong password',13),(17,'2023-02-16','Log in - The account has been blocked',13),(18,'2023-02-16','Successfully logged in',1),(19,'2023-02-16','Successfully logged in',13),(20,'2023-02-16','Successfully logged in',13),(21,'2023-02-16','Profile data has been changed!',13),(22,'2023-02-16','Password has been changed!',13),(24,'2023-02-17','Log in - wrong password',1),(25,'2023-02-17','Log in - wrong password',1),(26,'2023-02-17','Successfully logged in',1),(27,'2023-02-17','Successfully logged in',1),(29,'2023-02-17','Successfully logged in',1),(30,'2023-02-17','Log in - wrong password',1),(31,'2023-02-17','Log in - wrong password',1),(32,'2023-02-17','Successfully logged in',1),(33,'2023-02-17','Password has been changed!',1),(34,'2023-02-17','Successfully logged in',1);
/*!40000 ALTER TABLE `logs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `role_id` bigint(20) NOT NULL,
  `role_active` bit(1) NOT NULL,
  `role_description` varchar(300) DEFAULT NULL,
  `role_name` varchar(255) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `UK_716hgxp60ym1lifrdgp67xt5k` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'','Administrator','Admin'),(2,'','User','User');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sites`
--

DROP TABLE IF EXISTS `sites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sites` (
  `site_id` bigint(20) NOT NULL,
  `site_address` varchar(250) DEFAULT NULL,
  `site_date` varchar(255) NOT NULL,
  `site_login` varchar(50) DEFAULT NULL,
  `site_name` varchar(50) NOT NULL,
  `site_password` varchar(50) DEFAULT NULL,
  `account_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`site_id`),
  KEY `FKb50w4ol7u2y0awk32g0n9gfhh` (`account_id`),
  CONSTRAINT `FKb50w4ol7u2y0awk32g0n9gfhh` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sites`
--

LOCK TABLES `sites` WRITE;
/*!40000 ALTER TABLE `sites` DISABLE KEYS */;
INSERT INTO `sites` VALUES (23,'https://login.poczta.home.pl/','2023-02-16','mariusz.plaskota','Poczta e-mail','O44fcMbtkXV9ZtffdzdlhA==',13),(28,'https://login.poczta.home.pl/','2023-02-17','mariusz.plaskota','Poczta e-mail','7dsel6nnVEfSNVROaCqWY54RzA0h+aBT',1);
/*!40000 ALTER TABLE `sites` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-18  0:54:51
