-- MySQL dump 10.15  Distrib 10.0.23-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: HMS
-- ------------------------------------------------------
-- Server version	10.0.23-MariaDB

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
-- Table structure for table `appointments`
--

DROP TABLE IF EXISTS `appointments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appointments` (
  `appID` int(11) NOT NULL AUTO_INCREMENT,
  `patientID` int(11) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  `docID` int(11) DEFAULT NULL,
  `deptID` int(11) DEFAULT NULL,
  `patientname` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`appID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointments`
--

LOCK TABLES `appointments` WRITE;
/*!40000 ALTER TABLE `appointments` DISABLE KEYS */;
INSERT INTO `appointments` VALUES (2,2,'30-7-2017',1,2,'kgp'),(3,3,'30-7-2017',1,2,'akhilesh'),(6,7,'30-7-2017',1,2,'Mr. Akhilesh Sharma'),(11,1,'30-7-2017',1,2,'Mr. Paras Chadha');
/*!40000 ALTER TABLE `appointments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departments`
--

DROP TABLE IF EXISTS `departments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `departments` (
  `deptID` int(11) NOT NULL AUTO_INCREMENT,
  `deptName` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`deptID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departments`
--

LOCK TABLES `departments` WRITE;
/*!40000 ALTER TABLE `departments` DISABLE KEYS */;
INSERT INTO `departments` VALUES (1,'ANAESTHESIA'),(2,'CARDIOLOGY'),(3,'ANATOMY'),(4,'Cardiovascular and Thoracic Surgery'),(5,'Community Medicine'),(6,'Dermatology Venereology Leprology'),(7,'Dietitics'),(8,'Gastroenterology'),(9,'General Surgery'),(10,'Hematology'),(11,'Hepatology');
/*!40000 ALTER TABLE `departments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctors`
--

DROP TABLE IF EXISTS `doctors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doctors` (
  `docID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `deptID` int(11) DEFAULT NULL,
  `contact` varchar(100) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `uname` varchar(100) DEFAULT NULL,
  `gender` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`docID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctors`
--

LOCK TABLES `doctors` WRITE;
/*!40000 ALTER TABLE `doctors` DISABLE KEYS */;
INSERT INTO `doctors` VALUES (1,'doc1',2,'9888988998','#100,chd','doc1@gmail.com',34,'doc1','Male'),(2,'Akhil',1,'8198069959','#402,B-12','sharmaakb12@gmail.com',22,'DOC_1','male');
/*!40000 ALTER TABLE `doctors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patientinfo`
--

DROP TABLE IF EXISTS `patientinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patientinfo` (
  `patientID` int(11) NOT NULL,
  `disease` varchar(200) DEFAULT NULL,
  `prescription` varchar(500) DEFAULT NULL,
  `remarks` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  `deptID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patientinfo`
--

LOCK TABLES `patientinfo` WRITE;
/*!40000 ALTER TABLE `patientinfo` DISABLE KEYS */;
INSERT INTO `patientinfo` VALUES (1,'fever','Paracetamol - 2tablets morning and night','Take care','30-07-2017',5),(1,'shcbdbhb','bhbhb','bhbhb','Sun Jul 30 12:14:19 IST 2017',2),(5,'jhjghg','ghghgh','ghgg','Sun Jul 30 12:19:35 IST 2017',2),(5,'mental','schdhcgdahc ,cgdhcg\r\nsdghsgshd , hdagh','tu shi nhi ho skta','Sun Jul 30 12:22:05 IST 2017',2),(1,'Cold','Citrazin ,  1 tablet at night\r\ncough syrup','Take care!','Sun Jul 30 13:44:14 IST 2017',2),(1,'Acidity','Gelusin','Take care!','Sun Jul 30 13:46:03 IST 2017',2),(1,'Constipation','Eno , daily\r\n','Take Care!','Sun Jul 30 13:51:37 IST 2017',2);
/*!40000 ALTER TABLE `patientinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patients`
--

DROP TABLE IF EXISTS `patients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patients` (
  `patientID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `gender` varchar(100) DEFAULT NULL,
  `dob` varchar(100) DEFAULT NULL,
  `mstatus` varchar(100) DEFAULT NULL,
  `bgroup` varchar(100) DEFAULT NULL,
  `nationality` varchar(100) DEFAULT NULL,
  `address` varchar(300) DEFAULT NULL,
  `uid` varchar(100) DEFAULT NULL,
  `allergies` varchar(500) DEFAULT NULL,
  `uname` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`patientID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patients`
--

LOCK TABLES `patients` WRITE;
/*!40000 ALTER TABLE `patients` DISABLE KEYS */;
INSERT INTO `patients` VALUES (1,'Mr. Paras Chadha',23,'Female','','Married','A+','Indian','hghgg','67676767667878','ghghgg','paras','123','nayangupta824@gmail.com','6767676767'),(2,'Mr. Kumar Gaurav',23,'Male','','Married','B+','Indian','Hallomajra','43435245545435435','No','kgp','123','nayangupta824@gmail.com','8877877779'),(3,'Mr. Akhilesh Kumar',23,'Other','','Widowed','O-','Japanese','Nowhere','hai nahi','bimar hi bimar','akhil','123','nayangupta824@gmail.com','7867878777'),(5,'Mr. Akhilesh Kumar',22,'Male','','Unmarried','A+','Indian','23243','123223435556','jhgjjhh','akhil2','123','sharmaakb12@gmail.com','8764567890'),(6,'Mr. Akhil Sharma',22,'Male','','Unmarried','A+','Indian','2334','123223435567','sdfg','akhil3','asdf','sharmaakb12@gmail.com','8764567899'),(7,'Mr. Akhil Sharma',22,'Male','','Unmarried','A+','Indian','2335','123223435577','sdff','akhil4','fghj','sharmaakb12@gmail.com','8764567879'),(8,'Mr. Akhil Sharma',22,'Male','','Unmarried','A+','Indian','23378','123223435555','sdcv','akhil5','cvbn','sharmaakb12@gmail.com','8764567845');
/*!40000 ALTER TABLE `patients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `uname` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  `verified` int(11) NOT NULL,
  `category` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('admin','password',1,'admin'),('doc1','123',1,'doctor'),('paras','123',1,'patient'),('kgp','123',1,'patient'),('akhil','123',1,'patient'),('akhil2','123',1,'patient'),('akhil3','asdf',1,'patient'),('akhil4','fghj',1,'patient'),('akhil5','cvbn',1,'patient');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-30 14:32:29
