package com.umbrella.grammar.socket;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Socket服务端
 * Created by 大洲 on 15-6-6.
 */
public class MyServer {

    private static final Logger log = Logger.getLogger(MyServer.class);

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(8888);
            while(true) {
                log.info("waiting for connect");
                Socket client = server.accept();
                log.info("user access, " + client.getRemoteSocketAddress());
                OutputStream os = client.getOutputStream();
                os.write("Hello Android!".getBytes());
                client.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
