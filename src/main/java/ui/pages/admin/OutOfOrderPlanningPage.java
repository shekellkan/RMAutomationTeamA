package ui.pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by ArielWagner on 09/12/2015.
 */
public class OutOfOrderPlanningPage extends RoomMenuPage {

    @FindBy(xpath = "//label[@for = 'active-button']")
    WebElement calendarButton;

    @FindBy(xpath = "//label[contains(@for, 'dropdown')]")
    WebElement outOfOrderDropDown;

    @FindBy(xpath = "//input[@ng-model = 'hours']")
    WebElement hourStartInput;

    @FindBy(xpath = "//div[2][@class = 'col-md-6']//input[@ng-model = 'hours']")
    WebElement hourEndInput;

    @FindBy(xpath = "//a[@ng-click = 'incrementHours()']")
    WebElement incrementHourStartButton;

    @FindBy(xpath = "//div[2][@class = 'col-md-6']//a[@ng-click = 'incrementHours()']")
    WebElement incrementHourEndButton;

    @FindBy(xpath = "//a[@ng-click = 'decrementHours()']")
    WebElement decrementHourStartButton;

    @FindBy(xpath = "//div[2][@class = 'col-md-6']//a[@ng-click = 'decrementHours()']")
    WebElement decrementHourEndButton;

    @FindBy(xpath = "//button[@ng-click = 'toggleMeridian()']")
    WebElement stateMeridianStartButton;

    @FindBy(xpath = "//div[2][@class = 'col-md-6']//button[@ng-click = 'toggleMeridian()']")
    WebElement stateMeridianEndButton;

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

    /**
     * This method allows configure out of order a room
     * @param outOfOrder is the state
     * @param hourStart is the start time (hour)
     * @param hourEnd is the end time (hour)
     * @param meridian is the meridian state (PM or AM)
     * @return the ConferenceRooms page
     */
    public ConferenceRoomsPage configureOutOfOrder(String outOfOrder, String hourStart, String hourEnd, String meridian) {
        outOfOrderDropDown.click();
        outOfOrderOption = By.xpath("//ul//a[contains(text(), '"+ outOfOrder +"')]");
        driver.findElement(outOfOrderOption).click();
        int hourActualStart = Integer.parseInt(getHourActualStart());
        int hourExpectStart = Integer.parseInt(hourStart);
        int hourActualEnd = Integer.parseInt(getHourActualEnd());
        int hourExpectEnd = Integer.parseInt(hourEnd);
        while(hourActualStart != hourExpectStart) {
            if(hourExpectStart > hourActualStart ) {
                if(!(getStateMeridianStart().equals(meridian))) {
                    stateMeridianStartButton.click();
                    incrementHourStartButton.click();
                    hourActualStart++;
                }
                else {
                    incrementHourStartButton.click();
                    hourActualStart++;
                }
            }
            else {
                if(!(getStateMeridianStart().equals(meridian))) {
                    stateMeridianStartButton.click();
                    decrementHourStartButton.click();
                    hourActualStart--;
                }
                else {
                    decrementHourStartButton.click();
                    hourActualStart--;
                }
            }
        }

        while(hourActualEnd != hourExpectEnd) {
            if(hourExpectEnd > hourActualEnd ) {
                if(!(getStateMeridianEnd().equals(meridian))) {
                    stateMeridianEndButton.click();
                    incrementHourEndButton.click();
                    hourActualEnd++;
                }
                else {
                    incrementHourEndButton.click();
                    hourActualEnd++;
                }
            }
            else {
                if(!(getStateMeridianEnd().equals(meridian))) {
                    stateMeridianEndButton.click();
                    decrementHourEndButton.click();
                    hourActualEnd--;
                }
                else {
                    decrementHourEndButton.click();
                    hourActualEnd--;
                }
            }
        }
        OutOfOrderPlanningPage.super.clickSaveRoom();
        return new ConferenceRoomsPage();
    }

    /**
     * This method allows get the actual hour of start
     * @return a String
     */
    public String getHourActualStart() {
        return hourStartInput.getAttribute("value");
    }

    /**
     * This method allows get the actual hour od end
     * @return a String
     */
    public String getHourActualEnd() {
        return hourEndInput.getAttribute("value");
    }

    /**
     * This method allows get the meridian state of start
     * @return a String
     */
    public String getStateMeridianStart() {
        return stateMeridianStartButton.getText();
    }

    /**
     * This method allows get the meridian state of end
     * @return a String
     */
    public String getStateMeridianEnd() {
        return stateMeridianEndButton.getText();
    }
}
