package com.zackma.webstarter.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration//把该类标注为配置类，等同配置文件
@ComponentScan("com.zackma.webstarter")//标注包扫描路径
public class RootConfig {
    //to do sth...
}
