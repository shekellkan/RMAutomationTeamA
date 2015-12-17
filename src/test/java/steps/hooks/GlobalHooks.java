package steps.hooks;

import Framework.DriverManager;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import gherkin.formatter.model.Feature;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * Created with IntelliJ IDEA.
 * User: Jean Carlo Rodriguez
 * Date: 12/03/15
 * Time: 10:45: AM
 * To change this template use File | Settings | File Templates.
 */
public class GlobalHooks {
    private WebDriver webDriver;
    public GlobalHooks() {
        webDriver = DriverManager.getInstance().getWebDriver();
    }

    @After(order = 999)
    public void ScreenShot(Scenario scenario) {
        if (scenario.isFailed()) {
            // Take a screenShot...
            final byte[] screenShot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenShot, "image/png"); // ... and embed it in the report.
        }
    }
}
