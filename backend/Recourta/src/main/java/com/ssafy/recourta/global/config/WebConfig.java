package com.ssafy.recourta.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final String connectPath = "/uploads/img/**";
    private final String resourcePath = "file:///C:/Users/SSAFY/Desktop/uploads/";

    private final String modelRequestPath = "/uploads/model/**";
    private final String modelResourcePath = "file:///C:/Users/SSAFY/Desktop/uploads/model/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(connectPath)
                .addResourceLocations(resourcePath);

        registry.addResourceHandler(modelRequestPath)
                .addResourceLocations(modelResourcePath);
    }
}