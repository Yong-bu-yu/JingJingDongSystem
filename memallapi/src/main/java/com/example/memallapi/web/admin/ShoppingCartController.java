/**
 * @filename:ShoppingCartController 2019年6月12日
 * @project memall  V1.0
 * Copyright(c) 2020 PeiBo Co. Ltd.
 * All right reserved.
 */
package com.example.memallapi.web.admin;

import com.example.memallapi.aid.AbstractController;
import com.example.memallapi.entity.ShoppingCart;
import com.example.memallapi.service.ShoppingCartService;
import com.github.flying.cattle.mdg.aid.JsonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * <p>自动生成工具：mybatis-dsc-generator</p>
 *
 * <p>说明： 资金归集API接口层</P>
 * @version: V1.0
 * @author: PeiBo
 * @time    2019年6月12日
 *
 */
@Api(description = "购物车管理",value="购物车管理" )
@RestController
@PreAuthorize("hasRole('ROLE_管理员')")
@RequestMapping("/shoppingCart")
public class ShoppingCartController extends AbstractController<ShoppingCartService,ShoppingCart>{

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ShoppingCartService shoppingCartService;

	@GetMapping("/shoppingTendency")
	@ApiOperation(value = "用户购物倾向", notes = "作者：PeiBo")
	JsonResult<List<Map<Object,Object>>> ShoppingTendency(){
		JsonResult<List<Map<Object,Object>>> result = new JsonResult<>();
		return result.success(shoppingCartService.shoppingTendency());
	}
}
