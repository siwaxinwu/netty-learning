package com.javashitang.nioDemo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import org.junit.Test;

/**
 * @author lilimin
 * @since 2020-10-18
 */
public class ReactorDemo {

    // 单Reactor单线程
    @Test
    public void test1() {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup(1);
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(eventLoopGroup);
    }

    // 单Reactor多线程
    // EventLoopGroup不指定线程数的话默认是cpu核数的2倍
    @Test
    public void test2() {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(eventLoopGroup);
    }

    // 主从Reactor多线程
    @Test
    public void test3() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup);
    }
}
