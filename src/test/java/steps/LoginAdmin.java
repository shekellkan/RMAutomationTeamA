package steps;

import Framework.ExternalVariablesManager;
import cucumber.api.java.en.Given;
import ui.PageTransporter;
import ui.common.CommonMethods;
import ui.pages.admin.LoginAdminPage;

/**
 * User: Jean Carlo Rodriguez
 * Date: 12/7/15
 * Time: 8:34 PM
 */
public class LoginAdmin {

    @Given("^I'm logged in with the user \"([^\\\"]*)\" in the admin page$")
    public void imLoggedInWithTheUSer(String userName){
        if(!CommonMethods.isUserLoginInAdminPage())
        {
            LoginAdminPage loginAdminPage = new LoginAdminPage();
            String user = ExternalVariablesManager.getInstance().getAdminUserName();
            String password = ExternalVariablesManager.getInstance().getAdminUserPassword();

            if(userName.equalsIgnoreCase("admin")){
                loginAdminPage.loginSuccessful(user, password);
            }
        }else{
            PageTransporter.getInstance().goToAdminMainPage();
        }
    }
}
