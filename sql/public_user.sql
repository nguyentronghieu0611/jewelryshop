-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: public
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `email` varchar(255) DEFAULT NULL,
  `enabled` int DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `key_md5` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `updated_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKsb8bbouer5wak8vyiiy4pf2bx` (`username`),
  UNIQUE KEY `UKf9dvvibvpfsldnu8wh3enop4i` (`username`,`email`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (7,'2021-01-30 09:53:22','vuhoang@gmail.com',1,'Hoàng',NULL,'Vũ','$2a$10$Hsk8UoTIojGMj7AEqyMhtuwsEmiAKJOuxbKIzIR4hlgPWPy3dtbLy','2021-01-30 09:53:22','hoangvu'),(8,'2021-01-30 11:31:39','sonchu@gmail.com',1,'Sơn',NULL,'Chu','$2a$10$c.FKWHu8RuaBvUwORhQ8Fu9wLb6NNenOWjWwZ58wMB1gwWuSZqgZm','2021-01-30 11:31:39','sonchu'),(9,'2021-01-30 11:35:24','hoangkieu@gmail.com',1,'Hoang',NULL,'Kieu','$2a$10$oJAdBtIYpNAEFyM9nM/C5OTBbGI2iBvHxd3a4IYKsQdL4UxH800lK','2021-01-30 11:35:24','hoangkieu'),(10,'2021-01-30 11:53:13','nguyentronghieu06111@gmail.com',1,'Heiu',NULL,'Trong','$2a$10$ElYL/noePYzSHwOlFYQmyOUDiDdm3cDeuLWcx17VFkxhR4wfHeWvO','2021-01-30 11:53:13',NULL),(11,'2021-01-30 11:56:03','chucuc',1,'Cuc',NULL,'Chu','$2a$10$aroHJ.D0QuiCnDxprMKA/erbDby9rQMhRLqpejLgag9B7ZAndccEK','2021-01-30 11:56:03','chucuc'),(12,'2021-01-30 12:57:48','adminphutung@gmail.com',1,'Admin',NULL,'Phụ Tùng','$2a$10$L7.35JV1Fnmv8UXf1aEYae113ANM4M7oEEy37.cTIzsJt7SL2LC26','2021-01-30 12:57:48','admin');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-01  8:02:10
