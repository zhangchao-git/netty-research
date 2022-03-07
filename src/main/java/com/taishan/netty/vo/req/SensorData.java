package com.taishan.netty.vo.req;

import lombok.Data;

/**
 * 传感器数据
 */
@Data
public class SensorData {

    /**
     * 通道编号/传感器编号（前3位）
     */
    private Byte sensorNum;

    /**
     * 传感器类型（后5位）
     */
    private Byte sensorType;

    /**
     * 小数点（前3位） 1就是除以10,2就是除以100
     */
    private Byte decimalPoint;

    /**
     * 正负号(1位)
     */
    private Byte sign;

    /**
     * 测量值
     */
    private Float value;

}
