package ru.sbrf.jschool.socket.udp;

import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MulticastDatagramSender {
    public static final int PORT = 2991;
    public static final int BUFFER_SIZE = 64;
    public static final String END_LABEL = "<END>";

    public static void main(String[] args) throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getByName("239.255.255.0");
        try(DatagramSocket datagramSocket=new DatagramSocket()){

            InputStream is = MulticastDatagramSender.class.getClassLoader().getResourceAsStream("text_radio.txt");
            byte[] buffer = new byte[BUFFER_SIZE];
            int dataLength = 0;
            while ((dataLength=is.read(buffer))!=-1){
                System.out.print(new String(buffer,0,dataLength));
                DatagramPacket packet=new DatagramPacket(buffer,dataLength,inetAddress,PORT);
                datagramSocket.send(packet);
                Thread.sleep(1000);
            }
            DatagramPacket packet=new DatagramPacket(END_LABEL.getBytes(),END_LABEL.getBytes().length,inetAddress,PORT);
            datagramSocket.send(packet);

        } catch (Exception e) {
            System.out.println("Error creating socket, "+ e.getMessage());
        }
    }
}
