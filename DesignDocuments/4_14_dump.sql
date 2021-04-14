-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: stardew_assistant
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `crops`
--

DROP TABLE IF EXISTS `crops`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `crops` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `item_id` int NOT NULL,
                         `season` varchar(30) DEFAULT NULL,
                         `seed_price` int DEFAULT NULL,
                         `sell_price` int DEFAULT NULL,
                         `recipes` varchar(200) DEFAULT NULL,
                         `bundles` varchar(100) DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `id` (`id`),
                         UNIQUE KEY `crops_id_uindex` (`id`),
                         KEY `crops_item_id_fk` (`item_id`),
                         CONSTRAINT `crops_item_id_fk` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `crops`
--

INSERT INTO `crops` VALUES (2,2,'Spring',80,170,'Cheese Cauliflower','Spring Crops'),(3,3,'Spring',40,60,'Escargot, Fiddlehead Risotto','None'),(4,4,'Spring',70,110,'Stir Fry, Salmon Dinner','None'),(5,5,'Spring',20,35,'Farmers Lunch, Parsnip Soup','Spring Crops, Quality Crops'),(6,6,'Spring',50,80,'Hashbrowns','Spring Crops'),(7,7,'Spring',100,220,'Rhubarb Pie','None'),(8,8,'Spring',20,30,'None','None'),(9,9,'Spring',40,30,'None','None'),(10,10,'Spring, Summer',2500,15,'None','None'),(11,11,'Spring',60,40,'Bean Hotpot','Spring Crops'),(12,12,'Spring',100,120,'None','None'),(13,21,'Summer',80,250,'Pink Cake, Fruit Salad','Summer Crops, Quality Crops'),(14,22,'Summer',100,140,'Poppyseed Muffin','Chef\'s Bundle'),(15,23,'Summer',40,90,'Radish Salad, Red Plate','None'),(16,24,'Summer (2nd year+)',100,260,'Coleslaw, Fish Taco, Red Plate','Dye Bundle'),(17,25,'Summer',400,750,'None','None'),(18,26,'Summer',50,90,'None','None'),(19,27,'Summer, Fall',200,80,'None','Dye Bundle'),(20,28,'Summer, Fall',10,25,'None','Fodder Bundle'),(21,29,'Summer',80,50,'Fruit Salad, Blueberry Tart','Summer Crops'),(22,30,'Summer, Fall',150,50,'Tortilla','Fall Crops, Quality Crops'),(23,31,'Summer',60,25,'None','None'),(24,32,'Summer',40,40,'Pepper Poppers','Summer Crops'),(25,33,'Summer',50,60,'Pizza, Bruschetta, Eggplant Parmesan, Fish Stew, Spaghetti, Vegetable Stew','Summer Crops'),(26,38,'Fall',70,150,'Salmon Dinner','None'),(27,39,'Fall',30,160,'Artichoke Dip','None'),(28,40,'Fall',20,100,'Vegetable Stew','None'),(29,41,'Fall',50,80,'Super Meal','None'),(30,42,'Fall',200,290,'None','None'),(31,43,'Fall',100,320,'Autumn\'s Bounty, Pumpkin Pie, Pumpkin Soup','Fall Crops, Quality Crops'),(32,44,'Fall',1000,3000,'None','None'),(33,45,'Fall',60,160,'Glazed Yams, Autumn\'s Bounty','Fall Crops'),(34,46,'Fall',240,75,'Cranberry Candy, Cranberry Sauce, Stuffing, Super Meal','None'),(35,47,'Fall',20,60,'Survival Burger, Eggplant Parmesan','Fall Crops'),(36,48,'Fall',60,80,'None','Summer Foraging');

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `name` varchar(45) NOT NULL,
                        `type` varchar(45) NOT NULL,
                        PRIMARY KEY (`id`),
                        KEY `item_type_fk_idx` (`type`),
                        CONSTRAINT `item_type_fk` FOREIGN KEY (`type`) REFERENCES `item_type` (`item_type_name`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

INSERT INTO `item` VALUES (1,'Blue Jazz','crop'),(2,'Cauliflower','crop'),(3,'Garlic','crop'),(4,'Kale','crop'),(5,'Parsnip','crop'),(6,'Potato','crop'),(7,'Rhubarb','crop'),(8,'Tulip','crop'),(9,'Unmilled Rice','crop'),(10,'Coffee Bean','crop'),(11,'Green Bean','crop'),(12,'Strawberry','crop'),(13,'Wild Horseradish','forage'),(14,'Daffodil','forage'),(15,'Leek','forage'),(16,'Dandelion','forage'),(17,'Spring Onion','forage'),(18,'Morel','forage'),(19,'Common Mushjroom','forage'),(20,'Salmonberry','forage'),(21,'Melon','crop'),(22,'Poppy','crop'),(23,'Radish','crop'),(24,'Red Cabbage','crop'),(25,'Starfruit','crop'),(26,'Summer Spangle','crop'),(27,'Sunflower','crop'),(28,'Wheat','crop'),(29,'Blueberry','crop'),(30,'Corn','crop'),(31,'Hops','crop'),(32,'Hot Pepper','crop'),(33,'Tomato','crop'),(34,'Spice Berry','forage'),(35,'Sweet Pea','forage'),(36,'Red Mushroom','forage'),(37,'Fiddlehead Fern','forage'),(38,'Amaranth','crop'),(39,'Artichoke','crop'),(40,'Beet','crop'),(41,'Bok Choy','crop'),(42,'Fairy Rose','crop'),(43,'Pumpkin','crop'),(44,'Sweet Gem Berry','crop'),(45,'Yam','crop'),(46,'Cranberries','crop'),(47,'Eggplant','crop'),(48,'Grape','crop'),(49,'Winter Root','forage'),(50,'Crystal Fruit','forage'),(51,'Snow Yam','forage'),(52,'Crocus','forage'),(53,'Holly','forage');

--
-- Table structure for table `item_type`
--

DROP TABLE IF EXISTS `item_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item_type` (
                             `id_item_type` int NOT NULL AUTO_INCREMENT,
                             `item_type_name` varchar(20) NOT NULL,
                             PRIMARY KEY (`id_item_type`),
                             KEY `idx_item_type_item_type_name` (`item_type_name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_type`
--

INSERT INTO `item_type` VALUES (3,'animal product'),(1,'crop'),(4,'fish'),(2,'forage'),(5,'recipe');

--
-- Table structure for table `notes`
--

DROP TABLE IF EXISTS `notes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notes` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `user_id` int NOT NULL,
                         `item_id` int DEFAULT NULL,
                         `note` varchar(50) DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `id` (`id`),
                         KEY `notes_item_id_fk` (`item_id`),
                         KEY `notes_user_id_fk` (`user_id`),
                         CONSTRAINT `notes_item_id_fk` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                         CONSTRAINT `notes_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notes`
--

INSERT INTO `notes` VALUES (52,9,4,'a new note yaa'),(53,9,4,'hi');

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `role_name` varchar(25) DEFAULT NULL,
                        `username` varchar(20) DEFAULT NULL,
                        `user_id` int DEFAULT NULL,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `id` (`id`),
                        KEY `role_user_id_fk` (`user_id`),
                        CONSTRAINT `role_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

INSERT INTO `role` VALUES (4,'user','user6',6),(6,'user','newuser',7),(7,'user','newuser1',8),(8,'user','user',9),(13,'user','111',14);

--
-- Table structure for table `tokens`
--

DROP TABLE IF EXISTS `tokens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tokens` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `user_id` int DEFAULT NULL,
                          `token` varchar(100) DEFAULT NULL,
                          `expiration` datetime DEFAULT NULL,
                          PRIMARY KEY (`id`),
                          UNIQUE KEY `id` (`id`),
                          KEY `tokens_user_id_fk` (`user_id`),
                          CONSTRAINT `tokens_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tokens`
--


--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `email` varchar(300) DEFAULT NULL,
                        `username` varchar(20) NOT NULL,
                        `password` varchar(260) NOT NULL,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `id` (`id`),
                        UNIQUE KEY `user_id_uindex` (`id`),
                        UNIQUE KEY `user_username_uindex` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

INSERT INTO `user` VALUES (6,'fisher.jordyn@gmail.com','user6','0080b455380ad08fd932bbb54627d34ebf5a09998cdec4ffb74a7a1166c41422$1$d05444221199ad36414bef798e48b4ba76d3ca81590e8fd96041ba0286412f4a'),(7,'test@example.com','newuser','585684942248ab54a6563ae81768254da07b2ad71e08b8cb464fd828f4e363d5$1$98ad143aa6eec6beb537069a415c9631475e02cb7d4bdb69b4d9a5fa27cd543b'),(8,'newuser@test.com','newuser1','b80f72cccb0825eefd9a9906db964c124e3a4d35dbeb6f0d2d5c885234d44a7e$1$86e978d381f94a9eb10f5857f77386d20a88083c7c7a4d5beacabd8c74ec45bd'),(9,'test@example.com','user','05e701944c96fbff29eacaec443eb176b1f9c7e09a656274c95e4303f2cb70a7$1$ea555af7eb9a04e84f76ac6cb333a1c7e9ad11c234207548fe34f03fabfc98ae'),(14,'user@user.com','111','7ec3a6160200a6126cd566126d8c8682abb82edc6742db1687d119ea6f6851f2$1$d3487ad63a69dc1f8aa1e8bd5ef82aa9e720d691efc94193c2929c23079282fd');

--
-- Table structure for table `user_favorites`
--

DROP TABLE IF EXISTS `user_favorites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_favorites` (
                                  `id` int NOT NULL AUTO_INCREMENT,
                                  `user_id` int NOT NULL,
                                  `item_id` int NOT NULL,
                                  PRIMARY KEY (`id`),
                                  UNIQUE KEY `id` (`id`),
                                  UNIQUE KEY `user_favorites_id_uindex` (`id`),
                                  KEY `user_favorites_item_id_fk` (`item_id`),
                                  KEY `user_favorites_user_id_fk` (`user_id`),
                                  CONSTRAINT `user_favorites_item_id_fk` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                                  CONSTRAINT `user_favorites_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_favorites`
--

INSERT INTO `user_favorites` VALUES (12,9,33);

--
-- Table structure for table `user_searches`
--

DROP TABLE IF EXISTS `user_searches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_searches` (
                                 `id` int NOT NULL AUTO_INCREMENT,
                                 `user_id` int DEFAULT NULL,
                                 `item_id` int DEFAULT NULL,
                                 PRIMARY KEY (`id`),
                                 UNIQUE KEY `id` (`id`),
                                 KEY `user_searches_item_id_fk` (`item_id`),
                                 KEY `user_searches_user_id_fk` (`user_id`),
                                 CONSTRAINT `user_searches_item_id_fk` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                                 CONSTRAINT `user_searches_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=185 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_searches`
--

INSERT INTO `user_searches` VALUES (4,9,3),(5,9,3),(6,9,43),(7,9,12),(8,9,29),(9,9,12),(10,9,8),(11,9,4),(12,9,7),(13,9,43),(14,9,7),(15,9,12),(16,9,4),(17,9,3),(18,9,4),(19,9,12),(20,9,43),(21,9,43),(22,9,4),(23,9,4),(24,9,4),(25,9,4),(26,9,4),(27,9,4),(28,9,4),(29,9,4),(30,9,4),(31,9,4),(32,9,8),(33,9,4),(34,9,4),(35,9,4),(36,9,4),(37,9,4),(38,9,4),(39,9,4),(40,9,4),(41,9,28),(42,9,43),(43,9,4),(44,9,4),(45,9,4),(46,9,4),(47,9,4),(48,9,4),(49,9,33),(50,9,4),(51,9,4),(52,9,4),(53,9,4),(54,9,4),(55,9,4),(56,9,4),(57,9,4),(58,9,4),(59,9,4),(60,9,4),(61,9,4),(62,9,4),(63,9,4),(64,9,4),(65,9,4),(66,9,4),(67,9,4),(68,9,43),(69,9,43),(70,9,4),(71,9,4),(72,9,4),(73,9,4),(74,9,4),(75,9,4),(76,9,4),(77,9,4),(78,9,4),(79,9,4),(80,9,4),(81,9,4),(82,9,4),(83,9,4),(84,9,4),(85,9,4),(86,9,4),(87,9,4),(88,9,4),(89,9,4),(90,9,4),(91,9,4),(92,9,4),(93,9,4),(94,9,4),(95,9,4),(96,9,33),(97,9,4),(98,9,4),(99,9,4),(100,9,4),(101,9,4),(102,9,4),(103,9,4),(104,9,4),(105,9,4),(106,9,4),(107,9,4),(108,9,4),(109,9,4),(110,9,4),(111,9,4),(112,9,4),(113,9,4),(114,9,4),(115,9,43),(116,9,4),(117,9,33),(118,9,33),(119,9,33),(120,9,8),(121,9,8),(122,9,8),(123,9,43),(124,9,43),(125,9,4),(126,9,4),(127,9,4),(128,9,4),(129,9,4),(130,9,4),(131,9,4),(132,9,4),(133,9,4),(134,9,4),(135,9,4),(136,9,4),(137,9,4),(138,9,4),(139,9,33),(140,9,43),(141,9,28),(142,9,28),(143,9,28),(144,9,33),(145,9,33),(146,9,4),(147,9,33),(148,9,4),(149,9,4),(150,9,4),(151,9,4),(152,9,4),(153,9,4),(154,9,43),(155,9,43),(156,9,43),(157,9,43),(158,9,43),(159,9,43),(160,9,43),(161,9,43),(162,9,43),(163,9,43),(164,9,43),(165,9,43),(166,9,43),(167,9,43),(168,9,43),(169,9,4),(170,9,4),(171,9,4),(172,9,4),(173,9,4),(174,9,8),(175,9,43),(176,9,33),(177,9,28),(178,9,4),(179,9,4),(180,9,4),(181,9,4),(182,9,4),(183,9,43),(184,9,43);
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-14 14:52:49
