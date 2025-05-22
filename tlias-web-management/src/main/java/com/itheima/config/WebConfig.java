//package com.itheima.config;
//
//import com.itheima.interceptor.TokenInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
////配置类
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
////    @Autowired
////    private DemoInterceptor demoInterceptor;
////    @Override
////    public void addInterceptors(InterceptorRegistry registry) {
////        registry.addInterceptor(demoInterceptor).addPathPatterns("/**").excludePathPatterns("/login");
////    }
//
//    @Autowired
//    private TokenInterceptor tokenInterceptor;
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**").excludePathPatterns("/login");
//    }
//}
