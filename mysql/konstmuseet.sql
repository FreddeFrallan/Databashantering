-- MySQL dump 10.13  Distrib 8.0.13, for macos10.14 (x86_64)
--
-- Host: 127.0.0.1    Database: konstmuseet
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Anställd`
--

DROP TABLE IF EXISTS `Anställd`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Anställd` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PersonNr` varchar(255) NOT NULL,
  `Namn` varchar(255) NOT NULL,
  `TelefonNr` varchar(255) NOT NULL,
  `Epost` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `PersonNr_UNIQUE` (`PersonNr`),
  KEY `UK_Namn_TelefonNr` (`Namn`,`TelefonNr`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Anställd`
--

LOCK TABLES `Anställd` WRITE;
/*!40000 ALTER TABLE `Anställd` DISABLE KEYS */;
INSERT INTO `Anställd` VALUES (1,'19950127-1234','Melker Mossberg','0701413210','melker.mossberg@test.se'),(2,'19940612-1234','Fredrik Carlsson','0701123454','fcarlsson@test.se'),(3,'19120412-1234','Erik Lenas','0701654321','erik@test.se'),(4,'1975012703','Lina Svensson','07011234567','lina.svensson@hello.se'),(5,'19860415-1234','Erika Thors','0703423111','erika.thoors@javafx.io'),(6,'20010413-4321','Jan Folke','0702816240','janne@dadabas.se'),(7,'19950922-2413','Hans Rosling','0701987654','hans.rosling@email.se'),(8,'19741224-1234','Nils Tomte','0731612345','nils@tomte.se');
/*!40000 ALTER TABLE `Anställd` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BesökarTyp`
--

DROP TABLE IF EXISTS `BesökarTyp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `BesökarTyp` (
  `namn` varchar(255) NOT NULL,
  PRIMARY KEY (`namn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BesökarTyp`
--

LOCK TABLES `BesökarTyp` WRITE;
/*!40000 ALTER TABLE `BesökarTyp` DISABLE KEYS */;
INSERT INTO `BesökarTyp` VALUES ('Pensionär'),('Ungdom'),('Vuxen');
/*!40000 ALTER TABLE `BesökarTyp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EntrePris`
--

DROP TABLE IF EXISTS `EntrePris`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `EntrePris` (
  `BesökarTyp` varchar(255) NOT NULL,
  `UtID` int(11) NOT NULL,
  `Pris` decimal(6,1) NOT NULL,
  PRIMARY KEY (`BesökarTyp`,`UtID`),
  KEY `FK_EntrePris_Utställning_idx` (`UtID`),
  CONSTRAINT `FK_EntrePris_BesökarTyp` FOREIGN KEY (`BesökarTyp`) REFERENCES `besökartyp` (`namn`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_EntrePris_Utställning` FOREIGN KEY (`UtID`) REFERENCES `utställning` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EntrePris`
--

LOCK TABLES `EntrePris` WRITE;
/*!40000 ALTER TABLE `EntrePris` DISABLE KEYS */;
INSERT INTO `EntrePris` VALUES ('Pensionär',1,65.0),('Pensionär',2,60.0),('Pensionär',3,100.0),('Pensionär',4,150.0),('Pensionär',5,100.0),('Pensionär',6,25.0),('Ungdom',1,50.0),('Ungdom',2,75.0),('Ungdom',3,70.0),('Ungdom',4,100.0),('Ungdom',5,74.5),('Ungdom',6,10.0),('Vuxen',1,20.0),('Vuxen',2,80.0),('Vuxen',3,150.0),('Vuxen',4,250.0),('Vuxen',5,160.0),('Vuxen',6,50.0);
/*!40000 ALTER TABLE `EntrePris` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GuidadTur`
--

DROP TABLE IF EXISTS `GuidadTur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `GuidadTur` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `UtID` int(11) NOT NULL,
  `Guide` int(11) NOT NULL,
  `Språk` varchar(255) NOT NULL,
  `StartTid` datetime NOT NULL,
  `StopTid` datetime NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_GuidadTur_Guide_idx` (`Guide`),
  KEY `FK_GuidadTur_UtID_idx` (`UtID`),
  KEY `FK_GuidadTur_Språk_idx` (`Språk`),
  KEY `UK_Utställning_Guide_StartTid` (`UtID`,`Guide`,`StartTid`),
  CONSTRAINT `FK_GuidadTur_Guide` FOREIGN KEY (`Guide`) REFERENCES `guide` (`anstid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_GuidadTur_Språk` FOREIGN KEY (`Språk`) REFERENCES `språk` (`namn`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_GuidadTur_UtID` FOREIGN KEY (`UtID`) REFERENCES `utställning` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GuidadTur`
--

LOCK TABLES `GuidadTur` WRITE;
/*!40000 ALTER TABLE `GuidadTur` DISABLE KEYS */;
INSERT INTO `GuidadTur` VALUES (1,1,1,'Svenska','2018-12-12 08:00:00','2018-12-12 08:30:00'),(2,1,2,'Engelska','2018-12-13 08:00:00','2018-12-12 08:30:00'),(3,1,1,'Finska','2018-12-12 09:00:00','2018-12-12 09:30:00'),(4,1,1,'Svenska','2018-12-12 13:00:00','2018-12-12 08:30:00'),(5,2,2,'Svenska','2018-12-12 08:00:00','2018-12-12 08:30:00'),(6,2,2,'Franska','2018-12-12 12:00:00','2018-12-12 08:30:00'),(7,2,2,'Spanska','2018-12-13 08:00:00','2018-12-13 08:30:00'),(8,3,3,'Kinesiska','2018-12-13 08:00:00','2018-12-13 08:30:00'),(9,4,3,'Svenska','2018-12-13 10:00:00','2018-12-13 10:30:00'),(10,4,4,'Tyska','2018-12-13 15:00:00','2018-12-12 15:30:00'),(11,1,7,'Italienska','2018-12-14 09:00:00','2018-12-14 12:00:00'),(12,6,7,'Engelska','2018-12-13 10:00:00','2018-12-12 11:30:00'),(13,6,8,'Tyska','2018-12-11 08:00:00','2018-12-12 10:30:00'),(14,6,8,'Tyska','2018-12-14 12:30:00','2018-12-12 14:00:00');
/*!40000 ALTER TABLE `GuidadTur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Guide`
--

DROP TABLE IF EXISTS `Guide`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Guide` (
  `AnstID` int(11) NOT NULL,
  PRIMARY KEY (`AnstID`),
  CONSTRAINT `FK_Guide_Anställd` FOREIGN KEY (`AnstID`) REFERENCES `anställd` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Guide`
--

LOCK TABLES `Guide` WRITE;
/*!40000 ALTER TABLE `Guide` DISABLE KEYS */;
INSERT INTO `Guide` VALUES (1),(2),(3),(4),(7),(8);
/*!40000 ALTER TABLE `Guide` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Konstnär`
--

DROP TABLE IF EXISTS `Konstnär`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Konstnär` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Namn` varchar(255) DEFAULT NULL,
  `FödelseDatum` varchar(255) DEFAULT NULL,
  `DödsDatum` varchar(255) DEFAULT NULL,
  `BildURL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Konstnär`
--

LOCK TABLES `Konstnär` WRITE;
/*!40000 ALTER TABLE `Konstnär` DISABLE KEYS */;
INSERT INTO `Konstnär` VALUES (1,'Arvid Fougstedt','1882','1925',NULL),(2,'Pehr Krafft','1750','1812',NULL),(3,'Nils Forsberg','1802','1872',NULL),(4,'Olof Arborelius','1850','1922',NULL);
/*!40000 ALTER TABLE `Konstnär` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `KonstTyp`
--

DROP TABLE IF EXISTS `KonstTyp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `KonstTyp` (
  `namn` varchar(255) NOT NULL,
  PRIMARY KEY (`namn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `KonstTyp`
--

LOCK TABLES `KonstTyp` WRITE;
/*!40000 ALTER TABLE `KonstTyp` DISABLE KEYS */;
INSERT INTO `KonstTyp` VALUES ('Skulptur'),('Tavla');
/*!40000 ALTER TABLE `KonstTyp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Konstverk`
--

DROP TABLE IF EXISTS `Konstverk`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Konstverk` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Titel` varchar(255) DEFAULT NULL,
  `SkapadDatum` varchar(255) DEFAULT NULL,
  `BildURL` varchar(255) DEFAULT NULL,
  `Beskrivning` varchar(255) DEFAULT NULL,
  `Area` decimal(5,1) NOT NULL,
  `KonstTyp` varchar(45) NOT NULL,
  `Konstnär` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Konstverk_KonstTyp_idx` (`KonstTyp`),
  KEY `FK_Konstverk_Konstnär_idx` (`Konstnär`),
  CONSTRAINT `FK_Konstverk_KonstTyp` FOREIGN KEY (`KonstTyp`) REFERENCES `konsttyp` (`namn`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Konstverk_Konstnär` FOREIGN KEY (`Konstnär`) REFERENCES `konstnär` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Konstverk`
--

LOCK TABLES `Konstverk` WRITE;
/*!40000 ALTER TABLE `Konstverk` DISABLE KEYS */;
INSERT INTO `Konstverk` VALUES (1,'Konstnärens mor','ca 1910','https://konstakademien.se/wp-content/uploads/2015/04/Arvid-Fougstedt-Ingeborg-Fougstedt-konstn%C3%A4rens-mor-akvarell-ca-1910.jpg',NULL,1.3,'Tavla',1),(2,'Konstnärens barn Wilhelmina och Pehr','1783','https://konstakademien.se/wp-content/uploads/2015/05/Konstakademien_per_krafft.png',NULL,1.4,'Tavla',2),(3,'Akrobatpojke, studie till Akrobatfamilj inför cirkusdirektören','1884','https://konstakademien.se/wp-content/uploads/2015/05/Konstaademien_Nils_Forsberg.jpg',NULL,1.1,'Tavla',3),(4,'Receptionsstycke','1893','https://konstakademien.se/wp-content/uploads/2015/04/Arborelius-700x420.jpeg',NULL,2.2,'Tavla',4);
/*!40000 ALTER TABLE `Konstverk` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Språk`
--

DROP TABLE IF EXISTS `Språk`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Språk` (
  `namn` varchar(255) NOT NULL,
  PRIMARY KEY (`namn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Språk`
--

LOCK TABLES `Språk` WRITE;
/*!40000 ALTER TABLE `Språk` DISABLE KEYS */;
INSERT INTO `Språk` VALUES ('Danska'),('Engelska'),('Finska'),('Franska'),('Italienska'),('Kinesiska'),('Spanska'),('Svenska'),('Tyska');
/*!40000 ALTER TABLE `Språk` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SpråkKunskap`
--

DROP TABLE IF EXISTS `SpråkKunskap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `SpråkKunskap` (
  `GuideID` int(11) NOT NULL,
  `Språk` varchar(255) NOT NULL,
  PRIMARY KEY (`GuideID`,`Språk`),
  KEY `FK_SpråkKunskap_Språk_idx` (`Språk`),
  CONSTRAINT `FK_SpråkKunskap_Guide` FOREIGN KEY (`GuideID`) REFERENCES `guide` (`anstid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_SpråkKunskap_Språk` FOREIGN KEY (`Språk`) REFERENCES `språk` (`namn`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SpråkKunskap`
--

LOCK TABLES `SpråkKunskap` WRITE;
/*!40000 ALTER TABLE `SpråkKunskap` DISABLE KEYS */;
INSERT INTO `SpråkKunskap` VALUES (1,'Engelska'),(2,'Engelska'),(3,'Engelska'),(7,'Engelska'),(8,'Engelska'),(1,'Finska'),(2,'Franska'),(8,'Franska'),(7,'Italienska'),(3,'Kinesiska'),(8,'Kinesiska'),(2,'Spanska'),(4,'Spanska'),(1,'Svenska'),(2,'Svenska'),(3,'Svenska'),(4,'Svenska'),(7,'Svenska'),(4,'Tyska'),(7,'Tyska');
/*!40000 ALTER TABLE `SpråkKunskap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `talar_svenska`
--

DROP TABLE IF EXISTS `talar_svenska`;
/*!50001 DROP VIEW IF EXISTS `talar_svenska`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `talar_svenska` AS SELECT 
 1 AS `namn`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `talarengelska`
--

DROP TABLE IF EXISTS `talarengelska`;
/*!50001 DROP VIEW IF EXISTS `talarengelska`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `talarengelska` AS SELECT 
 1 AS `namn`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `Utställning`
--

DROP TABLE IF EXISTS `Utställning`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Utställning` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Titel` varchar(255) NOT NULL,
  `Area` decimal(4,1) NOT NULL,
  `StartTid` datetime NOT NULL,
  `SlutTid` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `UK_Titel_StartTid` (`Titel`,`StartTid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Utställning`
--

LOCK TABLES `Utställning` WRITE;
/*!40000 ALTER TABLE `Utställning` DISABLE KEYS */;
INSERT INTO `Utställning` VALUES (1,'Paper Stories',15.5,'2018-12-01 00:00:00','2019-04-16 00:00:00'),(2,'Sovjetisk Affischkonst',100.2,'2018-12-24 00:00:00','2018-12-29 00:00:00'),(3,'Vackta skulpturer',40.3,'2019-01-25 00:00:00',NULL),(4,'Nummer 4',4.1,'2019-02-04 00:00:00','2019-08-04 00:00:00'),(5,'Svenska Klassiker',30.5,'2019-01-01 00:00:00','2019-05-05 00:00:00'),(6,'Legends On Display',84.2,'2019-03-25 00:00:00','2019-07-16 00:00:00');
/*!40000 ALTER TABLE `Utställning` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UtställningsKunskap`
--

DROP TABLE IF EXISTS `UtställningsKunskap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `UtställningsKunskap` (
  `Guide` int(11) NOT NULL,
  `Utställning` int(11) NOT NULL,
  PRIMARY KEY (`Guide`,`Utställning`),
  KEY `FK_UtställningsKunskap_Utställning_idx` (`Utställning`),
  CONSTRAINT `FK_UtställningsKunskap_Guide` FOREIGN KEY (`Guide`) REFERENCES `guide` (`anstid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_UtställningsKunskap_Utställning` FOREIGN KEY (`Utställning`) REFERENCES `utställning` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UtställningsKunskap`
--

LOCK TABLES `UtställningsKunskap` WRITE;
/*!40000 ALTER TABLE `UtställningsKunskap` DISABLE KEYS */;
INSERT INTO `UtställningsKunskap` VALUES (1,1),(7,1),(1,2),(2,2),(3,3),(3,4),(4,4),(4,5),(7,6),(8,6);
/*!40000 ALTER TABLE `UtställningsKunskap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UtställningsObjekt`
--

DROP TABLE IF EXISTS `UtställningsObjekt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `UtställningsObjekt` (
  `UtID` int(11) NOT NULL,
  `KonstID` int(11) NOT NULL,
  PRIMARY KEY (`UtID`,`KonstID`),
  KEY `FK_UtställningsObjekt_Konstverk_idx` (`KonstID`),
  CONSTRAINT `FK_UtställningsObjekt_Konstverk` FOREIGN KEY (`KonstID`) REFERENCES `konstverk` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_UtställningsObjekt_Utställning` FOREIGN KEY (`UtID`) REFERENCES `utställning` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UtställningsObjekt`
--

LOCK TABLES `UtställningsObjekt` WRITE;
/*!40000 ALTER TABLE `UtställningsObjekt` DISABLE KEYS */;
INSERT INTO `UtställningsObjekt` VALUES (1,1),(2,2),(2,3),(3,3),(1,4),(3,4),(4,4);
/*!40000 ALTER TABLE `UtställningsObjekt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `talar_svenska`
--

/*!50001 DROP VIEW IF EXISTS `talar_svenska`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `talar_svenska` AS select `anställd`.`Namn` AS `namn` from `anställd` where `anställd`.`ID` in (select `guide`.`AnstID` from `guide` where `guide`.`AnstID` in (select `språkkunskap`.`GuideID` from `språkkunskap` where (`språkkunskap`.`Språk` = 'Svenska'))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `talarengelska`
--

/*!50001 DROP VIEW IF EXISTS `talarengelska`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `talarengelska` AS select `anställd`.`Namn` AS `namn` from `anställd` where `anställd`.`ID` in (select `språkkunskap`.`GuideID` from `språkkunskap` where (`språkkunskap`.`Språk` = 'Engelska') group by `språkkunskap`.`GuideID`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-19 11:50:22
