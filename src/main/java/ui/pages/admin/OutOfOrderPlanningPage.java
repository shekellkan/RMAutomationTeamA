package ui.pages.admin;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by ArielWagner on 09/12/2015.
 */
public class OutOfOrderPlanningPage extends RoomMenuPage {

    @FindBy(xpath = "//span[contains(@class, 'calendar')]")
    WebElement calendarButton;

    /**
     * This method is the constructor
     */
    public OutOfOrderPlanningPage() {
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(calendarButton));
    }
}
