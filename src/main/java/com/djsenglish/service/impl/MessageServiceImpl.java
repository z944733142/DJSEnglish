package com.djsenglish.service.impl;

import com.djsenglish.common.ServerResponse;
import com.djsenglish.dao.MessageMapper;
import com.djsenglish.dao.UserMapper;
import com.djsenglish.pojo.Message;
import com.djsenglish.pojo.User;
import com.djsenglish.service.IMessageService;
import com.djsenglish.vo.MessageVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: shuo
 * @date: 2019/05/23
 */
@Service("iMessageService")
public class MessageServiceImpl implements IMessageService {

    @Resource
    private MessageMapper messageMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    public void addMessageHistory(Message message) {
        messageMapper.insertSelective(message);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ServerResponse getUserMessageList(Integer pageNum, Integer pageSize, Integer userId, Integer friendId) {
        PageHelper.startPage(pageNum, pageSize);
        List<Message> list = messageMapper.getMessageList(userId, friendId);
        String userName = userMapper.selectNameById(userId);
        String friendName = userMapper.selectNameById(friendId);
        List<MessageVo> messageVoList;
        if (list != null && list.size() > 0 && userName != null && friendName != null) {
            messageVoList = new ArrayList<>(list.size());
            for (Message message : list) {
                MessageVo messageVo = new MessageVo();
                if(message.getSender().equals(userId))
                {
                    messageVo.setSender(userName);
                }else if(message.getSender().equals(friendId))
                {
                    messageVo.setSender(friendName);
                }
                messageVo.setTime(message.getCreateTime());
                messageVo.setText(message.getText());
                messageVoList.add(messageVo);
            }
        }else
        {
            return ServerResponse.createByErrorMsg("不存在记录");
        }
        PageInfo pageInfo = new PageInfo(list);
        pageInfo.setList(messageVoList);
        return ServerResponse.createBySuccess(pageInfo);
    }
}
