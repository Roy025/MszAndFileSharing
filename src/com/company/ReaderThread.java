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
            DataInputStream ois = new DataInputStream(socket.getInputStream());
            while (true) {
                try {
                    String received = ois.readUTF();
                    if (received.toLowerCase().contains("pass")) {
                        File file = new File("D:\\PRAPTI\\JavaApp\\Intellij\\images7.jpg");
                        FileOutputStream fis = new FileOutputStream(file);
                        int file_l = ois.readInt();
                        byte b[] = new byte[file_l];
                        ois.readFully(b, 0, file_l);
                        fis.write(b, 0, file_l);
                        System.out.println("File Sent");
                        fis.close();
                    }
                    System.out.println(Name + received);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
