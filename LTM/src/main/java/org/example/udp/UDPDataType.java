package org.example.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class UDPDataType {
    public static void main(String[] args) {
        String serverAddress = "203.162.10.109";
        int port = 2207;
        
        try {
            DatagramSocket socket = new DatagramSocket();

            String message = ";B21DCCN256;qGdYrn8R";
            DatagramPacket packet = new DatagramPacket(message.getBytes(), message.length(), InetAddress.getByName(serverAddress), port);
            socket.send(packet);

            byte[] buf = new byte[1024];
            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);

            String[] dataIn = new String(packet.getData()).trim().split(";");
            String[] numbers = dataIn[1].split(",");

            Arrays.sort(numbers, (a, b) ->{
                return Integer.compare(Integer.parseInt(a), Integer.parseInt(b));
            });

            message = dataIn[0] + ";" + numbers[numbers.length - 2] + "," + numbers[1];
            packet = new DatagramPacket(message.getBytes(), message.length(), InetAddress.getByName(serverAddress), port);
            socket.send(packet);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
