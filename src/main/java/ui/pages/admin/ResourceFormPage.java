package ui.pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BasePageObject;

/**
 * User: jeancarlorodriguez
 * Date: 12/8/15
 * Time: 2:17 PM
 */
public class ResourceFormPage extends BasePageObject {

    @FindBy(xpath = "//input[@ng-model='resource.name']")
    WebElement nameInput;

    @FindBy(xpath = "//input[@ng-model='resource.customName']")
    WebElement displayNameInput;

    @FindBy(xpath = "//textarea[@ng-model='resource.description']")
    WebElement descriptionTextarea;

    @FindBy(xpath = "//button[@id='convert']")
    WebElement iconMenuButton;

    @FindBy(xpath = "//span[text()='Save']//ancestor::button")
    WebElement saveButton;

    public ResourceFormPage()
    {
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(nameInput));
    }

    /**
     * Set name of a Resource
     * @param name
     * @return
     */
    public ResourceFormPage setName(String name)
    {
        nameInput.clear();
        nameInput.sendKeys(name);
        return this;
    }

    /**
     * Set display name of a Resource
     * @param displayName
     * @return
     */
    public ResourceFormPage setDisplayName(String displayName)
    {
        displayNameInput.clear();
        displayNameInput.sendKeys(displayName);
        return this;
    }

    /**
     * Set description of a Resource
     * @param description
     * @return
     */
    public ResourceFormPage setDescription(String description)
    {
        descriptionTextarea.clear();
        descriptionTextarea.sendKeys(description);
        return this;
    }

    /**
     * Set icon of a Resource
     * @param iconName
     * @return
     */
    public ResourceFormPage setIcon(String iconName)
    {
        iconMenuButton.click();
        By iconBy = By.xpath("//button[@value='"+iconName+"']");
        WebElement icon = driver.findElement(iconBy);
        icon.click();
        return this;
    }

    /**
     * This method set all the field of a resource and click in the button save to create a new resource
     * @param name
     * @param displayName
     * @param description
     * @param icon
     * @return
     */
    public ResourcesPage createAResource(String name, String displayName, String description, String icon) {
        setName(name);
        setDisplayName(displayName);
        setDescription(description);
        setIcon(icon);
        saveButton.click();
        return new ResourcesPage();
    }
}
