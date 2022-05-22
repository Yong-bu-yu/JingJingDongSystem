package com.example.memallapi.utils.upload;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.memallapi.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件上传
 * <p>
 * 1. 创建excel对应的实体对象 参照{@link User}
 * <p>
 * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link UploadListener}
 * <p>
 * 3. 直接读即可
 */

@Slf4j
public class UploadListener<S extends IService<T>, T> extends AnalysisEventListener<T> {

    private static final int BATCH_COUNT = 300;
    private List<T> list = new ArrayList<>();
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    protected S baseService;

    public UploadListener(S baseService){
        this.baseService = baseService;
    }


    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        if (t instanceof User)
            ((User) t).setPassword(passwordEncoder.encode(((User) t).getPassword()));
        list.add(t);
        if(list.size() >= BATCH_COUNT){
            saveData();
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
        log.info("数据添加完成");
    }

    private void saveData(){
        log.info("{}条数据，开始存储数据库！", list.size());
        baseService.saveOrUpdateBatch(list);
    }
}
