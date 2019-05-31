package org.ms.wetalk.center.servlet;

import org.ms.wetalk.center.SocketServer;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

//怎么可以去掉这个urlPatterns，或者是怎么处理。现在如果去掉的话，会导致其他controller都没法接收到请求了。
@WebServlet(name = "serverStarterServlet", urlPatterns = "/servlet/serverStarter", loadOnStartup = 1)
public class ServerStarterServlet extends HttpServlet {

    @Value("${socket.center.port}")
    private int centerPort;
    @Value("${socket.center.maxAlive}")
    private int maxAlive;

    @Override
    public void init() throws ServletException {
        System.out.println("服务端服务开始启动...");
        //单独开一个线程处理服务端
        ExecutorService executorService = Executors.newFixedThreadPool(1, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setDaemon(true);
                thread.setName("SocketServer-" + thread.getId());
                return thread;
            }
        });
        executorService.execute(new SocketServer(centerPort, maxAlive));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    public void destroy() {
        System.out.println("heiheiehiehiheiheiehiei");
        System.out.println("线程名称：" + Thread.currentThread().getName());
    }

}
