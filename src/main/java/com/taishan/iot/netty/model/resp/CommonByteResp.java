package com.taishan.iot.netty.model.resp;

import com.taishan.iot.netty.model.DataPacket;
import io.netty.buffer.ByteBuf;
import lombok.Data;

@Data
public class CommonByteResp extends DataPacket {

    private byte data;

    @Override
    public void toByteBufMsg(ByteBuf bb) {
        super.toByteBufMsg(bb);
        bb.writeByte(1);
    }

    public CommonByteResp(DataPacket dataPacket) {
        super.header.setVersion(dataPacket.getHeader().getVersion());
        super.header.setSerialNumber(dataPacket.getHeader().getSerialNumber());
        super.header.setReceiveAddr(dataPacket.getHeader().getSendAddr());
        super.header.setSendAddr(dataPacket.getHeader().getReceiveAddr());
        super.header.setOpCode(dataPacket.getHeader().getOpCode());
    }
}
