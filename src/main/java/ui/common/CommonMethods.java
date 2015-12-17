package ui.common;
import Framework.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import ui.PageTransporter;
import ui.pages.admin.MainAdminPage;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Jean Carlo Rodriguez
 * Date: 12/04/15
 * Time: 2:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class CommonMethods {
    private static PageTransporter pageTransporter = PageTransporter.getInstance();

    /**
     * this methods execute a javascript to high light a web element
     * @param element
     */
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

    /**
     * this methods return true if the user is login in the admin page
     * @return
     */
    public static boolean isUserLoginInAdminPage()
    {
        if(pageTransporter.imInTheRMAdminPage()){
            return !pageTransporter.imInTheLoginAdminPage();
        }else
            return false;
    }


    public static boolean isUserLoginInTabletPage(){

        if(pageTransporter.imInTheRMATabletPage()){
            return !(pageTransporter.imInTheLoginTabletPage() || pageTransporter.imInTheLoginTabletPageStatus());
        }
        else{
            return false;
        }
    }

     public static String buildMessageElement(String nameMessage){
        return "//div[contains(text(),'"+nameMessage+"')]";
    }

    /**
     * this method refresh the page
     * usually used after make changes with the API
     */
    public static void refresh() {
        WebDriver driver = DriverManager.getInstance().getWebDriver();
        driver.navigate().refresh();
    }

    /**
     * if the user is logged in the Admin page then logout from Admin page
     */
    public static void logoutFromAdminPage() {
        if (pageTransporter.imInTheRMAdminPage() && !pageTransporter.imInTheLoginAdminPage()){
            MainAdminPage mainAdminPage = new MainAdminPage();
            mainAdminPage.getTopHeaderPage().logout();
        }
    }
}
