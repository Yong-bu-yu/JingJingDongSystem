/**
 * @filename:UserDao 2019年6月12日
 * @project memall  V1.0
 * Copyright(c) 2020 PeiBo Co. Ltd. 
 * All right reserved. 
 */
package com.example.memallapi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.example.memallapi.entity.User;
import org.springframework.stereotype.Repository;

/**   
 * <p>自动生成工具：mybatis-dsc-generator</p>
 * 
 * <p>说明： 资金归集数据访问层</p>
 * @version: V1.0
 * @author: PeiBo
 * 
 */
//@Mapper
public interface UserDao extends BaseMapper<User> {
	
}
