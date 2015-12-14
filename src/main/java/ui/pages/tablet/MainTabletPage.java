package ui.pages.tablet;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BasePageObject;

/**
 * Created by MiguelTerceros on 12/9/2015.
 */
public class MainTabletPage extends BasePageObject{
    /**
     * declare WebElements
     */
    @FindBy(xpath = "//div[contains(@class,'room-name')]/span[contains(@class,'room-label')]")
    WebElement mainLabel;
    @FindBy(xpath = "//div[contains(@class,'info')]/div[contains(@class,'meeting-title') and contains(text(),'Available')]")
    WebElement availableSection;
    @FindBy(xpath = "//div[contains(@class,'info')]/div[contains(@class,'meeting-title') and contains(text(),'End of day')]")
    WebElement endOfDaySection;
    @FindBy(xpath = "//rm-panel-option[contains(@class,'tile-column-option-landscape')]/div[contains(@class,'tile-button-schedule')]/div[contains(@class,'tile-icon')]")
    WebElement scheduleSection;
    @FindBy(xpath = "//rm-panel-option[contains(@class,'tile-column-option-landscape')]/div[contains(@class,'tile-button-search')]/div[contains(@class,'tile-icon')]")
    WebElement searchSection;

    WebElement nameMeetingLabel;
    WebElement nameMeetingInNextSection;

    /**
     * This method is the constructor
     */
    public MainTabletPage() {
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(mainLabel));
    }

    /**
     * click in the section Available
     * @return new instance of SchedulePage
     */
    public SchedulePage clickAvailableSection(){
        availableSection.click();
        return new SchedulePage();
    }

    /**
     * click in the section End Of Day
     * @return new instance of SchedulePage
     */
    public SchedulePage clickEndOfDaySection(){
        endOfDaySection.click();
        return new SchedulePage();
    }

    /**
     * click in the section Schedule
     * @return new instance of SchedulePage
     */
    public SchedulePage clickScheduleSection(){
        scheduleSection.click();
        return new SchedulePage();
    }

    /**
     * click in the section Search
     * @return new instance of SearchPage
     */
    public SearchPage clickSearchSection(){
        searchSection.click();
        return new SearchPage();
    }

    /**
     * this method verify that the title of the room is present
     * @return true or false
     */
    public boolean isMainLabelPresent(){
        return mainLabel.isDisplayed();
    }

    /**
     * this method verify that the meeting is present in the MainPage
     * @param nameMeeting
     * @return true or false
     */
    public boolean isMeetingPresent(String nameMeeting){
        try{
            nameMeetingLabel = driver.findElement(By.xpath(buildNameMeetingTitle(nameMeeting)));
            return nameMeetingLabel.isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }

    /**
     * this method build the path for found a meeting in the schedule bar
     * @param nameMeeting
     * @return path
     */
    public String buildNameMeetingTitle(String nameMeeting){
        return "//div[contains(@class,'vis-item-content')]/span[contains(text(),'"+nameMeeting+"')]";
    }

    /**
     * this method build the path for found a meeting in the nex section
     * @param nameMeeting
     * @return path
     */
    public String buildNameMeetingInNextSection(String nameMeeting){
        return "//div[contains(@class,'info')]/div[contains(text(),'"+nameMeeting+"')]";
    }

    /**
     * this method verify that a meeting is present in the next section
     * @param nameMeeting
     * @return true or false
     */
    public boolean isMeetingPresentInNextSection(String nameMeeting){
        try{
            nameMeetingInNextSection = driver.findElement(By.xpath(buildNameMeetingInNextSection(nameMeeting)));
            return nameMeetingInNextSection.isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }

    /**
     * this method get the name of a meeting that is present in next section
     * @param nameMeeting
     * @return name meeting
     */
    public String getNameMeetingInNextSection(String nameMeeting){
        nameMeetingInNextSection = driver.findElement(By.xpath(buildNameMeetingInNextSection(nameMeeting)));
        return nameMeetingInNextSection.getText();
    }
}
