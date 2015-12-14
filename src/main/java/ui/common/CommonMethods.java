package ui.common;
import Framework.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import ui.PageTransporter;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Jean Carlo Rodriguez
 * Date: 12/04/15
 * Time: 2:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class CommonMethods {
    public static void elementHighlight(WebElement element) {
        WebDriver driver = DriverManager.getInstance().getWebDriver();
        for (int i = 0; i < 2; i++) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(
                    "arguments[0].setAttribute('style', arguments[1]);",
                    element, "color: red; border: 3px solid red;");
            js.executeScript(
                    "arguments[0].setAttribute('style', arguments[1]);",
                    element, "");
        }
    }
    public static void doubleClick(WebElement webElement) {
        Actions action = new Actions(DriverManager.getInstance().getWebDriver());
        action.doubleClick(webElement);
        action.perform();
    }

    public static boolean isUserLoginInAdminPage()
    {
        if(PageTransporter.getInstance().imInTheLoginAdminPage()){
            return false;
        }else{
            return true;
        }
    }

    /**
     * This method allows verify if an element is displayed
     * @param locator
     * @return false or true
     */
    public static boolean isElementDisplayed(By locator) {
        try {
            DriverManager.getInstance().getWebDriver().manage().timeouts()
                    .implicitlyWait(10, TimeUnit.SECONDS);
            DriverManager.getInstance().getWebDriver().findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        } finally {
            DriverManager.getInstance().getWebDriver().manage().timeouts()
                    .implicitlyWait(10, TimeUnit.SECONDS);
        }
    }
}
