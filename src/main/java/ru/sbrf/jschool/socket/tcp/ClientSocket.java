package ru.sbrf.jschool.socket.tcp;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by SBT-Pozdnyakov-AN on 02.07.2018.
 */
public class ClientSocket {
    public static void main(String[] args) throws IOException {
        InetAddress address = InetAddress.getByName("localhost");
        Socket socket = new Socket(address, 2018);
        try(
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in))
        ){
            String line = null;
            while ((line = reader.readLine())!=null){
                writer.write(line);
                writer.newLine();
                writer.flush();
            }
        }
    }
}
