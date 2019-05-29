package org.ms.wetalk.server.servlet;

import org.ms.wetalk.server.SocketServer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "serverStarterServlet", urlPatterns = "/servlet/serverStarer", loadOnStartup = 1)
public class ServerStarterServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("服务端服务开始启动...");
        // socketServer.excute();   //直接这样调用，会卡住启动流程的。应该需要对其单独开一个线程处理
        SocketServer server = new SocketServer();
        server.start();
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
    }

}
