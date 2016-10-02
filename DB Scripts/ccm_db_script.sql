 `ccm_db`.CREATE DATABASE `ccm_db`;

 DROP TABLE IF EXISTS `ccm_db`.`role`;
CREATE TABLE  `ccm_db`.`role` (
  `roleId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `roleName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`roleId`),
  KEY `roleId` (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `ccm_db`.`user`;
CREATE TABLE  `ccm_db`.`user` (
  `userId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userName` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `enabled` tinyint(4) DEFAULT '1',
  `displayName` varchar(100) DEFAULT NULL,
  `roleId` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`userId`),
  KEY `userId` (`userId`),
  KEY `roleId` (`roleId`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `ccm_db`.`customerdetail`;
CREATE TABLE  `ccm_db`.`customerdetail` (
  `customerDetailId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `clientId` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `clientName` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mobileNum` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `birthDate` date DEFAULT NULL,
  `gender` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `creationDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ipAddress` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `country` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `city` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `insertedDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updatedBy` int(10) unsigned DEFAULT NULL,
  `sourceSystem` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `zip` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phoneNum` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `location` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lastLogin` datetime DEFAULT NULL,
  PRIMARY KEY (`customerDetailId`),
  KEY `customerDetailId` (`customerDetailId`),
  KEY `updatedBy` (`updatedBy`),
  CONSTRAINT `customerdetail_ibfk_1` FOREIGN KEY (`updatedBy`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;