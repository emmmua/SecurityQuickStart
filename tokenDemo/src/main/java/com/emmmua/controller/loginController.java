package com.emmmua.controller;

import com.emmmua.domain.User;
import com.emmmua.service.LoginService;
import com.emmmua.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class loginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user) {
        // 登录
        ResponseResult responseResult = loginService.login(user);
        return responseResult;
    }

    @PostMapping("/logout")
    public ResponseResult logout() {
        return loginService.logout();
    }
}
