package com.taishan.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NettyResearchApplicationTests {

    @Test
    void contextLoads() {
//        注册包、心跳包 数据帧
//        AA 00 02 02 00 03 00 04 00 05 81 00 3A 00 06 0D
        ByteBuf bb = ByteBufAllocator.DEFAULT.heapBuffer();
        bb.writeByte(0xAA);//开始魔数，1字节
        bb.writeShort(2);//数据包长度，2字节
        bb.writeByte(2);//协议版本号，1字节
        bb.writeShort(3);//序列号，2字节
        bb.writeShort(4);//接收方地址，2字节
        bb.writeShort(5);//发送方地址，2字节
        bb.writeByte(0x80);//命令码，1字节
        bb.writeShort(58);//数据区
        bb.writeShort(6);//校验和，2字节
        bb.writeByte(0x0D);//结束码，1字节

        System.out.print(ByteBufUtil.hexDump(bb));
    }


}
