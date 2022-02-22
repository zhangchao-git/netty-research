package com.taishan.netty.common.register;

import cn.hutool.core.date.DateUtil;
import com.taishan.netty.common.OperationResult;
import io.netty.buffer.ByteBuf;
import lombok.Data;

import java.util.Date;

@Data
public class RegisterOperationResult extends OperationResult {

    private final byte year;

    private final byte month;

    private final byte day;

    private final byte hour;

    private final byte minute;

    private final byte second;

    public RegisterOperationResult() {

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
    }

    @Override
    public void encode(ByteBuf byteBuf) {
        byteBuf.writeByte(year);
        byteBuf.writeByte(month);
        byteBuf.writeByte(day);
        byteBuf.writeByte(hour);
        byteBuf.writeByte(minute);
        byteBuf.writeByte(second);
    }
}
