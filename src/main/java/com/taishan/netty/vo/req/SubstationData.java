package com.taishan.netty.vo.req;

import java.util.List;

/**
 * 子站数据
 */
public class SubstationData {

    /**
     * 子站地址
     */
    private Short substationAddr;

    /**
     * 检测仪数量
     */
    private Short detectorQty;

    /**
     * 检测仪地址
     */
    private Short detectorAddr;

    /**
     * 年
     */
    private Short year;

    /**
     * 月
     */
    private Short month;

    /**
     * 日
     */
    private Short day;

    /**
     * 时
     */
    private Short hour;

    /**
     * 分
     */
    private Short minute;

    /**
     * 秒
     */
    private Short second;

    /**
     * 节点类型（前五位）
     */
    private Byte nodeType;

    /**
     * 通道数量/传感器数量（后三位）
     */
    private Byte sensorQty;

    /**
     * 主板电量
     */
    private Short motherboardPower;

    /**
     * 无线电量
     */
    private Short wirelessPower;

    /**
     * 通道数据/传感器数据
     */
    private List<SensorData> sensorDataList;
}
