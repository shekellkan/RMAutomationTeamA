package steps.hooks;

import cucumber.api.java.en.Given;
import ui.pages.admin.LoginAdminPage;

/**
 * User: jeancarlorodriguez
 * Date: 12/7/15
 * Time: 8:34 PM
 */
public class LoginAdmin {

    @Given("^I'm logged in with the user \"([^\\\"]*)\" and password \"([^\\\"]*)\"$")
    public void imLoggedInWithTheUSer(String user, String password){
        LoginAdminPage loginAdminPage = new LoginAdminPage();
        loginAdminPage.loginSuccessful(user, password);

    }

}
