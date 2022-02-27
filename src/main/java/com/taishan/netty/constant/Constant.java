package com.taishan.netty.constant;

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
    short SEND_ADDR = 0x55;

    /**
     * 注册包
     */
    byte OP_CODE_REGISTER = 80;

    /**
     * 心跳包
     */
    byte OP_CODE_HEARTBEAT = 81;

    /**
     * 分站数据包
     */
    byte OP_CODE_SUBSTATION = 82;


}
