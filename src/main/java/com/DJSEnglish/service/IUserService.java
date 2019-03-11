package com.DJSEnglish.service;

import com.DJSEnglish.common.ServerResponse;
import com.DJSEnglish.pojo.User;

public interface IUserService {

    ServerResponse Login(String username, String password);

    ServerResponse Register(User user);

    ServerResponse updateUserInfo(User user);

    ServerResponse loginResetPassword(int id, String password);

}
