package com.djsenglish.util;

import com.djsenglish.pojo.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.websocket.WsSession;
import org.joda.time.DateTime;

import javax.websocket.Session;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author: shuo
 * @date: 2019/05/16
 */
public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    static {
        SimpleDateFormat smt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        objectMapper.setDateFormat(smt);
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
    }
    public static String serialize(Object object) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(object);
        System.out.println(json);
        return json;

    }

    public static Session sessionUnserialize(String json) throws IOException {
        Session session = objectMapper.readValue(json, Session.class);
        return session;

    }

    public static Message messageUnserialize(String json) throws IOException {
        if(json != null) {
            Map map = objectMapper.readValue(json, Map.class);
            String text = (String) map.get("text");
            Integer senderId = new Integer((String) map.get("senderId"));
            Integer to = new Integer((String)map.get("to"));
            Message message = new Message(senderId, to, text);
            System.out.println(message.toString());
        return message;
        }
        return null;
    }
}
