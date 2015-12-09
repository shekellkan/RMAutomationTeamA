package steps;

import Framework.ExternalVariablesManager;
import cucumber.api.java.en.Given;
import ui.pages.admin.LoginAdminPage;

/**
 * User: jeancarlorodriguez
 * Date: 12/7/15
 * Time: 8:34 PM
 */
public class LoginAdmin {

    @Given("^I'm logged in with the user \"([^\\\"]*)\" in the admin page$")
    public void imLoggedInWithTheUSer(String userName){
        LoginAdminPage loginAdminPage = new LoginAdminPage();
        String user = ExternalVariablesManager.getInstance().getAdminUserName();
        String password = ExternalVariablesManager.getInstance().getAdminUserPassword();

        if(userName.equalsIgnoreCase("admin")){
            loginAdminPage.loginSuccessful(user, password);
        }
    }
}
