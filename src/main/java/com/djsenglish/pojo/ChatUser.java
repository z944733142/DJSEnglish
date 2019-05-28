package com.djsenglish.pojo;

import javax.websocket.Session;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: shuo
 * @date: 2019/05/22
 */
public class ChatUser {
    private Integer id;
    private String name;
    private Session session;
    private List<Message> unreadMessages = new LinkedList<Message>();

    public ChatUser() {
    }

    public ChatUser(Integer id, String name) {
        this.id = id;
        this.name = name;
        session = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public List<Message> getUnreadMessages() {
        return unreadMessages;
    }

    public void setUnreadMessages(List<Message> unreadMessages) {
        this.unreadMessages = unreadMessages;
    }

    public ChatUser(Integer id, Session session, List<Message> unreadMessages) {
        this.id = id;
        this.session = session;
        this.unreadMessages = unreadMessages;
    }

    public ChatUser(Integer id, String name, Session session) {
        this.id = id;
        this.name = name;
        this.session = session;
    }

    public void putUnreadMessage(Message message) {
        unreadMessages.add(message);
    }
}
