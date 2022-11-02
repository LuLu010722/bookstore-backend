package com.bookstore.backend.config;

import com.bookstore.backend.util.AdminInterceptor;
import com.bookstore.backend.util.CommonInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Resource
    private CommonInterceptor commonInterceptor;

    @Resource
    private AdminInterceptor adminInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(commonInterceptor)
                .addPathPatterns("/**");
        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/book/add")
                .addPathPatterns("/book/delete")
                .addPathPatterns("/order/all")
                .addPathPatterns("/user/all")
                .addPathPatterns("/user/delete");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedHeaders("*")
                .allowedMethods("GET", "POST")
                .allowCredentials(true);
    }
}
