package org.example.udp;

import UDP.Product;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPObject {
    public static void main(String[] args) {
        String serverAddress = "203.162.10.109";
        int port = 2209;
        try {
            DatagramSocket socket = new DatagramSocket();

            String message = ";B21DCCN256;QQRB4uHZ";
            DatagramPacket packet = new DatagramPacket(message.getBytes(), message.length(), InetAddress.getByName(serverAddress), port);
            socket.send(packet);

            byte[] buf = new byte[1024];
            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);

            byte[] dataIn = packet.getData();
            String requestId = new String(dataIn, 0, 8).trim();
            System.out.println(requestId);
            ByteArrayInputStream bais = new ByteArrayInputStream(dataIn, 8, packet.getLength()-8);
            ObjectInputStream input = new ObjectInputStream(bais);
            Product product = (Product) input.readObject();

            product.setName(normalizeName(product.getName()));
            product.setQuantity(normalizeQuantity(product.getQuantity()));

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(product);
            oos.flush();

            byte[] productBytes = baos.toByteArray();
            byte[] requestIdBytes = requestId.getBytes();
            byte[] outputData = new byte[requestIdBytes.length + productBytes.length];
            System.arraycopy(requestIdBytes, 0, outputData, 0, requestIdBytes.length);
            System.arraycopy(productBytes, 0, outputData, requestIdBytes.length, productBytes.length);

            packet = new DatagramPacket(outputData, outputData.length, InetAddress.getByName(serverAddress), port);
            socket.send(packet);
            socket.close();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String normalizeName(String name) {
        StringBuilder normalName = new StringBuilder();

        String[] str = name.split("\\s+");
        normalName.append(str[str.length - 1]).append(" ");
        for (int i = 1; i < str.length-1; i++) {
            normalName.append(str[i]).append(" ");

        }
        normalName.append(str[0]);

        return normalName.toString().trim();
    }

    private static int normalizeQuantity(int quantity) {
        String str = Integer.toString(quantity);

        return Integer.parseInt(new StringBuilder(str).reverse().toString().trim());
    }
}
