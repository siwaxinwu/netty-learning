package com.javashitang.nioDemo;

import lombok.SneakyThrows;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author lilimin
 * @since 2020-10-12
 */
public class NioClient {

    @SneakyThrows
    public static void main(String[] args) {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8080);
        if (!socketChannel.connect(address)) {
            // 连接需要时间，客户端不会阻塞
            while (!socketChannel.finishConnect()) {

            }
        }
        // 连接成功
        String str = "hello netty";
        ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
        // 发送数据
        socketChannel.write(buffer);
        System.in.read();
    }
}
