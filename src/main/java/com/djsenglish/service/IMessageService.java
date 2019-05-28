package com.djsenglish.service;

import com.djsenglish.common.ServerResponse;
import com.djsenglish.pojo.Message;

/**
 * @author: shuo
 * @date: 2019/05/23
 */

public interface IMessageService {
    void addMessageHistory(Message message);

    ServerResponse getUserMessageList(Integer pageNum, Integer pageSize, Integer userId, Integer friendId);
}
