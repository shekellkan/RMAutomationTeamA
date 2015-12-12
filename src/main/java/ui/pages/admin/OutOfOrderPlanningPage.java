package ui.pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by ArielWagner on 09/12/2015.
 */
public class OutOfOrderPlanningPage extends RoomMenuPage {

    @FindBy(xpath = "//span[contains(@class, 'calendar')]")
    WebElement calendarButton;

    @FindBy(xpath = "//label[contains(@for, 'dropdown')]")
    WebElement outOfOrderdropDown;

    By outOfOrderOption;

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

    public void selectOutOfOrder(String outOfOrder) {
        outOfOrderdropDown.click();
        outOfOrderOption = By.xpath("//ul//a[contains(text(), '"+ outOfOrder +"')]");
        driver.findElement(outOfOrderOption).click();
    }
}
