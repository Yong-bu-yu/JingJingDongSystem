/**
 * @filename:ShoppingCartServiceImpl 2019年6月12日
 * @project memall  V1.0
 * Copyright(c) 2018 PeiBo Co. Ltd.
 * All right reserved.
 */
package com.example.memallapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.memallapi.dao.ShoppingCartDao;
import com.example.memallapi.entity.ShoppingCart;
import com.example.memallapi.service.ShoppingCartService;
import org.springframework.stereotype.Service;

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
public class ShoppingCartServiceImpl  extends ServiceImpl<ShoppingCartDao, ShoppingCart> implements ShoppingCartService  {

    @Override
    public List<Map<Object, Object>> shoppingTendency() {
        return baseMapper.shoppingTendency();
    }

    @Override
    public List<Map<Object, Object>> userShoppingCart(Long id) {
        return baseMapper.userShoppingCart(id);
    }
}
