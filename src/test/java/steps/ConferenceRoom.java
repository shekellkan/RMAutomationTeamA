package steps;

import cucumber.api.java.en.Then;
import entities.ResourceEntity;
import org.testng.Assert;
import ui.pages.admin.ConferenceRoomsPage;

/**
 * User: Jean Carlo Rodriguez
 * Date: 12/9/15
 * Time: 10:24 AM
 */
public class ConferenceRoom {
    ResourceEntity resourceEntity;
    ConferenceRoomsPage conferenceRoomsPage;
    public ConferenceRoom(ResourceEntity resource)
    {
        resourceEntity = resource;
    }


    @Then("^the Resource should be displayed as a button in the Conference Room page header$")
    public void theResourceShouldBeDisplayAsAButtonInTheConferenceRoomPageHeader()
    {
        conferenceRoomsPage = new ConferenceRoomsPage();
        boolean expectedResult = true;
        Assert.assertEquals(conferenceRoomsPage.isResourceButtonPresent(resourceEntity),expectedResult);
    }
}