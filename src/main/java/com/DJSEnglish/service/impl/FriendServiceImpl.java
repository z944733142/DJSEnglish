package com.DJSEnglish.service.impl;

import com.DJSEnglish.common.ServerResponse;
import com.DJSEnglish.dao.ConcernMapper;
import com.DJSEnglish.dao.UserMapper;
import com.DJSEnglish.pojo.Concern;
import com.DJSEnglish.pojo.User;
import com.DJSEnglish.service.IFriendService;
import com.DJSEnglish.util.FTPUtil;
import com.DJSEnglish.vo.FriendListVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("iFriendService")
public class FriendServiceImpl implements IFriendService {

    @Autowired
    private ConcernMapper concernMapper;

    @Autowired
    private UserMapper userMapper;

    public ServerResponse concern(Integer userId, Integer friendId)
    {
        Concern concern = new Concern();
        concern.setUser(userId);
        concern.setFriending(friendId);
        if(userMapper.selectByPrimaryKey(friendId) == null)
        {
            return ServerResponse.createByErrorMsg("用户不存在");
        }
        if(concernMapper.insert(concern) > 0)
        {
            return ServerResponse.createBySuccessMsg("关注成功");
        }
        return ServerResponse.createByErrorMsg("关注失败");
    }

    public ServerResponse unconcern(Integer userId, Integer friendId)
    {
        if(concernMapper.deleteConcern(userId, friendId) > 0)
        {
            return ServerResponse.createBySuccessMsg("取关成功");
        }
        return ServerResponse.createByErrorMsg("取关失败");
    }

    public ServerResponse checkConcern(Integer userId, Integer friendId)
    {
        if(concernMapper.selectCount(userId, friendId) > 0)
        {
            if(concernMapper.selectCount(friendId, userId) > 0)
            {
                return ServerResponse.createBySuccessMsg("互相关注");
            }
            return ServerResponse.createBySuccessMsg("已关注");
        }

        return ServerResponse.createBySuccessMsg("未关注");
    }

    public ServerResponse getList(Integer userId)
    {
        List<Integer> concerns = concernMapper.selectByUserId(userId);
        if(concerns == null || concerns.size() <= 0)
        {
            return ServerResponse.createByErrorMsg("好友数量为零");
        }
        List<FriendListVo> friendListVos = userMapper.selectFriendList(concerns);
        if(friendListVos != null && friendListVos.size() != 0)
        {
            return ServerResponse.createBySuccess(friendListVos);
        }
        return ServerResponse.createByErrorMsg("好友数量为零");
    }

    public ServerResponse getDetail(Integer friendId)
    {
        User user = userMapper.selectByPrimaryKey(friendId);
        if(user == null)
        {
            return ServerResponse.createByErrorMsg("用户不存在");
        }
        user.setImg(FTPUtil.ftpPrefix + user.getImg());
        return ServerResponse.createBySuccess(user);
    }
}
