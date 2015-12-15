package ui.pages.admin;

import org.openqa.selenium.By;
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
    WebElement emailServerMenuItem;

    By emailServerMenuItemBy = By.xpath("//a[contains(text(), 'Email Servers')]");

    @FindBy(xpath = "//a[contains(text(), 'Conference Rooms')]")
    WebElement conferenceRoomsMenuItem;
    @FindBy(xpath = "//a[contains(text(), 'Resources')]")
    WebElement resourcesMenuItem;

    @FindBy(xpath = "//a[contains(text(), 'Locations')]")
    WebElement locationsMenuItem;

    /**
     * This method is the constructor
     */
    public LeftMenuPage() {
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailServerMenuItemBy));
    }

    public ResourcesPage goToResources() {
        resourcesMenuItem.click();
        CommonMethods.elementHighlight(resourcesMenuItem);
        return new ResourcesPage();
    }

    public LocationsPage goToLocations() {
        CommonMethods.elementHighlight(locationsMenuItem);
        locationsMenuItem.click();
        return new LocationsPage();
    }

    public ConferenceRoomsPage goToRooms() {
        conferenceRoomsMenuItem.click();
        CommonMethods.elementHighlight(conferenceRoomsMenuItem);
        return new ConferenceRoomsPage();
    }
}
