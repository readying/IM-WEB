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

insert  into `sys_account`(`ID`,`USERNAME`,`PASSWORD`,`PLAT`,`PASSWORDPROMPT`,`SALT`,`CREATETIME`,`UPDATETIME`,`IFUSE`,`ORDERNUM`,`TOKEN`) values ('1','admin','b4f224455bf6969f42028d7fea91d21e',NULL,NULL,'123456',NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `sys_account_role` */

DROP TABLE IF EXISTS `sys_account_role`;

CREATE TABLE `sys_account_role` (
  `ID` varchar(50) NOT NULL COMMENT 'id',
  `ACCOUNTID` varchar(50) DEFAULT NULL COMMENT '账户id',
  `ROLEID` varchar(50) DEFAULT NULL COMMENT '角色id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_account_role` */

insert  into `sys_account_role`(`ID`,`ACCOUNTID`,`ROLEID`) values ('1','1','1');

/*Table structure for table `sys_account_user` */

DROP TABLE IF EXISTS `sys_account_user`;

CREATE TABLE `sys_account_user` (
  `ID` varchar(50) NOT NULL COMMENT 'id',
  `USERID` varchar(50) DEFAULT NULL COMMENT '用户ID',
  `ACCOUNTID` varchar(50) DEFAULT NULL COMMENT '账户ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_account_user` */

insert  into `sys_account_user`(`ID`,`USERID`,`ACCOUNTID`) values ('1','1','1');

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

insert  into `sys_authority`(`ID`,`AUTHORITYNAME`,`DESCRIPTION`,`CREATETIME`,`UPDATETIME`,`IFUSE`,`ORDERNUM`) values ('1','获取所有用户权限','获取所有用户权限','2017','2017','1','1');

/*Table structure for table `sys_department` */

DROP TABLE IF EXISTS `sys_department`;

CREATE TABLE `sys_department` (
  `ID` varchar(50) NOT NULL COMMENT '部门id',
  `REALNAME` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `LEADER` varchar(50) DEFAULT NULL COMMENT '部门领导',
  `ADMINISTRATOR` varchar(50) DEFAULT NULL COMMENT '部门管理员',
  `GROUP` varchar(50) DEFAULT NULL COMMENT '部门群'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_department` */

/*Table structure for table `sys_department_department` */

DROP TABLE IF EXISTS `sys_department_department`;

CREATE TABLE `sys_department_department` (
  `ID` varchar(50) NOT NULL COMMENT 'id',
  `PARENTID` varchar(50) DEFAULT NULL COMMENT '上级部门ID',
  `CHILDRENID` varchar(50) DEFAULT NULL COMMENT '下级部门ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_department_department` */

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
  `LEVEL` varchar(50) DEFAULT NULL COMMENT '等级',
  `ISMEMULEAF` varchar(50) DEFAULT NULL COMMENT '是否为菜单权限',
  `CREATETIME` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `UPDATETIME` varchar(50) DEFAULT NULL COMMENT '更新时间',
  `IFUSE` varchar(20) DEFAULT NULL COMMENT '是否使用',
  `ORDERNUM` varchar(50) DEFAULT NULL COMMENT '排序编号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_resource` */

insert  into `sys_resource`(`ID`,`RESOURCENAME`,`DESCRIPTION`,`TYPE`,`PARENTID`,`PARENTNAME`,`URL`,`LEVEL`,`ISMEMULEAF`,`CREATETIME`,`UPDATETIME`,`IFUSE`,`ORDERNUM`) values ('1','ACCOUNT_ALL','查询所有用户','web','root','root','/account/all','1','y','2017-01-13 12:12:12','2017-01-13 12:12:12','y','1');

/*Table structure for table `sys_resource_authority` */

DROP TABLE IF EXISTS `sys_resource_authority`;

CREATE TABLE `sys_resource_authority` (
  `ID` varchar(50) NOT NULL COMMENT 'id',
  `AUTHORITYID` varchar(50) DEFAULT NULL COMMENT '权限ID',
  `RESOURCEID` varchar(50) DEFAULT NULL COMMENT '资源ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_resource_authority` */

insert  into `sys_resource_authority`(`ID`,`AUTHORITYID`,`RESOURCEID`) values ('1','1','1');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `ID` varchar(50) NOT NULL COMMENT '角色id',
  `REALNAME` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `DESCRIPTION` varchar(200) DEFAULT NULL COMMENT '描述',
  `CREATETIME` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `UPDATETIME` varchar(50) DEFAULT NULL COMMENT '变更时间',
  `IFUSE` varchar(20) DEFAULT NULL COMMENT '是否使用',
  `ORDERNUM` varchar(50) DEFAULT NULL COMMENT '排序编号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

insert  into `sys_role`(`ID`,`REALNAME`,`DESCRIPTION`,`CREATETIME`,`UPDATETIME`,`IFUSE`,`ORDERNUM`) values ('1','超级管理员','拥有所有权限','2017-01-13 12:12:12','2017-01-13 12:12:12','1','1');

/*Table structure for table `sys_role_authority` */

DROP TABLE IF EXISTS `sys_role_authority`;

CREATE TABLE `sys_role_authority` (
  `ID` varchar(50) DEFAULT NULL COMMENT 'id',
  `ROLEID` varchar(50) DEFAULT NULL COMMENT '角色id',
  `AUTHORITYID` varchar(50) DEFAULT NULL COMMENT '权限id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_role_authority` */

insert  into `sys_role_authority`(`ID`,`ROLEID`,`AUTHORITYID`) values ('1','1','1');

/*Table structure for table `sys_user_department` */

DROP TABLE IF EXISTS `sys_user_department`;

CREATE TABLE `sys_user_department` (
  `ID` varchar(50) NOT NULL COMMENT 'id',
  `USERID` varchar(50) DEFAULT NULL COMMENT '用户id',
  `DEPARTMENTID` varbinary(50) DEFAULT NULL COMMENT '部门id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_user_department` */

/*Table structure for table `sys_userinfo` */

DROP TABLE IF EXISTS `sys_userinfo`;

CREATE TABLE `sys_userinfo` (
  `ID` varchar(50) NOT NULL COMMENT '用户id',
  `REALNAME` varchar(50) DEFAULT NULL COMMENT '用户姓名',
  `GENDER` varchar(50) DEFAULT NULL COMMENT '性别',
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
  `ORDERNUM` varchar(50) DEFAULT NULL COMMENT '排序编号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_userinfo` */

insert  into `sys_userinfo`(`ID`,`REALNAME`,`GENDER`,`NATION`,`NATIVEPLACE`,`BIRTHDATE`,`POLITICALSTATUS`,`MARITALSTATUS`,`NICKNAME`,`PICTURE`,`PHONE`,`MAIL`,`ORG`,`POLICENUM`,`IDENTITYCODE`,`QR`,`QQ`,`WECHAT`,`JOBCODE`,`STATION`,`AUTOGRAPH`,`HOMEADDRESS`,`OFFICEADDRESS`,`OTHERADDRESS`,`OFFICETELEPHONE`,`OTHERTELEPHONE`,`IFHIDENUM`,`CREATETIME`,`UPDATETIME`,`IFUSE`,`ORDERNUM`) values ('1','张三','M','北京','北京','1993','000000','北京','北京','home','12345','1332871@qq.com','HR','1234','1234','1','12231','21',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1',NULL),('7f099639-035b-11e7-970c-dd85038077d3','Lisi','\"WMM\"',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('2748d984-036d-11e7-970c-dd85038077d3',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),('61455c79-036d-11e7-970c-dd85038077d3','sss',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `v_account_authority` */

DROP TABLE IF EXISTS `v_account_authority`;

/*!50001 DROP VIEW IF EXISTS `v_account_authority` */;
/*!50001 DROP TABLE IF EXISTS `v_account_authority` */;

/*!50001 CREATE TABLE  `v_account_authority`(
 `ID` varchar(50) ,
 `AUTHORITYNAME` varchar(50) ,
 `DESCRIPTION` varchar(200) ,
 `CREATETIME` varchar(50) ,
 `UPDATETIME` varchar(50) ,
 `IFUSE` varchar(20) ,
 `ORDERNUM` varchar(50) ,
 `USERNAME` varchar(50) ,
 `TOKEN` varchar(50) 
)*/;

/*View structure for view v_account_authority */

/*!50001 DROP TABLE IF EXISTS `v_account_authority` */;
/*!50001 DROP VIEW IF EXISTS `v_account_authority` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_account_authority` AS (select `auth`.`ID` AS `ID`,`auth`.`AUTHORITYNAME` AS `AUTHORITYNAME`,`auth`.`DESCRIPTION` AS `DESCRIPTION`,`auth`.`CREATETIME` AS `CREATETIME`,`auth`.`UPDATETIME` AS `UPDATETIME`,`auth`.`IFUSE` AS `IFUSE`,`auth`.`ORDERNUM` AS `ORDERNUM`,`a`.`USERNAME` AS `USERNAME`,`a`.`TOKEN` AS `TOKEN` from ((((`sys_account` `a` join `sys_account_role` `ar`) join `sys_role` `r`) join `sys_role_authority` `ra`) join `sys_authority` `auth`) where ((`a`.`ID` = `ar`.`ACCOUNTID`) and (`ar`.`ROLEID` = `ra`.`ROLEID`) and (`auth`.`ID` = `ra`.`AUTHORITYID`))) */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
