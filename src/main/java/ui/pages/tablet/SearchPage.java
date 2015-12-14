package ui.pages.tablet;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BasePageObject;

/**
 * Created by MiguelTerceros on 12/10/2015.
 */
public class SearchPage extends BasePageObject {
    /**
     * declare of variables
     */
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

    /**
     * navigate to MainPage
     * @return new instance of MainPage
     */
    public MainTabletPage goMainPage(){
        return topMenuPage.clickInHome();
    }

    /**
     * navigate to SchedulePage
     * @return new instance of SchedulePage
     */
    public SchedulePage goSchedulePage(){
        return topMenuPage.clickInSchedule();
    }

    /**
     * navigate to the same page
     * @return the same instance of SearchPage
     */
    public SearchPage goSearchPage(){
        return topMenuPage.clickInSearch();
    }
}
