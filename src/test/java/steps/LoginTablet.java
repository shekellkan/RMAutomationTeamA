package steps;

import Framework.ExternalVariablesManager;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import entities.RoomEntity;
import ui.PageTransporter;
import ui.common.CommonMethods;
import ui.pages.tablet.LoginTabletPage;
import ui.pages.tablet.MainTabletPage;

/**
 * Created by MiguelTerceros on 12/10/2015.
 */
public class LoginTablet {
    LoginTabletPage loginTabletPage;
    private static PageTransporter pageTransporter;
    MainTabletPage mainTabletPage;
    String userName;
    String userPasswordTablet;
    String serviceURL;
    private RoomEntity roomEntity;

    public LoginTablet(RoomEntity roomEntity){
        this.roomEntity = roomEntity;
        pageTransporter = PageTransporter.getInstance();
    }

    @Given("^I'm logged in the tablet page$")
    public void loggedInWithTheUserInTheTabletMainPage(){
        if (pageTransporter.imInTheRMAdminPage()){
            CommonMethods.logoutFromAdminPage();
            loginInTabletPage();
        }else{
            if(CommonMethods.isUserLoginInTabletPage()){
                pageTransporter.goToTabletMainPage();
            }else{
                loginInTabletPage();
            }
        }
    }

    /**
     * Go to login tablet page and then log in
     */
    private void loginInTabletPage() {
        userName = ExternalVariablesManager.getInstance().getTabletUserName();
        userPasswordTablet = ExternalVariablesManager.getInstance().getTabletUserPassword();
        serviceURL = ExternalVariablesManager.getInstance().getRoomManagerService();

        loginTabletPage = pageTransporter.goToLoginTabletPage();
        loginTabletPage.LoginTablet(serviceURL, userName, userPasswordTablet);
    }

    @And("^I select the room \"([^\"]*)\"$")
    public void selectTheRoom(String nameRoom){
        if(!CommonMethods.isUserLoginInTabletPage()){
            mainTabletPage = loginTabletPage.selectRoom(nameRoom);
            roomEntity.setDisplayName(nameRoom);
        }
    }
}
