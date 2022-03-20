package com.taishan.iot.netty.client;

import com.taishan.iot.netty.codec.TernimalFrameDecoder;
import com.taishan.iot.netty.codec.TernimalFrameEncoder;
import com.taishan.iot.netty.codec.TernimalProtocolDecoder;
import com.taishan.iot.netty.codec.TernimalProtocolEncoder;
import com.taishan.iot.netty.model.req.RegisterMsg;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.util.concurrent.ExecutionException;

import static com.taishan.iot.netty.constant.Constant.OP_CODE_REGISTER;

public class ClientV0 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.channel(NioSocketChannel.class);

        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            bootstrap.group(group);

            bootstrap.handler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    pipeline.addLast(new TernimalFrameDecoder());
                    pipeline.addLast(new TernimalFrameEncoder());

                    pipeline.addLast(new TernimalProtocolEncoder());
                    pipeline.addLast(new TernimalProtocolDecoder());

                    pipeline.addLast(new LoggingHandler(LogLevel.INFO));
                }
            });

            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8090);

            channelFuture.sync();

            RegisterMsg requestMessage = new RegisterMsg();
            requestMessage.getHeader().setVersion((byte) 1);
            requestMessage.getHeader().setSerialNumber((short) 2);
            requestMessage.getHeader().setReceiveAddr((short) 3);
            requestMessage.getHeader().setSendAddr((short) 4);
            requestMessage.getHeader().setOpCode(OP_CODE_REGISTER);
            requestMessage.setSubstationAddr((byte) 58);

            channelFuture.channel().writeAndFlush(requestMessage);

            channelFuture.channel().closeFuture().sync();

        } finally {
            group.shutdownGracefully();
        }
    }

}
