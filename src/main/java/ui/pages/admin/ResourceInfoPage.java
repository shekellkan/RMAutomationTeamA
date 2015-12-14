package ui.pages.admin;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BasePageObject;

/**
 * User: Jean Carlo Rodriguez
 * Date: 12/12/15
 * Time: 2:35 PM
 */
public class ResourceInfoPage extends BasePageObject {
    ResourceFormPage resourceFormPage;

    @FindBy(xpath = "//div[@id='breadcrumb']//a[text()='Resource Info']")
    WebElement resourceInfoTabButton;

    @FindBy(xpath = "//div[@id='breadcrumb']//a[text()='Resource Associations']")
    WebElement resourceAssociationTabButton;

    public ResourceInfoPage() {
        resourceFormPage = new ResourceFormPage();
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(resourceInfoTabButton));
    }

    public ResourceAssociationPage goToAssociationTab ()
    {
        resourceAssociationTabButton.click();
        return new ResourceAssociationPage();
    }
}
