package com.minhduc.TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class DataStream {
    public static void main(String[] args) {
        String host = "203.162.10.109";
        int port = 2207;
        int timeout = 5000;

        try(Socket socket = new Socket()){
            socket.connect(new InetSocketAddress(host, port), timeout);
            socket.setSoTimeout(timeout);

            DataInputStream reader = new DataInputStream(socket.getInputStream());
            DataOutputStream writer = new DataOutputStream(socket.getOutputStream());

            String message = "B21DCCN256;1pIWmaAf";
            writer.writeUTF(message);
            writer.flush();

            int a = reader.readInt();
            int b = reader.readInt();

            System.out.print(a + " " + b);

            writer.writeInt(a+b);
            writer.writeInt(a*b);
            writer.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
