package org.example.tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class CharacterStream {
    public static void main(String[] args) {
        String serverAddress = "203.162.10.109";
        int port = 2208;
        int timeOut = 5000; // 5s
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(serverAddress, port), timeOut);

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String message = "B21DCCN256;Iaz1FJDs";
            writer.write(message);
            writer.newLine();
            writer.flush();

            String dataIn = reader.readLine();
            writer.write(normalCharacter(dataIn));
            writer.newLine();
            writer.write(specialCharacter(dataIn));
            writer.flush();

            socket.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String normalCharacter(String data) {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < data.length(); i++) {
            char c = data.charAt(i);
            if (Character.isLetterOrDigit(c)){
                str.append(c);
            }
        }
        return str.toString();
    }

    private static String specialCharacter(String data) {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i< data.length(); i++) {
            char c = data.charAt(i);
            if (!Character.isLetterOrDigit(c)) {
                str.append(c);
            }
        }
        return str.toString();
    }
}
