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
-- Table structure for table `seat`
--

DROP TABLE IF EXISTS `seat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seat` (
  `Number` int NOT NULL,
  `Available` tinyint(1) DEFAULT NULL,
  `MovieName` varchar(45) NOT NULL,
  `ShowDate` datetime NOT NULL,
  PRIMARY KEY (`Number`,`MovieName`,`ShowDate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seat`
--

LOCK TABLES `seat` WRITE;
/*!40000 ALTER TABLE `seat` DISABLE KEYS */;
INSERT INTO `seat` VALUES (1,1,'Gone With The Wind','2020-12-04 00:00:00'),(1,1,'Gone With The Wind','2020-12-04 00:03:00'),(1,1,'Jaws','2020-12-04 00:06:00'),(1,1,'Jaws','2020-12-04 00:08:30'),(1,1,'Kung Fu Panda','2020-12-04 00:15:00'),(1,1,'Kung Fu Panda','2020-12-04 00:17:00'),(1,1,'The Godfather','2020-12-04 00:11:00'),(1,1,'The Godfather','2020-12-04 00:13:00'),(1,1,'Unreleased','2022-01-01 00:10:00'),(1,1,'Unreleased','2022-01-01 00:13:00'),(2,1,'Gone With The Wind','2020-12-04 00:00:00'),(2,1,'Gone With The Wind','2020-12-04 00:03:00'),(2,1,'Jaws','2020-12-04 00:06:00'),(2,1,'Jaws','2020-12-04 00:08:30'),(2,1,'Kung Fu Panda','2020-12-04 00:15:00'),(2,1,'Kung Fu Panda','2020-12-04 00:17:00'),(2,1,'The Godfather','2020-12-04 00:11:00'),(2,1,'The Godfather','2020-12-04 00:13:00'),(2,1,'Unreleased','2022-01-01 00:10:00'),(2,1,'Unreleased','2022-01-01 00:13:00'),(3,1,'Gone With The Wind','2020-12-04 00:00:00'),(3,1,'Gone With The Wind','2020-12-04 00:03:00'),(3,1,'Jaws','2020-12-04 00:06:00'),(3,1,'Jaws','2020-12-04 00:08:30'),(3,1,'Kung Fu Panda','2020-12-04 00:15:00'),(3,1,'Kung Fu Panda','2020-12-04 00:17:00'),(3,1,'The Godfather','2020-12-04 00:11:00'),(3,1,'The Godfather','2020-12-04 00:13:00'),(3,1,'Unreleased','2022-01-01 00:10:00'),(3,1,'Unreleased','2022-01-01 00:13:00'),(4,1,'Gone With The Wind','2020-12-04 00:00:00'),(4,1,'Gone With The Wind','2020-12-04 00:03:00'),(4,1,'Jaws','2020-12-04 00:06:00'),(4,1,'Jaws','2020-12-04 00:08:30'),(4,1,'Kung Fu Panda','2020-12-04 00:15:00'),(4,1,'Kung Fu Panda','2020-12-04 00:17:00'),(4,1,'The Godfather','2020-12-04 00:11:00'),(4,1,'The Godfather','2020-12-04 00:13:00'),(4,1,'Unreleased','2022-01-01 00:10:00'),(4,1,'Unreleased','2022-01-01 00:13:00'),(5,1,'Gone With The Wind','2020-12-04 00:00:00'),(5,1,'Gone With The Wind','2020-12-04 00:03:00'),(5,1,'Jaws','2020-12-04 00:06:00'),(5,1,'Jaws','2020-12-04 00:08:30'),(5,1,'Kung Fu Panda','2020-12-04 00:15:00'),(5,1,'Kung Fu Panda','2020-12-04 00:17:00'),(5,1,'The Godfather','2020-12-04 00:11:00'),(5,1,'The Godfather','2020-12-04 00:13:00'),(5,1,'Unreleased','2022-01-01 00:10:00'),(5,1,'Unreleased','2022-01-01 00:13:00'),(6,1,'Gone With The Wind','2020-12-04 00:00:00'),(6,1,'Gone With The Wind','2020-12-04 00:03:00'),(6,1,'Jaws','2020-12-04 00:06:00'),(6,1,'Jaws','2020-12-04 00:08:30'),(6,1,'Kung Fu Panda','2020-12-04 00:15:00'),(6,1,'Kung Fu Panda','2020-12-04 00:17:00'),(6,1,'The Godfather','2020-12-04 00:11:00'),(6,1,'The Godfather','2020-12-04 00:13:00'),(6,1,'Unreleased','2022-01-01 00:10:00'),(6,1,'Unreleased','2022-01-01 00:13:00'),(7,1,'Gone With The Wind','2020-12-04 00:00:00'),(7,1,'Gone With The Wind','2020-12-04 00:03:00'),(7,1,'Jaws','2020-12-04 00:06:00'),(7,1,'Jaws','2020-12-04 00:08:30'),(7,1,'Kung Fu Panda','2020-12-04 00:15:00'),(7,1,'Kung Fu Panda','2020-12-04 00:17:00'),(7,1,'The Godfather','2020-12-04 00:11:00'),(7,1,'The Godfather','2020-12-04 00:13:00'),(7,1,'Unreleased','2022-01-01 00:10:00'),(7,1,'Unreleased','2022-01-01 00:13:00'),(8,1,'Gone With The Wind','2020-12-04 00:00:00'),(8,1,'Gone With The Wind','2020-12-04 00:03:00'),(8,1,'Jaws','2020-12-04 00:06:00'),(8,1,'Jaws','2020-12-04 00:08:30'),(8,1,'Kung Fu Panda','2020-12-04 00:15:00'),(8,1,'Kung Fu Panda','2020-12-04 00:17:00'),(8,1,'The Godfather','2020-12-04 00:11:00'),(8,1,'The Godfather','2020-12-04 00:13:00'),(8,1,'Unreleased','2022-01-01 00:10:00'),(8,1,'Unreleased','2022-01-01 00:13:00'),(9,1,'Gone With The Wind','2020-12-04 00:00:00'),(9,1,'Gone With The Wind','2020-12-04 00:03:00'),(9,1,'Jaws','2020-12-04 00:06:00'),(9,1,'Jaws','2020-12-04 00:08:30'),(9,1,'Kung Fu Panda','2020-12-04 00:15:00'),(9,1,'Kung Fu Panda','2020-12-04 00:17:00'),(9,1,'The Godfather','2020-12-04 00:11:00'),(9,1,'The Godfather','2020-12-04 00:13:00'),(9,1,'Unreleased','2022-01-01 00:10:00'),(9,1,'Unreleased','2022-01-01 00:13:00'),(10,1,'Gone With The Wind','2020-12-04 00:00:00'),(10,1,'Gone With The Wind','2020-12-04 00:03:00'),(10,1,'Jaws','2020-12-04 00:06:00'),(10,1,'Jaws','2020-12-04 00:08:30'),(10,1,'Kung Fu Panda','2020-12-04 00:15:00'),(10,1,'Kung Fu Panda','2020-12-04 00:17:00'),(10,1,'The Godfather','2020-12-04 00:11:00'),(10,1,'The Godfather','2020-12-04 00:13:00'),(10,1,'Unreleased','2022-01-01 00:10:00'),(10,1,'Unreleased','2022-01-01 00:13:00');
/*!40000 ALTER TABLE `seat` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-01 11:41:14
