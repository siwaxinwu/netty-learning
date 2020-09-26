package com.javashitang.bioDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lilimin
 * @since 2020-09-26
 */
public class BioServer {

    public static final int port = 8080;

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(port);
        while (true) {
            Socket socket = server.accept();
            new Thread(() -> {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
                    String line = null;
                    while ((line = in.readLine()) != null) {
                        String msg = "Server端回复 " + line;
                        out.println(msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
