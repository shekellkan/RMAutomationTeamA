package ui.pages.admin;
import entities.ResourceEntity;
import entities.RoomEntity;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.common.CommonMethods;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ArielWagner on 07/12/2015.
 */
public class ConferenceRoomsPage extends MainAdminPage {
    @FindBy(xpath = "//div[@id = 'roomsGrid']")
    WebElement roomsGridContainer;

    @FindBy(xpath = "//div[contains(text(), 'Room successfully Modified')]")
    WebElement roomModifiedMessagePopUp;

    @FindBy(xpath = "//input[contains(@ng-model, 'filterOptions')]")
    WebElement filterByRoomInput;

    By outOfOrderIcon = By.xpath("//out-of-order-icon//span");

    By roomDisplayNameLabel;

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
     * This method allows selectRoom for edit
     * @param room
     * @return the RoomInfo page
     */
    public RoomInfoPage selectRoom(String room){
        roomDisplayNameLabel = By.xpath("//span[2][contains(text(), '"+ room +"')]");
        CommonMethods.doubleClick(driver.findElement(roomDisplayNameLabel));
        return new RoomInfoPage();
    }
    /**
     * This method allows get the message displayed after of edit a room
     * @return a String
     */
    public String getRoomModifiedMessagePopUp() {
        return roomModifiedMessagePopUp.getText();
    }

    public boolean isResourceButtonPresent(ResourceEntity resource)
    {
        return isPresent(By.xpath("//div[@class='row']//span[text()='" + resource.getDisplayName() + "']"));
    }

    public void clickOnResourceButton(ResourceEntity resource)
    {
        driver.findElement(By.xpath("//div[@class='row']//span[text()='"+ resource.getDisplayName() +"']")).click();
    }

    /**
     * This method allows set the value for the filter by room
     * @param criteria
     */
    public void setFilterByRoom(String criteria) {
        filterByRoomInput.clear();
        filterByRoomInput.sendKeys(criteria);
    }

    /**
     * this method return a String array that contains the values for icon and quantity of a resource association
     * @param roomEntity
     * @param resourceEntity
     * @return
     */
    public String[] getIconAndQuantityFromResourceAssociated(RoomEntity roomEntity, ResourceEntity resourceEntity) {
        String resp[] = new String[2];
        String resourceDisplayName = resourceEntity.getDisplayName();
        String roomDisplayName = roomEntity.getDisplayName();
        //find the class of a resource column
        String classOfResourceColumn = getClassOfResourceColumn(resourceDisplayName);
        int columnNumber = findColumnNumber(classOfResourceColumn);
        //iconName
        resp[0] = driver.findElement(By.xpath("//div[@class='ngCell  col2 colt2']//span[2][text()='"+roomDisplayName+"']//ancestor::div[contains(@class,'ng-scope ngRow')]//div[@class='ngCell centeredColumn col"+columnNumber+" colt"+columnNumber+"']//div[@class='animate-if ng-scope']//span[1]")).getAttribute("class");
        //Quantity
        resp[1] = driver.findElement(By.xpath("//div[@class='ngCell  col2 colt2']//span[2][text()='"+roomDisplayName+"']//ancestor::div[contains(@class,'ng-scope ngRow')]//div[@class='ngCell centeredColumn col"+columnNumber+" colt"+columnNumber+"']//div[@class='animate-if ng-scope']//span[2]")).getText();
        return resp;
    }

    /**
     * this method return the class of a resource Column
     * @param resourceDisplayName
     * @return
     */
    private String getClassOfResourceColumn(String resourceDisplayName){
        return driver.findElement (By.xpath("//div[@class='ngHeaderContainer']//div[text()='"+resourceDisplayName+"']")).getAttribute("class");
    }

    /**
     * this method find the number of a column in string (class of Resource column)
     * @param classOfResourceColumn
     * @return
     */
    private int findColumnNumber(String classOfResourceColumn) {
        char array[] = classOfResourceColumn.toCharArray();
        String resNumber = "";
        for (int i=array.length-1;i>0;i--)
        {
            if(array[i]=='t')
            {
                i=0;
            }else
            {
                resNumber = array[i]+resNumber;
            }
        }
        return Integer.parseInt(resNumber);
    }
    /**
     * This method allows get the rooms displayed in ConferenceRooms UI
     * @return an ArrayList of strings
     */
    public ArrayList<String> getRoomsContainer() {
        ArrayList<String> roomsList = new ArrayList<String>();
        List<WebElement> roomsCollection = driver.findElements(By.xpath("//div[@class='ngCanvas']//div[@class='ngCell  col2 colt2']//span[2]"));
        for(WebElement element : roomsCollection) {
            roomsList.add(element.getText());
        }
        return roomsList;
    }

    /**
     * This method allows verify if a outOfOrder icon is displayed
     * @return a false or true
     */
    public boolean isPresentOutOfOrderIcon() {
        return isDisplayed(outOfOrderIcon);
    }

    /**
     * this method look for a component to say true if the resource is associated to a room
     * @param roomEntity
     * @param resourceEntity
     * @return
     */
    public boolean isResourceAssignedToTheRoom(RoomEntity roomEntity, ResourceEntity resourceEntity) {
        String roomDisplayName = roomEntity.getDisplayName();
        String resourceDisplayName = resourceEntity.getDisplayName();
        int columnNumber = findColumnNumber(getClassOfResourceColumn(resourceDisplayName));
        return isPresent(By.xpath("//div[@class='ngCell  col2 colt2']//span[2][text()='"+roomDisplayName+"']//ancestor::div[contains(@class,'ng-scope ngRow')]//div[@class='ngCell centeredColumn col"+columnNumber+" colt"+columnNumber+"']//div[@class='animate-if ng-scope']//span[2]"));
    }
}
