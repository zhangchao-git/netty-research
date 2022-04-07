
--数据库字符集：utf8mb4
--排序规则：utf8mb4_general_ci

CREATE TABLE `substation_data_record`  (
  `send_addr` int(11) NULL DEFAULT NULL COMMENT '发送方地址/分站地址',
  `substation_addr` int(11) NULL DEFAULT NULL COMMENT '子站地址',
  `detector_qty` int(5) NULL DEFAULT NULL COMMENT '监测仪数量',
  `detector_addr` int(11) NULL DEFAULT NULL COMMENT '监测仪地址',
  `upload_time` datetime(0) NULL DEFAULT NULL COMMENT '上传时间',
  `node_type` int(3) NULL DEFAULT NULL COMMENT '节点类型',
  `sensor_qty` int(3) NULL DEFAULT NULL COMMENT '通道数量',
  `main_board_power` int(5) NULL DEFAULT NULL COMMENT '主板电量',
  `wireless_power` int(5) NULL DEFAULT NULL COMMENT '无线电量',
  `sensor_num` int(3) NULL DEFAULT NULL COMMENT '通道编号',
  `sensor_type` int(3) NULL DEFAULT NULL COMMENT '传感器类型',
  `data_value` decimal(11, 5) NULL DEFAULT NULL COMMENT '数据值',
  `crdate` datetime(0) NULL DEFAULT NULL COMMENT '创建时间'
);

CREATE TABLE `substation_data`  (
  `send_addr` int(11) NULL DEFAULT NULL COMMENT '发送方地址/分站地址',
  `substation_addr` int(11) NULL DEFAULT NULL COMMENT '子站地址',
  `detector_qty` int(5) NULL DEFAULT NULL COMMENT '监测仪数量',
  `detector_addr` int(11) NULL DEFAULT NULL COMMENT '监测仪地址',
  `upload_time` datetime(0) NULL DEFAULT NULL COMMENT '上传时间',
  `node_type` int(3) NULL DEFAULT NULL COMMENT '节点类型',
  `sensor_qty` int(3) NULL DEFAULT NULL COMMENT '通道数量',
  `main_board_power` int(5) NULL DEFAULT NULL COMMENT '主板电量',
  `wireless_power` int(5) NULL DEFAULT NULL COMMENT '无线电量',
  `sensor_num` int(3) NULL DEFAULT NULL COMMENT '通道编号',
  `sensor_type` int(3) NULL DEFAULT NULL COMMENT '传感器类型',
  `data_value` decimal(11, 5) NULL DEFAULT NULL COMMENT '数据值',
  `crdate` datetime(0) NULL DEFAULT NULL COMMENT '创建时间'
) ;

CREATE TABLE `register_heartbeat_record`  (
  `substation_addr` int(11) NULL DEFAULT NULL COMMENT '分站地址',
  `op_code` int(3) NULL DEFAULT NULL COMMENT '操作码。80注册，81心跳',
  `upload_time` datetime(0) NULL DEFAULT NULL COMMENT '上传时间',
  `crdate` datetime(0) NULL DEFAULT NULL COMMENT '创建时间'
);

