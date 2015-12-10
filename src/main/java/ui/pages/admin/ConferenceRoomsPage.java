package ui.pages.admin;

import entities.ResourceEntity;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by ArielWagner on 07/12/2015.
 */
public class ConferenceRoomsPage extends MainAdminPage {

    @FindBy(xpath = "//div[@id = 'roomsGrid']")
    WebElement roomsGridContainer;

    /**
     * This method is the constructor
     */
    public ConferenceRoomsPage() {
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(roomsGridContainer));
    }

    /**
     * Method that return true if the resource is in the header of conference rooms
     * @param resource
     * @return
     */
    public boolean isResourceButtonPresent(ResourceEntity resource)
    {
        return isPresent(By.xpath("//div[@class='row']//span[text()='" + resource.getDisplayName() + "']"));
    }
}
