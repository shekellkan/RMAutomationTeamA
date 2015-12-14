package ui.pages.admin;

import entities.ResourceEntity;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * User: jeancarlorodriguez
 * Date: 12/11/15
 * Time: 5:34 PM
 */
public class RoomAssociationResourcePage extends RoomMenuPage {

    @FindBy(xpath = "//div//legend[text()='Associated']")
    WebElement associatedLegend;

    public RoomAssociationResourcePage() {
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded()
    {
        wait.until(ExpectedConditions.visibilityOf(associatedLegend));
    }

    public void associateResource(ResourceEntity resourceEntity, String quantity) {
        String displayName = resourceEntity.getDisplayName();
        WebElement plusButton = driver.findElement(By.xpath("//div[@class='col-xs-12 col-sm-5 col-lg-5']//span[text()='"+displayName+"']//ancestor::div[contains(@class,'list-group-item')]//button"));
        plusButton.click();
        WebElement quantityInput = driver.findElement(By.xpath("//div[@class='col-xs-12 col-sm-6 col-sm-offset-1 col-lg-6']//span[text()='"+displayName+"']//ancestor::div[contains(@class,'list-group-item')]//input"));
        quantityInput.clear();
        quantityInput.sendKeys(quantity);
    }
    public void removeTheAssociatedResource(ResourceEntity resourceEntity){
        String displayName = resourceEntity.getDisplayName();
        WebElement restButton = driver.findElement(By.xpath("//div[@class='col-xs-12 col-sm-6 col-sm-offset-1 col-lg-6']//span[text()='"+displayName+"']//ancestor::div[contains(@class,'list-group-item')]//button"));
        restButton.click();
    }
}
