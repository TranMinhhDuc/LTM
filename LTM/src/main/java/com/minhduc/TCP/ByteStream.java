package com.minhduc.TCP;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import static com.minhduc.TCP.CharacterStream.host;

public class ByteStream {
    public static void main(String[] args) {
        int port = 2206;
        int timeout = 5000;

        try(Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(host, port), timeout);
            socket.setSoTimeout(timeout);

            InputStream reader = socket.getInputStream();
            OutputStream writer = socket.getOutputStream();

            String message = "B21DCCN256;pFZOZwqi";
            writer.write(message.getBytes());
            writer.flush();

            byte[] res = new byte[1024];
            reader.read(res);

            String strRes = new String(res).trim();
            System.out.print(strRes);

            String[] arr = strRes.split("\\|");
            long result = 0;
            for(String s:arr){
                result += Integer.parseInt(s);
            }

            writer.write(String.valueOf(result).getBytes());
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
