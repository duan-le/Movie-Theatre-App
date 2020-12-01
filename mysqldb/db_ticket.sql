CREATE DATABASE  IF NOT EXISTS `db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `db`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: db
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
  `Number` int NOT NULL AUTO_INCREMENT,
  `SeatNumber` int NOT NULL,
  `MovieName` varchar(45) NOT NULL,
  `Price` float NOT NULL,
  `Date` datetime NOT NULL,
  PRIMARY KEY (`Number`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (1,1,'Gone With The Wind',10,'2020-12-04 00:00:00'),(2,2,'Gone With The Wind',10,'2020-12-04 00:00:00'),(3,3,'Gone With The Wind',10,'2020-12-04 00:00:00'),(4,4,'Gone With The Wind',10,'2020-12-04 00:00:00'),(5,5,'Gone With The Wind',10,'2020-12-04 00:00:00'),(6,6,'Gone With The Wind',10,'2020-12-04 00:00:00'),(7,7,'Gone With The Wind',10,'2020-12-04 00:00:00'),(8,8,'Gone With The Wind',10,'2020-12-04 00:00:00'),(9,9,'Gone With The Wind',10,'2020-12-04 00:00:00'),(10,10,'Gone With The Wind',10,'2020-12-04 00:00:00'),(11,1,'Gone With The Wind',10,'2020-12-04 00:03:00'),(12,2,'Gone With The Wind',10,'2020-12-04 00:03:00'),(13,3,'Gone With The Wind',10,'2020-12-04 00:03:00'),(14,4,'Gone With The Wind',10,'2020-12-04 00:03:00'),(15,5,'Gone With The Wind',10,'2020-12-04 00:03:00'),(16,6,'Gone With The Wind',10,'2020-12-04 00:03:00'),(17,7,'Gone With The Wind',10,'2020-12-04 00:03:00'),(18,8,'Gone With The Wind',10,'2020-12-04 00:03:00'),(19,9,'Gone With The Wind',10,'2020-12-04 00:03:00'),(20,10,'Gone With The Wind',10,'2020-12-04 00:03:00'),(21,1,'Jaws',15,'2020-12-04 00:06:00'),(22,2,'Jaws',15,'2020-12-04 00:06:00'),(23,3,'Jaws',15,'2020-12-04 00:06:00'),(24,4,'Jaws',15,'2020-12-04 00:06:00'),(25,5,'Jaws',15,'2020-12-04 00:06:00'),(26,6,'Jaws',15,'2020-12-04 00:06:00'),(27,7,'Jaws',15,'2020-12-04 00:06:00'),(28,8,'Jaws',15,'2020-12-04 00:06:00'),(29,9,'Jaws',15,'2020-12-04 00:06:00'),(30,10,'Jaws',15,'2020-12-04 00:06:00'),(31,1,'Jaws',15,'2020-12-04 00:08:30'),(32,2,'Jaws',15,'2020-12-04 00:08:30'),(33,3,'Jaws',15,'2020-12-04 00:08:30'),(34,4,'Jaws',15,'2020-12-04 00:08:30'),(35,5,'Jaws',15,'2020-12-04 00:08:30'),(36,6,'Jaws',15,'2020-12-04 00:08:30'),(37,7,'Jaws',15,'2020-12-04 00:08:30'),(38,8,'Jaws',15,'2020-12-04 00:08:30'),(39,9,'Jaws',15,'2020-12-04 00:08:30'),(40,10,'Jaws',15,'2020-12-04 00:08:30'),(41,1,'The Godfather',10,'2020-12-04 00:11:00'),(42,2,'The Godfather',10,'2020-12-04 00:11:00'),(43,3,'The Godfather',10,'2020-12-04 00:11:00'),(44,4,'The Godfather',10,'2020-12-04 00:11:00'),(45,5,'The Godfather',10,'2020-12-04 00:11:00'),(46,6,'The Godfather',10,'2020-12-04 00:11:00'),(47,7,'The Godfather',10,'2020-12-04 00:11:00'),(48,8,'The Godfather',10,'2020-12-04 00:11:00'),(49,9,'The Godfather',10,'2020-12-04 00:11:00'),(50,10,'The Godfather',10,'2020-12-04 00:11:00'),(51,1,'The Godfather',10,'2020-12-04 00:13:00'),(52,2,'The Godfather',10,'2020-12-04 00:13:00'),(53,3,'The Godfather',10,'2020-12-04 00:13:00'),(54,4,'The Godfather',10,'2020-12-04 00:13:00'),(55,5,'The Godfather',10,'2020-12-04 00:13:00'),(56,6,'The Godfather',10,'2020-12-04 00:13:00'),(57,7,'The Godfather',10,'2020-12-04 00:13:00'),(58,8,'The Godfather',10,'2020-12-04 00:13:00'),(59,9,'The Godfather',10,'2020-12-04 00:13:00'),(60,10,'The Godfather',10,'2020-12-04 00:13:00'),(61,1,'Kung Fu Panda',15,'2020-12-04 00:15:00'),(62,2,'Kung Fu Panda',15,'2020-12-04 00:15:00'),(63,3,'Kung Fu Panda',15,'2020-12-04 00:15:00'),(64,4,'Kung Fu Panda',15,'2020-12-04 00:15:00'),(65,5,'Kung Fu Panda',15,'2020-12-04 00:15:00'),(66,6,'Kung Fu Panda',15,'2020-12-04 00:15:00'),(67,7,'Kung Fu Panda',15,'2020-12-04 00:15:00'),(68,8,'Kung Fu Panda',15,'2020-12-04 00:15:00'),(69,9,'Kung Fu Panda',15,'2020-12-04 00:15:00'),(70,10,'Kung Fu Panda',15,'2020-12-04 00:15:00'),(71,1,'Kung Fu Panda',15,'2020-12-04 00:17:00'),(72,2,'Kung Fu Panda',15,'2020-12-04 00:17:00'),(73,3,'Kung Fu Panda',15,'2020-12-04 00:17:00'),(74,4,'Kung Fu Panda',15,'2020-12-04 00:17:00'),(75,5,'Kung Fu Panda',15,'2020-12-04 00:17:00'),(76,6,'Kung Fu Panda',15,'2020-12-04 00:17:00'),(77,7,'Kung Fu Panda',15,'2020-12-04 00:17:00'),(78,8,'Kung Fu Panda',15,'2020-12-04 00:17:00'),(79,9,'Kung Fu Panda',15,'2020-12-04 00:17:00'),(80,10,'Kung Fu Panda',15,'2020-12-04 00:17:00'),(81,1,'Unreleased',20,'2022-01-01 00:10:00'),(82,2,'Unreleased',20,'2022-01-01 00:10:00'),(83,3,'Unreleased',20,'2022-01-01 00:10:00'),(84,4,'Unreleased',20,'2022-01-01 00:10:00'),(85,5,'Unreleased',20,'2022-01-01 00:10:00'),(86,6,'Unreleased',20,'2022-01-01 00:10:00'),(87,7,'Unreleased',20,'2022-01-01 00:10:00'),(88,8,'Unreleased',20,'2022-01-01 00:10:00'),(89,9,'Unreleased',20,'2022-01-01 00:10:00'),(90,10,'Unreleased',20,'2022-01-01 00:10:00'),(91,1,'Unreleased',20,'2022-01-01 00:13:00'),(92,2,'Unreleased',20,'2022-01-01 00:13:00'),(93,3,'Unreleased',20,'2022-01-01 00:13:00'),(94,4,'Unreleased',20,'2022-01-01 00:13:00'),(95,5,'Unreleased',20,'2022-01-01 00:13:00'),(96,6,'Unreleased',20,'2022-01-01 00:13:00'),(97,7,'Unreleased',20,'2022-01-01 00:13:00'),(98,8,'Unreleased',20,'2022-01-01 00:13:00'),(99,9,'Unreleased',20,'2022-01-01 00:13:00'),(100,10,'Unreleased',20,'2022-01-01 00:13:00');
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-30 23:03:27
