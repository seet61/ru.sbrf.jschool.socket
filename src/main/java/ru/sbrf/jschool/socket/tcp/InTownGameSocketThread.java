package ru.sbrf.jschool.socket.tcp;

import ru.sbrf.jschool.socket.other.InTownGame;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class InTownGameSocketThread extends Thread {
    private Socket socket;
    private InTownGame game = new InTownGame();

    public InTownGameSocketThread(Socket socket) throws SocketException, UnknownHostException {

        this.socket = socket;
        game.addListener(new MulticastResultListener());
    }

    @Override
    public void run() {
        try(
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                BufferedWriter bufferedWriter = new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream()))
                ) {
            bufferedWriter.write("Назовите город");
            bufferedWriter.newLine();
            bufferedWriter.flush();
            while(socket.isConnected()){
                String line = bufferedReader.readLine();
                bufferedWriter.write(game.step(line));
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
