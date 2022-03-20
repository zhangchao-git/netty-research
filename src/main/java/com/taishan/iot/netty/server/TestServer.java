package com.taishan.iot.netty.server;

import com.taishan.iot.netty.codec.TernimalFrameDecoder;
import com.taishan.iot.netty.codec.TernimalFrameEncoder;
import com.taishan.iot.netty.codec.TernimalProtocolDecoder;
import com.taishan.iot.netty.codec.TernimalProtocolEncoder;
import com.taishan.iot.netty.server.handler.RegisterMsgHandler;
import com.taishan.iot.netty.server.handler.SubstationMsgHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.util.concurrent.ExecutionException;

public class TestServer {

    /**
     * TODO 初始化改造
     * TODO ByteBuf 手动管理
     * TODO CRC校验
     *
     * @param args
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.channel(NioServerSocketChannel.class);

        serverBootstrap.handler(new LoggingHandler(LogLevel.INFO));
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            serverBootstrap.group(group);

            serverBootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();

                    pipeline.addLast(new TernimalFrameDecoder());
                    pipeline.addLast(new TernimalFrameEncoder());

                    pipeline.addLast(new TernimalProtocolEncoder());
                    pipeline.addLast(new TernimalProtocolDecoder());

                    pipeline.addLast(new LoggingHandler(LogLevel.INFO));

                    pipeline.addLast(new RegisterMsgHandler());
                    pipeline.addLast(new SubstationMsgHandler());
                }
            });

            ChannelFuture channelFuture = serverBootstrap.bind(8090).sync();

            channelFuture.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }


    }

}
