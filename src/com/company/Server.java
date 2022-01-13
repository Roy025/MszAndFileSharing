package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(22223);
        System.out.println("Server Started..");
        Socket socket = serverSocket.accept();
        System.out.println("Client connected..");
        new WriterThread(socket, "Server : ");
        new ReaderThread(socket, "Server : ");
    }
}


