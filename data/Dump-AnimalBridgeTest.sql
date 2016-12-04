CREATE DATABASE  IF NOT EXISTS `cs160test` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `cs160test`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win32 (AMD64)
--
-- Host: localhost    Database: cs160test
-- ------------------------------------------------------
-- Server version	5.7.16-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `animalbridge_aboutus`
--

DROP TABLE IF EXISTS `animalbridge_aboutus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `animalbridge_aboutus` (
  `AboutUs_ID` int(11) NOT NULL AUTO_INCREMENT,
  `AboutUs_Title` varchar(100) NOT NULL,
  `AboutUs_Description` longtext NOT NULL,
  PRIMARY KEY (`AboutUs_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `animalbridge_aboutus`
--

LOCK TABLES `animalbridge_aboutus` WRITE;
/*!40000 ALTER TABLE `animalbridge_aboutus` DISABLE KEYS */;
/*!40000 ALTER TABLE `animalbridge_aboutus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `animalbridge_animals`
--

DROP TABLE IF EXISTS `animalbridge_animals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `animalbridge_animals` (
  `Animals_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Animals_Categories` enum('Dog','Cat','Other') NOT NULL,
  `Animals_Name` varchar(100) NOT NULL,
  `Animals_Ago` varchar(100) NOT NULL,
  `Animals_Breeds` varchar(100) NOT NULL,
  `Animals_Price` varchar(100) NOT NULL,
  `Animals_Address` varchar(100) NOT NULL,
  `Animals_Color` varchar(100) NOT NULL,
  `Animals_Description` longtext NOT NULL,
  `Animals_Size` varchar(100) NOT NULL,
  `Animals_OwnerID` int(11) NOT NULL,
  `Animals_OwnerName` varchar(100) NOT NULL,
  PRIMARY KEY (`Animals_ID`),
  KEY `Animals_OwnerID` (`Animals_OwnerID`,`Animals_OwnerName`),
  CONSTRAINT `animalbridge_animals_ibfk_1` FOREIGN KEY (`Animals_OwnerID`, `Animals_OwnerName`) REFERENCES `animalbridge_users` (`user_ID`, `user_Name`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `animalbridge_animals`
--

LOCK TABLES `animalbridge_animals` WRITE;
/*!40000 ALTER TABLE `animalbridge_animals` DISABLE KEYS */;
/*!40000 ALTER TABLE `animalbridge_animals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `animalbridge_contactus`
--

DROP TABLE IF EXISTS `animalbridge_contactus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `animalbridge_contactus` (
  `ContactUs_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ContactUs_Title` varchar(100) NOT NULL,
  `ContactUs_Description` longtext NOT NULL,
  `ContactUs_ContactEmail` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ContactUs_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `animalbridge_contactus`
--

LOCK TABLES `animalbridge_contactus` WRITE;
/*!40000 ALTER TABLE `animalbridge_contactus` DISABLE KEYS */;
/*!40000 ALTER TABLE `animalbridge_contactus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `animalbridge_emergencycontact`
--

DROP TABLE IF EXISTS `animalbridge_emergencycontact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `animalbridge_emergencycontact` (
  `EmergencyContact_ID` int(11) NOT NULL AUTO_INCREMENT,
  `EmergencyContact_Title` varchar(100) NOT NULL,
  `EmergencyContact_Description` longtext NOT NULL,
  `EmergencyContact_ZipCode` int(6) NOT NULL,
  `EmergencyContact_ContactEmail` varchar(100) DEFAULT NULL,
  `EmergencyContact_OwnerID` int(11) NOT NULL,
  `EmergencyContact_OwnerName` varchar(100) NOT NULL,
  PRIMARY KEY (`EmergencyContact_ID`),
  KEY `EmergencyContact_OwnerID` (`EmergencyContact_OwnerID`,`EmergencyContact_OwnerName`),
  CONSTRAINT `animalbridge_emergencycontact_ibfk_1` FOREIGN KEY (`EmergencyContact_OwnerID`, `EmergencyContact_OwnerName`) REFERENCES `animalbridge_users` (`user_ID`, `user_Name`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `animalbridge_emergencycontact`
--

LOCK TABLES `animalbridge_emergencycontact` WRITE;
/*!40000 ALTER TABLE `animalbridge_emergencycontact` DISABLE KEYS */;
/*!40000 ALTER TABLE `animalbridge_emergencycontact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `animalbridge_homepage`
--

DROP TABLE IF EXISTS `animalbridge_homepage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `animalbridge_homepage` (
  `HomePage_ID` int(11) NOT NULL AUTO_INCREMENT,
  `HomePage_Title` varchar(100) NOT NULL,
  `HomePage_Description` longtext NOT NULL,
  `HomePage_RecentNews` varchar(100) NOT NULL,
  PRIMARY KEY (`HomePage_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `animalbridge_homepage`
--

LOCK TABLES `animalbridge_homepage` WRITE;
/*!40000 ALTER TABLE `animalbridge_homepage` DISABLE KEYS */;
/*!40000 ALTER TABLE `animalbridge_homepage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `animalbridge_posting`
--

DROP TABLE IF EXISTS `animalbridge_posting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `animalbridge_posting` (
  `Posting_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Posting_Categories` enum('Emergency','Lost','AdpotionOffer','AdpotionRequest','RecentNews','ShelterPromotion') NOT NULL,
  `Posting_Priority` int(2) NOT NULL,
  `Posting_Title` varchar(100) NOT NULL,
  `Posting_Address` varchar(100) NOT NULL,
  `Posting_Description` longtext NOT NULL,
  `Posting_Price` varchar(100) DEFAULT NULL,
  `Posting_ContactEmail` varchar(100) DEFAULT NULL,
  `Posting_OwnerID` int(11) NOT NULL,
  `Posting_OwnerName` varchar(100) NOT NULL,
  PRIMARY KEY (`Posting_ID`),
  KEY `Posting_OwnerID` (`Posting_OwnerID`,`Posting_OwnerName`),
  CONSTRAINT `animalbridge_posting_ibfk_1` FOREIGN KEY (`Posting_OwnerID`, `Posting_OwnerName`) REFERENCES `animalbridge_users` (`user_ID`, `user_Name`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `animalbridge_posting`
--

LOCK TABLES `animalbridge_posting` WRITE;
/*!40000 ALTER TABLE `animalbridge_posting` DISABLE KEYS */;
/*!40000 ALTER TABLE `animalbridge_posting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `animalbridge_users`
--

DROP TABLE IF EXISTS `animalbridge_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `animalbridge_users` (
  `user_ID` int(11) NOT NULL AUTO_INCREMENT,
  `user_Name` varchar(100) NOT NULL,
  `user_Email` varchar(100) NOT NULL,
  `user_Pass` varchar(100) NOT NULL,
  `user_ComfirmStatus` enum('Y','N') NOT NULL DEFAULT 'N',
  `tokenCode` varchar(100) NOT NULL,
  PRIMARY KEY (`user_ID`,`user_Name`),
  UNIQUE KEY `user_Email` (`user_Email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `animalbridge_users`
--

LOCK TABLES `animalbridge_users` WRITE;
/*!40000 ALTER TABLE `animalbridge_users` DISABLE KEYS */;
/*!40000 ALTER TABLE `animalbridge_users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

