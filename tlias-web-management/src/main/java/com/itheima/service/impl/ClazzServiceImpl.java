package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.ClazzMapper;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.PageResult;
import com.itheima.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;
    @Override
    public List<Clazz> findAll() {
        return clazzMapper.findAll();
    }

    @Override
    public PageResult<Clazz> page(String name ,
                                  LocalDate begin ,
                                  LocalDate end,
                                  Integer page ,
                                  Integer pageSize) {
//        LocalDate begin = clazzQueryParam.getBegin();
//        LocalDate end = clazzQueryParam.getEnd();
//        LocalDate now = LocalDate.now();
//        if(now.isBefore(begin)){
//            clazzQueryParam.setStatus("未开班");
//        }
//        else if(now.isAfter(end)){
//            clazzQueryParam.setStatus("已结课");
//        }
//        else{
//            clazzQueryParam.setStatus("已开班");
//        }
        PageHelper.startPage(page, pageSize); //仅能对紧接着的一个查询操作生效
        List<Clazz> clazzList = clazzMapper.list(name, begin, end);
        Page<Clazz> p = (Page<Clazz>) clazzList;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    @Override
    public void deleteById(Integer id) {
        clazzMapper.deleteById(id);
    }

    @Override
    public void add(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());

        clazzMapper.insert(clazz);
    }

    @Override
    public Clazz getById(Integer id) {
        return clazzMapper.getById(id);
    }

    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.updateById(clazz);
    }
}
