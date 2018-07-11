package ru.sbrf.jschool.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class DatagramReciver {
    public static final int BUFFER_SIZE = 256;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(3233)) {
            long startTime = System.currentTimeMillis();
            byte[] buffer = new byte[BUFFER_SIZE];
            DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
            socket.receive(dp);
            String data = new String(dp.getData());
            System.out.println("recived data: " + data);
            long finishTime = System.currentTimeMillis() - startTime;
            System.out.println(String.format("finished after %s millisecond",String.valueOf(finishTime)));
        } catch (SocketException e) {
            System.err.println("Error creating socket: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O exception: " + e.getMessage());
        }
    }
}
