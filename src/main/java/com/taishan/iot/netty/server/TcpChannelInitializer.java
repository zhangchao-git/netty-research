package com.taishan.iot.netty.server;

import com.taishan.iot.netty.codec.TernimalFrameDecoder;
import com.taishan.iot.netty.codec.TernimalFrameEncoder;
import com.taishan.iot.netty.codec.TernimalProtocolDecoder;
import com.taishan.iot.netty.codec.TernimalProtocolEncoder;
import com.taishan.iot.netty.server.handler.RegisterMsgHandler;
import com.taishan.iot.netty.server.handler.SubstationMsgHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.EventExecutorGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @Author: Zpsw
 * @Date: 2019-05-15
 * @Description:
 * @Version: 1.0
 */
@Component
public class TcpChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Autowired
    @Qualifier("businessGroup")
    private EventExecutorGroup businessGroup;

    @Autowired
    private RegisterMsgHandler registerMsgHandler;

    @Autowired
    private SubstationMsgHandler substationMsgHandler;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new TernimalFrameDecoder());
        pipeline.addLast(new TernimalFrameEncoder());

        pipeline.addLast(new TernimalProtocolEncoder());
        pipeline.addLast(new TernimalProtocolDecoder());

        pipeline.addLast(new LoggingHandler(LogLevel.ERROR));

        pipeline.addLast(businessGroup, registerMsgHandler);
        pipeline.addLast(businessGroup, substationMsgHandler);

    }

}
