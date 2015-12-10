package ui.pages.admin;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BasePageObject;
import ui.common.CommonMethods;

/**
 * Created by ArielWagner on 07/12/2015.
 */
public class LeftMenuPage extends BasePageObject {

    @FindBy(xpath = "//a[contains(text(), 'Email Servers')]")
    WebElement emailServerButton;

    @FindBy(xpath = "//a[contains(text(), 'Conference Rooms')]")
    WebElement conferenceRoomsButton;

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
        wait.until(ExpectedConditions.visibilityOf(emailServerButton));
    }

    /**
     * This method allows press the conference rooms tab
     * @return the ConferenceRooms page
     */
    public ConferenceRoomsPage clickConferenceRoomsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(conferenceRoomsButton));
        conferenceRoomsButton.click();
        return new ConferenceRoomsPage();
    }

    public ResourcesPage goToResources() {
        resourcesButton.click();
        CommonMethods.elementHighlight(resourcesButton);
        return new ResourcesPage();
    }

    public LocationsPage goToLocations() {
        CommonMethods.elementHighlight(locationsButton);
        locationsButton.click();
        return new LocationsPage();
    }

    public ConferenceRoomsPage goToRooms() {
        conferenceRoomsButton.click();
        CommonMethods.elementHighlight(conferenceRoomsButton);
        return new ConferenceRoomsPage();
    }
}
