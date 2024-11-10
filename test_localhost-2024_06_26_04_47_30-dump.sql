CREATE DATABASE  IF NOT EXISTS `schema01` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `schema01`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: schema01
-- ------------------------------------------------------
-- Server version	8.0.37

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `applicationdb`
--

DROP TABLE IF EXISTS `applicationdb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `applicationdb` (
  `applicationID` bigint NOT NULL AUTO_INCREMENT COMMENT '申请ID',
  `company` varchar(30) DEFAULT NULL COMMENT '公司名称',
  `applicant` varchar(20) DEFAULT NULL COMMENT '申请人姓名',
  `topic` varchar(100) DEFAULT NULL COMMENT '培训主题',
  `time` varchar(50) DEFAULT NULL COMMENT '申请提交时间',
  `date` varchar(50) DEFAULT NULL COMMENT '培训申请时间',
  `content` varchar(300) DEFAULT NULL COMMENT '培训内容',
  `scale` int DEFAULT NULL COMMENT '数量',
  `remarks` varchar(300) DEFAULT NULL COMMENT '备注',
  `fee` double DEFAULT NULL COMMENT '费用',
  `status` varchar(20) DEFAULT NULL COMMENT '培训状态',
  `email` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`applicationID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='申请数据库';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applicationdb`
--

LOCK TABLES `applicationdb` WRITE;
/*!40000 ALTER TABLE `applicationdb` DISABLE KEYS */;
INSERT INTO `applicationdb` VALUES (1,'珍妮咖啡','珍妮','叫你做软件咖啡','2024-06-26','2023-05-16','软件测试与咖啡制作',64,'咖啡是很好喝的',48400,'已批准','无'),(5,'未来科技有限公司','张三','Java高级编程培训','2024-07-06','2023-05-15','本次培训将深入讲解Java高级编程技巧，包括多线程、网络编程等。',50,'希望提供午餐及交通补贴。',0,'待处理','无'),(6,'创新动力有限公司','李四','项目管理实战培训','2024-07-06','2023-04-20','本次培训涵盖项目管理从启动到结束的全过程，包含敏捷管理等前沿理论。',30,'需要安排住宿。',0,'已拒绝','无'),(7,'智慧云图集团','王五','AI技术入门培训','2024-07-06','2023-03-15','介绍AI基础概念，包括机器学习、深度学习等，并实操演示。',20,'希望讲师能结合行业案例讲解。',0,'已拒绝','无'),(8,'绿色能源开发有限公司','赵六','可持续能源技术研讨会','2024-07-06','2023-06-01','探讨太阳能、风能等可再生能源的最新技术进展和应用案例。',100,'需要场地支持，并希望邀请行业专家进行分享。',0,'已拒绝','无'),(20,'公司','a','学习','2024-07-06','2024-07-16','学习',30,'学习',0,'已批准',NULL);
/*!40000 ALTER TABLE `applicationdb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attendance_recording_list`
--

DROP TABLE IF EXISTS `attendance_recording_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attendance_recording_list` (
  `attendanceID` int NOT NULL AUTO_INCREMENT,
  `attendanceStudent` varchar(100) NOT NULL,
  `attendanceStaff` varchar(100) NOT NULL,
  `attendanceStatus` varchar(100) NOT NULL,
  `attendanceStudentJob` varchar(100) NOT NULL,
  `attendanceStudentSex` varchar(20) NOT NULL,
  `attendanceStudentEmail` varchar(100) NOT NULL,
  `attendanceStudentCompany` varchar(100) NOT NULL,
  `attendanceCourseName` varchar(100) NOT NULL,
  PRIMARY KEY (`attendanceID`)
) ENGINE=InnoDB AUTO_INCREMENT=370 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='签到记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendance_recording_list`
--

