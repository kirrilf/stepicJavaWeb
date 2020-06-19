package main;

import accountServer.AccountServer;
import accountServer.AccountServerController;
import accountServer.AccountServerControllerMBean;
import accountServer.AccountServerI;
import accounts.AccountService;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import resources.ResourceService;
import sax.ReadXMLFileSAX;
import servlets.*;
import servlets.authorizedServlets.SessionsServlet;
import servlets.authorizedServlets.SignInServlet;
import servlets.authorizedServlets.SignUpServlet;
import servlets.sockets.WebSocketChatServlet;
import resources.TestResourceController;
import resources.TestResource;
import resources.TestResourceControllerMBean;
import threads.ThreadPooledServer;
import threads.WorkerRunnable;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {
    public static void main(String[] args) throws Exception {
        //AllRequestsServlet allRequestsServlet = new AllRequestsServlet();
        //ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        //context.addServlet(new ServletHolder(allRequestsServlet), "/*");

       /*AccountService accountService = new AccountService();

       Server server = new Server(8080);
       ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(new UsersServlet(accountService)), "/api/v1/users");
        context.addServlet(new ServletHolder(new SessionsServlet(accountService)), "/api/v1/sessions");
        context.addServlet(new ServletHolder(new SignUpServlet(accountService)), "/signup");
        context.addServlet(new ServletHolder(new SignInServlet(accountService)), "/signin");
       // context.addServlet(new ServletHolder(new WebSocketChatServlet()), "/chat");
        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("public_html");
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});






        //server.setHandler(context);
        server.setHandler(handlers);
*/

/*
        AccountServerI accountServer = new AccountServer(10);

        AccountServerControllerMBean serverStatistics = new AccountServerController(accountServer);
        TestResource testResource = new TestResource();
        TestResourceControllerMBean testResourceControllerMBean = new TestResourceController(testResource);
        MBeanServer mBeanServer  = ManagementFactory.getPlatformMBeanServer();
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        //ObjectName name = new ObjectName("ServerManager:type=AccountServerController");
        ObjectName name = new ObjectName("Admin:type=AccountServerController.usersLimit");
        ObjectName objectName = new ObjectName("Admin:type=ResourceServerController");
        mbs.registerMBean(serverStatistics, name);
       // mBeanServer.registerMBean(testResourceControllerMBean, objectName);

        ResourceService resourceService = ResourceService.getInstance();

        Server server = new Server(8080);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new AdminPageServlet(accountServer)), AdminPageServlet.PAGE_URL);
        context.addServlet(new ServletHolder(new ResourcesServlet(testResource)), ResourcesServlet.PAGE_URL);

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setResourceBase("static");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});

        server.setHandler(handlers);




        server.start();
        System.out.println("Server started");
        server.join();*/



        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        ServerSocket serverSocketSocket = new ServerSocket(5050);
        System.out.println("Server started");
        while (true){
            Socket clientSocket = serverSocketSocket.accept();
            threadPool.execute(new WorkerRunnable(clientSocket));
        }




    }
}

