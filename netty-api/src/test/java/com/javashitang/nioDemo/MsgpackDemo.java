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
}
