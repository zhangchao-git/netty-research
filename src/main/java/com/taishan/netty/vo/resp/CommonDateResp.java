package com.taishan.netty.vo.resp;

import cn.hutool.core.date.DateUtil;
import com.taishan.netty.vo.DataPacket;
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
        int month = DateUtil.month(date);
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

        bb.writeByte(year);
        bb.writeByte(month);
        bb.writeByte(day);
        bb.writeByte(hour);
        bb.writeByte(minute);
        bb.writeByte(second);
    }

    public CommonDateResp(DataPacket dataPacket) {
        super.header.setVersion(dataPacket.getHeader().getVersion());
        super.header.setSerialNumber(dataPacket.getHeader().getSerialNumber());
        super.header.setReceiveAddr(dataPacket.getHeader().getSendAddr());
        super.header.setSendAddr(dataPacket.getHeader().getReceiveAddr());
        super.header.setOpCode(dataPacket.getHeader().getOpCode());
    }
}
