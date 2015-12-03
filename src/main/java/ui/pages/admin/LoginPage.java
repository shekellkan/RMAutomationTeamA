package ui.pages.admin;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BasePageObject;

/**
 * Created by Ariel Rojas on 02/12/2015.
 */
public class LoginPage extends BasePageObject {

    @FindBy(xpath = "//input[@id='loginUsername']")
    @CacheLookup
    WebElement userNameInput;

    @FindBy(xpath = "//input[@id='loginPassword']")
    @CacheLookup
    WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement signInBtn;

    /**
     * This method is the constructor
     */
    public LoginPage() {
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(signInBtn));
    }

    /**
     * This method sets the username
     * @param userName
     * @return the Login page
     */
    private LoginPage setUserNameInput(String userName) {
        userNameInput.clear();
        userNameInput.sendKeys(userName);
        return this;
    }

    /**
     * This method sets the password
     * @param password
     * @return the Login page
     */
    private LoginPage setPasswordInput(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
        return this;
    }

    /**
     * This method allows press the logIn button having a valid account
     * @return the Main page
     */
    private MainPage clickLoginBtnSuccessful() {
        signInBtn.click();
        return new MainPage();
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
    public MainPage loginSuccessful(String userName, String password) {
        login(userName, password);
        return clickLoginBtnSuccessful();
    }
}
