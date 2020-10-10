package com.javashitang.nioDemo;

import org.junit.Test;

import java.nio.IntBuffer;

/**
 * @author lilimin
 * @since 2020-09-26
 */
public class BufferDemoTest {

    @Test
    public void test1() {
        IntBuffer intBuffer = IntBuffer.allocate(5);
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i);
        }
        intBuffer.flip();
        while (intBuffer.hasRemaining()) {
            // 省略换行
            // 0 1 2 3 4
            System.out.println(intBuffer.get());
        }
    }

    @Test
    public void test2() {
        IntBuffer intBuffer = IntBuffer.allocate(5);
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i);
        }
        intBuffer.flip();
        intBuffer.position(1);
        intBuffer.limit(3);
        while (intBuffer.hasRemaining()) {
            // 1 2
            System.out.println(intBuffer.get());
        }
    }

    @Test
    public void test3() {
        IntBuffer intBuffer = IntBuffer.allocate(7);
        for (int i = 0; i < intBuffer.capacity() - 2; i++) {
            intBuffer.put(i);
        }
        intBuffer.flip();
        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }
        System.out.println();
        intBuffer.flip();
        intBuffer.put(6);
        intBuffer.put(7);
        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }
    }
}
