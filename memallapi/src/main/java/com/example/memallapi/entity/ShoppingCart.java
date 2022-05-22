/**
 * @filename:ShoppingCart 2019年6月12日
 * @project memall  V1.0
 * Copyright(c) 2020 PeiBo Co. Ltd.
 * All right reserved.
 */
package com.example.memallapi.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>自动生成工具：mybatis-dsc-generator</p>
 *
 * <p>说明： 资金归集实体类</P>
 * @version: V1.0
 * @author: PeiBo
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ShoppingCart extends Model<ShoppingCart> {

	private static final long serialVersionUID = 1645023828754L;

	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(name = "id" , value = "购物车ID")
	private Long id;

	@ApiModelProperty(name = "userID" , value = "用户ID")
	private Long userID;

	@ApiModelProperty(name = "commodityID" , value = "商品ID")
	private Long commodityID;

	@ApiModelProperty(name = "quantity" , value = "商品数量")
	private Long quantity;

	@Override
	public Serializable pkVal() {
        return this.id;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public Long getCommodityID() {
		return commodityID;
	}

	public void setCommodityID(Long commodityID) {
		this.commodityID = commodityID;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
}
