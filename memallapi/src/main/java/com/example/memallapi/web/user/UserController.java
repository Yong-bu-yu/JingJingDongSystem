package com.example.memallapi.web.user;

import com.example.memallapi.entity.User;
import com.example.memallapi.service.RedisService;
import com.example.memallapi.service.UserService;
import com.example.memallapi.utils.JWTUtils;
import com.github.flying.cattle.mdg.aid.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(description = "用户管理", value = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    private JsonResult<Map<String, String>> result = new JsonResult<>();

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private PasswordEncoder encoder;

    @ApiOperation(value = "用户登录", notes = "作者：PeiBo")
    @PostMapping("/login")
    @ResponseBody
    JsonResult<Map<String, String>> login() {
        SecurityContext context = SecurityContextHolder.getContext();
        Map<String, String> map = new HashMap<>();
        Authentication authentication = context.getAuthentication();
        User user = new User();
        user.setName(authentication.getName());
        if (user != null) {
            String token = JWTUtils.createToken(user);
            redisService.set(user.getName(),token);
            map.put("Authentication", token);
            return result.success(map);
        }
        return result.error("登录失败");
    }

    @ApiOperation(value = "用户注册", notes = "作者：PeiBo")
    @PostMapping("/register")
    @ResponseBody
    @PreAuthorize("#password.length() >= 6")
    JsonResult<Map<String,String>> register(String username,String password){
        if(redisService.hasKey(username))
            return result.error("注册失败");
        User user = (User) redisService.get(username);
        if(user != null){
            userService.save(user);
            return result.success("注册成功");
        }
        return result.error("注册失败");
    }

    @ApiOperation(value = "用户退出", notes = "作者：PeiBo")
    @PostMapping("/logout")
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROLE_管理员','ROLE_用户')")
    JsonResult<Map<String, String>> logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
        return result.success("退出成功");
    }

    @ApiOperation(value = "用户信息", notes = "作者：PeiBo")
    @PreAuthorize("hasAnyRole('ROLE_管理员','ROLE_用户')")
    @GetMapping("/info")
    @ResponseBody
    JsonResult<Map<String, String>> info() {
        SecurityContext context = SecurityContextHolder.getContext();
        Map<String, String> map = new HashMap<>();
        Authentication authentication = context.getAuthentication();
        User user = userService.query().eq("name", authentication.getName()).one();
        if(user == null)
            return result.error("获取失败");
        map.put("id",user.getId().toString());
        map.put("principal", user.getName());
        map.put("authorities", user.getType());
        System.out.println(authentication);
        return result.success(map);
    }

    @ApiOperation(value = "用户重置密码", notes = "作者：PeiBo")
    @PreAuthorize("hasAnyRole('ROLE_管理员','ROLE_用户') and #newpassword.length() > 6 or #newpassword == null")
    @PostMapping("/resetPassword")
    @ResponseBody
    JsonResult<Map<String, String>> resetPassword(String username,String oldpassword, String newpassword){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.query().eq("name", authentication.getName()).one();
        if(oldpassword == null && newpassword == null && username != null){
            user.setName(username);
            userService.saveOrUpdate(user);
            return result.success("成功修改用户名");
        }
        else {
            if(encoder.matches(oldpassword,user.getPassword())){
                user.setPassword(newpassword);
                userService.saveOrUpdate(user);
                return result.success("成功修改密码");
            }
        }
        return result.error("修改失败");
    }
}
