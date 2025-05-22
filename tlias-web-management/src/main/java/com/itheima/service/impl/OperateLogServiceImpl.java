package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.OperateLog;
import com.itheima.pojo.PageResult;
import com.itheima.service.OperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperateLogServiceImpl implements OperateLogService {
    @Autowired
    private OperateLogMapper operateLogMapper;
    @Override
    public PageResult<OperateLog> page(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize); //仅能对紧接着的一个查询操作生效
        List<OperateLog> operateLogList = operateLogMapper.list();
        Page<OperateLog> p = (Page<OperateLog>) operateLogList;
        return new PageResult<>(p.getTotal(), p.getResult());
    }
//        @Override
//        public void save(OperateLog log) {
//            operateLogMapper.insert(log);
//        }
//        // 其他方法...

}
