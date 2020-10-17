package com.javashitang.groupChat;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author lilimin
 * @since 2020-10-17
 */
@Slf4j
public class GroupChatClient {

    private String HOST = "127.0.0.1";
    private final int PORT = 8080;
    private Selector selector;
    private SocketChannel channel;
    private String username;

    @SneakyThrows
    public GroupChatClient() {
        selector = Selector.open();
        channel = SocketChannel.open(new InetSocketAddress(HOST, PORT));
        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_READ);
        username = channel.getLocalAddress().toString();
        System.out.println(username + " is ok");
    }


    @SneakyThrows
    public void sendInfo(String info) {
        info = username + " 说：" + info;
        channel.write(ByteBuffer.wrap(info.getBytes()));
    }

    @SneakyThrows
    public void readInfo() {
        log.info("start readInfo");
        int count = selector.select();
        if (count > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    channel.read(byteBuffer);
                    String msg = new String(byteBuffer.array());
                    System.out.println(msg);
                }
            }
        } else {
            System.out.println("没有可用通道");
        }
    }

    public static void main(String[] args) {
        GroupChatClient client = new GroupChatClient();
        new Thread(() -> client.readInfo()).start();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            client.sendInfo(line);
        }
    }
}
