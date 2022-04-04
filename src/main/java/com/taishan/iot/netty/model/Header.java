package com.taishan.iot.netty.model;

import lombok.Data;

@Data
public class Header {
    /**
     * 数据包长度
     */
    private Integer length;

    /**
     * 协议版本号
     */
    private Short version = 1;

    /**
     * 序列号
     */
    private Integer serialNumber;

    /**
     * 接收方地址
     */
    private Integer receiveAddr;

    /**
     * 发送方地址
     */
    private Integer sendAddr;

    /**
     * 命令码
     */
    private Short opCode;

}
