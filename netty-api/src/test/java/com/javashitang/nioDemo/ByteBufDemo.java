package com.javashitang.nioDemo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Test;

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
}
