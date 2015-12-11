package ui.pages.tablet;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BasePageObject;

/**
 * Created by MiguelTerceros on 12/10/2015.
 */
public class SearchPage extends BasePageObject {
    private TopMenuPage topMenuPage;
    @FindBy(xpath = "//div[contains(@class,'item-title')]/span")
    WebElement searchLabel;

    /**
     * This method is the constructor
     */
    public SearchPage() {
        waitUntilPageObjectIsLoaded();
        topMenuPage = new TopMenuPage();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(searchLabel));
    }

    public MainTabletPage goMainPage(){
        return topMenuPage.clickInHome();
    }

    public SchedulePage goSchedulePage(){
        return topMenuPage.clickInSchedule();
    }

    public SearchPage goSearchPage(){
        return this;
    }
}
