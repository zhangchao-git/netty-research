package com.taishan.iot.netty.server.handler;

import cn.hutool.core.date.DateUtil;
import com.taishan.iot.dao.RegisterHeartbeatRecordMapper;
import com.taishan.iot.model.entity.RegisterHeartbeatRecord;
import com.taishan.iot.netty.model.req.RegisterMsg;
import com.taishan.iot.netty.model.resp.CommonDateResp;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ChannelHandler.Sharable
public class RegisterMsgHandler extends SimpleChannelInboundHandler<RegisterMsg> {

    @Autowired
    private RegisterHeartbeatRecordMapper registerHeartbeatRecordMapper;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RegisterMsg msg) throws Exception {
        //存储数据
        RegisterHeartbeatRecord record = new RegisterHeartbeatRecord();
        record.setCrdate(DateUtil.date());
        record.setOpCode(msg.getHeader().getOpCode());
        record.setSubstationAddr(msg.getSubstationAddr());
        registerHeartbeatRecordMapper.insert(record);

        CommonDateResp commonDateResp = new CommonDateResp(msg);
        ctx.writeAndFlush(commonDateResp);
    }
}
