package org.example.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class DataStream {
    public static void main(String[] args) {
        String serverAddress = "203.162.10.109";
        int port = 2207;
        int timeOut = 5000; //5s

        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(serverAddress, port), timeOut);

            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            String message = "B21DCCN256;j5jIOkby";
            output.writeUTF(message);
            output.flush();

            int times = input.readInt();
            int[] numbers = new int[7];
            float[] result = new float[7];

            for (int i = 0; i < times; i++) {
                int number = input.readInt();
                numbers[number]++;
            }

            for(int i = 1; i <= 6; i++) {
                result[i] = (float) numbers[i]/times;
            }
            for (int i = 1; i <= 6; i++) {
                output.writeFloat(result[i]);
                output.flush();
            }

            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
