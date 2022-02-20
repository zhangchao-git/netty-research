package com.taishan.netty.common;

import io.netty.buffer.ByteBuf;
import lombok.Data;

@Data
public abstract class OperationResult extends MessageBody {

    /**
     * 数据区编码
     *
     * @param byteBuf
     */
    public abstract void encode(ByteBuf byteBuf);
}
