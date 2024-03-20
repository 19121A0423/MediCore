-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: online
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `address_id` int NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `pin_code` bigint DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `street_name` varchar(255) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'Bengaluru',566016,'Karnataka','active','Hopefarm',1),(2,'Bengaluru',566016,'Karnataka','active','BTM',2),(3,'Bengaluru',566016,'Karnataka','active','BTM',1),(4,'Bengaluru',566016,'Karnataka','active','BTM',6);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `cart_id` int NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`cart_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (1,0,0,'Active',1),(2,0,0,'Active',2),(3,0,0,'Deactivated',6),(4,279,1,'Active',5);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_products`
--

DROP TABLE IF EXISTS `cart_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_products` (
  `cart_cart_id` int NOT NULL,
  `products_product_id` int DEFAULT NULL,
  KEY `FKewu4olnfvfyd6wfdix1s4gdtr` (`cart_cart_id`),
  KEY `FKs05ud3dow9bi7li6q05gmh546` (`products_product_id`),
  CONSTRAINT `FKewu4olnfvfyd6wfdix1s4gdtr` FOREIGN KEY (`cart_cart_id`) REFERENCES `cart` (`cart_id`),
  CONSTRAINT `FKs05ud3dow9bi7li6q05gmh546` FOREIGN KEY (`products_product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_products`
--

LOCK TABLES `cart_products` WRITE;
/*!40000 ALTER TABLE `cart_products` DISABLE KEYS */;
INSERT INTO `cart_products` VALUES (4,7);
/*!40000 ALTER TABLE `cart_products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `composition`
--

DROP TABLE IF EXISTS `composition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `composition` (
  `composition_id` int NOT NULL AUTO_INCREMENT,
  `composition_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`composition_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `composition`
--

LOCK TABLES `composition` WRITE;
/*!40000 ALTER TABLE `composition` DISABLE KEYS */;
INSERT INTO `composition` VALUES (1,'Ayurvedic'),(2,'Fever'),(3,'Paracetamol'),(4,'Cold And Cough'),(5,'Pain Killer'),(6,'Allergies'),(7,'Mom and Baby');
/*!40000 ALTER TABLE `composition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedback` (
  `feedback_id` int NOT NULL AUTO_INCREMENT,
  `feedback` varchar(255) DEFAULT NULL,
  `feedback_date` datetime(6) DEFAULT NULL,
  `ratings` double DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `order_id` int DEFAULT NULL,
  PRIMARY KEY (`feedback_id`),
  UNIQUE KEY `UK_bx8xib8nsamd39qx9fea6x1pi` (`order_id`),
  CONSTRAINT `FK66tdec0kx8px7cc7xbw3ffx8h` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `cart_id` int DEFAULT NULL,
  `ordered_date` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `address_id` int DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `FKf5464gxwc32ongdvka2rtvw96` (`address_id`),
  CONSTRAINT `FKf5464gxwc32ongdvka2rtvw96` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,1,'2024-03-20 16:01:41.068564','delivered',1),(2,1,'2024-03-20 16:08:11.604566','delivered',1),(3,1,'2024-03-20 16:12:46.965640','delivered',1),(4,1,'2024-03-20 16:16:12.259976','delivered',1),(5,1,'2024-03-20 16:19:35.502854','delivered',1),(6,1,'2024-03-20 16:22:32.893600','delivered',1),(7,2,'2024-03-20 16:30:10.865387','delivered',2),(8,1,'2024-03-20 16:31:35.801679','delivered',1),(9,1,'2024-03-20 16:40:35.418101','delivered',1),(10,1,'2024-03-20 17:26:04.259047','delivered',3),(11,3,'2024-03-20 17:49:29.920934','Ordered',4),(12,3,'2024-03-20 17:53:08.724308','Ordered',4);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `otp`
--

DROP TABLE IF EXISTS `otp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `otp` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `date_time` datetime(6) DEFAULT NULL,
  `otp` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otp`
--

LOCK TABLES `otp` WRITE;
/*!40000 ALTER TABLE `otp` DISABLE KEYS */;
/*!40000 ALTER TABLE `otp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `payment_id` int NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `payment_date` datetime(6) DEFAULT NULL,
  `payment_mode` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `order_id` int DEFAULT NULL,
  PRIMARY KEY (`payment_id`),
  UNIQUE KEY `UK_mf7n8wo2rwrxsd6f3t9ub2mep` (`order_id`),
  CONSTRAINT `FKlouu98csyullos9k25tbpk4va` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1,613,'2024-03-20 16:01:41.223818','upi','success',1),(2,297,'2024-03-20 16:08:11.694326','upi','success',2),(3,307,'2024-03-20 16:16:12.354723','upi','success',4),(4,217,'2024-03-20 16:22:32.994330','upi','success',6),(5,27,'2024-03-20 16:30:10.926404','upi','success',7),(6,279,'2024-03-20 16:31:35.962393','upi','success',8),(7,31,'2024-03-20 16:40:35.599526','upi','success',9),(8,279,'2024-03-20 17:26:04.484319','upi','success',10),(9,279,'2024-03-20 17:49:30.185796','upi','success',11),(10,279,'2024-03-20 17:53:08.780797','upi','success',12);
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `price` double NOT NULL,
  `quantity` int NOT NULL,
  `description` text NOT NULL,
  `product_quantity` int NOT NULL,
  `image` varchar(200) NOT NULL,
  `category_id` int NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `category_id_idx` (`category_id`),
  CONSTRAINT `category_id` FOREIGN KEY (`category_id`) REFERENCES `product_category` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (4,'Cheston Cold Tablet 10\'S',28,-1,'Cheston Cold Tablet is a combination of medicines that effectively relieves symptoms of the common cold such as blocked nose, runny nose, watery eyes, sneezing, and congestion or stuffiness. It helps to loosen thick mucus, making it easier to cough out. This makes it easier for air to move in and out.',1,'https://www.netmeds.com/images/product-v1/600x600/303853/cheston_cold_tablet_10s_3_0.jpg',6,'Add To Cart'),(7,'SEBAMED BABY Powder 200gm',279,91,'It Keep your baby fresh smelling and relaxed with the Sebamed 200g Baby Powder. The powder prevents diaper rash plus its olive oil ingredient helps soothe dry skin. It also reduces the effect of rubbing and chafing and keeps your baby happy and playful. It is available in 200g package.',1,'https://www.netmeds.com/images/product-v1/600x600/375746/sebamed_baby_powder_200gm_44963_0_2.jpg',6,'Add To Cart'),(8,'Dolo 500mg Tablet 15\'S',27,94,'Dolo 500 tablet is used to reduce fever and relieve pain. It contains paracetamol (also known as acetaminophen) as its active ingredient. This medicine works by inhibiting the action of certain substances that causes pain and acts on the brain centre which is responsible for controlling body temperature.',1,'https://www.netmeds.com/images/product-v1/600x600/45297/dolo_500mg_tablet_15s_35282_0_1.jpg',6,'Add To Cart'),(9,'Dabur Ashokarishta Syrup 450 ml',270,98,'Dabur Ashokarishta is an ayurvedic preparation that can help alleviate pain and cramps in women and reduce other difficulties during their tough days. The formulation contains herbs like ashoka, dhataki, musta, haritaki, and amlaki, which have anti-inflammatory and rejuvenating properties. The syrup helps improve digestive power and reduces loss of appetite.',1,'https://www.netmeds.com/images/product-v1/600x600/313684/dabur_ashokarishta_syrup_450_ml_0.jpg',6,'Add To Cart'),(10,'Lejet Tablet 10\'S',38,300,'it is a combination of cetrizine, paracetamole and phenylephire.',1,'https://www.netmeds.com/images/product-v1/600x600/355334/lejet_tablet_10s_43009_0_1.jpg',7,'Add To Cart'),(11,'Hatric 3 Suspension 60ml',31,398,'choloropheramine 1mg+ paracetamole 125+ mg pseudoephedrine 15mg',1,'https://www.netmeds.com/images/product-v1/600x600/390327/hatric_3_suspension_60ml_46823_0_1.jpg',7,'Add To Cart'),(12,'Dipax 5mg Tablet 10\'S',158,198,'DIPAX 5MG TABLET contains Diazepam which belongs to the group of medicines known as Benzodiazepines. DIPAX 5MG TABLET is used in adults for short term relief (2-4 weeks only) of severe anxiety, to relax muscles ',1,'https://www.netmeds.com/images/product-v1/600x600/formulation_based/tablets.jpg',8,'Add To Cart'),(13,'DULOT 30 Tablet 10\'s',59,199,'DULOT 30 TABLET contains Duloxetine which belongs to the group of medicines called Antidepressants. It is used in adults to treat major depressive disorders (persistently depressed mood, feeling of sadness and lack of interest)',1,'https://www.netmeds.com/images/product-v1/600x600/939169/dulot_30_tablet_10s_169210_0_1.jpg',8,'Add To Cart'),(14,'Dulozep 1mg Tablet 10\'S',14,200,' It is used to treat anxiety or sleeping difficulties due to anxiety which significantly affects routine life. ',1,'https://www.netmeds.com/images/product-v1/600x600/formulation_based/tablets.jpg',8,'Add To Cart'),(15,'Baby Organo Herbal Kids Toothpaste',134,250,'It prevents cavities in the tooth, bad breath and gum inflammation. ',1,'https://www.netmeds.com/images/product-v1/600x600/1025963/baby_organo_ayurvedic_herbal_kids_toothpaste_strawberry_50_gm_337556_0_1.jpg',9,'Add To Cart'),(16,'Cetaphil Baby Gentle Wash & Shampoo',626,250,'Cetaphil Baby Gentle Wash & Shampoo is a 2-in-1 formula that blends into a rich lather to gently cleanse the baby’s delicate skin and hair, leaving it soft and clean.',1,'https://www.netmeds.com/images/product-v1/600x600/887115/cetaphil_baby_gentle_wash_and_shampoo_230ml_81117_0_2.jpg',9,'Add To Cart'),(17,'Cetaphil Baby Daily Lotion',716,250,'Ideal for the baby’s delicate skin and hydrates and nourishes the skin.',1,'https://www.netmeds.com/images/product-v1/600x600/958247/cetaphil_baby_daily_with_organic_calendula_lotion_400ml_205797_0_2.jpg',9,'Add To Cart'),(18,'Vaseline Intensive Body Care',190,350,'It is designed to penetrate the outermost layer of the skin to help heal dry skin for long periods of time.',1,'https://www.netmeds.com/images/product-v1/600x600/113523/vaseline_intensive_care_cocoa_glow_body_lotion_100_ml_0.jpg',10,'Add To Cart'),(19,'CeraVe Moisturising Cream',390,350,'It helps increase the skin’s ability to attract, hold, and distribute moisture. It penetrates deeply into the layers of stratum corneum .',1,'https://www.netmeds.com/images/product-v1/600x600/1124770/cerave_moisturising_cream_for_dry_to_very_dry_skin_454_gm_702513_0_1.jpg',10,'Add To Cart'),(21,'ADEL Alfalfa Tonic with Ginseng',1179,150,'is a homoeopathic medicine which is produced from a foraging crop and is enriched with essential vitamins and nutrients. ',1,'https://www.netmeds.com/images/product-v1/600x600/847315/adel_alfalfa_tonic_100_ml_415679_2_0.jpg',12,'Add To Cart'),(22,'Caripill Tablet 15\'S',567,190,'Always take this medicine exactly as your doctor has told you. Try to take this medicine at the same time each day. Do not crush or chew the medicine.',1,'https://www.netmeds.com/images/product-v1/600x600/325841/caripill_tablet_15_s_0.jpg',6,'Added To Cart'),(23,'Caripill Syrup 150ml',422,190,'Always take this medicine exactly as prescribed by your doctor. Shake well befor use.',1,'https://www.netmeds.com/images/product-v1/600x600/412145/caripill_syrup_150ml_53615_0_1.jpg',6,'Add To Cart'),(24,'Tripride 2mg Tablet 15\'S',45,98,'It is used in adults to manage type II diabetes mellitus when diet, exercise and management with single agent does not result in adequate sugar control. ',1,'https://www.netmeds.com/images/product-v1/600x600/377609/tripride_2mg_tablet_15s_0_0.jpg',1,'Add To Cart'),(25,'Galvus Met 50/500mg Tablet 15\'S',350.05,190,'GALVUS MET 50/500MG TABLET is a combination of Metformin and Vildagliptin, which belongs to a group of medicines called Antidiabetic agents.',1,'https://www.netmeds.com/images/product-v1/600x600/846898/galvus_met_50_500mg_tablet_15s_73740_0_4.jpg',1,'Add To Cart'),(26,' Glycomet 500 SR Tablet 20\'S',24.9,200,'GLYCOMET 500 SR TABLET contains Metformin which belongs to the group of medicines called Anti-diabetic agents.',1,'https://www.netmeds.com/images/product-v1/600x600/53613/glycomet_sr_500mg_tablet_20s_0_1.jpg',1,'Add To Cart'),(27,' Istamet 50/500mg Tablet 15\'S',156,200,' It is used to control blood sugar levels in patients with type II diabetes mellitus, along with proper diet and exercise.',1,'https://www.netmeds.com/images/product-v1/600x600/380428/istamet_50_500mg_tablet_15s_45692_0_3.jpg',1,'Add To Cart'),(34,'Dipya Ayurvedic Digestive Care Syrup',234,150,'t follows its unique ayurvedic principles for preventing, diagnosing, treating and curing diseases. Its methods are effective and provide good results. ',1,'https://www.netmeds.com/images/product-v1/600x600/987595/dipya_ayurvedic_digestive_care_syrup_200_ml_0_1.jpg',5,'Add To Cart'),(36,'Bjain Omeo Digestion Syrup 200 ml',153,300,'It is a homoeopathic speciality product for complaints due to digestive disorders. It Acts upon the glandular organs of the intestinal tract and helps to regulate the bile content and flow and helps to emulsify fatty acids in the digestive system.',1,'https://www.netmeds.com/images/product-v1/600x600/828575/bjain_omeo_digestion_syrup_200_ml_0.jpg',4,'Add To Cart'),(37,'LDD Bioscience Yelo-Lax Tablet ',123,250,'LDD Bioscience Yelo-Lax Tablet is a homoeopathic laxative that helps prevent constipation and relieves other gastrointestinal discomforts. It aids bowel movement. It improves the metabolism process and improves the functioning of the gut. ',1,'https://www.netmeds.com/images/product-v1/600x600/919795/ldd_bioscience_yelo_lax_tablet_50s_0_0.jpg',4,'Add To Cart'),(38,'Hapro Digestone Syrup 120 ml',84,200,'Hapro Digestone Syrup. It is known to provide prompt action and can be used by adults and older individuals to eliminate constipation. It is also recommended to prevent constipation and promote healthy bowel movements',1,'https://www.netmeds.com/images/product-v1/600x600/907600/hapro_digestone_syrup_120_ml_0_0.jpg',4,'Add To Cart'),(39,'Hapro Iron Vita Syrup 500 ml',239.2,350,'Hapro Iron Vita Syrup is a homeopathic syrup that helps combat iron deficiency. It can help regulate body temperature and alleviate symptoms related to iron deficiency. Key Ingredients: Withania somnifera; Ferrum met',1,'https://www.netmeds.com/images/product-v1/600x600/907599/hapro_iron_vita_syrup_500_ml_0_0.jpg',4,'Add To Cart'),(40,'Dr.John\'s Gassonil Forte S.F Syrup',234.05,300,'Dr. Johns Gassonil Forte Syrup Dr. Johns Gassonil Forte Syrup is an excellent treatment for stomach and intestinal problems. This medicine helps in treating the burning sensation and reduce hyperacidity. ',1,'https://www.netmeds.com/images/product-v1/600x600/978176/dr_johns_gassonil_forte_s_f_syrup_500_ml_0_0.jpg',4,'Add To Cart'),(41,'Allen Gastropep Digestive Tonic',153,300,'Allen Gastropep Digestive Tonic is useful in the case of gastrointestinal discomforts. The product is useful in the case of abdominal discomfort, gastralgia, indigestion, heart burn, constipation and hyperacidity. Key Ingredients',1,'https://www.netmeds.com/images/product-v1/600x600/831102/allen_gastropep_digestive_tonic_200_ml_0.jpg',4,'Add To Cart'),(42,'Allen Gastropep Digestive Sugar Free Tonic ',112.5,300,'Allen Gastropep Digestive Sugar Free Tonic is a sugar free homeopathic remedy. It is useful in the case of gastrointestinal discomforts. The product is useful in the case of abdominal discomfort, gastralgia, indigestion, heart burn, constipation and hyperacidity.',1,'https://www.netmeds.com/images/product-v1/600x600/831099/allen_gastropep_digestive_sugar_free_tonic_100_ml_0.jpg',4,'Add To Cart'),(43,'Hyponidd Tablet 30\'S',134.5,200,'It is an insulin sensitizer, antihyperglycemic, hypolipidemic, and antioxidant that, when given early enough, reduces the risk of diabetic complications. Because of its position as a natural insulin sensitiser with the d-chiro-inositol edge.',1,'https://www.netmeds.com/images/product-v1/600x600/346528/hyponidd_tablet_30s_0_1.jpg',1,'Add To Cart'),(44,'Benforce M Tablet 10\'S',123,300,'Benforce-M Tablet is a combination of two medicines used to treat type 2 diabetes mellitus in adults. It helps control blood sugar levels in people with diabetes. ',1,'https://www.netmeds.com/images/product-v1/600x600/813166/benforce_m_tablet_10s_58299_0_1.jpg',1,'Add To Cart'),(45,'INZUTEN M Tablet 10\'s',120.8,250,'nzuten M 500mg/20mg Tablet is a combination of two medicines that work together in different ways to lower your blood glucose levels. Lowering blood glucose levels is an essential part of managing diabetes.',1,'https://www.netmeds.com/images/product-v1/600x600/996985/inzuten_m_tablet_10s_0_1.jpg',1,'Add To Cart'),(46,'Planet Ayurveda Dia Beta Plus Capsules',1359.8,300,'The best herbal formula for Diabetes - Diabeta plus is a mixture of various herbs for diabetes, which are described in Ayurveda for taking care of diabetes naturally. ',1,'https://ayurvedamegastore.com/image/catalog/Planet%20Ayurveda/Dia-Beta%20Plus.png',5,'Add To Cart'),(47,'Phenergan Syrup 100ml',488.88,300,'Phenergan 5 MG Syrup is an anti-allergy medicine used to treat the symptoms of allergy. It is also used to prevent and/or control nausea and vomiting due to various conditions (such as motion sickness, surgery).',1,'https://www.practostatic.com/practopedia-images/v3/res-150/phenergan-syrup-100ml_09d4744c-4ced-4a8c-b606-cb498bc80501.JPG',7,'Add To Cart'),(48,'Xyzal Syrup 60ml',164.25,300,'Xyzal Syrup is used in the treatment of Allergic conditions. View Xyzal Syrup (bottle of 60.0 ml Syrup) uses, composition, side-effects, price, substitutes, drug interactions, precautions, warnings, expert advice',1,'https://www.netmeds.com/images/product-v1/600x600/341204/xyzal_syrup_60ml_41400_0_1.jpg',7,'Add To Cart'),(49,'Alerid Syrup 60ml',39.65,250,'Alerid Syrup 60 ml belongs to a class of drugs called anti-histamine or anti-allergic. It contains cetirizine, primarily used to treat various kinds of allergies. ',1,'https://www.netmeds.com/images/product-v1/600x600/313496/alerid_syrup_60ml_38724_0_1.jpg',7,'Add To Cart'),(50,'MuscleBlaze Koshaveda - Ashwagandha',329.56,300,'Under its Ayurveda for Performance range, MuscleBlaze introduces MB Koshaveda Ashwagandha 500mg to plug in a natural boost to your athletic performance. The product delivers 500mg of Ashwagandha per tablet standardized to 5% Withanolides',1,'https://img4.hkrtcdn.com/29151/prd_2915073_o.jpg',5,'Add To Cart'),(52,'Fast&Up Rest Ashwagandha KSM 66',238.86,250,'Fast&Up Ashwagandha KSM 66, Natural Vitality Booster Capsule contains 600mg of Ashwagandha KSM-66 extract from the root with 5% withanolides. It is an Ayurvedic formulation which helps manage stress, anxiety, boosts vitality and is free from alcohol and chemical processing.',1,'https://cdn.nutrabay.com/wp-content/uploads/2022/12/NB-FUP-1063-01-01.jpg',5,'Add To Cart'),(53,'Johnson\'s Baby Shampoo 200 ml',161.1,300,'Johnson\'s baby shampoo is specially designed to be as gentle and mild to the eyes as pure water. Johnson\'s baby no more tears shampoo is free of soap. It makes the rinsing of the hair very easy. You can clean the scalp and hair perfectly without leaving any adverse impact on the protective layer.',1,'https://www.netmeds.com/images/product-v1/600x600/13711/johnson_s_baby_shampoo_200_ml_0.jpg',9,'Add To Cart'),(54,'Johnson\'s Baby Hair Oil',168.2,200,'Johnson\'s Baby Hair Oil is pure, mild and gentle. A light non-greasy hair oil enriched with Avocado and Pro-Vitamin B5 (Panthenyl Triacetate) that helps make hair look soft and healthy.',1,'https://www.netmeds.com/images/product-v1/600x600/13754/johnson_s_baby_hair_oil_200_ml_0.jpg',9,'Add To Cart'),(55,'J.L.Morison Powder Puff-Premium',80.5,200,'J.L.Morison Powder Puff-Premium belongs to ayush, herbal, ayurdevic, himalaya or others category of products. It is available as over the counter products without precription.',1,'https://www.netmeds.com/images/product-v1/600x600/408557/j_l_morison_powder_puff_premium_0_0.jpg',9,'Add To Cart'),(85,'ManSure Ayurvedic Male Health Supplement Capsule',251,190,'Always take this medicine exactly as prescribed by your doctor.',1,'https://www.netmeds.com/images/product-v1/600x600/930359/mansure_ayurvedic_male_health_supplement_capsule_pack_of_10_x_10s_151547_0_2.jpg',5,'Add to cart');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_category`
--

DROP TABLE IF EXISTS `product_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_category` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(45) NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_category`
--

LOCK TABLES `product_category` WRITE;
/*!40000 ALTER TABLE `product_category` DISABLE KEYS */;
INSERT INTO `product_category` VALUES (1,'Diabetes'),(4,'HomeoPathy'),(5,'Ayurvedic'),(6,'Health Care'),(7,'Allergies'),(8,'Anxiety'),(9,'Baby Care'),(10,'Body Care'),(12,'Tonic');
/*!40000 ALTER TABLE `product_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_compositions`
--

DROP TABLE IF EXISTS `product_compositions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_compositions` (
  `product_product_id` int NOT NULL,
  `compositions_composition_id` int DEFAULT NULL,
  UNIQUE KEY `UK_sq21j55qrg7k4h2yp9ngrq352` (`compositions_composition_id`),
  KEY `FKaqswa14x83xugt7mvtetb4loy` (`product_product_id`),
  CONSTRAINT `FKaqswa14x83xugt7mvtetb4loy` FOREIGN KEY (`product_product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `FKe0v2v2awqtr01b0fdxxwh1ahd` FOREIGN KEY (`compositions_composition_id`) REFERENCES `composition` (`composition_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_compositions`
--

LOCK TABLES `product_compositions` WRITE;
/*!40000 ALTER TABLE `product_compositions` DISABLE KEYS */;
INSERT INTO `product_compositions` VALUES (10,6);
/*!40000 ALTER TABLE `product_compositions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_email` varchar(255) DEFAULT NULL,
  `user_gender` char(1) DEFAULT NULL,
  `user_mobile_number` bigint DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_password` varchar(255) DEFAULT NULL,
  `user_role` varchar(255) DEFAULT NULL,
  `user_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'anushauppar1998@gmail.com','F',9458745654,'anusha','anusha@1999','User','Active'),(2,'rajeswaribayapureddymula@gmail.com','F',8596321475,'Rajeswari','raji@123','User','Active'),(3,'nandini@gmail.com','F',8596324512,'Nandini','nandu@!23','User','Active'),(4,'dhrakshayini@gmail.com','F',6321458596,'Dhrashayini','dhraksha@123','User','Active'),(5,'nagaraj@gmail.com','M',9972896654,'Nagraj','nagu@!23','User','Active'),(6,'shalini@gmail.com','F',9547854235,'Shalini','shalini2123','User','Active');
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

-- Dump completed on 2024-03-20 18:14:30
