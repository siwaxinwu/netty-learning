package com.javashitang.nioDemo;

import org.junit.Test;
import org.msgpack.MessagePack;
import org.msgpack.template.Templates;

import java.io.IOException;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lilimin
 * @since 2020-09-26
 */
public class MsgpackDemo {

    @Test
    public void test1() throws IOException {
        List<String> source = new ArrayList<>();
        source.add("one");
        source.add("two");
        source.add("three");
        MessagePack messagePack = new MessagePack();
        byte[] raw = messagePack.write(source);
        List<String> target = messagePack.read(raw, Templates.tList(Templates.TString));
        // [one, two, three]
        System.out.println(target);
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
