package com.company;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(22223);
        System.out.println("Server Started..");


            Socket socket = serverSocket.accept();
            System.out.println("Client connected..");
            new ServerThread(socket);
    }
}

class ServerThread implements Runnable {

    Socket clientSocket;
    Thread t;

    ServerThread(Socket clientSocket){
        this.clientSocket = clientSocket;
        t = new Thread(this);
        t.start();
    }


    @Override
    public void run() {

        try {
            DataInputStream ois = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream oos = new DataOutputStream(clientSocket.getOutputStream());

            while (true) {
                //read from client...
                String cMsg = ois.readUTF();
                if(cMsg==null)
                    break;
                System.out.println("From Client: " + cMsg);
                if(cMsg.toLowerCase().contains("pass")){
                    File file = new File("D:\\PRAPTI\\JavaApp\\Intellij\\images1.jpg");
                    FileInputStream fis = new FileInputStream(file);
                    int file_l = (int)file.length();
                    byte b[] = new byte[file_l];
                    fis.read(b, 0, file_l);
                    oos.writeInt(file_l);
                    oos.write(b, 0, file_l);
                }
                cMsg = cMsg.toUpperCase();
                //send to client..
                oos.writeUTF(cMsg);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}