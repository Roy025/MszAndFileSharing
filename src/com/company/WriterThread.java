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
    public void run() {
        try {
            DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
            DataInputStream ois = new DataInputStream(socket.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                try {
                    String message = br.readLine();
                    if (message.toLowerCase().contains("pass")) {
                        System.out.println("Positon - ");
                        String path = br.readLine();
                        File file = new File(path);
                        oos.writeUTF("Pass " + file.getName());
                        FileInputStream fis = new FileInputStream(file.getPath());
                        int file_l = (int) file.length();
                        byte b[] = new byte[file_l];
                        fis.read(b, 0, file_l);
                        oos.writeInt(file_l);
                        oos.write(b, 0, file_l);
                    }
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
