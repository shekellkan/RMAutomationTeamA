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

    @FindBy(xpath = "//button[@ng-click='addResourceDialog()']")
    WebElement addButton;

    public ResourcesPage()
    {
        waitUntilPageObjectIsLoaded();
    }
    @Override
    public void waitUntilPageObjectIsLoaded()
    {
        wait.until(ExpectedConditions.visibilityOf(resourcesGrid));
    }

    public ResourcesPage createResource(String name, String displayName, String description, String icon)
    {

        return this;
    }

    public ResourceFormPage goToAddNewResource() {
        addButton.click();
        return new ResourceFormPage();
    }
}
