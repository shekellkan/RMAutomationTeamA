package ui;

import Framework.DriverManager;
import Framework.ExternalVariablesManager;
import org.openqa.selenium.WebDriver;
import ui.pages.admin.LoginAdminPage;
import ui.pages.tablet.LoginTabletPage;
import ui.pages.admin.MainAdminPage;
/**
 * Created with IntelliJ IDEA.
 * User: Jean Carlo Rodriguez
 * Date: 12/03/15
 * Time: 3:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class PageTransporter {
    private static WebDriver driver;
    private static PageTransporter instance;
    private static ExternalVariablesManager externalVariablesManager;
    private PageTransporter()
    {
        driver = DriverManager.getInstance().getWebDriver();
        externalVariablesManager = ExternalVariablesManager.getInstance();
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
        goToURL(externalVariablesManager.getLoginTabletURL());
        return new LoginTabletPage();
    }

    public LoginAdminPage goToLoginAdminPage() {
        goToURL(externalVariablesManager.getAdminURL());
        return new LoginAdminPage();
    }

    public MainAdminPage goToAdminMainPage() {
        goToURL(externalVariablesManager.getMainAdminURL());
        return new MainAdminPage();
    }

    public boolean imInTheLoginAdminPage() {
        if(getCurrentURL().contains("admin/#/login"))
            return true;
        else
            return false;
    }
    public boolean imInTheRMAdminPage()
    {
        if(getCurrentURL().contains("admin"))
            return true;
        else
            return false;
    }
}
