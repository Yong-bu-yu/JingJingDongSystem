/**
 * @filename:OrdersServiceImpl 2019年6月12日
 * @project memall  V1.0
 * Copyright(c) 2018 PeiBo Co. Ltd.
 * All right reserved.
 */
package com.example.memallapi.service.impl;

import com.example.memallapi.entity.Orders;
import com.example.memallapi.dao.OrdersDao;
import com.example.memallapi.service.OrdersService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

/**
 * <p>自动生成工具：mybatis-dsc-generator</p>
 *
 * <p>说明： 资金归集服务实现层</P>
 * @version: V1.0
 * @author: PeiBo
 *
 */
@Service
public class OrdersServiceImpl  extends ServiceImpl<OrdersDao, Orders> implements OrdersService  {

    @Override
    public List<Map<Object, Object>> userOrders(Long id) {
        return baseMapper.userOrders(id);
    }

    @Override
    public List<Map<Object, Object>> userPay() {
        return baseMapper.userPay();
    }


}
