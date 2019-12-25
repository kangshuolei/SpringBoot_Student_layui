/*
Navicat MySQL Data Transfer

Source Server         : xiaokang
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : login

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-12-24 11:43:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `cs_id` bigint(50) NOT NULL AUTO_INCREMENT,
  `cs_name` varchar(20) DEFAULT NULL,
  `cs_tec` varchar(11) DEFAULT NULL,
  `cs_sex` varchar(2) DEFAULT NULL,
  `cs_description` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`cs_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '语文', '康硕雷', '女', '教授语文的阅读和写作');
INSERT INTO `course` VALUES ('2', '数学', '张三', '女', '教授数学的运算和处理');
INSERT INTO `course` VALUES ('3', '化学', '李四', '男', '教授化学的实验和公式');
INSERT INTO `course` VALUES ('4', '物理', '王五', '男', '教授物理的公式和运算');
INSERT INTO `course` VALUES ('5', '英语', '马六', '女', '教授英语的阅读和发音');
INSERT INTO `course` VALUES ('6', '生物', '田七', '女', '教授生物的遗传和物种');
INSERT INTO `course` VALUES ('7', '地理', '周八', '男', '教授地理的方位和气候');
INSERT INTO `course` VALUES ('8', '历史', '刘一', '女', '教授历史的文化和传统');
INSERT INTO `course` VALUES ('9', '政治', '郑十', '男', '教授政治的法律和社会');
INSERT INTO `course` VALUES ('10', '体育', '王二', '女', '教授体育的技能和技巧');
INSERT INTO `course` VALUES ('11', '微机', '张五', '女', '教授微机的使用和开发');
INSERT INTO `course` VALUES ('16', '语文', '张三', '男', '教授语文的选择和简答');

-- ----------------------------
-- Table structure for c_s_relation
-- ----------------------------
DROP TABLE IF EXISTS `c_s_relation`;
CREATE TABLE `c_s_relation` (
  `r_id` int(11) NOT NULL AUTO_INCREMENT,
  `id` int(11) DEFAULT NULL,
  `cs_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`r_id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of c_s_relation
-- ----------------------------
INSERT INTO `c_s_relation` VALUES ('1', '2', '2');
INSERT INTO `c_s_relation` VALUES ('13', '2', '4');
INSERT INTO `c_s_relation` VALUES ('14', '3', '4');
INSERT INTO `c_s_relation` VALUES ('15', '4', '4');
INSERT INTO `c_s_relation` VALUES ('16', '2', '7');
INSERT INTO `c_s_relation` VALUES ('17', '3', '7');
INSERT INTO `c_s_relation` VALUES ('18', '4', '7');
INSERT INTO `c_s_relation` VALUES ('19', '5', '7');
INSERT INTO `c_s_relation` VALUES ('20', '6', '7');
INSERT INTO `c_s_relation` VALUES ('21', '7', '7');
INSERT INTO `c_s_relation` VALUES ('22', '3', '6');
INSERT INTO `c_s_relation` VALUES ('23', '4', '6');
INSERT INTO `c_s_relation` VALUES ('24', '5', '6');
INSERT INTO `c_s_relation` VALUES ('25', '3', '2');
INSERT INTO `c_s_relation` VALUES ('26', '4', '2');
INSERT INTO `c_s_relation` VALUES ('27', '5', '2');
INSERT INTO `c_s_relation` VALUES ('28', '2', '1');
INSERT INTO `c_s_relation` VALUES ('29', '3', '1');
INSERT INTO `c_s_relation` VALUES ('30', '4', '1');
INSERT INTO `c_s_relation` VALUES ('31', '2', '6');
INSERT INTO `c_s_relation` VALUES ('32', '6', '6');
INSERT INTO `c_s_relation` VALUES ('33', '7', '6');
INSERT INTO `c_s_relation` VALUES ('34', '2', '8');
INSERT INTO `c_s_relation` VALUES ('35', '3', '8');
INSERT INTO `c_s_relation` VALUES ('36', '4', '8');
INSERT INTO `c_s_relation` VALUES ('37', '5', '8');
INSERT INTO `c_s_relation` VALUES ('38', '6', '8');
INSERT INTO `c_s_relation` VALUES ('39', '7', '8');
INSERT INTO `c_s_relation` VALUES ('40', '8', '8');
INSERT INTO `c_s_relation` VALUES ('41', '9', '8');
INSERT INTO `c_s_relation` VALUES ('42', '10', '8');
INSERT INTO `c_s_relation` VALUES ('43', '11', '8');
INSERT INTO `c_s_relation` VALUES ('44', '13', '8');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stu_num` int(20) DEFAULT NULL,
  `stu_name` varchar(20) DEFAULT NULL,
  `stu_sex` varchar(2) DEFAULT NULL,
  `stu_address` varchar(20) DEFAULT NULL,
  `stu_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('2', '171307258', '张三', '男', '石家庄', '2019-12-16 16:20:55');
INSERT INTO `student` VALUES ('3', '171307250', '李四', '女', '石家庄', '2019-12-11 17:30:38');
INSERT INTO `student` VALUES ('4', '171307251', '王五', '男', '深圳', '2019-12-11 17:30:52');
INSERT INTO `student` VALUES ('5', '171307252', '马六', '男', '成都', '2019-12-11 17:31:03');
INSERT INTO `student` VALUES ('6', '171307253', '田七', '女', '成都', '2019-12-11 17:31:15');
INSERT INTO `student` VALUES ('7', '171307254', '周八', '男', '深圳', '2019-12-11 17:32:08');
INSERT INTO `student` VALUES ('8', '171307255', '孙九', '女', '成都', '2019-12-11 17:32:24');
INSERT INTO `student` VALUES ('9', '171307256', '郑十', '女', '石家庄', '2019-12-11 17:32:44');
INSERT INTO `student` VALUES ('10', '171307259', '陈二', '女', '北京', '2019-12-11 17:33:03');
INSERT INTO `student` VALUES ('11', '171307352', '刘一', '女', '深圳', '2019-12-11 17:34:01');
INSERT INTO `student` VALUES ('13', '171307589', '李六', '女', '石家庄', '2019-12-11 17:34:39');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `u_name` varchar(20) DEFAULT NULL,
  `u_pwd` varchar(50) DEFAULT NULL,
  `u_email` varchar(20) DEFAULT NULL,
  `u_tel` varchar(20) DEFAULT NULL,
  `u_rank` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('2', 'xiaokang', '98293CCDC86C3296221E2CCC150F6520', '2374500210@qq.com', '17692388892', '普通用户');
INSERT INTO `user` VALUES ('3', 'kangshuolei', '61E84491B55A0E503BC4377C66E6FBB1', '2374500210@qq.com', '17692388891', '管理员');
INSERT INTO `user` VALUES ('5', 'admin', '61E84491B55A0E503BC4377C66E6FBB1', '2374500210@qq.com', '17692388891', '管理员');

-- ----------------------------
-- View structure for view_student_course
-- ----------------------------
DROP VIEW IF EXISTS `view_student_course`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_student_course` AS select `student`.`id` AS `id`,`student`.`stu_num` AS `stu_num`,`student`.`stu_name` AS `stu_name`,`student`.`stu_sex` AS `stu_sex`,`student`.`stu_address` AS `stu_address`,`student`.`stu_date` AS `stu_date`,`course`.`cs_id` AS `cs_id`,`course`.`cs_name` AS `cs_name`,`course`.`cs_tec` AS `cs_tec`,`course`.`cs_sex` AS `cs_sex`,`course`.`cs_description` AS `cs_description` from ((`course` join `c_s_relation` on((`c_s_relation`.`cs_id` = `course`.`cs_id`))) join `student` on((`student`.`id` = `c_s_relation`.`id`))) ;
