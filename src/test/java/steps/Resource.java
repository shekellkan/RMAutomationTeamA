package steps;

import Models.ResourceModel;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import ui.pages.admin.MainAdminPage;
import ui.pages.admin.ResourcesPage;

/**
 * User: Jean Carlo Rodriguez
 * Date: 12/7/15
 * Time: 8:41 PM
 */
public class Resource {
    MainAdminPage mainAdminPage;
    ResourceModel resourceModel;
    public Resource(MainAdminPage mainAdminPage,ResourceModel resourceModel)
    {
        this.mainAdminPage = mainAdminPage;
        this.resourceModel = resourceModel;
    }

    @Given("^I go to Resources page$")
    public void iGoToResourcesPage()
    {
        mainAdminPage.getLeftMenuPage().goToResources();
    }

    @When("^I create a Resource with values: \"([^\\\"]*)\",\"([^\\\"]*)\",\"([^\\\"]*)\" and \"([^\\\"]*)\"$")
    public void iCreateAResourceWithValues(String name, String displayName, String description, String icon)
    {
        mainAdminPage.getLeftMenuPage()
                .goToResources()
                .goToAddNewResource()
                .createAResource(name, displayName, description, icon);
        resourceModel.fillAllFields(name, displayName, description, icon);
        //walk around step to avoid the resource issue(at create a resources the list of resource is empty)
        mainAdminPage.getLeftMenuPage().goToLocations();
        mainAdminPage.getLeftMenuPage().goToResources();
    }

    @Then("^the Resource is displayed in the list of Resources$")
    public void theResourceIsDisplayedInTheListOfResources()
    {
        ResourcesPage resourcesPage = new ResourcesPage();
        String actualName = resourcesPage.getName(resourceModel.getName());
        String actualDisplayName = resourcesPage.getDisplayName(resourceModel.getName());
        String actualIconName = resourcesPage.getIconName(resourceModel.getName());

        Assert.assertEquals(actualName,resourceModel.getName());
        Assert.assertEquals(actualDisplayName,resourceModel.getDisplayName());
        Assert.assertEquals(actualIconName,"fa "+resourceModel.getIconName());
    }
}
