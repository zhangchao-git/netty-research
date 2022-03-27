package com.taishan.iot.netty.server.handler;

import cn.hutool.core.date.DateUtil;
import com.taishan.iot.dao.TestDao;
import com.taishan.iot.model.entity.Test;
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
    private TestDao testDao;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RegisterMsg msg) throws Exception {
        log.error(" 注册包：" + msg.toString() + "\n");
        //存储数据
        Test test = new Test();
        test.setCrdate(DateUtil.date());
        test.setData(msg.toString());
        testDao.insert(test);

        CommonDateResp commonDateResp = new CommonDateResp(msg);
        ctx.writeAndFlush(commonDateResp);
    }
}
