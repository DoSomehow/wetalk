package org.ms.wetalk.center;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer implements Runnable {

    private int count = 0;  //记录客户端的数量
    private int centerPort;
    private int maxAlive;

    private ServerSocket serverSocket = null;
    private Map<String, Socket> sessionMap;

    public SocketServer(int centerPort, int maxAlive) {
        this.centerPort = centerPort;
        this.maxAlive = maxAlive;
        this.sessionMap = new HashMap<>();
    }

    @Override
    public void run() {
        //服务器代码
        try {
            serverSocket = new ServerSocket(centerPort);
            Socket socket = null;
            while(true){
                socket = serverSocket.accept();
                if (socket != null) {
                    System.out.println("[center]客户端" + count + "连接成功!");
                    // sessionMap.put(count, socket);
                    ExecutorService executorService = Executors.newFixedThreadPool(maxAlive);
                    executorService.execute(new SocketServerHandler(socket, sessionMap));
                    count++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        // SocketServer center = new SocketServer();
        // center.excute();
    }

}
