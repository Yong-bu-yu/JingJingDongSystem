/**
 * @filename:CommodityController 2019年6月12日
 * @project memall  V1.0
 * Copyright(c) 2020 PeiBo Co. Ltd. 
 * All right reserved. 
 */
package com.example.memallapi.web.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.memallapi.aid.AbstractController;
import com.example.memallapi.entity.Commodity;
import com.example.memallapi.service.CommodityService;
import com.github.flying.cattle.mdg.aid.JsonResult;
import com.github.flying.cattle.mdg.aid.PageParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**   
 * <p>自动生成工具：mybatis-dsc-generator</p>
 * 
 * <p>说明： 资金归集API接口层</P>
 * @version: V1.0
 * @author: PeiBo
 * @time    2019年6月12日
 *
 */
@Api(description = "商品管理",value="商品管理" )
@RestController
@RequestMapping("/commodity")
@PreAuthorize("hasRole('ROLE_管理员')")
public class CommodityController extends AbstractController<CommodityService,Commodity>{

	Logger logger = LoggerFactory.getLogger(this.getClass());

}