package com.djsenglish.websocket;

import com.djsenglish.pojo.Message;
import com.djsenglish.service.impl.FileServiceImpl;
import com.djsenglish.util.JedisUtil;
import com.djsenglish.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.collections.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.List;

/**
 * @author: shuo
 * @date: 2019/05/15
 */
@Component
@ServerEndpoint("/chat")
public class WebSocket {

    private Session session;

    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    /**
     *
     * @param id
     * @param session
     *
     * 建立连接, redis加入session
     */
    @OnOpen
    public void onOpen(Integer id, Session session){
        this.session = session;
        JedisUtil.putSession(id, session);
        JedisUtil.getUnreadMessages(id);
        List<Message> unreadMessages = JedisUtil.getUnreadMessages(id);
        if(unreadMessages != null && unreadMessages.size() != 0) {
            sendMessages(id, unreadMessages);
        }
        logger.info("有新用户加入 id:" + id);
    }

    /**
     *
     * @param id
     * 关闭连接 redis删除session
     */
    @OnClose
    public void onClose(Integer id){
        JedisUtil.removeSession(id);
        logger.info(id + "用户连接关闭");
    }


    public void sendMessage(Integer id, Message message)
    {
        try {
            String messageJson = JsonUtil.serialize(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Session session = JedisUtil.getSession(id);
        if(session != null)
        {
            try {
                String messageJson = JsonUtil.serialize(message);
                session.getAsyncRemote().sendText(messageJson);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }else
        {
            JedisUtil.putUnreadMessage(id, message);
        }
    }

    public void sendMessages(Integer id, List<Message> messages)
    {
        for (Message message : messages) {
            sendMessage(id, message);
        }

    }
}
