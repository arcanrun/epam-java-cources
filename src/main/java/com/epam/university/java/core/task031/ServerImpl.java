package com.epam.university.java.core.task031;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

public class ServerImpl implements Server {
    private ServerSocket serverSocket;
    public static final int PORT = 8189;
    public static final String HOST = "localhost";
    private DataInputStream in;
    private DataOutputStream out;
    private Vector<String> messages;
    private Vector<ServerClient> clients;

    @Override
    public String readMessage() {
        if (messages.size() == 0) {
            return "";
        }
        return messages.remove(messages.size() - 1);
    }

    /**
     * ServerImpl.
     */

    public ServerImpl() {
        messages = new Vector<>();
        clients = new Vector<>();
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start() {
        new Thread(() -> {
            try {
                while (true) {
                    System.out.println("waiting for connection...");
                    Socket socket = serverSocket.accept();
                    System.out.println("New client has been connected: "
                            + socket.getInetAddress()
                            + " "
                            + socket.getPort());
                    new ServerClient(this, socket);
                }
            } catch (IOException e) {
                try {
                    serverSocket.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                e.printStackTrace();
            }
        }).start();

    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void putMessage(String message) {
        System.out.println("Message has been received!!!===>" + message);
        messages.add(message);
    }

    public void addClient(ServerClient serverClient) {
        clients.add(serverClient);
    }

    @Override
    public void stop() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
