package com.gd;


import com.gd.filters.CORSFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;


@SpringBootApplication
@MapperScan("com.gd.dao")
@ComponentScan
@EnableAutoConfiguration
public class MaApplication {
//    @Bean
//    public FilterRegistrationBean someFilterRegistration() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(sessionFilter());
//        registration.addUrlPatterns("/*");
//
//        registration.setName("corsFilter");
//        return registration;
//    }

//    /**
//     * 创建一个bean
//     * @return
//     */
//    @Bean(name = "corsFilter")
//    public Filter sessionFilter() {
//        return new CORSFilter();
//    }
//@Bean
//MultipartConfigElement multipartConfigElement() {
//    MultipartConfigFactory factory = new MultipartConfigFactory();
//    factory.setLocation("D:/code/ma_test");
//    return factory.createMultipartConfig();
//}
    public static void main(String[] args) {
        SpringApplication.run(MaApplication.class, args);
    }
}
