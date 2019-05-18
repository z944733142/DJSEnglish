package com.djsenglish.service;

import com.djsenglish.common.ServerResponse;

public interface IFriendService {

    ServerResponse concern(Integer userId, Integer friendId);

    ServerResponse unconcern(Integer userId, Integer friendId);

    ServerResponse checkConcern(Integer userId, Integer friendId);

    ServerResponse getList(Integer userId);

    ServerResponse getDetail(Integer friendId);
}
