package ru.sbrf.jschool.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastReciver {
    public static final String  MULTICAST_GROUP = "239.255.255.0";
    public static final int PORT = 2019;
    public static final int BUFFER_SIZE = 256;

    public static void main(String[] args) {
        try(MulticastSocket multicastSocket = new MulticastSocket(PORT)){
            System.out.println("Создали сокет");
            InetAddress inetAddress = InetAddress.getByName(MULTICAST_GROUP);
            multicastSocket.joinGroup(inetAddress);

            System.out.println("ожидаем пакет");
            String recivedData=null;
            do{
                DatagramPacket packet = new DatagramPacket(new byte[BUFFER_SIZE],BUFFER_SIZE);
                multicastSocket.receive(packet);
                recivedData = new String(packet.getData());
                System.out.print(recivedData);
            }while (!"<END>".equalsIgnoreCase(recivedData));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
