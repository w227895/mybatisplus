/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.13-log : Database - mp
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mp` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `mp`;

/*Data for the table `user` */

insert  into `user`(`id`,`name`,`age`,`email`,`create_time`,`update_time`,`version`) values (1,'Jone',24,'test1@baomidou.com',NULL,'2020-11-17 21:22:36',NULL),(2,'Jack',20,'test2@baomidou.com',NULL,NULL,NULL),(3,'Tom',28,'test3@baomidou.com',NULL,NULL,NULL),(4,'Sandy',21,'test4@baomidou.com',NULL,NULL,NULL),(5,'Billie',24,'test5@baomidou.com',NULL,NULL,NULL),(1328674484424626178,'Helen',23,'249183617@qq.com',NULL,NULL,NULL),(1328674484424626179,'Helen',23,'249183617@qq.com','2020-11-17 21:12:47','2020-11-17 21:12:47',NULL),(1328674484424626180,'Helen',24,'249183617@qq.com','2020-11-17 21:27:03','2020-11-17 21:30:44',3);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
