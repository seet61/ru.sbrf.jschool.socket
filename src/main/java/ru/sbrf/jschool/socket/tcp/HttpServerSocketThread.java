package ru.sbrf.jschool.socket.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HttpServerSocketThread extends Thread{

    public static final String RESP = "HTTP/1.1 200 OK\n" +
            "Server: java-socket server\n" +
            "Content-Type: text/html; charset=UTF-8\n" +
            "\n\r" +
            "<HTML><BODY><H1>Hello! I'm simple http-server</H1></BODY></HEAD>";

    private Socket client;

    public HttpServerSocketThread(Socket socket){
        this.client = socket;
    }
    @Override
    public void run() {

        System.out.println("Start channel");
        try {
            if(!client.isClosed()) {
                DataOutputStream outputStream = new DataOutputStream(client.getOutputStream());
                outputStream.writeUTF(RESP);
                outputStream.flush();
                outputStream.close();
                System.out.println("finish channel");
                client.close();
            }
        } catch (IOException e) {
            System.out.println("Error for client: "+e.getMessage());
        }

    }
}
