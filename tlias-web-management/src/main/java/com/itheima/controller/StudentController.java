package com.itheima.controller;

import com.itheima.anno.OperateLog;
import com.itheima.pojo.*;
import com.itheima.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/students")
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping
    public Result page(String name,
                       Integer degree,
                       Integer clazzId,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("分页查询学生信息：{}, {}, {}, {}, {}", name, degree, clazzId, page, pageSize);
        PageResult<Student> pageResult = studentService.page(name, degree, clazzId, page, pageSize);
        return Result.success(pageResult);
    }

    @OperateLog
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("删除学员信息：{}", ids);
        studentService.delete(ids);
        return Result.success();
    }

    @OperateLog
    @PostMapping
    public Result save(@RequestBody Student student){
        log.info("新增学员：{}",student);
        studentService.save(student);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        //System.out.println("根据ID查询班级："+id);
        log.info("根据ID查询学员：{}",id);
        Student student = studentService.getById(id);
        return Result.success(student);
    }

    @OperateLog
    @PutMapping
    public Result update(@RequestBody Student student) {
        log.info("修改学员信息：{}", student);
        studentService.update(student);
        return Result.success();
    }

    @OperateLog
    @PutMapping("/violation/{id}/{score}")
    public Result violation(@PathVariable Integer id, @PathVariable Integer score) {
        log.info("学员：{} 违纪扣分：{}", id, score);
        studentService.violation(id, score);
        return Result.success();
    }
}
