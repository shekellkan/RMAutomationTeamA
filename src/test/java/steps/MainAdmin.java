package steps;

import cucumber.api.java.en.Given;
import ui.pages.admin.MainAdminPage;

/**
 * User: jeancarlorodriguez
 * Date: 12/9/15
 * Time: 12:09 PM
 */
public class MainAdmin {
    MainAdminPage mainAdminPage;

    public MainAdmin(MainAdminPage mainAdminPage) {
        this.mainAdminPage = mainAdminPage;
    }

    @Given("^I go to Resources page$")
    public void iGoToResourcesPage()
    {
        mainAdminPage.getLeftMenuPage().goToResources();
    }

    @Given("^I go to Conference Rooms page$")
    public void iGoToRoomsPage()
    {
        mainAdminPage.getLeftMenuPage().goToRooms();
    }
}
