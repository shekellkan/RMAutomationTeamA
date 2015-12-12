package ui;

import Framework.DriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.NoSuchElementException;

/**
 * Created with IntelliJ IDEA.
 * User: Jean Carlo Rodriguez
 * Date: 12/03/15
 * Time: 3:42 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class BasePageObject {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected DriverManager driverManager;
    final static Logger logger = Logger.getLogger(BasePageObject.class);
    /**
     * Initializes the web driver, wait and web elements
     */
    public BasePageObject() {
        driverManager = DriverManager.getInstance();
        this.driver = DriverManager.getInstance().getWebDriver();
        this.wait = DriverManager.getInstance().getWait();
        PageFactory.initElements(driver, this);
    }

    public abstract void waitUntilPageObjectIsLoaded();

    /**
     * the isPresent method returns true if the element By is present
     * otherwise return false
     */
    public boolean isPresent(By by)
    {
        try {
            driver.findElement(by);
            return true;
        }catch (NoSuchElementException e)
        {
            return false;
        }
    }

    /**
     * return true if the element is deleted after a number of tries
     * @param numberOfTries
     * @param by
     * @return
     */
    public boolean isDeleted(int numberOfTries, By by)
    {

        boolean isDeleted = false;
        int count = 0;
        while(count!=numberOfTries)
        {
            count++;
            try {
                Thread.sleep(200);
            }catch (Exception e){
                logger.info("Sleep fails: ",e);
                System.out.println("Sleep fails: "+e);
            }

            if(!isPresent(by)){
                isDeleted =  true;
                count= numberOfTries;
            }
        }
        return isDeleted;
    }

    /**
     * return true if the element is displayed after 2 seconds of searching it
     * @param by
     * @return
     */
    public boolean isDisplayed(By by)
    {
        boolean isDisplayed;
        try {
            driverManager.setImplicitWait(3);
            driver.findElement(by);
            isDisplayed = true;
        }catch (Exception e)
        {
            isDisplayed = false;
        }finally{
            driverManager.setImplicitWait(driverManager.getImplicitWait());
        }
        return isDisplayed;
    }
}
