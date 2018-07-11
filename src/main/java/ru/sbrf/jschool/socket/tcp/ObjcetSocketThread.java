package ru.sbrf.jschool.socket.tcp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * Created by SBT-Pozdnyakov-AN on 02.07.2018.
 */
public class ObjcetSocketThread implements Runnable {
    private Socket socket;

    public ObjcetSocketThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            while (socket.isConnected()){
                TestClass object = (TestClass) ois.readObject();
                System.out.println(object.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
