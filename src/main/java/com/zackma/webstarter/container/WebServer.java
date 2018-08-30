package com.zackma.webstarter.container;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import javax.servlet.ServletException;
import java.io.File;

/**
 * Embed Tomcat加载和启动应用
 * （SpringBoot内置Tomcat启动应用原理）
 */
public class WebServer {
    private static final int DEFAULT_PORT = 8080;
    private static final String DOCBASE = "src/main";//项目代码路径
    private static final String CTXPATH = "/";//应用容器访问路径
    private static final String PRECOMP_PATH = "/WEB-INF/classes";//tomcat预编译类文件路径
    private static final String CLZPATH = "target/classes";//项目编译后路径

    /**
     * 封装启动方法，
     * 分别初始化tomcat,
     * 应用容器context
     * 和类文件预编译路径webRoot
     * @param port
     * @throws ServletException
     * @throws LifecycleException
     */
    public static void run(int port) throws ServletException, LifecycleException {
        Tomcat tomcat = new Tomcat();
        //设置端口
        if(port<1||port>65535){
            tomcat.setPort(DEFAULT_PORT);
        }
        tomcat.setPort(port);
        //关闭自动部署
        tomcat.getHost().setAutoDeploy(false);
        //创建应用容器(传入应用容器访问路径和实际项目代码路径,从main下面开始读取保证能读到静态资源文件)
        StandardContext ctx = (StandardContext) tomcat.addWebapp(CTXPATH,new File(DOCBASE).getAbsolutePath());
        //禁止重新加载
        ctx.setReloadable(false);
        //添加容器监听
        ctx.addLifecycleListener(new Tomcat.FixContextListener());

        //创建springmvc应用classes文件读取地址
        File classes = new File(CLZPATH);
        //创建WebRoot
        WebResourceRoot webRoot = new StandardRoot(ctx);
        //添加WebRoot类文件
        webRoot.addPreResources(new DirResourceSet(webRoot,PRECOMP_PATH,classes.getAbsolutePath(),CTXPATH));

        tomcat.start();
        tomcat.getServer().await();
    }

    public static void main(String... args){
        try {
            WebServer.run(9080);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }

    /**
     * 以下方法用于加载和配置servlet应用
     */
//    private static final int port = 9080;
//    private static final String docBase = "e:/codes/webstart";
//    public static void main(String... args) throws LifecycleException {
//        Tomcat server = new Tomcat();
//        server.setPort(port);
//        server.setBaseDir(docBase);
//        server.getHost().setAutoDeploy(false);
//
//        String ctxPath = "/";
//        StandardContext context = new StandardContext();
//        context.setPath(ctxPath);
//        context.addLifecycleListener(new Tomcat.FixContextListener());
//
//        server.getHost().addChild(context);
//        server.addServlet(ctxPath,"tempServlet",new TempServlet());
//        context.addServletMappingDecoded("/temp","tempServlet");
//
//        server.start();
//        server.getServer().await();
//    }
}
