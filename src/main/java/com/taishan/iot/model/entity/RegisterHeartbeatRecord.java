package com.taishan.iot.model.entity;

import lombok.Data;

import java.util.Date;

@Data
public class RegisterHeartbeatRecord {
    private Integer substationAddr;

    private Short opCode;

    private Date uploadTime;

    private Date crdate;

}
