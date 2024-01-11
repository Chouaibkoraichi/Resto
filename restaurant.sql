-- MySQL dump 10.13  Distrib 8.0.32, for Linux (x86_64)
--
-- Host: localhost    Database: restaurant
-- ------------------------------------------------------
-- Server version	8.0.32-0buntu0.20.04.1

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
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `restaurant_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY  (`restaurant_id`),
  CONSTRAINT FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`id`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;

--
-- Table structure for table `restaurant`
--

DROP TABLE IF EXISTS `restaurant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS `restaurant` (
  `id` int AUTO_INCREMENT NOT NULL ,
  `address` varchar(255) DEFAULT NULL,
  `ville` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `rating` double NULL,
  `specialite` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant`
--

/*!40000 ALTER TABLE `restaurant` DISABLE KEYS */;
/*!40000 ALTER TABLE `restaurant` ENABLE KEYS */;

--
-- Table structure for table `restaurant_prices`
--

DROP TABLE IF EXISTS `restaurant_prices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `restaurant_prices` (
  `restaurant_id` int NOT NULL,
  `prices` double DEFAULT NULL,
  KEY  (`restaurant_id`),
  CONSTRAINT  FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`id`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant_prices`
--

/*!40000 ALTER TABLE `restaurant_prices` DISABLE KEYS */;
/*!40000 ALTER TABLE `restaurant_prices` ENABLE KEYS */;

--
-- Table structure for table `restaurant_ratings`
--

DROP TABLE IF EXISTS `restaurant_ratings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `restaurant_ratings` (
  `restaurant_id` int NOT NULL,
  `rating` int DEFAULT NULL,
  KEY  (`restaurant_id`),
  CONSTRAINT  FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`id`)
);/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant_ratings`
--

/*!40000 ALTER TABLE `restaurant_ratings` DISABLE KEYS */;
/*!40000 ALTER TABLE `restaurant_ratings` ENABLE KEYS */;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `id` int NOT NULL,
  `created_at` date DEFAULT NULL,
  `likes` int  NULL,
  `rating` int  NULL,
  `text` varchar(255) DEFAULT NULL,
  `restaurant_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY  (`restaurant_id`),
  KEY  (`user_id`),
  CONSTRAINT  FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`id`),
  CONSTRAINT  FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

/*!40000 ALTER TABLE `review` DISABLE KEYS */;
/*!40000 ALTER TABLE `review` ENABLE KEYS */;

--
-- Table structure for table `review_likes`
--

DROP TABLE IF EXISTS `review_likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review_likes` (
  `review_id` int NOT NULL,
  `user_id` int NOT NULL,
  KEY  (`user_id`),
  KEY  (`review_id`),
  CONSTRAINT  FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT  FOREIGN KEY (`review_id`) REFERENCES `review` (`id`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review_likes`
--

/*!40000 ALTER TABLE `review_likes` DISABLE KEYS */;
/*!40000 ALTER TABLE `review_likes` ENABLE KEYS */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL,
  `address` varchar(255) DEFAULT NULL,
   `role` int DEFAULT 2,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `phone_no` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-28 16:33:43
