package com.taishan.netty.common;

import io.netty.buffer.ByteBuf;

public abstract class Operation extends MessageBody {

    /**
     * 执行操作
     *
     * @return
     */
    public abstract OperationResult execute();


    /**
     * 数据区解码> ByteBuf->Java对象
     *
     * @param msg
     */
    public abstract void decode(ByteBuf msg);
}
