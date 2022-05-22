package com.example.memallapi.config;

import com.example.memallapi.entity.User;
import com.example.memallapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@Configuration
public class UserDetailsConfig{

    @Autowired
    private UserService service;

    private User user;

    @Bean
    public UserDetails userDetails() {
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getType()));
                return authorities;
            }

            @Override
            public String getPassword() {
                return user.getPassword();
            }

            @Override
            public String getUsername() {
                return user.getName();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }

            @Override
            public String toString() {
                return getUsername();
            }
        };
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return s -> {
            user = service.query().eq("name", s).one();
            if (user == null)
                return null;
            log.warn(s);
            log.warn("当前用户 " + s + ", 权限为 " + user.getType());
            return userDetails();
        };
    }
}
