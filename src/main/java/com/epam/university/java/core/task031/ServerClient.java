package com.epam.university.java.core.task031;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerClient {
    private DataInputStream in;
    private DataOutputStream out;
    private ServerImpl server;

    /**
     * ServerClinet.
     */

    public ServerClient(ServerImpl server, Socket socket) {
        this.server = server;
        this.server.addClient(this);
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            while (true) {
                try {
                    String str = in.readUTF();
                    System.out.println("SERVER CLIENT HAS BEEN ACCEPTED MESSAGE: " + str);
                    server.putMessage(str);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


}
