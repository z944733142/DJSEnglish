package com.djsenglish.service;

import com.djsenglish.common.ServerResponse;
import com.djsenglish.pojo.User;

public interface IUserService {

    ServerResponse Login(String phoneNumber, String password) throws Exception;

    ServerResponse Register(User user, String msgCode);

    ServerResponse updateUserInfo(User user);

    ServerResponse loginResetPassword(int userId, String password);

    ServerResponse forgetResetPassword(String msgCode, String phoneNumber, String password);

    ServerResponse<User> getUserInfo(Integer userId);

    ServerResponse<User> checkName(String userName);
}
