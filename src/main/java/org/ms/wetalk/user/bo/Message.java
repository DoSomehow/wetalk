package org.ms.wetalk.user.bo;

import java.io.Serializable;

/**
 * @Author Ryan
 * @Description
 * @Date Created in 2019/5/29 23:43
 */
public class Message implements Serializable {

    private Integer id;
    private String sessionUser;
    private String targetUser;
    private String msg;

    public Message() {
    }

    public Message(String sessionUser, String targetUser, String msg) {
        this.sessionUser = sessionUser;
        this.targetUser = targetUser;
        this.msg = msg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSessionUser() {
        return sessionUser;
    }

    public void setSessionUser(String sessionUser) {
        this.sessionUser = sessionUser;
    }

    public String getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(String targetUser) {
        this.targetUser = targetUser;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
