package ui.pages.admin;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BasePageObject;

/**
 * Created by ArielWagner on 02/12/2015.
 */
public class MainAdminPage extends BasePageObject {

    @FindBy(xpath = "//a[contains(text(), 'Room Manager')]")
    WebElement applicationNameLabel;

    public MainAdminPage(){
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(applicationNameLabel));
    }
}
