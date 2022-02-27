package com.taishan.netty.vo;

import com.taishan.netty.constant.Constant;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.util.ReferenceCountUtil;
import lombok.Data;

/**
 * 数据包
 */
@Data
public class DataPacket {

    /**
     * 消息头
     */
    protected Header header = new Header();

    /**
     * 消息流
     */
    protected ByteBuf payload;

    public DataPacket() {
    }

    public DataPacket(ByteBuf payload) {
        this.payload = payload;
    }

    public void parse() {
        try {
            this.parseHead();
            //验证包体长度
            if (this.header.getLength() != this.payload.readableBytes()) {
                throw new RuntimeException("包体长度有误");
            }
            this.parseBody();
        } finally {
//            ReferenceCountUtil.safeRelease(this.payload);
        }
    }

    protected void parseHead() {
        header.setLength(payload.readShort());
        header.setVersion(payload.readByte());
        header.setSerialNumber(payload.readShort());
        header.setReceiveAddr(payload.readShort());
        header.setSendAddr(payload.readShort());
        header.setOpCode(payload.readByte());
    }

    /**
     * 请求报文重写
     */
    protected void parseBody() {

    }

    /**
     * 响应报文重写 并调用父类
     *
     * @return
     */
    public void toByteBufMsg(ByteBuf bb) {
        bb.writeShort(1);//长度,此时并不知道消息体的具体长度，所有此时我们先占住位置，回头再来写
        bb.writeByte(header.getVersion());//版本
        bb.writeShort(header.getSerialNumber());//序列号
        bb.writeShort(header.getReceiveAddr());//接受方地址
        bb.writeShort(header.getSendAddr());//发送方地址
        bb.writeByte(header.getOpCode());//命令码
    }

    /**
     * 从ByteBuf中read固定长度的数组,相当于ByteBuf.readBytes(byte[] dst)的简单封装
     *
     * @param length
     * @return
     */
    public byte[] readBytes(int length) {
        byte[] bytes = new byte[length];
        this.payload.readBytes(bytes);
        return bytes;
    }
}
