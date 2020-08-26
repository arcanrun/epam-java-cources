package com.epam.university.java.core.task031;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ClientImpl implements Client {
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;

    @Override
    public void sendMessage(String message) {
        try {
            System.out.println("CLIENT SAYS: " + message);
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start() {
        try {
            socket = new Socket(InetAddress.getLocalHost(), ServerImpl.PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            try {
                socket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            e.printStackTrace();
        }
//        new Thread(() -> {
//            try {
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }).start();
    }

    @Override
    public void stop() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
