package ui.pages.tablet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BasePageObject;

/**
 * Created by MiguelTerceros on 12/9/2015.
 */
public class MainTabletPage extends BasePageObject{
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

    public SchedulePage clickAvailableSection(){
        availableSection.click();
        return new SchedulePage();
    }

    public SchedulePage clickEndOfDaySection(){
        endOfDaySection.click();
        return new SchedulePage();
    }

    public SchedulePage clickScheduleSection(){
        scheduleSection.click();
        return new SchedulePage();
    }

    public SearchPage clickSearchSection(){
        searchSection.click();
        return new SearchPage();
    }

    public boolean isMainLabelPresent(){
        return mainLabel.isDisplayed();
    }

    public boolean isMeetingPresent(String nameMeeting){
        nameMeetingLabel = driver.findElement(By.xpath(buildNameMeetingTitle(nameMeeting)));
        return nameMeetingLabel.isDisplayed();
    }

    public String buildNameMeetingTitle(String nameMeeting){
        return "//div[contains(@class,'info')]/div[contains(@class,'meeting-title') and contains(text(),'"+nameMeeting+"')]";
    }
}
