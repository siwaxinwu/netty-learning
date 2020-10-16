package com.javashitang.practice.length;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * @author lilimin
 * @since 2020-10-16
 */
public class ToLongDecoder extends ReplayingDecoder<Void> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // 不需要判断数据是否足够读取，ReplayingDecoder类会进行判断
        out.add(in.readLong());
    }
}
