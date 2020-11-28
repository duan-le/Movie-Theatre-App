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
  `ShowDay` int NOT NULL,
  `ShowMonth` int NOT NULL,
  `ShowYear` int NOT NULL,
  `StartTime` varchar(45) NOT NULL,
  `EndTime` varchar(45) NOT NULL,
  PRIMARY KEY (`Number`,`MovieName`,`ShowDay`,`ShowMonth`,`ShowYear`,`StartTime`,`EndTime`),
  KEY `FK2_idx` (`MovieName`,`ShowDay`,`ShowMonth`,`ShowYear`,`StartTime`,`EndTime`),
  CONSTRAINT `FK2` FOREIGN KEY (`MovieName`, `ShowDay`, `ShowMonth`, `ShowYear`, `StartTime`, `EndTime`) REFERENCES `showtime` (`MovieName`, `Day`, `Month`, `Year`, `StartTime`, `EndTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seat`
--

LOCK TABLES `seat` WRITE;
/*!40000 ALTER TABLE `seat` DISABLE KEYS */;
INSERT INTO `seat` VALUES (1,1,'Movie 1',2,1,2000,'14:00','16:00'),(1,1,'Movie 2',2,2,2000,'15:00','17:00'),(1,1,'Movie 3',2,1,2000,'14:00','16:00'),(2,1,'Movie 1',2,1,2000,'14:00','16:00'),(2,1,'Movie 2',2,2,2000,'15:00','17:00'),(2,1,'Movie 3',2,1,2000,'14:00','16:00'),(3,1,'Movie 1',2,1,2000,'14:00','16:00'),(3,1,'Movie 2',2,2,2000,'15:00','17:00');
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

-- Dump completed on 2020-11-27 16:24:23
