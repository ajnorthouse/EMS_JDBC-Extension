-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: ems
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'71 Pineknoll Ct.','Palo Alto','CA','94304'),(2,'356 Woodsman Dr.','Redwood City','CA','95014'),(3,'8446 Greystone St.','Redwood City','CA','95014'),(4,'9691 Armstrong Dr.','Palo Alto','CA','94304'),(5,'11 Roehampton St.','Stanford','CA','94309'),(6,'8626 Sierra St.','Redwood City','CA','95014'),(7,'674 Green Hill St.','Palo Alto','CA','94304'),(8,'8 E. Grove Rd.','Stanford','CA','94309'),(9,'9931 East Riverside Ave.','Redwood City','CA','95014'),(10,'8446 W. Foxrun Ave.','Seattle','WA','98101'),(11,'52 Washington St.','Seattle','WA','98101'),(12,'4 PeachTree Rd.','Seattle','WA','98101'),(13,'320 Rockville Ave.','Seattle','WA','98101'),(14,'73 W. Marlborough','Seattle','WA','98101'),(15,'254 Pheasant Ave.','Seattle','WA','98101'),(16,'7780 Paris Hill St.','Seattle','WA','98101'),(17,'7964 Riverview St.','Seattle','WA','98101'),(18,'76 S. Maple Ave.','Seattle','WA','98101'),(19,'9070 Green St.','Mountain View','CA','94039'),(20,'8975 Sout Bellevue Dr.','Mountain View','CA','94039'),(21,'517 Aradia Ln.','Cupertino','CA','94024'),(22,'9917 Glenlake St.','Cupertino','CA','94024'),(23,'64 South Second St.','Mountain View','CA','94039'),(24,'8413 Gainsway St.','Cupertino','CA','94024'),(25,'888 Princess Dr.','Cupertino','CA','94024'),(26,'9809 Sherman Ct.','Mountain View','CA','94039'),(27,'739 Wayn St.','Mountain View','CA','94039');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (1,'Tesla',5000000),(2,'Amazon',6500000),(3,'Apple',6000000);
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'Software development','(999) 999-8888',1500000,1),(2,'Testing','(999) 222-7722',1000000,1),(3,'Manufacturing','(999) 444-6679',2500000,1),(4,'Data Entry','(878) 334-5555',1500000,2),(5,'Hardware Design','(878) 222-8744',2250000,2),(6,'Research','(878) 777-4422',2750000,2),(7,'Web Development','(535) 807-3222',1100000,3),(8,'Micro Proccessing','(535) 600-0099',2750000,3),(9,'AI','(535) 788-3221',2150000,3);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Charles','Sanson',75000,'Jr. Software Developer','(330) 228-3704',1,1),(2,'Kyle','Tail',81000,'Sr. Software Developer','(947) 344-8841',2,1),(3,'Chealsea','Miles',125000,'Lead Software Engineer','(267) 733-5360',3,1),(4,'Rick','Sanchez',50000,'Alpha Tester','787-584-7625',4,2),(5,'Ash','Ketchum',45000,'Beta Tester','(443) 306-2370',5,2),(6,'Gary','Oak',60000,'Charge Tester','(574) 631-6043',6,2),(7,'Gon','Freecss',76000,'Jr. Mechanical Engineer','(714) 276-2451',7,3),(8,'Killua','Zoldyck',90000,'Sr. Mechanical Engineer','(415) 595-4830',8,3),(9,'Hisoka','Amorou',135000,'Lead Mechanical Engineer','(704) 822-4097',9,3),(10,'Edward','Elric',76000,'Jr. Mechanical Engineer','(304) 676-9027',10,4),(11,'Alphonse','Elric',90000,'Sr. Mechanical Engineer','(918) 880-0002',11,4),(12,'Roy','Mustang',135000,'Lead Mechanical Engineer','(304) 561-2210',12,4),(13,'Gray','Fullbuster',95000,'Jr. Hardware Engineer','(714) 276-2651',13,5),(14,'Natsu','Dragneel',110000,'Sr. Hardware Engineer','(415) 595-4330',14,5),(15,'Jellal','Fernandes',152000,'Lead Hardware Engineer','(704) 822-9633',15,5),(16,'Eren','Yeager',67000,'Jr. Researcher','(304) 676-5149',16,6),(17,'Armin','Arlert',78000,'Sr. Researcher','(918) 880-1149',17,6),(18,'Mikasa','Ackerman',96000,'Lead Researcher','(304) 561-3639',18,6),(19,'Naruto','Uzimaki',66800,'Jr. Web Developer','(714) 276-6025',19,7),(20,'Sususke','Uchiha',71200,'Sr. Web Developer','(415) 595-6637',20,7),(21,'Neji','Hyuga',92000,'Lead Web Developer','(704) 822-0656',21,7),(22,'Izuku','Midoriya',78000,'Jr. Micro Proc. Engineer','(304) 676-0632',22,8),(23,'Shoto','Todoroki',87000,'Sr. Micro Proc. Engineer','(918) 880-4257',23,8),(24,'All','Might',123000,'Lead Micro Proc. Engineer','304-561-6314',24,8),(25,'Glenn','Sturges',90000,'Jr. AI Developer','(714) 276-8096',25,9),(26,'Michael','Scott',105000,'Sr. AI Developer','(415) 595-6994',26,9),(27,'Ron','Swanson',143000,'Lead AI Developer','(704) 822-2636',27,9);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-18 19:02:37
