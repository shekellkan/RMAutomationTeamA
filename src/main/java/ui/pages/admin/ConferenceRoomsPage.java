package ui.pages.admin;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by ArielWagner on 07/12/2015.
 */
public class ConferenceRoomsPage extends MainAdminPage {

    @FindBy(xpath = "//span[contains(text(), 'Filter By Room')]")
    WebElement filterByRoomLabel;

    /**
     * This method is the constructor
     */
    public ConferenceRoomsPage() {
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(filterByRoomLabel));
    }
}
