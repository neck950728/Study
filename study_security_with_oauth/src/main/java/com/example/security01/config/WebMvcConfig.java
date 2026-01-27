package com.example.security01.config;

import org.springframework.boot.mustache.servlet.view.MustacheViewResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// ↓ application.yaml 설정으로 대체
/*
    @Configuration
    public class WebMvcConfig implements WebMvcConfigurer {
        @Override
        public void configureViewResolvers(ViewResolverRegistry registry) {
            MustacheViewResolver resolver = new MustacheViewResolver();
            resolver.setCharset("UTF-8");
            resolver.setContentType("text/html; charset=utf-8");
            resolver.setPrefix("classpath:/templates/");
            resolver.setSuffix(".html");

            registry.viewResolver(resolver);
        }
    }
*/