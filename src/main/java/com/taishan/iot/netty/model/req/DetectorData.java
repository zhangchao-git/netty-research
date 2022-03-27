package com.taishan.iot.netty.model.req;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 检测仪
 */
@Data
public class DetectorData {

    /**
     * 检测仪地址
     */
    private Integer detectorAddr;

    /**
     * 年
     */
    private String year;

    /**
     * 月
     */
    private String month;

    /**
     * 日
     */
    private String day;

    /**
     * 时
     */
    private String hour;

    /**
     * 分
     */
    private String minute;

    /**
     * 秒
     */
    private String second;

    /**
     * 节点类型/通道数量，拆分使用
     */
    private Short typeAndQty;

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
    private Short mainBoardPower;

    /**
     * 无线电量
     */
    private Short wirelessPower;

    /**
     * 通道数据/传感器数据
     */
    private List<SensorData> sensorDataList = new ArrayList<>();

    /**
     * 节点类型-传感器数量赋值
     */
    public void setNodeTypeAndSensorQty() {
        if (typeAndQty != null) {
            this.nodeType = (byte) (typeAndQty >> 3);
            this.sensorQty = (byte) (typeAndQty & 0x7);
        }
    }


}
