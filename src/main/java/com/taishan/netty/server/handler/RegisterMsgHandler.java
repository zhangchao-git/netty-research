package com.taishan.netty.server.handler;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import com.taishan.netty.vo.req.RegisterMsg;
import com.taishan.netty.vo.resp.CommonResp;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: Zpsw
 * @Date: 2019-05-15
 * @Description: 鉴权消息->CommonResp
 * @Version: 1.0
 */

@Slf4j
@Component
@ChannelHandler.Sharable
public class RegisterMsgHandler extends SimpleChannelInboundHandler<RegisterMsg> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RegisterMsg msg) throws Exception {
        FileUtil.appendUtf8String("注册包：" + msg.toString() + "\n", "C:/nettylog/" + DateUtil.formatDate(DateUtil.date()) + ".txt");
        CommonResp commonResp = new CommonResp(msg);
        ctx.writeAndFlush(commonResp);
    }
}
