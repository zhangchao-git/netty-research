package com.taishan.netty.vo;

import lombok.Data;

@Data
public class Header {
    /**
     * 数据包长度
     */
    private short length;

    /**
     * 协议版本号
     */
    private byte version = 1;

    /**
     * 序列号
     */
    private short serialNumber;

    /**
     * 接收方地址
     */
    private short receiveAddr;

    /**
     * 发送方地址
     */
    private short sendAddr;

    /**
     * 命令码
     */
    private byte opCode;

}
