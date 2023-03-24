package com.emmmua.service;

import com.emmmua.domain.User;
import com.emmmua.utils.ResponseResult;

public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
