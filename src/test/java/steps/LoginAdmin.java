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
        String user = ExternalVariablesManager.getInstance().getAdminUserName();
        String password = ExternalVariablesManager.getInstance().getAdminUserPassword();

        PageTransporter pageTransporter = PageTransporter.getInstance();
        if(!pageTransporter.imInTheRMAdminPage())
            pageTransporter.goToLoginAdminPage();
        //login
        else
        if(!CommonMethods.isUserLoginInAdminPage())
        {
            LoginAdminPage loginAdminPage = new LoginAdminPage();

//            if(userName.equalsIgnoreCase("admin")){
                loginAdminPage.loginSuccessful(user, password);
//            }
        }else{
            pageTransporter.goToAdminMainPage();
        }
    }
}
