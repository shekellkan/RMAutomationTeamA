package runner;

import Framework.DriverManager;
import api.APIResourcesMethods;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import entities.ResourceEntity;
import gherkin.formatter.model.Scenario;
import org.apache.log4j.Logger;
import org.testng.ISuite;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import steps.hooks.FeatureHooks;
import steps.hooks.GlobalHooks;
import ui.common.CommonMethods;

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


    @BeforeTest
    public void beforeTest(){
        logger.info("Test Start!!!!!");
    }

    @AfterTest
    public void afterExecution() {
        FeatureHooks.executeAfterHookMethod();


        if (CommonMethods.isUserLoginInAdminPage())
            CommonMethods.logoutFromAdminPage();
        try {
            DriverManager.getInstance().quit();
        } catch (Exception e) {
            logger.error("Unable to quit the driver", e);
        }
        logger.info("End test!!!!!");
    }
}
