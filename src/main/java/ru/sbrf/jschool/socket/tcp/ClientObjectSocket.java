package ru.sbrf.jschool.socket.tcp;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by SBT-Pozdnyakov-AN on 02.07.2018.
 */
public class ClientObjectSocket {
    public static void main(String[] args) throws IOException {
        InetAddress address = InetAddress.getByName("localhost");
        Socket socket = new Socket(address, 2018);
        try(
                ObjectOutputStream ois = new ObjectOutputStream(socket.getOutputStream())
        ){
            ois.writeObject(new TestClass("Привет мир!!!"));
            ois.flush();
        }
    }
}
