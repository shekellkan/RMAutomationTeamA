package ui.pages.admin;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BasePageObject;

/**
 * Created by ArielWagner on 02/12/2015.
 */
public class MainAdminPage extends BasePageObject {

    protected TopHeaderPage topHeaderPage;

    protected LeftMenuPage leftMenuPage;

    @FindBy(xpath = "//a[contains(text(), 'Room Manager')]")
    WebElement applicationNameLabel;

    /**
     * This method is the constructor
     */
    public MainAdminPage(){
        topHeaderPage = new TopHeaderPage();
        leftMenuPage = new LeftMenuPage();
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(applicationNameLabel));
    }

    /**
     * This method allows get the TopHeader page
     * @return the TopHeader page
     */
    public TopHeaderPage getTopHeaderPage() {
        return topHeaderPage;
    }

    /**
     * This method allows get the LeftMenu page
     * @return the LeftMenu page
     */
    public LeftMenuPage getLeftMenuPage() {
        return leftMenuPage;
    }
}
