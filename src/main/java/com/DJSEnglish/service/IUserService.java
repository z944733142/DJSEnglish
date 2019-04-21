package com.DJSEnglish.service;

import com.DJSEnglish.common.ServerResponse;
import com.DJSEnglish.pojo.User;

public interface IUserService {

    ServerResponse Login(String phoneNumber, String password) throws Exception;

    ServerResponse Register(User user, String msgCode);

    ServerResponse updateUserInfo(User user);

    ServerResponse loginResetPassword(int userId, String password);

    ServerResponse forgetResetPassword(String msgCode,String phoneNumber, String password);

    ServerResponse<User> getUserInfo(Integer userId);

    ServerResponse<User> checkName(String name);
}
