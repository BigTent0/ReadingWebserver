/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : happyreading

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2019-11-11 12:48:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `FileNum` int(10) NOT NULL AUTO_INCREMENT,
  `UserID` int(10) NOT NULL,
  `FileName` varchar(20) NOT NULL,
  `URL` varchar(20) NOT NULL,
  `UpTime` date NOT NULL,
  `Downloads` int(10) NOT NULL,
  `FileInfo` varchar(100) NOT NULL,
  PRIMARY KEY (`FileNum`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of file
-- ----------------------------
INSERT INTO `file` VALUES ('1', '1241231', 'jinpingmei', 'D://afafafw', '2019-11-04', '1', 'dasfqfaffafgasgda');
INSERT INTO `file` VALUES ('2', '1241231', '小说', 'D://afafafw', '2019-11-04', '0', 'dasfqfaffafgasgda');
