package com.djsenglish.util;

import com.djsenglish.common.Const;
import com.djsenglish.pojo.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Lists;
import redis.clients.jedis.Jedis;

import javax.websocket.Session;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JedisUtil {

    private static Jedis jedis;

    /**
     * 链接redis
     */
    private static void init() {
        jedis = new Jedis("localhost");
    }

    /**
     * @param id
     * @param session
     *
     * 根据id加入用户
     * 放入session
     */
    public static void putSession(Integer id, Session session)
    {
        init();
        String key = Const.CHATUSER_PREFIX + id;
        String json = null;
        try {
            json = JsonUtil.serialize(session);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        jedis.set(Const.CHATUSER_PREFIX + key, json);
    }

    /**
     * @param id
     * @return
     *
     * 获得用户session
     */
    public static Session getSession(Integer id) {
        init();
        String json = jedis.get(Const.CHATUSER_PREFIX + id);
        Session session = null;
        try {
            session = JsonUtil.sessionUnserialize(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return session;
    }

    /**
     * @param id
     *
     * 移除session
     */
    public static void removeSession(Integer id)
    {
        jedis.del(Const.CHATUSER_PREFIX + id);
    }

    /**
     * @param id
     * @param message
     *
     * 根据id讲消息加入用户未读消息队列
     */
    public static void putUnreadMessage(Integer id, Message message)
    {
        String mesageJson = null;
        try {
            mesageJson = JsonUtil.serialize(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        jedis.lpush(Const.MSG_LIST_PREFIX + id, mesageJson);
    }

    /**
     * @param id
     *
     * 根据id获得用户未读消息队列
     */
    public static List<Message> getUnreadMessages(Integer id)
    {
        List<String> lrange = jedis.lrange(Const.MSG_LIST_PREFIX + id, 0, -1);
        if(lrange == null || lrange.size() == 0)
        {
            return null;
        }
        List<Message> messages = new ArrayList();
        for (String s : lrange) {
            try {
                Message message = JsonUtil.messageUnserialize(s);
                messages.add(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return messages;
    }
}
