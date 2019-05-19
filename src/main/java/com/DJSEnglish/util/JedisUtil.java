package com.djsenglish.util;

import com.djsenglish.common.Const;
import redis.clients.jedis.Jedis;

import javax.websocket.Session;

public class JedisUtil {

    private static Jedis jedis;
    private static void init() {
        jedis = new Jedis("localhost");
    }

    /**
     * @param id
     * @param session
     */
    public static void putSession(Integer id, Session session)
    {
        init();
        String key = Const.CHATUSER_PREFIX + id;
        String json = JsonUtil.serialize(session);
        jedis.set(key, json);
    }

    /**
     * @param id
     * @return
     */
    public static Session getSession(String id) {
        init();
        String json = jedis.get(Const.CHATUSER_PREFIX + id);
        Session session = JsonUtil.sessionUnserialize(json);
        return session;
    }

    /**
     * @param id
     */
    public static void removeSession(Integer id)
    {
        jedis.del(Const.CHATUSER_PREFIX + id);
    }

    /**
     * @param id
     * @param message
     */
    public static void putUnreadMessages(Integer id, String message)
    {

    }

    /**
     * @param id
     */
    public static void getUnreadMessages(Integer id)
    {

    }
}
