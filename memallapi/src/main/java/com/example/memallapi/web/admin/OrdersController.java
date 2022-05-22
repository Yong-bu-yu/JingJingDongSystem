/**
 * @filename:OrdersController 2019年6月12日
 * @project memall  V1.0
 * Copyright(c) 2020 PeiBo Co. Ltd.
 * All right reserved.
 */
package com.example.memallapi.web.admin;

import com.example.memallapi.aid.AbstractController;
import com.example.memallapi.entity.Orders;
import com.example.memallapi.service.OrdersService;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
@Api(description = "订单管理",value="订单管理" )
@RestController
@RequestMapping("/orders")
@PreAuthorize("hasRole('ROLE_管理员')")
public class OrdersController extends AbstractController<OrdersService,Orders>{

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private OrdersService ordersService;

	@ApiModelProperty(value = "用户订单完成统计", notes = "作者: PeiBo")
	@PostMapping("/userPay")
	@ResponseBody
	public List<Map<Object,Object>> userPay(){
		return ordersService.userPay();
	}
}
