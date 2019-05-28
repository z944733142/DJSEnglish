package com.djsenglish.controller;

import com.auth0.jwt.interfaces.Claim;
import com.djsenglish.common.Const;
import com.djsenglish.common.ServerResponse;
import com.djsenglish.dao.MessageMapper;
import com.djsenglish.service.IMessageService;
import com.djsenglish.util.JWTUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;

/**
 * @author: shuo
 * @date: 2019/05/12
 */
@SuppressWarnings("all")
@RequestMapping("/chat_message/")
@Controller
public class ChatController {

    @Resource
    private IMessageService iMessageService;

    @RequestMapping(value = "get_Message_History.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getMesaageHistoryList(HttpServletRequest request, @RequestParam(required = false, defaultValue = "1") Integer pageNum, @RequestParam(required = false, defaultValue = "10")Integer pageSize, Integer friendId)
    {
        Integer userId = (Integer) request.getAttribute(Const.ID);
        return iMessageService.getUserMessageList(pageNum, pageSize, userId, friendId);
    }


}
