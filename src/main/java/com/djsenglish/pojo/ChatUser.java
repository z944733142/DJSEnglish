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
    private Session session;
    private List<Message>  unreadMessages = new LinkedList<Message>();

    public ChatUser() {
    }

    public ChatUser(Integer id) {
        this.id = id;
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

    public ChatUser(Integer id, Session session) {
        this.id = id;
        this.session = session;
        this.unreadMessages = new LinkedList<Message>();
    }

    public void putUnreadMessage(Message message) {
        unreadMessages.add(message);
    }
}
