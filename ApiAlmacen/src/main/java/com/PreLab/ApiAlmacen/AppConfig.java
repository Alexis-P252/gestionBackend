package com.PreLab.ApiAlmacen;

import com.PreLab.ApiAlmacen.utils.JWTUtil;
import com.PreLab.ApiAlmacen.utils.VerifyTokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Autowired
    private JWTUtil jwtUtil;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new VerifyTokenInterceptor(jwtUtil));
    }
}