/**
 * @filename:Commodity 2019年6月12日
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
import java.math.BigDecimal;

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
public class Commodity extends Model<Commodity> {

	private static final long serialVersionUID = 1645023899946L;

	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(name = "id" , value = "商品ID")
	private Long id;

	@ApiModelProperty(name = "name" , value = "商品名称")
	private String name;

	@ApiModelProperty(name = "type" , value = "商品类型")
	private String type;

	@ApiModelProperty(name = "quantity" , value = "商品数量")
	private Long quantity;

	@ApiModelProperty(name = "price", value = "商品价格")
	private BigDecimal price;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
