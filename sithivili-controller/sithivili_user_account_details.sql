--
-- Table structure for table `user_account_details`
--

DROP TABLE IF EXISTS `user_account_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account_details` (
  `userid` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `usertype` varchar(255) DEFAULT NULL,
  `dtype` varchar(31) NOT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account_details`
--

LOCK TABLES `user_account_details` WRITE;
/*!40000 ALTER TABLE `user_account_details` DISABLE KEYS */;
INSERT INTO `user_account_details` VALUES (18,'fRj7kSXeNu7QpLQLgR2VTQ==','vol5','Volunteer','Volunteer',NULL,'male'),(19,'HOb9h/ZInb0et4fQboCgoA==','tsaa','Client','Client','0112729728',NULL),(20,'fRj7kSXeNu7QpLQLgR2VTQ==','abc','Client','Client','0112345678',NULL),(21,'fOZZK2ZstJU/XqKhDWm5SQ==','as','Client','Client','0112898989',NULL),(22,'fRj7kSXeNu7QpLQLgR2VTQ==','vol1','Volunteer','Volunteer',NULL,'female'),(23,'fRj7kSXeNu7QpLQLgR2VTQ==','vol2','Volunteer','Volunteer',NULL,'male'),(24,'fRj7kSXeNu7QpLQLgR2VTQ==','vol3','Volunteer','Volunteer',NULL,'male'),(25,'fRj7kSXeNu7QpLQLgR2VTQ==','vol4','Volunteer','Volunteer',NULL,'female');
/*!40000 ALTER TABLE `user_account_details` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-08-07 10:13:22
