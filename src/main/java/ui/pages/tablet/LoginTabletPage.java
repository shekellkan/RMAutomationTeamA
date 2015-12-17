package ui.pages.tablet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BasePageObject;

/**
 * Created by MiguelTerceros on 12/9/2015.
 */
public class LoginTabletPage extends BasePageObject{
    //declare WebElements
    @FindBy(xpath = "//div[contains(@class,'account-header')]/div/h1/bold[contains(text(),'Room Manager')]")
    WebElement roomManagerLabel;
    @FindBy(xpath = "//div[contains(@class,'input-group')]/input[contains(@id,'service-url-input')]")
    @CacheLookup
    WebElement serviceURLInput;
    @FindBy(xpath = "//div[contains(@class,'form-group')]/input[contains(@id,'username')]")
    @CacheLookup
    WebElement userNameInput;
    @FindBy(xpath = "//div[contains(@class,'form-group')]/input[contains(@id,'password')]")
    @CacheLookup
    WebElement passwordInput;
    @FindBy(xpath = "//div[contains(@form,'account-register-for')]//span[contains(text(),'Sign In')]")
    WebElement singInButton;

    @FindBy(xpath = "//i[@ng-show = 'login.form.serviceUrl.$valid']")
    WebElement serviceURLValidIcon;

    WebElement listRoomSelect;
    WebElement startNowButton;
    WebElement searchRoomInput;
    WebElement roomItemSearch;

    /**
     * This method is the constructor
     */
    public LoginTabletPage() {
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(roomManagerLabel));
    }

    /**
     * set the value in the field service URL
     * @param serviceURL
     * @return the same instance of LoginTabletPage
     */
    public LoginTabletPage setServiceURL(String serviceURL){
        serviceURLInput.clear();
        serviceURLInput.sendKeys(serviceURL);
        wait.until(ExpectedConditions.visibilityOf(serviceURLValidIcon));
        return this;
    }

    /**
     * set the value in the field username
     * @param userName
     * @return the same instance of LoginTabletPage
     */
    public LoginTabletPage setUserName(String userName){
        userNameInput.clear();
        userNameInput.sendKeys(userName);
        return this;
    }

    /**
     * set the value in the field password
     * @param password
     * @return the same instance of LoginTabletPage
     */
    public LoginTabletPage setPassword(String password){
        passwordInput.clear();
        passwordInput.sendKeys(password);
        return this;
    }

    /**
     * click in the Sing In button
     * @return the same instance of LoginTabletPage
     */
    public LoginTabletPage clickSingIn(){
        singInButton.click();
        return this;
    }

    /**
     * this method make login in the tablet
     * @param serviceURL
     * @param userName
     * @param userPassword
     * @return the same instance of LoginTabletPage
     */
    public LoginTabletPage LoginTablet(String serviceURL, String userName, String userPassword){
        setServiceURL(serviceURL);
        setUserName(userName);
        setPassword(userPassword);
        clickSingIn();
        return this;
    }

    /**
     * click in the list deployed
     * @return the same instance of LoginTabletPage
     */
    public LoginTabletPage clickListRoom(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'account-main')]/div/label[contains(text(),'Account expiration date')]")));
        listRoomSelect = driver.findElement(By.xpath("//span[contains(@class,'input-group-btn')]/div[contains(@class,'toggle-btn')]"));
        listRoomSelect.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'rm-select-item')]/div[contains(@class,'list-group')]/span/input")));
        return this;
    }

    /**
     * set the value in the field Search Room
     * @param nameRoom
     * @return the same instance of LoginTabletPage
     */
    public LoginTabletPage setSearchRoom(String nameRoom){
        searchRoomInput = driver.findElement(By.xpath("//div[contains(@class,'rm-select-item')]/div[contains(@class,'list-group')]/span/input"));
        searchRoomInput.clear();
        searchRoomInput.sendKeys(nameRoom);
        return this;
    }

    /**
     * click in a option of Room
     * @param nameRoom
     * @return the same instance of LoginTabletPage
     */
    public LoginTabletPage clickInRoomSearch(String nameRoom){
        roomItemSearch = driver.findElement(By.xpath(buildPathRoomSearch(nameRoom)));
        roomItemSearch.click();
        return this;
    }

    /**
     * click in the Start Now button
     * @return new instance of MAinTabletPage
     */
    public MainTabletPage clickStartNow(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'account-main')]/div/label[contains(text(),'Account expiration date')]")));
        startNowButton = driver.findElement(By.xpath("//div[contains(@class,'options-area')]/div/div/button[contains(@class,'btn-primary')]"));
        startNowButton.click();
        return new MainTabletPage();
    }

    /**
     * this method search and selected a Room specific
     * @param nameRoom
     * @return new instance of MAinTabletPage
     */
    public MainTabletPage selectRoom(String nameRoom){
        clickListRoom();
        setSearchRoom(nameRoom);
        clickInRoomSearch(nameRoom);
        return clickStartNow();
    }

    /**
     * this method build the path for a room
     * @param nameRoom
     * @return path
     */
    public String buildPathRoomSearch(String nameRoom){
        return "//div[contains(@class,'item-box')]/a/strong[contains(@class,'ng-binding') and contains(text(),'"+nameRoom+"')]";
    }
}
