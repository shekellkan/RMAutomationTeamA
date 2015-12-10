package ui.pages.admin;

import entities.ResourceEntity;
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

    @FindBy(id = "btnRemove")
    WebElement removeButton;

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

    public String getIconName(String resourceName)
    {
        return driver.findElement(By.xpath("//div[contains(@class,'col2')]//span[text()='" + resourceName + "']/ancestor::div[contains(@class,'ng-scope ngRow')]//div[contains(@class,'col1 colt1')]//span")).getAttribute("class");
    }

    public String getName(String resourceName)
    {
        return driver.findElement(By.xpath("//div[contains(@class,'col2')]//span[text()='"+resourceName+"']/ancestor::div[contains(@class,'ng-scope ngRow')]//div[contains(@class,'col2 colt2')]//span")).getText();
    }

    public String getDisplayName(String resourceName)
    {
        return driver.findElement(By.xpath("//div[contains(@class,'col2')]//span[text()='"+resourceName+"']/ancestor::div[contains(@class,'ng-scope ngRow')]//div[contains(@class,'col3 colt3')]//span")).getText();
    }

    public RemoveResourcesConfirmationPage removeResource(ResourceEntity resourceEntity) {
        String resourceName = resourceEntity.getName();
        WebElement resourceCheckInput
                = driver.findElement(By.xpath("//div[contains(@class,'col2')]//span[text()='"+resourceName+"']/ancestor::div[contains(@class,'ng-scope ngRow')]//div[contains(@class,'col0 colt0')]//input"));
        resourceCheckInput.click();
        removeButton.click();
        return new RemoveResourcesConfirmationPage();
    }

    public boolean isResourceInTheResourceList(ResourceEntity resourceEntity) {
        String resourceName = resourceEntity.getName();
        return isDeleted(10,By.xpath("//div[contains(@class,'col2')]//span[text()='"+resourceEntity+"']/ancestor::div[contains(@class,'ng-scope ngRow')]"));
    }
}
