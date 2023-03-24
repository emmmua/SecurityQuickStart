package com.emmmua.utils;

import com.alibaba.fastjson.annotation.JSONField;
import com.emmmua.domain.User;
import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
public class LoginUser implements UserDetails {

    private User user;


    //存储权限信息
    private List<String> permissions;

    // 获取权限信息

    @JSONField(serialize = false)
    List<SimpleGrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        // 第一次转换，不为空，返回
        if (!Objects.isNull(authorities)) {
            return authorities;
        }

        // 把permissions中的string封装成SimpleGrantedAuthority对象
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        for (String permission : permissions) {
//            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(permission);
//            grantedAuthorities.add(simpleGrantedAuthority);
//        }

        // stream流的方式
        authorities = permissions.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return authorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUserName();
    }

    // 是否没过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    // 是否未锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 凭证是否过期（是否超时）
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public LoginUser(User user) {
        this.user = user;
    }

    public LoginUser(User user, List<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    // 是否可用
    @Override
    public boolean isEnabled() {
        return true;
    }
}
