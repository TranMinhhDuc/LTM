package org.example.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ByteStream {

    public static void main(String[] args) {
        int timeOut = 5000; //5s
        int port =2206;
        String serverAddress = "203.162.10.109";

        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(serverAddress, port), timeOut);

            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();

            String message = "B21DCCN256;2DmFbqP4";
            output.write(message.getBytes());
            output.flush();

            byte[] binaryIn = new byte[1024];
            input.read(binaryIn);
            String str = new String(binaryIn).trim();
            output.write(sum(str).getBytes());
            output.flush();

            socket.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String sum(String strIn) {
        long sum = 0;
        String[] numbers = strIn.split("\\|");

        for (int i = 0; i < numbers.length; i ++) {
            sum += Integer.parseInt(numbers[i]);
        }
        return Long.toString(sum);
    }
}
