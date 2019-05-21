package com.djsenglish.interceptor;

import com.djsenglish.util.PropertiesUtil;
import com.google.common.collect.Sets;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Set;

/**
 * @author: shuo
 * @date: 2019/05/14
 */
@Configuration
public class Adapter implements WebMvcConfigurer {



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String uri = PropertiesUtil.getProperty("interceptor.uri");
        String URL[] = uri.split("-");
        System.out.println(Arrays.toString(URL));
        registry.addInterceptor(new TokenInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(URL);
    }
}
