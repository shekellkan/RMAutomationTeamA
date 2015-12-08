package steps;

import cucumber.api.java.en.Given;
import ui.pages.admin.MainAdminPage;

/**
 * User: jeancarlorodriguez
 * Date: 12/7/15
 * Time: 8:41 PM
 */
public class Resource {
    MainAdminPage mainAdminPage;
    public Resource(MainAdminPage mainAdminPage)
    {
        this.mainAdminPage = mainAdminPage;
    }

    @Given("^I go to Resources page$")
    public void iGoToResourcesPage()
    {
        mainAdminPage.getLeftMenuPage().goToResources();
    }
}
