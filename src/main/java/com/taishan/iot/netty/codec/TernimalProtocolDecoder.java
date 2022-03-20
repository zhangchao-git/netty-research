package com.taishan.iot.netty.codec;

import com.taishan.iot.netty.model.DataPacket;
import com.taishan.iot.netty.model.req.RegisterMsg;
import com.taishan.iot.netty.model.req.SubstationMsg;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.taishan.iot.netty.constant.Constant.*;

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
            case OP_CODE_HEARTBEAT:
                packet = new RegisterMsg(bb);
                break;
            case OP_CODE_SUBSTATION:
                packet = new SubstationMsg(bb);
                break;
            default:
                packet = new DataPacket(bb);
                break;
        }
        packet.parse();
        return packet;
    }

}
