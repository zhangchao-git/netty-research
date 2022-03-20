package com.taishan.iot;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NettyResearchApplicationTests {

    /**
     * 测试报文
     */
    @Test
    void contextLoads() {
//        注册包、心跳包 数据帧
//        AA 00 02 02 00 03 00 04 00 05 81 00 3A 00 06 0D
//        分站包
//        aa001102000300040005820102010316030d160e15210506e830000700060d
        ByteBuf bb = ByteBufAllocator.DEFAULT.heapBuffer();
        bb.writeByte(0xAA);//开始魔数，1字节
        bb.writeShort(17);//数据包长度，2字节
        bb.writeByte(2);//协议版本号，1字节
        bb.writeShort(3);//序列号，2字节
        bb.writeShort(4);//接收方地址，2字节
        bb.writeShort(5);//发送方地址，2字节
        bb.writeByte(0x82);//命令码，1字节
//        bb.writeShort(58);//注册包、心跳包 数据区
        //分站包数据区
        bb.writeByte(1);//子站数量
        bb.writeByte(2);//子站地址
        bb.writeByte(1);//监测仪数量
        bb.writeByte(3);//监测仪地址

        bb.writeByte(22);//年
        bb.writeByte(3);//月
        bb.writeByte(13);//日
        bb.writeByte(22);//时
        bb.writeByte(14);//分
        bb.writeByte(21);//秒

        bb.writeByte(33);//节点类型/通道数量4/1 00100001
        bb.writeByte(5);//主板电量 00100001
        bb.writeByte(6);//无线电量 00100001
        bb.writeByte(232);//通道编号/传感器类型 7/8 11101000
        bb.writeByte(48);//小数点/正负号/预留位 1/1 00110000
        bb.writeShort(7);//7

        bb.writeShort(6);//校验和，2字节
        bb.writeByte(0x0D);//结束码，1字节
        System.out.print(ByteBufUtil.hexDump(bb));
    }

}
