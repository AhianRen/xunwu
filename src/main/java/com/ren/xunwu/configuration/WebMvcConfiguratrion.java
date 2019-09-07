package com.ren.xunwu.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//在SpringBoot的2.x新版本中WebMvcConfigurerAdapter配置类已经不推荐使用了，可以使用WebMvcConfigurer 或者WebMvcConfigurationSupport来配置自己的配置信息
@Configuration
public class WebMvcConfiguratrion implements WebMvcConfigurer {

    //解决页面不能加载静态资源的问题
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
