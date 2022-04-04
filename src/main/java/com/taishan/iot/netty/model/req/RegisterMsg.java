package com.taishan.iot.netty.model.req;

import com.taishan.iot.netty.model.DataPacket;
import io.netty.buffer.ByteBuf;
import lombok.Data;

/**
 * 注册
 */
@Data
public class RegisterMsg extends DataPacket {

    /**
     * 分站地址
     */
    private Integer substationAddr;

    public RegisterMsg() {
    }

    public RegisterMsg(ByteBuf byteBuf) {
        super(byteBuf);
    }

    /**
     * 请求报文重写
     */
    @Override
    protected void parseBody() {
        this.substationAddr = this.payload.readUnsignedShort();
    }

    @Override
    public void toByteBufMsg(ByteBuf bb) {
        super.toByteBufMsg(bb);
        bb.writeShort(substationAddr);
    }
}
