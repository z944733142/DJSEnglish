package com.djsenglish.service;

import com.djsenglish.common.ServerResponse;
import com.djsenglish.pojo.User;

/**
 * @author shuo
 */
public interface IUserService {

    ServerResponse login(String phoneNumber, String password) throws Exception;

    ServerResponse qqLogin(String qqId)throws Exception;

    ServerResponse register(User user, String msgCode);

    ServerResponse updateUserInfo(User user);

    ServerResponse loginResetPassword(int userId, String password);

    ServerResponse forgetResetPassword(String msgCode, String phoneNumber, String password);

    ServerResponse<User> getUserInfo(Integer userId);

    ServerResponse<User> checkName(String userName);

    ServerResponse qqRegister(String qqId, String phone, String msgCode, String img, String name);
}
