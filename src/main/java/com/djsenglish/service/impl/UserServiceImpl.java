package com.djsenglish.service.impl;

import com.djsenglish.common.Const;
import com.djsenglish.common.ServerResponse;
import com.djsenglish.dao.UserMapper;
import com.djsenglish.pojo.User;
import com.djsenglish.service.IUserService;
import com.djsenglish.util.FTPUtil;
import com.djsenglish.util.JWTUtil;
import com.djsenglish.util.MD5Util;
import com.djsenglish.util.PhoneUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service("iUserService")
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public ServerResponse Login(String phoneNumber, String password) throws Exception {
        if(CheckVaild(phoneNumber, Const.PHONE).isSuccess())
        {
            return ServerResponse.createByErrorMsg("账号不存在");
        }
        String MD5PassWord = MD5Util.MD5EncodeUtf8(password);
        User user = userMapper.selectUser(phoneNumber, MD5PassWord);
        if(user != null)
        {
            user.setImg(FTPUtil.ftpPrefix + user.getImg());
            String token = JWTUtil.createToken(user.getId());
            Map map = new HashMap();
            map.put("user", user);
            map.put("token", token);
            return ServerResponse.createBySuccess("登录成功",map);
        }
        return ServerResponse.createByErrorMsg("账号密码不匹配");
    }

    public boolean checkValid(User user)
    {
        if(StringUtils.isBlank(user.getPhone()) || StringUtils.isBlank(user.getPassword()) || StringUtils.isBlank(user.getPhone()))
        {
            return false;
        }
        return true;
    }

    @Override
    public ServerResponse Register(User user, String msgCode)
    {
        ServerResponse serverResponse = CheckVaild(user.getPhone(), Const.PHONE);
        if(!serverResponse.isSuccess())
        {
            return serverResponse;
        }
        if(!checkValid(user))
        {
            ServerResponse.createByErrorMsg("信息不完全");
        }

        if(!PhoneUtil.judgeCodeIsTrue(msgCode, user.getPhone()))
        {
            return ServerResponse.createByErrorMsg("注册失败, 验证码不正确");
        }
        User insertUser = new User();
        insertUser.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        insertUser.setName("手机用户" + user.getPhone());
        insertUser.setPhone(user.getPhone());
        int count = userMapper.insertSelective(insertUser);
        if(count > 0)
        {
            return ServerResponse.createBySuccessMsg("注册成功");
        }
        return ServerResponse.createByErrorMsg("注册失败");
    }

    public ServerResponse CheckVaild(String str, String type)
    {
        if(StringUtils.equals(type, Const.PHONE))
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

    @Override
    public ServerResponse updateUserInfo(User user)
    {
        User updateUser = new User();
        if(Const.checkSex(user.getSex()))
        {
            updateUser.setSex(user.getSex());
        }
        updateUser.setStage(user.getStage());
        updateUser.setId(user.getId());
        updateUser.setName(user.getName());
        updateUser.setMsg(user.getMsg());
        if(userMapper.updateByPrimaryKeySelective(updateUser) > 0)
        {
            return ServerResponse.createBySuccessMsg("更新信息成功");
        }
        return ServerResponse.createByErrorMsg("更新失败");
    }

    @Override
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

    @Override
    public ServerResponse forgetResetPassword(String msgCode, String phoneNumber, String password)
    {
        if (!PhoneUtil.judgeCodeIsTrue(msgCode, phoneNumber)) {
            return ServerResponse.createByErrorMsg("验证码错误");
        }
        password = MD5Util.MD5EncodeUtf8(password);
        if(userMapper.updateByPhone(phoneNumber, password) > 0)
        {
            return ServerResponse.createBySuccessMsg("修改成功");
        }
        return ServerResponse.createByErrorMsg("修改失败");
    }

    @Override
    public ServerResponse<User> getUserInfo(Integer id) {
        User user;
        if((user = userMapper.selectByPrimaryKey(id)) != null) {
            user.setImg(FTPUtil.ftpPrefix + user.getImg());
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorMsg("获取失败");
    }

    @Override
    public ServerResponse<User> checkName(String userName) {
        if(userMapper.selectNameCount(userName) > 0)
        {
            return ServerResponse.createByErrorMsg("昵称重复");
        }
        return ServerResponse.createBySuccessMsg("昵称可用");
    }
}
