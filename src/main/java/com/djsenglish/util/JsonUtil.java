package com.djsenglish.util;

import com.djsenglish.pojo.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.websocket.Session;
import java.io.*;

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

        Message message = objectMapper.readValue(json, Message.class);
        return message;

    }
}
