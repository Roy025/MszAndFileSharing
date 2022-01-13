package com.company;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class WriterThread implements Runnable {
    private DataOutputStream oos;
    private String Name;
    Socket socket;
    WriterThread(Socket socket, String name) {
        this.socket = socket;
        this.Name = name;
        new Thread(this).start();
    }

    @Override
    public void run(){
        try {
            DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
            DataInputStream ois = new DataInputStream(socket.getInputStream());

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            try {
                String message = br.readLine();
                oos.writeUTF(message);
                System.out.println("Message Sent");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
