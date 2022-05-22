/**
 * @filename:Orders 2019年6月12日
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
import java.sql.Timestamp;

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
public class Orders extends Model<Orders> {

	private static final long serialVersionUID = 1645023853813L;

	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(name = "id" , value = "订单ID")
	private Long id;

	@ApiModelProperty(name = "number" , value = "订单编号")
	private String number;

	@ApiModelProperty(name = "shoppingCartID" , value = "购物车ID")
	private Long shoppingCartID;

	@ApiModelProperty(name = "isPay" , value = "是否已支付")
	private Long isPay;

	@ApiModelProperty(name = "time" , value = "订单时间")
	private Timestamp time;

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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Long getShoppingCartID() {
		return shoppingCartID;
	}

	public void setShoppingCartID(Long shoppingCartID) {
		this.shoppingCartID = shoppingCartID;
	}

	public Long getIsPay() {
		return isPay;
	}

	public void setIsPay(Long isPay) {
		this.isPay = isPay;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}
}
