package ui.pages.admin;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * User: jeancarlorodriguez
 * Date: 12/8/15
 * Time: 4:52 PM
 */
public class LocationsPage extends MainAdminPage {

    @FindBy(id = "locationGrid")
    WebElement locationsGrid;

    public LocationsPage()
    {
        waitUntilPageObjectIsLoaded();
    }

    public void waitUntilPageObjectIsLoaded()
    {
        wait.until(ExpectedConditions.visibilityOf(locationsGrid));
    }
}
