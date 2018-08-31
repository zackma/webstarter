package com.zackma.webstarter.container;

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
public class WebServer implements AppServer{

    /**
     * 发布应用到Tomcat并返回应用发布Tomcat后实例：
     * 分别初始化tomcat,
     * 应用容器context
     * 和类文件预编译路径webRoot
     * @return
     * @throws ServletException
     */
    private static Tomcat deployAppOnTomcat() throws ServletException{

        Tomcat tomcat = new Tomcat();
        //关闭自动部署
        tomcat.getHost().setAutoDeploy(false);
        //创建应用容器(传入应用容器访问路径和实际项目代码路径,从main下面开始读取保证能读到静态资源文件),即加载ServletContext部分
        StandardContext ctx = (StandardContext) tomcat.addWebapp(CTXPATH,new File(DOCBASE).getAbsolutePath());
        //禁止重新加载
        ctx.setReloadable(false);
        //添加容器监听
        ctx.addLifecycleListener(new Tomcat.FixContextListener());

        //创建springmvc应用classes文件读取地址，加载WebApplicationContext部分
        File classes = new File(CLZPATH);
        //创建WebRoot
        WebResourceRoot webRoot = new StandardRoot(ctx);
        //添加WebRoot类文件
        webRoot.addPreResources(new DirResourceSet(webRoot,PRECOMP_PATH,classes.getAbsolutePath(),CTXPATH));

        return tomcat;
    }


    /**
     * 无参启动方法
     * @throws Exception
     */
    @Override
    public void run() throws Exception{
        Tomcat tomcat = WebServer.deployAppOnTomcat();
        //设置端口
        tomcat.setPort(DEFAULT_PORT);

        tomcat.start();
        tomcat.getServer().await();
    }

    /**
     * 带参启动方法，暂定端口号自定义
     * @param port
     * @throws Exception
     */
    @Override
    public void run(int port) throws Exception{
        Tomcat tomcat = WebServer.deployAppOnTomcat();
        if(port<1||port>65535){
            tomcat.setPort(DEFAULT_PORT);
        }
        tomcat.setPort(port);

        tomcat.start();
        tomcat.getServer().await();
    }

    public static void main(String... args){
        try {
            WebServer webServer = new WebServer();
            webServer.run(9080);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
