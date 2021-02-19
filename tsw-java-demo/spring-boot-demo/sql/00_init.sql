-- Create Schema
DROP SCHEMA IF EXISTS `tsw_demo`;
CREATE SCHEMA `tsw_demo`;

-- ----------------------------
-- Table structure for tsw_demo_account
-- ----------------------------
CREATE TABLE `tsw_demo`.`tsw_demo_account` (
  `account_id` int(11) NOT NULL COMMENT '账户ID',
  `balance` int(11) NOT NULL COMMENT '账户余额',
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账户表';

-- ----------------------------
-- Records of tsw_demo_account
-- ----------------------------
BEGIN;
INSERT INTO `tsw_demo`.`tsw_demo_account` VALUES (1, 2000000000);
COMMIT;

-- ----------------------------
-- Table structure for tsw_demo_inventory
-- ----------------------------
CREATE TABLE `tsw_demo`.`tsw_demo_inventory` (
  `product_id` int(11) NOT NULL COMMENT '产品ID',
  `qty` int(11) NOT NULL COMMENT '产品库存',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='库存表';

-- ----------------------------
-- Records of tsw_demo_inventory
-- ----------------------------
BEGIN;
INSERT INTO `tsw_demo`.`tsw_demo_inventory` VALUES (1, 2000000000);
COMMIT;

-- ----------------------------
-- Table structure for tsw_demo_order
-- ----------------------------
CREATE TABLE `tsw_demo`.`tsw_demo_order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `product_id` int(11) NOT NULL COMMENT '产品ID',
  `qty` int(11) NOT NULL COMMENT '数量',
  `account_id` int(11) NOT NULL COMMENT '账户ID',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '订单状态，0-订单开启，1-订单结束',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COMMENT='订单表';
