package com.taishan.netty.codec;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import lombok.extern.slf4j.Slf4j;

import static com.taishan.netty.constant.Constant.HEAD_START;

@Slf4j
public class TernimalFrameDecoder extends LengthFieldBasedFrameDecoder {

    public TernimalFrameDecoder() {
        super(Integer.MAX_VALUE, 1, 2, 11, 1);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        log.debug(">>>>>> TernimalFrameDecoder: ip:{},hex:{}\n", ctx.channel().remoteAddress(), ByteBufUtil.hexDump(in));
        //AA 开始
        while (in.readableBytes() > 0) {
            short headStart = in.getUnsignedByte(in.readerIndex());
            if (HEAD_START == headStart) {
                break;
            }
            in.readUnsignedByte();
        }
        if (in.readableBytes() < 14) {
            return null;
        }
        return super.decode(ctx, in);
    }
}
