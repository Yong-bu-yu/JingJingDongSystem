/**
 * @filename:ShoppingCartDao 2019年6月12日
 * @project memall  V1.0
 * Copyright(c) 2020 PeiBo Co. Ltd.
 * All right reserved.
 */
package com.example.memallapi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.memallapi.entity.ShoppingCart;

import java.util.List;
import java.util.Map;

/**
 * <p>自动生成工具：mybatis-dsc-generator</p>
 *
 * <p>说明： 资金归集数据访问层</p>
 * @version: V1.0
 * @author: PeiBo
 *
 */
//@Mapper
public interface ShoppingCartDao extends BaseMapper<ShoppingCart> {
	List<Map<Object,Object>> shoppingTendency();

	List<Map<Object,Object>> userShoppingCart(Long id);
}
