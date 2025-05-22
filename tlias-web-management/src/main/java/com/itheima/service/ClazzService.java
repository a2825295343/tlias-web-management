package com.itheima.service;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;

public interface ClazzService {
    List<Clazz> findAll();

    PageResult<Clazz> page(String name ,
                           LocalDate begin ,
                           LocalDate end,
                           Integer page ,
                           Integer pageSize);

    void deleteById(Integer id);

    void add(Clazz clazz);

    Clazz getById(Integer id);

    void update(Clazz clazz);
}
