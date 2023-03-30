# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.5.42)
# Database: todo-app
# Generation Time: 2023-03-30 10:51:25 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table todo_statuses
# ------------------------------------------------------------

DROP TABLE IF EXISTS `todo_statuses`;

CREATE TABLE `todo_statuses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

LOCK TABLES `todo_statuses` WRITE;
/*!40000 ALTER TABLE `todo_statuses` DISABLE KEYS */;

INSERT INTO `todo_statuses` (`id`, `name`)
VALUES
	(1,'To Do'),
	(2,'Done');

/*!40000 ALTER TABLE `todo_statuses` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table todos
# ------------------------------------------------------------

DROP TABLE IF EXISTS `todos`;

CREATE TABLE `todos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `status_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp9j5rnjx3ij26e9apnihvvbwi` (`status_id`),
  KEY `FK9605g76a1dggbvs18f2r80gvu` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

LOCK TABLES `todos` WRITE;
/*!40000 ALTER TABLE `todos` DISABLE KEYS */;

INSERT INTO `todos` (`id`, `content`, `status_id`, `user_id`)
VALUES
	(1,'Wash the car',1,1),
	(2,'Wash the van',2,1),
	(16,'dfsf',2,13),
	(6,'ffdsf',2,13),
	(9,'dsffs',2,13),
	(11,'dasdsa',2,13),
	(12,'fdsfds',2,13),
	(13,'dsfdfsdfs',1,13),
	(15,'dfgfdgfdg',1,13),
	(17,'adsasd',1,13),
	(19,'dffdsdsffsd',1,13),
	(20,'afdsfdafdas',1,13),
	(21,'fdsdfs',1,13);

/*!40000 ALTER TABLE `todos` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table users
# ------------------------------------------------------------

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;

INSERT INTO `users` (`id`, `username`)
VALUES
	(1,'plamen9887'),
	(2,'georgi'),
	(3,'viara'),
	(7,'georgi123'),
	(9,'dsfsd'),
	(10,'ffdfg'),
	(11,'dfggdf'),
	(12,''),
	(13,'mario'),
	(14,'kohn'),
	(15,'123');

/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
