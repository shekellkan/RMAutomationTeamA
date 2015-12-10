package ui.pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * User: Jean Carlo Rodriguez
 * Date: 12/7/15
 * Time: 8:45 PM
 */
public class ResourcesPage extends MainAdminPage {

    @FindBy(id = "resourcesGrid")
    WebElement resourcesGrid;

    @FindBy(xpath = "//button[@ng-click='addResourceDialog()']")
    WebElement addButton;

    public ResourcesPage()
    {
        waitUntilPageObjectIsLoaded();
    }
    @Override
    public void waitUntilPageObjectIsLoaded()
    {
        wait.until(ExpectedConditions.visibilityOf(resourcesGrid));
    }

    /**
     * click on Add
     * @return new instance of ResourceFormPage
     */
    public ResourceFormPage goToAddNewResource() {
        addButton.click();
        return new ResourceFormPage();
    }

    //Todo
    private WebElement getRowOfAResource(String resourceName)
    {
        return driver.findElement(By.xpath("//div[contains(@class,'col2')]//span[text()='"+resourceName+"']/ancestor::div[contains(@class,'ng-scope ngRow')]"));
    }

    public String getIconName(String resourceName)
    {
        return getRowOfAResource(resourceName).findElement(By.xpath("//div[contains(@class,'col1 colt1')]//span")).getAttribute("class");
    }

    public String getName(String resourceName)
    {
        return getRowOfAResource(resourceName).findElement(By.xpath("//div[contains(@class,'col2 colt2')]//span")).getText();
    }

    public String getDisplayName(String resourceName)
    {
        return getRowOfAResource(resourceName).findElement(By.xpath("//div[contains(@class,'col3 colt3')]//span")).getText();
    }
}
