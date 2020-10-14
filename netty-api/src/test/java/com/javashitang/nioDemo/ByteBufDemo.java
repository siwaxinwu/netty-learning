package com.javashitang.nioDemo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Test;

import java.nio.charset.Charset;

/**
 * @author lilimin
 * @since 2020-10-14
 */
public class ByteBufDemo {

    @Test
    public void test1() {
        ByteBuf byteBuf = Unpooled.buffer(10);
        byteBuf.writeInt(10);
        byteBuf.writeInt(20);
        int a = byteBuf.readInt();
        int b = byteBuf.readInt();
        System.out.println(a + " " + b);
    }

    @Test
    public void test2() {
        ByteBuf byteBuf = Unpooled.buffer(10);
        for (int i = 0; i < 5; i++) {
            byteBuf.writeByte(i);
        }
        for (int i = 0; i < byteBuf.readableBytes(); i++) {
            // 0 1 2 3 4
            System.out.println(byteBuf.getByte(i));
        }
    }

    @Test
    public void test3() {
        ByteBuf byteBuf = Unpooled.copiedBuffer("hello world", Charset.forName("utf-8"));
        if (byteBuf.hasArray()) {
            byte[] content = byteBuf.array();
            System.out.println(new String(content, Charset.forName("utf-8")));
            System.out.println(byteBuf);
            System.out.println(byteBuf.arrayOffset());
            System.out.println(byteBuf.readerIndex());
            System.out.println(byteBuf.writerIndex());
            System.out.println(byteBuf.capacity());
            // 可读字节数
            System.out.println(byteBuf.readableBytes());
            for (int i = 0; i < byteBuf.readableBytes(); i++) {
                System.out.println((char) byteBuf.getByte(i));
            }
            // hell
            System.out.println(byteBuf.getCharSequence(0, 4, Charset.forName("utf-8")));
        }
    }
}
