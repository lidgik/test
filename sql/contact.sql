# ************************************************************
# Sequel Pro SQL dump
# Version 4096
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 192.168.56.100 (MySQL 5.5.35-0ubuntu0.12.04.1)
# Database: test
# Generation Time: 2014-07-19 17:42:11 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table contact
# ------------------------------------------------------------

DROP TABLE IF EXISTS `contact`;

CREATE TABLE `contact` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `vpmn` varchar(20) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `home_address` varchar(20) DEFAULT NULL,
  `office_address` varchar(20) DEFAULT NULL,
  `memo` varchar(100) DEFAULT NULL,
  `job` varchar(40) DEFAULT NULL,
  `job_level` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 PACK_KEYS=0;

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;

INSERT INTO `contact` (`id`, `name`, `mobile`, `vpmn`, `email`, `home_address`, `office_address`, `memo`, `job`, `job_level`)
VALUES
	(1,'Baijiayong','18211111111','6001','baijiayong@gmail.com','TYSF','Bodejidi','haha','CEO',16),
	(2,'Renjian','18222222222','6002','renjian@gmail.com','TYUT','Bodejidi','hehe','CTO',15);

/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table contact_group
# ------------------------------------------------------------

DROP TABLE IF EXISTS `contact_group`;

CREATE TABLE `contact_group` (
  `id_contact` int(11) DEFAULT NULL,
  `id_group` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 PACK_KEYS=0;



# Dump of table group
# ------------------------------------------------------------

DROP TABLE IF EXISTS `group`;

CREATE TABLE `group` (
  `id_group` int(11) NOT NULL AUTO_INCREMENT,
  `name_group` varchar(20) DEFAULT NULL,
  `memo_group` varchar(100) DEFAULT NULL,
  `parent_group` varchar(20) DEFAULT NULL,
  `address_group` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_group`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 PACK_KEYS=0;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

