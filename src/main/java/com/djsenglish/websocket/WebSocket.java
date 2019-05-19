package com.djsenglish.websocket;

import com.djsenglish.service.impl.FileServiceImpl;
import com.djsenglish.util.JedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

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
        System.out.println(id + "用户连接关闭");
    }

//    public void sendMessage(String )

}
