package com.javashitang.nioDemo;

import org.junit.Test;

/**
 * @author lilimin
 * @since 2020-10-11
 *
 * selector能够检测多个注册的通道上是否有事件发生
 * 只有当通道上有读写事件发生时，才会进行读写，并且不必为每个连接创建一个线程，不用维护多个线程，避免了多线程上下文切换导致的开销
 * Netty的IO线程NioEventLoop聚合了Selector，同时可以处理成百上千个客户端连接
 *
 * 1. 当有客户端连时，会通过ServerSocketChannel得到SocketChannel
 * 2. 将SocketChannel注册到Selector，一个Selector可以注册多个SocketChannel
 * 3. 注册后返回一个SelectionKey，会和该Selector关联
 * 4. Selector进行监听，返回有事件发生的SelectionKey
 * 5. 通过SelectionKey反向获取SocketChannel
 * 6. 通过SocketChannel完成业务处理
 */
public class SelectorDemo {

    @Test
    public void test1() {

    }
}
