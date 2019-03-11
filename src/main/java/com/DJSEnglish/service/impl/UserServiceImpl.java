package com.DJSEnglish.service.impl;

import com.DJSEnglish.common.ServerResponse;
import com.DJSEnglish.dao.UserMapper;
import com.DJSEnglish.pojo.User;
import com.DJSEnglish.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

    @Autowired
    private UserMapper userMapper;

    public ServerResponse Login(String username, String password)
    {
        int count = userMapper.selectUserCount(username);
        if(count < 0)
        {
            return ServerResponse.createByErrorMsg("用户名不存在");
        }

        String MD5PassWord = MD5Util.MD5EncodeUtf8(password);
        User user = userMapper.selectUser(username, password);
        if(user != null)
        {
            return ServerResponse.createBySuccess("登录成功", user);
        }
        return ServerResponse.createByErrorMsg("账号密码不匹配");

    }
}
