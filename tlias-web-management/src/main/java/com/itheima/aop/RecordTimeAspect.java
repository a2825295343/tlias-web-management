package com.itheima.aop;

import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect    //标识这是一个切面类
@Component
@Slf4j
public class RecordTimeAspect {
    @Autowired
    private OperateLogMapper operateLogMapper;

//    @Pointcut("execution(* com.itheima.service.impl.*.*(..))") //定义切入点
//    private void pt(){}

    //@Around("pt()") //拦截com.itheima.service.impl包下的所有方法
    @Around("@annotation(com.itheima.anno.OperateLog)")
    public Object recordTime(ProceedingJoinPoint pjp) throws Throwable {
        long startTime=System.currentTimeMillis();

        Object result=pjp.proceed(); //执行原始方法

        long endTime=System.currentTimeMillis();
        long costTime=endTime-startTime;

        //MethodSignature methodSignature=(MethodSignature)pjp.getSignature();
        //Method method=methodSignature.getMethod();

        OperateLog olog = new OperateLog();
        olog.setOperateEmpId(getCurrentUserId());
        olog.setOperateTime(LocalDateTime.now());
        olog.setClassName(pjp.getTarget().getClass().getName());
        olog.setMethodName(pjp.getSignature().getName());
        olog.setMethodParams(Arrays.toString(pjp.getArgs()));
        olog.setReturnValue(result != null ? result.toString() : "void");
        olog.setCostTime(costTime);

        log.info("记录操作日志：{}",olog);
        operateLogMapper.insert(olog);
        return result;
    }

    private Integer getCurrentUserId(){
        return CurrentHolder.getCurrentId();
    }
}
