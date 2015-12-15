package runner;

import Framework.DriverManager;
import api.APIResourcesMethods;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import entities.ResourceEntity;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber", "json:target/cucumber.json"},
        glue={"steps"},
        features = {"src/test/resources/features"},
        monochrome = true)
public class RunCukesTest  extends AbstractTestNGCucumberTests {
    final static Logger logger = Logger.getLogger(RunCukesTest.class);
    ArrayList<ResourceEntity> listOfResources = new ArrayList<ResourceEntity>();

    @BeforeTest
    public void beforeTest(){
        logger.info("Test Start!!!!!");
        System.out.println("Before all the execution");

        APIResourcesMethods apiResourcesMethods = new APIResourcesMethods();
        ResourceEntity resource1 = new ResourceEntity();
        resource1.setAllFields("Mac_Pro","Mac pro computer","My mac pro computer","fa-desktop");
        ResourceEntity resource2 = new ResourceEntity();
        resource2.setAllFields("Telephone_Pro","Inter Communicator","My inter communicator telephone","fa-group");
        ResourceEntity resource3 = new ResourceEntity();
        resource3.setAllFields("04ac5_Pro","Mac computer","My mac computer","fa-desktop");

        listOfResources.add(resource1);
        listOfResources.add(resource2);
        listOfResources.add(resource3);

        for(ResourceEntity item : listOfResources){
            apiResourcesMethods.createResource(item);
        }

    }

    @AfterTest
    public void afterExecution() {
        APIResourcesMethods apiResourcesMethods = new APIResourcesMethods();
        for(ResourceEntity item : listOfResources){
            apiResourcesMethods.removeResource(item);
        }

        try {
            DriverManager.getInstance().quit();
        } catch (Exception e) {
            logger.error("Unable to quit the driver", e);
        }
        logger.info("End test!!!!!");

    }
}
