package com.itheima.service;

import com.itheima.pojo.ClazzCountOption;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {
    PageResult<Student> page(String name, Integer degree, Integer clazzId, Integer page, Integer pageSize);

    void delete(List<Integer> ids);

    void save(Student student);

    Student getById(Integer id);

    void update(Student student);

    void violation(Integer id, Integer score);

    //List<Map> getStudentDegreeData();

    /**
     * 统计班级人数
     */
    //ClazzCountOption getStudentCountData();

}
