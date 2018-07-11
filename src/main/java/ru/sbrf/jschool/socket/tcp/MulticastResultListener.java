package ru.sbrf.jschool.socket.tcp;

import ru.sbrf.jschool.socket.other.StepListener;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * Created by SBT-Pozdnyakov-AN on 02.07.2018.
 */
public class MulticastResultListener implements StepListener {

    DatagramSocket socket;

    public MulticastResultListener() throws UnknownHostException, SocketException {

        socket = new DatagramSocket();
    }

    @Override
    public void stepPerformed(int stepNumber, String userCity, String aiCity) {
        String sendLine = String.format("step %d userCity %s aiCity %s",
                stepNumber,
                userCity,
                aiCity);

        DatagramPacket packet = null;
        try {
            packet = new DatagramPacket(
                    sendLine.getBytes(),
                    sendLine.length(),
                    InetAddress.getByName("239.255.255.0"),
                    2019);

            socket.send(packet);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
