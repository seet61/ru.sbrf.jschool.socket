package ru.sbrf.jschool.socket.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by SBT-Pozdnyakov-AN on 02.07.2018.
 */
public class SocketThread implements Runnable {
    private Socket socket;

    public SocketThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            System.out.println(String.format("connect accepted, client ip %s port %s", socket.getInetAddress(), socket.getPort()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(String.format("Received line: %s", line));
                if ("exit".equalsIgnoreCase(line)) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
