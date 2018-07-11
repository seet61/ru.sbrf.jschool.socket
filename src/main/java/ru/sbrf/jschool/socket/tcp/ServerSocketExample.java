package ru.sbrf.jschool.socket.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerSocketExample {

    public static final int PORT = 2018;

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println(String.format("Application started, and listening port: %d", PORT));
        System.out.println("Wait connect...");
        ExecutorService pool = Executors.newFixedThreadPool(10);
        while (true) {
           pool.execute(new InTownGameSocketThread(serverSocket.accept()));
        }

    }
}
