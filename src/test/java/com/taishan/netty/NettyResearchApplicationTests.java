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
//        00aa000102000300040005503a0006000d
        ByteBuf bb = ByteBufAllocator.DEFAULT.heapBuffer();//在JT808Encoder escape()方法处回收
        bb.writeShort(0xAA);
        bb.writeShort(1);
        bb.writeByte(2);
        bb.writeShort(3);
        bb.writeShort(4);
        bb.writeShort(5);
        bb.writeByte(80);
        bb.writeByte(58);
        bb.writeShort(6);
        bb.writeShort(0x0D);
        System.out.print(ByteBufUtil.hexDump(bb));
    }


}
