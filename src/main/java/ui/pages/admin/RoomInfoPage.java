package ui.pages.admin;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by ArielWagner on 09/12/2015.
 */
public class RoomInfoPage extends RoomPage {

    @FindBy(xpath = "//button[contains(@ng-click, 'enableDisableRoom')]")
    WebElement enableDisableRoomButton;

    @FindBy(xpath = "//input[contains(@ng-model, 'DisplayName')]")
    WebElement displayNameInput;

    @FindBy(xpath = "//input[contains(@ng-model, 'code')]")
    WebElement codeInput;

    @FindBy(xpath = "//input[contains(@ng-model, 'capacity')]")
    WebElement capacityInput;

    @FindBy(xpath = "//button[contains(@ng-click, 'save')]")
    WebElement saveButton;

    /**
     * This method is the constructor
     */
    public RoomInfoPage() {
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(enableDisableRoomButton));
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
     * This method allows edit the display name
     * @param name
     */
    public void editDisplayName(String name)
    {
        displayNameInput.clear();
        displayNameInput.sendKeys(name);
    }

    /**
     * This method allows edit the code
     * @param code
     */
    public void editCode(String code){
        codeInput.clear();
        codeInput.sendKeys(code);
    }

    /**
     * This method allows edit the capacity
     * @param capacity
     */
    public void editCapacity(String capacity){
        capacityInput.clear();
        capacityInput.sendKeys(capacity);
    }

    /**
     * This method allows edit the room data
     * @param name
     * @param code
     * @param capacity
     */
    public void editRoom(String name, String code, String capacity) {
        editDisplayName(name);
        editCode(code);
        editCapacity(capacity);
    }

    /**
     * This method allows clear the data entered
     * @param beforeDisplayName
     */
    public void clearDataEntered(String beforeDisplayName){
        displayNameInput.clear();
        displayNameInput.sendKeys(beforeDisplayName);
        codeInput.clear();
        capacityInput.clear();
    }

    /**
     * This method allows get the displayName
     * @return a String
     */
    public String getDisplayName() {
        return displayNameInput.getAttribute("value");
    }

    /**
     * This method allows get the code
     * @return a String
     */
    public String getCode() {
        return codeInput.getAttribute("value");
    }

    /**
     * This method allows get the capacity
     * @return a String
     */
    public  String getCapacity() {
        return capacityInput.getAttribute("value");
    }
}
