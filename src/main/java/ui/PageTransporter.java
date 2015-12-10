package ui;

import Framework.DriverManager;
import Framework.ExternalVariablesManager;
import org.openqa.selenium.WebDriver;
import ui.pages.admin.LoginAdminPage;
import ui.pages.tablet.LoginTabletPage;

/**
 * Created with IntelliJ IDEA.
 * User: Jean Carlo Rodriguez
 * Date: 12/03/15
 * Time: 3:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class PageTransporter {
    private static WebDriver driver = DriverManager.getInstance().getWebDriver();
    private static ExternalVariablesManager externalVariablesManager = ExternalVariablesManager.getInstance();
    private static PageTransporter instance;

    private PageTransporter()
    {
    }

    public static PageTransporter getInstance()
    {
        if(instance==null)
        {
            instance = new PageTransporter();
        }
        return instance;
    }

    private static void goToURL(String url) {
        driver.navigate().to(url);
    }

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    public static LoginTabletPage goToLoginTabletPage(){
        goToURL(externalVariablesManager.getTabletURL());
        return new LoginTabletPage();
    }

    public static LoginAdminPage goToLoginAdminPage(){
        goToURL(externalVariablesManager.getAdminURL());
        return new LoginAdminPage();
    }
}
