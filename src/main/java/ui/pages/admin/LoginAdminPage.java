package ui.pages.admin;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BasePageObject;

/**
 * Created by Ariel Rojas on 02/12/2015.
 */
public class LoginAdminPage extends BasePageObject {

    @FindBy(xpath = "//input[@id='loginUsername']")
    @CacheLookup
    WebElement userNameInput;

    @FindBy(xpath = "//input[@id='loginPassword']")
    @CacheLookup
    WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement signInButton;

    /**
     * This method is the constructor
     */
    public LoginAdminPage() {
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(signInButton));
    }

    /**
     * This method sets the username
     * @param userName
     * @return the Login page
     */
    private LoginAdminPage setUserNameInput(String userName) {
        userNameInput.clear();
        userNameInput.sendKeys(userName);
        return this;
    }

    /**
     * This method sets the password
     * @param password
     * @return the Login page
     */
    private LoginAdminPage setPasswordInput(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
        return this;
    }

    /**
     * This method allows press the logIn button having a valid account
     * @return the Main page
     */
    private MainAdminPage clickLoginBtnSuccessful() {
        signInButton.click();
        return new MainAdminPage();
    }

    /**
     * This method allows set the username and password
     * @param userName
     * @param password
     */
    private void login(String userName, String password) {
        setUserNameInput(userName);
        setPasswordInput(password);
    }

    /**
     * This method allows submit the username and password to login method, having a valid account
     * @param userName
     * @param password
     * @return
     */
    public MainAdminPage loginSuccessful(String userName, String password) {
        login(userName, password);
        return clickLoginBtnSuccessful();
    }
}
