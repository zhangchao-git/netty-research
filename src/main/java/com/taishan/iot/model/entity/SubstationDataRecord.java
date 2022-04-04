package com.taishan.iot.model.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class SubstationDataRecord {
    private Integer sendAddr;

    private Integer substationAddr;

    private Short detectorQty;

    private Integer detectorAddr;

    private Date uploadTime;

    private Byte nodeType;

    private Byte sensorQty;

    private Short mainBoardPower;

    private Short wirelessPower;

    private Byte sensorNum;

    private Byte sensorType;

    private BigDecimal dataValue;

    private Date crdate;

    public SubstationDataRecord() {
    }

    public SubstationDataRecord(Integer sendAddr, Integer substationAddr, Short detectorQty,
                                Integer detectorAddr, Date uploadTime, Byte nodeType,
                                Byte sensorQty, Short mainBoardPower, Short wirelessPower,
                                Byte sensorNum, Byte sensorType, Float dataValue,
                                Date crdate) {
        this.sendAddr = sendAddr;
        this.substationAddr = substationAddr;
        this.detectorQty = detectorQty;
        this.detectorAddr = detectorAddr;
        this.uploadTime = uploadTime;
        this.nodeType = nodeType;
        this.sensorQty = sensorQty;
        this.mainBoardPower = mainBoardPower;
        this.wirelessPower = wirelessPower;
        this.sensorNum = sensorNum;
        this.sensorType = sensorType;
        this.dataValue = BigDecimal.valueOf(dataValue);
        this.crdate = crdate;
    }
}
