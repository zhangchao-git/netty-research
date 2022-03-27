package com.taishan.iot.netty.codec;


import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import lombok.extern.slf4j.Slf4j;

import static com.taishan.iot.netty.constant.Constant.HEAD_START;

@Slf4j
public class TernimalFrameDecoder extends LengthFieldBasedFrameDecoder {

    public TernimalFrameDecoder() {
        super(Integer.MAX_VALUE, 1, 2, 10, 1);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        log.error(">>>>>> TernimalFrameDecoder: ip:{},hex:{}\n", ctx.channel().remoteAddress(), ByteBufUtil.hexDump(in));
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
        FileUtil.appendUtf8String(DateUtil.format(DateUtil.date(), DatePattern.NORM_DATETIME_FORMATTER) + "||" + ctx.channel().remoteAddress() + ctx.channel().remoteAddress() + " :" + ByteBufUtil.hexDump(in) + "\n", "nettylog/" + DateUtil.formatDate(DateUtil.date()) + ".log");

        return super.decode(ctx, in);
    }
}
