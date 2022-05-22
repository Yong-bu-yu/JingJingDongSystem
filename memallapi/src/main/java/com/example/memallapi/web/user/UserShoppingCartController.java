package com.example.memallapi.web.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.memallapi.entity.Commodity;
import com.example.memallapi.entity.ShoppingCart;
import com.example.memallapi.entity.User;
import com.example.memallapi.service.CommodityService;
import com.example.memallapi.service.ShoppingCartService;
import com.example.memallapi.service.UserService;
import com.github.flying.cattle.mdg.aid.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Api(description = "用户购物车", value = "用户购物车")
@RestController
@RequestMapping("/user/shoppingCart")
@PreAuthorize("hasAnyRole('ROLE_管理员','ROLE_用户')")
public class UserShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommodityService commodityService;

    private JsonResult<Map<String, String>> result = new JsonResult<>();

    @ApiOperation(value = "购物车添加", notes = "作者：PeiBo")
    @PostMapping("/insert")
    @ResponseBody
    @PreAuthorize("#shoppingCart.quantity > 0")
    public JsonResult<Map<String, String>> insert(ShoppingCart shoppingCart) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.query().eq("name", authentication.getName()).one();
        Commodity commodity = commodityService.getById(shoppingCart.getCommodityID());
        if (user != null && commodity != null) {
            shoppingCart.setUserID(user.getId());
            long quantity = commodity.getQuantity() - shoppingCart.getQuantity();
            if (quantity < 0)
                return result.error("添加失败");
            commodity.setQuantity(quantity);
            commodityService.saveOrUpdate(commodity);
            shoppingCartService.save(shoppingCart);
            return result.success("添加成功");
        }
        return result.error("添加失败");
    }

    @ApiOperation(value = "购物车更新", notes = "作者：PeiBo")
    @PostMapping("/update")
    @ResponseBody
    public JsonResult<Map<String, String>> update(ShoppingCart shoppingCart) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.query().eq("name", authentication.getName()).one();
        Commodity commodity = commodityService.getById(shoppingCart.getCommodityID());
        if (user != null && commodity != null) {
            shoppingCart.setUserID(user.getId());
            QueryWrapper<ShoppingCart> queryWrapper = new QueryWrapper<>();
            ShoppingCart temp = new ShoppingCart();
            temp.setId(shoppingCart.getId());
            queryWrapper.setEntity(temp);
            ShoppingCart ordShoppingCart = shoppingCartService.getOne(queryWrapper);
            //商品不为0
            long q = ordShoppingCart.getQuantity() - shoppingCart.getQuantity();
            if (q > 0 && commodity.getQuantity() == 0)
                commodity.setQuantity(commodity.getQuantity() + q);
            if (commodity.getQuantity() > 0) {
                commodity.setQuantity(commodity.getQuantity() + q);
                commodityService.saveOrUpdate(commodity);
                shoppingCartService.saveOrUpdate(shoppingCart);
            }
            if (shoppingCartService.getById(shoppingCart.getId()).getQuantity() == 0) {
                shoppingCartService.removeById(shoppingCart);
            }
            return result.success("更新成功");
        }
        return result.error("更新失败");
    }

    @ApiOperation(value = "购物车查询", notes = "作者：PeiBo")
    @PostMapping("/select")
    @ResponseBody
    public List<Map<Object, Object>> select() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.query().eq("name", authentication.getName()).one();
        if (user != null)
            return shoppingCartService.userShoppingCart(user.getId());
        return null;
    }
}
