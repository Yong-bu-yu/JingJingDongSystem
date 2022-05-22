package com.example.memallapi.filter;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.memallapi.entity.User;
import com.example.memallapi.service.RedisService;
import com.example.memallapi.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(filterName = "TokenFilter", urlPatterns = "/*")
public class TokenFilter extends OncePerRequestFilter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authentication");

        if (request.getRequestURI().indexOf("login") != -1 || request.getRequestURI().indexOf("logout") != -1 || request.getRequestURI().indexOf("register") != -1) {

            String username = request.getParameter("username");
            String password = request.getParameter("password");
            if (request.getRequestURI().indexOf("login") != -1) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if (userDetails != null) {
                    if (passwordEncoder.matches(password, userDetails.getPassword())) {
                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
                        authentication.setDetails(userDetails);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                    else
                        response.setStatus(404);
                }
                log.warn("用户："+username+" 将进行登录");
            }
            else if(request.getRequestURI().indexOf("logout") != -1){
                if(StringUtils.isNotBlank(token)){
                    String name = JWTUtils.verifyToken(token);
                    if(redisService.hasKey(name))
                        redisService.del(name);
                    log.warn("用户："+name+" 已经登出");
                }
            }
            else {
                User user = new User();
                user.setName(username);
                user.setPassword(passwordEncoder.encode(password));
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if(userDetails != null)
                    response.setStatus(404);
                if(!redisService.hasKey(username))
                    redisService.set(username,user);
            }
            filterChain.doFilter(request, response);
            return;
        }

        if (StringUtils.isNotBlank(token)) {
            String name = JWTUtils.verifyToken(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(name);
            if (userDetails != null) {
                Object redisToken = null;
                if(redisService.hasKey(userDetails.getUsername())){
                    redisToken = redisService.get(userDetails.getUsername());
                }
                if (redisToken instanceof String)
                    if (redisToken.toString().equals(token)) {
                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
                        authentication.setDetails(userDetails);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        log.warn("设置授权");
                    }
            }
        }
        filterChain.doFilter(request, response);
    }
}
