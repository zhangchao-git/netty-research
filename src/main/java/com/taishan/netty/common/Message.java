package com.taishan.netty.common;

import io.netty.buffer.ByteBuf;
import lombok.Data;

@Data
public abstract class Message<T extends MessageBody> {

    private MessageHeader messageHeader;

    private T messageBody;

    public T getMessageBody() {
        return messageBody;
    }

    public void encode(ByteBuf byteBuf) {
        //TODO  怎样判断数据区长度,长度是否需要写出去, 起始、结束魔数是否需要写出去
        byteBuf.writeShort(Constant.PROTOCOL_START);
        byteBuf.writeShort(1);
        byteBuf.writeByte(messageHeader.getVersion());
        byteBuf.writeShort(messageHeader.getSerialNumber());
        byteBuf.writeShort(messageHeader.getReceiveAddr());
        byteBuf.writeShort(messageHeader.getSendAddr());
        byteBuf.writeByte(messageHeader.getOpCode());

        //TODO 写出数据
        Class<T> bodyClazz = getMessageBodyDecodeClass(messageHeader.getOpCode());
        messageBody.encode(byteBuf);

        //结尾, 校验和
        byteBuf.writeShort(1);
        byteBuf.writeShort(Constant.PROTOCOL_END);
    }

    public abstract Class<T> getMessageBodyDecodeClass(int opcode);

    public void decode(ByteBuf msg) {
        short length = msg.readShort();
        byte version = msg.readByte();
        short serialNumber = msg.readShort();
        short receiveAddr = msg.readShort();
        short sendAddr = msg.readShort();
        byte opCode = msg.readByte();

        MessageHeader messageHeader = new MessageHeader();
        messageHeader.setLength(length);
        messageHeader.setVersion(version);
        messageHeader.setSerialNumber(serialNumber);
        messageHeader.setReceiveAddr(receiveAddr);
        messageHeader.setSendAddr(sendAddr);
        messageHeader.setOpCode(opCode);

        //TODO 解析数据
        ByteBuf bodyByteBuf = msg.readBytes(length);
        messageBody.decode(bodyByteBuf);

        short checkSum = msg.readShort();
        byte endCode = msg.readByte();
        messageHeader.setCheckSum(checkSum);
        messageHeader.setEndCode(endCode);
        this.messageHeader = messageHeader;

    }

}
