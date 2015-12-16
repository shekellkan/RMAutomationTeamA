package ui.pages.tablet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BasePageObject;

/**
 * Created by MiguelTerceros on 12/10/2015.
 */
public class CredentialsPage extends BasePageObject {
    //declare WebElements
    @FindBy(xpath = "//div[contains(@ui-view,'modal')]/div[contains(@class,'ng-scope')]/h3")
    WebElement credentialsLabel;

    @FindBy(xpath = "//div[contains(@class,'input-control')]/input[contains(@placeholder,'username')]")
    @CacheLookup
    WebElement userNameInput;

    @FindBy(xpath = "//div[contains(@class,'input-control')]/input[contains(@placeholder,'password')]")
    @CacheLookup
    WebElement userPasswordInput;

    @FindBy(xpath = "//button[contains(@class,'pull-right')]/span[contains(text(),'OK')]")
    WebElement okButton;
    @FindBy(xpath = "//button[contains(@class,'pull-right')]/span[contains(text(),'Cancel')]")
    WebElement cancelButton;
    @FindBy(xpath = "//div[contains(text(),'There is a conflict with another meeting, please choose another time interval')]")
    WebElement conflictMessage;

    By condition = By.xpath("//div[contains(@ui-view,'modal')]/div[contains(@class,'ng-scope')]/h3");

    /**
     * This method is the constructor
     */
    public CredentialsPage() {
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(credentialsLabel));
    }

    /**
     * set the value in the field username
     * @param userName
     * @return the same instance of CredentialsPage
     */
    public CredentialsPage setUserNameInput(String userName){
        userNameInput.clear();
        userNameInput.sendKeys(userName);
        return this;
    }

    /**
     * set the value in the field password
     * @param userPassword
     * @return the same instance of CredentialsPage
     */
    public CredentialsPage setUserPasswordInput(String userPassword){
        userPasswordInput.clear();
        userPasswordInput.sendKeys(userPassword);
        return this;
    }

    /**
     * click in OK button
     * @return new instance of SchedulePage
     */
    public SchedulePage clickOkButton(){
        okButton.click();
        if(isDisplayed(condition)){
            return new SchedulePage();
        }else{
            return clickCancelButton();
        }
    }

    /**
     *  click in the Cancel button
     * @return new instance of SchedulePage
     */
    public SchedulePage clickCancelButton(){
        cancelButton.click();
        return new SchedulePage();
    }

    /**
     * this method confirm the credentials of the administrator
     * @param userName
     * @param userPassword
     * @return new instance of SchedulePage
     */
    public SchedulePage confirmCredentials(String userName, String userPassword){
        setUserNameInput(userName);
        setUserPasswordInput(userPassword);
        return clickOkButton();
    }

    /**
     * Cancel the credential of the administrator
     * @param userName
     * @param userPassword
     * @return
     */
    public CredentialsPage cancelCredentials(String userName, String userPassword){
        setUserNameInput(userName);
        setUserPasswordInput(userPassword);
        okButton.click();
        return this;
    }

    /**
     * this method verify that a message of conflic is displayed
     * @return true or false
     */
    public boolean isConflictMessageDisplayed(){
        return conflictMessage.isDisplayed();
    }

    /**
     * this method confirm for delete a meeting
     * @param userPassword
     * @return new instance of SchedulePage
     */
    public SchedulePage confirmDelete(String userPassword){
        setUserPasswordInput(userPassword);
        return clickOkButton();
    }

    /**
     * this method confirm for update information in a meeting
     * @param userPassword
     * @return new instance of SchedulePage
     */
    public SchedulePage confirmUpdate(String userPassword){
        setUserPasswordInput(userPassword);
        return clickOkButton();
    }
}
