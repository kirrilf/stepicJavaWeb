package servlets;

import accountServer.AccountServerI;
import resources.ResourceService;
import resources.TestResource;
import resources.TestResourceController;
import sax.ReadXMLFileSAX;

import javax.management.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.management.ManagementFactory;


public class ResourcesServlet extends HttpServlet {

    //  static final Logger logger = LogManager.getLogger(AdminPageServlet.class.getName());
    public static final String PAGE_URL = "/resources";
    private final TestResource testResource;

    public ResourcesServlet(TestResource testResource) {
        this.testResource = testResource;
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        response.setContentType("text/html;charset=utf-8");

        String pathToResource = request.getParameter("path");
        //System.out.println(pathToResource);
        TestResource testResource = (TestResource) ReadXMLFileSAX.readXML(pathToResource);

        ResourceService resourceService = ResourceService.getInstance();
        resourceService.setXMLParametrs(testResource);
        response.setStatus(HttpServletResponse.SC_OK);


    }
}