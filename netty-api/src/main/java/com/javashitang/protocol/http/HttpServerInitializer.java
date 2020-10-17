package com.javashitang.protocol.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author lilimin
 * @since 2020-10-14
 */
public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // netty 提供的编解码器
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpServerHandler());
    }
}
