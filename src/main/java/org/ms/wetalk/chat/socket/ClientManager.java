package org.ms.wetalk.chat.socket;

import org.ms.wetalk.user.bo.Message;
import org.ms.wetalk.user.bo.User;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientManager {

    private static Map<String, LinkedList<Message>> msgMap = new HashMap<>();


    public static void createClientSocket(User user) {
        if(!msgMap.containsKey(user.getUsername())){
            ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.execute(new SocketClientHandler(user));
            msgMap.put(user.getUsername(), new LinkedList<>());
        }
    }


    public static void sendMsg(Message message){
        LinkedList<Message> queue = msgMap.get(message.getSessionUser());
        queue.add(message);
    }

    public static  LinkedList<Message> getMsgQueue(String sessionUser) {
        return msgMap.get(sessionUser);
    }
}
