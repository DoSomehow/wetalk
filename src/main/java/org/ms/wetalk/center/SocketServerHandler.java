package org.ms.wetalk.center;

import org.ms.wetalk.client.bo.Message;
import org.ms.wetalk.client.bo.User;

import java.io.*;
import java.net.Socket;
import java.util.Map;

public class SocketServerHandler implements Runnable {

    private Socket socket;
    private Map<String, Socket> sessionMap;
    private String clientIp;
    private InputStream in = null;

    public SocketServerHandler(Socket socket, Map<String, Socket> sessionMap) {
        this.socket = socket;
        this.sessionMap = sessionMap;
        this.clientIp = socket.getInetAddress().toString();
    }

    public void run() {
        try {
            in = socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(in);
            User user = (User) ois.readObject();
            sessionMap.put(user.getUsername(), socket);

            while (true) {
                try {
                    Message msg = (Message) ois.readObject();
                    // System.out.println("[center]消息接受对象：用户" + msg.getTargetUser() + "，消息内容：" + msg.getMsg());

                    try {
                        Socket targetSocket = sessionMap.get(msg.getTargetUser());
                        if (targetSocket != null) {
                            OutputStream out = targetSocket.getOutputStream();
                            ObjectOutputStream objOut = new ObjectOutputStream(out);
                            objOut.writeObject(msg);
                            // System.out.println("[center]已转发");
                        }else{
                            System.out.println("[center]用户" + msg.getTargetUser() + "不在线！");
                        }
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
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
