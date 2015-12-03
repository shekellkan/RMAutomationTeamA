package ui;

import Framework.DriverManager;
import org.openqa.selenium.WebDriver;

/**
 * Created with IntelliJ IDEA.
 * User: jeancarlorodriguez
 * Date: 11/14/15
 * Time: 3:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class PageTransporter {
    private static WebDriver driver = DriverManager.getInstance().getWebDriver();
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
}
