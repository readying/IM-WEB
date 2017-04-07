/*
SQLyog Ultimate v9.62 
MySQL - 5.1.73-community : Database - cmd
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`cmd` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `cmd`;

/*Table structure for table `logging_event` */

DROP TABLE IF EXISTS `logging_event`;

CREATE TABLE `logging_event` (
  `timestmp` bigint(20) NOT NULL,
  `formatted_message` text COLLATE utf8_bin NOT NULL,
  `logger_name` varchar(254) COLLATE utf8_bin NOT NULL,
  `level_string` varchar(254) COLLATE utf8_bin NOT NULL,
  `thread_name` varchar(254) COLLATE utf8_bin DEFAULT NULL,
  `reference_flag` smallint(6) DEFAULT NULL,
  `arg0` varchar(254) COLLATE utf8_bin DEFAULT NULL,
  `arg1` varchar(254) COLLATE utf8_bin DEFAULT NULL,
  `arg2` varchar(254) COLLATE utf8_bin DEFAULT NULL,
  `arg3` varchar(254) COLLATE utf8_bin DEFAULT NULL,
  `caller_filename` varchar(254) COLLATE utf8_bin NOT NULL,
  `caller_class` varchar(254) COLLATE utf8_bin NOT NULL,
  `caller_method` varchar(254) COLLATE utf8_bin NOT NULL,
  `caller_line` char(4) COLLATE utf8_bin NOT NULL,
  `event_id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `logging_event` */

/*Table structure for table `logging_event_exception` */

DROP TABLE IF EXISTS `logging_event_exception`;

CREATE TABLE `logging_event_exception` (
  `event_id` bigint(20) NOT NULL,
  `i` smallint(6) NOT NULL,
  `trace_line` varchar(254) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`event_id`,`i`),
  CONSTRAINT `logging_event_exception_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `logging_event` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `logging_event_exception` */

/*Table structure for table `logging_event_property` */

DROP TABLE IF EXISTS `logging_event_property`;

CREATE TABLE `logging_event_property` (
  `event_id` bigint(20) NOT NULL,
  `mapped_key` varchar(254) COLLATE utf8_bin NOT NULL,
  `mapped_value` text COLLATE utf8_bin,
  PRIMARY KEY (`event_id`,`mapped_key`),
  CONSTRAINT `logging_event_property_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `logging_event` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `logging_event_property` */

insert  into `logging_event_property`(`event_id`,`mapped_key`,`mapped_value`) values (1,'HOSTNAME','dell'),(2,'HOSTNAME','dell'),(3,'HOSTNAME','dell'),(4,'HOSTNAME','dell'),(5,'HOSTNAME','dell'),(6,'HOSTNAME','dell'),(7,'HOSTNAME','dell'),(8,'HOSTNAME','dell'),(9,'HOSTNAME','dell'),(10,'HOSTNAME','dell'),(11,'HOSTNAME','dell'),(12,'HOSTNAME','dell'),(13,'HOSTNAME','dell'),(14,'HOSTNAME','dell'),(15,'HOSTNAME','dell'),(16,'HOSTNAME','dell'),(17,'HOSTNAME','dell'),(18,'HOSTNAME','dell'),(19,'HOSTNAME','dell'),(20,'HOSTNAME','dell'),(21,'HOSTNAME','dell'),(22,'HOSTNAME','dell'),(23,'HOSTNAME','dell'),(24,'HOSTNAME','dell'),(25,'HOSTNAME','dell'),(26,'HOSTNAME','dell'),(27,'HOSTNAME','dell'),(28,'HOSTNAME','dell'),(29,'HOSTNAME','dell'),(30,'HOSTNAME','dell'),(31,'HOSTNAME','dell'),(32,'HOSTNAME','dell'),(33,'HOSTNAME','dell'),(34,'HOSTNAME','dell'),(35,'HOSTNAME','dell'),(36,'HOSTNAME','dell'),(37,'HOSTNAME','dell'),(38,'HOSTNAME','dell'),(39,'HOSTNAME','dell'),(40,'HOSTNAME','dell'),(41,'HOSTNAME','dell'),(42,'HOSTNAME','dell'),(43,'HOSTNAME','dell'),(44,'HOSTNAME','dell'),(45,'HOSTNAME','dell'),(46,'HOSTNAME','dell'),(47,'HOSTNAME','dell'),(48,'HOSTNAME','dell'),(49,'HOSTNAME','dell'),(50,'HOSTNAME','dell'),(51,'HOSTNAME','dell'),(52,'HOSTNAME','dell'),(53,'HOSTNAME','dell'),(54,'HOSTNAME','dell'),(55,'HOSTNAME','dell'),(56,'HOSTNAME','dell'),(57,'HOSTNAME','dell'),(58,'HOSTNAME','dell'),(59,'HOSTNAME','dell'),(60,'HOSTNAME','dell'),(61,'HOSTNAME','dell'),(62,'HOSTNAME','dell'),(63,'HOSTNAME','dell'),(64,'HOSTNAME','dell'),(65,'HOSTNAME','dell'),(66,'HOSTNAME','dell'),(67,'HOSTNAME','dell'),(68,'HOSTNAME','dell'),(69,'HOSTNAME','dell'),(70,'HOSTNAME','dell'),(71,'HOSTNAME','dell'),(72,'HOSTNAME','dell'),(73,'HOSTNAME','dell'),(74,'HOSTNAME','dell'),(75,'HOSTNAME','dell'),(76,'HOSTNAME','dell'),(77,'HOSTNAME','dell'),(78,'HOSTNAME','dell'),(79,'HOSTNAME','dell'),(80,'HOSTNAME','dell'),(81,'HOSTNAME','dell'),(82,'HOSTNAME','dell'),(83,'HOSTNAME','dell'),(84,'HOSTNAME','dell'),(85,'HOSTNAME','dell'),(86,'HOSTNAME','dell'),(87,'HOSTNAME','dell'),(88,'HOSTNAME','dell'),(89,'HOSTNAME','dell'),(90,'HOSTNAME','dell'),(91,'HOSTNAME','dell'),(92,'HOSTNAME','dell'),(93,'HOSTNAME','dell'),(94,'HOSTNAME','dell'),(95,'HOSTNAME','dell'),(96,'HOSTNAME','dell'),(97,'HOSTNAME','dell'),(98,'HOSTNAME','dell'),(99,'HOSTNAME','dell'),(100,'HOSTNAME','dell'),(101,'HOSTNAME','dell'),(102,'HOSTNAME','dell'),(103,'HOSTNAME','dell'),(104,'HOSTNAME','dell'),(105,'HOSTNAME','dell'),(106,'HOSTNAME','dell'),(107,'HOSTNAME','dell'),(108,'HOSTNAME','dell'),(109,'HOSTNAME','dell'),(110,'HOSTNAME','dell'),(111,'HOSTNAME','dell'),(112,'HOSTNAME','dell'),(113,'HOSTNAME','dell'),(114,'HOSTNAME','dell'),(115,'HOSTNAME','dell'),(116,'HOSTNAME','dell'),(117,'HOSTNAME','dell'),(118,'HOSTNAME','dell'),(119,'HOSTNAME','dell'),(120,'HOSTNAME','dell'),(121,'HOSTNAME','dell'),(122,'HOSTNAME','dell'),(123,'HOSTNAME','dell'),(124,'HOSTNAME','dell'),(125,'HOSTNAME','dell'),(126,'HOSTNAME','dell'),(127,'HOSTNAME','dell'),(128,'HOSTNAME','dell'),(129,'HOSTNAME','dell'),(130,'HOSTNAME','dell'),(131,'HOSTNAME','dell'),(132,'HOSTNAME','dell'),(133,'HOSTNAME','dell'),(134,'HOSTNAME','dell'),(135,'HOSTNAME','dell'),(136,'HOSTNAME','dell'),(137,'HOSTNAME','dell');

/*Table structure for table `sys_account` */

DROP TABLE IF EXISTS `sys_account`;

CREATE TABLE `sys_account` (
  `ID` varchar(50) NOT NULL COMMENT '账户ID',
  `USERNAME` varchar(50) DEFAULT NULL COMMENT '名称',
  `PASSWORD` varchar(50) DEFAULT NULL COMMENT '密码',
  `PLAT` varchar(50) DEFAULT NULL COMMENT '登录平台',
  `PASSWORDPROMPT` varchar(200) DEFAULT NULL COMMENT '密码提示',
  `SALT` varchar(50) DEFAULT NULL COMMENT '防止暴力破解密码',
  `CREATETIME` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `UPDATETIME` varchar(50) DEFAULT NULL COMMENT '变更时间',
  `IFUSE` varchar(20) DEFAULT NULL COMMENT '是否使用',
  `ORDERNUM` varchar(50) DEFAULT NULL COMMENT '排序编号',
  `TOKEN` varchar(50) DEFAULT NULL COMMENT 'TOKEN'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_account` */

insert  into `sys_account`(`ID`,`USERNAME`,`PASSWORD`,`PLAT`,`PASSWORDPROMPT`,`SALT`,`CREATETIME`,`UPDATETIME`,`IFUSE`,`ORDERNUM`,`TOKEN`) values ('1','admin','b4f224455bf6969f42028d7fea91d21e',NULL,NULL,'123456',NULL,NULL,NULL,'0',NULL),('d01dbcf3-0822-11e7-befe-7c6896b5c9d2','Ada_account','b4f224455bf6969f42028d7fea91d21e','PC','xxx','123456','2017-03-14 03:25:28','2017-03-14 03:25:28','N','1',NULL),('e7947dbc-0822-11e7-befe-7c6896b5c9d2','Mike_account','b4f224455bf6969f42028d7fea91d21e','PC','111111MD5','123456','2017-03-14 03:26:08','2017-03-14 03:26:08','N','2',NULL),('f1e592b0-0822-11e7-befe-7c6896b5c9d2','Lion_account','b4f224455bf6969f42028d7fea91d21e','PC','111111MD5','123456','2017-03-14 03:26:25','2017-03-14 03:26:25','N','3',NULL),('f95730d4-0822-11e7-befe-7c6896b5c9d2','admin_account','b4f224455bf6969f42028d7fea91d21e','PC','111111MD5','123456','2017-03-14 03:26:37','2017-03-14 03:26:37','N','4',NULL),('aa519481-0ab8-11e7-9114-c51f4c21be57','my_salt_test','4704ae057a683b8e09b7bfb2708c7125',NULL,'mima11111','898474','2017-03-17 10:23:12','2017-03-17 10:23:12','N','5',NULL);

/*Table structure for table `sys_account_role` */

DROP TABLE IF EXISTS `sys_account_role`;

CREATE TABLE `sys_account_role` (
  `ID` varchar(50) NOT NULL COMMENT 'id',
  `ACCOUNTID` varchar(50) DEFAULT NULL COMMENT '账户id',
  `ROLEID` varchar(50) DEFAULT NULL COMMENT '角色id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_account_role` */

insert  into `sys_account_role`(`ID`,`ACCOUNTID`,`ROLEID`) values ('1','1','1'),('d28e985f-09e3-11e7-9114-c51f4c21be57','f95730d4-0822-11e7-befe-7c6896b5c9d2','1c338a08-081c-11e7-befe-7c6896b5c9d2'),('ed24f003-09e3-11e7-9114-c51f4c21be57','f1e592b0-0822-11e7-befe-7c6896b5c9d2','01343d8f-081c-11e7-befe-7c6896b5c9d2');

/*Table structure for table `sys_account_user` */

DROP TABLE IF EXISTS `sys_account_user`;

CREATE TABLE `sys_account_user` (
  `ID` varchar(50) NOT NULL COMMENT 'id',
  `USERID` varchar(50) DEFAULT NULL COMMENT '用户ID',
  `ACCOUNTID` varchar(50) DEFAULT NULL COMMENT '账户ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_account_user` */

insert  into `sys_account_user`(`ID`,`USERID`,`ACCOUNTID`) values ('1','1','1'),('e4c7b942-09e2-11e7-9114-c51f4c21be57','643895b1-09e2-11e7-9114-c51f4c21be57','f95730d4-0822-11e7-befe-7c6896b5c9d2'),('f84b4686-09e2-11e7-9114-c51f4c21be57','a07a2957-0812-11e7-befe-7c6896b5c9d2','f1e592b0-0822-11e7-befe-7c6896b5c9d2'),('2','643895b1-09e2-11e7-9114-c51f4c21be57','f1e592b0-0822-11e7-befe-7c6896b5c9d2');

/*Table structure for table `sys_authority` */

DROP TABLE IF EXISTS `sys_authority`;

CREATE TABLE `sys_authority` (
  `ID` varchar(50) NOT NULL COMMENT '权限id',
  `AUTHORITYNAME` varchar(50) DEFAULT NULL COMMENT '权限名称',
  `DESCRIPTION` varchar(200) DEFAULT NULL COMMENT '权限描述',
  `CREATETIME` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `UPDATETIME` varchar(50) DEFAULT NULL COMMENT '变更时间',
  `IFUSE` varchar(20) DEFAULT NULL COMMENT '是否使用',
  `ORDERNUM` varchar(50) DEFAULT NULL COMMENT '排序编号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_authority` */

insert  into `sys_authority`(`ID`,`AUTHORITYNAME`,`DESCRIPTION`,`CREATETIME`,`UPDATETIME`,`IFUSE`,`ORDERNUM`) values ('1','获取所有用户权限sdf','获取所有用户权限','2017','2017-03-30 16:06:51','N','1'),('ea43ee19-0821-11e7-befe-7c6896b5c9d2','userinfo_authority','userinfo_manager','2017-03-14 03:19:03','2017-03-14 03:19:03','N','2'),('f61379ec-0821-11e7-befe-7c6896b5c9d2','role_authority','role_manager','2017-03-14 03:19:22','2017-03-14 03:19:22','N','3'),('fd47205a-0821-11e7-befe-7c6896b5c9d2','account_authority','account_manager','2017-03-14 03:19:35','2017-03-14 03:19:35','N','4'),('069be2dc-0822-11e7-befe-7c6896b5c9d2','authority_authority','authority_manager','2017-03-14 03:19:50','2017-03-14 03:19:50','N','5'),('0fee242a-0822-11e7-befe-7c6896b5c9d2','resource_authority','resource_manager','2017-03-14 03:20:06','2017-03-14 03:20:06','N','6'),('1a9affaa-0822-11e7-befe-7c6896b5c9d2','department_authority','department_manager','2017-03-14 03:20:24','2017-03-14 03:20:24','N','7'),('6a54690f-151b-11e7-8bf3-5863ea6b5879','asdf','asfd','2017-03-30 15:35:22','2017-03-30 15:35:22','N','8');

/*Table structure for table `sys_department` */

DROP TABLE IF EXISTS `sys_department`;

CREATE TABLE `sys_department` (
  `ID` varchar(50) NOT NULL COMMENT '部门id',
  `DEPARTMENTNAME` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `LEADER` varchar(50) DEFAULT NULL COMMENT '部门领导',
  `ADMINISTRATOR` varchar(50) DEFAULT NULL COMMENT '部门管理员',
  `GROUPS` varchar(50) DEFAULT NULL COMMENT '部门群',
  `CREATETIME` varchar(50) DEFAULT NULL,
  `UPDATETIME` varchar(50) DEFAULT NULL,
  `IFUSE` varchar(20) DEFAULT NULL,
  `ORDERNUM` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_department` */

insert  into `sys_department`(`ID`,`DEPARTMENTNAME`,`LEADER`,`ADMINISTRATOR`,`GROUPS`,`CREATETIME`,`UPDATETIME`,`IFUSE`,`ORDERNUM`) values ('1','研发部','11','星星','1','2017','2017','1','1'),('43d7db05-0821-11e7-befe-7c6896b5c9d2','Ministry of Personnel','Mr.chen','Mrs.liu','Ministry','2017-03-14 03:14:23','2017-03-14 03:14:23','N','2'),('600d9b09-0821-11e7-befe-7c6896b5c9d2','Finance Department','Mr.zhang','Mrs.liu','Finance','2017-03-14 03:15:11','2017-03-14 03:15:11','N','3'),('7a5f7cfc-0821-11e7-befe-7c6896b5c9d2','Security Department','Mr.sun','Mrs.liu','Security','2017-03-14 03:15:55','2017-03-14 03:15:55','N','4'),('8d2deafa-0821-11e7-befe-7c6896b5c9d2','Research Department','Mr.wan','Mrs.liu','Research','2017-03-14 03:16:27','2017-03-14 03:16:27','N','5'),('9bc513e4-0821-11e7-befe-7c6896b5c9d2','Audit department','Mr.yang','Mrs.liu','Audit','2017-03-14 03:16:51','2017-03-14 03:16:51','N','6'),('90b5e399-1419-11e7-8bf3-5863ea6b5879','航天长峰','Mr.shi','Mrs.gao','1','2017-03-14 03:16:51','2017-03-14 03:16:51','Y','7'),('49afd075-141a-11e7-8bf3-5863ea6b5879','人事部','Mr.shi','Mrs.gao','1','2017-03-14 03:16:51','2017-03-14 03:16:51','Y','8'),('4d850cb7-141a-11e7-8bf3-5863ea6b5879','市场部','Mr.shi','Mrs.gao','1','2017-03-14 03:16:51','2017-03-14 03:16:51','Y','9'),('50971fae-141a-11e7-8bf3-5863ea6b5879','指挥云组','Mrs.shan','Mrs.shan','1','2017-03-14 03:16:51','2017-03-14 03:16:51','Y','10'),('54062387-141a-11e7-8bf3-5863ea6b5879','人事组','Mr.chen','Mr.chen','1','2017-03-14 03:16:51','2017-03-14 03:16:51','Y','11'),('576c5e58-141a-11e7-8bf3-5863ea6b5879','视频大数据','Mr.liu','Mr.liu','1','2017-03-14 03:16:51',NULL,NULL,NULL);

/*Table structure for table `sys_department_department` */

DROP TABLE IF EXISTS `sys_department_department`;

CREATE TABLE `sys_department_department` (
  `ID` varchar(50) NOT NULL COMMENT 'id',
  `PARENTID` varchar(50) DEFAULT NULL COMMENT '上级部门ID',
  `CHILDRENID` varchar(50) DEFAULT NULL COMMENT '下级部门ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_department_department` */

insert  into `sys_department_department`(`ID`,`PARENTID`,`CHILDRENID`) values ('e4f75bc7-141a-11e7-8bf3-5863ea6b5879','90b5e399-1419-11e7-8bf3-5863ea6b5879','49afd075-141a-11e7-8bf3-5863ea6b5879'),('e891f46b-141a-11e7-8bf3-5863ea6b5879','90b5e399-1419-11e7-8bf3-5863ea6b5879','4d850cb7-141a-11e7-8bf3-5863ea6b5879'),('eb5ab877-141a-11e7-8bf3-5863ea6b5879','90b5e399-1419-11e7-8bf3-5863ea6b5879','1'),('ee4d0671-141a-11e7-8bf3-5863ea6b5879','1','50971fae-141a-11e7-8bf3-5863ea6b5879'),('f0f15fc0-141a-11e7-8bf3-5863ea6b5879','1','576c5e58-141a-11e7-8bf3-5863ea6b5879'),('74e84bd5-141d-11e7-8bf3-5863ea6b5879','49afd075-141a-11e7-8bf3-5863ea6b5879','54062387-141a-11e7-8bf3-5863ea6b5879');

/*Table structure for table `sys_resource` */

DROP TABLE IF EXISTS `sys_resource`;

CREATE TABLE `sys_resource` (
  `ID` varchar(50) NOT NULL COMMENT '资源ID',
  `RESOURCENAME` varchar(50) DEFAULT NULL COMMENT '资源名称',
  `DESCRIPTION` varchar(200) DEFAULT NULL COMMENT '资源描述',
  `TYPE` varchar(50) DEFAULT NULL COMMENT '资源类型',
  `PARENTID` varchar(50) DEFAULT NULL COMMENT '父节点ID',
  `PARENTNAME` varchar(50) DEFAULT NULL COMMENT '父节点名称',
  `URL` varchar(50) DEFAULT NULL COMMENT '访问路径',
  `APPNAME` varchar(50) DEFAULT NULL COMMENT '客户端类型',
  `LEVEL` varchar(50) DEFAULT NULL COMMENT '等级',
  `ISMENULEAF` varchar(50) DEFAULT NULL COMMENT '是否为菜单权限',
  `CREATETIME` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `UPDATETIME` varchar(50) DEFAULT NULL COMMENT '更新时间',
  `IFUSE` varchar(20) DEFAULT NULL COMMENT '是否使用',
  `ORDERNUM` varchar(50) DEFAULT NULL COMMENT '排序编号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_resource` */

insert  into `sys_resource`(`ID`,`RESOURCENAME`,`DESCRIPTION`,`TYPE`,`PARENTID`,`PARENTNAME`,`URL`,`APPNAME`,`LEVEL`,`ISMENULEAF`,`CREATETIME`,`UPDATETIME`,`IFUSE`,`ORDERNUM`) values ('1','ACCOUNT_ALL','查询所有用户','webiukrt','root','root','/account/all',NULL,'1','y','2017-01-13 12:12:12','2017-03-30 10:21:47','N','1'),('524f6e85-081d-11e7-befe-7c6896b5c9d2','userinfo_resource','userinfo_show','data_privilege','0','root','/userinfo/userinfos','PC','2','N','2017-03-14 02:46:10','2017-03-29 17:03:33','N','2'),('684c8800-081d-11e7-befe-7c6896b5c9d2','role_resource','role_show','data_privilege','0','root','/role/roles','PC','2','N','2017-03-14 02:46:47','2017-03-14 02:46:47','N','3'),('110fb45c-0820-11e7-befe-7c6896b5c9d2','department_resource','department_show','data_privilege','0','root','/department/departments','PC','2','N','2017-03-14 03:05:49','2017-03-14 03:05:49','N','4'),('2dcac603-0820-11e7-befe-7c6896b5c9d2','authority_resource','authority_show','data_privilege','0','root','/authority/aothorities','PC','2','N','2017-03-14 03:06:37','2017-03-14 03:06:37','N','5'),('3eacc3b4-0820-11e7-befe-7c6896b5c9d2','resource_resource','resources_show','data_privilege','0','root','/resource/resources','PC','2','N','2017-03-14 03:07:05','2017-03-14 03:07:05','N','6'),('acc0fef1-0820-11e7-befe-7c6896b5c9d2','account_resource','account_show','data_privilege','0','root','/account/accounts','PC','2','N','2017-03-14 03:10:10','2017-03-14 03:10:10','N','7'),('dbab1a3b-0a23-11e7-9114-c51f4c21be57','userinfo_resource2','userinfo_resource2','data_privilege','0','root','/userinfo/add','PC','2','N','2017-03-14 03:10:10','2017-03-14 03:10:10','N','8'),('8d875f75-14e6-11e7-8bf3-5863ea6b5879','wer','wer','wer',NULL,NULL,NULL,NULL,NULL,NULL,'2017-03-30 09:16:57','2017-03-30 09:16:57','N','9'),('ec2ac855-151a-11e7-8bf3-5863ea6b5879','sdf','asdf',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2017-03-30 15:31:50','2017-03-30 15:31:50','N','10');

/*Table structure for table `sys_resource_authority` */

DROP TABLE IF EXISTS `sys_resource_authority`;

CREATE TABLE `sys_resource_authority` (
  `ID` varchar(50) NOT NULL COMMENT 'id',
  `AUTHORITYID` varchar(50) DEFAULT NULL COMMENT '权限ID',
  `RESOURCEID` varchar(50) DEFAULT NULL COMMENT '资源ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_resource_authority` */

insert  into `sys_resource_authority`(`ID`,`AUTHORITYID`,`RESOURCEID`) values ('1','1','1'),('f6ce5aff-09e4-11e7-9114-c51f4c21be57','ea43ee19-0821-11e7-befe-7c6896b5c9d2','524f6e85-081d-11e7-befe-7c6896b5c9d2'),('3014a5e9-09e5-11e7-9114-c51f4c21be57','f61379ec-0821-11e7-befe-7c6896b5c9d2','684c8800-081d-11e7-befe-7c6896b5c9d2'),('420ad408-0a24-11e7-9114-c51f4c21be57','ea43ee19-0821-11e7-befe-7c6896b5c9d2','dbab1a3b-0a23-11e7-9114-c51f4c21be57'),('164c872d-1a6a-11e7-a9c1-2842d35d579d','1','524f6e85-081d-11e7-befe-7c6896b5c9d2'),('1be0b6fa-1a6a-11e7-a9c1-2842d35d579d','1','1'),('208f436e-1a6a-11e7-a9c1-2842d35d579d','1','3'),('23bfa4d1-1a6a-11e7-a9c1-2842d35d579d','1','56'),('269f131f-1a6a-11e7-a9c1-2842d35d579d','1','56'),('2ee56d82-1a6a-11e7-a9c1-2842d35d579d','1','56'),('2ee56f29-1a6a-11e7-a9c1-2842d35d579d','1','34'),('314180e6-1a6a-11e7-a9c1-2842d35d579d','1','56'),('33a1f180-1a6a-11e7-a9c1-2842d35d579d','1','56'),('33a1f336-1a6a-11e7-a9c1-2842d35d579d','1','90');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `ID` varchar(50) NOT NULL COMMENT '角色id',
  `ROLENAME` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `DESCRIPTION` varchar(200) DEFAULT NULL COMMENT '描述',
  `CREATETIME` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `UPDATETIME` varchar(50) DEFAULT NULL COMMENT '变更时间',
  `IFUSE` varchar(20) DEFAULT NULL COMMENT '是否使用',
  `ORDERNUM` varchar(50) DEFAULT NULL COMMENT '排序编号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

insert  into `sys_role`(`ID`,`ROLENAME`,`DESCRIPTION`,`CREATETIME`,`UPDATETIME`,`IFUSE`,`ORDERNUM`) values ('1','超级管理员','拥有所有权限','2017-01-13 12:12:12','2017-03-14 02:39:10','N','1'),('01343d8f-081c-11e7-befe-7c6896b5c9d2','employee','having some privilege','2017-03-14 02:36:44','2017-03-14 02:36:44','N','2'),('1c338a08-081c-11e7-befe-7c6896b5c9d2','admin','having much privilege','2017-03-14 02:37:29','2017-03-14 02:37:29','N','3');

/*Table structure for table `sys_role_authority` */

DROP TABLE IF EXISTS `sys_role_authority`;

CREATE TABLE `sys_role_authority` (
  `ID` varchar(50) DEFAULT NULL COMMENT 'id',
  `ROLEID` varchar(50) DEFAULT NULL COMMENT '角色id',
  `AUTHORITYID` varchar(50) DEFAULT NULL COMMENT '权限id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_role_authority` */

insert  into `sys_role_authority`(`ID`,`ROLEID`,`AUTHORITYID`) values ('1','1','1'),('876fb0e5-09e4-11e7-9114-c51f4c21be57','1c338a08-081c-11e7-befe-7c6896b5c9d2','f61379ec-0821-11e7-befe-7c6896b5c9d2'),('a22fa0ab-09e4-11e7-9114-c51f4c21be57','01343d8f-081c-11e7-befe-7c6896b5c9d2','ea43ee19-0821-11e7-befe-7c6896b5c9d2');

/*Table structure for table `sys_user_department` */

DROP TABLE IF EXISTS `sys_user_department`;

CREATE TABLE `sys_user_department` (
  `ID` varchar(50) NOT NULL COMMENT 'id',
  `USERID` varchar(50) DEFAULT NULL COMMENT '用户id',
  `DEPARTMENTID` varbinary(50) DEFAULT NULL COMMENT '部门id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_user_department` */

insert  into `sys_user_department`(`ID`,`USERID`,`DEPARTMENTID`) values ('7b3158bf-141d-11e7-8bf3-5863ea6b5879','a07a2957-0812-11e7-befe-7c6896b5c9d2','50971fae-141a-11e7-8bf3-5863ea6b5879'),('7dd0ec62-141d-11e7-8bf3-5863ea6b5879','b3d360ba-0812-11e7-befe-7c6896b5c9d2','50971fae-141a-11e7-8bf3-5863ea6b5879'),('80ad2e28-141d-11e7-8bf3-5863ea6b5879','c5449c88-0812-11e7-befe-7c6896b5c9d2','576c5e58-141a-11e7-8bf3-5863ea6b5879'),('838d1716-141d-11e7-8bf3-5863ea6b5879','643895b1-09e2-11e7-9114-c51f4c21be57','576c5e58-141a-11e7-8bf3-5863ea6b5879'),('ca9b4a6c-1689-11e7-8bf3-5863ea6b5879','576c5e58-141a-11e7-8bf3-5863ea6b5879','a9753ce3-13a2-11e7-8bf3-5863ea6b5879'),('ca9b4c76-1689-11e7-8bf3-5863ea6b5879','576c5e58-141a-11e7-8bf3-5863ea6b5879','f4135b3a-0dda-11e7-91d5-6f4819a5f2cb');

/*Table structure for table `sys_user_user` */

DROP TABLE IF EXISTS `sys_user_user`;

CREATE TABLE `sys_user_user` (
  `ID` varchar(50) NOT NULL COMMENT 'ID',
  `USERIDLEFT` varchar(50) DEFAULT NULL COMMENT '好友id1',
  `USERIDRIGHT` varchar(50) DEFAULT NULL COMMENT '好友id2',
  `FRIENDSHIPTYPE` varchar(200) DEFAULT NULL COMMENT '关系类型'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_user_user` */

insert  into `sys_user_user`(`ID`,`USERIDLEFT`,`USERIDRIGHT`,`FRIENDSHIPTYPE`) values ('f81b5540-16b3-11e7-8bf3-5863ea6b5879','a07a2957-0812-11e7-befe-7c6896b5c9d2','b3d360ba-0812-11e7-befe-7c6896b5c9d2','friend'),('4f53a4df-16b4-11e7-8bf3-5863ea6b5879','a07a2957-0812-11e7-befe-7c6896b5c9d2','643895b1-09e2-11e7-9114-c51f4c21be57','friend'),('004c1231-16b4-11e7-8bf3-5863ea6b5879','a07a2957-0812-11e7-befe-7c6896b5c9d2','c5449c88-0812-11e7-befe-7c6896b5c9d2','black');

/*Table structure for table `sys_userinfo` */

DROP TABLE IF EXISTS `sys_userinfo`;

CREATE TABLE `sys_userinfo` (
  `ID` varchar(50) NOT NULL COMMENT '用户id',
  `REALNAME` varchar(50) NOT NULL COMMENT '用户姓名',
  `GENDER` varchar(50) NOT NULL COMMENT '性别',
  `NATION` varchar(50) DEFAULT NULL COMMENT '民族',
  `NATIVEPLACE` varchar(200) DEFAULT NULL COMMENT '籍贯',
  `BIRTHDATE` varchar(50) DEFAULT NULL COMMENT '出生日期',
  `POLITICALSTATUS` varchar(50) DEFAULT NULL COMMENT '政治面貌',
  `MARITALSTATUS` varbinary(50) DEFAULT NULL COMMENT '婚姻状况',
  `NICKNAME` varchar(50) DEFAULT NULL COMMENT '昵称',
  `PICTURE` varchar(50) DEFAULT NULL COMMENT '头像路径',
  `PHONE` varchar(50) DEFAULT NULL COMMENT '电话号码(唯一)',
  `MAIL` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `ORG` varchar(50) DEFAULT NULL COMMENT '部门',
  `POLICENUM` varchar(50) DEFAULT NULL COMMENT '警号',
  `IDENTITYCODE` varchar(50) DEFAULT NULL COMMENT '身份证号',
  `QR` varchar(200) DEFAULT NULL COMMENT '二维码',
  `QQ` varchar(50) DEFAULT NULL COMMENT 'qq号',
  `WECHAT` varchar(50) DEFAULT NULL COMMENT '微信号',
  `JOBCODE` varchar(50) DEFAULT NULL COMMENT '职务代码',
  `STATION` varchar(50) DEFAULT NULL COMMENT '职称',
  `AUTOGRAPH` varchar(200) DEFAULT NULL COMMENT '个性签名',
  `HOMEADDRESS` varchar(200) DEFAULT NULL COMMENT '家庭住址',
  `OFFICEADDRESS` varchar(200) DEFAULT NULL COMMENT '办公地址',
  `OTHERADDRESS` varchar(200) DEFAULT NULL COMMENT '其他地址',
  `OFFICETELEPHONE` varchar(50) DEFAULT NULL COMMENT '办公电话',
  `OTHERTELEPHONE` varchar(50) DEFAULT NULL COMMENT '其他联系电话',
  `IFHIDENUM` varchar(20) DEFAULT NULL COMMENT '开启号码隐藏',
  `CREATETIME` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `UPDATETIME` varchar(50) DEFAULT NULL COMMENT '变更时间',
  `IFUSE` varchar(20) DEFAULT NULL COMMENT '是否使用',
  `ORDERNUM` varchar(50) DEFAULT NULL COMMENT '排序编号',
  UNIQUE KEY `PHONE` (`PHONE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_userinfo` */

insert  into `sys_userinfo`(`ID`,`REALNAME`,`GENDER`,`NATION`,`NATIVEPLACE`,`BIRTHDATE`,`POLITICALSTATUS`,`MARITALSTATUS`,`NICKNAME`,`PICTURE`,`PHONE`,`MAIL`,`ORG`,`POLICENUM`,`IDENTITYCODE`,`QR`,`QQ`,`WECHAT`,`JOBCODE`,`STATION`,`AUTOGRAPH`,`HOMEADDRESS`,`OFFICEADDRESS`,`OTHERADDRESS`,`OFFICETELEPHONE`,`OTHERTELEPHONE`,`IFHIDENUM`,`CREATETIME`,`UPDATETIME`,`IFUSE`,`ORDERNUM`) values ('a07a2957-0812-11e7-befe-7c6896b5c9d2','Lion士大夫asdf','mansdfgsadf','Chinese','beijing','1992','000000','N','lili','/home/ccf/','12311','13326581741@qq.com','China','000000','130683251124367','chae','133265812','weChat01','10201','genderman','life is impossable','China','UK','US','0312-3450211','0312-854214','N','2017-03-14 01:29:36','2017-03-31 17:49:13','N','2'),('b3d360ba-0812-11e7-befe-7c6896b5c9d2','Adazhangboa','woman','Chinese','beijing','1992','000000','N','lili','/home/ccf/','11','13326581741@qq.com','China','000000','130683251124367','chae','133265812','weChat01','10201','genderman','life is impossable','China','UK','US','0312-3450211','0312-854214','N','2017-03-14 01:30:09','2017-03-29 10:10:29','N','3'),('c5449c88-0812-11e7-befe-7c6896b5c9d2','Sherry','woman','Chinese','beijing','1992','000000','N','lili','/home/ccf/','1111','13326581741@qq.com','China','000000','130683251124367','chae','133265812','weChat01','10201','genderman','life is impossable','China','UK','US','0312-3450211','0312-854214','N','2017-03-14 01:30:38','2017-03-14 01:30:38','N','4'),('643895b1-09e2-11e7-9114-c51f4c21be57','adminUser','M','Chinese','tianjin','1994','000000','Y','boss','/home/admin/','1920183491','2210639195@qq.com','China','000000','1309281037',NULL,NULL,'weChat02','182917','laddy','hello sunshine','US','UK','GAN','18291739','09103819',NULL,'2017-03-16 08:49:20','2017-03-16 08:49:20','N','8'),('817c61af-0ad7-11e7-9114-c51f4c21be57','werrsdhg','wersdffgh',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2017-03-17 14:03:58','2017-03-27 15:29:14','N','9'),('e33fbeb9-0ad9-11e7-9114-c51f4c21be57','dfggf','sdfgfgh',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2017-03-17 14:21:01','2017-03-27 17:00:26','N','10'),('5b4a16c2-0ada-11e7-9114-c51f4c21be57','士大夫',' 随风倒',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2017-03-17 14:24:22','2017-03-27 17:00:32','N','11'),('15a123b2-0d4d-11e7-8e93-bb74150e04eb','fg','dfg',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2017-03-20 17:10:39','2017-03-20 17:10:39','N','12'),('3a7ce198-0d4d-11e7-8e93-bb74150e04eb','dfg','sdf',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2017-03-20 17:11:40','2017-03-20 17:11:40','N','13'),('44a30fd2-0d4d-11e7-8e93-bb74150e04eb',';l\'','l;\'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2017-03-20 17:11:57','2017-03-27 17:00:36','N','14'),('4fbc8ea5-0d4d-11e7-8e93-bb74150e04eb','hj','jhk',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2017-03-20 17:12:16','2017-03-20 17:12:16','N','15'),('82c2b55b-0d4d-11e7-8e93-bb74150e04eb','ere','r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2017-03-20 17:13:42','2017-03-20 17:13:42','N','16'),('d42051b8-0dde-11e7-8e93-bb74150e04eb','888888888','88888888',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2017-03-21 10:34:10','2017-03-21 10:34:10','N','17'),('892f2906-0de4-11e7-8e93-bb74150e04eb','asdf','asdf',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2017-03-21 11:14:46','2017-03-21 11:14:46','N','18'),('ff1b10d8-0e04-11e7-8e93-bb74150e04eb','1·','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2017-03-21 15:07:08','2017-03-21 15:07:08','N','19'),('f963e867-0e16-11e7-8e93-bb74150e04eb','yj','yj',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2017-03-21 17:16:05','2017-03-21 17:16:05','N','20'),('b3417432-0dd9-11e7-91d5-6f4819a5f2cb','iop','th',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2017-03-21 17:57:20','2017-03-21 17:57:20','N','21'),('f4135b3a-0dda-11e7-91d5-6f4819a5f2cb','sdf','df',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2017-03-21 18:06:19','2017-04-06 09:42:32','N','22'),('05ee0d8f-0ddb-11e7-91d5-6f4819a5f2cb','666666666','66666666',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2017-03-21 18:06:49','2017-04-06 09:42:32','N','23'),('83eb647b-1278-11e7-97e8-e219d7e80970','gf','gf',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'fg',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2017-03-27 15:04:27','2017-03-27 15:04:41','N','24'),('2a0f585e-13a2-11e7-8bf3-5863ea6b5879','zhangsan','sf',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2017-03-28 18:34:48','2017-03-31 18:39:31','N','25'),('a19c0c7e-13a2-11e7-8bf3-5863ea6b5879','zhangsan','sf',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2017-03-28 18:38:08','2017-03-28 18:38:08','N','26'),('a9753ce3-13a2-11e7-8bf3-5863ea6b5879','zhangsan','sf',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2017-03-28 18:38:21','2017-03-28 18:38:21','N','27'),('32f162ca-15f7-11e7-8bf3-5863ea6b5879','ssdf','sdf',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'sdf@13.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2017-03-31 17:48:42','2017-03-31 17:48:42','N','28'),('6ae2511f-1a61-11e7-a9c1-2842d35d579d','er','sadf',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2017-04-06 08:38:56','2017-04-06 08:38:56','N','29'),('15816d0a-1a68-11e7-a9c1-2842d35d579d','dfsa','asf',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2017-04-06 09:26:39','2017-04-06 09:26:39','N','30'),('5882e8e7-1a68-11e7-a9c1-2842d35d579d','sad','asdf',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2017-04-06 09:28:37','2017-04-06 09:28:37','N','31');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
