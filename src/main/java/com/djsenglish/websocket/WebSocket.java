package com.djsenglish.websocket;

import com.djsenglish.common.Const;
import com.djsenglish.pojo.ChatUser;
import com.djsenglish.pojo.Message;
import com.djsenglish.service.IMessageService;
import com.djsenglish.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.tomcat.websocket.WsSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

/**
 * @author: shuo
 * @date: 2019/05/15
 */
@Component
@ServerEndpoint("/chat/{id}")
public class WebSocket {

    @Resource
    private IMessageService iMessageService;

    private Session session;

    private Logger logger = LoggerFactory.getLogger(WebSocket.class);

    private static ConcurrentHashMap<String, ChatUser> chatUserMap = new ConcurrentHashMap<>();


    /**
     * 建立连接, redis加入session
     * 并发送未读消息列表
     *
     * @param id
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam("id") Integer id, Session session) {
        this.session = session;
        String key = Const.CHATUSER_PREFIX + id;
        try {
            JsonUtil.serialize((WsSession)session);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if (chatUserMap.containsKey(key)) {
            ChatUser chatUser = chatUserMap.get(key);
            chatUser.setSession(session);
            List<Message> messagesList = chatUser.getUnreadMessages();
            sendMessage(messagesList);
        } else {
            ChatUser chatUser = new ChatUser(id, session);
            chatUserMap.put(key, chatUser);
        }
        logger.info("有新用户加入 id:" + id);
    }

    /**
     * 关闭连接 redis删除session
     *
     * @param id
     */
    @OnClose
    public void onClose(@PathParam("id") Integer id) {
        String key = Const.CHATUSER_PREFIX + id;
        chatUserMap.get(key).setSession(null);
        logger.info(id + "用户连接关闭");
    }

    @OnMessage
    public void onMessage(String messagejson) {
        Message message = null;
        try {
            message = JsonUtil.messageUnserialize(messagejson);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sendMessage(message);
        logger.info(message + "用户连接关闭");
    }

    /**
     * 发生错误
     *
     * @param session
     * @param error
     */

    @OnError
    public void onError(Session session, Throwable error) {
        logger.info("发生错误");
        logger.error(error.getMessage());
    }

    /**
     * 发送消息
     * 判断session是否存在
     * 存在即发送到session
     * 不存在则加入chatUser的未读消息队列
     * @param message
     */
    public void sendMessage(Message message) {
        Integer to = message.getTo();
        String key = Const.CHATUSER_PREFIX + to;
        ChatUser chatUser;
        if(!chatUserMap.containsKey(key)) {
            chatUser = new ChatUser(to);
            chatUserMap.put(key, chatUser);
        }
        chatUser = chatUserMap.get(key);
        Session session = chatUser.getSession();
        if(session == null)
        {
            chatUser.putUnreadMessage(message);
        }else
        {
            sendMessageToSession(session, message);
        }
    }

    /**
     * 发送消息队列
     * 判断session是否存在
     * 存在即发送到session
     * 不存在则加入chatUser的未读消息队列
     * @param messages
     */
    public void sendMessage(List<Message> messages) {
        Integer to = messages.get(0).getTo();
        String key = Const.CHATUSER_PREFIX + to;
        ChatUser chatUser;
        if(!chatUserMap.containsKey(key)) {
            chatUser = new ChatUser(to);
            chatUserMap.put(key, chatUser);
        }
        chatUser = chatUserMap.get(key);
        Session session = chatUser.getSession();
        for (Message message : messages) {
            if(session == null)
            {
                chatUser.putUnreadMessage(message);
            }else
            {
                sendMessageToSession(session, message);
            }
        }


    }

    /**
     * 发送消息到session
     * @param session
     * @param message
     */
    public void sendMessageToSession(Session session, Message message) {
        String messageJson = null;
        try {
            messageJson = JsonUtil.serialize(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Future<Void> future = session.getAsyncRemote().sendText(messageJson);
        if(future.isDone())
        {
            iMessageService.addMessageHistory(message);
        }
    }
}
