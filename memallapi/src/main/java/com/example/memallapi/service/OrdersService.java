/**
 * @filename:OrdersService 2019年6月12日
 * @project memall  V1.0
 * Copyright(c) 2020 PeiBo Co. Ltd.
 * All right reserved.
 */
package com.example.memallapi.service;

import com.example.memallapi.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>自动生成工具：mybatis-dsc-generator</p>
 *
 * <p>说明： 资金归集服务层</P>
 * @version: V1.0
 * @author: PeiBo
 *
 */
public interface OrdersService extends IService<Orders> {

    List<Map<Object,Object>> userOrders(Long id);

    List<Map<Object,Object>> userPay();
}