LOCK TABLES `attendance_recording_list` WRITE;
/*!40000 ALTER TABLE `attendance_recording_list` DISABLE KEYS */;
INSERT INTO `attendance_recording_list` VALUES (360,'李四','sta','未签到','质量保证工程师','女','lisi@example.com','杨用于','软件过程'),(361,'赵六','sta','未签到','英语教师','女','zhaoliu@edu.com','某教育机构','软件过程'),(363,'stu','sta','未签到','duty','男','2014911413@qq.com','腾讯','承担这个是的'),(364,'stu','sta','未签到','duty','男','2014911413@qq.com','腾讯','沟通和'),(365,'stu','sta','未签到','duty','男','2014911413@qq.com','腾讯','下午'),(366,'stu','sta','未签到','duty','男','2014911413@qq.com','腾讯','法发顺丰发顺丰'),(367,'stu','sta','未签到','duty','男','2014911413@qq.com','腾讯','fgdg'),(368,'stu','sta','未签到','duty','男','2014911413@qq.com','腾讯','软件过程'),(369,'stu','sta','已签到','duty','男','2014911413@qq.com','腾讯','学习');
/*!40000 ALTER TABLE `attendance_recording_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_enrolling_list`
--

DROP TABLE IF EXISTS `course_enrolling_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_enrolling_list` (
  `course_name` varchar(150) NOT NULL,
  `student_name` varchar(150) NOT NULL,
  `enrolling_message` text NOT NULL,
  `ID` int NOT NULL AUTO_INCREMENT,
  `status` varchar(10) NOT NULL COMMENT '处理状态',
  `date` varchar(50) DEFAULT NULL COMMENT '申请日期',
  `sex` varchar(10) DEFAULT NULL,
  `company` varchar(30) DEFAULT NULL,
  `duty` varchar(20) DEFAULT NULL,
  `level` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `age` int DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_enrolling_list`
--

LOCK TABLES `course_enrolling_list` WRITE;
/*!40000 ALTER TABLE `course_enrolling_list` DISABLE KEYS */;
INSERT INTO `course_enrolling_list` VALUES ('法发顺丰发顺丰','stu','adadad',6,'已批准','2024-07-04','男','腾讯','duty','gaoji','2014911413@qq.com','15016621942',25),('da','stu','fagag',7,'已批准','2024-07-05','男','腾讯','duty','gaoji','2014911413@qq.com','15016621942',25),('com_name','stu','特别想上',8,'已批准','2024-07-06','男','腾讯','duty','gaoji','2014911413@qq.com','15016621942',18),('eee','stu','222',9,'已拒绝','2024-07-06','男','腾讯','duty','gaoji','2014911413@qq.com','15016621942',18),('dfef','stu','有有有由于',10,'待处理','2024-07-06','男','腾讯','duty','gaoji','2014911413@qq.com','15016621942',18),('下午','stu','111',11,'已批准','2024-07-06','男','腾讯','duty','gaoji','2014911413@qq.com','15016621942',18),('沟通和','stu','1',12,'已批准','2024-07-06','男','腾讯','duty','gaoji','2014911413@qq.com','15016621942',18),('方式','stu','1',13,'已批准','2024-07-06','男','腾讯','duty','gaoji','2014911413@qq.com','15016621942',18),('承担这个是的','stu','123',14,'已批准','2024-07-06','男','腾讯','duty','gaoji','2014911413@qq.com','15016621942',18),('学习','stu','学习',16,'已批准','2024-07-06','男','腾讯','duty','高手','2014911413@qq.com','15016621942',18);
/*!40000 ALTER TABLE `course_enrolling_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_evaluation_list`
--

DROP TABLE IF EXISTS `course_evaluation_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_evaluation_list` (
  `course_name` varchar(20) DEFAULT NULL COMMENT '课程名字',
  `student_name` varchar(30) DEFAULT NULL COMMENT '学生姓名',
  `tutor_evaluation` double DEFAULT NULL COMMENT '对导师评价',
  `course_evaluation` double DEFAULT NULL COMMENT '对课程评价',
  `suggestion` varchar(200) DEFAULT NULL COMMENT '建议',
  `reason` varchar(200) DEFAULT NULL COMMENT '申请理由',
  `evaluation_status` varchar(10) NOT NULL COMMENT '是否评价',
  `pay_status` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课程-学生信息数据库';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_evaluation_list`
--

LOCK TABLES `course_evaluation_list` WRITE;
/*!40000 ALTER TABLE `course_evaluation_list` DISABLE KEYS */;
INSERT INTO `course_evaluation_list` VALUES ('软件过程','李四',4,5,'课程内容丰富，讲师讲解清晰易懂。','dada','未评价','未缴费'),('软件过程','赵六',1,1,NULL,NULL,'未评价','未缴费'),('软件过程','stu',3,3,'333',NULL,'已评价','已缴费'),('fgdg','stu',2,3,'111',NULL,'已评价','已缴费'),('法发顺丰发顺丰','stu',3,3,'666',NULL,'已评价','已缴费'),('下午','stu',3,3,'33333',NULL,'已评价','已缴费'),('沟通和','stu',4,5,'爱来自中国',NULL,'已评价','未缴费'),('方式','stu',3,1,NULL,NULL,'已评价','未缴费'),('承担这个是的','stu',3,5,NULL,NULL,'已评价','未缴费'),('学习','stu',5,5,'很好',NULL,'已评价','已缴费');
/*!40000 ALTER TABLE `course_evaluation_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coursedb`
--

DROP TABLE IF EXISTS `coursedb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coursedb` (
  `name` varchar(100) DEFAULT NULL COMMENT '课程名称',
  `company` varchar(100) DEFAULT NULL COMMENT '公司名',
  `executor` varchar(100) DEFAULT NULL COMMENT '执行人姓名',
  `cost` int DEFAULT NULL COMMENT '培训费用',
  `plan` varchar(100) DEFAULT NULL COMMENT '培训计划',
  `instructorname` varchar(100) DEFAULT NULL COMMENT '讲师姓名',
  `instructortitle` varchar(100) DEFAULT NULL COMMENT '职称',
  `instructorfield` varchar(100) DEFAULT NULL COMMENT '擅长领域',
  `instructoremail` varchar(100) DEFAULT NULL,
  `instructorphone` varchar(100) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '课程ID',
  `status` varchar(100) DEFAULT NULL,
  `trainingcontent` varchar(100) DEFAULT NULL,
  `trainingendtime` varchar(100) DEFAULT NULL,
  `traininglocation` varchar(30) DEFAULT NULL,
  `trainingstarttime` varchar(50) DEFAULT NULL,
  `staff` varchar(100) DEFAULT NULL COMMENT '现场工作人员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=139 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课程信息数据库';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coursedb`
--

LOCK TABLES `coursedb` WRITE;
/*!40000 ALTER TABLE `coursedb` DISABLE KEYS */;
INSERT INTO `coursedb` VALUES ('软件过程','好加哦就','e',1800,'好','你好','哈哈','睡觉','da','150',117,'已结束','内容待定','2024-07-30T16:00:00.000Z','西安','2024-07-04T10:04:56.000Z','sta'),('下午','下午公司','e',1200,'da','da','czc','czc','cz','zcz',118,'已结束','内容待定','2024-07-30T16:00:00.000Z','西安','2024-07-04T10:04:56.000Z','sta'),('da','dad13','e',31,'dad','da','dad','da','ad','da',119,'已结束','内容待定','2024-07-30T16:00:00.000Z','西安','2024-07-04T10:04:56.000Z','sta'),('fgdg','gegge','e',220,'eg','eg','eg','g','gg','20000',120,'进行中','内容待定','2024-07-30T16:00:00.000Z','西安','2024-07-04T10:04:56.000Z','sta'),('eee','eee','e',2,'eee','e','e','e','e','e',121,'进行中','内容待定','2024-07-30T16:00:00.000Z','西安','2024-07-04T10:04:56.000Z','sta'),('方式','fsdf','zcy',234,'bsg','fs','fa','fa','fa','fa',122,'未开始','内容待定','2024-07-30T16:00:00.000Z','西安','2024-07-04T10:04:56.000Z','wb'),('沟通和','复活复活就','e',423,'发生法发','安得海u','阿发','saffron','dhyh','法国',124,'未开始','内容待定','2024-07-30T16:00:00.000Z','西安','2024-07-04T10:04:56.000Z','sta'),('法发顺丰发顺丰','归属感','e',57865,'分v阿塞飞','东莞市东莞市','fa','否定是','发','级',125,'已结束','内容待定','2024-07-30T16:00:00.000Z','西安','2024-07-04T10:04:56.000Z','sta'),('承担这个是的','vxbb','e',434,'发士大夫','就，联合健康','dAD的','大苏打的大大大','发的意思打扰d','3453',126,'进行中','内容待定','2024-07-30T16:00:00.000Z','西安','2024-07-04T10:04:56.000Z','sta'),('dfef','efef','zcy',222,'fefeffe','efef','efefef','efefe','efe','efeef',127,'未开始','内容待定','2024-07-30T16:00:00.000Z','西安','2024-07-04T10:04:56.000Z','wb'),('com_name','com','zcy',2000,'111','tea','教授','算法','2014911413@qq.com','15016621942',131,'已结束','111','2024-07-30T16:00:00.000Z','征信','2024-07-23T16:00:00.000Z','wb'),('学习','公司a','e',2000,'学习','zcy','教师','学习','123213@12.com','16548426',138,'未开始','学习','2024-07-24T16:00:00.000Z','中心','2024-07-17T16:00:00.000Z','sta');
/*!40000 ALTER TABLE `coursedb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `executordb`
--

DROP TABLE IF EXISTS `executordb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `executordb` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(20) DEFAULT NULL COMMENT '名字',
  `title` varchar(30) DEFAULT NULL COMMENT '职称',
  `experience` varchar(20) DEFAULT NULL COMMENT '就职年限',
  `status` varchar(10) DEFAULT NULL COMMENT '工作状态',
  `email` varchar(40) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=243235 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='执行人信息数据库';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `executordb`
--

LOCK TABLES `executordb` WRITE;
/*!40000 ALTER TABLE `executordb` DISABLE KEYS */;
INSERT INTO `executordb` VALUES (959,'sakdh','高级工程师','1年','空闲','65262@212.com'),(1001,'e','工程师','3年','工作中','2014911413@qq.com'),(3453,'剪剪剪','项目经理','2年','工作中','2014911413@qq.com'),(3466,'data','项目经理','2.5年','工作中','2014911413@qq.com'),(24523,'afa','工程师','3.14年','空闲','2014364413@qq.com');
/*!40000 ALTER TABLE `executordb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `noticedb`
--

DROP TABLE IF EXISTS `noticedb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `noticedb` (
  `courseName` varchar(20) DEFAULT NULL COMMENT '课程名',
  `date` varchar(50) DEFAULT NULL COMMENT '培训日期',
  `amount` int DEFAULT NULL COMMENT '招生数量',
  `teacherName` varchar(20) DEFAULT NULL COMMENT '讲师姓名',
  `courseContent` varchar(300) DEFAULT NULL COMMENT '课程内容',
  `cost` int DEFAULT NULL COMMENT '课程费用',
  `startTime` varchar(50) DEFAULT NULL COMMENT '开始时间',
  `timestamp` varchar(20) DEFAULT NULL COMMENT '通知发布日期',
  `location` varchar(30) DEFAULT NULL COMMENT '地点',
  `deadLine` varchar(50) DEFAULT NULL COMMENT '报名截止日期',
  `note` varchar(100) DEFAULT NULL COMMENT '备注'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `noticedb`
--

LOCK TABLES `noticedb` WRITE;
/*!40000 ALTER TABLE `noticedb` DISABLE KEYS */;
INSERT INTO `noticedb` VALUES ('Python编程入门','2023-09-15',30,'李老师','介绍Python编程的基础知识，包括变量、数据类型、条件语句、循环等。',2000,'09:00','2023-11-06','北京科技大学教学楼','2023-09-10','本课程适合编程初学者，提供课后答疑服务。'),('高级程序设计','2023-09-16',30,'李老师','介绍Python编程的基础知识，包括变量、数据类型、条件语句、循环等。',2000,'09:00','2024-06-27','北京科技大学教学楼','2023-09-10','本课程适合编程初学者，提供课后答疑服务。'),('UI/UX设计进阶','2023-10-20',20,'王设计师','深入学习UI/UX设计的高级技巧，包括原型设计、用户体验优化等。',3500,'10:00','2024-06-27','上海创意设计中心','2023-10-10','课程将邀请业界专家分享实战经验，并提供项目实践机会。'),('Java Web开发实战','2023-11-10',45,'张工程师','学习Java Web开发的全流程，包括Spring Boot、MyBatis、前端技术等。',4000,'09:30','2024-06-27','深圳软件园','2023-11-05','本课程包含大量实战项目，帮助学员快速掌握Java Web开发技能。'),('机器学习基础','2023-12-15',50,'赵博士','介绍机器学习的基础知识，包括监督学习、非监督学习、深度学习等。',3000,'14:00','2024-06-27','杭州人工智能研究院','2023-12-10','本课程将结合具体案例进行讲解，让学员深入理解机器学习的原理和应用。'),('1111','2024-06-03T16:00:00.000Z',1,'1111','1111',1,'2024-06-27T09:20:54.000Z','2024-06-27','feffef','2024-06-10T16:00:00.000Z','1111111'),('546','2024-07-22T16:00:00.000Z',168,'686','46568',356,'2024-07-04T10:09:33.000Z','2024-07-04','566','2024-07-22T16:00:00.000Z','686'),('com_name','2024-07-22T16:00:00.000Z',2,'tea','算法',2000,'2024-07-06T07:44:36.000Z','2024-07-06','征信','2024-07-23T16:00:00.000Z','111'),('学习','2024-07-23T16:00:00.000Z',30,'adafa','学习',2000,'2024-07-06T12:54:02.000Z','2024-07-06','中心','2024-07-29T16:00:00.000Z','学习');
/*!40000 ALTER TABLE `noticedb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studentdb`
--

DROP TABLE IF EXISTS `studentdb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `studentdb` (
  `name` varchar(20) DEFAULT NULL COMMENT '学员姓名',
  `sex` varchar(10) DEFAULT NULL COMMENT '性别',
  `company` varchar(30) DEFAULT NULL COMMENT '所属公司',
  `duty` varchar(20) DEFAULT NULL COMMENT '工作岗位',
  `level` varchar(20) DEFAULT NULL COMMENT '技术水平',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `age` int DEFAULT NULL,
  `phone` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学员信息数据';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studentdb`
--

LOCK TABLES `studentdb` WRITE;
/*!40000 ALTER TABLE `studentdb` DISABLE KEYS */;
INSERT INTO `studentdb` VALUES ('李四','女','杨用于','质量保证工程师','高级','lisi@example.com',40,'15016621942'),('赵六','女','某教育机构','英语教师','中级','zhaoliu@edu.com',20,'15016621942'),('孙七','女','腾讯','外科医生','主任医师','sunqi@hospi大大dadtal.com',45,'15016621942'),('stu','男','腾讯','duty','高手','2014911413@qq.com',18,'15016621942');
/*!40000 ALTER TABLE `studentdb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacherdb`
--

DROP TABLE IF EXISTS `teacherdb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacherdb` (
  `name` varchar(20) DEFAULT NULL COMMENT '讲师名字',
  `title` varchar(20) DEFAULT NULL COMMENT '职称',
  `major` varchar(20) DEFAULT NULL COMMENT '擅长领域',
  `email` varchar(20) DEFAULT NULL COMMENT '邮箱',
  `status` varchar(20) DEFAULT NULL COMMENT '工作状态',
  `number` varchar(30) DEFAULT NULL COMMENT '电话'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='讲师信息数据库';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacherdb`
--

LOCK TABLES `teacherdb` WRITE;
/*!40000 ALTER TABLE `teacherdb` DISABLE KEYS */;
INSERT INTO `teacherdb` VALUES ('王中介','发顺丰','cascade','阿发是否','空闲','564634564'),('fsdf','发放f','发顺丰','f阿发','工作中','15646946'),('ben','VS的v','VS的v','vdsv','工作中','496496469'),('云的一','gerg','gfedrg','rgreg','空闲','5964958'),('吴苯','sges','gsgs','gseges','工作中','2694985'),('种子一','seges','sges','gsgs','空闲','49849646'),('藩国项','gseg','seges','segs','工作中','2161694694'),('到你家','gesg','segse','seg','空闲','19849456'),('张晨阳','公45','大大','2424','工作中','4949496'),('翁好难','教授','计算机科学','zhan@example.com','空闲','96459464'),('tea','教授','算法','123@123.com','空闲','4616142251'),('刘会触','发顺丰','cascade','阿发是否','工作中','41854854'),('酥饼他','发顺丰','cascade','阿发是否','工作中','606266');
/*!40000 ALTER TABLE `teacherdb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userdb`
--

DROP TABLE IF EXISTS `userdb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userdb` (
  `username` varchar(10) NOT NULL COMMENT '用户名',
  `password` varchar(20) NOT NULL COMMENT '密码',
  `role` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户信息数据库';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userdb`
--

LOCK TABLES `userdb` WRITE;
/*!40000 ALTER TABLE `userdb` DISABLE KEYS */;
INSERT INTO `userdb` VALUES ('m','m','manager'),('c','c','company'),('e','e','executor'),('sta','s','staff'),('stu','s','student'),('admin','111','manager'),('admin','111','company'),('admin','111','executor'),('admin','111','student'),('admin','111','staff');
/*!40000 ALTER TABLE `userdb` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-07 15:40:31
