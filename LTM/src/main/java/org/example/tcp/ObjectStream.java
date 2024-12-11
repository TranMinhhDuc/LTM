package org.example.tcp;

import TCP.Laptop;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ObjectStream {
    public static void main(String[] args) {
        String serverAddress = "203.162.10.109";
        int port = 2209;
        int timeOut = 5000; //5s;

        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(serverAddress, port), timeOut);

            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

            String message = "B21DCCN256;nlfXc33d";
            output.writeObject(message);
            output.flush();

            Laptop product = (Laptop) input.readObject();
            product.setName(normalizeName(product.getName()));
            product.setQuantity(normalizeQuantity(product.getQuantity()));

            output.writeObject(product);
            output.flush();
            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private static int normalizeQuantity(int quantity) {
        String str = Integer.toString(quantity);

        return Integer.parseInt(new StringBuilder(str).reverse().toString().trim());
    }

    private static String normalizeName(String name) {
        String[] str = name.split("\\s+");
        StringBuilder normalName = new StringBuilder();

        normalName.append(str[str.length - 1]).append(" ");
        for (int i = 1; i < str.length - 1; i++) {
            normalName.append(str[i]).append(" ");
        }
        normalName.append(str[0]);
        return normalName.toString().trim();
    }
}
