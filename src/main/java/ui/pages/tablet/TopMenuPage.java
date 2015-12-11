package ui.pages.tablet;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BasePageObject;

/**
 * Created by MiguelTerceros on 12/10/2015.
 */
public class TopMenuPage extends BasePageObject {
    @FindBy(xpath = "//div[contains(@class,'header-primary-bar')]/div[contains(@id,'go-home')]")
    WebElement goHomeIcon;
    @FindBy(xpath = "//div[contains(@class,'header-primary-bar')]/div[contains(@id,'go-schedule')]")
    WebElement goScheduleIcon;
    @FindBy(xpath = "//div[contains(@class,'header-primary-bar')]/div[contains(@id,'go-search')]")
    WebElement goSearchIcon;

    /**
     * This method is the constructor
     */
    public TopMenuPage() {
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(goHomeIcon));
    }

    public MainTabletPage clickInHome(){
        goHomeIcon.click();
        return new MainTabletPage();
    }

    public SchedulePage clickInSchedule(){
        goScheduleIcon.click();
        return new SchedulePage();
    }

    public SearchPage clickInSearch(){
        goSearchIcon.click();
        return new SearchPage();
    }
}
