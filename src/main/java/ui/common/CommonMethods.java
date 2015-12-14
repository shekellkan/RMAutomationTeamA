package ui.common;
import Framework.DriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import ui.PageTransporter;

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


    public static boolean isUserLoginInTabletPage(){
        if(PageTransporter.getInstance().imInTheLoginTabletPage()){
            return false;
        }else{
            return true;
        }
    }

    public static boolean isUserLoginStatusURL(){
        if(PageTransporter.getInstance().imInTheLoginTabletPageStatus()){
            return false;
        }else{
            return true;
        }
    }

    public static boolean isURLPresent(){
        if (PageTransporter.getInstance().getCurrentURL().isEmpty()){
            return true;
        }else {
            return false;
        }
    }

    public static String buildMessageElement(String nameMessage){
        return "//div[contains(text(),'"+nameMessage+"')]";
    }

    public static void refresh() {
        WebDriver driver = DriverManager.getInstance().getWebDriver();
        driver.navigate().refresh();
    }
}
