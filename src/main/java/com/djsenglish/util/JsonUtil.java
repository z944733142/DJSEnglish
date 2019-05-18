package com.djsenglish.util;

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

    public static String serialize(Object object) {
        try {
            String json = objectMapper.writeValueAsString(object);
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Session sessionUnserialize(String json) {
        try {
            Session session = objectMapper.readValue(json, Session.class);
            return session;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
