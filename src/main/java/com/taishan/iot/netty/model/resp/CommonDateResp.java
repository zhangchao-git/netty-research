package com.taishan.iot.netty.model.resp;

import cn.hutool.core.codec.BCD;
import cn.hutool.core.date.DateUtil;
import com.taishan.iot.netty.model.DataPacket;
import io.netty.buffer.ByteBuf;
import lombok.Data;

import java.util.Date;

@Data
public class CommonDateResp extends DataPacket {

    private byte year;

    private byte month;

    private byte day;

    private byte hour;

    private byte minute;

    private byte second;

    @Override
    public void toByteBufMsg(ByteBuf bb) {
        super.toByteBufMsg(bb);
        Date date = DateUtil.date();
        //获取年份后两位，超过2099年会有问题. but, i don't care
        int year = DateUtil.year(date) - 2000;
        int month = DateUtil.month(date) + 1;
        int day = DateUtil.dayOfMonth(date);
        int hour = DateUtil.hour(date, true);
        int minute = DateUtil.minute(date);
        int second = DateUtil.second(date);

        this.year = (byte) year;
        this.month = (byte) month;
        this.day = (byte) day;
        this.hour = (byte) hour;
        this.minute = (byte) minute;
        this.second = (byte) second;

        bb.writeByte(BCD.strToBcd(year + "")[0]);
        bb.writeByte(BCD.strToBcd(month + "")[0]);
        bb.writeByte(BCD.strToBcd(day + "")[0]);
        bb.writeByte(BCD.strToBcd(hour + "")[0]);
        bb.writeByte(BCD.strToBcd(minute + "")[0]);
        bb.writeByte(BCD.strToBcd(second + "")[0]);
    }

    public CommonDateResp(DataPacket dataPacket) {
        super.header.setVersion(dataPacket.getHeader().getVersion());
        super.header.setSerialNumber(dataPacket.getHeader().getSerialNumber());
        super.header.setReceiveAddr(dataPacket.getHeader().getSendAddr());
        super.header.setSendAddr(dataPacket.getHeader().getReceiveAddr());
        super.header.setOpCode(dataPacket.getHeader().getOpCode());
    }
}
