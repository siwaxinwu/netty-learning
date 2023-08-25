package com.javashitang.practice.tuning;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@ChannelHandler.Sharable
public class ClientBusinessHandler extends SimpleChannelInboundHandler<ByteBuf> {

    public static final ChannelHandler INSTANCE = new ClientBusinessHandler();

    /**
     * 开始发起请求的时间
     */
    private static AtomicLong beginTime = new AtomicLong(0);

    /**
     * 总的响应时间
     */
    private static AtomicLong totalResponseTime = new AtomicLong(0);

    /**
     * 总的响应次数
     */
    private static AtomicLong totalRequest = new AtomicLong(0);

    /**
     * qps: 每秒的查询量
     */
    public static final Thread thread = new Thread(() -> {
        while (true) {
            long duration = System.currentTimeMillis() - beginTime.get();
            if (duration != 0) {
                System.out.println("qps: " + 1000 * totalRequest.get() / duration
                        + " avg response time: " + ((float) totalResponseTime.get()) / totalRequest.get());
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        totalResponseTime.addAndGet(System.currentTimeMillis() - msg.readLong());
        totalRequest.incrementAndGet();
        if (beginTime.compareAndSet(0, System.currentTimeMillis())) {
            thread.start();
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 每隔1秒发送一次请求
        ctx.executor().scheduleAtFixedRate(() -> {
            runTask(ctx);
        }, 0, 1, TimeUnit.SECONDS);
    }

    public void runTask(ChannelHandlerContext ctx){
        ByteBuf byteBuf = ctx.alloc().ioBuffer();
        byteBuf.writeLong(System.currentTimeMillis());
        ctx.channel().writeAndFlush(byteBuf);
    }
}
