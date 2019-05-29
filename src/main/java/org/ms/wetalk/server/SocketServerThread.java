package org.ms.wetalk.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class SocketServerThread extends Thread {

    private Socket socket;
    private String clientIp;

    public SocketServerThread(Socket socket) {
        this.socket = socket;
        this.clientIp = socket.getInetAddress().toString();
    }

    public void run() {
        InputStream is = null;
        try {
            is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String info =null;
            while((info=br.readLine())!=null){
                System.out.println("[server]"+ clientIp +"："+info);
            }
            socket.shutdownInput();//关闭输入流

            //关闭资源
            br.close();
            isr.close();
            is.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
