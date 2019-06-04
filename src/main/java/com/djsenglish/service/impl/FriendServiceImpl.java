package com.djsenglish.service.impl;

import com.djsenglish.common.ServerResponse;
import com.djsenglish.dao.ConcernMapper;
import com.djsenglish.dao.UserMapper;
import com.djsenglish.pojo.Concern;
import com.djsenglish.pojo.User;
import com.djsenglish.service.IFriendService;
import com.djsenglish.util.FTPUtil;
import com.djsenglish.vo.FriendListVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author shuo
 */
@Service("iFriendService")
public class FriendServiceImpl implements IFriendService {

    @Resource
    private ConcernMapper concernMapper;

    @Resource
    private UserMapper userMapper;


    @Override
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

    @Override
    public ServerResponse unconcern(Integer userId, Integer friendId)
    {
        if(concernMapper.deleteConcern(userId, friendId) > 0)
        {
            return ServerResponse.createBySuccessMsg("取关成功");
        }
        return ServerResponse.createByErrorMsg("取关失败");
    }

    @Override
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

    @Override
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

    @Override
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
