package com.javashitang.bioDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * @author lilimin
 * @since 2020-09-26
 */
public class BioClient {

    public static final String host = "127.0.0.1";
    public static final int port = 8080;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(host, port);
        while (true) {
            try {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println("now is " + System.currentTimeMillis());
                TimeUnit.SECONDS.sleep(2);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println(in.readLine());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
