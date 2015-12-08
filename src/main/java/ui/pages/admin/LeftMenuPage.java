package ui.pages.admin;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BasePageObject;

/**
 * Created by ArielWagner on 07/12/2015.
 */
public class LeftMenuPage extends BasePageObject {

    @FindBy(xpath = "//a[contains(text(), 'Email Servers')]")
    WebElement emailServerTab;

    @FindBy(xpath = "//a[contains(text(), 'Conference Rooms')]")
    WebElement conferenceRoomsTab;

    @FindBy(xpath = "//a[contains(text(), 'Resources')]")
    WebElement resourcesButton;

    @FindBy(xpath = "//a[contains(text(), 'Locations')]")
    WebElement locationsButton;

    /**
     * This method is the constructor
     */
    public LeftMenuPage() {
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(emailServerTab));
    }

    /**
     * This method allows press the conference rooms tab
     * @return the ConferenceRooms page
     */
    public ConferenceRoomsPage clickConferenceRoomsTab() {
        conferenceRoomsTab.click();
        return new ConferenceRoomsPage();
    }

    public ResourcesPage goToResources() {
        resourcesButton.click();
        return new ResourcesPage();
    }

    public LocationsPage goToLocations() {
        locationsButton.click();
        return new LocationsPage();
    }
}
