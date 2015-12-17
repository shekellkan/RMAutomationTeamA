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
    private static PageTransporter pageTransporter;

    public LoginAdmin() {
        pageTransporter = PageTransporter.getInstance();
    }

    @Given("^I'm logged in the admin page$")
    public void imLoggedInWithTheUSer(){

        if (pageTransporter.imInTheRMATabletPage()){
            loginInAdminPage();
        }else{
            if(CommonMethods.isUserLoginInAdminPage()){
                pageTransporter.goToAdminMainPage();
            }else{
                loginInAdminPage();
            }
        }
    }

    /**
     * go to login admin page and then log in
     */
    public void loginInAdminPage(){

        LoginAdminPage loginAdminPage = pageTransporter.goToLoginAdminPage();
        String user = ExternalVariablesManager.getInstance().getAdminUserName();
        String password = ExternalVariablesManager.getInstance().getAdminUserPassword();
        loginAdminPage.loginSuccessful(user, password);
    }
}
