package com.taishan.iot.netty.model.req;

import lombok.Data;

/**
 * 传感器数据
 */
@Data
public class SensorData {

    /**
     * 通道编号和传感器类型，拆分使用
     */
    private Short numAndType;

    /**
     * 通道编号（前3位）
     */
    private Byte sensorNum;

    /**
     * 传感器类型（后5位）
     */
    private Byte sensorType;

    /**
     * 计算方式（小数点、正负号），拆分使用
     */
    private Short computing;

    /**
     * 分母，计算值使用
     */
    private float denominator = 1;

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

    public void setSensorNumAndSensorType() {
        if (numAndType != null) {
            this.sensorNum = (byte) (numAndType >> 5);
            this.sensorType = (byte) (numAndType & 0x1f);
        }
    }

    public void setDecimalPointAndSign() {
        if (computing != null) {
            this.decimalPoint = (byte) (computing >> 5);
            byte clacDenominator = this.decimalPoint;
            while (clacDenominator > 0) {
                denominator = denominator * 10;
                clacDenominator--;
            }
            this.sign = (byte) ((computing & 0x10) >> 4);
        }
    }


}
