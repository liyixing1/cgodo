SET FOREIGN_KEY_CHECKS=0;

CREATE TABLE `client_request` (
  `ID` varchar(32) NOT NULL,
  `IP` varchar(19) DEFAULT NULL COMMENT 'ip地址',
  `REQUEST_TIME` datetime DEFAULT NULL COMMENT '请求时间',
  `PROCESSING_TIME` bigint(20) DEFAULT NULL COMMENT '处理时间',
  `REQUEST_URL` varchar(4000) DEFAULT NULL COMMENT '请求地址',
  `USER_AGENT` varchar(1000) DEFAULT NULL COMMENT '请求浏览器',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `client_request_summary` (
  `ID` varchar(32) NOT NULL,
  `CLIENT_NUMBER` int(11) DEFAULT NULL COMMENT '总客户数，每次请求，都被视为一次统计',
  `CURRENT_DAY_NUMBER` int(11) DEFAULT NULL COMMENT '当天客户数，每次请求，都被视为一次统计',
  `SUMMARY_TIME` datetime DEFAULT NULL COMMENT '统计当天时间',
  `UV` int(11) DEFAULT NULL COMMENT '当天UV，按站点统计，即当天进入站点的人数(IP)',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
