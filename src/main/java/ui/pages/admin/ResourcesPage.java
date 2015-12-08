package ui.pages.admin;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * User: jeancarlorodriguez
 * Date: 12/7/15
 * Time: 8:45 PM
 */
public class ResourcesPage extends MainAdminPage {

    @FindBy(id = "resourcesGrid")
    WebElement resourcesGrid;

    public ResourcesPage()
    {
        waitUntilPageObjectIsLoaded();
    }
    @Override
    public void waitUntilPageObjectIsLoaded()
    {
        wait.until(ExpectedConditions.visibilityOf(resourcesGrid));
    }
}
