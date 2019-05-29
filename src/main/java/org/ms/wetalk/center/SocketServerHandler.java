package org.ms.wetalk.center;

import org.ms.wetalk.client.bo.Message;

import java.io.*;
import java.net.Socket;
import java.util.Map;

public class SocketServerHandler implements Runnable {

    private Socket socket;
    private Map<Integer, Socket> sessionMap;
    private String clientIp;
    private InputStream is = null;

    public SocketServerHandler(Socket socket, Map<Integer, Socket> sessionMap) {
        this.socket = socket;
        this.sessionMap = sessionMap;
        this.clientIp = socket.getInetAddress().toString();
    }

    public void run() {
        try {
            is = socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            while (true) {
                try {
                    Message msg = (Message) ois.readObject();
                    System.out.println("[center]消息接受对象：客户端" + msg.getId() + "，消息内容：" + msg.getMsg());

                    try {
                        Socket targetSocket = sessionMap.get(msg.getId());
                        OutputStream out = targetSocket.getOutputStream();
                        ObjectOutputStream objOut = new ObjectOutputStream(out);
                        objOut.writeObject(msg);
                        System.out.println("[center]已转发");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (socket.isClosed()) {
                        break;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    try {
                        socket.close();
                        break;
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }

                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }

            }

            ois.close();
            is.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
