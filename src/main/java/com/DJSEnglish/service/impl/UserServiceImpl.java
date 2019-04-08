package com.DJSEnglish.service.impl;

import com.DJSEnglish.common.Const;
import com.DJSEnglish.common.ServerResponse;
import com.DJSEnglish.dao.UserMapper;
import com.DJSEnglish.pojo.User;
import com.DJSEnglish.service.IUserService;
import com.DJSEnglish.util.FTPUtil;
import com.DJSEnglish.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("iUserService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    public ServerResponse Login(String username, String password)
    {
        ServerResponse serverResponse = CheckVaild(username, Const.USERNAME);
        if(serverResponse.isSuccess())
        {
            return ServerResponse.createByErrorMsg("账号不存在");
        }

        String MD5PassWord = MD5Util.MD5EncodeUtf8(password);
        User user = userMapper.selectUser(username, MD5PassWord);
        if(user != null)
        {
            user.setPassword("");
            if(user.getImg() != "null")
            {
                user.setImg(FTPUtil.ftpPrefix + user.getImg());
            }
            return ServerResponse.createBySuccess("登录成功", user);
        }
        return ServerResponse.createByErrorMsg("账号密码不匹配");
    }

    public boolean checkValid(User user)
    {
        if(StringUtils.isBlank(user.getName()) || StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword()) || StringUtils.isBlank(user.getPhone()))
        {
            return false;
        }
        return true;
    }

    public ServerResponse Register(User user)
    {
        ServerResponse serverResponse = CheckVaild(user.getUsername(), Const.USERNAME);
        if(!serverResponse.isSuccess())
        {
            return serverResponse;
        }
        serverResponse = CheckVaild(user.getPhone(), Const.PHONE);
        if(!serverResponse.isSuccess())
        {
            return serverResponse;
        }
        serverResponse = CheckVaild(user.getEmail(), Const.EMAIL);
        if(!serverResponse.isSuccess())
        {
            return serverResponse;
        }

        if(!checkValid(user))
        {
            ServerResponse.createByErrorMsg("信息不完全");
        }
        User insertUser = new User();
        insertUser.setUsername(user.getUsername());
        insertUser.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        insertUser.setName("手机用户" + user.getPhone());
        insertUser.setPhone(user.getPhone());
        insertUser.setImg("default.jpg");
        int count = userMapper.insertSelective(insertUser);
        if(count > 0)
        {
            return ServerResponse.createBySuccessMsg("注册成功");
        }
        return ServerResponse.createByErrorMsg("注册失败");
    }

    public ServerResponse CheckVaild(String str, String type)
    {
        if(StringUtils.equals(type, Const.USERNAME))
        {
            if(userMapper.selectUserCount(str) > 0)
            {
                return ServerResponse.createByErrorMsg("用户名已存在");
            }
        }
        else if(StringUtils.equals(type, Const.EMAIL))
        {
            if(userMapper.selectEmailCount(str) > 0)
            {
                return ServerResponse.createByErrorMsg("邮箱已存在");
            }
        }
        else if(StringUtils.equals(type, Const.PHONE))
        {
            if(userMapper.selectPhoneCount(str) > 0)
            {
                return ServerResponse.createByErrorMsg("手机号码已存在");
            }
        }else {
            return ServerResponse.createByErrorMsg("参数错误");
        }
        return ServerResponse.createBySuccessMsg("信息无重复, 可用");
    }

    public ServerResponse updateUserInfo(User user)
    {
        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setName(user.getName());
        updateUser.setMsg(user.getMsg());
        updateUser.setEmail(user.getEmail());

        if(userMapper.updateByPrimaryKeySelective(updateUser) > 0)
        {
            return ServerResponse.createBySuccessMsg("更新信息成功");
        }
        return ServerResponse.createByErrorMsg("更新失败");
    }

    public ServerResponse loginResetPassword(int id, String password)
    {
        User updateUser = new User();
        updateUser.setId(id);
        updateUser.setPassword(MD5Util.MD5EncodeUtf8(password));
        if(userMapper.updateByPrimaryKeySelective(updateUser) > 0)
        {
            return ServerResponse.createBySuccessMsg("重置成功, 请重新登录");
        }

        return ServerResponse.createBySuccessMsg("重置失败");
    }

    public ServerResponse forgetResetPassword(String phoneNumber, String password)
    {
        User updateUser = new User();
        password = MD5Util.MD5EncodeUtf8(password);
        if(userMapper.updateByPhone(phoneNumber, password) > 0)
        {
            return ServerResponse.createBySuccessMsg("修改成功");
        }
        return ServerResponse.createByErrorMsg("修改失败");
    }
}
