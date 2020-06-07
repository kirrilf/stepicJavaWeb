package main;

import accounts.AccountService;
import dataSets.UsersDataSet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.*;
import servlets.authorizedServlets.SessionsServlet;
import servlets.authorizedServlets.SignInServlet;
import servlets.authorizedServlets.SignUpServlet;
import servlets.db.DBService;
import servlets.db.DBException;


public class Main {
    public static void main(String[] args) throws Exception {
        //AllRequestsServlet allRequestsServlet = new AllRequestsServlet();
        //ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        //context.addServlet(new ServletHolder(allRequestsServlet), "/*");

       AccountService accountService = new AccountService();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new UsersServlet(accountService)), "/api/v1/users");
        context.addServlet(new ServletHolder(new SessionsServlet(accountService)), "/api/v1/sessions");
        context.addServlet(new ServletHolder(new SignUpServlet(accountService)), "/signup");
        context.addServlet(new ServletHolder(new SignInServlet(accountService)), "/signin");

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("public_html");
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});



        Server server = new Server(8080);
        //server.setHandler(context);
        server.setHandler(handlers);


        server.start();
        System.out.println("Server started");
        server.join();




    }
}

