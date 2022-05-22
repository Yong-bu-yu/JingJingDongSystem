/**
 * @filename:User 2019年6月12日
 * @project memall  V1.0
 * Copyright(c) 2020 PeiBo Co. Ltd.
 * All right reserved.
 */
package com.example.memallapi.entity;

import com.alibaba.excel.annotation.ExcelProperty;
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
public class User extends Model<User> {

	private static final long serialVersionUID = 1645023800039L;

	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(name = "id" , value = "用户ID")
	@ExcelProperty(value = "用户ID")
	private Long id;

	@ApiModelProperty(name = "name" , value = "用户名称")
	@ExcelProperty(value = "名称")
	private String name;

	@ApiModelProperty(name = "type" , value = "类型")
	@ExcelProperty(value = "类型")
	private String type;

	@ApiModelProperty(name = "password" , value = "密码")
	@ExcelProperty(value = "密码")
	private String password;


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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
