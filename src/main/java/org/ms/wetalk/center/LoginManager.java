package org.ms.wetalk.center;

import org.ms.wetalk.client.bo.User;

import java.text.SimpleDateFormat;
import java.util.*;

public class LoginManager {

    private static Map<String, String> tokenMap = new HashMap<>();

    public static boolean login(User user) {

        boolean flag = validAccountInfo(user.getUsername(), user.getPassword());
        if(flag){
            String token = generateToken(user.getUsername());
            user.setToken(token);
        }

        return flag;
    }

    private static boolean validAccountInfo(String username, String password) {
        boolean flag = false;
        if("wangsy".equals(username) && "wangsy".equals(password)){
            flag = true;
        }else if("tom".equals(username) && "tom".equals(password)){
            flag = true;
        }
        return flag;
    }

    private static String generateToken(String username) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String time = sdf.format(new Date());

        Random random = new Random();
        String randCode = String.valueOf(random.nextInt(999));
        String prefix = "000";
        if (randCode.length() < 3) {
            int diff = 3 - randCode.length();
            prefix = prefix.substring(0, diff);
            randCode = prefix + randCode;
        }

        String token = time + username + randCode;
        tokenMap.put(username, token);
        return token;
    }

    public static String getToken(String username) {
        String token = tokenMap.get(username);
        return token;
    }

    public static List<String> getLongUsers() {
        List<String> userList = new ArrayList<>(tokenMap.keySet());
        return userList;
    }


}
