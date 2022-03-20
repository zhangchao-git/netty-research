package com.taishan.iot.netty.model;

import lombok.Data;

@Data
public class Header {
    /**
     * 数据包长度
     */
    private int length;

    /**
     * 协议版本号
     */
    private short version = 1;

    /**
     * 序列号
     */
    private int serialNumber;

    /**
     * 接收方地址
     */
    private int receiveAddr;

    /**
     * 发送方地址
     */
    private int sendAddr;

    /**
     * 命令码
     */
    private short opCode;

}
