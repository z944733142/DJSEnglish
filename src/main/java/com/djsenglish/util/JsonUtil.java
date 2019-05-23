package com.djsenglish.util;

import com.djsenglish.pojo.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.websocket.WsSession;
import org.joda.time.DateTime;

import javax.websocket.Session;
import java.io.*;
import java.util.Map;

/**
 * @author: shuo
 * @date: 2019/05/16
 */
public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String serialize(Object object) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(object);
        return json;

    }

    public static Session sessionUnserialize(String json) throws IOException {
        Session session = objectMapper.readValue(json, Session.class);
        return session;

    }

    public static Message messageUnserialize(String json) throws IOException {
        if(json != null) {
            Map map = objectMapper.readValue(json, Map.class);
            Message message = new Message();
            message.setText((String) map.get("text"));
            message.setSenderId(new Integer((String) map.get("senderId")));
            message.setTo(new Integer((String)map.get("to")));
            message.setTime(new DateTime());
            System.out.println(message.toString());
        return message;
        }
        return null;
    }
}
