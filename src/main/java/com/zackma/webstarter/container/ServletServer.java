package com.zackma.webstarter.container;

import com.zackma.webstarter.testservlet.TempServlet;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;

public class ServletServer implements AppServer {

    /**
     * 发布Servlet应用到Tomcat并返回应用发布Tomcat后实例：
     * 分别初始化tomcat,
     * 应用容器context,
     * 添加servlet路径映射
     * @return
     */
    private static Tomcat deployAppOnTomcat(){
        Tomcat server = new Tomcat();
        server.setBaseDir(DOCBASE);
        server.getHost().setAutoDeploy(false);

        StandardContext context = new StandardContext();
        context.setPath(CTXPATH+"/");
        context.addLifecycleListener(new Tomcat.FixContextListener());

        server.getHost().addChild(context);
        server.addServlet(CTXPATH,"tempServlet",new TempServlet());
        context.addServletMappingDecoded("/temp","tempServlet");

        return server;
    }

    /**
     * 无参servlet应用启动加载
     * @throws Exception
     */
    @Override
    public void run() throws Exception {
        Tomcat tomcat = ServletServer.deployAppOnTomcat();
        tomcat.setPort(DEFAULT_PORT);

        tomcat.start();
        tomcat.getServer().await();
    }

    /**
     * servlet应用启动加载
     * @param port
     * @throws Exception
     */
    @Override
    public void run(int port) throws Exception {
        Tomcat tomcat = ServletServer.deployAppOnTomcat();
        tomcat.setPort(port);
        if(port<1||port>65535){
            tomcat.setPort(DEFAULT_PORT);
        }

        tomcat.start();
        tomcat.getServer().await();
    }

    public static void main(String... args){
        try{
            ServletServer server = new ServletServer();
            server.run();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
