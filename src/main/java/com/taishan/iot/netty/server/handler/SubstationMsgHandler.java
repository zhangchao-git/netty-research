package com.taishan.iot.netty.server.handler;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import com.taishan.iot.dao.TestDao;
import com.taishan.iot.model.entity.Test;
import com.taishan.iot.netty.model.req.SubstationMsg;
import com.taishan.iot.netty.model.resp.CommonByteResp;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ChannelHandler.Sharable
public class SubstationMsgHandler extends SimpleChannelInboundHandler<SubstationMsg> {

    @Autowired
    private TestDao testDao;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SubstationMsg msg) throws Exception {
        FileUtil.appendUtf8String(DateUtil.format(DateUtil.date(), DatePattern.NORM_DATETIME_FORMATTER) + " 分站数据包：" + msg.toString() + "\n", "C:/nettylog/" + DateUtil.formatDate(DateUtil.date()) + ".txt");
        //存储数据
        Test test = new Test();
        test.setCrdate(DateUtil.date());
        test.setData(msg.toString());
        testDao.insert(test);

        CommonByteResp commonDateResp = new CommonByteResp(msg);
        ctx.writeAndFlush(commonDateResp);
    }
}
