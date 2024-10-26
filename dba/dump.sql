-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bite_size_test
-- ------------------------------------------------------
-- Server version	8.0.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schedule` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date_created` date DEFAULT (curdate()),
  `expiration_date` date DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `schedule_user_id_fk` (`user_id`),
  CONSTRAINT `schedule_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` VALUES (1,'2024-05-03','2024-05-18','Ugh',1),(2,'2024-05-03','2024-05-07','Wahoo',2);
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule_task`
--

DROP TABLE IF EXISTS `schedule_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schedule_task` (
  `schedule_id` int DEFAULT NULL,
  `task_id` int DEFAULT NULL,
  KEY `schedule_id_fk` (`schedule_id`),
  KEY `task_id__fk` (`task_id`),
  CONSTRAINT `schedule_id_fk` FOREIGN KEY (`schedule_id`) REFERENCES `schedule` (`id`),
  CONSTRAINT `task_id__fk` FOREIGN KEY (`task_id`) REFERENCES `task` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule_task`
--

LOCK TABLES `schedule_task` WRITE;
/*!40000 ALTER TABLE `schedule_task` DISABLE KEYS */;
INSERT INTO `schedule_task` VALUES (1,1),(1,2),(1,3);
/*!40000 ALTER TABLE `schedule_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task` (
  `name` varchar(50) NOT NULL,
  `deadline` date DEFAULT NULL,
  `urgency` varchar(20) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `theme_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `task_user_id_fk` (`user_id`),
  KEY `task_theme_id_fk` (`theme_id`),
  CONSTRAINT `task_theme_id_fk` FOREIGN KEY (`theme_id`) REFERENCES `theme` (`id`),
  CONSTRAINT `task_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES ('BiteSize','2024-05-01','Urgent','That\'s this project',1,1,1),('Final Exam',NULL,NULL,'Final Exam for PHP',1,2,2),('REST Project','2024-05-07','Done','Build off of project 3',1,3,3),('Eat Breakfast',NULL,'Urgent','To start off the day',1,4,4),('Eat Lunch',NULL,NULL,'It can wait',1,5,4),('Eat Dinner',NULL,'Urgent','Can\'t skip this one though',1,6,4),('Go To Bed',NULL,'Urgent','Hooray!',1,7,4),('Apply For Jobs',NULL,NULL,'After graduation plans',2,8,NULL),('Hang out with Ian',NULL,'Urgent','Thanks, Arianna.',2,9,NULL),('Accounting',NULL,NULL,'Arianna\'s job',2,12,NULL);
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task_schedule`
--

DROP TABLE IF EXISTS `task_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task_schedule` (
  `task_id` int NOT NULL,
  `schedule_id` int NOT NULL,
  PRIMARY KEY (`schedule_id`,`task_id`),
  KEY `id_idx` (`task_id`),
  CONSTRAINT `schedule_id` FOREIGN KEY (`schedule_id`) REFERENCES `schedule` (`id`),
  CONSTRAINT `task_id` FOREIGN KEY (`task_id`) REFERENCES `task` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task_schedule`
--

LOCK TABLES `task_schedule` WRITE;
/*!40000 ALTER TABLE `task_schedule` DISABLE KEYS */;
/*!40000 ALTER TABLE `task_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `theme`
--

DROP TABLE IF EXISTS `theme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `theme` (
  `name` varchar(50) DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `theme_user_id_fk` (`user_id`),
  CONSTRAINT `theme_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `theme`
--

LOCK TABLES `theme` WRITE;
/*!40000 ALTER TABLE `theme` DISABLE KEYS */;
INSERT INTO `theme` VALUES ('Java',1,1),('PHP',2,1),('JavaScript',3,1),('Health',4,1);
/*!40000 ALTER TABLE `theme` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `name` varchar(25) NOT NULL,
  `email` varchar(50) NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('Ian','iandeloach398@gmail.com',1),('Arianna','arianna@gmail.com',2),('Nate','nate@gmail.com',3);
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

-- Dump completed on 2024-10-26 17:06:51
