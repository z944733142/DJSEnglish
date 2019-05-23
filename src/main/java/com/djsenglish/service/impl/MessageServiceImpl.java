package com.djsenglish.service.impl;

import com.djsenglish.common.ServerResponse;
import com.djsenglish.dao.MessageMapper;
import com.djsenglish.pojo.Message;
import com.djsenglish.service.IMessageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: shuo
 * @date: 2019/05/23
 */
@Service("iMessageService")
public class MessageServiceImpl implements IMessageService {

    @Resource
    private MessageMapper messageMapper;

    @Override
    public void addMessageHistory(Message message)
    {
        messageMapper.insert(message);
    }

    @Override
    public ServerResponse getUserMessageList(Integer pageNum, Integer pageSize, Integer senderId, Integer receiverId)
    {
        PageHelper.startPage(pageNum, pageSize);
        List<Message> list = messageMapper.getMessageList(senderId, receiverId);
        PageInfo pageInfo = new PageInfo(list);
        return ServerResponse.createBySuccess(pageInfo);
    }
}
