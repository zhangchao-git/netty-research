package com.taishan.netty.codec;


import com.taishan.netty.constant.Constant;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class TernimalFrameEncoder extends MessageToMessageEncoder<ByteBuf> {

    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        ByteBuf buf = ctx.alloc().buffer();
        escape(msg, buf);
        log.debug("<<<<<< TernimalFrameEncoder: {},hex:{}\n", ctx.channel().remoteAddress(), ByteBufUtil.hexDump(buf));
        out.add(buf);
    }


    /**
     * 添加魔数
     *
     * @param raw
     * @return
     */
    public void escape(ByteBuf raw, ByteBuf buf) {
        int len = raw.readableBytes();
        buf.writeShort(Constant.HEAD_START);
        while (len > 0) {
            byte b = raw.readByte();
            buf.writeByte(b);
            len--;
        }
        buf.writeShort(Constant.HEAD_END);
    }
}
