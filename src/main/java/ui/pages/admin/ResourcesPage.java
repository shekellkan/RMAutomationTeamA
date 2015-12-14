package ui.pages.admin;

import entities.ResourceEntity;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.common.CommonMethods;

import java.util.ArrayList;
import java.util.List;

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

    @FindBy(xpath = "//input[@ng-model='resourceNameFilter']")
    WebElement resourceFilterInput;

    public ResourcesPage(){
        waitUntilPageObjectIsLoaded();
    }
    @Override
    public void waitUntilPageObjectIsLoaded(){
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

    public String getIconName(String resourceName){
        return driver.findElement(By.xpath("//div[contains(@class,'col2')]//span[text()='" + resourceName + "']/ancestor::div[contains(@class,'ng-scope ngRow')]//div[contains(@class,'col1 colt1')]//span")).getAttribute("class");
    }

    public String getName(String resourceName){
        return driver.findElement(By.xpath("//div[contains(@class,'col2')]//span[text()='"+resourceName+"']/ancestor::div[contains(@class,'ng-scope ngRow')]//div[contains(@class,'col2 colt2')]//span")).getText();
    }

    public String getDisplayName(String resourceName){
        return driver.findElement(By.xpath("//div[contains(@class,'col2')]//span[text()='"+resourceName+"']/ancestor::div[contains(@class,'ng-scope ngRow')]//div[contains(@class,'col3 colt3')]//span")).getText();
    }

    /**
     * this method removes a resources from the list of resources
     * @param resourceEntity
     * @return
     */
    public RemoveResourcesConfirmationPage removeResource(ResourceEntity resourceEntity){
        String resourceName = resourceEntity.getName();
        WebElement resourceCheckInput
                = driver.findElement(By.xpath("//div[contains(@class,'col2')]//span[text()='"+resourceName+"']/ancestor::div[contains(@class,'ng-scope ngRow')]//div[contains(@class,'col0 colt0')]//input"));
        resourceCheckInput.click();
        removeButton.click();
        return new RemoveResourcesConfirmationPage();
    }

    /**
     * this method look for a Resource in the Resource list
     * @param resourceEntity
     * @return
     */
    public boolean isResourceInTheResourceList(ResourceEntity resourceEntity) {
        String resourceName = resourceEntity.getName();
        return isDeleted(10, By.xpath("//div[contains(@class,'col2')]//span[text()='" + resourceName + "']/ancestor::div[contains(@class,'ng-scope ngRow')]"));
    }

    /**
     * this method fill the field filter in the resources list, to filter the resource by some criteria
     * @param criteria
     */
    public void setCriteria(String criteria) {
        resourceFilterInput.sendKeys(criteria);
    }

    /**
     * this method returns a list of the actual Resource in the Resources list
     * @return String array
     */
    public ArrayList<String> getActualTheListOfResources() {
        List<WebElement> WebElementsNameList = driver.findElements(By.xpath("//div[@class='ngCanvas']//div[@class='ngCell centeredColumn col2 colt2']//span"));
        ArrayList<String> resourcesNames = new ArrayList<String>();
        for (WebElement item: WebElementsNameList)
        {
            resourcesNames.add(item.getText());
        }
        return resourcesNames;
    }

    /**
     * this method open a resource
     * @param resourceEntity
     * @return
     */
    public ResourceInfoPage openResource(ResourceEntity resourceEntity) {
        WebElement resourceNameButton = driver.findElement(By.xpath("//div[@class='ngCell centeredColumn col2 colt2']//span[text()='" + resourceEntity.getName() + "']"));
        CommonMethods.doubleClick(resourceNameButton);
        return new ResourceInfoPage();
    }
}
