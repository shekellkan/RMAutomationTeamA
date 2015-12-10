package ui.pages.admin;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BasePageObject;

/**
 * Created by ArielWagner on 09/12/2015.
 */
public class RoomPage extends BasePageObject{

    protected RoomMenuPage roomMenuPage;

    @FindBy(xpath = "//span[@ng-click = 'cancel()']")
    WebElement closeButton;

    /**
     * This method is the constructor
     */
    public RoomPage() {
        waitUntilPageObjectIsLoaded();
        roomMenuPage = new RoomMenuPage();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(closeButton));
    }

    /**
     * This method allows get the RoomMenu page
     * @return the RoomMenu page
     */
    public RoomMenuPage getRoomMenuPage() {
        return roomMenuPage;
    }
}
