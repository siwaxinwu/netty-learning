package com.javashitang.quickStart;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    // 客户端读取到数据
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        log.info("client channelRead0");
        System.out.println("client accept: " + byteBuf.toString(CharsetUtil.UTF_8));
    }

    // channel可用时做的事情
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("client channelActive");
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello netty", CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
