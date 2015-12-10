package ui;

import Framework.DriverManager;
import Framework.ExternalVariablesManager;
import Framework.JsonReader;
import org.openqa.selenium.WebDriver;
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

    public MainAdminPage goToAdminMainPage() {
        driver.get(externalVariablesManager.getMainAdminURL());
        return new MainAdminPage();
    }
}
