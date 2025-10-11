-- MySQL dump 10.13  Distrib 8.0.35, for Win64 (x86_64)
--
-- Host: localhost    Database: spring_boot
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `list`
--

DROP TABLE IF EXISTS `list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `list` (
  `view_count` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `followers_count` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `is_following` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `title` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `introduction` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `banner` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `updated` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `id` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `category` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `list`
--

LOCK TABLES `list` WRITE;
/*!40000 ALTER TABLE `list` DISABLE KEYS */;
INSERT INTO `list` VALUES ('8236809','4','FALSE','2024年世界互联网大会乌镇峰会',NULL,'https://pic4.zhimg.com/100/v2-53f6195467d7cc22996afb45c69192bf_hd.png','1732174074','1.84181E+18','科技'),('3060849','65','FALSE','元旦快乐丨2025更精彩',NULL,'https://pic2.zhimg.com/100/v2-0135e21b7ab1ffaa6885f7d4f86f2ee5_hd.png','1735616783','1.85738E+18','文化'),('47650110','11','FALSE','北京大视听·春天的“视”集','根据北京市广电局元旦春节两节文化活动整体安排，统筹北京大视听优质资源，知乎于2025年春节期间，在首页首屏上线“北京大视听·春天的‘视’集”专区，集中展播优秀作品，丰富群众假日文化供给。','https://pic3.zhimg.com/100/v2-c59acde5fb37ac1012228908cfb3e492_hd.png','1737352422','1.86466E+18','科技'),('808538','5','FALSE','京彩春节的N种打开方式','春节，承载着团圆的温暖和对未来的美好祝愿。2025 年第一个非遗春节即将登场，你准备好迎新年了吗？','https://pic2.zhimg.com/100/v2-d0dd147c29e7703e168843dc8a0d0291_hd.png','1737513877','1.8651E+18','科技'),('948423','4','FALSE','金蛇起新岁，欢乐闹元宵','「元」气满满，喜「宵」颜开。欢乐元宵，共享团圆。','https://pic2.zhimg.com/100/v2-dd8b92dff1d267a787d43f0c3182d215_hd.png','1739170765','1.87227E+18','科技'),('878106','4','FALSE','忆满九州 情思华夏','清明时节，扫墓踏青，你了解哪些关于清明的诗词文化、传统习俗？准备通过什么样的方式寄托哀思？','https://pic3.zhimg.com/100/v2-b5e37f6959020e42f688ef99c4a41486_hd.png','1743500834','1.8883E+18','科技'),('1478250','35','FALSE','2025｜云上阅读季','云上阅读季，重温经典作品魅力，聚焦网络文学创新表达。','https://pic3.zhimg.com/100/v2-055b906c2678e177b21bf843bc23f0c2_hd.png','1751442884','1.8926E+18','科技'),('811720','4','FALSE','和满京城 奋进九州','粽香浸润古都，龙舟激荡新潮，你了解端午节最初的起源吗？你的家乡都有哪些特殊的传统习俗？这个时节，都有哪些美食呢？你又有哪些出门游玩的计划呢？','https://pic4.zhimg.com/100/v2-9a42dc88c297516b7ecba2933b5dfb77_hd.png','1748581894','1.9103E+18','科技'),('968439','13','FALSE','2025年中国网络文明大会','汇聚网络正能量 引领时代新风尚','https://pic4.zhimg.com/100/v2-a845dcd4264f2cc02b19aee084b11dfb_hd.png','1749527747','1.91546E+18','科技');
/*!40000 ALTER TABLE `list` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-10-11 15:10:00
