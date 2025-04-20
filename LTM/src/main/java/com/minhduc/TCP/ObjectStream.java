package com.minhduc.TCP;

import TCP.Product;

import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import static com.minhduc.TCP.CharacterStream.host;

public class ObjectStream {
    public static void main(String[] args) {
        int port = 2209;
        int timeout = 5000;

        try (Socket socket = new Socket()){
            socket.connect(new InetSocketAddress(host, port), timeout);
            socket.setSoTimeout(timeout);

            ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());

            String message = "B21DCCN256;fI8IdbIn";
            writer.writeObject(message);
            writer.flush();

            Product product = (Product) reader.readObject();

            System.out.println((int) Math.floor(product.getPrice()));

            String str = String.valueOf((int) Math.floor(product.getPrice()));
            int discount = 0;
            for (int i = 0; i < str.length(); i++) {
                char x = str.charAt(i);
                discount += x - '0';
            }

            System.out.println(discount);
            product.setDiscount(discount);

            writer.writeObject(product);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
