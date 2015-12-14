package ui.pages.admin;

import entities.RoomEntity;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BasePageObject;

/**
 * User: Jean Carlo Rodriguez
 * Date: 12/12/15
 * Time: 2:45 PM
 */
public class ResourceAssociationPage extends BasePageObject{

    @FindBy(xpath = "//div[@ng-controller='ResourceAsociationsModalCtrl']")
    WebElement associationContainer;

    @FindBy(xpath = "//span[text()='Close']//ancestor::button")
    WebElement cancelButton;

    public ResourceAssociationPage() {
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(associationContainer));

    }

    /**
     * this method return true if the room is associated to the actual resource
     * @param roomEntity
     * @return
     */
    public boolean isResourceAssociatedWithTheRoom(RoomEntity roomEntity) {
        return isPresent(By.xpath("//div[@class='ngCellText ng-scope col0 colt0']//span[text()='"+roomEntity.getDisplayName()+"']"));
    }

    public ResourcesPage  clickOnCloseButton() {
        cancelButton.click();
        isDisplayed(By.xpath("//span[text()='Cancel']//ancestor::button"));
        return new ResourcesPage();
    }
}
