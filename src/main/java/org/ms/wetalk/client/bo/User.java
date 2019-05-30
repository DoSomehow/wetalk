package org.ms.wetalk.client.bo;

import java.io.Serializable;

/**
 * @Author Ryan
 * @Description
 * @Date Created in 2019/5/30 15:13
 */
public class User implements Serializable {

    private String username;
    private String password;
    private String token;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}