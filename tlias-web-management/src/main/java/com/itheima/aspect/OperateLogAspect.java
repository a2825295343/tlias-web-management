//package com.itheima.aspect;
//
//import com.itheima.pojo.OperateLog;
//import com.itheima.service.OperateLogService;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//import java.util.Arrays;
//
//@Slf4j
//@Aspect
//@Component
//public class OperateLogAspect {
//
//    @Autowired
//    private OperateLogService operateLogService;
//
//    // 拦截所有Service层的增删改方法
//    @Around("execution(* com.itheima.service.*.*(..)) && (@annotation(org.springframework.web.bind.annotation.PostMapping) || @annotation(org.springframework.web.bind.annotation.PutMapping) || @annotation(org.springframework.web.bind.annotation.DeleteMapping))")
//    public Object recordOperateLog(ProceedingJoinPoint joinPoint) throws Throwable {
//        long start = System.currentTimeMillis();
//        Object result = null;
//        Exception ex = null;
//        try {
//            result = joinPoint.proceed();
//            return result;
//        } catch (Exception e) {
//            ex = e;
//            throw e;
//        } finally {
//            long end = System.currentTimeMillis();
//            OperateLog logEntry = new OperateLog();
//            logEntry.setOperateEmpId(1); // 可根据实际登录用户设置
//            logEntry.setOperateTime(LocalDateTime.now());
//            logEntry.setClassName(joinPoint.getTarget().getClass().getName());
//            logEntry.setMethodName(joinPoint.getSignature().getName());
//            logEntry.setMethodParams(Arrays.toString(joinPoint.getArgs()));
//            logEntry.setReturnValue(result != null ? result.toString() : (ex != null ? ex.getMessage() : null));
//            logEntry.setCostTime((int) (end - start));
//            logEntry.setOperateEmpName("admin"); // 可根据实际登录用户设置
//            operateLogService.save(logEntry);
//        }
//    }
//}