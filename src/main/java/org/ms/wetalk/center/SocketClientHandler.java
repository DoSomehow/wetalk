package org.ms.wetalk.center;

import org.ms.wetalk.client.bo.Message;
import org.ms.wetalk.client.bo.User;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author Ryan
 * @Description
 * @Date Created in 2019/5/30 0:00
 */
public class SocketClientHandler implements Runnable {

    // private String seesionUser;
    private User user;
    private Socket centerSocket;
    private OutputStream out;

    // private InputStreamReader input;


    public SocketClientHandler(User user) {
        this.user = user;
        try {
            this.centerSocket = new Socket("localhost", 10086);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(new SocketClientReceiveHandler(centerSocket));

        try {
            out = centerSocket.getOutputStream();
            ObjectOutputStream objOut = new ObjectOutputStream(out);

            objOut.writeObject(user);

            LinkedList<Message> msgQueue = ClientManager.getMsgQueue(user.getUsername());
            while (true) {
                // input = new InputStreamReader(System.in);
                // String msg = new BufferedReader(input).readLine();
                // Message message = new Message();
                // message.setId(2);
                // message.setMsg(msg);

                Message message = msgQueue.poll();
                if (message != null) {
                    objOut.writeObject(message);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // try {
            //     input.close();
            // } catch (IOException e) {
            //     e.printStackTrace();
            // }
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                centerSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
