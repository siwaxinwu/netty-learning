package com.javashitang.nioDemo;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author lilimin
 * @since 2020-09-26
 */
public class ChannelDemoTest {

    @Test
    public void test1() throws Exception {
        String content = "hello nio";
        String fileName = "";
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        FileChannel writeChannel = fileOutputStream.getChannel();
        ByteBuffer writeBuffer = ByteBuffer.allocate(content.getBytes().length);
        writeBuffer.put(content.getBytes());
        writeBuffer.flip();
        writeChannel.write(writeBuffer);
        fileOutputStream.close();

        File file = new File(fileName);
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel readChannel = fileInputStream.getChannel();
        ByteBuffer readBuffer = ByteBuffer.allocate((int)file.length());
        readChannel.read(readBuffer);
        System.out.println(new String(readBuffer.array()));
        fileInputStream.close();
    }


    @Test
    public void test2() throws Exception {
        FileInputStream fileInputStream = new FileInputStream("");
        FileChannel readChannel = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("");
        FileChannel writeChannel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        while (true) {
            byteBuffer.clear();
            int read = readChannel.read(byteBuffer);
            if (read == - 1) {
                break;
            }
            byteBuffer.flip();
            writeChannel.write(byteBuffer);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }
}
