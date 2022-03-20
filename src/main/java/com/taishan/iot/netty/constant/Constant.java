package com.taishan.iot.netty.constant;

public interface Constant {

    /**
     * 协议开始字符
     */
    short HEAD_START = 0xAA;

    /**
     * 协议结束字符
     */
    short HEAD_END = 0x0D;

    /**
     * 发送方地址
     */
    int SEND_ADDR = 0X5555;

    /**
     * 注册包
     */
    short OP_CODE_REGISTER = 0x80;

    /**
     * 心跳包
     */
    short OP_CODE_HEARTBEAT = 0x81;

    /**
     * 分站数据包
     */
    short OP_CODE_SUBSTATION = 0x82;


}
