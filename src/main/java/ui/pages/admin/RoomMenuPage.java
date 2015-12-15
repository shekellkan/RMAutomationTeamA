package ui.pages.admin;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BasePageObject;

/**
 * Created by ArielWagner on 09/12/2015.
 */
public class RoomMenuPage extends BasePageObject {

    @FindBy(xpath = "//span[@ng-click = 'cancel()']")
    WebElement cancelButton;

    @FindBy(xpath = "//button[contains(@ng-click, 'save')]")
    WebElement saveButton;

    @FindBy(xpath = "//a[@class='current']")
    WebElement roomInfoTab;

    @FindBy(xpath = "//a[contains(text(), 'Resource Associations')]")
    WebElement resourceAssociationsTab;

    @FindBy(xpath = "//a[contains(text(), 'Out of Order Planning')]")
    WebElement outOfOrderPlanningTab;

    /**
     * This method is the constructor
     */
    public RoomMenuPage() {
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(cancelButton));
    }

    /**
     * This method allows press the save button
     * @return the ConferenceRooms page
     */
    public ConferenceRoomsPage clickSaveRoom(){
        saveButton.click();
        return new ConferenceRoomsPage();
    }

    /**
     * This method allows go to RoomInfo page
     * @return the RoomInfo page
     */
    public RoomInfoPage goToRoomInfo() {
        roomInfoTab.click();
        return new RoomInfoPage();
    }

    /**
     * This method allows go to ResourceAssociations page
     * @return the ResourceAssociations page
     */
    public RoomAssociationResourcePage goToResourceAssociations() {
        resourceAssociationsTab.click();
        return new RoomAssociationResourcePage();
    }

    /**
     * This method allows go to OutOfOrderPlanning page
     * @return the OutOfOrderPlanning page
     */
    public OutOfOrderPlanningPage goToOutOfOrderPlanning() {
        outOfOrderPlanningTab.click();
        return new OutOfOrderPlanningPage();
    }

    /**
     * This method allows press the cancel button
     * @return the ConferenceRooms page
     */
    public ConferenceRoomsPage clickCancelButton() {
        cancelButton.click();
        return new ConferenceRoomsPage();
    }
}
