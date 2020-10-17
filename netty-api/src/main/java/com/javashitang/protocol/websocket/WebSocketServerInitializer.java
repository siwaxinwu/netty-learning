package com.javashitang.protocol.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author lilimin
 * @since 2020-10-14
 */
public class WebSocketServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // 基于http协议，使用http的编码和解码器
        pipeline.addLast(new HttpServerCodec());
        // 是以块方式写，添加ChunkedWrite处理器
        pipeline.addLast(new ChunkedWriteHandler());
        // http数据在传输过程中是分段的，HttpObjectAggregator可以将多个段聚合
        // 这就是为什么当浏览器发送大量数据时，就会发送多个http请求
        pipeline.addLast(new HttpObjectAggregator(8192));
        // WebSocketServerProtocolHandler 是将http协议升级为websocket协议，保持长连接
        pipeline.addLast(new WebSocketServerProtocolHandler("/websocket"));
        pipeline.addLast(new TextWebSocketFrameHandler());
    }
}
