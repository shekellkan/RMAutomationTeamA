package ui.pages.admin;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by ArielWagner on 09/12/2015.
 */
public class ResourceAssociationsPage extends RoomPage {

    @FindBy(xpath = "//a[contains(text(), 'Resource Associations')]")
    WebElement resourceAssociationsTab;

    /**
     * This method is the constructor
     */
    public ResourceAssociationsPage() {
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(resourceAssociationsTab));
    }

}
