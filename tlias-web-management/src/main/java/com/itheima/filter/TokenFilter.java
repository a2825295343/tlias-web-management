package com.itheima.filter;

import com.itheima.utils.CurrentHolder;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
//    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //获取请求路径
        String requestURI = request.getRequestURI();
        //判断是否是登录请求
        if (requestURI.contains("/login")) {
            log.info("登录请求，放行");
            //放行
            filterChain.doFilter(request, response);
            return;
        }
        //获取请求头中的token
        String token = request.getHeader("token");
        //判断token是否存在
        if (token == null || token.isEmpty()) {
            log.info("token不存在，拒绝访问");
            //设置响应状态码
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); //401
            //设置响应内容
            //response.getWriter().write("token不存在，拒绝访问");
            return;
        }
        //若token存在，校验token
        try {
            Claims claims=JwtUtils.parseToken(token);
            Integer empId=Integer.valueOf(claims.get("id").toString());
            CurrentHolder.setCurrentId(empId);
            log.info("当前登录员工ID：{}",empId);
        } catch (Exception e) {
            log.info("token非法，拒绝访问");
            //设置响应状态码
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); //401
            //设置响应内容
            //response.getWriter().write("token不存在，拒绝访问");
            return;
            //throw new RuntimeException(e);
        }
        //若校验通过，放行
        log.info("token合法，放行");
        filterChain.doFilter(request, response);
        //删除ThreadLocal中的数据
        CurrentHolder.remove();
    }

//    @Override
//    public void destroy() {
//        Filter.super.destroy();
//    }
}
