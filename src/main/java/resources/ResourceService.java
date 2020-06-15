package resources;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class ResourceService {
    private final TestResourceController testResourceController;
    private static ResourceService instance;
    private ResourceService(TestResourceController testResourceController){
        this.testResourceController = testResourceController;
        try {
            MBeanServer mBeanServer  = ManagementFactory.getPlatformMBeanServer();
            ObjectName name = new ObjectName("Admin:type=ResourceServerController");
            mBeanServer.registerMBean(this.testResourceController, name);
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }
    public static ResourceService getInstance(){
        if(instance == null){
            instance = new ResourceService(new TestResourceController(new TestResource()));
        }
        return instance;
    }

    public void setXMLParametrs(TestResource testResource){
        testResourceController.setAge(testResource.getAge());
        testResourceController.setName(testResource.getName());
    }


}
