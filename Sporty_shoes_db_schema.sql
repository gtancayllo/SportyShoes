-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: sporty_shoes_db
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `categoryName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Running Shoes'),(2,'Walking Shoes'),(3,'Tennis Shoes'),(4,'Hiking Boots'),(5,'Soccer Shoes');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `productName` varchar(255) DEFAULT NULL,
  `price` int DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `category_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_product_category_idx` (`category_id`),
  CONSTRAINT `fk_product_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Nebula Man',190,'Color: Black; Size : 39-47',1),(2,'Spherica Man',119,'Color:Blue,Black; Size : 39-47',1),(3,'Iberica Women',210,'Color:White, Black: Size:35-41',2),(4,'Radente Man',170,'Color:Gray, Black;Size :39-46',2),(5,'Asics Woman',130,'Color:White;Size:36-41',3),(6,'TenisZon Woman',190,'Color:White,Light blue;Size:34-41',3),(7,'Mountaiin Hugh',210,'Color:Briown,Blue; Size:39-47',4),(8,'Soccer Zero Woman',250,'Color:White,Red;Size:36-42',5),(9,'Soccer Extreme Man',270,'Color: Black,Red;Size:39-41',5),(10,'Nebulosa  Man',190,'Color: Black; Size : 39-47',2),(15,'Himalaya Man',210,'Color: White ; Size:36-42',1);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchaseorder`
--

DROP TABLE IF EXISTS `purchaseorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchaseorder` (
  `id` int NOT NULL AUTO_INCREMENT,
  `orderDate` datetime DEFAULT NULL,
  `totalCost` double DEFAULT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_purchaseorder_user1_idx` (`user_id`),
  CONSTRAINT `fk_purchaseorder_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchaseorder`
--

LOCK TABLES `purchaseorder` WRITE;
/*!40000 ALTER TABLE `purchaseorder` DISABLE KEYS */;
INSERT INTO `purchaseorder` VALUES (1,'2022-12-20 00:00:00',590,3),(2,'2022-12-21 09:00:00',130,4),(3,'2022-12-22 11:02:00',250,3),(4,'2022-12-23 17:12:00',270,4),(5,'2022-12-24 18:10:00',130,4),(6,'2022-12-25 21:10:00',440,5),(7,'2022-12-26 14:25:00',460,6);
/*!40000 ALTER TABLE `purchaseorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchaseorderdetail`
--

DROP TABLE IF EXISTS `purchaseorderdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchaseorderdetail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `quantity` int DEFAULT NULL,
  `cost` double DEFAULT NULL,
  `product_id` int NOT NULL,
  `purchaseorder_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_purchaseorderdetail_product1_idx` (`product_id`),
  KEY `fk_purchaseorderdetail_purchaseorder1_idx` (`purchaseorder_id`),
  CONSTRAINT `fk_purchaseorderdetail_product1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `fk_purchaseorderdetail_purchaseorder1` FOREIGN KEY (`purchaseorder_id`) REFERENCES `purchaseorder` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchaseorderdetail`
--

LOCK TABLES `purchaseorderdetail` WRITE;
/*!40000 ALTER TABLE `purchaseorderdetail` DISABLE KEYS */;
INSERT INTO `purchaseorderdetail` VALUES (1,2,380,1,1),(2,1,210,3,1),(3,1,130,5,2),(4,1,250,7,3),(5,1,270,9,4),(6,1,130,5,5),(7,1,210,3,6),(8,1,170,4,6),(9,1,270,9,7),(10,1,190,10,7);
/*!40000 ALTER TABLE `purchaseorderdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `role` varchar(70) DEFAULT NULL,
  `signedUp` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Ricardo','Artola','rartola','1234','rartola@mail.com','Administrator',0),(2,'Natalie','Ortega','nortega','5678','nortega@mail.com','SalesStaff',0),(3,'Maria','Mujica','mmujica','8910','mmujica@mail.com','User',1),(4,'Pedro','Contreras','pcontreras','7890','pcontreras@mail.com','User',1),(5,'Pablo','Perez','pperez','1478','pperez@mail.com','User',1),(6,'Ximena','Prianka','xprianka','5896','xprianka@mail.com','User',1);
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

-- Dump completed on 2022-12-25 20:41:59
