package steps;

import Framework.ExternalVariablesManager;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import ui.PageTransporter;
import ui.common.CommonMethods;
import ui.pages.tablet.LoginTabletPage;
import ui.pages.tablet.MainTabletPage;

/**
 * Created by MiguelTerceros on 12/10/2015.
 */
public class LoginTablet {
    LoginTabletPage loginTabletPage;
    PageTransporter pageTransporter = PageTransporter.getInstance();
    MainTabletPage mainTabletPage;
    String userName;
    String userPasswordTablet;
    String serviceURL;

    @Given("^I'm logged in the tablet page$")
    public void loggedInWithTheUserInTheTabletMainPage(){

        PageTransporter pageTransporter = PageTransporter.getInstance();
        if(!pageTransporter.imInTheRMATabletPage())
            pageTransporter.goToLoginTabletPage();

        if(!CommonMethods.isUserLoginInTabletPage()){
                userName = ExternalVariablesManager.getInstance().getTabletUserName();
                userPasswordTablet = ExternalVariablesManager.getInstance().getTabletUserPassword();
                serviceURL = ExternalVariablesManager.getInstance().getRoomManagerService();

                loginTabletPage = pageTransporter.goToLoginTabletPage();
                loginTabletPage.LoginTablet(serviceURL, userName, userPasswordTablet);
        }
        else{
            pageTransporter.goToTabletMainPage();
        }
    }

    @And("^I select the room \"([^\"]*)\"$")
    public void selectTheRoom(String nameRoom){
        if(!CommonMethods.isUserLoginInTabletPage()){
            mainTabletPage = loginTabletPage.selectRoom(nameRoom);
        }
    }
}
