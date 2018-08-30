package com.zackma.webstarter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration//把该类标注为配置类，等同配置文件
@EnableWebMvc//开启SpringMVC
@ComponentScan(basePackages = {"com.zackma.webstarter.controller"})//标注包扫描路径
public class WebConfig {

    /**
     * 配置试图解析器
     * @return
     */
    @Bean
    public ViewResolver viewResolver(){
        //jsp视图解析器
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/webapp/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }

}
