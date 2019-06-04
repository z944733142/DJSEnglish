package com.djsenglish.websocket;

import com.djsenglish.common.Const;
import com.djsenglish.dao.UserMapper;
import com.djsenglish.pojo.ChatUser;
import com.djsenglish.pojo.Message;
import com.djsenglish.service.IMessageService;

import com.djsenglish.util.JsonUtil;
import com.djsenglish.vo.MessageVo;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: shuo
 * @date: 2019/05/15
 */
@Component
@ServerEndpoint("/chat/{id}")
public class FriendChatWebSocket {

    private IMessageService iMessageService;

    private UserMapper userMapper;

    private ChatUser chatUser;

    private Logger logger = LoggerFactory.getLogger(FriendChatWebSocket.class);

    private static ConcurrentHashMap<String, ChatUser> chatUserMap = new ConcurrentHashMap<>();


    /**
     * 建立连接, chatUserMap加入ChatUser
     * 并发送未读消息列表
     *
     * @param id
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam("id") Integer id, Session session) {
        ApplicationContext applicationContext = ApplicationContextRegister.getApplicationContext();
        iMessageService = applicationContext.getBean(IMessageService.class);
        userMapper = applicationContext.getBean(UserMapper.class);
        String key = Const.CHATUSER_PREFIX + id;
        this.chatUser =getChatUser(id, session);
        sendMessage(chatUser.getUnreadMessages());
        logger.info("有新用户加入 id:" + id);
    }

    /**
     * 关闭连接 map删除session
     *
     */
    @OnClose
    public void onClose() {
        Integer id = this.chatUser.getId();
        String key = Const.CHATUSER_PREFIX + id;
        chatUserMap.remove(key);
        try {
            chatUser.getSession().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        iMessageService.addMessageHistory(message);
    }

    /**
     * 发生错误
     *
     * @param session
     * @param error
     */

    @OnError
    public void onError(Session session, Throwable error) {
        String key = Const.CHATUSER_PREFIX + this.chatUser.getId();
        chatUserMap.remove(key);
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("发生错误");
        logger.error(error.getMessage());
        error.printStackTrace();
    }

    private ChatUser getChatUser(Integer id) {
        String key = Const.CHATUSER_PREFIX + id;
        ChatUser chatUser = null;
        if (!chatUserMap.containsKey(key)) {
            String name = userMapper.selectNameById(id);
            if (name == null) {
                Message message = new Message(0, id, "用户不存在");
                String messageJson = null;
                try {
                    messageJson = JsonUtil.serialize(message);
                    this.chatUser.getSession().getBasicRemote().sendText(messageJson);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                onClose();
                return null;
            } else {
                chatUser = new ChatUser(id, name);
            }
            chatUserMap.put(key, chatUser);
        } else {
            chatUser = chatUserMap.get(key);
        }
        return chatUser;
    }

    private ChatUser getChatUser(Integer id, Session session) {
        ChatUser chatUser = getChatUser(id);
        if (chatUser != null) {
            chatUser.setSession(session);
        }
        return chatUser;
    }


    /**
     * 发送消息队列
     *
     * @param messages
     */
    private void sendMessage(List<Message> messages) {
        for (Message message : messages) {
            sendMessage(message);
        }
    }

    private void sendMessage(Message message) {
        Integer to = message.getTo();
        ChatUser toChatUser = getChatUser(to);
        String name = userMapper.selectNameById(message.getSender());
        String messageJson = null;
        MessageVo messageVo = new MessageVo();
        Session session = toChatUser.getSession();
        if (session == null) {
            toChatUser.putUnreadMessage(message);
        } else {

            messageVo.setSender(name);
            messageVo.setText(message.getText());
            messageVo.setTime(message.getCreateTime());

            try {
                messageJson = JsonUtil.serialize(messageVo);
                logger.info(messageJson);
                session.getBasicRemote().sendText(messageJson);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

//    /**
//     * 使用同一个session异步发送报错,
//     * The remote endpoint was in state [TEXT_FULL_WRITING] which is an invalid state for called method
//     * 需要改为同步
//     * session.getBasicRemote().sendText(messageJson);
//     * 发送消息到session
//     * @param session
//     * @param message
//     */
//    public void sendMessageToSession(Session session, Message message) {
//        String messageJson = null;
//        try {
//            messageJson = JsonUtil.serialize(message);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        Future<Void> future = null;
//        future = session.getAsyncRemote().sendText(messageJson);
//        iMessageService.addMessageHistory(message);
//    }
}
