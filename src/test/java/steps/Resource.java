package steps;

import db.DBResourcesMethods;
import entities.ResourceEntity;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import ui.pages.admin.MainAdminPage;
import ui.pages.admin.ResourcesPage;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Jean Carlo Rodriguez
 * Date: 12/7/15
 * Time: 8:41 PM
 */
public class Resource {
    MainAdminPage mainAdminPage;
    ResourceEntity resourceEntity;
    ResourcesPage resourcesPage;
    static String criteria;
    public Resource(MainAdminPage mainAdminPage, ResourceEntity resourceEntity)
    {
        this.mainAdminPage = mainAdminPage;
        this.resourceEntity = resourceEntity;
    }

    @When("^I create a Resource with values: \"([^\\\"]*)\",\"([^\\\"]*)\",\"([^\\\"]*)\" and \"([^\\\"]*)\"$")
    public void iCreateAResourceWithValues(String name, String displayName, String description, String icon)
    {
        resourcesPage = new ResourcesPage();
        resourceEntity.setAllFields(name, displayName, description, icon);
        resourcesPage.goToAddNewResource()
                .createAResource(resourceEntity);
        //walk around step to avoid the resource issue(at create a resources the list of resource is empty)
        mainAdminPage.getLeftMenuPage().goToLocations();
        mainAdminPage.getLeftMenuPage().goToResources();
    }

    @Then("^the Resource is displayed in the list of Resources$")
    public void theResourceIsDisplayedInTheListOfResources()
    {
        String actualName = resourcesPage.getName(resourceEntity.getName());
        String actualDisplayName = resourcesPage.getDisplayName(resourceEntity.getName());
        String actualIconName = resourcesPage.getIconName(resourceEntity.getName());

        Assert.assertEquals(actualName, resourceEntity.getName());
        Assert.assertEquals(actualDisplayName, resourceEntity.getDisplayName());
        Assert.assertEquals(actualIconName,"fa "+ resourceEntity.getIconName());
    }

    @Then("^the Resource is not longer displayed in the Resource list$")
    public void theResourceIsNotLongerDisplayedInTheResourceList()
    {
        boolean actualResult = resourcesPage.isResourceInTheResourceList(resourceEntity);
        Assert.assertTrue(actualResult);
    }

    @When("^I remove the Resource$")
    public void iRemoveTheResource()
    {
        resourcesPage.removeResource(resourceEntity).ClickOnRemoveButton();
        //walk around step to avoid the resource issue(at create a resources the list of resource is empty)
        mainAdminPage.getLeftMenuPage().goToLocations();
        mainAdminPage.getLeftMenuPage().goToResources();
    }

    @When("^I filter the Resources with the criteria \"([^\\\"]*)\"$")
    public void iFilterTheResourcesWithTheCriteria(String byCriteria)
    {
        resourcesPage = new ResourcesPage();
        resourcesPage.setCriteria(byCriteria);
        criteria = byCriteria;

    }

    @Then("^the result of filter should be the same Resources for the UI and the DB$")
    public void theResultOfFilterShouldBeTheSameResourcesFotTheUIAndTheDB()
    {
        DBResourcesMethods dbResourcesMethods = new DBResourcesMethods();
        ArrayList<String> actualResult = resourcesPage.getActualTheListOfResources();
        ArrayList<String> expectedResult = dbResourcesMethods.likeFilterByCriteria("name",criteria);

        Assert.assertEqualsNoOrder(actualResult.toArray(),expectedResult.toArray());

        mainAdminPage.getLeftMenuPage().goToLocations();
    }

}
