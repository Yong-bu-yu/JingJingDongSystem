package com.example.memallapi.web.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.memallapi.entity.Commodity;
import com.example.memallapi.service.CommodityService;
import com.github.flying.cattle.mdg.aid.JsonResult;
import com.github.flying.cattle.mdg.aid.PageParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "商品展示",value="商品展示" )
@RestController
public class CommodityListController {

    @Autowired
    private CommodityService commodityService;

    private JsonResult<IPage<Commodity>> returnPage=new JsonResult<IPage<Commodity>>();
    @GetMapping(value = "/commodityList", produces="application/json;charset=UTF-8")
    @ApiOperation(value = "分页查询", notes = "分页查询返回[IPage<T>],作者：PeiBo")
    public JsonResult<IPage<Commodity>> getPages(Commodity commodity,PageParam<Commodity> param){
        Page<Commodity> page=new Page<Commodity>(param.getPageNum(),param.getPageSize());
        QueryWrapper<Commodity> queryWrapper =new QueryWrapper<Commodity>();
        if(commodity.getName() != null)
            queryWrapper.like("name",commodity.getName());
        else queryWrapper.setEntity(commodity);
        //分页数据
        IPage<Commodity> pageData=commodityService.page(page, queryWrapper);
        return returnPage.success(pageData);
    }
}
