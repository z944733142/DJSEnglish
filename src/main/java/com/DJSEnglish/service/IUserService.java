package com.DJSEnglish.service;

import com.DJSEnglish.common.ServerResponse;

public interface IUserService {

    ServerResponse Login(String username, String password);
}
