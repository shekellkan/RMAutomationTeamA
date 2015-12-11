package Framework;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Jean Carlo Rodriguez
 * Date: 12/03/15
 * Time: 12:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class DriverManager {
    private static DriverManager instance;
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static ExternalVariablesManager externalVariablesManager = ExternalVariablesManager.getInstance();
    final static Logger logger = Logger.getLogger(DriverManager.class);
    private static JsonReader jsonReader;
    private static int implicitWait;
    private static int explicitWait;

    protected DriverManager(){
    }

    public static DriverManager getInstance()
    {
        if(instance == null)
        {
            instance = new DriverManager();
            jsonReader = new JsonReader("config.json");
            if(driver == null)
            {
                String browserName = externalVariablesManager.getBrowserName();
                implicitWait = Integer.parseInt(jsonReader.getKeyFromSingleJson("implicitWait"));
                explicitWait = Integer.parseInt(jsonReader.getKeyFromSingleJson("explicitWait"));
                if(browserName.equalsIgnoreCase("Firefox"))
                {
                    driver = new FirefoxDriver();

                }
                else if(browserName.equalsIgnoreCase("Chrome"))
                {
                    System.setProperty("webdriver.chrome.driver", jsonReader.getKeyFromSingleJson("chrome driver path"));
                    driver = new ChromeDriver();
                }
                logger.info("initializing the web driver: "+ browserName);

                driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
                driver.manage().window().maximize();
                driver.get(externalVariablesManager.getAdminURL());
            }
            if(wait == null)
            {
                wait = new WebDriverWait(driver,explicitWait);
            }

        }
        return instance;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public WebDriver getWebDriver() {
        return driver;
    }

    public void quit()
    {
        driver.quit();
        driver = null;
    }
    public int getImplicitWait(){return implicitWait;}
    public void setImplicitWait(int implicitSecondTime) {driver.manage().timeouts().implicitlyWait(implicitSecondTime,TimeUnit.SECONDS);}
}
