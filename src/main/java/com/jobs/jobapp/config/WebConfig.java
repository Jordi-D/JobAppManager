package com.jobs.jobapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${jobs.route.images}")
    private String routeImg;

    @Value("${jobs.route.cv}")
    private String routeCv;

    /**
     * Configures resource handlers for images and CV files.
     * Images are served from the configured file path.
     * CV files are also served from the same file path.
     *
     * @param registry ResourceHandlerRegistry instance to register resource handlers
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/logos/**").addResourceLocations("file:" + routeImg);
        registry.addResourceHandler("/cv/**").addResourceLocations("file:" + routeCv);
    }
}
