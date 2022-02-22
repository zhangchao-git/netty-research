package com.taishan.netty.common.register;

import com.taishan.netty.common.Operation;
import io.netty.buffer.ByteBuf;
import lombok.Data;
import lombok.extern.java.Log;

@Data
@Log
public class RegisterOperation extends Operation {

    private byte substationAddr;

    @Override
    public RegisterOperationResult execute() {
        //返回处理结果
        return new RegisterOperationResult();
    }

    @Override
    public void decode(ByteBuf msg) {
        this.substationAddr = msg.readByte();
    }
}
