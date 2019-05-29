package org.ms.wetalk.center;

import org.ms.wetalk.client.bo.Message;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author Ryan
 * @Description
 * @Date Created in 2019/5/30 0:00
 */
public class SocketClientHandler implements Runnable {

    private Socket centerSocket;
    private OutputStream out;

    private InputStreamReader input;


    public SocketClientHandler() {
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
            while (true) {
                input = new InputStreamReader(System.in);
                String msg = new BufferedReader(input).readLine();
                Message message = new Message();
                message.setId(2);
                message.setMsg(msg);
                objOut.writeObject(message);
                System.out.println("已发送");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
