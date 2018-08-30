package com.zackma.webstarter.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * SpringMVC应用注解启动核心类
 * SpringBoot框架的三个核心：
 * 1、maven父子关系引入可复用父类框架
 * 2、利用Spring 4之后注解代替配置文件特性实现零xml配置
 * 3、利用Embed Tomcat实现内置tomcat启动应用
 */
public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * 加载Spring根配置（获取Spring根上下文——引自知乎）
     * @return
     */
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    /**
     * 加载SpringMVC配置（获取SpringMVC上下文——引自知乎）
     * @return
     */
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    /**
     * 加载Servlet初始化拦截路径
     * @return
     */
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
