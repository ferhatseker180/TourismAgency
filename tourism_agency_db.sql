-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: patika_tourismagency
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `tbl_featuretype`
--

DROP TABLE IF EXISTS `tbl_featuretype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_featuretype` (
  `id` int NOT NULL AUTO_INCREMENT,
  `feature_name` varchar(150) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_featuretype`
--

LOCK TABLES `tbl_featuretype` WRITE;
/*!40000 ALTER TABLE `tbl_featuretype` DISABLE KEYS */;
INSERT INTO `tbl_featuretype` VALUES (1,'Ücretsiz Otopark'),(2,'7/24 Oda Servisi'),(3,'Fitness Center'),(4,'SPA'),(5,'Ücretsiz WiFi'),(6,'Yüzme havuzu'),(7,'Hotel Concierge');
/*!40000 ALTER TABLE `tbl_featuretype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_hotel`
--

DROP TABLE IF EXISTS `tbl_hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_hotel` (
  `hotel_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `city` varchar(150) NOT NULL,
  `region` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phoneNumber` varchar(15) NOT NULL,
  `stars` int NOT NULL,
  PRIMARY KEY (`hotel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_hotel`
--

LOCK TABLES `tbl_hotel` WRITE;
/*!40000 ALTER TABLE `tbl_hotel` DISABLE KEYS */;
INSERT INTO `tbl_hotel` VALUES (1,'Marmara Hotel','İstanbul','Taksim','Beyoğlu, İstanbul','marmara@gmail','02125058085',3),(2,'Hilton Hotel','İstanbul','Şişli','Harbiye, Cumhuriyet Cd. No:50, 34367 Şişli/İstanbul','hilton@gmail.com','02123406780',6),(3,'kapadokya','nevşehir','kapadokya','Ortahisar/Kapadokya/Nevşehir','kapadokya@gmail.com','021678',4),(4,'Swissotel','İzmir','Alsancak, Konak, İzmir','Konak','swissotel@gmail.com','02325056070',3),(5,'Kılıç Hotel','Ankara ','Çankaya',' Yıldızevler, Jose Marti Cd. No:2, 06550 Çankaya/Ankara','divan@hotmail.com','03124093000',7),(16,'Hilton Hotel','İzmir','Konak','İsmet Kaptan, Gazi Osman Paşa Blv. No:7, 35210 Konak/İzmir','izmirhilton@hotmail.com','50687',5),(22,'Karahanlı Hotel','İstanbul','Eyüpsultan','Eyüpsultan/İstanbul','karahanlı@mail.com','147852258',5),(23,'Ferhat Hotel','İzmir','Göztepe','Göztepe/İzmir','ferhat@mail.com','14785236998',10);
/*!40000 ALTER TABLE `tbl_hotel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_hotel_feature`
--

DROP TABLE IF EXISTS `tbl_hotel_feature`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_hotel_feature` (
  `hotel_id` int NOT NULL,
  `feature_id` int NOT NULL,
  `feature_name` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_hotel_feature`
--

LOCK TABLES `tbl_hotel_feature` WRITE;
/*!40000 ALTER TABLE `tbl_hotel_feature` DISABLE KEYS */;
INSERT INTO `tbl_hotel_feature` VALUES (1,5,'Ücretsiz WİFİ'),(9,7,'Hotel Concierge'),(9,7,'Hotel Concierge'),(1,1,'Ücretsiz Otopark'),(2,2,'7/24 Oda Servisi'),(4,1,'Ücretsiz Otopark'),(5,4,'Fitness Center'),(12,2,'7/24 Oda Servisi'),(9,8,'Yüzme Havuzu'),(13,1,'Ücretsiz Otopark'),(14,5,'Ücretsiz WiFi'),(15,4,'Fitness Center'),(16,1,'Ücretsiz Otopark'),(16,8,'Yüzme Havuzu'),(12,7,'Hotel Concierge'),(1,6,'Yüzme havuzu'),(1,4,'SPA'),(9,5,'Ücretsiz WiFi'),(17,3,'Fitness Center'),(17,6,'Yüzme havuzu'),(1,7,'Hotel Concierge'),(16,5,'Ücretsiz WiFi'),(9,6,'Yüzme havuzu'),(23,1,'Ücretsiz Otopark'),(23,2,'7/24 Oda Servisi'),(23,3,'Fitness Center'),(23,4,'SPA'),(3,1,'Ücretsiz Otopark'),(3,5,'Ücretsiz WiFi');
/*!40000 ALTER TABLE `tbl_hotel_feature` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_hotel_pension`
--

DROP TABLE IF EXISTS `tbl_hotel_pension`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_hotel_pension` (
  `id` int NOT NULL AUTO_INCREMENT,
  `hotel_id` int NOT NULL,
  `pension_id` int NOT NULL,
  `pension_name` varchar(150) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_hotel_pension`
--

LOCK TABLES `tbl_hotel_pension` WRITE;
/*!40000 ALTER TABLE `tbl_hotel_pension` DISABLE KEYS */;
INSERT INTO `tbl_hotel_pension` VALUES (7,12,1,'Ultra Herşey Dahil'),(11,16,7,'Oda Kahvaltı'),(12,1,1,'Ultra All Inclusive'),(14,2,1,'Ultra All Inclusive'),(17,23,1,'Ultra All Inclusive'),(20,3,5,'HALF PENSION'),(21,3,3,'ROOM BREAKFAST');
/*!40000 ALTER TABLE `tbl_hotel_pension` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_hotel_room`
--

DROP TABLE IF EXISTS `tbl_hotel_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_hotel_room` (
  `room_id` int NOT NULL AUTO_INCREMENT,
  `hotel_id` int NOT NULL,
  `season_id` int NOT NULL,
  `pension_id` int NOT NULL,
  `room_type` varchar(150) NOT NULL,
  `bed_number` int NOT NULL,
  `stock` int NOT NULL,
  `price_child` int NOT NULL,
  `price_adult` int NOT NULL,
  PRIMARY KEY (`room_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_hotel_room`
--

LOCK TABLES `tbl_hotel_room` WRITE;
/*!40000 ALTER TABLE `tbl_hotel_room` DISABLE KEYS */;
INSERT INTO `tbl_hotel_room` VALUES (1,1,1,1,'Single',70,199,200,300),(2,1,10,2,'Double',10,100,200,500),(17,1,15,6,'king',4,5,600,800),(19,1,1,1,'junior king',3,6,500,600),(22,1,1,6,'King',20,20,1000,2000),(23,3,11,4,'Suit',2,20,1500,3000);
/*!40000 ALTER TABLE `tbl_hotel_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_pensiontype`
--

DROP TABLE IF EXISTS `tbl_pensiontype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_pensiontype` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pension_name` varchar(150) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_pensiontype`
--

LOCK TABLES `tbl_pensiontype` WRITE;
/*!40000 ALTER TABLE `tbl_pensiontype` DISABLE KEYS */;
INSERT INTO `tbl_pensiontype` VALUES (1,'Ultra All Inclusive'),(2,'ALL INCLUSIVE'),(3,'ROOM BREAKFAST'),(4,'FULL PENSION'),(5,'HALF PENSION'),(6,'ONLY BED'),(7,'FULL CREDIT EXCLUDING ALCOHOL\r\n');
/*!40000 ALTER TABLE `tbl_pensiontype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_reservation`
--

DROP TABLE IF EXISTS `tbl_reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_reservation` (
  `reservation_id` int NOT NULL AUTO_INCREMENT,
  `hotel_id` int NOT NULL,
  `room_id` int NOT NULL,
  `customer_name` varchar(150) NOT NULL,
  `customer_tc` varchar(150) NOT NULL,
  `customer_phone` varchar(150) NOT NULL,
  `customer_email` varchar(150) NOT NULL,
  `child_number` int NOT NULL,
  `adult_number` int NOT NULL,
  PRIMARY KEY (`reservation_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_reservation`
--

LOCK TABLES `tbl_reservation` WRITE;
/*!40000 ALTER TABLE `tbl_reservation` DISABLE KEYS */;
INSERT INTO `tbl_reservation` VALUES (22,1,1,'Ferhat','14785236932','15698745132','ferhat@mail.com',0,1);
/*!40000 ALTER TABLE `tbl_reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_room_property`
--

DROP TABLE IF EXISTS `tbl_room_property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_room_property` (
  `room_id` int NOT NULL,
  `property_name` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_room_property`
--

LOCK TABLES `tbl_room_property` WRITE;
/*!40000 ALTER TABLE `tbl_room_property` DISABLE KEYS */;
INSERT INTO `tbl_room_property` VALUES (1,'Televizyon'),(2,'Minibar'),(3,'Kasa'),(4,'Ücretsiz Wifi'),(5,'Wifi'),(6,'Oyun Konsolu'),(7,'Projeksiyon'),(20,'televizyon');
/*!40000 ALTER TABLE `tbl_room_property` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_season`
--

DROP TABLE IF EXISTS `tbl_season`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_season` (
  `season_id` int NOT NULL AUTO_INCREMENT,
  `hotel_id` int NOT NULL,
  `season_name` varchar(150) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  PRIMARY KEY (`season_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_season`
--

LOCK TABLES `tbl_season` WRITE;
/*!40000 ALTER TABLE `tbl_season` DISABLE KEYS */;
INSERT INTO `tbl_season` VALUES (1,1,'Kış Sezonu','2024-01-01','2024-05-31'),(10,2,'Yaz sezonu','2024-05-01','2024-09-01'),(11,3,'Yaz sezonu','2024-05-01','2024-09-01'),(12,4,'Kış Sezonu','2024-01-01','2024-05-31'),(13,5,'Kış Sezonu','2024-01-01','2024-05-31'),(15,1,'Yaz sezonu','2024-06-06','2024-09-06'),(18,3,'Kış sezonu','2024-01-01','2024-06-01'),(19,16,'kış sezonu','2024-01-01','2024-05-01'),(21,1,'Winter','2025-01-01','2025-06-01'),(22,23,'Winter Season','2024-01-01','2024-05-31'),(23,23,'Summer Season','2024-06-01','2024-12-31');
/*!40000 ALTER TABLE `tbl_season` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_user`
--

DROP TABLE IF EXISTS `tbl_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tcNo` varchar(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(50) NOT NULL,
  `name` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `usertype` enum('admin','employee','user') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_user`
--

LOCK TABLES `tbl_user` WRITE;
/*!40000 ALTER TABLE `tbl_user` DISABLE KEYS */;
INSERT INTO `tbl_user` VALUES (26,'147852369','ferhat','12','ferhattt','Şeker','admin'),(28,'147258369','süleyman','12','Süleyman','Çakır','employee'),(29,'987654123','karahanlı','12','karahanlıt','Karahanlı','admin'),(30,'852369741','kılıç','12','Ali','Kılıç','employee');
/*!40000 ALTER TABLE `tbl_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'patika_tourismagency'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-23 22:24:29
