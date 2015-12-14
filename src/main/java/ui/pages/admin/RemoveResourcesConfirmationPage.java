package ui.pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BasePageObject;

/**
 * User: Jean Carlo Rodriguez
 * Date: 12/10/15
 * Time: 12:20 PM
 */
public class RemoveResourcesConfirmationPage extends BasePageObject {

    @FindBy(xpath = "//div[@class='modal-footer ng-scope']//button//span[text()='Remove']")
    WebElement removeButton;

    public RemoveResourcesConfirmationPage() {
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(removeButton));
    }

    public ResourcesPage ClickOnRemoveButton()
    {
        removeButton.click();
        isDisplayed(By.xpath("//div[@class='modal-footer ng-scope']//button//span[text()='Remove']"));
        return new ResourcesPage();
    }
}
