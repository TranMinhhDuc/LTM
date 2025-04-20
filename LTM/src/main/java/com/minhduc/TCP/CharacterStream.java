package com.minhduc.TCP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class CharacterStream {

    static String host = "203.162.10.109";
    public static void main(String[] args) {
        int port = 2208;
        int timeout = 5000;

        try (Socket socket = new Socket()){
            socket.connect(new InetSocketAddress(host, port), timeout);
            socket.setSoTimeout(timeout);

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String message = "B21DCCN256;aQpIb32R";
            writer.write(message);
            writer.newLine();
            writer.flush();

            String res = reader.readLine();
            System.out.println(res);

            String[] arr = res.split(", ");
            String str  = "";
            for (int i = 0; i < arr.length; i++) {
                if(arr[i].trim().endsWith("edu")){
                    str += arr[i] + ", ";
                }
            }

            System.out.print(str.substring(0, str.length() - 2));
            writer.write(str.substring(0, str.length() - 2));
            writer.newLine();
            writer.flush();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
