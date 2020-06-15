package servlets;


import accountServer.AccountServerI;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminPageServlet extends HttpServlet {
  //  static final Logger logger = LogManager.getLogger(AdminPageServlet.class.getName());
    public static final String PAGE_URL = "/admin";
    private final AccountServerI accountServer;

    public AdminPageServlet(AccountServerI accountServer){
        this.accountServer = accountServer;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html;charset=utf-8");

        int limit = accountServer.getUsersLimit();

       // logger.info("Limit " + limit);
        response.getWriter().println(limit);
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
