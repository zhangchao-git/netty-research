package com.taishan.iot.netty.codec;

import com.taishan.iot.netty.model.DataPacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class TernimalProtocolEncoder extends MessageToMessageEncoder<DataPacket> {


    @Override
    protected void encode(ChannelHandlerContext ctx, DataPacket dataPacket, List<Object> out) {
        ByteBuf buffer = ctx.alloc().buffer();

        dataPacket.toByteBufMsg(buffer);
        buffer.markWriterIndex();//标记一下，先到前面去写覆盖的，然后回到标记写校验码
        int bodyLen = buffer.readableBytes() - 10;//包体长度=总长度-头部长度
        buffer.writerIndex(0);
        buffer.writeShort(bodyLen);
        buffer.resetWriterIndex();

        //TODO CRC校验
        buffer.writeShort(9);
        out.add(buffer);
        log.debug("<<<<<< TernimalProtocolEncoder: {},hex:{}\n", ctx.channel().remoteAddress(), ByteBufUtil.hexDump(buffer));

    }
}
