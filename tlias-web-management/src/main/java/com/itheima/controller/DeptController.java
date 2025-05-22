package com.itheima.controller;

import com.itheima.anno.OperateLog;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {
    //private static final Logger log= LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;

    //@RequestMapping(value="/depts",method= RequestMethod.GET)
    @GetMapping
    public Result list(){
        //System.out.println("查询全部部门数据");
        log.info("查询全部部门数据");
        List<Dept> deptList=deptService.findAll();
        return Result.success(deptList);
    }

//    @DeleteMapping("/depts")
//    public Result delete(HttpServletRequest request){
//        String idStr=request.getParameter("id");
//        int id=Integer.parseInt(idStr);
//        System.out.println("根据ID删除部门："+id);
//        return Result.success();
//    }

//    @DeleteMapping("/depts")
//    public Result delete(@RequestParam("id")Integer deptId){
//        System.out.println("根据ID删除部门："+deptId);
//        return Result.success();
//    }

    //@RequestParam可省略要求：形参变量名与请求参数名相同
    @OperateLog
    @DeleteMapping
    public Result delete(Integer id){
        //System.out.println("根据ID删除部门："+id);
        log.info("根据ID删除部门：{}",id);
        deptService.deleteById(id);
        return Result.success();
    }

    @OperateLog
    @PostMapping
    public Result add(@RequestBody Dept dept){
        //System.out.println("新增部门："+dept);
        log.info("新增部门：{}",dept);
        deptService.add(dept);
        return Result.success();
    }

//    @GetMapping("/depts/{id}")
//    public Result getInfo(@PathVariable("id") Integer deptId){
//        System.out.println("根据ID查询部门："+deptId);
//        //Dept dept = deptService.findById(id);
//        return Result.success();
//    }

    //若方法形参名和路径参数名一致，@PathVariable后面的属性值可以省略
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        //System.out.println("根据ID查询部门："+id);
        log.info("根据ID查询部门：{}",id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    @OperateLog
    @PutMapping
    public Result update(@RequestBody Dept dept){
        //System.out.println("修改部门："+dept);
        log.info("修改部门：{}",dept);
        deptService.update(dept);
        return Result.success();
    }
}
