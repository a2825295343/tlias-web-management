package com.itheima.pojo;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Student {
    private Integer id; //ID,主键
    //private String username; //用户名
    //private String password; //密码
    private String name; //姓名
    private String no;
    private Integer gender; //性别，1:男，2:女
    private String phone;
    private String idCard; //身份证号
    private Integer isCollege; // 是否来自院校，1:是，0:否
    private String address;
    private Integer degree; //最高学历, 1:初中, 2:高中, 3:大专, 4:本科, 5:硕士, 6:博士
    private LocalDate graduationDate;
    private Integer clazzId;
    private Integer violationCount; //违纪次数
    private Integer violationScore; //违纪扣分
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String clazzName; // 班级名称
    //private String deptName; //部门名称
    //private List<EmpExpr> exprList; //工作经历集合
}
