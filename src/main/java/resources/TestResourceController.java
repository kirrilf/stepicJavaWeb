package resources;

public class TestResourceController implements TestResourceControllerMBean{
    private final TestResource testResource;

    public TestResourceController(TestResource testResource){
        this.testResource = testResource;
    }

    @Override
    public String getName(){
        return testResource.getName();
    }

    @Override
    public int getAge(){
        return testResource.getAge();
    }

    @Override
    public void setName(String name){
        testResource.setName(name);
    }
    @Override
    public void setAge(int age){
        testResource.setAge(age);
    }

}
