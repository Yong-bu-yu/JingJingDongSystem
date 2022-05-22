package com.example.memallapi;

import com.example.memallapi.service.RedisService;
import com.example.memallapi.service.ShoppingCartService;
import com.example.memallapi.service.UserService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@MapperScan("com/example/memallapi/dao")
@SpringBootTest
class MemallapiApplicationTests {

    @Autowired
    UserService userService;
    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    HandlerMapping viewControllerHandlerMapping;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    RedisService redisService;

    @Test
    void contextLoads() throws Exception {
//        userService.list().forEach(System.out::println);
//        Map<String,Object> map = new HashMap<>();
//        map.put("name","永不语");
//        System.out.println(userService.query().eq("name", "永不语").one());
//        User user = userService.getById(1);
//        JWTUtils jwtUtils = new JWTUtils();
//        String token = jwtUtils.createToken(user);
//        String verifyToken = jwtUtils.verifyToken(token);
//        System.out.println(token+" "+verifyToken);
//        System.out.println(new BCryptPasswordEncoder().encode("123"));

//        Page<User> page=new Page<User>(1,10);
//        QueryWrapper<User> queryWrapper =new QueryWrapper<User>();
//        User user = new User();
//        user.setId(1L);
//        user.setName("2333");
//        user.setType("用户");
//        queryWrapper.setEntity(user);
        //分页数据
//        IPage<User> pageData=userService.page(page, queryWrapper);
//        System.out.println(pageData.getRecords());
//        shoppingCartService.shoppingTendency();
        System.out.println(UUID.randomUUID().toString().length());
    }

}
