package com.itheima.controller;

import com.itheima.anno.OperateLog;
import com.itheima.pojo.*;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {
    @Autowired
    private EmpService empService;

    //分页查询
//    @GetMapping
//    public Result page(@RequestParam(defaultValue="1") Integer page,
//                       @RequestParam(defaultValue="10")Integer pageSize,
//                       String name, Integer gender,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
//        log.info("分页查询：{}, {}, {}, {}, {}, {}",page,pageSize,name,gender,begin,end);
//        PageResult<Emp> pageResult = empService.page(page,pageSize,name,gender,begin,end);
//        return Result.success(pageResult);
//    }

    @GetMapping
    public Result page(EmpQueryParam empQueryParam){
        log.info("分页查询员工信息：{}",empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    //新增员工
    @PostMapping
    @OperateLog
    public Result save(@RequestBody Emp emp){
        log.info("新增员工：{}",emp);
        empService.save(emp);
        return Result.success();
    }

//    @DeleteMapping
//    public Result delete(Integer[] ids){
//        log.info("删除员工信息：{}", Arrays.toString(ids));
//        return Result.success();
//    }

    @DeleteMapping
    @OperateLog
    public Result delete(@RequestParam List<Integer> ids){
        log.info("删除员工信息：{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    //根据id查询员工信息
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据ID查询员工信息：{}",id);
        Emp emp=empService.getInfo(id);
        return Result.success(emp);
    }

    //修改员工信息
    @OperateLog
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改员工信息：{}",emp);
        empService.update(emp);
        return Result.success();
    }

    @GetMapping("/list")
    public Result listByJob(){
        //System.out.println("查询全部员工数据");
        log.info("查询全部员工数据（暂时）");
        List<Emp> empList=empService.findByJob();
        return Result.success(empList);
    }
}
