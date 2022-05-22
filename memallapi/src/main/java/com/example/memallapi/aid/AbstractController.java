/**
 * @filename:UserController 2019年6月12日
 * @project memall  V1.0
 * Copyright(c) 2020 PeiBo Co. Ltd.
 * All right reserved.
 */
package com.example.memallapi.aid;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.memallapi.entity.User;
import com.example.memallapi.utils.upload.UploadListener;
import com.github.flying.cattle.mdg.aid.JsonResult;
import com.github.flying.cattle.mdg.aid.PageParam;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * <p>自动生成工具：mybatis-dsc-generator</p>
 *
 * <p>说明： 资金归集API接口层</P>
 *
 * @version: V1.0
 * @author: PeiBo
 * @time 2019年6月12日
 */
@Slf4j
@PreAuthorize("hasRole('ROLE_管理员')")
@CrossOrigin
public class AbstractController<S extends IService<T>, T>{

    @Autowired
    protected S baseService;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    protected JsonResult<T> result = new JsonResult<T>();

    /**
     * @param 对象参数：id
     * @return JsonResult
     * @explain 查询对象  <swagger GET请求>
     * @author PeiBo
     * @time 2019年6月12日
     */
    @GetMapping("/getById/{id}")
    @ApiOperation(value = "获取对象", notes = "作者：PeiBo")
    @ApiImplicitParam(paramType = "path", name = "id", value = "对象id", required = true, dataType = "Long")
    public JsonResult<T> getById(@PathVariable("id") Long id) {
        T obj = baseService.getById(id);
        if (null != obj) {
            result.success(obj);
        } else {
            result.error("查询对象不存在！");
        }
        return result;
    }

    /**
     * @param 对象参数：id
     * @return JsonResult
     * @explain 删除对象
     * @author PeiBo
     * @time 2019年6月12日
     */
    @PostMapping("/deleteById")
    @ApiOperation(value = "删除", notes = "作者：PeiBo")
    @ApiImplicitParam(paramType = "query", name = "id", value = "对象id", required = true, dataType = "Long")
    public JsonResult<T> deleteById(Long id) {
        JsonResult<T> result = new JsonResult<T>();
        T obj = baseService.getById(id);
        if (null != obj) {
            if(obj instanceof User)
                if(((User) obj).getId() == 1)
                    id = null;
            boolean rsg = baseService.removeById(id);
            if (rsg) {
                result.success("删除成功");
            } else {
                result.error("删除失败！");
            }
        } else {
            result.error("删除的对象不存在！");
        }
        return result;
    }

    /**
     * @param 对象参数：T
     * @return Boolean
     * @explain 添加
     * @author PeiBo
     * @time 2019年6月12日
     */
    @PostMapping("/insert")
    @ApiOperation(value = "添加", notes = "作者：PeiBo")
    public JsonResult<T> insert(T entity) {
        JsonResult<T> result = new JsonResult<T>();
        if (null != entity) {
            if (entity instanceof User) {
                ((User) entity).setPassword(passwordEncoder.encode(((User) entity).getPassword()));
            }
            boolean rsg = baseService.save(entity);
            if (rsg) {
                result.success("添加成功");
            } else {
                result.error("添加失败！");
            }
        } else {
            result.error("请传入正确参数！");
        }
        return result;
    }

    /**
     * @param 对象参数：T
     * @return Boolean
     * @explain 修改
     * @author PeiBo
     * @time 2019年6月12日
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "作者：PeiBo")
    public JsonResult<T> update(T entity) {
        JsonResult<T> result = new JsonResult<T>();
        if (null != entity) {
            if (entity instanceof User)
                ((User) entity).setPassword(passwordEncoder.encode(((User) entity).getPassword()));
            boolean rsg = baseService.updateById(entity);
            if (rsg) {
                result.success("修改成功");
            } else {
                result.error("修改失败！");
            }
        } else {
            result.error("请传入正确参数！");
        }
        return result;
    }

    /**
     * @param 对象参数：AppPage<User>
     * @return PageInfo<User>
     * @explain 分页条件查询用户
     * @author PeiBo
     * @time 2019年6月12日
     */
    @GetMapping("/getPages")
    @ApiOperation(value = "分页查询", notes = "分页查询返回[IPage<T>],作者：PeiBo")
    public JsonResult<IPage<T>> getPages(T t, PageParam<T> param) {
        JsonResult<IPage<T>> returnPage = new JsonResult<IPage<T>>();
        Page<T> page = new Page<T>(param.getPageNum(), param.getPageSize());
        QueryWrapper<T> queryWrapper = new QueryWrapper<T>();
        queryWrapper.setEntity(t);
        //分页数据
        IPage<T> pageData = baseService.page(page, queryWrapper);
        returnPage.success(pageData);
        return returnPage;
    }

    @PostMapping(value = "/deleteList")
    @ResponseBody
    public JsonResult<T> deleteList(@RequestBody List<T> ts) {
		JsonResult<T> result = new JsonResult<T>();
		if (ts.size() != 0){
			boolean res = baseService.removeBatchByIds(ts);
			if (res)
				return result.success("删除成功");
			else
				return result.error("删除失败！");
		}
        return result;
    }

    @PostMapping(value = "/upload")
    @ResponseBody
    public JsonResult<T> upload(MultipartFile file) throws IOException {
        JsonResult<T> result = new JsonResult<T>();
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        Type actualTypeArgument = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[1];
        UploadListener<S,T> uploadListener = new UploadListener<>(baseService);
        EasyExcel.read(file.getInputStream(), (Class) actualTypeArgument, uploadListener).sheet().doRead();
        return result.success("添加成功");
    }
}
