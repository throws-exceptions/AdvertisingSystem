package com.ad.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * created by Mr.F on 2019/4/30
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.clear();
        converters.add(new MappingJackson2HttpMessageConverter());
        //实现Java对象向json字符串的转换
    }
}
