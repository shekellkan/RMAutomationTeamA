package ui.pages.admin;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BasePageObject;

/**
 * Created by ArielWagner on 07/12/2015.
 */
public class TopHeaderPage extends BasePageObject {

    @FindBy(xpath = "//ul[@class='nav navbar-nav navbar-right']//span[text()='sign out']")
    WebElement signOutButton;

    /**
     * This method is the constructor
     */
    public TopHeaderPage() {
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(signOutButton));
    }
}
