package org.ms.wetalk.center;

import org.ms.wetalk.client.bo.Message;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * @Author Ryan
 * @Description
 * @Date Created in 2019/5/30 0:34
 */
public class SocketClientReceiveHandler implements Runnable {

    private Socket centerSocket;
    private InputStream in;

    public SocketClientReceiveHandler(Socket centerSocket) {
        this.centerSocket = centerSocket;
    }

    @Override
    public void run() {
        try {
            while (true) {
                in = centerSocket.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(in);
                Message msg = (Message) ois.readObject();
                System.out.println("[client]" + msg.getSessionUser() + "对" + msg.getTargetUser() + "说：" + msg.getMsg());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
