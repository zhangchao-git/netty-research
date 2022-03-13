package com.taishan.netty.server.handler;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import com.taishan.netty.vo.req.SubstationMsg;
import com.taishan.netty.vo.resp.CommonByteResp;
import com.taishan.netty.vo.resp.CommonDateResp;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ChannelHandler.Sharable
public class SubstationMsgHandler extends SimpleChannelInboundHandler<SubstationMsg> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SubstationMsg msg) throws Exception {
        FileUtil.appendUtf8String(DateUtil.format(DateUtil.date(), DatePattern.NORM_DATETIME_FORMATTER) + " 分站数据包：" + msg.toString() + "\n", "C:/nettylog/" + DateUtil.formatDate(DateUtil.date()) + ".txt");
        CommonByteResp commonDateResp = new CommonByteResp(msg);
        ctx.writeAndFlush(commonDateResp);
    }
}
