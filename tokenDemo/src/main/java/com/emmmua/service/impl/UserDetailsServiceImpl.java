package com.emmmua.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.emmmua.domain.User;
import com.emmmua.mapper.MenuMapper;
import com.emmmua.mapper.UserMapper;
import com.emmmua.utils.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(username != null, User::getUserName, username);
        User user = userMapper.selectOne(queryWrapper);

        // 如果没有查询到用户抛出异常
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户名或者密码错误");
        }

        // TODO 查询对应的权限信息
        List<String> list = menuMapper.selectPermsByUserId(user.getId());

        // 吧数据封装成UserDetails

        return new LoginUser(user, list);
    }
}
