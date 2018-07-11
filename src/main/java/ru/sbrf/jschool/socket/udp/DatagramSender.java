package ru.sbrf.jschool.socket.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class DatagramSender {

    public static void main(String[] args) throws UnknownHostException {
        InetAddress dst = InetAddress.getByName("localhost");
        try (DatagramSocket socket = new DatagramSocket()) {
            String sensorData = "{temperature: 12}";
            DatagramPacket datagramPacket = new DatagramPacket(sensorData.getBytes(), sensorData.length(), dst, 3233);
            socket.send(datagramPacket);
        } catch (Exception e) {
            System.err.println("Can't create datagram socket " + e.getMessage());
        }
    }
}
