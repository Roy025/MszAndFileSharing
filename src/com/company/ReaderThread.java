package com.company;

import java.io.*;
import java.net.Socket;

public class ReaderThread implements Runnable {
    String Name;
    Socket socket;

    ReaderThread(Socket socket, String name) {
        this.socket = socket;
        this.Name = name;
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
            DataInputStream ois = new DataInputStream(socket.getInputStream());

            while (true) {
                try {
                    String received = ois.readUTF();
                    if (received.toLowerCase().contains("pass")) {
                        File file = new File("D:\\PRAPTI\\JavaApp\\Intellij\\images2.jpg");
                        FileOutputStream fis = new FileOutputStream(file);
                        int file_l = ois.readInt();
                        byte b[] = new byte[file_l];
                        ois.readFully(b, 0, file_l);
                        fis.write(b, 0, file_l);
                        fis.close();
                        System.out.println("File Sent");
                    }
                    System.out.println(Name + "Got: " + received);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
