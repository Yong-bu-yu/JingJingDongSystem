/**
 * @filename:UserController 2019年6月12日
 * @project memall  V1.0
 * Copyright(c) 2020 PeiBo Co. Ltd.
 * All right reserved.
 */
package com.example.memallapi.web.admin;

import com.example.memallapi.aid.AbstractController;
import com.example.memallapi.entity.User;
import com.example.memallapi.service.UserService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>自动生成工具：mybatis-dsc-generator</p>
 *
 * <p>说明： 资金归集API接口层</P>
 * @version: V1.0
 * @author: PeiBo
 * @time 2019年6月12日
 *
 */
@Api(description = "管理员管理", value = "管理员管理")
@RestController
@PreAuthorize("hasRole('ROLE_管理员')")
@RequestMapping("/admin")
public class AdminController extends AbstractController<UserService, User> {

    Logger logger = LoggerFactory.getLogger(this.getClass());
}
