package com.zackma.webstarter.container;

public interface AppServer {

    int DEFAULT_PORT = 8080;
    String DOCBASE = "src/main";//项目代码路径
    String CTXPATH = "/";//应用容器访问路径
    String PRECOMP_PATH = "/WEB-INF/classes";//tomcat预编译类文件路径
    String CLZPATH = "target/classes";//项目编译后路径

    void run() throws Exception;

    void run(int port) throws Exception;
}
