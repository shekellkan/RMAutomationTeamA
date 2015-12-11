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

    public LoginTabletPage setServiceURL(String serviceURL){
        serviceURLInput.clear();
        serviceURLInput.sendKeys(serviceURL);
        return this;
    }

    public LoginTabletPage setUserName(String userName){
        userNameInput.clear();
        userNameInput.sendKeys(userName);
        return this;
    }

    public LoginTabletPage setPassword(String password){
        passwordInput.clear();
        passwordInput.sendKeys(password);
        return this;
    }

    public LoginTabletPage clickSingIn(){
        singInButton.click();
        return this;
    }

    public LoginTabletPage LoginTablet(String serviceURL, String userName, String userPassword){
        setServiceURL(serviceURL);
        setUserName(userName);
        setPassword(userPassword);
        clickSingIn();
        return this;
    }

    public LoginTabletPage clickListRoom(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'account-main')]/div/label[contains(text(),'Account expiration date')]")));
        listRoomSelect = driver.findElement(By.xpath("//span[contains(@class,'input-group-btn')]/div[contains(@class,'toggle-btn')]"));
        listRoomSelect.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'rm-select-item')]/div[contains(@class,'list-group')]/span/input")));
        return this;
    }

    public LoginTabletPage setSearchRoom(String nameRoom){
        searchRoomInput = driver.findElement(By.xpath("//div[contains(@class,'rm-select-item')]/div[contains(@class,'list-group')]/span/input"));
        searchRoomInput.clear();
        searchRoomInput.sendKeys(nameRoom);
        return this;
    }

    public LoginTabletPage clickInRoomSearch(String nameRoom){
        roomItemSearch = driver.findElement(By.xpath(buildPathRoomSearch(nameRoom)));
        roomItemSearch.click();
        return this;
    }

    public MainTabletPage clickStartNow(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'account-main')]/div/label[contains(text(),'Account expiration date')]")));
        startNowButton = driver.findElement(By.xpath("//div[contains(@class,'options-area')]/div/div/button[contains(@class,'btn-primary')]"));
        startNowButton.click();
        return new MainTabletPage();
    }

    public MainTabletPage selectRoom(String nameRoom){
        clickListRoom();
        setSearchRoom(nameRoom);
        clickInRoomSearch(nameRoom);
        return clickStartNow();
    }

    public String buildPathRoomSearch(String nameRoom){
        return "//div[contains(@class,'item-box')]/a/strong[contains(@class,'ng-binding') and contains(text(),'"+nameRoom+"')]";
    }
}
