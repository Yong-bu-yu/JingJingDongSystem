/**
 * @filename:UserServiceImpl 2019年6月12日
 * @project memall  V1.0
 * Copyright(c) 2018 PeiBo Co. Ltd. 
 * All right reserved. 
 */
package com.example.memallapi.service.impl;

import com.example.memallapi.entity.User;
import com.example.memallapi.dao.UserDao;
import com.example.memallapi.service.UserService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**   
 * <p>自动生成工具：mybatis-dsc-generator</p> 
 * 
 * <p>说明： 资金归集服务实现层</P>
 * @version: V1.0
 * @author: PeiBo
 * 
 */
@Service
public class UserServiceImpl  extends ServiceImpl<UserDao, User> implements UserService  {
	
}