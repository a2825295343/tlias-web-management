//package com.itheima.interceptor;
//
//import com.itheima.utils.CurrentHolder;
//import com.itheima.utils.JwtUtils;
//import io.jsonwebtoken.Claims;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//@Slf4j
//@Component
//public class TokenInterceptor implements HandlerInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        //HttpServletRequest request = (HttpServletRequest) servletRequest;
//        //HttpServletResponse response = (HttpServletResponse) servletResponse;
//        //获取请求路径
//        //String requestURI = request.getRequestURI();
//        //判断是否是登录请求
////        if (requestURI.contains("/login")) {
////            log.info("登录请求，放行");
////            //放行
////            return true;
////        }
//        //获取请求头中的token
//        String token = request.getHeader("token");
//        //判断token是否存在
//        if (token == null || token.isEmpty()) {
//            log.info("token不存在，拒绝访问");
//            //设置响应状态码
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); //401
//            //设置响应内容
//            //response.getWriter().write("token不存在，拒绝访问");
//            return false;
//        }
//        //若token存在，校验token
//        try {
//            Claims claims=JwtUtils.parseToken(token);
//            Integer empId=Integer.valueOf(claims.get("id").toString());
//            //JwtUtils.parseToken(token);
//            CurrentHolder.setCurrentId(empId);
//            log.info("当前登录员工ID：{}",empId);
//        } catch (Exception e) {
//            log.info("token非法，拒绝访问");
//            //设置响应状态码
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); //401
//            //设置响应内容
//            //response.getWriter().write("token不存在，拒绝访问");
//            return false;
//            //throw new RuntimeException(e);
//        }
//        //若校验通过，放行
//        log.info("token合法，放行");
//        return true;
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        CurrentHolder.remove();
//        //log.info("清除当前登录员工ID");
//    }
//}
