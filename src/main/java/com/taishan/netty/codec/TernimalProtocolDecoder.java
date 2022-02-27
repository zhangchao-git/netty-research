package com.taishan.netty.codec;

import com.taishan.netty.vo.DataPacket;
import com.taishan.netty.vo.req.RegisterMsg;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.taishan.netty.constant.Constant.HEAD_END;
import static com.taishan.netty.constant.Constant.OP_CODE_REGISTER;

@Slf4j
public class TernimalProtocolDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out) throws Exception {
        log.debug(">>>>>> TernimalProtocolDecoder: ip:{},hex:{}\n", ctx.channel().remoteAddress(), ByteBufUtil.hexDump(byteBuf));
        // 结尾校验 0xOD
        short headEnd = byteBuf.getUnsignedByte(byteBuf.writerIndex() - 1);
        byteBuf.writerIndex(byteBuf.writerIndex() - 1);
        if (headEnd != HEAD_END) {
            log.warn("结束码错误,headEnd:{}", headEnd);
            return;
        }

        int checkSum = byteBuf.getUnsignedShort(byteBuf.writerIndex() - 2);//排除校验码
        byteBuf.writerIndex(byteBuf.writerIndex() - 2);
        //TODO CRC校验
//        byte calCheckSum = JT808Util.XorSumBytes(escape);
//        if (pkgCheckSum != calCheckSum) {
//            log.warn("校验码错误,pkgCheckSum:{},calCheckSum:{}", pkgCheckSum, calCheckSum);
//            ReferenceCountUtil.safeRelease(escape);
//            return null;
//        }
        DataPacket msg = parse(byteBuf);

        if (msg != null) {
            out.add(msg);
        }
    }

    public DataPacket parse(ByteBuf bb) {
        DataPacket packet = null;
        short opCode = bb.getUnsignedByte(bb.readerIndex() + 9);
        switch (opCode) {
            case OP_CODE_REGISTER:
                packet = new RegisterMsg(bb);
                break;
            default:
                packet = new DataPacket(bb);
                break;
        }
        packet.parse();
        return packet;
    }

}
