package org.example.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPString {
    public static void main(String[] args) {
        String serverAddress = "203.162.10.109";
        int port = 2208;

        try {
            DatagramSocket socket = new DatagramSocket();

            String message = ";B21DCCN256;b01gD2Pt";
            DatagramPacket packet = new DatagramPacket(message.getBytes(), message.length(), InetAddress.getByName(serverAddress), port);
            socket.send(packet);

            byte[] buf = new byte[1024];
            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);

            String[] dataIn = new String(packet.getData()).trim().split(";");
            message = dataIn[0] + ";" + proccessData(dataIn[1]);
            packet = new DatagramPacket(message.getBytes(), message.length(), InetAddress.getByName(serverAddress), port);
            socket.send(packet);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String proccessData (String data) {
        StringBuilder str = new StringBuilder();
        String[] s = data.split("\\s+");

        for (String s1:s) {
            str.append(s1.substring(0, 1).toUpperCase()).append(s1.substring(1).toLowerCase()).append(" ");
        }
        return str.toString().trim();
    }
}
