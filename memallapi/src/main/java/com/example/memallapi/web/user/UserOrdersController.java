package com.example.memallapi.web.user;

import com.example.memallapi.entity.Orders;
import com.example.memallapi.entity.ShoppingCart;
import com.example.memallapi.entity.User;
import com.example.memallapi.service.OrdersService;
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
import java.util.UUID;

@Api(description = "用户订单", value = "用户订单")
@RestController
@RequestMapping("/user/orders")
@PreAuthorize("hasAnyRole('ROLE_管理员','ROLE_用户')")
public class UserOrdersController {

    private JsonResult<Map<String, String>> result = new JsonResult<>();

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "订单添加", notes = "作者：PeiBo")
    @PostMapping("/insert")
    @ResponseBody
    public JsonResult<Map<String, String>> insert(ShoppingCart shoppingCart) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.query().eq("name", authentication.getName()).one();

        if(user == null)
            return result.error("订单生成失败");
        ShoppingCart oldShoppingCart = shoppingCartService.query().eq("id",shoppingCart.getId()).eq("user_i_d",user.getId()).one();

        Orders orders = new Orders();
        orders.setNumber(UUID.randomUUID().toString());
        orders.setShoppingCartID(oldShoppingCart.getId());
        orders.setIsPay(0L);

        ordersService.save(orders);
        return result.success("订单生成成功");
    }

    @ApiOperation(value = "订单更新", notes = "作者：PeiBo")
    @PostMapping("/update")
    @ResponseBody
    public JsonResult<Map<String,String>> update(Long shoppingCarID){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.query().eq("name", authentication.getName()).one();
        ShoppingCart shoppingCart = shoppingCartService.query().eq("id", shoppingCarID).eq("user_i_d",user.getId()).one();
        if(shoppingCart == null)
            return result.error("不存在购物车");
        Orders orders = ordersService.query().eq("shopping_cart_i_d", shoppingCart.getId()).one();
        if(orders == null)
            return result.error("不存在的订单");
        orders.setIsPay(1L);
        ordersService.saveOrUpdate(orders);

        return result.success("更新成功");
    }

    @ApiOperation(value = "订单查询", notes = "作者：PeiBo")
    @PostMapping("/select")
    @ResponseBody
    public List<Map<Object,Object>> userOrders(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.query().eq("name", authentication.getName()).one();

        return ordersService.userOrders(user.getId());
    }
}
